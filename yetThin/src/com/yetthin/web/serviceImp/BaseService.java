package com.yetthin.web.serviceImp;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;

import com.yetthin.web.commit.Md5UnitTool;
import com.yetthin.web.commit.sendEmailVerify;

public class BaseService {
	// 设置MD5 加盐参数
	protected static final String ENCRYPT_SALT="34d4yf73s!23fd";
	
	protected  static final String EMAIL_CALLBACK_ADDRESS="www.cktim.net/yetThin/user/emailCallback";
	 
	 
	private Md5UnitTool md5Tool = Md5UnitTool.getInstance();
	
	protected SimpleDateFormat simple=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	protected  SimpleDateFormat format_yyyyMMdd =new SimpleDateFormat("yyyy/MM/dd");
	protected  DecimalFormat   format_double_2=new   java.text.DecimalFormat("#.#");  
	protected  SimpleDateFormat format_hhmm=new SimpleDateFormat("hh:mm");
	
	@Resource(name="Sender")
	private sendEmailVerify sender;
	
	public sendEmailVerify getSender() {
		return sender;
	}
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
	protected <E>String tojson(List<E> list){
		
		StringBuffer buffer =new StringBuffer();
		buffer.append("\"value\":[");
		Class clazz =list.get(0).getClass();
		Field[] fiedls = clazz.getDeclaredFields();
		final String [] fiedlsName=new String [fiedls.length];
		for (int i = 0; i < fiedls.length; i++) {
			String name =fiedls[i].getName();
			fiedlsName[i] =name.substring(0, 1).toUpperCase()+name.substring(1);
		}
		for (int i = 0; i < list.size(); i++) {
			buffer.append("{");
			for(int j=0;j<fiedlsName.length;++j){
				try{
			Method method = clazz.getDeclaredMethod("get"+fiedlsName[j]);
			Object value = method.invoke(list.get(i));
			String name =fiedlsName[j].substring(0, 1).toLowerCase()+fiedlsName[j].substring(1);
			if(value!=null)
			buffer.append("\""+name+"\":\""+value+"\"");
			
			if(j!=fiedlsName.length-1)
				buffer.append(",");
				}catch(Exception e){
					e.printStackTrace();
					}
				 
			}
			
			buffer.append("}");
			if(i!=list.size()-1)
				buffer.append(",");
		}
		
		buffer.append("]");
		return buffer.toString();
		
	}
	protected <E>String tojson(E list){
		
		StringBuffer buffer =new StringBuffer();
		buffer.append("\"value\":");
		Class clazz =list.getClass();
		Field[] fiedls = clazz.getDeclaredFields();
		final String [] fiedlsName=new String [fiedls.length];
		for (int i = 0; i < fiedls.length; i++) {
			String name =fiedls[i].getName();
			fiedlsName[i] =name.substring(0, 1).toUpperCase()+name.substring(1);
		}
		
			buffer.append("{");
			for(int j=0;j<fiedlsName.length;++j){
				try{
			Method method = clazz.getDeclaredMethod("get"+fiedlsName[j]);
			Object value = method.invoke(list);
			String name =fiedlsName[j].substring(0, 1).toLowerCase()+fiedlsName[j].substring(1);
			Class<?> type = fiedls[j].getType();
			String tmp =null;
			if(type==String.class){
				String val =(String)value;
				if(val.indexOf("{")>0)
					tmp=val;
					
			}
			if(tmp==null)
			buffer.append("\""+name+"\":\""+value+"\"");
			else
				buffer.append("\""+name+"\":"+value+"");
				
			if(j!=fiedlsName.length-1)
				buffer.append(",");
				}catch(Exception e){
					e.printStackTrace();
					}
				 
			}
			
			buffer.append("}");
			
	
		return buffer.toString();
		
	}
	protected int[] partitionPage(int totlePage,int pageNum,int pageSize){
		int [] page=new int [3];
		int totlePageNum=totlePage/pageSize;
		if(pageNum>totlePageNum) pageSize=totlePageNum;
		if(pageNum<=0) pageNum=1;
		
		if(totlePage%pageSize!=0) 
			totlePageNum++;
		int begin=0;
		int end=0;
		if(pageNum==totlePageNum){
			begin =(totlePageNum-1)*pageSize+1;
			end=totlePage;
		}else{
		 begin=(pageSize-1)*pageSize+1;
		 end =(pageSize)*pageSize;
		}
		page[0]=begin;
		page[1]=end;
		page[2]=totlePageNum;
		return page;
	}
	protected String getRequestPath(String path){
		Pattern pattern =Pattern.compile(".+yetThin");
		Matcher matcher =pattern.matcher(path);
		String requestPath=null;
		if(matcher.find()){
			requestPath=matcher.group(0);
		}
		return requestPath;
	}
}
