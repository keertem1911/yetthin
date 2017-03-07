package com.yetthin.web.persistence;

import java.util.List;

import com.yetthin.web.domain.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    List<Admin> getAll();
    
    int countById();
    
    Admin selectByName(String name);
}