package com.yetthin.web.serviceImp;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.tomcat.jni.Buffer;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.CurrentIncome;
import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.HeroIncome;
import com.yetthin.web.domain.RecommendList;
import com.yetthin.web.domain.UserGroups;
import com.yetthin.web.persistence.IndexPageContextMapper;
import com.yetthin.web.service.IndexPageContextService;
@Service("IndexPageContextService")
public class IndexPageContextServiceImp extends BaseService implements IndexPageContextService{
	
	@Resource
	private IndexPageContextMapper indexPageContextMapper;
	
	
		@Override
	public String getIncomeRecommendList(int type, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		int totlePageSize=2;
//	 	int totlePage=indexPageContextMapper.getTotlePageIncome(type);
//		int []pageA=partitionPage(totlePage, pageNum, pageSize);
//		List<RecommendList> list =indexPageContextMapper.getIncomeRecommendList(type, pageA[0], pageA[1]);
		List<RecommendList> list =new ArrayList<RecommendList>();
		for(int i=0;i<pageSize;++i){
			RecommendList recommend=new RecommendList();
			recommend.setGroupName("新能源汽车");
			recommend.setIncomeRatio(format_double_2.format(10.0-0.1*i));
			recommend.setIncomeType(type);
			recommend.setUserName("王"+i);
			recommend.setUserId("ea193c352fda49de8e34f319ec411960");
			recommend.setRecommendReason("人工智能+智能算法");
			recommend.setVipFlag(i%2);
			recommend.setCreateTime(format_yyyyMMdd.format(new Date()));
			 
			list.add(recommend);
		}
		String json =tojson(list);
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
		int totlePage=2;
//		int totlePage=indexPageContextMapper.getTotlePageHero();
//		int []pageArr=partitionPage(totlePage, pageNum, pageSize);
//		List<HeroIncome> list=indexPageContextMapper.getBestIncomeList();
		List<HeroIncome> list =new ArrayList<HeroIncome>();
		for(int i=0;i<pageSize;++i){
			HeroIncome income=new HeroIncome();
			income.setUserName("李"+i);
			income.setUserId("w23w2dqwer12");
			income.setVipFlag(i%2);
			income.setUserImg(getRequestPath(path)+"/image/user-"+income.getUserId()+".jpg");
			income.setNear3MonthIncome(format_double_2.format(10.2-0.1*i));
			income.setBelongDepart("前景私募股份有限公司"+i);
			 list.add(income);
		}
		String json =tojson(list);
		json="{\"currentPage\":\""+pageNum+"\",\"totlePageSize\":\""+
				totlePage+"\","+json+"}";
		return json;
	}
	@Override
	public String getCurrentIncomeList(String groupNameOrId, int pageNum, int pageSize, int type) {
		// TODO Auto-generated method stub
		int totlePage=2;
//		int totlePage =indexPageContextMapper.getTotlePageCurrentIncome(type);
//		int [] pageA=partitionPage(totlePage, pageNum, pageSize);
//		List<CurrentValue> lists =indexPageContextMapper.getCurrentIncome(type, pageA[0],pageA[1]);
		 
		ArrayList<CurrentValue> lists = new ArrayList<CurrentValue>();

		 Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, 1); 
		CurrentIncome income=new CurrentIncome();
		income.setCurrentPage(Integer.toString(pageNum));
		income.setTotlePageSize(Integer.toString(totlePage));
		for (int i = 0; i < pageSize; i++) {
			 CurrentValue value=new CurrentValue();
			 value.setIncomeRate("12.2"+i);
			 value.setIndexWaveRate("2.1"+i);
			 value.setTime(format_yyyyMMdd.format(cal.getTime()));
			 cal.set(Calendar.DATE,i+2);
			 lists.add(value);
		}
		income.setCurrentValue(lists);
		
		String json =JSON.toJSONString(income);
		return json;
	}

	@Override
	public String getUserGroups(String userName, int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		int totlePage=2;
//		int totlePage =indexPageContextMapper.getTotlePageUserGroups(userName);
//		int [] pageA=partitionPage(totlePage, pageNum, pageSize);
//		List<UserGroups> lists =indexPageContextMapper.getUserGroups(userName,pageA[0],pageA[1]);
//		String json =tojson(lists);
		List<UserGroups> lists= new ArrayList<UserGroups>();
		Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-1);
		for (int i = 0; i < pageSize; i++) {
			UserGroups user =new UserGroups();
			user.setCreateTime(format_yyyyMMdd.format(cal.getTime()));
			user.setGroupName("小韩"+i);
			user.setImcomeRatio(format_double_2.format(8.4+i*0.11));
			lists.add(user);
		}
		String json =tojson(lists);
		json="{\"currentPage\":\""+pageNum+"\",\"totlePageSize\":\""+
				totlePage+"\","+json+"}";
		return json;
	}

}
