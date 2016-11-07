package com.yetthin.web.commit;

public interface JtdoaValueMarket {
	public static final int HU_SHEN=0;
	public static final int GANG_GU=2;
	public static final int MEI_GU=3;
	public static final int GU_ZHI=4;
	public static final int BAN_KUAI=5;
	
	public static final String JTDOA_SPLIT_STR=":";
	//0 为涨幅榜 1 为跌幅榜 2换手率
	public static final String [][] MARKET={{0+"","0","1","2"}};
	public static final String [] NAME_MARKET={"涨幅榜","跌幅榜","换手率"};
	public static final String [] STOCK_INDEX_PROPERTIES={"国内指数"};
	public static final String [] [] HU_SHEN_STOCK_INDEX={	{"000001.SH","上证综指"},
			{"399001.SZ","深证成指"},
			{"399006.SZ","创业板指"},
			{"000300.SH","沪深300"},
			{"399101.SZ","中小板综"},
			{"000852.SH","中证1000"},
			{"399344.SZ","深证300R"},
			{"000905.SH","中证500"},
			{"399004.SZ","深证100R"},
			{"399012.SZ","创业300"},
			{"399102.SZ","创业板综"},
			{"000009.SH","上证380"},
			{"399107.SZ","深证A指"},
			{"399106.SZ","深证综指"},
			{"399005.SZ","中小板指"},
			{"881001.WI","万得全A"},
			{"399008.SZ","中小300"},
			{"399108.SZ","深证B指"},
			{"000003.SH","上证B指"},
			{"000906.SH","中证800"},
			{"000002.SH","上证A指"},
			{"000903.SH","中证100"},
			{"000015.SH","上证红利"},
			{"000016.SH","上证50"},
			{"000010.SH","上证180"}};
}
