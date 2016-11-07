package com.yetthin.web.serviceImp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.catalina.tribes.util.Arrays;
import org.codehaus.jackson.map.ser.ArraySerializers;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.yetthin.web.domain.Evaluation;
import com.yetthin.web.domain.GroupAnalyse;
import com.yetthin.web.domain.GroupComponent;
import com.yetthin.web.domain.GroupDetail;
import com.yetthin.web.domain.GroupRecommend;
import com.yetthin.web.domain.Model2Info;
import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupget;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.domain.StockType;
import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.Summarize;
import com.yetthin.web.domain.UpPersonComment;
import com.yetthin.web.persistence.GroupMapper;
import com.yetthin.web.service.GroupService;
@Service("GroupService")
public class GroupServiceImp extends BaseService implements GroupService{
	
	private static final String [] STOCK_TYPE_INDEX1={"股票板块","期货品种","期权品种","自定义分组"};
	private static final String [][] STOCK_TYPE_INDEX2={{"行业板块","概念板块","市场板块"},{"期货市场","商品期货"},{"指数期权标的","股票期权标的","期货期权标的"}};
	
	private static final String [][]STOCK_TYPE_INDEX3={
			{"有色金属冶炼和压延加工业,专用设备制造业,房屋建筑业,煤炭开采和洗选业,其他服务业,道路运输业,机动车、电子产品和日用产品修理业","概念1,概念2,概念3,","市场1,市场2,市场3,市场4,市场5"},
			{"沪深300,上证50,中证500,恒生指数,H股指数","上期所,大商所,郑商所,上金所"},
			{"50ETF,180ETF(仿真),300ETF(仿真),上证50(仿真)","股票期权1,股票期权2,股票期权3","期货期权1,期货期权2,期货期权3"}};
	private List<Model2Info> readLable(int stockType,int index){
		List<Model2Info> list=new ArrayList<>();
		String [] split=STOCK_TYPE_INDEX3[stockType][index].split(",");
		for (int i = 0; i < split.length; i++) {
			Model2Info info=new Model2Info(i+"",split[i]);
			list.add(info);
		}
		return list;
	}
	@Resource
	private GroupMapper groupMapper;
	/**
	 * 识别参数为name 或 id 
	 * @param nameOrId
	 * @return 0位name　1 为id
	 */
	private boolean  regonizedisName(String nameOrid){
		boolean flag =true;
		if(nameOrid.length()==32) flag=false;
		
		return flag;
	
	}
	@Override
	public String getRecommend(String nameOrid) {
		// TODO Auto-generated method stub
		boolean flag =regonizedisName(nameOrid);
		List<GroupRecommend> lists=null;
//		if(flag) list= groupMapper.getRecommendByname(nameOrid);
//		else list =groupMapper.getRecommendByid(nameOrid);
		lists=new ArrayList<GroupRecommend>();
		Calendar cal =Calendar.getInstance();
		cal.setTime(new Date());
		String [] str= {"天机为什么天天跌？？","高手啊","顶你！！！！！！"};
		String [][] repate= {
				{"变价股神:同问","投智星:你好!组合中没有这支股票"},
				{"吴欢:求带高手","达伟:高手,求联系"},
				{"大街上的人:我也顶~!!!!!!!","投智星:谢谢支持","大街上的人:不客气"}};
		for (int i = 0; i < str.length; i++) {
			GroupRecommend list = new GroupRecommend();
			list.setBelongGroupId("ea193c352fda49de8e34f319ec411960");
			list.setCommentContext(str[i]);
			cal.set(Calendar.HOUR_OF_DAY, 8+i);
			list.setCommentTime(format_hhmm.format(cal.getTime()));
			list.setRecommendPerson("午夜草"+i);
			List<UpPersonComment> ups=new ArrayList<>();
			
			for (int j = 0; j < repate[i].length; j++) {
				UpPersonComment c=new UpPersonComment();
				c.setFromName("午夜草"+i);
				String [] value =repate[i][j].split(":");
				c.setName(value[0]);
				c.setRepateContext(value[1]);
				ups.add(c);
			}
			list.setUpPersonComments(ups);
			list.setVipFlag("1");
			lists.add(list);
		}
		String json =JSON.toJSONString(lists);
		json ="{\"value\":"+json+"}";
		return json;
	}

