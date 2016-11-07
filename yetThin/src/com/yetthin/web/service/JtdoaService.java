package com.yetthin.web.service;

public interface JtdoaService {

	String[] getL1(int huShen,long begin,long end, String market,boolean indexMarket,boolean master);

	String[] getL2(String upperCase);

	String[] getLevel2Detail(String symbol);

	String[] getIndexDetail(String indexCode);

	String[] getStockIndexList(int begin, int end, String marketCode, boolean master);

	String[] getHistoryBar(String symbol,String barNum, String currenyTime, int type, int cycNum);

	String[] getLevel1MarketNum(String marketCode);

	String[] getStockIndustry(String beginIndex, String endIndex,String time,String size);
	
	String [] getStockIndustryDK(String id,String time,String size);
}
