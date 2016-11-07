package com.yetthin.web.commit;
/**
 * 腾讯行情接口 （详情类） 市场股类     数据格式
 * v_sz000858=
 * "51~五 粮 液~000858~27.78~27.60~27.70~417909~190109~227800~
 * 27.78~492~27.77~332~27.76~202~27.75~334~27.74~291~
 * 27.79~305~27.80~570~27.81~269~27.82~448~27.83~127~
 * 15:00:13/27.78/4365/S/12124331/24602|14:56:55/27.80/14/S/38932/24395|14:56:52/27.81/116/B/322585/24392|14:56:49/27.80/131/S/364220/24385|14:56:46/27.81/5/B/13905/24381|14:56:43/27.80/31/B/86199/24375~
 * 20121221150355~
 * 0.18~0.65~28.11~27.55~27.80/413544/1151265041~
 * 417909~116339~1.10~10.14~~28.11~27.55~2.03~1054.39~1054.52~3.64~30.36~24.84~";
 * 
 *    0: 未知  
 1: 名字  
 2: 代码  
 3: 当前价格  
 4: 昨收  
 5: 今开  
 6: 成交量（手） 
 7: 外盘  
 8: 内盘  
 9: 买一  
10: 买一量（手）  
11-18: 买二 买五  
19: 卖一  
20: 卖一量  
21-28: 卖二 卖五  
29: 最近逐笔成交   =》 现手 new
30: 时间  
31: 涨跌  
32: 涨跌%  
33: 最高  
34: 最低  
35: 价格/成交量（手）/成交额  
36: 成交量（手）   =》 总手 
37: 成交额（万）   =》金额 万
38: 换手率     =》 换手
39: 市盈率   =》市盈动   new 
40:   
41: 最高  
42: 最低  
43: 振幅    =》 振幅   new 
44: 流通市值 =》 流通市值 亿  new 
45: 总市值    => 总市值 亿  new 
46: 市净率    =》 市净值  new 
47: 涨停价  
48: 跌停价  

	请求参数  http://qt.gtimg.cn/q=sz000858 
 * @author Administrator 
 *
 */
public interface QQMarketLevelUtilByMaster {
	public static final String QQ_M_REQUEST_URL="http://qt.gtimg.cn/q=";
	public static final int QQ_M_NAME =1;// 名字  
	public static final int QQ_M_YMBOL=2;// 代码  
	public static final int QQ_M_LAST_PRICE=3;// 当前价格  
	public static final int QQ_M_YST_CLOSE=4;// 昨收  
	public static final int QQ_M_OPEN_PRICE=5;// 今开  
	public static final int QQ_M_DEPTH_SIDE0= 9;// 买一  
	 							//10 买一量（手）  
	  							//11-18; 买二 买五  
	public static final int QQ_M_DEPTH_SIDE1=19;// 卖一  
								//10  卖一量  
								//11-28  卖二 卖五  
	public static final int QQ_M_UP_DOWN_TIME=30;// 时间  
	public static final int QQ_M_UP_DOWN=31;// 涨跌  
	public static final int QQ_M_UP_DOWN_RATE=32;// 涨跌%  
	public static final int QQ_M_HEIGHT_PRICE=33;// 最高  
	public static final int QQ_M_LOW_PRICE=34;// 最低  
								// 35   价格/成交量（手）/成交额  
	public static final int QQ_M_VOLUME=36;// 成交量（手）  
	public static final int QQ_M_TOTLE_SUM=37;// 成交额（万）  
	public static final int QQ_M_EXCHANGE=38;// 换手率  
	public static final int QQ_M_LIMIT_UP=47;// 涨停价
	public static final int QQ_M_LIMIT_DOWN=48;// 跌停价  
	public static final int QQ_M_LAST_DONE=29;//: 最近逐笔成交   =》 现手 new
	public static final int QQ_M_PRICE_EARING_RATIO=39;//: 市盈率   =》市盈动   new 
	public static final int QQ_M_STOCK_AMPLITUPE=43;//: 振幅    =》 振幅   new 
	public static final int QQ_M_FAMC= 44;//: 流通市值 =》 流通市值 亿  new 
	public static final int QQ_M_TOTLE_MARKET_VALUE=45;//: 总市值    => 总市值 亿  new 
	public static final int QQ_M_TOTLE_NET_WORTH=46;//: 市净率    =》 市净值  new 

	
	public static final String QQ_M_SPLIT_STR="~";// 分割符   
	 
	 
}
