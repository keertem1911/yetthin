package zcom.yetthin.web.controller;

 
 
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSON;
import com.yetthin.web.domain.User;
import com.yetthin.web.service.UserInfoService;

@Controller
@SessionAttributes(value="auth_id,type",types=String.class)
@RequestMapping("/user")
public class UserInfoController extends BaseController{

	@Resource(name="UserInfoService")
	private UserInfoService userInfoService;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        super.request = request;  
        super.response = response;  
         
    }  
	@InitBinder  
	public void initBinder(WebDataBinder binder) {  
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
	    dateFormat.setLenient(false);  
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));  
	    
	}
	/**
	 * 注册
	 * @param userInfo
	 * @param pw
	 * @keerte
	 */
	@ResponseBody
	@RequestMapping(value="/register",method=RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	public String Rigister(@RequestParam("phoneNum")String phoneNum,
			@RequestParam("userName")String userName,
			@RequestParam("password")String password){
		
		String msg=null;
		String statusCode="200";
		if(!"".equals(phoneNum)&&!"".equals(password)){
			password =password.trim();
			phoneNum=phoneNum.trim();
			 
			List<User> lists=userInfoService.getListAll();
			boolean same=false;
			for(User u:lists){
				if(u.getUserPhone().equals(phoneNum)){
					same=true;
					break;
				}
			}//for
			
			if(!same){
			 	  	password= getEncrty(phoneNum+","+password);
						
						User user=new User();
						user.setUserPassword(password);
						user.setUserPhone(phoneNum);
						user.setUserName(userName);
						String s=getUUID();
						user.setUserId(s);
						try {
							int flag=userInfoService.save(user);
							if(flag==0){
								msg="添加失败";
								statusCode="501";
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			}else{ //same if 
				msg="电话号码已注册";
				statusCode="502";
			}
		}else{// not empty 
			statusCode="503";
			msg="用户名或密码不能为空";
		}
		if(msg==null||"null".equals(msg))
			msg="\'\'";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";

	}
	/**
	 * 注册验证码
	 * @param phone
	 * @param pw
	 */
//	@ResponseBody
//	@RequestMapping(value="/getRegisterVerify",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
//	public String getRegisterVerify(@RequestParam(value="phoneNum",required=true)String phone){
//		System.out.println(" getRegisterVerify session    "+request.getSession().getId()+" =================="); 
//		System.out.println("come into getRegisterVerify $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		String msg ="";
//		String statusCode="200";
//		if(!"".equals(phone.trim())){
//			
//		 }else{
//			msg="电话输入为空";
//			statusCode="503";
//		}
//		if(msg==null||"null".equals(msg))
//			msg="\'\'";
//		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
//		
//	}
	/**
	 * 登陆
	 * @param phone
	 * @param password
	 * @return
	 * @keerte
	 */
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String login(@RequestParam("phoneNum")String phone,
			@RequestParam("password")String password,Model model){
		System.out.println("login session    "+request.getSession().getId()+" =================="); 
		phone=phone.trim();
		logger.info(phone+" login");
		password=password.trim();
		System.out.println("come into login $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		password=getEncrty(phone+","+password);
		
		User u = userInfoService.selectByPhoneNum(phone);
		String item="\"\"";
		String msg="";
		String statusCode="200";
		if(u==null){
			msg="用户不存在";
			statusCode="504";
		}else{ if(u.getUserPassword().trim().equals(password.trim())){
			String auth_id=phone+"="+getEncrty(u.getUserPhone());
			model.addAttribute("auth_id", auth_id);
			 
			u.setUserPassword("");
			 
			item=JSON.toJSONString(u);
			HttpSession session = request.getSession();
//			String sessionId=session.getId();
//			Cookie cookie=new Cookie("JSESSION", sessionId);
			session.setAttribute("userId", u.getUserId());
//			response.addCookie(cookie);
		}else{
			msg="密码错误";
			statusCode="505";
			
		}
		}
		if(msg==null||"null".equals(msg))
			msg="\'\'";
				return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\",\"item\":"+item+"}";
	}
	
	/**
	 * 忘记密码
	 * @param phoneNum
	 * @param verifyCode 验证码
	 * @param password
	 * @return 状态码
	 * @keerte
	 */
	@ResponseBody
	@RequestMapping(value="/forgetPwd",method=RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	public String  forgetPwd(@RequestParam(value="phoneNum")String phoneNum,
			@RequestParam(value="password")String password){
		System.out.println("forgetPwd session    "+request.getSession().getId()+" =================="); 
		System.out.println("come into forgetPwd $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String msg=null;
		String statusCode="200";
		  
		 	if(!"".equals(password)){
		 
		 
			 	 
		 		User userinfo = userInfoService.selectByPhoneNum(phoneNum);
				if(userinfo!=null){
					password=getEncrty(phoneNum+","+password);
					userinfo.setUserPassword(password);
					try {
						String i=userInfoService.forgetPwd(userinfo);
						statusCode=i.split(",")[0];
						msg=i.split(",")[1];
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					msg="用户未注册";
					statusCode="504";
				}
			 
		}else{
			statusCode="503";
			if("".equals(phoneNum)){
			msg="电话输入为空";
			} 
		}
		 	if(msg==null||"null".equals(msg))
				msg="\'\'";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	
	}
	/**
	 * 忘记密码的验证码
	 * @param phoneNum
	 * @return
	 * 
	 */
//	@ResponseBody
//	@RequestMapping(value="/getforgetPwdVerify",method=RequestMethod.PUT,
//			produces = {"application/json;charset=UTF-8"})
//	public String getForgetPwdVerify(@RequestParam(value="phoneNum",required=true)String phoneNum){
//		String statusCode="200";
//		System.out.println("getForgetPwdVerify session    "+request.getSession().getId()+" =================="); 
//		String msg=null;
//		System.out.println("come into getForgetPwdVerify $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//		System.out.println("phoneNum="+phoneNum);
//		 
//		if(!"".equals(phoneNum.trim())){
//		String verifyCode="";
//		String results =MessageSendToPhone.sendToRigister(phoneNum,"SMS_10140393","投智星app");
//	 
//		}else{
//			msg="电话输入为空";
//			statusCode="503";
//		}
//		if(msg==null||"null".equals(msg))
//			msg="\'\'";
//			return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
//	}
	
	 
	/**
	 * 更新极光id
	 * @param userId
	 * @param JpushID
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateJPushID",method=RequestMethod.PUT,
			produces = {"application/json;charset=UTF-8"})
	public String updateJpushID(@RequestParam("userID")String userId,
			@RequestParam(value="JpushID")String JpushID,@RequestParam(value="phoneType")String type){
			String msg="''";
		String statusCode="200";
 		     
 		 HttpSession session = request.getSession();
 		 System.out.println(session.getId()+" ididididididididid");
 		 if(session.getAttribute("type")!=null )
 			System.out.println(" $$$$$$$$$$$      "+type+"   ￥￥￥￥￥￥￥￥￥￥￥￥￥");
		if(!"".equals(userId)&&!"".equals(JpushID)&&!"".equals(type)){
			userId=userId.trim();
			JpushID =JpushID.trim();
	 	session.setAttribute("phoneType", type);
	 	  session = request.getSession();
		String sessionId=session.getId();
		Cookie cookie=new Cookie("JSESSION", sessionId);
		response.addCookie(cookie);
		System.out.println("come into updateJpushID $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(userId+"="+JpushID);
		msg=userInfoService.updateJpushId(userId, JpushID,"0");
		String []subStr=msg.split("=");
		if(subStr[1]!=null){
			msg=subStr[1];
			
		}
		statusCode=subStr[0];
		}else{
			msg="用户名或极光ID不能为空";
			statusCode="503";
		}
		System.out.println("msagmsgmsgmsmg "+msg);
		if(msg==null||"null".equals(msg))
			msg="\'\'";
		 
			return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	}
	/**
	 * 更新本机推送开关
	 * @param userId
	 * @param status
	 * @return
	 */
	@ResponseBody()
	@RequestMapping(value="/updateJPushStatus",method=RequestMethod.POST,
			produces = {"application/json;charset=UTF-8"})
	public String updateJpushStatus(@RequestParam(value="userID")String userId,
			@RequestParam(value="JpushStatus")String jpushStatus,
			@RequestParam(value="JpushType")String JpushType){
		String msg="";
		String statusCode="200";
		if(!"".equals(userId)&&!"".equals(jpushStatus)){
			userId =userId.trim();
			jpushStatus=jpushStatus.trim();
			if(jpushStatus.equals("1")||jpushStatus.equals("0")){
			System.out.println("come into updateJpushStatus $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String flag=userInfoService.updateJpushStatus(userId, jpushStatus,JpushType);
		String [] subStr=flag.split("=");
		if(subStr[1]!=null){
			msg=subStr[1];
		}
		statusCode=subStr[0];
		System.out.println(userId+"="+status);
			}else{
				msg="开关值只能为1或0";
				statusCode="507";
			}
		}else{
			msg="id不能为空或开关为空";
			statusCode="503";
		}
		if(msg==null||"null".equals(msg.trim()))
			msg="";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	}
	public boolean checkEmail(String email){
		boolean flag=true;
		int i=email.indexOf("@");
		int j=email.lastIndexOf(".");
		if(i==0&&i>=(email.length())){
			flag=false;
		}else{
			if(j>email.length()){
				flag=false;
			}else{
				if(i>j)
					flag=false;
			}
		}
		return flag;
	}
	 
	/**
	 * 绑定邮箱   
	 * @param userId
	 * @param email
	 * @return
	 * @keerte
	 */
	@ResponseBody
	@RequestMapping(value="/bindingEmail",method=RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
	public String bindingEmail(@RequestParam(value="userID")String userId,
			@RequestParam(value="email")String email,
			HttpServletRequest request){
		String statusCode="200";
		String msg="";
		userId=userId.trim();
		email=email.trim();
		if(checkEmail(email)){
		System.out.println("come into updateJpushStatus bindingEmail $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println("userID="+userId+",email="+email);
		String flag=userInfoService.bindingEmail(userId, email,request.getRequestURL().toString());
		String [] subStr=flag.split("=");
		if(subStr[1]!=null){
			msg=subStr[1];
		}
		statusCode=subStr[0];
		}else{
			statusCode="507";
			msg="邮箱格式错误";
		}
		if(msg==null||"null".equals(msg))
			msg="\'\'";
			return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	}
	/**
	 * 更改密码
	 * @param userId
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @keerte
	 */
	@ResponseBody
	@RequestMapping(value="/changePwd",method=RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
	public String changePwd(@RequestParam(value="userID")String userId,
			@RequestParam(value="oldPwd")String oldPassword,
			@RequestParam(value="newPwd")String newPassword){
		System.out.println("come into  changePwd $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String statusCode="200";
		String msg="";
		if(!"".equals(userId)&&!"".equals(newPassword)&&!"".equals(oldPassword)){
		userId =userId.trim();
		oldPassword=oldPassword.trim();
		newPassword=newPassword.trim();
		User u=userInfoService.get(userId);
		if(u==null){
			msg="用户不存在";
			statusCode="504";
		}else{
			System.out.println(u);
			String oldP=getEncrty(u.getUserPhone()+","+oldPassword);
			if(u.getUserPassword().equals(oldP.trim())){
			String newP=getEncrty(u.getUserPhone()+","+newPassword);
			u.setUserPassword(newP);
			String flag=userInfoService.changePwd(u);
			if(!flag.equals("200")){
				msg=flag;
				statusCode="506";
			}
			}else{
				statusCode="505";
				msg="密码错误";
			}
		}
		System.out.println(userId+"="+oldPassword+"="+newPassword);
		}else{
			statusCode="503";
			if("".equals(userId)){
			msg="用户名为空";
			}else if("".equals(oldPassword)){
			msg="旧为空";
			}else{
			msg="新为空";
			}
		}
		if(msg==null||"null".equals(msg))
			msg="\'\'";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	}
	@ResponseBody
	@RequestMapping(value="/changeNameAndDepart",method=RequestMethod.PUT,
	produces = {"application/json;charset=UTF-8"})
	public String changeUserName(@RequestParam(value="userID")String userId,
			@RequestParam(value="userName")String userName,
			@RequestParam(value="userDepart")String userDepart){
		System.out.println("come into  changePwd $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		 String json ="";
		String statusCode;
		String msg="";
		if(!"".equals(userId)&&!"".equals(userName)&&!"".equals(userName)){
		userId =userId.trim();
	 
		 
		User u=userInfoService.get(userId);
		if(u==null){
			msg="用户不存在";
			statusCode="504";
		}else{
			System.out.println(u);
			 
			u.setUserName(userName);
			u.setUserFirm(userDepart);
			String flag=userInfoService.changePwd(u);
			if(flag.equals("200")){
				statusCode=flag;
				json =",\"userName\":\""+userName+"\",\"userDepart\":\""+userDepart+"\"";
			}else if(!flag.equals("200")){
				msg=flag;
				statusCode="506";
			}else{
				statusCode="505";
				msg="id错误";
			}
		 
		}
		 
		}else{
			statusCode="503";
			if("".equals(userId)){
			msg="id为空";
			}else if("".equals(userName)){
			msg="用户名为空";
			}else{
			msg="新为空";
			}
		}
		if(msg==null||"null".equals(userName))
			msg="\'\'";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\""+json+"}";
	}
	
	
	 
	/**
	 * 意见反馈
	 * @param userId
	 * @param ideaText
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/feedback",method=RequestMethod.PUT,produces = {"application/json;charset=UTF-8"})
	public String feedBack(@RequestParam(value="userID",required=true)String userId,
			@RequestParam(value="ideaText",required=false)String ideaText){
		System.out.println("come into feedback $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		String statusCode="200";
		String msg="";
		if(!"".equals(userId)&&!"".equals(ideaText)){
		String flag=userInfoService.feedBack(userId, ideaText);
		String [] subStr=flag.split("=");
		if(subStr[1]!=null){
			msg=subStr[1];
		}
		statusCode=subStr[0];
		System.out.println(userId+"="+ideaText);
		}else{
			statusCode="503";
			if("".equals(userId))
					msg="用户ID为空";
			else msg="反馈内容为空";
		}
		if(msg==null||"null".equals(msg))
			msg="\'\'";
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	}
	/**
	 * 更新头像 
	 * @param file
	 * @param userId
	 * @return
	 * @keerte
	 */
	@ResponseBody 
	@RequestMapping(value="/uploadPicture",
	method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	public String updatePicture(
			HttpServletRequest request){
		String msg="";	
		String statusCode="200";
		String userID = request.getParameter("userId");
		String imgPath="";
		HttpSession session = request.getSession();
		System.out.println(session.getId());
//		String userId  =(String)session.getAttribute("userId");
//		if(!"".equals(userId)){
			imgPath =request.getRequestURL().toString().split("/user")[0];
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
					request.getSession().getServletContext());
			 
			if (multipartResolver.isMultipart(request)) {
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				Iterator<String> iter = multiRequest.getFileNames();
				while (iter.hasNext()) {
					MultipartFile fileer = multiRequest.getFile((String) iter.next());
					 
					if (fileer != null && fileer.getSize() > 0) {
			String fileName=fileer.getOriginalFilename();
			String format=fileName.substring(fileName.lastIndexOf("."));
			
//			userId=userId.trim();
			User user = userInfoService.get(userID);
			if(user!=null){
				String path=request.getSession().getServletContext().getRealPath("/");
				String lastName = fileName.split("[.]")[1];
				System.out.println(lastName);
				String pictureName ="user-"+getUUID()+"."+lastName;
				imgPath =imgPath +"/image/"+pictureName;
				user.setUserHeadPic(imgPath);
				String savepicture=path+"image/";
				File directory=new File(savepicture+pictureName);
				try {
					String  i =userInfoService.changePwd(user);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if(directory.exists()==false){
					directory.mkdirs();
				}
				try {
					fileer.transferTo(directory);
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
					}
				}
//				InputStream is=file.getInputStream();
// 				OutputStream out=FileUtils.openOutputStream(new File(savepicture++"/"+user.));
//		}else{
//				msg="用户不存在";
//				statusCode="504";
//				
//			}
					 
	}else{
			msg="输入不能为空";
			statusCode="503";
		}
	if(msg==null||"null".equals(msg))
			msg="\'\'";
	
		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\",\"user_img\":\""+imgPath+"\"}";
		
			 
		 
	}
//	/**
//	 * 更新头像 
//	 * @param file
//	 * @param userId
//	 * @return
//	 * @keerte
//	 */
//	@ResponseBody 
//	@RequestMapping(value="/uploadPicture",produces = {"application/json;charset=UTF-8"})
//	public String updatePicture(@RequestParam("file")MultipartFile file,@RequestParam("userID")String userId,
//			HttpServletRequest request){
//		String msg="";	
//		String statusCode="200";
//		String imgPath="";
//		if(!"".equals(userId)&&file!=null){
//			imgPath =request.getRequestURL().toString().split("/user")[0];
//			String fileName=file.getOriginalFilename();
//			String format=fileName.substring(fileName.lastIndexOf("."));
//			
//			userId=userId.trim();
//			User user = userInfoService.get(userId);
//			if(user!=null){
//				String path=request.getSession().getServletContext().getRealPath("/");
//				String lastName = fileName.split("[.]")[1];
//				System.out.println(lastName);
//				user.setUserHeadPic("user-"+userId+"."+lastName);
//				imgPath =imgPath +"/image/"+user.getUserHeadPic();
//				String savepicture=path+"image/";
//				File directory=new File(savepicture+user.getUserHeadPic());
//				
//				if(directory.exists()==false){
//					directory.mkdirs();
//				}
//				try {
//					file.transferTo(directory);
//				} catch (IllegalStateException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				
////				InputStream is=file.getInputStream();
//// 				OutputStream out=FileUtils.openOutputStream(new File(savepicture++"/"+user.));
//			}else{
//				msg="用户不存在";
//				statusCode="504";
//				
//			}
//			
//		}else{
//			msg="输入不能为空";
//			statusCode="503";
//		}
//		if(msg==null||"null".equals(msg))
//			msg="\'\'";
//		return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\",\"user_img\":\""+imgPath+"\"}";
//	}
	 @ResponseBody
	@RequestMapping(value="/getSplash",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	 public String  getSplash(HttpServletRequest request){
		String jsessionid=request.getSession().getId();
		String path =request.getRequestURL().toString();
		String requestPath = path.split("user/")[0];
		System.out.println("getSplash session    "+request.getSession().getId()+" =================="); 
		
			return "{\"JSESSION\":\""+jsessionid+"\",\"img\":\""+requestPath+"/image/start.png\",\"status\":\"200\"}";
	 }
	 /**
	  * 邮箱激活回调
	  * @param email
	  * @param verifyEmail
	  * @return
	  */
	 
	 @RequestMapping(value="/emailCallback",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	public String emailCallback(@RequestParam(value="email")String email,
			@RequestParam(value="verifyEmail")String verifyEmail){
			String msg="200";
//		 msg=userInfoService.checkEmailVerify(email, verifyEmail);
		request.setAttribute("email", email);
		request.setAttribute("msg", msg);
		 if(!msg.equals("200")){
			 return "/bindingEmail/error";
		 }else{
			 return "/bindingEmail/success";
			 
		 }
	}
	/**
	 * 
	 * @param userId
	 * @param NewphoneNum
	 * @param password
	 * @return
	 * @keerte
	 */
	 @ResponseBody
	 @RequestMapping(value="/changePhone",method=RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
	 public String changePhoneNum(@RequestParam(value="userID")String userId,
			 @RequestParam(value="newPhoneNum")String NewphoneNum,@RequestParam(value="password")String password){
		 String statusCode="200";
			String msg="";
		 msg=userInfoService.changePhoneNum(userId,NewphoneNum,password);
		 if("200".equals(msg.trim())){
			 msg="";
		 }else{
			 statusCode=msg.split(",")[0];
			 msg=msg.split(",")[1];
		 }
		 return "{\"status\":\""+statusCode+"\",\"msg\":\""+msg+"\"}";
	 }
	@Override
	public Class getClassType() {
		// TODO Auto-generated method stub
		return this.getClass();
	}
}
