package com.yetthin.web.commit;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class SessionFilter extends OncePerRequestFilter{
	private static final String SALT="34d4yf73s!23fd";
	@Override
	protected void doFilterInternal(HttpServletRequest req,
			HttpServletResponse resp, FilterChain chain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String [] notFilter=new String[]{".css",".img",".js","index.jsp","/user/login",
				"/user/Register","/user/getRegisterVerify","/user/forgetPwd",
				"/user/getforgetPwdVerify"};
		String url=req.getRequestURI();
		System.out.println(url+"=================");
		boolean doFilter=true;
		for (String string : notFilter) {
			if(url.indexOf(string)!=-1){
				doFilter=false;
				break;
			}
		}
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		StringBuffer bf=new StringBuffer();
		if(doFilter){
			
			//do filter 
			String auth_id =(String)req.getSession().getAttribute("auth_id");
			if(auth_id==null||"".equals(auth_id.trim())){
				bf.append("{status:用户不存在}");
			}else{ 
				String []subStr=auth_id.split("=");
					if(subStr[1].equals(Md5UnitTool.getInstance().encrypt(subStr[0], SALT))){
						chain.doFilter(req, resp);
						}else{
							bf.append("{status:非法登陆}");
						}
			}
			
		}else{
			chain.doFilter(req, resp);
		}
		if(bf.toString()!=null)
		resp.getWriter().print(bf.toString());
	}

}
