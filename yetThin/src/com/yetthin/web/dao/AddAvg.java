package com.yetthin.web.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.yetthin.web.dao.AvgDao;
import com.yetthin.web.dao.jdbcSqlDao;
import com.yetthin.web.domain.BarDataOfDay;


public class AddAvg {
	public static void main(String[] args) {
		insertAvg();
	}
	/**
	 * 反射 求均线
	 */
	public static void reflectAvg(int i,int cnt,List<BarDataOfDay> list,Class<BarDataOfDay> clazz){
		try {
			Method method = clazz.getDeclaredMethod("setM"+cnt, Double.class);
			 
		 
			double sum=0;
			for (int j = 0; j < cnt; j++) {
				sum+=list.get(i+cnt).getClose();
			}
				sum=sum/(cnt+0.0);
			 long l1=Math.round(sum*100);
			 method.invoke(list.get(i),l1/100.0);
//			 list.get(i).setM5(l1/100.0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/*
	 * 写入平均数
	 */
	private static void insertAvg() {
	  List<BarDataOfDay> list = AvgDao.getAvg_Close();
	  Map<String, List<BarDataOfDay>> mapByStockcode= list.stream()
			  .collect(Collectors.groupingBy(BarDataOfDay::getStockcode));
	  Set<String> keys = mapByStockcode.keySet();
	  keys.forEach(key->{
		  List<BarDataOfDay> subList = mapByStockcode.get(key);
		 mapByStockcode.put(key, subList.stream().sorted(Comparator.comparing(BarDataOfDay::getDatetime).reversed()).collect(Collectors.toList()));
	  });
	  
	
	  keys.forEach(key->{
		  	List<BarDataOfDay> listSorted = mapByStockcode.get(key);
		  	int size= listSorted.size();
		  	for (int i = 0; i < size; i++) {
		  		int cnt =0;
		  		double dcnt=0;
				if(size-i-5>0){
					reflectAvg(i, 5, listSorted, BarDataOfDay.class);
				}
				if(size-i-10>0){
					reflectAvg(i, 10, listSorted, BarDataOfDay.class);
				}
				if(size-i-60>0){
					reflectAvg(i, 60, listSorted, BarDataOfDay.class);
				}
				if(size-i-120>0){
					reflectAvg(i, 120, listSorted, BarDataOfDay.class);
				}
					
			}
	  });
	  List<BarDataOfDay> insertAvgList=new ArrayList<>();
	   
	  keys.forEach(key->{
		  insertAvgList.addAll(mapByStockcode.get(key));
	  });
	   
	  AvgDao.updateBardatalist(insertAvgList);
	  
	  
	}
}
