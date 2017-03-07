package com.yetthin.web.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.dao.UrlRequestDao;
import com.yetthin.web.dao.jdbcSqlDao;
import com.yetthin.web.domain.BarDataOfDay;

public class Snippet {
	static long currentTimeMillis;
	
	private static ExecutorService exector= Executors.newCachedThreadPool();
	private static int size;
	private static int spite;
	private static int page;
	
	public static void main(String[] args) {
		
		long currentTimeMillis = System.currentTimeMillis();
		
		String stockName,starty="2016",startm="11",startd="19",endy="2016",endm="11",endd="21",cycle="d",ignore=".csv";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(jdbcSqlDao.class.getResourceAsStream("/symbol.txt")));
		List<List<String>> list =new LinkedList<>(); 
		try {
			int count =0;
			List<String> newcodes= new LinkedList<>();
			while ((stockName=bufferedReader.readLine())!=null ) {
				count ++;
				newcodes.add(stockName);
				if(count%200==0){
					list.add(newcodes);
					newcodes=new LinkedList<>();
				}
			}
			if(newcodes.size()!=0)
			list.add(newcodes);
		  size= list.size();
		
		 spite=10;
		int n = size/spite;
		int ren = size%spite;
		for (int i = 0,page=i; i < list.size(); i++,page=i) {
			Runnable m =new myThread(list.get(i), starty, startm, startd, endy, endm, endd, cycle);
			exector.execute(m);
		}
		exector.shutdown();
	
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long adp = System.currentTimeMillis()-currentTimeMillis;
		System.out.println(adp);
	}

}