	@Override
	public String getAnalyse(String nameOrid) {
		// TODO Auto-generated method stub
		GroupAnalyse list =null;
		boolean flag =regonizedisName(nameOrid);
//		if(flag) list=groupMapper.getAnalyseByname(nameOrid);
//		else list =groupMapper.getAnalyseByid(nameOrid);
		list =new GroupAnalyse();
		Evaluation e=new Evaluation();
		e.setNumber("9");
		e.setStatus("涨");
		e.setStatusNum("5");
		Evaluation e1=new Evaluation();
		e1.setNumber("12");
		e1.setStatus("跌");
		e1.setStatusNum("2");
		Evaluation e2=new Evaluation();
		e2.setNumber("2");
		e2.setStatus("整理");
		e2.setStatusNum("6");
		list.setWarningLevel("21");
		list.setNetizen_attention_rate("55");
		list.setMedia_attention_rate("10");
		list.setStockLabelNum(e);
		list.setStrategyAbleNum(e1);
		list.setWarningStockLableNum(e2);
		list.setGroupKindNum("2");
		list.setGroupStrategyCir("年");
		list.setGroupStrategyType("股票 期货");
		list.setGroupCapital("10万到200万");


		String json =JSON.toJSONString(list);
		return json;
	}

	@Override
	public String getComponent(String nameOrid) {
		// TODO Auto-generated method stub
		GroupComponent list= null;
		boolean flag =regonizedisName(nameOrid);
//		if(flag) list= groupMapper.getComponentByname(nameOrid);
//		else list= groupMapper.getComponentByid(nameOrid);
		list=new GroupComponent();
		list.setReferIndustryRatio("[{\"name\":\"军工板块\",\"value\":\"25\"},{\"name\":\"金属板块\",\"value\":\"30\"},{\"name\":\"能源板块\",\"value\":\"15\"},{\"name\":\"新能源\",\"value\":\"30\"}]");
		list.setReferKindRatio("[{\"name\":\"股票\",\"value\":\"25\",\"changeShare\":\"10\"},{\"name\":\"期权\",\"value\":\"30\",\"changeShare\":\"2.4\"},{\"name\":\"外汇\",\"value\":\"15\",\"changeShare\":\"5.7\"},{\"name\":\"期货\",\"value\":\"30\",\"changeShare\":\"21.1\"}]");
		list.setSumFund("1001112.12");
		list.setFreeFund("801112.11");
		String json =tojson(list);

		return json;
	}

