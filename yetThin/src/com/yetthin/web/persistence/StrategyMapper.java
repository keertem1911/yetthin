package com.yetthin.web.persistence;

import com.yetthin.web.domain.Strategy;

public interface StrategyMapper {
    int deleteByPrimaryKey(String strategyId);

    int insert(Strategy record);

    int insertSelective(Strategy record);

    Strategy selectByPrimaryKey(String strategyId);

    int updateByPrimaryKeySelective(Strategy record);

    int updateByPrimaryKey(Strategy record);
}