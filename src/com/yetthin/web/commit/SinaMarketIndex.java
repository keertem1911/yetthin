package com.yetthin.web.commit;
/**
 * 新浪 大盘指数查询  数据格式
 * var hq_str_s_sh000001=
 * "上证指数,3094.668,-128.073,-3.97,436653,5458126";
 * 0 指数名称，
 * 1 当前点数，
 * 2 当前价格，
 * 3 涨跌率，
 * 4 成交量（手），
 * 5 成交额（万元）
 *  
 *  查询上证综合指数（000001）：
 * 请求参数  http://hq.sinajs.cn/list=s_sh000001
 * @author Administrator
 *
 */
public interface SinaMarketIndex {
	public static final String SINA_I_REQUEST_URL="http://hq.sinajs.cn/list=";
	public static final int SINA_I_INDEX_NAME=0;
	public static final int SINA_I_INDEX_POINT=1;
	public static final int SINA_I_LAST_PRICE=2;
	public static final int SINA_I_UP_DOWN_RATE=3;
	public static final int SINA_I_VOLUME=4;
	public static final int SINA_I_TOTLE_SUM=5;
	public static final String SINA_I_SPLIT_STR=",";// 分割符 

}
