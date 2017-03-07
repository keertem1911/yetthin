package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.IdustryIndex;

public interface IdustryIndexMapper {
    int deleteByPrimaryKey(String industryCode);

    int insert(IdustryIndex record);

    int insertSelective(IdustryIndex record);

    IdustryIndex selectByPrimaryKey(String industryCode);

    int updateByPrimaryKeySelective(IdustryIndex record);

    int updateByPrimaryKey(IdustryIndex record);
    
 
}