package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.barData;

public interface barDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(List<barData> record);

    int insertSelective( barData  record);

    List<barData> selectByPrimaryKey(Map map);

    int updateByPrimaryKeySelective(barData record);

    int updateByPrimaryKey(barData record);
    List<barData> getBetweenBeginAndEnd(Map<String, String> map);
}