package com.yetthin.web.serviceImp;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yetthin.web.commit.sendEmailVerify;
import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.domain.UserInfo;
import com.yetthin.web.persistence.UserInfoMapper;
import com.yetthin.web.service.UserInfoService;

@Service("UserInfoService")
public class UserInfoServiceImp extends BaseService implements UserInfoService{

	@Autowired
	private UserInfoMapper userInfoMapper;
	
	private SimpleDateFormat  sf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public UserInfo get(String id) {
		// TODO Auto-generated method stub
	 
		return userInfoMapper.selectByPrimaryKey(id);
	}
	 
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED)
	@Override
	public int save(UserInfo entity) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(entity+"===========================");
		return userInfoMapper.insert(entity);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,isolation=Isolation.READ_COMMITTED)
	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return userInfoMapper.deleteByPrimaryKey(id);
	}
	
 
	@Override
	public String getRegisterVerify(String phoneNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String forgetPwd(UserInfo userinfo) {
		// TODO Auto-generated method stub
		int i=userInfoMapper.updateByPrimaryKey(userinfo);
		  
		return		i==0?"506,添加失败":"200, ";
	}
	@Override
	public String getforgetPwdVerify(String phoneNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String updateJpushId(String userId, String JpushID,String JpushType) {
		// TODO Auto-generated method stub
		String msg=null;
		String statusCode="200";
		UserInfo ui=userInfoMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
			
		}else{
			ui.setJpushId(JpushID);
			ui.setJpushType(JpushType);
			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				msg="更新失败";
				statusCode="506";
			}
		}
		return statusCode+"="+msg;
	}
	@Override
	public String updateJpushStatus(String userId, String jpushStatus,String JpushType) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		UserInfo ui=userInfoMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setJpushStatus(Integer.parseInt(jpushStatus));
			ui.setJpushType(JpushType);
			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				statusCode="506";
				msg="更新失败";
			}
		}
		return statusCode+"="+msg;
		 
	}
	@Override
	public String bindingEmail(String userID, String email) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		UserInfo ui=userInfoMapper.selectByPrimaryKey(userID);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setEmail(email);
			ui.setVerifyEmail(getEncrty(email));
			String date1=simple.format(new Date(System.currentTimeMillis()+28800000));
			ui.setRegisterTime(date1);
			ui.setEmailStatus("0");
			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
			StringBuffer sb=new StringBuffer();
			sb.append("<html><head></head><body><h2>复制下面链接到浏览器激活账号，8小时生效，否则重新注册账号</h2><br/>");
		//	sb.append("                        <a href=\""+EMAIL_CALLBACK_ADDRESS+"?email="+email+"&verifyEmail="+getEncrty(email)+"\">");
			sb.append(""+EMAIL_CALLBACK_ADDRESS+"?email="+email+"&verifyEmail="+getEncrty(email));

		//	sb.append("</a>");
			sb.append("</body></htmk>");
			
			
			System.out.println(sb.toString());
			getSender().sendEmail(email, "投智星邮件绑定验证",sb.toString());
			if(i==0){
				msg="更新失败";
				statusCode="506";
			}
		}
		return statusCode+"="+msg;
	}
	@Override
	public String changePwd(UserInfo u) {
		// TODO Auto-generated method stub
		int i=userInfoMapper.updateByPrimaryKey(u);
		return i==0?"更新失败":"200";
	}
	@Override
	public PhoneVersion checkNewVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String feedBack(String userId, String ideaText) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		UserInfo ui=userInfoMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setIdeaText(ideaText);
			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				msg="更新失败";
				statusCode="506";
			}
		}
		return statusCode+"="+msg;
		 
	}
	@Override
	public List<UserInfo> getListAll() {
		// TODO Auto-generated method stub
		return userInfoMapper.getAllUser();
	}
	@Override
	public int countByExample() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public UserInfo selectByPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByPhoneNum(phoneNum);
	}
	@Override
	public UserInfo selectByEmail(String email) {
		// TODO Auto-generated method stub
		return userInfoMapper.selectByEmail(email);
	}
	@Override
	public List<UserInfo> lookIdeaText() {
		// TODO Auto-generated method stub
		return userInfoMapper.lookIdeaText();
	}
	@Override
	public UserInfo getByPhoneAndPassword(String phone, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		UserInfo ui=userInfoMapper.selectByPhoneNumAndPassWord(map);
		return null;
	}
	
	public int sendEmailVerifyService(String to){
			int i=0;
			getSender().sendEmail(to, "邮件验证", "");
			return i;
	}

	@Override
	public String checkEmailVerify(String email, String verifyCode)  {
		// TODO Auto-generated method stub
		String error="";
		UserInfo user= userInfoMapper.findVerifyEmailByEmail(email);
		 if(user!=null){
			 	Date date= new Date();
			 	Date date2=null;
			 	try {
					date2=simple.parse(user.getRegisterTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	if(date.before(date2)){
			 		if(user.getVerifyEmail().equals(verifyCode.trim())){
			 			System.out.println("激活成功！！！");
			 			user.setRegisterTime(simple.format(new Date(System.currentTimeMillis())));
			 			user.setEmailStatus("1");
			 			userInfoMapper.updateByPrimaryKeySelective(user);
			 			error="200";
			 		}else{
			 			error="验证信息错误";
			 		}
			 	}else{
			 		error="验证链接过期";
			 	}
		 }else{
			 error="用户不存在";
		 }
		return error;
	}

	@Override
	public String changePhoneNum(String userId, String newphoneNum, String password) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg="200";
		UserInfo user=userInfoMapper.selectByPrimaryKey(userId);
		if(user!=null){
			boolean same=false;
			List<UserInfo> lists=userInfoMapper.getAllUser();
			for (UserInfo userInfo : lists) {
				if(newphoneNum.equals(userInfo.getPhoneNum())){
					same=true;
					break;
				}
			}
			if(!same){
			String phoneOld=user.getPhoneNum();
			String passwordt=getEncrty(phoneOld+","+password);
			if(passwordt.trim().equals(user.getPassword())){
					user.setPhoneNum(newphoneNum);
					user.setPassword(getEncrty(newphoneNum+","+password));
					System.out.println(user);
					int i =userInfoMapper.updateByPrimaryKey(user);
					if(i==0){
						statusCode="506";
					 	msg=",更新失败";
					}
			}else{// 密码错误
				msg=",密码错误";
				statusCode="505";
			}
			}else{
				msg=",电话号码已注册";
				statusCode="502";
			}
		}else{ //user ==null
			msg=",用户不存在";
			statusCode="504";
		}
		
		if(statusCode.equals("200"))
			msg="";
		
		return statusCode+msg;
	}
}
