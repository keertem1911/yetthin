package zcom.yetthin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.yetthin.web.commit.Md5UnitTool;

@Component
@Scope("prototype")
public class BaseController {
	// 设置 COOKIE 标志位
	private static final String AUTH_COOKIE_NAME = "yetthin";

	protected    HttpServletRequest request;
	protected HttpServletResponse response;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){  
        this.request = request;  
        this.response = response;  
         
    } 
	// 设置Cookie 的持续时间
	// 长 1个月
	private static final int longLogin = 60 * 60 * 24 * 30;
	// 短 1天
	private static final int shortLogin = 60 * 60 * 24 * 1;
	// 用户 id
	private String userId = null;
	// @Resource(name="userService")
	// private UserService userService;
	protected Map<String, Object> status = new HashMap<>();
	// 设置MD5 加盐参数
	protected static final String ENCRYPT_SALT="34d4yf73s!23fd";
	
	 
	 
	 
	private Md5UnitTool md5Tool = Md5UnitTool.getInstance();

	/**
	 * 用户名及密码 MD5加密存储
	 * 
	 * @param plainText
	 * @return
	 */
	protected String getEncrty(String plainText) {

		String res = md5Tool.encrypt(plainText, ENCRYPT_SALT);

		return res;
	}

}
