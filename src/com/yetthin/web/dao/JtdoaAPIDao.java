package com.yetthin.web.dao;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.yetthin.web.commit.JtdoaValueMarket;
import com.yetthin.web.commit.QQMarketLevelUtilByMaster;
import com.yetthin.web.commit.QQMarketLevelUtilBySimple;
import com.yetthin.web.commit.RedisUtil;
import com.yetthin.web.commit.SinaMarketIndex;
import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.domain.BarDataOfDay;
import com.yetthin.web.domain.Group;
import com.yetthin.web.domain.ProfitData;
import com.yetthin.web.domain.Share;
import com.yetthin.web.domain.StockWithPriceAndRadio;
import com.yetthin.web.serviceImp.BaseService;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository("JtdoaAPIDao")
public class JtdoaAPIDao extends BaseService implements QQMarketLevelUtilByMaster, QQMarketLevelUtilBySimple,
		SinaMarketIndex, ValueFormatUtil, JtdoaValueMarket {

	 

	private static JedisPool jedispool = RedisUtil.getInstanceMsater();
	private static String auth="keertem1911";

	private String joinStringSplit(String[] array, String sp) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(array[i]);
			if (i < array.length - 1)
				buffer.append(sp);
		}

		return buffer.toString();
	}

	/**
	 * 
	 * @param values
	 * @param index
	 * @return 判断是否相同 及 节假日为星期内
	 */
	public int saveQQ_M_REQUEST_URL(List<String> values, boolean index) {
		int single = -1;
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		// TODO Auto-generated method stub
		jedis.select(0);
		for (int i = 0; i < values.size(); i++) {
			// 请求的信息
			if (values.get(i) != null && !"".equals(values.get(i))) {
				String[] subValue = values.get(i).split("&")[0].split(QQ_M_SPLIT_STR);

				String symbol = values.get(i).split("&")[1];
				// 股票代码
				String redisValue = jedis.get(symbol.substring(2) + "." + symbol.substring(0, 2).toUpperCase());
				try {
					// 保存的值
					if (redisValue != null) {
						String[] subStr = redisValue.split(SPLIT_STR);
						// 更新操作
						subStr[DATE_INDEX] = subValue[QQ_M_UP_DOWN_TIME];
						subStr[PRE_CLOSE_INDEX] = subValue[QQ_M_YST_CLOSE];// 前收盘价
						subStr[OPEN_INDEX] = subValue[QQ_M_OPEN_PRICE];// 开盘价
						subStr[HEIGHT_INDEX] = subValue[QQ_M_HEIGHT_PRICE];// 最高价
						subStr[LOW_INDEX] = subValue[QQ_M_LOW_PRICE];// 最低价
						subStr[LAST_PRICE_INDEX] = subValue[QQ_M_LAST_PRICE];// 最新价
						subStr[VOLUME_INDEX] = subValue[QQ_M_VOLUME];// 成交量
						subStr[TOTLE_SUM_INDEX] = subValue[QQ_M_TOTLE_SUM];// 成交额
						subStr[LIMIT_UP_INDEX] = subValue[QQ_M_LIMIT_UP];// 涨停价
						subStr[LIMIT_DOWN_INDEX] = subValue[QQ_M_LIMIT_DOWN];// 跌停价
						subStr[EXCHANGE_RATE] = subValue[QQ_M_EXCHANGE]; // 换手率
						subStr[UP_DOWN_PRICE] = subValue[QQ_M_UP_DOWN]; // 涨跌价
						subStr[UP_DOWN_PRICE_RATE] = subValue[QQ_M_UP_DOWN_RATE];// 涨跌率
						subStr[NAME] = subValue[QQ_M_NAME];
						if (subValue[QQ_M_LAST_DONE] != null) {
							subStr[LAST_DONE] = subValue[QQ_M_LAST_DONE].replace(":", "-");
						} else
							subStr[LAST_DONE] = "0";
						subStr[PRICE_EARING_RATIO] = subValue[QQ_M_PRICE_EARING_RATIO];
						if (subValue[QQ_M_STOCK_AMPLITUPE] == null || "".equals(subValue[QQ_M_STOCK_AMPLITUPE].trim()))

							subStr[STOCK_AMPLITUPE] = "0";
						else
							subStr[STOCK_AMPLITUPE] = subValue[QQ_M_STOCK_AMPLITUPE];
						if (subValue[QQ_M_FAMC] == null && "".equals(subValue[QQ_M_FAMC].trim()))
							subStr[FAMC] = "0";
						else
							subStr[FAMC] = subValue[QQ_M_FAMC];
						if (subValue[TOTLE_MAREKT_VALUE] == null || "".equals(subValue[TOTLE_MAREKT_VALUE].trim()))
							subStr[TOTLE_MAREKT_VALUE] = "0";
						else
							subStr[TOTLE_MAREKT_VALUE] = subValue[TOTLE_MAREKT_VALUE];
						if (subValue[QQ_M_TOTLE_NET_WORTH] == null || "".equals(subValue[QQ_M_TOTLE_NET_WORTH].trim()))
							subStr[TOTLE_NET_WORTH] = "0";
						else
							subStr[TOTLE_NET_WORTH] = subValue[QQ_M_TOTLE_NET_WORTH];
						for (int j = 0, k = 0; j < 10; k++, j += 2) {
							// 15 14 13 12 11
							// 25 24 23 22 21
							subStr[LEVEL2_INDEX_SIDE1 + 4 - k] = subValue[QQ_M_DEPTH_SIDE1 + j];// 卖方
																								// 卖5
							subStr[LEVEL2_INDEX_SIDE1 + 10 + 4 - k] = subValue[QQ_M_DEPTH_SIDE1 + j + 1];// 卖方
																											// 卖5
							// 16 17 18 19 20
							// 26 27 28 29 30
							subStr[LEVEL2_INDEX_SIDE0 + k] = subValue[QQ_M_DEPTH_SIDE0 + j];// 买方
																							// 买1
							subStr[LEVEL2_INDEX_SIDE0 + 10 + k] = subValue[QQ_M_DEPTH_SIDE0 + j + 1];// 买方
																										// 买1

						}
						redisValue = joinStringSplit(subStr, SPLIT_STR);
						String[] preValue = jedis.get(symbol.substring(2) + "." + symbol.substring(0, 2).toUpperCase())
								.split(SPLIT_STR);
						if (!preValue[LAST_DONE].equals(subStr[LAST_DONE])) {
							single = 1;
							 
							jedis.set(symbol.substring(2) + "." + symbol.substring(0, 2).toUpperCase(), redisValue);
							if (!index)
								saveSortBalance(symbol.substring(2), symbol.substring(0, 2).toUpperCase(), subValue);
						}

					}
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}
		RedisUtil.RealseJedis_M(jedis);
		return single;
	}

	/**
	 * 涨幅 及 跌幅排序
	 * 
	 * @param symbol
	 * @param exchange
	 * @param L1Value
	 */
	private void saveSortBalance(String symbol, String exchange, String[] subStr) {
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(1);
		double desc_reate = Double.parseDouble(subStr[QQ_M_UP_DOWN_RATE]);

		int marketName = 0;
		if ("SZ".equals(exchange.toUpperCase().trim()) || "SH".equals(exchange.toUpperCase().trim())) {
			marketName = HU_SHEN;
		}
		// 涨跌幅
		jedis.zadd(MARKET[marketName][0] + ":" + MARKET[marketName][1], desc_reate,
				symbol + "." + exchange.toUpperCase());

		// 换手率
		jedis.zadd(MARKET[marketName][0] + ":" + MARKET[marketName][3], Double.parseDouble(subStr[QQ_M_EXCHANGE]),
				symbol + "." + exchange.toUpperCase());
		jedis.select(0);
		RedisUtil.RealseJedis_M(jedis);
	}

	public void saveSina_REQUEST_URL_INDEX(List<String> values) {
		// TODO Auto-generated method stub
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(1);
		for (int i = 0; i < values.size(); i++) {
			String sybmol = values.get(i).split("&")[1];
			sybmol = sybmol.substring(2) + "." + sybmol.substring(0, 2).toUpperCase();
			String subValue = values.get(i).split("&")[0];
			jedis.set(sybmol, subValue);
		}
		RedisUtil.RealseJedis_M(jedis);
	}

	/**
	 * 获取行业数据
	 * 
	 * @author keerte return Map K ->行业代码 V -> 行业排序集合 及 Zset
	 */

//	private Map<String, Set<String>> getStock() {
//		Jedis jedis = jedispool.getResource();
//		jedis.select(2);
//		/**
//		 * 获取行业的排序集合
//		 */
//		Set<String> names = jedis.keys("*z");
//		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
//		for (String string : names) {
//
//			Set<String> str = jedis.zrange(string, 0, -1);
//			map.put(string.substring(0, string.length() - 1), str);
//		}
//		Set<Entry<String, Set<String>>> entry = map.entrySet();
//		// Iterator<Entry<String, Set<String>>> it =entry.iterator();
//		// while(it.hasNext()){
//		// Entry<String, Set<String>> entry1 = it.next();
//		// System.out.println(jedis.get(entry1.getKey()+"n")+"
//		// "+Arrays.asList(entry1.getValue()));
//		// }
//		RedisUtil.RealseJedis_M(jedis);
//		return map;
//	}

	/**
	 * 返回 当前价 成交量
	 * 
	 * @return
	 */
	public Map<String, String> getAllSymbolValueList() {
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(0);
		Map<String, String> map = new HashMap<String, String>();
		Set<String> symbols = jedis.keys("*");
		for (String string : symbols) {
			String[] arraysSymbols = jedis.get(string).split(SPLIT_STR);
			if (arraysSymbols.length > 4)
				map.put(string, arraysSymbols[LAST_PRICE_INDEX] + SPLIT_STR + arraysSymbols[VOLUME_INDEX] + SPLIT_STR
						+ arraysSymbols[UP_DOWN_PRICE] + SPLIT_STR + arraysSymbols[UP_DOWN_PRICE_RATE]+SPLIT_STR
						+arraysSymbols[DATE_INDEX]+SPLIT_STR+arraysSymbols[OPEN_INDEX]+SPLIT_STR
						+arraysSymbols[HEIGHT_INDEX]+SPLIT_STR+arraysSymbols[LOW_INDEX]+SPLIT_STR
						+arraysSymbols[NAME]
						); 
							
		}  
		RedisUtil.RealseJedis_M(jedis);
		return map;
	}
	public Map<String, String> getSourceStockValue(){
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(0);
		Map<String, String> map = new HashMap<>();
		Set<String> keys = jedis.keys("*");
		for(String key :keys){
			map.put(key, jedis.get(key));
		}
		RedisUtil.RealseJedis_M(jedis);
		return map;
	}

//	private Map<String, List<String>> getKValueByType(String type, int begin, int end) {
//		Map<String, List<String>> map = new HashMap<>();
//		Jedis jedis = jedispool.getResource();
//		int dbIndex = 0;
//		if (type != null) {
//			if ("S".equals(type.trim()))
//				dbIndex = 5;
//			else if ("M".equals(type.trim()))
//				dbIndex = 4;
//		}
//		if (end != -1 && begin > end) {
//			int temp = end;
//			end = begin;
//			begin = temp;
//		}
//		jedis.select(dbIndex);
//		Set<String> setKey = jedis.keys("*.K" + type);
//		for (String string : setKey) {
//			List<String> list = jedis.lrange(string, begin, end);
//			map.put(string.split("[.]")[0], list);
//		}
//		RedisUtil.RealseJedis_M(jedis);
//		return map;
//	}

	// private static final DecimalFormat df=new DecimalFormat("#.00");
	// private static final SimpleDateFormat dateFormat=new
	// SimpleDateFormat("HH-mm");
//	private void flushdbKS(int index) {
//		Jedis jedis = jedispool.getResource();
//		jedis.select(index);
//		jedis.flushDB();
//		RedisUtil.RealseJedis_M(jedis);
//
//	}
	// public void getMysqlLastDay(String userName,String passwd){
	// lastMysqlDay;
	// Map<Integer, String> map=new HashMap<>();
	// Connection conn =null;
	//
	// try {
	// Class.forName("com.mysql.jdbc.Driver");
	//// 时间:开:收:最高:最低:
	// conn
	// =DriverManager.getConnection("jdbc:mysql://localhost:3306/bardata",userName,passwd);
	// PreparedStatement pre =conn.prepareStatement("select
	// s.sid,s.dateTime,s.open,s.close,s.height,s.low from"
	// + "(select max(dateTime)as dateTime from bardata group by sid limit 1)t "
	// + "left join bardata as s on s.dateTime =t.dateTime ");
	// ResultSet res = pre.executeQuery();
	// while(res.next()){
	// Date date = res.getDate("dateTime");
	// String open = res.getString("open");
	// String close= res.getString("close");
	// String height = res.getString("height");
	// String low =res.getString("low");
	// Integer id =res.getInt("sid");
	// map.put(id,
	// date.toString()+SPLIT_STR+open+SPLIT_STR+close+SPLIT_STR+height+SPLIT_STR+low);
	// }
	// } catch (ClassNotFoundException e) {
 
	// e.printStackTrace();
	// } catch (SQLException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }finally{
	//
	// lastMysqlDay=map;
	// if(conn!=null){
	// try{
	// conn.close();
	// }catch(SQLException e1){
	// e1.printStackTrace();
	// }
	// }
	// }

	// }
	/**
	 * K线的分钟线为 时间:开:收:最高:最低:涨跌值:涨跌幅
	 */
	public void updateMinuteKandIndex() {
		// Jedis jedis=jedispool.getResource();
		// jedis.select(4);
		// Map<String, List<String>> mapKS = getKValueByType("S",0,-1);
		// Set<Entry<String, List<String>>> entrySet = mapKS.entrySet();
		// for (Entry<String, List<String>> entry : entrySet) {
		// double min;
		// double max;
		//
		// String KSname =entry.getKey();
		// List<String> KSValue= entry.getValue();
		// double close =Double.parseDouble(KSValue.get(0).split(SPLIT_STR)[2]);
		// double open
		// =Double.parseDouble(KSValue.get(KSValue.size()-1).split(SPLIT_STR)[2]);
		// double preIndex =
		// Double.parseDouble(KSValue.get(0).split(SPLIT_STR)[1]);
		//
		// max = Double.parseDouble(KSValue.get(0).split(SPLIT_STR)[3]);
		// min = Double.parseDouble(KSValue.get(0).split(SPLIT_STR)[4]);
		//// for (int i = 1; i < KSValue.size(); i++) {
		//// double temp=Double.parseDouble(KSValue.get(i).split(SPLIT_STR)[2]);
		//// if(temp>max) max = temp;
		//// if(temp<min) min = temp;
		//// }
		// long length= jedis.llen(KSname+".KM");
		// double preLast=0,updownValue=0,updownValueRate=0;
		// String pre=null;
		// if(length>0){
		// pre =jedis.lrange(KSname+".KM", 0, 0).get(0);
		//// 时间:开:收:最高:最低:涨跌值:涨跌幅
		// preLast=Double.parseDouble(pre.split(SPLIT_STR)[3]);
		//
		//
		// }else{
		//// 时间:开:收:最高:最低:
		// pre=lastMysqlDay.get(KSname);
		// preLast=Double.parseDouble(pre.split(SPLIT_STR)[2]);
		// }
		// if(preLast!=0){
		// updownValue =close-preLast;
		// updownValueRate= (updownValue)/close;
		// }
		// jedis.lpush(KSname+".KM",
		// dateFormat.format(System.currentTimeMillis())+SPLIT_STR+preIndex+SPLIT_STR
		// +open+SPLIT_STR+close+SPLIT_STR+max+SPLIT_STR+min+SPLIT_STR+df.format(updownValue)+SPLIT_STR+df.format(updownValueRate*100));
		//
		// }
		// flushdbKS(KS_SELECT);
		// RedisUtil.RealseJedis_M(jedis);
	}

	/**
	 * 在每次新的一秒 开始时获取 上一分钟的KM线中的 指数值就 收益
	 * 
	 * @param key
	 * @return
	 */
//	private String[] getMinutePre1(String key) {
//		Jedis jedis = jedispool.getResource();
//		jedis.select(4);
//		String[] value = new String[4];
//		long length = jedis.llen(key);
//		if (length != 0) {
//			String findKs = jedis.lrange(key, 0, 0).get(0);
//			value[0] = findKs.split(SPLIT_STR)[1];
//			value[1] = findKs.split(SPLIT_STR)[3];
//			value[2] = findKs.split(SPLIT_STR)[4];
//			value[3] = findKs.split(SPLIT_STR)[5];
//
//		} else {
//			value[0] = "0";
//			value[1] = "1000";
//			value[2] = "1000";
//			value[3] = "1000";
//		}
//		RedisUtil.RealseJedis_M(jedis);
//		return value;
//	}

	/**
	 * 返回 收盘价:开盘价:最高价:最低价:成交量 分钟线格式 日期:上一次的成交额:指数值
	 * 
	 * @return
	 */
	/*
	 * private Map<String, String> getMinuteKAndDeal(){ Jedis jedis =
	 * jedispool.getResource(); Map<String, List<Contract>> map =new
	 * HashMap<String,List<Contract>>(); jedis.select(4);
	 * 
	 * Set<String> setNames=jedis.keys("*"); for (String string : setNames) {
	 * StringBuffer sb=new StringBuffer(); List<String> list =
	 * jedis.lrange(string, 0, -1);
	 * sb.append(list.get(0).split("[,]")[2]+SPLIT_STR);
	 * sb.append(list.get(list.size()-1).split("[,]")[2]+SPLIT_STR); int max=0;
	 * int min=0; max = min =Integer.parseInt(list.get(0).split("[,]")[2]); for
	 * (int i = 1; i < list.size(); i++) { int value =
	 * Integer.parseInt((list.get(i).split("[,]")[2]); if(value>max) max =value;
	 * if(value<min) min = value; } sb.append(max+SPLIT_STR+min+SPLIT_STR);
	 * sb.append(list.get(0).split("[,]")[2]); } RedisUtil.RealseJedis_M(jedis);
	 * }
	 */
//	private void insertList(List<barData> bardataList) {
		// Connection connection=null;
		// boolean passed=true;
		// try {
		// Class.forName("com.mysql.jdbc.Driver");
		// connection =
		// DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/yetthin?useUnicode=true&amp;characterEncoding=utf8&zeroDateTimeBehavior
		// = convertToNull",
		// "yetthindb","yetthindb2016");
		// PreparedStatement pre = connection.prepareStatement(" insert into
		// bardata (dateTime, open, height, low, close, "+
		// "ystClose, volume, matchItems,"+
		// " sId)"+
		// "values (?,?,?,?,?,?,?,?,?)");
		// for (int i=0;i<bardataList.size();++i) {
		// barData bar=bardataList.get(i);
		// pre.setDate(1, bar.getDatetime());
		// pre.setString(2, bar.getOpen());
		// pre.setString(3, bar.getHeight());
		// pre.setString(4, bar.getLow());
		// pre.setString(5, bar.getClose());
		// pre.setString(6, bar.getYstclose());
		// pre.setString(7, bar.getVolume());
		// pre.setString(8, bar.getMatchitems());
		// pre.setInt(9, bar.getSid());
		// boolean flag=pre.execute();
		// }
		// } catch (Exception e) {
	 
		// e.printStackTrace();
		// passed=false;
		// }finally {
		// if(connection!=null)
		// try {
		// connection.close();
		// } catch (SQLException e) {
	 
		// e.printStackTrace();
		// passed=false;
		// }
		// }
		// return passed;
//	}
	private void InsertBarDataOfDay(Map<String, String> stockMap){
		Set<String> keys =stockMap.keySet();
		List<BarDataOfDay> barDataOfDays= new ArrayList<>();
		for (String string : keys) {
			String value =stockMap.get(string);
			if(value!=null){
				String [] prices = value.split(SPLIT_STR);
				if(prices.length>5&&!"0".equals(prices[0].trim())){
					BarDataOfDay day= new BarDataOfDay();
					// 0 -close 4 -date 5 open 6 height 7 low 8 name
					 
					day.setClose(Double.parseDouble(prices[0]));
					day.setOpen(Double.parseDouble(prices[5]));
					day.setHeight(Double.parseDouble(prices[6]));
					day.setLow(Double.parseDouble(prices[7]));
					String time= prices[4].substring(0, 8);
					time = time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6);
					day.setDatetime(time);
					day.setStockname(prices[8]);
					day.setStockcode(string);
					day.setVolume(prices[1]);
					barDataOfDays.add(day);
				}
			}
		}
		if(barDataOfDays.size()!=0){
		jdbcSqlDao jdbcDao =new jdbcSqlDao();
		 
		/**
		 * 更新日线
		 */
		jdbcDao.insertBarDataOfDay(barDataOfDays);
		/**
		 * 更新 日均线
		 */
		jdbcDao.updateKMvalue();
		}
 
	}
	/**
	 * 保存 更新当前收益表
	 */
	public void updateProfitData() {
		boolean sameBarData = checkbarDataSame();
		/**
		 * 和之前的数据库对比，防止在周内放假等因素 导致数据相同
		 */
		if(!sameBarData){
		/**
		 * 更新仓位表的 当前收益
		 */
		Map<String, String> stockList = getAllSymbolValueList();
		final jdbcSqlDao jdbcdao = new jdbcSqlDao();
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@");
		List<Share> shares = jdbcdao.selectSharesAll();
		System.out.println(format_hhmm.format(new Date()));
		Map<String, List<Share>> group_shareMap = shares.stream()
				.collect(Collectors.groupingBy(Share::getShareGroupId));
		/**
		 * 更新组合 收益列表
		 */
		List<Group> groups = jdbcdao.selectGroupsAll();
		DecimalFormat format_double1 = new DecimalFormat("#");
		for (Group group : groups) {
			List<Share> shareLists = group_shareMap.get(group.getGroupId());
			if (shareLists != null)
				if (shareLists.size() != 0) {
					shareLists.stream().forEach(d -> {
						String values = stockList.get(d.getShareStocklableId());
						if(values!=null){
						String [] substr =values.split(SPLIT_STR);
//						System.out.println(Arrays.asList(substr));
						if(substr!=null&&substr.length>=0){
						double stockPrice = Double.parseDouble(
								substr[0]);
						String strPrice = format_double1.format(stockPrice);
						if (!strPrice.equals("0")) {
							double num = d.getShareCurrentNum();
							double shareCurrentIncome = num * stockPrice;
							d.setShareCurrentIncome(shareCurrentIncome);
							jdbcdao.updateByPrimaryKey(d);
						}
						}
						}
					});
					double groupIncomeTotole = shareLists.stream().mapToDouble(Share::getShareCurrentIncome).sum();
					double groupStartTotole = shareLists.stream().mapToDouble(Share::getShareStartFund).sum();
					double shareFundNum = jdbcSqlDao.selectProfitData(group.getGroupId());
					ProfitData profitData = new ProfitData();
					profitData.setProfitdataId(getUUID());
					profitData.setProfitdataTime(LocalDate.now().toString()+" "+format_hhmm.format(new Date()));
					profitData.setProfitdataGroupId(group.getGroupId());
					if(shareFundNum==0){
						group.setGroupIncomeTotle(groupIncomeTotole-groupStartTotole );
						profitData.setProfitdataShareFundNum(group.getGroupInitMoney()+groupIncomeTotole-groupStartTotole);
					}else{
						group.setGroupIncomeTotle(shareFundNum-group.getGroupInitMoney() );
						profitData.setProfitdataShareFundNum(groupIncomeTotole -groupStartTotole+shareFundNum);
					}
					
					
					jdbcdao.insertProfitData(profitData);
					jdbcdao.updateByPrimaryKey(group);
				}
		}
		int  da=jdbcdao.deleteNumberZ();
		
		System.out.println("delete ");
		InsertBarDataOfDay(stockList);
		}
		/**
		 * 向 收益列表 插入 收益值
		 */

	}
	/**
	 * 检查是否存在相同的日线
	 */
	private boolean  checkbarDataSame() {
		// TODO Auto-generated method stub
		boolean same=false;
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(0);
		String SH000001= jedis.get("000001.SH");
		String SZ000001= jedis.get("000001.SZ");
		/**
		 * 选择备份数据库对比数据
		 */
		jedis.select(3);
		String SH000001BAK= jedis.get("000001.SH");
		String SZ000001BAK= jedis.get("000001.SZ");
		if(SH000001BAK!=null&&SZ000001BAK!=null&&SH000001.trim().equals(SH000001BAK.trim())&&SZ000001.trim().equals(SZ000001BAK.trim())){
			same=true;
		}else{
		bankupBardata();
		}
		RedisUtil.RealseJedis_M(jedis);
		return same;
	}

	/**
	 * 存秒线
	 */
	// public void updateSecondK() {
	// /**
	// * 获取行业名称
	// */
	// Map<String, String> mapValue=getAllSymbolValueList();
	// Jedis jedis=jedispool.getResource();
	// jedis.select(5);
	// /**
	// * 返回Map K ->行业代码 V -> 行业排序集合 及 Zset
	// */
	// Map<String, Set<String>> map = getStock();
	// Set<Entry<String, Set<String>>> set = map.entrySet();
	// Iterator<Entry<String, Set<String>>> it= set.iterator();
	//
	// while(it.hasNext()){
	// Entry<String, Set<String>> entrys = it.next();
	// /**
	// * 行业代码
	// */
	// String name =entrys.getKey();
	// double max;
	// double min;
	// double sum = 0;
	// /**
	// * 行业子股票
	// */
	// Set<String> value = entrys.getValue();
	// for (String string : value) {
	// String va=mapValue.get(string);
	//
	// if(va!=null&&!"".equals(va.trim())){
	// jedis.select(2);
	// jedis.zadd(name, Double.parseDouble(va.split(SPLIT_STR)[3]), string);
	// sum+=(Double.parseDouble(mapValue.get(string).split(SPLIT_STR)[0])*Double.parseDouble(mapValue.get(string).split(SPLIT_STR)[1]));
	// jedis.select(5);
	// }
	// }
	// Long length =jedis.llen(name+".KS");
	// double lastIndex=1000;
	//
	// System.out.println("length = "+length);
	// if(length!=0){
	// String value1 =jedis.lrange(name+".KS", 0, 0).get(0);
	// double preVal = Double.parseDouble(value1.split(SPLIT_STR)[1]);
	// double preIndex=Double.parseDouble(value1.split(SPLIT_STR)[2]);
	// max = Double.parseDouble(value1.split(SPLIT_STR)[3]);
	// min = Double.parseDouble(value1.split(SPLIT_STR)[4]);
	//
	// if(sum!=0&& preIndex!=0){
	// lastIndex=(sum/preVal)*preIndex;
	// if(lastIndex < min) min = lastIndex;
	// if(lastIndex > max) max = lastIndex;
	// }else{
	//
	// }
	// }else{
	//
	// String [] preMimutevalue= getMinutePre1(name+".KM");
	// if(!"0".equals(preMimutevalue[0])){
	// double preVal=Double.parseDouble(preMimutevalue[0]);
	// double preIndex= Double.parseDouble(preMimutevalue[1]);
	// max=Double.parseDouble(preMimutevalue[2]);
	// min=Double.parseDouble(preMimutevalue[3]);
	// lastIndex=(sum/preVal)*preIndex;
	// if(lastIndex < min) min = lastIndex;
	// if(lastIndex > max) max = lastIndex;
	// }else{
	// lastIndex=1000;
	// max =1000;
	// min =1000;
	// }
	// }
	// if(sum!=0){
	// jedis.lpush(name+".KS",dateFormat.format(System.currentTimeMillis())+SPLIT_STR+df.format(sum)+SPLIT_STR+df.format(lastIndex)+SPLIT_STR+df.format(max)+SPLIT_STR+df.format(min));
	//// System.out.println(name+".val"+ " -------- "+sum+SPLIT_STR+lastIndex);
	// }
	// }
	//
	//
	// RedisUtil.RealseJedis_M(jedis);
	/**
	 * 备份 日线数据
	 */
	private void bankupBardata() {
		// TODO Auto-generated method stub
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(0);
		Set<String> keys = jedis.keys("*");
		final Map<String, String> mapValue= new HashMap<>();
		keys.forEach(key->{
			mapValue.put(key, jedis.get(key));
		});
		jedis.select(3);
		keys.forEach(key->{
			jedis.set(key, mapValue.get(key));
		});
		
		RedisUtil.RealseJedis_M(jedis);
	}

	// }
	/**
	 * 返回查询的上一条记录
	 * 
	 * @param data
	 * @return
	 */
	// public Map<String, barData> getBarDataByTimeFromSql(String data ){
	//// 5kkj Map<String, barData> map = new HashMap<>();
	// return map;
	// }
	public int getRedisLengthBySelect(int n) {
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(n);
		Set<String> set = jedis.keys("*");

		RedisUtil.RealseJedis_M(jedis);
		return set.size();
	}
	public String StockIsMasterByStockCodeToString(List<String> list,boolean master){
		StringBuffer subSb=new StringBuffer();
		Map<String, String> mapStockMasterRedis=getSourceStockValue();
		subSb.append("\"value\":[");
		
		for(int i=0;i<list.size();++i){
			String stock= list.get(i);
			String values[] =mapStockMasterRedis.get(stock).split(SPLIT_STR);
		subSb.append(  "{\"name\":\""+values[NAME]+"\","+ 
                "\"stockID\":\""+stock+"\",");
	boolean plus =true;
 
		if(values[UP_DOWN_PRICE_RATE].indexOf("-")!=-1){
			plus=false;
			values[UP_DOWN_PRICE_RATE]=values[UP_DOWN_PRICE_RATE].substring(1);
		}
	subSb.append("\"increase\":\""+plus+"\","+ 
                "\"updown\": \""+values[UP_DOWN_PRICE]+"\","+
                "\"price\": \""+values[LAST_PRICE_INDEX]+"\","+
                "\"rate\": \""+values[UP_DOWN_PRICE_RATE]+"\","+
                "\"exchange\":\""+values[EXCHANGE_RATE]+"\"");
	if(master){
		 
		String lastDone=null;
//		System.out.println(values[LAST_DONE]);
		if(values[LAST_DONE]!=null&&!"".equals(values[LAST_DONE])){
		  String []subStr2= values[LAST_DONE].split("[/]");
//		System.out.println(Arrays.asList(subStr2));
		  if(subStr2.length>2)
		lastDone=subStr2[2];
		  else
			  lastDone="0";
		}else 
			lastDone="0";
		 subSb.append(",\"lastDone\":\""+lastDone+"\"");
		 subSb.append(",\"priceEaring\":\""+values[PRICE_EARING_RATIO]+"\"");
		 subSb.append(",\"stockAmplitupe\":\""+values[STOCK_AMPLITUPE]+"\"");
		 subSb.append(",\"famc\":\""+values[FAMC]+"\"");
		 subSb.append(",\"totleMarketValue\":\""+values[TOTLE_MAREKT_VALUE]+"\"");
		 subSb.append(",\"totleNetWorth\":\""+values[TOTLE_NET_WORTH]+"\"");
	}
	subSb.append("}");
	if(i<list.size()-1) subSb.append(",");
		}
		subSb.append("]");
		return subSb.toString();
	}
	/**
	 * 
	 * @param stockIndex
	 * @param begin
	 * @param end
	 * @param master
	 * @return 
	 * "value": [
                {
                    "name": "万福生科",
                    "stockID": " 300268.SZ",
                    "increase": "true",
                    "updown": "2.85",
                    "price": "31.30",
                    "rate": "10.02",
                    "exchange": "0.14",
                    "lastDone": "1",
                    "priceEaring": "",
                    "stockAmplitupe": "0.00",
                    "famc": "41.94",
                    "totleMarketValue": "0.14",
                    "totleNetWorth": "21.30"
                }]
 	 */
	public String getStockIndexChild(
			String stockIndex,int begin,int end,boolean master) {
		// TODO Auto-generated method stub
		Jedis jedis = jedispool.getResource();
		jedis.auth(auth);
		jedis.select(2);
		List<String> lists = jedis.lrange(stockIndex, 0, -1);
		Map<String, String> stock_reids_map= getAllSymbolValueList();
		List<StockWithPriceAndRadio> stock =lists.parallelStream().map(stockCode->{
			String stockPice = stock_reids_map.get(stockCode);
			StockWithPriceAndRadio stockWithPriceAndRadio=null;
			if(stockPice!=null){
				String [] stockSub=stockPice.split(SPLIT_STR);
				stockWithPriceAndRadio=new StockWithPriceAndRadio();
				 
				stockWithPriceAndRadio.setStockCode(stockCode);
				 
				stockWithPriceAndRadio.setPriceRate(stockSub[2]);
				return stockWithPriceAndRadio;
			}else{
				return null;
			}
		}).collect(Collectors.toList());
		stock =stock.parallelStream().filter(s->s!=null).sorted(Comparator.comparing(StockWithPriceAndRadio::getPriceRate).reversed()).collect(Collectors.toList());
//		/int []a =partitionPage(stock.size(), pageNum, pageSize);
		if(begin<0) begin=0;
		if(end>stock.size()-1) end =stock.size()-1;
		stock =stock.subList(begin, end);
		List<String> stockCodes = stock.stream().map(StockWithPriceAndRadio::getStockCode).collect(Collectors.toList());
		
		return StockIsMasterByStockCodeToString(stockCodes,master);
	}

//	public void flushKS() {
//		// TODO Auto-generated method stub
//		flushdbKS(5);
//
//	}

}
