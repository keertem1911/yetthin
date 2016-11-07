package com.yetthin.web.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yetthin.web.commit.JtdoaValueMarket;
import com.yetthin.web.commit.QQMarketLevelUtilByMaster;
import com.yetthin.web.commit.RedisUtil;
import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.domain.barData;
import com.yetthin.web.persistence.barDataMapper;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Tuple;
import util.BarData;
import util.Contract;
import util.JHdboa;
import util.TickData;
import util.TickSort;
import util.UseRTH;

@Service("JtdoaDao")
public class JtdoaDao implements ValueFormatUtil,JtdoaValueMarket,QQMarketLevelUtilByMaster{
	
	private static JedisPool poolM=RedisUtil.getInstanceMsater();
	
	private static JedisPool poolS=RedisUtil.getInstanceSlave();
	
	@Autowired 
	private barDataMapper barDataMapper;
	
	private static Jedis jedis_M=null;
	private static Jedis jedis_S=null;
	static{
		 
		jedis_S=poolS.getResource();
		jedis_M=poolM.getResource();
	}
	private  String joinStringSplit(String [] array,String sp){
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i]);
			if(i<array.length-1)
				buffer.append(sp);
		}
		 
		return buffer.toString();
	}
	/**
	 * JNI SAVE LEVE2
	 * @param tickId
	 * @param symbol
	 * @param exchange
	 * @param currency
	 * @param market
	 * @param side
	 * @param price
	 * @param size
	 * @param checksum
	 */
	public void Leve2Depth(long tickId, String symbol,String exchange,String currency, String market, int side, double price, int size, int checksum){
		jedis_M.select(0);
		symbol= symbol+"."+exchange.toUpperCase();
		if(checksum<6){
			 
			int start =LEVEL2_INDEX_SIDE1;
			int index= start+(5-checksum);
			if(side==0){
				start=LEVEL2_INDEX_SIDE0;
				index= start+checksum-1;
			}
			 
			System.out.println(index);
			String value=jedis_M.get(symbol);
			String [] subStr=value.split(SPLIT_STR);
			Date date=new Date();
			String dateStr=format.format(date);
			subStr[DATE_INDEX]=dateStr;
			subStr[index]=Double.toString(price);
			subStr[index+10]=Integer.toString(size);
			value = joinStringSplit(subStr, SPLIT_STR);
			jedis_M.set(symbol, value);
		}
	}
	/**
	 * 涨幅 及 跌幅排序
	 * @param symbol
	 * @param exchange
	 * @param L1Value
	 */
	private void saveSortBalance(String symbol,String exchange,String L1Value,String openValue,String date){
//		jedis_M.select(1);
//		
//		double l1Val=Double.parseDouble(L1Value);
//		double openVal=Double.parseDouble(openValue);
//		double desc =(l1Val-openVal)/openVal;
//		int marketName=0;
//		if("SZ".equals(exchange.toUpperCase().trim())||"SH".equals(exchange.toUpperCase().trim())){
//			marketName=HU_SHEN;
//		}
//		if(desc>0)
//			jedis_M.zadd(MARKET[marketName][0]+":"+MARKET[marketName][1],desc,symbol+"."+exchange.toUpperCase()+":"+l1Val+":"+date);
//		else 
//			jedis_M.zadd(MARKET[marketName][0]+":"+MARKET[marketName][2],desc,symbol+"."+exchange.toUpperCase()+":"+l1Val+":"+l1Val+":"+date);
//		
//		jedis_M.select(0);
		
	}
	/**
	 * FROM JNI 
	 *  level1 的数据处理 根据不同的 tickType 更新对应的值
	 * @param tickId
	 * @param symbol 股票代码
	 * @param secType
	 * @param exchange 市场代码
	 * @param currency 
	 * @param tickType 更新类型
	 * @param L1Value 类型对应的值
	 * @param size 大小
	 */
	public void Level1Data(long tickId, String symbol,String secType,String exchange,String currency,int tickType,String L1Value, int size){
		/*jedis_M.select(0);
		String value=jedis_M.get(symbol+"."+exchange);
		String [] subStr=value.split(SPLIT_STR);
		String date=format.format(System.currentTimeMillis());
		System.out.println("1");
		subStr[DATE_INDEX]=date;
		double price=Double.parseDouble(L1Value);
		L1Value=doubleformat.format(price);
		switch(tickType){
		case 0:
			break;
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 4: // LAST 最新成交价
			
			subStr[LAST_PRICE_INDEX] =L1Value;
			saveSortBalance(symbol, exchange, L1Value, subStr[OPEN_INDEX], date);
			break;
		case 5: // 最后一次成交笔数
			subStr[PRE_VOLUME_INDEX] =L1Value;
			System.out.println(L1Value);
			break;
		case 6: // 开盘价
			subStr[OPEN_INDEX] =L1Value;
			break;
		case 7:// 最高价
			subStr[HEIGHT_INDEX] =L1Value;
			break;
		case 8: // 最低价
			subStr[LOW_INDEX] =L1Value;
			break;
		case 9: // 总成交量
			subStr[VOLUME_INDEX] =L1Value;
			break;
		case 10: // 收盘价
			break;
		case 11: // 涨停价
			subStr[LIMIT_UP_INDEX] =L1Value;
			break;  
		case 12: // 跌停价
			subStr[LIMIT_DOWN_INDEX] =L1Value;
			break;
		case 13: //昨日收盘价
			subStr[PRE_CLOSE_INDEX]=L1Value;
			break;
		case 14: // 今日结算
			break;
		case 15: // 昨日结算
			break;
		case 16: // 委托买入总量
			break; 
		case 17: // 委托卖出总量
			break;
		case 18: // 持仓量
			break;
		case 19: // 昨日持仓量
			break;
		case 20: // 成交总金额
			break;
		}
		value=joinStringSplit(subStr, SPLIT_STR);
		System.out.println("save succes "+value );
		jedis_M.set(symbol+"."+exchange, value);*/
	 
	}
	/**
	 * 获取指数字符串
	 * @param huShen 市场编号 
	 * @param begin 开始位置
	 * @param end  结束位置(不包含)
	 * @return
	 */
	public String [] getStockIndex(int huShen,int begin,int end,boolean master) {
		// TODO Auto-generated method stub
		String [][] STOCK_NAME=null;
		switch(huShen){
		case HU_SHEN:// 指数为沪深指数
			STOCK_NAME=HU_SHEN_STOCK_INDEX;
		break;
		case MEI_GU:
			break;
		case GANG_GU:
			break;
		}
		int size=end-begin+1; 
		if(size<0) size=STOCK_NAME.length;
		String [] StockIndexs=new String [size];
		/*
		 * 模拟指数部分值
		 */
		jedis_S.select(0);
		for (int i = begin; i < size; i++) {
		String value=jedis_S.get(HU_SHEN_STOCK_INDEX[i][0]);
		String [] subStr=value.split(SPLIT_STR);
		if(subStr.length>5){
		StockIndexs[i]=HU_SHEN_STOCK_INDEX[i][0]+":"+HU_SHEN_STOCK_INDEX[i][1]+":"+subStr[LAST_PRICE_INDEX]+":"+subStr[UP_DOWN_PRICE]+":"+subStr[UP_DOWN_PRICE_RATE];
		if(master)
			StockIndexs[i]+=":"+subStr[STOCK_AMPLITUPE]+":"+subStr[TOTLE_SUM_INDEX]+":"+subStr[VOLUME_INDEX];
		}
		}
	 	return StockIndexs;
	}
 
	/**
	 * 获取行情下的 股票涨跌榜信息 
	 * @param huShen  股票市场
	 * @param params 
	 * @param b 是否全部
	 * @return
	 */
	public Map<String, List<String>> getL1StockMarketData(int huShen,long begin,long end,
			String[] params,boolean master) {
		// TODO Auto-generated method stub
		Map<String, List<String>> map=new HashMap<>();
//		jedis_M.select(1);
//		Set<String> sets=jedis_M.keys(huShen+":*");
//		int size=b==false?4:-1;
//		for (String string : sets) {
//			Set<Tuple> tuple=jedis_M.zrevrangeByScoreWithScores(string, 0, size);
//			map.put(string, tuple);
//		}
		 Map<String, Set<Tuple>> mapTuple=new HashMap<>();
		jedis_S.select(1);
			for (int i=0;i<params.length;++i) {
				int index= Integer.parseInt(params[i]);
				String string=null;
				if(index ==0||index==1)
				 string=MARKET[huShen][0]+":"+MARKET[huShen][1];
				else
					string=MARKET[huShen][0]+":"+MARKET[huShen][index+1];
				if(string!=null){	
				if(jedis_S.zcard(string)<=end){
					end =jedis_S.zcard(string)-1;
				}
				Set<Tuple> range=null;
				switch(index){
				case 0:
					range=jedis_S.zrevrangeWithScores(string, begin,end);
					break;
				case 1:
					range=jedis_S.zrangeWithScores(string, begin,end); 
					break;
				case 2:
					range=jedis_S.zrevrangeWithScores(string, begin,end);
					break;
				}
				
				
				 if(range!=null)
				mapTuple.put(MARKET[huShen][0]+":"+index, range);
				}
			}
		jedis_S.select(0);
		
		/**
		 *  sb =>>  股票代码,名称,价格,涨跌幅率
		 */
		Set<Entry<String, Set<Tuple>>> set=mapTuple.entrySet();
		for (Entry<String, Set<Tuple>> entry : set) {
			String market = entry.getKey();
			List<String> symbolList=new ArrayList<>();
			Set<Tuple> tupleSet=entry.getValue();
			for (Tuple tuple : tupleSet) {
				String symbol=tuple.getElement();
				String redisValue = jedis_S.get(symbol);
				symbolList.add(redisValue+":"+symbol);
			}
			map.put(market, symbolList);
		}
			
		return map;
	}
	 
	/**
	 * FROM JNI 
	 * @param tickId
	 * @param symbol
	 * @param secType
	 * @param exchange
	 * @param currency
	 * @param price
	 * @param time
	 * @param volume
	 */
	public void tickPrice(long tickId, String symbol, String secType, String exchange, String currency, double price,
			String time, long volume) {
		// TODO Auto-generated method stub
			jedis_M.select(0);
			String value=jedis_M.get(symbol+"."+exchange.toUpperCase());
			String [] subStr=value.split(SPLIT_STR);
			String date=format.format(System.currentTimeMillis());
			System.out.println("2");
			subStr[DATE_INDEX]=date;
			
			String L1Value=doubleformat.format(price);
			 
				subStr[LAST_PRICE_INDEX] =L1Value;
				saveSortBalance(symbol, exchange, L1Value, subStr[OPEN_INDEX], date);
			//	subStr[PRE_VOLUME_INDEX] =Long.toString(volume);
				
			value=joinStringSplit(subStr, SPLIT_STR);
			jedis_M.set(symbol+"."+exchange.toUpperCase(), value);
	}
	/**
	 * 获取单支 股票的  level2 
	 * @param symbol
	 * @return
	 */
	public String getL2(String symbol) {
		// TODO Auto-generated method stub
		jedis_S.select(0);
		String value = jedis_S.get(symbol);
		
		return value;
	}
	/**
	 * 
	 * @param symbol
	 * @param jniCallBackType 回调函数类型
	 * @param [] params  参数列表  K线列表顺序 [barNum,currenyTime,cycType,cycNum]
	 * @return
	 */
	private List  connectJHdboa(String symbol,int jniCallBackType,String [] params){
		List listReturn=null;
		JHdboa jhd=new JHdboa();
		jhd.HdboaInit(new BarData(),new Contract(),new TickData());
		jhd.HdboaConnect("222.173.29.210", 7008);
		while(!jhd.connected)
		{
			System.out.println("wait");
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 SimpleDateFormat yy_MM_ddformat =new SimpleDateFormat("yyyy:MM:dd");
		 SimpleDateFormat ALL_format =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
//		 Contract contract=new Contract();
//		 contract.symbol="002362";
//		 contract.currency="CNY";
//		 contract.exchange="SZ";
//		 contract.secType="STK";
		 
		 
		 Contract contract=new Contract();
		 contract.symbol=symbol.split("[.]")[0];
		 contract.currency="CNY";
		 contract.exchange=symbol.split("[.]")[1];
		 contract.secType="STK";
		 long cntTime=0l;
		 
		 long current_Time=System.currentTimeMillis();
		 // 起始时间与终止时间之差 30分钟
		 long index_time=1000*60*30;
		 String dateStr=yy_MM_ddformat.format(current_Time);
		 Date beginTime = null;
		try {
			beginTime = ALL_format.parse(dateStr+" 09:30:00");
		} catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		 
		 if(jniCallBackType==1){
			List<TickSort> tickSort=null;
		 try {
		 Date endTime =ALL_format.parse(dateStr+" 15:01:00");
		 long fromTime=0l;
		 long toTime=0l;
		 // 第二天请求昨天的
		 long yestday=1000*60*60*24;
		
		 do{
			 jhd.setTickSorts(null);
			 if(endTime.getTime()<current_Time){// 超过 收盘时间
				 fromTime=endTime.getTime()-index_time-cntTime;
				 toTime=endTime.getTime();
			 }else{
				 if(beginTime.getTime()>current_Time){// 先于  开盘时间
					 fromTime=endTime.getTime()-index_time-cntTime-yestday;
					 toTime=endTime.getTime()-yestday;
				 }else{
					 toTime=current_Time;
					 fromTime=current_Time-index_time-cntTime;
				 }
			 }
				 
				 tickSort=jhd.getTickSorts();
				 System.out.println("from "+new Date(fromTime)+"  to "+new Date(toTime));
				int j= jhd.HdboaReqHistoricalTickData(3, contract, fromTime/1000, toTime/1000,UseRTH.USE_RTH.ordinal());
				System.out.println(j);
				Thread.sleep(1000);
				while(!jhd.isset){
					Thread.sleep(100);
				//	System.out.println(jhd.isset);
				}
					 Thread.sleep(1000);
					 System.out.println(tickSort.size());
					 cntTime+=1000*60;
		 }while(tickSort.size()<20&&fromTime>beginTime.getTime());
		 } catch (Exception e) {
			 // TODO Auto-generated catch block
			 e.printStackTrace();
		 } 
		 Collections.sort(tickSort);
	 	listReturn=tickSort;
		 }else{
			 //[barNum,currenyTime,cycType,cycNum]
			 if(params.length>3){
				 int barNum=Integer.parseInt(params[0]);
				 String currenyTime=params[1];
				 int  cycType=Integer.parseInt(params[2]);
				 int cycNum=Integer.parseInt(params[3]);
				 Date date = null;
				 long longDate=0l;
				try {
					date = ALL_format.parse(currenyTime);
					 longDate=date.getTime();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long beginTime1=0l;
				String []day_hourse=currenyTime.split("[\\s]");
				String day=day_hourse[0];
				String hourse=day_hourse[1];
				 
				int [][] SEASONS={{01,01,03,31},{04,01,06,30},{07,01,9,30},{10,01,12,31}};
				int [][] HalfYears ={{6,30},{12,31}};
				Calendar cal =Calendar.getInstance();
				 int yeartemp=Integer.parseInt(day.split("[:]")[0]);
				 int monthtemp=Integer.parseInt(day.split("[:]")[1]);
				 int daytemp=Integer.parseInt(day.split("[:]")[2]);
				cal.setTime(date);
			//	(0:SECOND,1:MINUTE,2:DAY,3:WEEK,4:MONTH,5:SEASON,6:HAFLYEAR,7:YEAR) 
				switch(cycType){
				case 0: cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)-(barNum*cycNum+1)); // second
					break;
				case 1: // mintue
					cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-(barNum*cycNum+1));
					break;
				case 2:  
					 cal.set(Calendar.HOUR_OF_DAY, 15);
					 cal.set(Calendar.MINUTE, 15);
					 cal.set(Calendar.SECOND, 15);
					 longDate=cal.getTimeInMillis();
					 cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-(barNum*cycNum+1));
					 
					break;
				case 3: // weekend
					cal.set(Calendar.WEEK_OF_MONTH,cal.get(Calendar.WEEK_OF_MONTH) -(barNum*cycNum+1));
					break; 
				case 4: // month
						for (int i = 0; i < barNum*cycNum; i++) {
							
							int month = cal.get(Calendar.MONTH);
							if(month<0){
								int year =cal.get(Calendar.YEAR);
								year--;
								cal.set(Calendar.YEAR, year);
								cal.set(Calendar.MONTH, 12);
							}else{
								cal.set(Calendar.MONDAY, month-1);
							}
						}
						
					break;
				case 5: //season
				{
					int cnt = SEASONS.length-1;
					 cal.set(Calendar.HOUR_OF_DAY, 15);
					 cal.set(Calendar.MINUTE, 15);
					 cal.set(Calendar.SECOND, 15);
					 int index=0;
					 for(int i=0;i<SEASONS.length;++i){
						 if(monthtemp>=SEASONS[i][0]&&monthtemp<=SEASONS[i][2]){
							 index=i;
							 break;
						 }
					 }
					 cal.set(Calendar.MONDAY, SEASONS[index][2]-1);
					 cal.set(Calendar.DAY_OF_MONTH, SEASONS[index][3]);
					 longDate=cal.getTimeInMillis();
					 int endcnt=barNum-1+(cnt-index);
					 int yearcnt=endcnt/SEASONS.length;
					 int indexcnt=cnt-(endcnt%SEASONS.length);
					 index= indexcnt;
					 cal.set(Calendar.YEAR, yeartemp-yearcnt);
					 cal.set(Calendar.MONDAY, SEASONS[index][0]-1);
					 cal.set(Calendar.DAY_OF_MONTH, SEASONS[index][1]);
					 cal.set(Calendar.HOUR_OF_DAY, 9);
					 cal.set(Calendar.MINUTE, 00);
					 cal.set(Calendar.SECOND, 00);
					 }
					break;
				case 6://halfyear
					{
						int [] halfYear=HalfYears[cycNum];
					int index=0;
					cal.set(Calendar.HOUR_OF_DAY, 15);
					 cal.set(Calendar.MINUTE, 15);
					 cal.set(Calendar.SECOND, 15);
					for(int i=0;i<HalfYears.length;++i){
						 if(monthtemp<=HalfYears[i][0]&&daytemp<=HalfYears[i][1]){
							 index=i;
							 break;
						 }
					 }
					cal.set(Calendar.MONTH, HalfYears[index][0]-1);
					cal.set(Calendar.DAY_OF_MONTH, HalfYears[index][1]);
					longDate=cal.getTimeInMillis();
					int cnt=HalfYears.length-1;
					int endcnt=barNum-1+(cnt-index);
					 int yearcnt=endcnt/HalfYears.length;
					 int indexcnt=cnt-(endcnt%HalfYears.length);
					 index=indexcnt;
					 cal.set(Calendar.YEAR, yeartemp-yearcnt);
					 cal.set(Calendar.MONDAY, HalfYears[index][0]-1);
					 cal.set(Calendar.DAY_OF_MONTH, HalfYears[index][1]);
					 cal.set(Calendar.HOUR_OF_DAY, 9);
					 cal.set(Calendar.MINUTE, 00);
					 cal.set(Calendar.SECOND, 00);
					}
					break;
				case 7://year
					 cal.set(Calendar.MONDAY, 11);
					 cal.set(Calendar.DAY_OF_MONTH, 31);
					 cal.set(Calendar.HOUR_OF_DAY, 16);
					 cal.set(Calendar.MINUTE, 00);
					 cal.set(Calendar.SECOND, 00);
					 longDate=cal.getTimeInMillis();
					int year=cal.get(Calendar.YEAR);
					cal.set(Calendar.YEAR, year-(barNum*cycNum));
					break;	
				}
				 int size=0;
				beginTime1 =cal.getTimeInMillis();
				 List<BarData> barDatas=null;
				 do{
				 jhd.setBarDatas(null);
				 barDatas=null;
				 barDatas=jhd.getBarDatas();
				 System.out.println(ALL_format.format(new Date(beginTime1))+" : "+ALL_format.format(new Date(longDate)));
				 int j =jhd.HdboaReqHistoricalData(12,contract,
						 beginTime1/1000,longDate/1000,cycType,cycNum,UseRTH.USE_RTH.ordinal());
				 if(j==1){
					 try {
					 while(!jhd.isIsset()){
							Thread.sleep(50);
					 }// while end
					 		Thread.sleep(2000);
					 		
					 } catch (InterruptedException e) {
						 // TODO Auto-generated catch block
						 e.printStackTrace();
					 }
				 }//if end 
					 		if(barDatas.size()<barNum){
					 			switch(cycType){
					 			case 0: 
					 				cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)-((barNum*cycNum)));
					 				break;
					 			case 1:
					 				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-((barNum*cycNum)/2));
					 				break;
					 			case 2:
					 			 cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-(((barNum*cycNum)/7)*2));
					 			 break;
					 			case 5:
					 				int cnt = SEASONS.length-1;
								 	 int index=0;
									 for(int i=0;i<SEASONS.length;++i){
										 if(monthtemp>=SEASONS[i][0]&&monthtemp<=SEASONS[i][2]){
											 index=i;
											 break;
										 }
									 }
									 int endcnt1=barNum-1+(cnt-index);
									 int yearcnt1=endcnt1/SEASONS.length;
									 int indexcnt1=cnt-(endcnt1%SEASONS.length);
									 index= indexcnt1;
									 cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-yearcnt1);
									 cal.set(Calendar.MONDAY, SEASONS[index][0]-1);
									 cal.set(Calendar.DAY_OF_MONTH, SEASONS[index][1]);
					 				break;
					 			}
					 			 
					 		}
					 		beginTime1=cal.getTimeInMillis();
					 		System.out.println("size ="+barDatas.size());
					 		if(size!=barDatas.size())
					 			size=barDatas.size();
					 		else break;
				 	}while(barDatas.size()<barNum);
				 Collections.sort(barDatas);
				 System.out.println(Arrays.asList(barDatas));
				 listReturn=barDatas;
				 }// params.length ==3 
		 }
			jhd.HdboaDisconnect();
