package com.yetthin.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.mybatis.spring.SqlSessionTemplate;

import com.yetthin.web.domain.Share;

public class RegularTest {
	private   SqlSessionTemplate sqlSessionTemplate;
	
		public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	public void dofun(){
	List<Share> list = sqlSessionTemplate.selectList("com.yetthin.web.persistence.ShareMapper.selectSharesAll");
	}
//		public static void main(String[] args) {
//			char a= 'A';
//			String b= "C";
//			char [] chars = b.toCharArray();
//			  int s= Integer.valueOf(chars[0])-a;
//			  System.out.println(s);
//			  RegularTest re = new RegularTest();
//			  
//		}
	protected static  SimpleDateFormat format_yyyy_MM_dd =new SimpleDateFormat("yyyy-MM-dd");
		public static void main(String[] args) {
			Calendar calendar = Calendar.getInstance();  
			// 设置时间,当前时间不用设置  
			// calendar.setTime(new Date());  
			// 设置日期为本月最大日期  
//			calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE)); 
//			String end= format_yyyy_MM_dd.format(calendar.getTime());
//			calendar.set(Calendar.DATE,calendar.getActualMinimum(calendar.DATE));
//			String begin = format_yyyy_MM_dd.format(calendar.getTime());
//			System.out.println(begin +" "+end);
//			
			 calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			String begin =format_yyyy_MM_dd.format(calendar.getTime());
			 calendar.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
			String end =format_yyyy_MM_dd.format(calendar.getTime());
			 System.out.println(begin +" "+end);
			 
		}
}	
