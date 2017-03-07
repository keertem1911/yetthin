package com.yetthin.web.persistence;

import com.yetthin.web.domain.Agree;

public interface AgreeMapper {
    int deleteByPrimaryKey(Integer agreeId);

    int insert(Agree record);

    int insertSelective(Agree record);

    Agree selectByPrimaryKey(Integer agreeId);

    int updateByPrimaryKeySelective(Agree record);

    int updateByPrimaryKey(Agree record);
}