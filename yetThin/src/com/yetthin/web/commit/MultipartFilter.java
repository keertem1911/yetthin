package com.yetthin.web.commit;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class MultipartFilter extends HandlerInterceptorAdapter{
		
	
		@Resource
		private MultipartResolver multipartResolver = null;
		 
		
		
		
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
				throws Exception {
			 	String enctype= request.getContentType();
			 	if(StringUtils.isNotBlank(enctype) && enctype.contains("multipart/form-data")){
			 		MultipartHttpServletRequest req = multipartResolver.resolveMultipart((HttpServletRequest)request);
			 		return super.preHandle(req, response, handler); 
			 	}else{
			 		return super.preHandle(request, response, handler);
			 	}
			 }
}
