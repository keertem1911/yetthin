package com.yetthin.web.persistence;

import com.yetthin.web.domain.PhoneVersion;

public interface PhoneVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PhoneVersion record);

    int insertSelective(PhoneVersion record);

    PhoneVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PhoneVersion record);

    int updateByPrimaryKey(PhoneVersion record);
}