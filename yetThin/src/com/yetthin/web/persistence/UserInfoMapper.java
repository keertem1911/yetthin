package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.User;

 

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKeyWithBLOBs(User record);

    int updateByPrimaryKey(User record);
    

    User selectByPhoneNumAndPassWord(User record);
    List<User> getAllUser();
    User selectByPhoneNum(String phoneNum);
    User selectByEmail(String email);
    
    List<User> lookIdeaText();

    User selectByPhoneNumAndPassWord(Map<String, String> map);
	
    User  findVerifyEmailByEmail(String email);
	
	
}