package com.yetthin.test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.yetthin.web.commit.RedisUtil;

import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JsonTest {
	private static JedisPool jedispool = RedisUtil.getInstanceMsater();
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
//		LocalDate data1= LocalDate.of(2016, 10, 1);
//		LocalDate data2= LocalDate.of(2016, 12, 19);
//		Period p= Period.between(data1, data2);
//		System.out.println(p.getDays());
		LocalDate nowDate= LocalDate.now();
		LocalDate oldDate= nowDate.plus(-15, ChronoUnit.WEEKS);
		Jedis jedis = jedispool.getResource();
		jedis.auth("keertem1911");
		jedis.select(3);
		String s = jedis.get("000001.SZ");
		System.out.println(s);
	}
}
