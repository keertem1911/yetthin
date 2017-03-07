package com.yetthin.web.persistence;

import com.yetthin.web.domain.ReasonWord;

public interface ReasonWordMapper {
    int deleteByPrimaryKey(String reasonwordId);

    int insert(ReasonWord record);

    int insertSelective(ReasonWord record);

    ReasonWord selectByPrimaryKey(String reasonwordId);

    int updateByPrimaryKeySelective(ReasonWord record);

    int updateByPrimaryKey(ReasonWord record);

	int deleteByGroupId(String groupId);
}