	@Override
	public String getDetail(String nameOrid,String path) {
		// TODO Auto-generated method stub
		boolean flag =regonizedisName(nameOrid);
		GroupDetail list= null;
//		if(flag) list= groupMapper.getDetailByname(nameOrid);
//		else list =groupMapper.getDetailByid(nameOrid);
		list=new GroupDetail();
		list.setGroupName("新能源汽车");
		list.setEmotionIndex("75");
		list.setTotleIncome("87.53");
		list.setEvaluateLevel("中");
		list.setDayIncome("0.21");
		list.setMonthIncome("1.22");
		list.setNetIncome("1.55");
		list.setUserName("王德胜");
		list.setUserId("w23w2dqwer12");
		list.setUserImg(getRequestPath(path)+"/image/"+"user-"+list.getUserId()+".jpg");
		list.setVipFlag("1");
		list.setBelongDepart("前景私募有限公司");
		list.setNear3MonthIncome("112.3");
		list.setLatestChangeShareTime("2015-01-21");
		String json =tojson(list);
		
		return json;
	}
	@Override
	public String stockOfGroupSave(StockOfGroup stockOfGroup) {
		// TODO Auto-generated method stub
		String json =null;
//		int status =groupMapper.insertStockOfGroup(stockOfGroup);
		int status =1;
		if(status!=0) json ="{\"status\":\"200\"}";
		else json ="{\"status\":\"500\"}";
		return json;
	}
	@Override
	public String getStockofGroup(StockOfGroupreq req) {
		// TODO Auto-generated method stub
		 
//		List<StockOfGroupget> lists= groupMapper.getStockOfGroup(req);
		List<StockOfGroupget> lists=new ArrayList<>();
		String [] radio={"21.4","22","11.2","1.2","4.3","6.5","8.8"};
		for(int i=1;i<8;++i){
		StockOfGroupget get =new StockOfGroupget();
		get.setStockCode("00000"+i);
		get.setStockName("万科"+i);
		get.setStockRatio(radio[i-1]);
		get.setStockType("0");
		lists.add(get);
		}
		for(int i=1;i<3;++i){
			StockOfGroupget get =new StockOfGroupget();
			get.setStockCode("00021"+i);
			get.setStockName("上证50"+i);
			get.setStockRatio(radio[i+3]);
			get.setStockType("1");
			lists.add(get);
			}
		String json =tojson(lists);
		json ="{"+json+"}";
		return json;
	}
	@Override
	public String getStockTypeList(int stockType) {
		// TODO Auto-generated method stub
		String json =null;
		json ="{\"stockType\":\""+stockType+"\",\"value\":[";
		
		for(int i=0;i<STOCK_TYPE_INDEX2[stockType].length;++i){
			List<Model2Info> lists=null;
//			if(stockType==0&&i==1)
//		 lists= groupMapper.getConpetType();
				
//			else lists =readLable(stockType, i);
			lists =readLable(stockType, i);
		String json1=tojson(lists);
		json1="{\"modelTypeIndex\":\""+i+"\",\"modelTypeName\":\""+
				STOCK_TYPE_INDEX2[stockType][i]+"\","+json1+"}";
		
		json+=json1;
		if(i!=STOCK_TYPE_INDEX2[stockType].length-1) 
			json+=",";
		}
		json +="]}";
		return json;
	}
	@Override
	public String getSummarize(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		int totlePage=2;
	//	totlePage=groupMapper.getTotlePageSummarize();
//		int [] pageA=partitionPage(totlePage, pageNum, pageSize);
	//	List<Summarize> lists =groupMapper.getSummarize(pageA[0],pageA[1]);
		List<Summarize> lists= new ArrayList<>();
		Calendar cal =Calendar.getInstance();
		cal.setTime(new Date());
		for (int i = 0; i < pageSize; i++) {
				Summarize s =new Summarize();
				cal.set(Calendar.DAY_OF_MONTH, i+2);
				s.setCreateTime(format_yyyyMMdd.format(cal.getTime()));
				s.setGroupName("期权组合智能"+(i+1)+"号");
				s.setRecommentNum("7");
				s.setSumIncome(format_double_2.format(9.2+i*0.01+i));
				lists.add(s);
		}
		String json =tojson(lists);
		json ="{\"totlePageSize\":\""+totlePage+"\",\"currentPage\":\""+pageNum+"\","+json+"}";
		return json;
	}
	@Override
	public String getStockType() {
		// TODO Auto-generated method stub
//		List<StockType> list =groupMapper.getStockType();
		List<StockType> list =new ArrayList<StockType>();
		for (int i = 0; i < 3; i++) {
			StockType type =new StockType();
			type.setStockType(i+"");
			type.setTypeName(STOCK_TYPE_INDEX1[i]);
			list.add(type);
		}
		String json =tojson(list);
		return "{"+json+"}";
		
	}
	@Override
	public String saveRecommend(String groupNameOrId, String belongId, String upRecommendUserId, String context) {
		// TODO Auto-generated method stub
		String json =null;
		Map<String, String> map =new HashMap<>();
		map.put("groupNameOrId", groupNameOrId);
		map.put("belongId", belongId);
		map.put("upRecommendUserId", context);
//		int i =groupMapper.saveRecommend(map);
		int i=1;
		if(i!=0) json ="{\"status\":\"200\"}";
		else json ="{\"status\":\"520\"}";
		return json;
	}

	
	 

}