//			jhd.HdboaDestory();
		 	jhd=null;
		  
		return listReturn;
	}
	public List<TickSort> getLevel2Detail(String symbol){
		List<TickSort> list=null;
		String []params=new String[1];
		list = connectJHdboa(symbol,1,params);
			 
		return list;
	}
	public String[] getIndexDetail(String indexCode) {
		// TODO Auto-generated method stub
		Jedis jedis=poolS.getResource();
		jedis.select(0);
		String [] subStr=jedis.get(indexCode).split(SPLIT_STR);
		RedisUtil.RealseJedis_S(jedis);
		return subStr;
	}
	private List<BarData> putBarData(){
		List<BarData> barDatas=null;
		
		return barDatas;
	}
	/**
	 * 查找 从当前时间之前指定周期及个数的K线
	 * @param barNum
	 * @param currenyTime
	 * @param type
	 * @param cycNum
	 * @return
	 */
	public List<BarData> getHistoryBar(String symbol,String barNum, String currenyTime, int type, int cycNum) {
		// TODO Auto-generated method stub
//		[barNum,currenyTime,cycType,cycNum]
		String [] params= new String[4];
		params[0]=barNum;
		params[1]=currenyTime;
		params[2]=Integer.toString(type);
		params[3]=Integer.toString(cycNum);
		
		List<BarData> list= connectJHdboa(symbol, 0, params);
		return list;
	}
	public String getLevel1MarketNum(String marketCode) {
		// TODO Auto-generated method stub
		Jedis  jedis = poolM.getResource();
		jedis.select(1);
		String num="0";
		int index =Integer.parseInt(marketCode.split("[:]")[1]);
		if(marketCode.split("[:]")[1].equals("0")||marketCode.split(":")[1].equals("1")){
			marketCode=marketCode.split("[:]")[0]+":0";
			Set<String> tupless;
			if(index==0)
				tupless =jedis.zrangeByScore(marketCode, 0, 200);
			else
				tupless =jedis.zrangeByScore(marketCode, -100, 0);
				
			num =Integer.toString(tupless.size());
		}else{
			num=Long.toString(jedis.zcard(marketCode));
			
		}
		RedisUtil.RealseJedis_M(jedis);
		return num;
	}
	/**
	 * 获取指数集合
	 */
	public List<barData> getStockIndex(String begin,String end,String time,String size){
		Map<String, String> map =new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("time", time);
		map.put("size", size);
		List<barData> lists= barDataMapper.getBetweenBeginAndEnd(map);
		return lists;
	}
	/**
	 * 获取单支股票的详细 日线值
	 * 
	 */
	public List<barData> getStockIndustryDK(Map map){
		List<barData> lists =barDataMapper.selectByPrimaryKey(map);
		return lists;
	}
}
