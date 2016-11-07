package com.yetthin.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularTest {
		public static void main(String[] args) {
			String path="http://222.24.62.77:8080/yetThin/picture/getHeadList";
			Pattern pattern =Pattern.compile(".+yetThin/");
			Matcher matcher =pattern.matcher(path);
			if(matcher.find()){
				System.out.println(matcher.group(0));
			}
		}
}	
