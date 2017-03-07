package com.yetthin.web.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import com.yetthin.web.commit.JtdoaValueMarket;

public class StockLevelTset implements JtdoaValueMarket{
	public static final String GET_URL =   "http://qt.gtimg.cn/q=";
	  
	  
    public static final String POST_URL = " http://localhost:8080/demo/  ";   


    

    public static void readContentFromGet(String url) throws IOException {   

        // 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编码   

      

        URL getUrl = new URL("http://table.finance.yahoo.com/table.csv?s=002797.SZ&a=06&b=01&c=2016&d=08&e=01&f=2016&g=d&ignore=.csv");   

        // 根据拼凑的URL，打开连接，URL.openConnection()函数会根据 URL的类型，返回不同的URLConnection子类的对象，在这里我们的URL是一个http，因此它实际上返回的是HttpURLConnection   

        HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();   

        // 建立与服务器的连接，并未发送数据   

        connection.connect();   

        // 发送数据到服务器并使用Reader读取返回的数据   

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"gb2312"));   

        System.out.println(" ============================= ");   

        System.out.println(" Contents of get request ");   

        System.out.println(" ============================= ");   

        String lines;   

        while ((lines = reader.readLine()) != null) {   
     	   		 
        	 
//        	String [] subStr = lines.split("=");
//        	String value=subStr[1];
//        	String []sub=value.split("~"); 
//        	for (int i = 0; i < sub.length; i++) {
//				System.out.println(i+" ="+sub[i]);
//			} 
 	   		 System.out.println(lines);
        }   

        reader.close();   

        // 断开连接   

        connection.disconnect();   

        System.out.println(" ============================= ");   

        System.out.println(" Contents of get request ends ");   

        System.out.println(" ============================= ");   

 }   



 public static void readContentFromPost() throws IOException {   

        // Post请求的url，与get不同的是不需要带参数   

        URL postUrl = new URL(POST_URL);   

        // 打开连接   

        HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();   

        //打开读写属性，默认均为false   
        connection.setDoOutput(true);                   
        connection.setDoInput(true);  
        // 设置请求方式，默认为GET   

        connection.setRequestMethod(" POST ");   

        // Post 请求不能使用缓存   

        connection.setUseCaches(false);   

        // URLConnection.setFollowRedirects是static 函数，作用于所有的URLConnection对象。   

        // connection.setFollowRedirects(true);   

        //URLConnection.setInstanceFollowRedirects 是成员函数，仅作用于当前函数   

        connection.setInstanceFollowRedirects(true);   

        // 配置连接的Content-type，配置为application/x- www-form-urlencoded的意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode进行编码   

        connection.setRequestProperty(" Content-Type ",   

                        " application/x-www-form-urlencoded ");   

        // 连接，从postUrl.openConnection()至此的配置必须要在 connect之前完成，   

        // 要注意的是connection.getOutputStream()会隐含的进行调用 connect()，所以这里可以省略   

        //connection.connect();   

        DataOutputStream out = new DataOutputStream(connection   

                        .getOutputStream());   

        //正文内容其实跟get的URL中'?'后的参数字符串一致   

        String content = " firstname= "+URLEncoder.encode(" 一个大肥人 ", " utf-8 ");   

        // DataOutputStream.writeBytes将字符串中的16位的 unicode字符以8位的字符形式写道流里面   

        out.writeBytes(content);   

        out.flush();   

        out.close(); // flush and close   

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));   

        String line;   

        System.out.println(" ============================= ");   

        System.out.println(" Contents of post request ");   

        System.out.println(" ============================= ");   

        while ((line = reader.readLine()) != null) {   

                System.out.println(line);   

        }   

        System.out.println(" ============================= ");   

        System.out.println(" Contents of post request ends ");   

        System.out.println(" ============================= ");   

        reader.close();   

        //connection.disconnect();   

 }   

    public static void main(String[] args) {   
    		
    	
        String [][] market=HU_SHEN_STOCK_INDEX;    
    	StringBuffer sb=new StringBuffer();
    	for (int i = 0; i < market.length; i++) {
    		String  subs=market[i][0].substring(market[i][0].lastIndexOf("."))+market[i][0].substring(0, market[i][0].lastIndexOf("."));
			sb.append(subs.substring(1).toLowerCase());
			System.out.println(subs.substring(1));
			if(i<market.length-1) sb.append(",");
		}
    
    	try {
			readContentFromGet(GET_URL+sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }   
}	











