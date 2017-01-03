package com.yetthin.web.serviceImp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.domain.Group;
import com.yetthin.web.domain.Share;
import com.yetthin.web.domain.ShareChange;
import com.yetthin.web.persistence.GroupMapper;
import com.yetthin.web.persistence.ShareChangeMapper;
import com.yetthin.web.persistence.ShareMapper;
import com.yetthin.web.service.ShareAndChangeService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service("ShareAndChangeService")
public class ShareAndChangeServiceImp extends BaseService 
	implements ShareAndChangeService, ValueFormatUtil{

	@Resource
	private ShareMapper shareMapper;
	@Resource
	private ShareChangeMapper shareChangeMapper;
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private JtdoaAPIDao jtdoaAPIDao;
	@Override
	public String getShareInfoByGroupId(String groupId) {
		// TODO Auto-generated method stub
		Group group =groupMapper.selectByPrimaryKey(groupId);
		Map<String, Double> share_Start_Currnt = shareMapper.selectSumFundByGroupId(groupId);
		double currentSumFund= group.getGroupInitMoney()-
				share_Start_Currnt.get("start_fund")+share_Start_Currnt.get("current_income");
	
		List<Share> shares = shareMapper.selectByGroupIdList(groupId);
		Function<Share, Share> funInterface =d->{
					double nowPrice=d.getShareCurrentIncome();
					double radio = nowPrice/currentSumFund;
					d.setShareCurrentNum(null);
					d.setShareStartFund(null);
					d.setShareCurrentIncome(Double.parseDouble(format_double_2.format(radio*100)));
					return d;
					};
	shares =	shares.parallelStream().map(funInterface).collect(Collectors.toList());
	String json =JSON.toJSONString(shares);	
	return  "{\"groupId\":\""+groupId+"\",\"values\":"+json+"}";
	
	}

	@Override
	public Share getByShareId(String shareId) {
		// TODO Auto-generated method stub
		
		return shareMapper.selectByPrimaryKey(shareId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int deleteByShareId(String shareId) {
		// TODO Auto-generated method stub
		return shareMapper.deleteByPrimaryKey(shareId);
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int updateAndSaveShare(String groupId, String stockList) {
		// TODO Auto-generated method stub
		System.out.println(stockList);
		System.out.println(groupId);
		JSONObject jsonobject = JSONObject.fromObject("{ \"value\":" + stockList + "}");
		Group group =groupMapper.selectByPrimaryKey(groupId);
		Map<String, Double> share_Start_Currnt = shareMapper.selectSumFundByGroupId(groupId);
		List<Share> oldShares = shareMapper.selectByGroupIdList(groupId);
		/**
		 * 删除清理
		 */
		shareMapper.updateDeleteStatus(groupId);
		long init =(long) group.getGroupInitMoney();
		Double currentSumFund= init-
				share_Start_Currnt.get("start_fund")+share_Start_Currnt.get("current_income");
		
		JSONArray jsonarray = jsonobject.getJSONArray("value");
		// 获取当前股票池
		final Map<String, String> stockCode_Value = jtdoaAPIDao.getAllSymbolValueList();
		 
		List<Share> newShare =new ArrayList<Share>();
		Map<String,String> changePriceDir=new HashMap<>();
//		int deleteN=shareMapper.deleteByGroupId(groupId);
		for (int i = 0; i < jsonarray.size(); ++i) {
			Share s= new Share();
			Object subjson = jsonarray.get(i);
			JSONObject object = JSONObject.fromObject(subjson);
			 
			String isNew = object.getString("stockStatus");
			s.setShareStatus(isNew);
			s.setShareStocklableId((object.getString("stockCode")));
			double fund =currentSumFund*Double.parseDouble(object.getString("stockRatio"))/100; 
			s.setShareStartFund(fund);
			s.setShareCurrentIncome(s.getShareStartFund());
			s.setShareGroupId(group.getGroupId());
			s.setShareStocklableName(object.getString("stockName"));
			double lastPrice=0l;
			String values =stockCode_Value.get(s.getShareStocklableId());
			s.setShareCurrentNum(0.0);
			if(values!=null)
			if(s.getShareStocklableId().split(SPLIT_STR).length>0&&
					!values.split(SPLIT_STR)[0].split("[.]")[0].equals("0")){
			   lastPrice =Double.parseDouble(
					 values.split(SPLIT_STR)[0]
					);
			    
			   changePriceDir.put(s.getShareStocklableId(), values.split(SPLIT_STR)[0]);
			 String number = format_double_3.format(s.getShareStartFund()/ lastPrice);
			s.setShareCurrentNum(Double.parseDouble(number));
      		}
			s.setShareId(getUUID());
			if(lastPrice!=0l)
			newShare.add(s);
		}
		int changeNum=0;
		LocalDateTime datetime=LocalDateTime.now();
		List<ShareChange> shareChanges=null;
		List<Share> updateShares=null;
		/** 
		 * 获取 以股票代码分类的股票 
		 */
		Map<String, List<Share>> mapKeyStockCodeByShare= oldShares.stream().collect(Collectors.groupingBy(Share::getShareStocklableId));
		/**
		 * 分类 区分股票 是 新增还是 修改 还有没有变化的 删除的
		 * 0 未变 1 修改 2 更新 3 删除
		 */
		Map<String,List<Share>> groupByStatusShare = newShare.stream().collect(Collectors.groupingBy(Share::getShareStatus));
		/**
		 * 对未修改的股票直接改变状态位
		 */
		updateShares= groupByStatusShare.get("0");
		if(updateShares!=null){
			updateShares.forEach(share->{
				share.setShareId(mapKeyStockCodeByShare.get(share.getShareStocklableId()).get(0).getShareId());
				shareMapper.updateShareStatusToAble(share.getShareId());
			});
			
			shareChanges=updateShares.parallelStream().map(share->{
				List<Share> oldSharesFrom = mapKeyStockCodeByShare.get(share.getShareStocklableId());
				
				Share s = oldSharesFrom.get(0);
				ShareChange sc= new ShareChange();
				sc.setSharechangeDir("0");
				sc.setSharechangeId(getUUID());
				sc.setSharechangeNum(s.getShareCurrentNum());
				sc.setSharechangePrice((s.getShareStartFund())/(s.getShareCurrentNum()));
				sc.setSharechangeStocklableId(s.getShareStocklableId());
				sc.setSharechangeTime(datetime.format(simple));
				sc.setSharechangeGroupId(s.getShareGroupId());   
				return sc;
			}).collect(Collectors.toList());
			changeNum = shareChangeMapper.insert(shareChanges);
		}
		/**
		 * 对修改的股票 进行更新 
		 */
		updateShares= groupByStatusShare.get("1");
		if(updateShares!=null){
		
		
		  shareChanges=updateShares.parallelStream().map(share->{
			  List<Share> oldSharesFrom = mapKeyStockCodeByShare.get(share.getShareStocklableId());
			  
					Share s = oldSharesFrom.get(0);
					double lastPrice = Double.parseDouble(changePriceDir.get(s.getShareStocklableId()));
					double oldPrice = s.getShareCurrentIncome()/
							s.getShareCurrentNum();
					 if(!format_double_3.format(lastPrice).equals(format_double_3.format(oldPrice)))
						changePriceDir.put(s.getShareStocklableId(), format_double_3.format(lastPrice).compareTo(format_double_3.format(oldPrice))>0?"1":"-1");// 价格上调/下调
					 else
						 changePriceDir.put(s.getShareStocklableId(), "-2");// 未改变价格
			ShareChange sc= new ShareChange();
			sc.setSharechangeDir(changePriceDir.get(share.getShareStocklableId()));
			sc.setSharechangeId(getUUID());
			sc.setSharechangeNum(s.getShareCurrentNum());
			sc.setSharechangePrice((s.getShareStartFund())/s.getShareCurrentNum());
			sc.setSharechangeStocklableId(s.getShareStocklableId());
			sc.setSharechangeTime(datetime.format(simple));
			sc.setSharechangeGroupId(share.getShareGroupId());   
			return sc;
		}).collect(Collectors.toList());
		changeNum = shareChangeMapper.insert(shareChanges);
		
		updateShares.forEach(share->{
			List<Share> oldSharesFrom = mapKeyStockCodeByShare.get(share.getShareStocklableId());
			if(oldSharesFrom!=null&&oldSharesFrom.size()>0){
				Share s = oldSharesFrom.get(0);
				s.updateShare(share,false);
				shareMapper.updateByPrimaryKey(s);
			}
			});
		}
		/**
		 *  对新增的股票进行更新
		 */
		updateShares=groupByStatusShare.get("2");
		if(updateShares!=null){
		int num =shareMapper.insert(updateShares);
		shareChanges=updateShares.parallelStream().map(share->{
			ShareChange sc= new ShareChange();
			sc.setSharechangeDir("0");
			sc.setSharechangeId(getUUID());
			sc.setSharechangeNum(share.getShareCurrentNum());
			sc.setSharechangePrice(Double.parseDouble(stockCode_Value.get(share.getShareStocklableId()).split(SPLIT_STR)[0]));
			sc.setSharechangeStocklableId(share.getShareStocklableId());
			sc.setSharechangeTime(datetime.format(simple));
			sc.setSharechangeGroupId(share.getShareGroupId());
			return sc;
		}).collect(Collectors.toList());
		
		changeNum +=shareChangeMapper.insert(shareChanges);
		}
		/**
		 * 对删除的股票进行标记
		 */
		updateShares=groupByStatusShare.get("3");
		if(updateShares!=null){
			shareChanges=updateShares.parallelStream().map(share->{
				List<Share> oldSharesFrom = mapKeyStockCodeByShare.get(share.getShareStocklableId());
				share.setShareStatus("0");
				
				Share s = oldSharesFrom.get(0);
				ShareChange sc= new ShareChange();
				sc.setSharechangeDir("-3");// 删除
				sc.setSharechangeId(getUUID());
				sc.setSharechangeNum(s.getShareCurrentNum());
				sc.setSharechangePrice((s.getShareStartFund())/s.getShareCurrentNum());
				sc.setSharechangeStocklableId(s.getShareStocklableId());
				sc.setSharechangeTime(datetime.format(simple));
				sc.setSharechangeGroupId(share.getShareGroupId());
				return sc;
			}).collect(Collectors.toList());
			changeNum +=shareChangeMapper.insert(shareChanges);
		}
		return changeNum;
	}
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	@Override
	public int saveShare(Share share) {
		// TODO Auto-generated method stub
		ShareChange sc= new ShareChange();
		sc.setSharechangeStocklableId(share.getShareStocklableId());
		sc.setSharechangeId(getUUID());
		LocalDateTime time =LocalDateTime.now();
		sc.setSharechangeTime(time.format(simple));
		sc.setSharechangeNum(0.0);
		sc.setSharechangePrice((share.getShareCurrentIncome())/share.getShareCurrentNum());
		return shareChangeMapper.insertSelective(sc);
	 
	}
	 
	@Override
	public String getShareChangeListAll(String groupId, String userId) {
		// TODO Auto-generated method stub
		Group group = groupMapper.selectByPrimaryKey(groupId);
		String json ="\"value\":[]";
		if(group.getGroupCreateId().trim().equals(userId.trim())){
//			String date= shareChangeMapper.getLastShareChangeByGroupId(groupId);
			/**
			 * 获取 组合下的仓位变化 
			 */
			List<Share> shareLists= shareMapper.selectAllShareByGroupIdList(groupId);
			/**
			 * 将仓位进行分组 以ShareId
			 */
			Map<String, List<Share>> shareGroupByStockLableId=
					shareLists.stream().collect(Collectors.groupingBy(Share::getShareStocklableId));
			 
			List<ShareChange> shareChangeLists= shareChangeMapper.selectByGroupId(groupId);
			Map<String, List<ShareChange>> shareChangeGroupByTime=shareChangeLists.
					stream().collect(Collectors.groupingBy(ShareChange::getSharechangeTime));
			Set<String> timeSet = shareChangeGroupByTime.keySet();
			/**
			 * 按照降序排列 调仓时间
			 */
			List<String> sortedTime= new LinkedList<String>(timeSet).
				stream().
				sorted((sc1,sc2)->sc2.compareTo(sc1)).collect(Collectors.toList());
			StringBuffer sb= new StringBuffer();
			sb.append("{");
			sb.append("\"groupId\":\""+groupId+"\",");
			sb.append("\"value\":[");
			for(int i=0;i<sortedTime.size();++i){
				sb.append("{");
				sb.append("\"changeTime\":\""+sortedTime.get(i)+"\",");
				sb.append("\"item\":[");
				
				List<ShareChange> sharechageList = shareChangeGroupByTime.get(sortedTime.get(i));
				List<ShareChange> beforeSharechageList=null;
				Map<String, List<ShareChange>> beforeShareChangeListGroupByShareId=null;
			 if(i!=0){
				 beforeSharechageList= shareChangeGroupByTime.get(sortedTime.get(i-1));
					 beforeShareChangeListGroupByShareId=
							beforeSharechageList.stream().collect(Collectors.groupingBy(ShareChange::getSharechangeStocklableId));
			 }
				for(int j =0;j<sharechageList.size();++j){
					ShareChange sc = sharechageList.get(j);
					Share currentShare= shareGroupByStockLableId.get(sc.getSharechangeStocklableId()).get(0);
					int dir = Integer.parseInt(sc.getSharechangeDir());
					if(dir==-2||dir==-1||dir==1||dir==-3){
					sb.append("{");
					sb.append("\"stockCode\":\""+currentShare.getShareStocklableId()+"\",");
					sb.append("\"stockName\":\""+currentShare.getShareStocklableName()+"\",");
					sb.append("\"shareChangeTime\":\""+sc.getSharechangeTime()+"\",");
					if(dir!=-3){
					if(i==0){
						/**
						 * dir -1 跌  0 新增 1 涨  3 删除
						 */
						String lastPrice = format_double_3.format(currentShare.getShareStartFund()/currentShare.getShareCurrentNum());
						sb.append("\"changePricePre\":\""+format_double_3.format(sc.getSharechangePrice())+"\",");
						sb.append("\"changePriceAft\":\""+lastPrice+"\",");
						sb.append("\"changeNumPre\":\""+format_double_1.format(sc.getSharechangeNum())+"\",");
						sb.append("\"changeNumAft\":\""+format_double_1.format(currentShare.getShareCurrentNum())+"\",");
					}else{
						ShareChange beforeShareChange = beforeShareChangeListGroupByShareId.get(sc.getSharechangeStocklableId()).get(0);
						sb.append("\"changePricePre\":\""+format_double_3.format(beforeShareChange.getSharechangePrice())+"\",");
						sb.append("\"changePriceAft\":\""+format_double_3.format(sc.getSharechangePrice())+"\",");
						sb.append("\"changeNumPre\":\""+format_double_1.format(beforeShareChange.getSharechangeNum())+"\",");
						sb.append("\"changeNumAft\":\""+format_double_1.format(sc.getSharechangeNum())+"\",");
					}
					}else if(dir ==-3){
						if(i==0){
							/**
							 * dir -1 跌  0 新增 1 涨  3 删除
							 */
							String lastPrice = format_double_3.format(currentShare.getShareStartFund()/currentShare.getShareCurrentNum());
							sb.append("\"changePricePre\":\""+format_double_3.format(sc.getSharechangePrice())+"\",");
							sb.append("\"changePriceAft\":\""+lastPrice+"\",");
							sb.append("\"changeNumPre\":\""+format_double_1.format(sc.getSharechangeNum())+"\",");
							sb.append("\"changeNumAft\":\""+format_double_1.format(currentShare.getShareCurrentNum())+"\",");
						}else{
							ShareChange beforeShareChange = beforeShareChangeListGroupByShareId.get(sc.getSharechangeStocklableId()).get(0);
							sb.append("\"changePricePre\":\""+format_double_3.format(beforeShareChange.getSharechangePrice())+"\",");
							sb.append("\"changePriceAft\":\""+format_double_3.format(sc.getSharechangePrice())+"\",");
							sb.append("\"changeNumPre\":\""+format_double_1.format(beforeShareChange.getSharechangeNum())+"\",");
							sb.append("\"changeNumAft\":\""+format_double_1.format(sc.getSharechangeNum())+"\",");
						}
					}
					sb.append("\"changeDir\":\""+dir+"\"");                         	
					sb.append("}");
					if(j<sharechageList.size()-1)
						sb.append(",");
					}
				}// for j 
				 
				sb.append("]");
				sb.append("}");
				if(i<sortedTime.size()-1)
					sb.append(",");
			}// for i
			sb.append("]}");
			json =sb.toString();
			json =json.replaceAll("},]", "}]");
		}
		return json;
	}

}
