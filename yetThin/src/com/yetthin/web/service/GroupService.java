package com.yetthin.web.service;

import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupreq;

public interface GroupService {
	String getRecommend(String nameOrid,String path);
	String getAnalyse(String nameOrid);
	String getComponent(String nameOrid);
	String getDetail(String nameOrid,String path);
	String stockOfGroupSave(StockOfGroup stockOfGroup);
	String getStockofGroup(StockOfGroupreq req);
	String getStockTypeList(int stockType);
	String getSummarize(int pageNum, int pageSize);
	String getStockType();
	String saveRecommend(String groupNameOrId, String belongId,
			String upRecommendUserId, String context,String userId);
	
}
