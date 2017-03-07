package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.CurrentIncome;
import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.GroupStockByStratgyLable;
import com.yetthin.web.domain.HeroIncome;
import com.yetthin.web.domain.RecommendList;
import com.yetthin.web.domain.StockInfo;
import com.yetthin.web.domain.StockLable;
import com.yetthin.web.domain.UserGroups;
import com.yetthin.web.domain.stockKempty;

public interface IndexPageContextMapper {
 
	 
 
	int  getTotlePageCurrentIncome(int type);
	 
	int getTotlePageUserGroups(String userName);
	List<UserGroups> getUserGroups(String userName, int i, int j);
	List<StockInfo> getStockBySearchLike(Map map);
	List<StockLable> getStockByStrategy(GroupStockByStratgyLable lable);
	
}
