package com.yetthin.test;

import net.sf.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		String s="{\'value\':{'groupName':'新能源汽车','emotionIndex':'75','totleIncome':'87.53','evaluateLevel':'中','dayIncome':'0.21','monthIncome':'1.22','netIncome':'1.55','userImg':'http://222.24.62.77:8080/yetThin/image/user-w23w2dqwer12.jpg','userName':'王德胜','userId':'w23w2dqwer12','vipFlag':'1','belongDepart':'前景私募有限公司','near3MonthIncome':'112.3','latestChangeShareTime':'2015-01-21'}}";
		JSONObject json =JSONObject.fromObject(s);
		System.out.println(json.getString("value"));
		
	}
}
