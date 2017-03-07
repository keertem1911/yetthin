package com.yetthin.web.persistence;

import java.util.List;
import java.util.Map;

import com.yetthin.web.domain.User;

public interface UserMapper {
	int deleteByPrimaryKey(String userId);

	int insert(User record);

	int insertSelective(User record);

	User selectByPrimaryKey(String userId);

	int updateByPrimaryKeySelective(User record);

	int updateByPrimaryKeyWithBLOBs(User record);

	int updateByPrimaryKey(User record);

	List<User> getAllUser();

	User selectByPhoneNum(String phoneNum);

	User selectByPhoneNumAndPassWord(Map<String, String> map);

	User findVerifyEmailByEmail(String email);

	User selectByEmail(String email);

	List<User> lookIdeaText();

	int getUserCount();
}