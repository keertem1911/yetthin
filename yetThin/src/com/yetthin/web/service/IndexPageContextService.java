package com.yetthin.web.service;


public interface IndexPageContextService {
		String getIncomeRecommendList(int type,int pageNum,int pageSize);
		String getBestIncomeList(int pageNum,int pageSize, String path);
		String getCurrentIncomeList(String groupNameOrId,int type);
		String getUserGroups(String userName, int pageNum, int pageSize);
		String getStockBySearchLike(String stockCode, int limitNum,boolean isSearch,boolean master);
		String searchStockCodeWithPrice(String stockCode, int limitNum);
		
}
