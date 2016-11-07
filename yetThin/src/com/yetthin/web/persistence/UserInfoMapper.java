package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.UserInfo;

public interface UserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKeyWithBLOBs(UserInfo record);

    int updateByPrimaryKey(UserInfo record);
    

    UserInfo selectByPhoneNumAndPassWord(UserInfo record);
    List<UserInfo> getAllUser();
    UserInfo selectByPhoneNum(String phoneNum);
    UserInfo selectByEmail(String email);
    
    List<UserInfo> lookIdeaText();

	UserInfo selectByPhoneNumAndPassWord(Map<String, String> map);
	
	UserInfo  findVerifyEmailByEmail(String email);
	
	
}