package com.yetthin.web.serviceImp;

 
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yetthin.web.domain.PhoneVersion;
import com.yetthin.web.domain.User;
 
import com.yetthin.web.persistence.UserMapper;
import com.yetthin.web.service.UserInfoService;

@Service("UserInfoService")
public class UserInfoServiceImp extends BaseService implements UserInfoService{


	@Resource
	private UserMapper userMapper;
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_UNCOMMITTED)
	@Override
	public User get(String id) {
		// TODO Auto-generated method stub
	 
		return userMapper.selectByPrimaryKey(id);
	}
	 /**
	  * 注册用户
	  * @keerte
	  */
	 
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public int save(User entity)   {
		// TODO Auto-generated method stub
		System.out.println(entity+"===========================");
		entity.setUserRegisterTime(LocalDateTime.now().format(simple));
		return userMapper.insertSelective(entity);
	}
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class) 
	@Override
	public int delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.deleteByPrimaryKey(id);
	}
	
 
	@Override
	public String getRegisterVerify(String phoneNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String forgetPwd(User userinfo) {
		// TODO Auto-generated method stub
		int i=userMapper.updateByPrimaryKey(userinfo);
		  
		return		i==0?"506,添加失败":"200, ";
	}
	@Override
	public String getforgetPwdVerify(String phoneNum) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public String updateJpushId(String userId, String JpushID,String JpushType) {
//		// TODO Auto-generated method stub
//		String msg=null;
//		String statusCode="200";
//		User ui=userInfoMapper.selectByPrimaryKey(userId);
//		if(ui==null){
//			msg="用户不存在";
//			statusCode="504";
//			
//		}else{
//			ui.setJpushId(JpushID);
//			ui.setJpushType(JpushType);
//			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
//			if(i==0){
//				msg="更新失败";
//				statusCode="506";
//			}
//		}
//		return statusCode+"="+msg;
//	}
//	@Override
//	public String updateJpushStatus(String userId, String jpushStatus,String JpushType) {
//		// TODO Auto-generated method stub
//		String statusCode="200";
//		String msg=null;
//		User ui=userInfoMapper.selectByPrimaryKey(userId);
//		if(ui==null){
//			msg="用户不存在";
//			statusCode="504";
//		}else{
//		
//			ui.setJpushStatus(Integer.parseInt(jpushStatus));
//			ui.setJpushType(JpushType);
//			int i=userInfoMapper.updateByPrimaryKeySelective(ui);
//			if(i==0){
//				statusCode="506";
//				msg="更新失败";
//			}
//		}
//		return statusCode+"="+msg;
//		 
//	}
	@Override
	public String bindingEmail(String userID, String email,String path) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		 path=path.split("user/")[0];
		User ui=userMapper.selectByPrimaryKey(userID);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setEmail(email);
			ui.setVerifyEmail(getEncrty(email));
			LocalDateTime time= LocalDateTime.now().plusHours(8);
			String date1=time.format(simple);
			ui.setUserRegisterTime(date1);
			ui.setEmailStatus("0");
			int i=userMapper.updateByPrimaryKeySelective(ui);
			StringBuffer sb=new StringBuffer();
			sb.append("<html><head></head><body><h2>复制下面链接到浏览器激活账号，8小时生效，否则重新注册账号</h2><br/>");
		//	sb.append("                        <a href=\""+EMAIL_CALLBACK_ADDRESS+"?email="+email+"&verifyEmail="+getEncrty(email)+"\">");
			sb.append(""+path+"user/emailCallback?email="+email+"&verifyEmail="+getEncrty(email));

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
	public String changePwd(User u) {
		// TODO Auto-generated method stub
		int i=userMapper.updateByPrimaryKey(u);
		return i==0?"更新失败":"200";
	}
	@Override
	public PhoneVersion checkNewVersion() {
		// TODO Auto-generated method stub
		return null;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String feedBack(String userId, String ideaText) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		User ui=userMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setIdeaText(ideaText);
			int i=userMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				msg="更新失败";
				statusCode="506";
			}
		}
		return statusCode+"="+msg;
		 
	}
	@Override
	public List<User> getListAll() {
		// TODO Auto-generated method stub
		return userMapper.getAllUser();
	}
	@Override
	public int countByExample() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public User selectByPhoneNum(String phoneNum) {
		// TODO Auto-generated method stub
		return userMapper.selectByPhoneNum(phoneNum);
	}
