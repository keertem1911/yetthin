package com.yetthin.web.service;

import java.util.List;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.domain.UserInfo;

public interface UserInfoService extends BaseService<UserInfo>{
	public abstract String getRegisterVerify(String phoneNum);
	public abstract String forgetPwd(UserInfo userinfo);
	public abstract String getforgetPwdVerify(String phoneNum);
	public abstract String updateJpushId(String userId,String JpushID,String JpushType);
	public abstract String updateJpushStatus(String userId,String status,String JpushType);
	public abstract String bindingEmail(String userID,String email);
	public abstract String changePwd(UserInfo u);
	public abstract PhoneVersion checkNewVersion();
	public abstract String feedBack(String userId,String ideaText);
	
	public abstract  UserInfo selectByPhoneNum(String phoneNum);
	public abstract  UserInfo selectByEmail(String email);
	public abstract  List<UserInfo> lookIdeaText();
	public abstract UserInfo getByPhoneAndPassword(String phone, String password);
	public abstract String checkEmailVerify(String email,String verifyCode);
	public abstract String changePhoneNum(String userId, String newphoneNum, String password);
}
