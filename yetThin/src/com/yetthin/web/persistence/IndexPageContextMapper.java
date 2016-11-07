package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.CurrentIncome;
import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.HeroIncome;
import com.yetthin.web.domain.RecommendList;
import com.yetthin.web.domain.UserGroups;

public interface IndexPageContextMapper {
	List<RecommendList> getIncomeRecommendList(int type,int begin,int end);
	List<HeroIncome> getBestIncomeList();
	int  getTotlePageIncome(int type);
	int  getTotlePageHero();
	int  getTotlePageCurrentIncome(int type);
	List<CurrentValue> getCurrentIncome(int type,int begin,int end);
	int getTotlePageUserGroups(String userName);
	List<UserGroups> getUserGroups(String userName, int i, int j);
}
