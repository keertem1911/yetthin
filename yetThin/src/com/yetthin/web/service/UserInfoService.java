package com.yetthin.web.service;

import java.util.List;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.domain.User;
 

public interface UserInfoService extends BaseService<User>{
	public abstract String getRegisterVerify(String phoneNum);
	public abstract String forgetPwd(User userinfo);
	public abstract String getforgetPwdVerify(String phoneNum);
	public abstract String updateJpushId(String userId,String JpushID,String JpushType);
	public abstract String updateJpushStatus(String userId,String status,String JpushType);
	public abstract String bindingEmail(String userID,String email,String path);
	public abstract String changePwd(User u);
	public abstract PhoneVersion checkNewVersion();
	public abstract String feedBack(String userId,String ideaText);
	public abstract User selectUserDetailById(String userId);
	public abstract  User selectByPhoneNum(String phoneNum);
	public abstract  int updateUser(User user);
	public abstract  List<User> lookIdeaText();
	public abstract User getByPhoneAndPassword(String phone, String password);
	public abstract String checkEmailVerify(String email,String verifyCode);
	public abstract String changePhoneNum(String userId, String newphoneNum, String password);
}
