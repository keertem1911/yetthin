package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.Group;
import com.yetthin.web.domain.GroupAnalyse;
import com.yetthin.web.domain.GroupComponent;
import com.yetthin.web.domain.GroupRecommend;
import com.yetthin.web.domain.HeroIncome;
import com.yetthin.web.domain.Model2Info;
import com.yetthin.web.domain.RecommendInfo;
import com.yetthin.web.domain.StockOfGroup;
import com.yetthin.web.domain.StockOfGroupget;
import com.yetthin.web.domain.StockOfGroupreq;
import com.yetthin.web.domain.StockType;
import com.yetthin.web.domain.Summarize;
import com.yetthin.web.domain.UserGroups;

public interface GroupMapper {
	GroupRecommend getRecommendByid(String id);

	GroupAnalyse getAnalyseByid(String id);

	GroupComponent getComponentByid(String id);

 

	GroupRecommend getRecommendByname(String name);

	GroupAnalyse getAnalyseByname(String name);

	GroupComponent getComponentByname(String name);

//	Group getDetailByname(String name);

	int insertStockOfGroup(StockOfGroup stockOfGroup);

	List<StockOfGroupget> getStockOfGroup(StockOfGroupreq req);

	int getTotlePageSummarize();

	List<Summarize> getSummarize(int i, int j);

	List<StockType> getStockType();

	List<Model2Info> getConpetType();

	 

	int insertRecommend(RecommendInfo info);

	int deleteRecommend(String id);
/**
 * group database
 * @param groupId
 * @return
 */
	int deleteByPrimaryKey(String groupId);

	int insert(Group record);

	int insertSelective(Group record);

	List<UserGroups> selectByPrimaryKeySimple(Map<String, Object> map);
	Group selectByPrimaryKey(String groupId);

	int updateByPrimaryKeySelective(Group record);

	int updateByPrimaryKey(Group record);

	int getTotlePageUserGroups(String userId);

	int getTotlePageUserAll();

	Group selectGroupByUserId(Map<String, String> map);

	List<Group> selectGroupsAll();

	List<HeroIncome> selectByHeroIncome(Map<String, Integer> map);

	 List<Group> selectByUserId(String userId);
	 /**
	  * 根据 组合id 列表查找 祝贺列表 并且分页
	  * @param map
	  * @return
	  */
	List<UserGroups> selectByGroupIds(Map<String, Object> map);
}
