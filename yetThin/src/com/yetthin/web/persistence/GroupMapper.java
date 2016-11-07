package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.GroupAnalyse;
import com.yetthin.web.domain.GroupComponent;
import com.yetthin.web.domain.GroupDetail;
import com.yetthin.web.domain.GroupRecommend;
import com.yetthin.web.domain.Model2Info;
import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupget;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.domain.StockType;
import com.yetthin.web.domain.Summarize;

public interface GroupMapper {
	GroupRecommend getRecommendByid(String id);
	GroupAnalyse getAnalyseByid(String id);
	GroupComponent getComponentByid(String id);
	GroupDetail getDetailByid(String id);
	GroupRecommend getRecommendByname(String name);
	GroupAnalyse getAnalyseByname(String name);
	GroupComponent getComponentByname(String name);
	GroupDetail getDetailByname(String name);
	int insertStockOfGroup(StockOfGroup stockOfGroup);
	List<StockOfGroupget> getStockOfGroup(StockOfGroupreq req);
	int getTotlePageSummarize();
	List<Summarize> getSummarize(int i, int j);
	 
	List<StockType> getStockType();
	List<Model2Info> getConpetType();
	int saveRecommend(Map<String, String> map);
}
