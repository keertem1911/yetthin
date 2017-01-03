package com.yetthin.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.sf.json.JSONObject;

public class JsonTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// 				String stocklableCode="A111.SZ";
//				String stock = stocklableCode.split("[.]")[0];
//				String [] arays = stock.split("");
//				for (int i =0;i<arays.length;++i) {
//					char ch=arays[i].toLowerCase().toCharArray()[0];
//					if(ch<'0'||ch>'9'){
//						arays[i]=Integer.toString((ch+'a')); 
//					}
//				}
//				stocklableCode = Stream.of(arays).collect(Collectors.joining());
//		 
//		Calendar cal = Calendar.getInstance();
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
//		LocalDate endDate= LocalDate.now();
//		LocalDate beginDate= endDate.minusMonths(1);
//		System.out.println(beginDate+" "+endDate);
		LocalDate data1= LocalDate.of(2016, 10, 1);
		LocalDate data2= LocalDate.of(2016, 12, 19);
		Period p= Period.between(data1, data2);
		System.out.println(p.getDays());
	}
}
