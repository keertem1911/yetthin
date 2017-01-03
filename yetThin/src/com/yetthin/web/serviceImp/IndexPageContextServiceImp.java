package com.yetthin.web.serviceImp;

import static java.util.stream.Collectors.toList;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.domain.CurrentIncome;
import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.HeroIncome;
import com.yetthin.web.domain.ProfitData;
import com.yetthin.web.domain.RecommendList;
import com.yetthin.web.domain.StockInfo;
import com.yetthin.web.domain.UserGroups;
import com.yetthin.web.persistence.GroupMapper;
import com.yetthin.web.persistence.IndexPageContextMapper;
import com.yetthin.web.persistence.MyAgreeMapper;
import com.yetthin.web.persistence.ProfitDataMapper;
import com.yetthin.web.persistence.ShareMapper;
import com.yetthin.web.persistence.UserMapper;
import com.yetthin.web.service.IndexPageContextService;
@Service("IndexPageContextService")
public class IndexPageContextServiceImp extends BaseService implements IndexPageContextService,
ValueFormatUtil{
	
	@Resource
	private IndexPageContextMapper indexPageContextMapper;
	@Resource
	private GroupMapper groupMapper;
	@Resource
	private ProfitDataMapper profitDataMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	private ShareMapper shareMapper;
	
	//IncomeType(0-月,1-年,2-优选,3-总)
	@Resource
	private JtdoaAPIDao jtdoaAPIDao;
	
	@Resource
	private MyAgreeMapper myAgreeMapper;
	@Override
	public String getIncomeRecommendList(int type, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		int totlePageSize=2;
		String json="\"value\":[]";
	 	int totlePage=groupMapper.getTotlePageUserAll();
	 	 
		int []pageA=partitionPage(totlePage, pageNum, pageSize);
		Map<String, Object> map = new HashMap<>();
		List<RecommendList> list=null;
		if(type<2){
		map.put("type", type);
		map.put("begin", pageA[0]);
		map.put("end", pageA[1]);
		LocalDate endDate= LocalDate.now();
		LocalDate beginDate=null;
		if(type==0) beginDate= endDate.minusMonths(1);
		else beginDate=endDate.plusYears(1);
		
		map.put("beginTime", beginDate.format(format_yyyy_MM_dd));
		map.put("endTime", endDate.format(format_yyyy_MM_dd));
		 list =profitDataMapper.selectByGroupIdFromLastMonthList(map);
		 list = list.stream().sorted((d1,d2)->{
				double p1= Double.parseDouble(d1.getIncomeRatio());
				double p2= Double.parseDouble(d2.getIncomeRatio());
				return (int) Math.floor(p2-p1);
			}).collect(Collectors.toList());
		}else if(type==2){
			list =null;
		}else{
			map.put("begin", pageA[0]);
			map.put("end", pageA[1]);
			list= profitDataMapper.selectByGroupIdFromTotleIncomeList(map);
			list = list.stream().sorted((d1,d2)->{
				double p1= Double.parseDouble(d1.getIncomeRatio());
				double p2= Double.parseDouble(d2.getIncomeRatio());
				return (int) Math.floor(p2-p1);
			}).collect(Collectors.toList());
		 
		}
		json  =tojson(list);
	 	 
		json="{\"currentPage\":\""+pageNum+"\",\"totlePageSize\":\""+
		totlePageSize+"\","+json+"}";
		return json;
	}
	
	/**
	 * 
	 * @param pageNum  每页的数目
	 * @param pageSize 页码数
	 * @return
	 */
	@Override
	public String getBestIncomeList(int pageNum,int pageSize,String path) {
		// TODO Auto-generated method stub
		System.out.println(pageNum+" "+pageSize);
		int totlePage=userMapper.getUserCount();
		int []pageArr=partitionPage(totlePage, pageNum, pageSize);
		Map<String, Integer> map =new HashMap<>();
		map.put("begin", pageArr[0]);
		map.put("end", pageSize);
		System.out.println(pageArr[0]+"     "+pageArr[1]);
		List<HeroIncome> list=groupMapper.selectByHeroIncome(map);
		list =list.stream().
		sorted(
				(d1,d2)->{
					double price1 =Double.parseDouble(d1.getNear3MonthIncome());
					double price2 =Double.parseDouble(d2.getNear3MonthIncome());
					
				return 	price1<price2?1:-1;
					}).collect(Collectors.toList());
		String json="\"value\":[]";
		if(list!=null)
		json =tojson(list);
		json =json.replace(",}", "}");
		json="{\"currentPage\":\""+pageNum+"\",\"totlePageSize\":\""+
				totlePage+"\","+json+"}";
		return json;
	}
	@Override
	public String getCurrentIncomeList(String groupNameOrId, int type) {
		// TODO Auto-generated method stub
 
		CurrentIncome income=new CurrentIncome();
		Map<String, Object> map=new HashMap<>();
		// type = 0 | 1
		 
		map.put("groupId", groupNameOrId);
		List<CurrentValue> values= new ArrayList<>();
		List<ProfitData> lists =null;
		Long  init_share =  shareMapper.countSumStartFundByGroupId(groupNameOrId);
		double lastCurrentIncome =init_share;
		if(type==0||type==1){
			Calendar calendar = Calendar.getInstance();  
			// 设置时间,当前时间不用设置  
			// 设置日期为本月最大日期
			String begin=null;
			String end =null;
			LocalDate firstDate=null;
			LocalDate lastDate=null;
			 LocalDate nowDate = LocalDate.now();
			if(type==1){
			 
				lastDate= nowDate.with(TemporalAdjusters.lastDayOfMonth());
				firstDate= nowDate.with(TemporalAdjusters.firstDayOfMonth());
			end= lastDate.format(format_yyyy_MM_dd);
			 begin =firstDate.format(format_yyyy_MM_dd);
		}else{
				firstDate= nowDate.with(ChronoField.DAY_OF_WEEK,1);
				lastDate= nowDate.with(ChronoField.DAY_OF_WEEK,5);
			 begin =firstDate.format(format_yyyy_MM_dd);
			  end =lastDate.format(format_yyyy_MM_dd);
		}
			map.put("begin", begin);
			map.put("end", end);
		lists =profitDataMapper.getCurrentIncome(map);
		}
		else if(type==3) 
			lists= profitDataMapper.selectByGroupId(groupNameOrId);
		if(lists !=null)
		if(type==0&&lists.size()!=0){ // 本周收益 list
			int index =0;
			for (int i = 0; i < lists.size(); i++) {
				LocalDate date =  LocalDate.parse(lists.get(i).getProfitdataTime());
				int weekDay = date.getDayOfWeek().getValue();
				if(weekDay==1){
				index =i;
				break;
				}
			}
			for (int i = index; i < lists.size(); i++) {
				CurrentValue value = new CurrentValue();
				double v = lists.get(i).getProfitdataShareFundNum();
				value.setIncomeRate(format_double_2.format(100*(v-lastCurrentIncome)/lastCurrentIncome));
				value.setIndexWaveRate("0");
				value.setTime(lists.get(i).getProfitdataTime());
				values.add(value);
			}
		}
		else if(type==1&&lists.size()!=0){ // 本月收益 list
			int index =0;
			for (int i = 0; i < lists.size(); i++) {
				LocalDate date =  LocalDate.parse(lists.get(i).getProfitdataTime());
				int month = date.getDayOfMonth();
				if(month<28){
				index =i;
				break;}
			}
			 
			for (int i = index; i < lists.size(); i++) {
				CurrentValue value = new CurrentValue();
				double v = lists.get(i).getProfitdataShareFundNum();
				value.setIncomeRate(format_double_2.format(100*(v-lastCurrentIncome)/lastCurrentIncome));
				value.setIndexWaveRate("0");
				value.setTime(lists.get(i).getProfitdataTime());
				values.add(value);
			}
		} 
		if(type==3){
			 
			
			List<ProfitData> createFromLists= new ArrayList<>();
			for (int i = 0; i < lists.size(); i++) {
				createFromLists.add(lists.get(i));
			}
			for (int i = 0; i < createFromLists.size(); i++) {
				CurrentValue value = new CurrentValue();
				double v = createFromLists.get(i).getProfitdataShareFundNum();
				value.setIncomeRate(format_double_2.format(100*(v-lastCurrentIncome)/lastCurrentIncome));
				value.setIndexWaveRate("0");
				value.setTime(createFromLists.get(i).getProfitdataTime());
				values.add(value);
			}
			
		}
		values= values.stream().sorted((d1,d2)->d1.getTime().compareTo(d2.getTime())).collect(Collectors.toList());
		
		income.setCurrentValue(values);
		income.setType(Integer.toString(type));
		
		String json =JSON.toJSONString(income);
		return json;
	}

	@Override
	public String getUserGroups(String userId, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		List<UserGroups> lists=null; 
		int totlePage =groupMapper.getTotlePageUserGroups(userId);
		if(totlePage!=0){
		int [] pageA=partitionPage(totlePage, pageNum, pageSize);
		Map<String, Object> map = new HashMap<>();
		map.put("userId", userId);
		map.put("begin", pageA[0]);
		map.put("end", pageA[1]);
		lists  =groupMapper.selectByPrimaryKeySimple(map);
		if(lists.size()>=0){
			lists = lists.stream().map(d->{
				d.setRecommentNum("0");
				double sum =0;
				String income =d.getImcomeRatio();
				double cnt= 0l;
				if(!"0".equals(income.trim())){
				cnt= Long.parseLong(income)-d.getGroupInitMoney();
				 
				}
				if(d.getGroupInitMoney()!=0&&cnt!=0)
					sum = cnt/d.getGroupInitMoney();
				d.setImcomeRatio(format_double_3.format(sum));
				return d;
			}).collect(Collectors.toList());
			lists= lists.stream().map(g->{
				int count = myAgreeMapper.selectCountByGroupId(g.getGroupId());
				g.setMyAgreeNum(count+"");
				return g;
			}).collect(toList());
		}
		}
		String json =tojson(lists);
		
		json="{\"currentPage\":\""+pageNum+"\",\"totlePageSize\":\""+
				totlePage+"\","+json+"}";
		json= json.replaceAll(",{2,10}", ",");
		return json;
	}
	private	String getStockBySearchStockCode(String stockCode,int limitNum,boolean master){
		Map<String, Object > map=new HashMap<>();
		map.put("stockCode", "%"+stockCode+"%");
		map.put("num",limitNum);
		List<StockInfo> stokelimites= indexPageContextMapper.getStockBySearchLike(map);
		List<String> stocks =stokelimites.parallelStream().map(StockInfo::getStockCode).collect(Collectors.toList());
		String values=jtdoaAPIDao.StockIsMasterByStockCodeToString(stocks,master);
		return values;
	}
	
	@Override
	public String getStockBySearchLike(String stockCode, int limitNum,
			boolean isSearch,boolean master) {
		// TODO Auto-generated method stub
		String json  =null;
		if(isSearch)
		json =getStockBySearchStockCode(stockCode, limitNum,master);
		else json =jtdoaAPIDao.StockIsMasterByStockCodeToString(Arrays.asList(stockCode), master);
		
		return "{"+json+"}";
	}

	@Override
	public String searchStockCodeWithPrice(String stockCode, int limitNum) {
		// TODO Auto-generated method stub
		String json = getStockBySearchStockCode(stockCode, limitNum,false);
		 
		 return "{"+json+"}";
	}

}
