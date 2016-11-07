package com.yetthin.web.service;


public interface IndexPageContextService {
		String getIncomeRecommendList(int type,int pageNum,int pageSize);
		String getBestIncomeList(int pageNum,int pageSize, String path);
		String getCurrentIncomeList(String groupNameOrId,int pageNum,int pageSize,int type);
		String getUserGroups(String userName, int pageNum, int pageSize);
		
}
