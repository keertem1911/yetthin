package com.yetthin.web.commit;
/**
 * 腾讯股票行情接口 市场股类  (简洁版) 数据格式
 * v_s_sz000858=
 * "51~五 粮 液~000858~27.78~0.18~0.65~417909~116339~~1054.52";  
0: 未知  
1: 名字  
2: 代码  
3: 当前价格  
4: 涨跌  
5: 涨跌%  
6: 成交量（手）  
7: 成交额（万）  
8:   
9: 总市值  

	请求参数 http://qt.gtimg.cn/q=s_sz000858  
 * @author Administrator
 *
 */
public interface QQMarketLevelUtilBySimple {
	public static final String QQ_S_REQUEST_URL="http://qt.gtimg.cn/q=s_";
	public static final int QQ_S_NAME =1;// 名字  
	public static final int QQ_S_SYMBOL=2;// 代码  
	public static final int QQ_S_LAST_PRICE=3;// 当前价格  
	public static final int QQ_S_UP_DOWN=4;// 涨跌  
	public static final int QQ_S_UP_DOWN_RATE=5;// 涨跌% 
	public static final int QQ_S_VOLUME=6;// 成交量（手）  
	public static final int QQ_S_TOTLE_SUM=7;// 成交额（万）  
	public static final String QQ_S_SPLIT_STR="~";// 分割符 

}
