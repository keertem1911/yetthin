package com.yetthin.web.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.yetthin.web.domain.BarDataOfDay;

 
public	class myThread implements Runnable{
	private static JtdoaAPIDao jdApiDao=new JtdoaAPIDao();
	private static Map<String, String> map =jdApiDao.getAllSymbolValueList();
		private Snippet s=new Snippet();
		private List<String> list;
		private String	starty;
		private String startm;
		private String startd;
		private String endy;
		private String endm;
		private String endd;
		private String cycle;
		public myThread( List<String> codes,
			String	starty,String startm,String startd,String endy,String endm,String endd,String cycle) {
			// TODO Auto-generated constructor stub
			 
			list=codes ;
			this.starty = starty;
			this.startm = startm;
			this.startd = startd;
			this.endy = endy;
			this.endm = endm;
			this.endd = endd;
			this.cycle = cycle;
		}
		private jdbcSqlDao jdbcSqlDao = new jdbcSqlDao();
		private List<BarDataOfDay> totleList=new  LinkedList<BarDataOfDay>();
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			// TODO Auto-generated method stub
			for (String string : list) {
				System.out.println(string+"----------------------------------------");
				 selectStock(string, starty, startm, startd, endy, endm, endd, cycle);
			}
			jdbcSqlDao.insertBarDataOfDay(totleList);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("finish -------------------"+Thread.currentThread().getName());
		}
		public  void selectStock(String stockName,String starty ,String startm,String startd,String endy,
				String endm,String endd,String cycle) {
			UrlRequestDao urlDao = new UrlRequestDao();
			
			List<String> readContentFromGet=null;
			List<BarDataOfDay> urlResult = null;
			List<BarDataOfDay> sqlResult = null;
			Function<String, String> fun =line->line;
			
			String url = getUrl(stockName, starty, startm, startd, endy, endm, endd, cycle);
			try {
				readContentFromGet = urlDao.readContentFromGet(url,fun);
				if(readContentFromGet!=null&&readContentFromGet.size()!=0){
					urlResult = EncapsulationResult(readContentFromGet, stockName);
					sqlResult = jdbcSqlDao.selectByStockcode(stockName);
					 
					List<BarDataOfDay> distinct = distinct(urlResult, sqlResult);
//					jdbcSqlDao.insertBarDataOfDay(distinct);
					totleList.addAll(distinct);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		/*
		 * 数据结果集的封装
		 */
		private   List<BarDataOfDay> EncapsulationResult(List<String> list,String stockName) {
			ArrayList<BarDataOfDay> arrayList = new ArrayList<BarDataOfDay>();
			Iterator<String> iterator = list.iterator();
			if(iterator.hasNext()){
				iterator.next();
			}
			while (iterator.hasNext()) {
				String result = iterator.next();
				BarDataOfDay barDataOfDay = new BarDataOfDay();
				barDataOfDay.setStockcode(stockName);
				barDataOfDay.setStockname(map.get(stockName.toUpperCase()).split(":")[8]);
				String[] split = result.split(",");
				barDataOfDay.setDatetime(split[0]);
				barDataOfDay.setOpen(Double.parseDouble(split[1]));
				barDataOfDay.setHeight(Double.parseDouble(split[2]));
				barDataOfDay.setLow(Double.parseDouble(split[3]));
				barDataOfDay.setClose(Double.parseDouble(split[4]));
				barDataOfDay.setVolume(split[5]);
				arrayList.add(barDataOfDay);
			}
			return arrayList;
		}
		/*
		 * url的拼接
		 */
		private   String getUrl(String stockName, String starty ,String startm,String startd,
				String endy,String endm,String endd,String cycle){
			if("sh".equalsIgnoreCase(stockName.substring(stockName.lastIndexOf(".")+1,stockName.length()))){
				return "http://table.finance.yahoo.com/table.csv?s="+stockName.substring(0,stockName.lastIndexOf("."))+".ss"+"&d="+endm+"&e="+endd+"&f="+endy+"&g="+cycle+"&a="
			+startm+"&b="+startd+"&c="+starty+"&ignore=.cvs";
			}else{
				return "http://table.finance.yahoo.com/table.csv?s="+stockName+"&d="+endm+"&e="+endd+"&f="+endy+"&g="+cycle+"&a="
			+startm+"&b="+startd+"&c="+starty+"&ignore=.cvs";
			}
			
			
		}
		private   List<BarDataOfDay> move(List<BarDataOfDay> listUrl , List<BarDataOfDay> listSql) {
			ArrayList<BarDataOfDay> arrayList = new ArrayList<BarDataOfDay>();
			HashSet<BarDataOfDay> hashSet = new HashSet<BarDataOfDay>();
			
			return arrayList;
		}
		/*
		 * 去除结果集中的重复记录
		 */
		private   List<BarDataOfDay> distinct(List<BarDataOfDay> listUrl , List<BarDataOfDay> listSql) {
			
			Map<String, List<BarDataOfDay>> map =listUrl.stream()
					.collect(Collectors.groupingBy(BarDataOfDay::getDatetime));
			listSql.forEach(sql->{
				String date=sql.getDatetime();
				List<BarDataOfDay> day= map.get(date);
				if(day!=null){
					map.remove(date);
				}
			});
			List<BarDataOfDay>listNew=new ArrayList<>();
			Set<String> keys = map.keySet();
			keys.forEach(key->{
				listNew.add(map.get(key).get(0));
			});
			return listNew;
		}
	}
 
