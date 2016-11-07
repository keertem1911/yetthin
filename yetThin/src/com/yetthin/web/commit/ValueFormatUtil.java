package com.yetthin.web.commit;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
// 保存记录 信息记录
public interface ValueFormatUtil {
	public static final int DATE_INDEX=0; // 时间日期 小时分秒
	public static final int PRE_CLOSE_INDEX=1;// 前收盘价
	public static final int OPEN_INDEX=2;// 开盘价
	public static final int HEIGHT_INDEX=3;// 最高价
	public static final int LOW_INDEX=4;//最低价
	public static final int LAST_PRICE_INDEX=5;// 最新价
	public static final int VOLUME_INDEX=6;// 成交量
	public static final int TOTLE_SUM_INDEX=7;// 成交额
	public static final int LIMIT_UP_INDEX=8;// 涨停价
	public static final int LIMIT_DOWN_INDEX=9;//跌停价
	public static final int EXCHANGE_RATE=10; //换手率
	public static final int LEVEL2_INDEX_SIDE1=11;// 卖方 卖5
	public static final int LEVEL2_INDEX_SIDE0=16;// 买方  买1
	public static final int UP_DOWN_PRICE=31; // 涨跌价
	public static final int UP_DOWN_PRICE_RATE=32; // 涨跌率
	public static final int NAME=33;// 名称
	public static final int LAST_DONE=34;// 最近一次成交信息  现手
	public static final int PRICE_EARING_RATIO=35;// 市盈率 （市盈动）
	public static final int STOCK_AMPLITUPE=36;// 振幅
	public static final int FAMC=37;// 流通市值
	public static final int TOTLE_MAREKT_VALUE=38;// 总市值
	public static final int TOTLE_NET_WORTH=39;// 总净值
	
	public static final String SPLIT_STR=":";
	public static SimpleDateFormat format=new SimpleDateFormat("HH-mm");
	public static DecimalFormat   doubleformat=new DecimalFormat("#.###"); 
}
