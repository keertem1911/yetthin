package com.yetthin.web.commit;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import util.Contract;
import util.Level1Value;
import util.Level2Value;

public class RedisOfReader implements ValueFormatUtil{
	private static JedisPool pool=RedisUtil.getInstanceMsater();
	
	 
	 
	private static final String INIT_STRING="0:0:0:0:0:0:0:0:0:0:0"
//			   1	   2     3     4     5   6	  7    8         >> SUM 11
	 	+ ":0:0:0:0:0:0:0:0:0:0" 
//		 	9    10    11    12    13     14    15    16    17    18  >>SUM    10   
	 	+ ":0:0:0:0:0:0:0:0:0:0:0:0:0"
//		  19 20 2122 23 24 25 26  27 28 29 30 31 32 33          TOTLE 33 INDEX 0 -32
	 	+":0:0:0:0:0:0";
//		34 35 36 37 38 39  >> SUM 40 		
	 
	/**
	 * 
	 */
	public static void initReadInredisKeyLevel1(List<Contract> list){
		Jedis jedis=pool.getResource();
		jedis.select(0);
		for (Contract contract : list) {
//			if(!"".equals(contract.getSymbol()+"."+contract.getExchange())){
//				break;
//			}
			jedis.set(contract.getSymbol()+"."+contract.getExchange(), INIT_STRING);
		}
		
		RedisUtil.RealseJedis_M(jedis);
	}
	/**
	 * 
	 * @param symbol 股票代码
	 * @param side 买或卖 
	 * @param checkSum 层级
	 */
	// 模糊匹配 查询key
	public static void getL2BysideAndCheckSum(String symbol,int side,int checkSum) {
			Jedis jedis=pool.getResource();
				jedis.select(1);
		
		 		String l2=jedis.hget(symbol,side+":"+checkSum);
				System.out.println(l2);
			RedisUtil.RealseJedis_M(jedis);
	}
	/**
	 * 返回指定 代码的 摆单 list 
	 * @param symbol
	 * @param exchange
	 * @return
	 */
	public static String getL2Value(String symbol){
		List<Level2Value> list2=new LinkedList<>();
		Jedis jedis=pool.getResource();
		jedis.select(0);
		 String value=jedis.get(symbol.toUpperCase());
		 StringBuffer sb=new StringBuffer();
		String [] subStr=value.split(SPLIT_STR);
		for (int i = 0; i < 10; i++) {
			sb.append(subStr[LEVEL2_INDEX_SIDE1+i]);
			sb.append(":");
			sb.append(subStr[LEVEL2_INDEX_SIDE1+i+10]);
			if(i!=9)
				sb.append(",");
		}
		RedisUtil.RealseJedis_M(jedis);
		return sb.toString();
	}
	public static String getL2Value(String symbol,String exchange){
		return getL2Value(symbol+":"+exchange);
		
	}
	/**
	 *  根据市场代码解析 
	 * @param exchange  市场代码
	 * @return 返回本市场的所有股票
	 */
	// 解析 l1  
	public static List<Level1Value> getLevel1(String exchange){
		Jedis jedis=pool.getResource();
			jedis.select(0);
			Map<String , String> map=jedis.hgetAll(exchange);
			List<Level1Value> listsL1= new LinkedList<>();
			Set<Entry<String, String>> entry = map.entrySet();
			Iterator<Entry<String, String>> it = entry.iterator();
			Class clazz=Level1Value.class;
			while(it.hasNext()){
				Level1Value con=new Level1Value();
				Entry<String, String> obj = it.next();
				String sybmol =obj.getKey();
				String fieds= obj.getValue();
				if(!fieds.equals("")){
				String [] param=fieds.split(",");
				for (int i = 0; i < param.length; i++) {
					String [] sub=param[i].split("=");
					String field =(sub[0].substring(0, 1)).toUpperCase()+sub[0].substring(1);
					try {
						Method method = clazz.getDeclaredMethod("set"+field, String.class);
						method.invoke(con, sub[1]);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				}
				con.setSymbol(sybmol);
				listsL1.add(con);
			}
			 Collections.sort(listsL1);
			 RedisUtil.RealseJedis_M(jedis);
			return listsL1;
		}
	public static void main(String[] args) {
//		List<Level2Value> list=getL2Value("002647", "SZ");
//		System.out.println(JSON.toJSON(list));
//		for (Level2Value level2Value : list) {
//			System.out.println(level2Value);
//		}
	}
	/**
	 * 保存代码对应的名字
	 * @param names
	 */
	public static void initNameToSymbol(Map<String, String> names) {
		// TODO Auto-generated method stub
		Jedis jedis=pool.getResource();
		jedis.select(0);
		
		Set<Entry<String, String>> sets=names.entrySet();
		Iterator<Entry<String, String>> it =sets.iterator();
		while(it.hasNext()){
			Entry<String, String> entry=(Entry<String, String>)it.next();
			jedis.set("name"+entry.getKey(), entry.getValue());
		}
		RedisUtil.RealseJedis_M(jedis);
	}
	public static void initReadInredisKeyLevel1Index(String[][] huShenStockIndex) {
		// TODO Auto-generated method stub
		Jedis jedis=pool.getResource();
		jedis.select(0);
		
		for (int i = 0; i < huShenStockIndex.length; i++) {
			
			jedis.set(huShenStockIndex[i][0], INIT_STRING);
			
		}
		
		RedisUtil.RealseJedis_M(jedis);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
