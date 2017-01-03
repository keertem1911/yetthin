package com.yetthin.test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class testTime {
	protected static DateTimeFormatter simple=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	private static int compareTime(String sc1,String sc2){
		 LocalDateTime time1 = LocalDateTime.parse(sc1, simple);
		 LocalDateTime time2 = LocalDateTime.parse(sc2, simple);
		 int cnt=0;
		 
		 Period p = Period.between(time2.toLocalDate(),time1.toLocalDate());
		 cnt=p.getDays();
		 if(p.getDays()==0){
			 Duration duration=Duration.between(time2,time1);
			 	cnt=duration.getNano();
			 	if(cnt>0) cnt=1;
			 	else cnt =0;
		 }
		return cnt;
		
	}
	public static void main(String[] args) {
		List<String> list=Arrays.asList("2016-12-14 15:34:30","2016-12-13 10:48:15","2016-12-13 11:31:20","2016-12-13 10:38:03");
		list =list.stream().sorted((s1,s2)->s2.compareTo(s1)).collect(Collectors.toList());
		System.out.println(Arrays.asList(list));
	}
}
