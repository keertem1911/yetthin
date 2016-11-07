package com.yetthin.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.CurrentIncome;
import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.Evaluation;
import com.yetthin.web.domain.GroupAnalyse;
import com.yetthin.web.domain.GroupRecommend;
import com.yetthin.web.domain.UpPersonComment;

public class JsonTest {
	public static void function1() {
		ArrayList<CurrentValue> lists = new ArrayList<CurrentValue>();
		int totlePage=2;
		int pageNum=2;
		int pageSize =10;
		 Calendar cal=Calendar.getInstance();
		cal.setTime(new Date());
		cal.set(Calendar.DATE, 1); 
		CurrentIncome income=new CurrentIncome();
		income.setCurrentPage(Integer.toString(pageNum));
		income.setTotlePageSize(Integer.toString(pageSize));
		for (int i = 0; i < pageSize; i++) {
			 CurrentValue value=new CurrentValue();
			 value.setIncomeRate("12.2"+i);
			 value.setIndexWaveRate("2.1"+i);
			 value.setTime("2014-01-20");
			 cal.set(Calendar.DATE,i+2);
			 lists.add(value);
		}
		income.setCurrentValue(lists);
		String json =JSON.toJSONString(income);
		System.out.println(json);
	}
	public static void main(String[] args) {
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
			list.setCommentTime("12:00");
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
		System.out.println(json);
	}
}
