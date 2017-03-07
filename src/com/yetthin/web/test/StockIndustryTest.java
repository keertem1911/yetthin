package com.yetthin.web.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.BarData;
import util.CYCTYPE;
import util.Contract;
import util.JHdboa;
import util.TickData;
import util.UseRTH;

public class StockIndustryTest {
	private static final String indexTime="2016-06-01";
	private static final SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd");
	private static final String [] symbols={"002679.SZ","600265.SS","002200.SZ","000592.SZ"};

	private String  YaHooget(String url){
		  
		        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码   

			StringBuffer sb=new StringBuffer();
		        URL getUrl = null;
				try {
					getUrl = new URL("http://biz.finance.sina.com.cn/stock/flash_hq/kline_data.php?&rand=random(10000)&symbol=sh600000&end_date=20150809&begin_date=20000101&type=plain");
//					getUrl = new URL("http://q.stock.sohu.com/hisHq?code=cn_300228&start=20130930&end=20131231&stat=1&order=D&period=d&callback=historySearchHandler&rt=jsonp");
				} catch (MalformedURLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}   

		        // 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection   

		        HttpURLConnection connection = null;
				try {
					connection = (HttpURLConnection) getUrl.openConnection();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}   

		        // 建立与服务器的连接，并未发送数据   

		        try {
					connection.connect();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   

		        // 发送数据到服务器并使用Reader读取返回的数据   

		        BufferedReader reader = null;
				try {
					reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"gb2312"));
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}   

		    
		        String lines;   

		        try {
					while ((lines = reader.readLine()) != null) {   
						System.out.println(lines);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   

		        try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}   

		        // 断开连接   

		        connection.disconnect();   

		       

		   return sb.toString();

	}
	private List<BarData> connetction(Contract contract){
		List<BarData> lists=null;
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
		long indexLongTime;
	 
		try {
			indexLongTime = format.parse(indexTime).getTime();
			 
			Calendar cal=Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-1);
			cal.set(Calendar.HOUR_OF_DAY, 16);
			lists=jhd.getBarDatas();
			long currenyTime= cal.getTimeInMillis();
			int j =jhd.HdboaReqHistoricalData(currenyTime%211, contract, indexLongTime/1000, currenyTime/1000, CYCTYPE.CYC_DAY.ordinal(), 1, UseRTH.USE_RTH.ordinal());
			if(j!=0){
				while(!jhd.isset){
					 	Thread.sleep(100);
				}
					Thread.sleep(500);
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lists;
	}
	private void countHistoryStockIndex(Map<String , String [] >map ){
	
		int dataSize=map.get(symbols[0]).length;
		String [] historyDataSame=new String[dataSize-1];
		for (int i = 1; i < dataSize; i++) {
			StringBuffer sb=new StringBuffer();
			for (int j = 0; j < symbols.length; j++) {
				String [] substr=map.get(symbols[j]);
				sb.append(substr[i]);
				if(j<symbols.length-1)
					sb.append("#");
			}
			historyDataSame[i-1]=sb.toString();
		}
		double preIndexVlaue=1000;
		double lastIndexVlaue=1000;
		DecimalFormat    df   = new DecimalFormat("######0.00");   
		System.out.println(Arrays.asList(historyDataSame));
		 	
		for (int i = historyDataSame.length-2; i >-1 ; --i) {
			String  [] lastsubValue= historyDataSame[i].split("[#]");
			String  [] preValue= historyDataSame[i+1].split("[#]");
			
			String dataTime=null;
			double preVlaue=0;
			double lastValue=0;
			boolean stop=false;
			for (int j = 0; j < lastsubValue.length; j++) {
				//Date,Open,High,Low,Close,Volume,Adj Close
				String  [] lastSymbol= lastsubValue[j].split("[,]");
				String  [] preSymbol= preValue[j].split("[,]");
				if(j==0)
					dataTime=lastSymbol[0];
				 
				preVlaue+=(Double.parseDouble(preSymbol[4])*Double.parseDouble(preSymbol[5]));
				lastValue+=(Double.parseDouble(lastSymbol[4])*Double.parseDouble(lastSymbol[5]));
				
			} 
			if(lastValue!=0&&preVlaue!=0){
			lastIndexVlaue=(lastValue/preVlaue)*preIndexVlaue;
			preIndexVlaue=(double)Math.round(lastIndexVlaue);
			} 
				
		}
	}
	public static void main(String[] args) {
	StockIndustryTest test=new StockIndustryTest();
	String [] historySymbols=new String[symbols.length];
	Map<String, String[] > map=new HashMap<>();
	//for (int i = 0; i < symbols.length; i++) {
		 
		System.out.println("------------------"+symbols[0]+"------------------------------");//http://finance.yahoo.com/d/quotes.csv?s=600000.SS&f=npl1
		 String tempSymbol=test.YaHooget("s="+symbols[0]+"&a=05&b=01&c=2016&d=07&e=22&f=2016&g=di&ignore=.csv");
		map.put(symbols[0], tempSymbol.split("[#]"));
		System.out.println("---------------------------------------------------");
		System.out.println("---------------------------------------------------");
		//		System.out.println(s);
	//}
	//test.countHistoryStockIndex(map);
	 
	}
}
