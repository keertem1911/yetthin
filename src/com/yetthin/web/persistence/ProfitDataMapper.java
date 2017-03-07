package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.CurrentValue;
import com.yetthin.web.domain.ProfitData;
import com.yetthin.web.domain.RecommendList;

public interface ProfitDataMapper {
    int deleteByPrimaryKey(String profitdataId);

    int insert(ProfitData record);

    int insertSelective(ProfitData record);

    ProfitData selectByPrimaryKey(String profitdataId);

    int updateByPrimaryKeySelective(ProfitData record);

    int updateByPrimaryKey(ProfitData record);

	List<ProfitData> selectByGroupIdFromDay(String groupId);
	List<ProfitData> selectByGroupId(String groupId);

	ProfitData selectByGroupIdFromCuurentDay(String groupId);

	ProfitData selectByGroupIdFromLastMonth(String groupId);

	ProfitData selectByGroupIdFromNear3Month(String groupId);

	List<ProfitData> getCurrentIncome(Map<String, Object> map);

	List<RecommendList> getIncomeRecommendList(Map<String, Object> map);

	List<RecommendList> selectByGroupIdFromLastMonthList(Map<String, Object> map);

	int deleteByGroupId(String groupId);

	List<RecommendList> selectByGroupIdFromTotleIncomeList(Map<String, Object> map);

	ProfitData selectByGroupIdFromCreateTime(String groupId);

}