//	@Override
//	public User selectByEmail(String email) {
//		// TODO Auto-generated method stub
//		return userInfoMapper.selectByEmail(email);
//	}
//	@Override
//	public List<User> lookIdeaText() {
//		// TODO Auto-generated method stub
//		return userInfoMapper.lookIdeaText();
//	}
	@Override
	public User getByPhoneAndPassword(String phone, String password) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<>();
		map.put("phone", phone);
		map.put("password", password);
		User ui=userMapper.selectByPhoneNumAndPassWord(map);
		return ui;
	}
	
	public int sendEmailVerifyService(String to){
			int i=0;
			getSender().sendEmail(to, "邮件验证", "");
			return i;
	}
	/**
	 * 邮箱验证
	 */
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String checkEmailVerify(String email, String verifyCode)  {
		// TODO Auto-generated method stub
		String error="";
		User user= userMapper.findVerifyEmailByEmail(email);
		 if(user!=null){
//			 	Date date= new Date();
			 	LocalDateTime date=LocalDateTime.now();
//			 	Date date2=null;
			 	LocalDateTime date2=null;
			 	try {
					date2=LocalDateTime.parse(user.getUserRegisterTime(), simple);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	if(Duration.between(date2, date).getSeconds()>0){
			 		if(user.getVerifyEmail().equals(verifyCode.trim())){
			 			System.out.println("激活成功！！！");
			 			user.setUserRegisterTime(date.format(simple));
			 			user.setEmailStatus("1");
			 			userMapper.updateByPrimaryKeySelective(user);
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
	
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String changePhoneNum(String userId, String newphoneNum, String password) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg="200";
		User user=userMapper.selectByPrimaryKey(userId);
		if(user!=null){
			boolean same=false;
			List<User> lists=userMapper.getAllUser();
			for (User userInfo : lists) {
				if(newphoneNum.equals(userInfo.getUserPhone())){
					same=true;
					break;
				}
			}
			if(!same){
			String phoneOld=user.getUserPhone();
			String passwordt=getEncrty(phoneOld+","+password);
			if(passwordt.trim().equals(user.getUserPassword())){
					user.setUserPhone(newphoneNum);
					user.setUserPassword(getEncrty(newphoneNum+","+password));
					System.out.println(user);
					int i =userMapper.updateByPrimaryKey(user);
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
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String updateJpushId(String userId, String JpushID,String JpushType) {
		// TODO Auto-generated method stub
		String msg=null;
		String statusCode="200";
		User ui=userMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
			
		}else{
			ui.setJpushId(JpushID);
			ui.setJpushType(JpushType);
			int i=userMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				msg="更新失败";
				statusCode="506";
			}
		}
		return statusCode+"="+msg;
	}
	@Transactional(propagation=Propagation.REQUIRED,isolation=Isolation.READ_COMMITTED,
			rollbackFor=Exception.class)
	@Override
	public String updateJpushStatus(String userId, String jpushStatus,String JpushType) {
		// TODO Auto-generated method stub
		String statusCode="200";
		String msg=null;
		User ui=userMapper.selectByPrimaryKey(userId);
		if(ui==null){
			msg="用户不存在";
			statusCode="504";
		}else{
		
			ui.setJpushStatus(Integer.parseInt(jpushStatus));
			ui.setJpushType(JpushType);
			int i=userMapper.updateByPrimaryKeySelective(ui);
			if(i==0){
				statusCode="506";
				msg="更新失败";
			}
		}
		return statusCode+"="+msg;
		 
	}
	
	 
 
	 
	@Override
	public List<User> lookIdeaText() {
		// TODO Auto-generated method stub
		return userMapper.lookIdeaText();
	}
	@Override
	public User selectUserDetailById(String userId) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(userId);
	}
	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	
	}
	 
	 
}
