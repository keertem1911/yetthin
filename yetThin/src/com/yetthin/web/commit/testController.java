package com.yetthin.web.commit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testController {
	public   String sendPost(final String url, final String param) {  
		  
		final PrintWriter out = null;  
		final BufferedReader in = null;  
		final String result = "";  
        try {  
        	ExecutorService cache=Executors.newCachedThreadPool();
        	cache.execute(new Runnable() {
				public void run() {
					 PrintWriter out = null;  
					  BufferedReader in = null; 
		            //打开和URL之间的连接  
		            URLConnection conn=null;
		            URL realUrl=null;
		            String error=null;
					try {
						realUrl = new URL(url);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						conn = realUrl.openConnection();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
					//setContentType(URLEncodedUtils.CONTENT_TYPE);
		            //设置通用的请求属性  
		            conn.setRequestProperty("accept", "*/*");  
		            conn.setRequestProperty("connection", "Keep-Alive");  
		            conn.setRequestProperty("user-agent",  
		                "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");  
		            //发送POST请求必须设置如下两行  
		            conn.setDoOutput(true);  
		            conn.setDoInput(true);  
		           conn.setRequestProperty("Cookie", "JSESSIONID=BF1ACBD7472EBFD563BD4382C4903E32"  );  
		            //获取URLConnection对象对应的输出流  
		            try {
						out = new PrintWriter(conn.getOutputStream());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		            //发送请求参数  
		            out.print(param);  
		            //flush输出流的缓冲  
		            out.flush();  
		            //定义BufferedReader输入流来读取URL的响应  
		            try {
						in = new BufferedReader(  
						    new InputStreamReader(conn.getInputStream(),"utf-8"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		            String line=null;  
		            try {
						while ((line = in .readLine()) != null) {  
						    System.out.println(line);  
						}
						String key=null;
						String sessionId=null;
						   if (conn != null) { 
					           for (int i = 1; (key = conn.getHeaderFieldKey(i)) != null; i++) { 
					              if (key.equalsIgnoreCase("Set-Cookie")) { 
					                  sessionId = conn.getHeaderField(key); 
					                  System.out.println(sessionId);
					                  break;
					              } 
					           } 
					        } 
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
              
        } catch (Exception e) {  
            System.out.println("发送POST请求出现异常！" + e);  
            e.printStackTrace();  
        }  
        //使用finally块来关闭输出流、输入流  
        finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if ( in != null) { in .close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  
  
  
    //提供主方法，测试发送GET请求和POST请求  
    public static void main(String args[]) {  
             //发送POST请求  
        String s1=null; 
		try {
//			System.out.println("发送验证码     ======");
//			s1 =new  testController().sendPost("http://www.cktim.net/yetThin/user/getRegisterVerify",  
//			    "phoneNum=18829290541");
//			Thread.sleep(4000);
//			
			
 
			s1 =new  testController().sendPost("http://localhost:8080/yetThin/user/updateJPushStatus",  
			    "userID=4018787c83f9485ebe6c3d4614d8a6a9&JpushStatus=1&JpushType=1");
			Thread.sleep(4000);
			
//			s1 =new  testController().sendPost("http://www.cktim.net/yetThin/user/register",  
//				    "phoneNum=18829290541&verifyCode=290541&password=123456");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
        System.out.println(s1);  
    }  

}
