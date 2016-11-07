package com.yetthin.web.dao;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

import net.oauth.OAuth;
import net.p5w.gdsp.client.base.GdspClient;
import net.p5w.gdsp.client.base.GdspException;
import java.util.*;

public class Opinionpost {

	static GdspClient client;
	public Opinionpost() {
		codes="000001,000002,600000,399001,000004,000005,000006,000007,000008,000009,000010,000011,000012,000014,000016,000017,000018,000019,000416,000421,000422,000423,000411";
		did="1";//1：媒体关注度 2：网民关注度 3：舆情关注度 4：舆情预警 5：新闻热度 6：事件热度;5.6无效
		servIp="1.85.59.245";
		oauIp="1.85.59.246";
		maplist=new ArrayList<Map<String,String>>();
		 
	}
	
	String servIp="";
	String oauIp="";

	String codes="";
	String did="";
	String strRtn="";
	List<Map<String,String>> maplist=null;
	
	public String getCodes() {
		return codes;
	}
	public void setCodes(String codes) {
		this.codes = codes;
	}
	public String getDid() {
		return did;
	}
	public void setDid(String did) {
		this.did = did;
	}
	public void getContent(){
		JSONObject json=new JSONObject();
		json.put("codes", codes);
		json.put("did", did);
		json.put("p","[currentPageNo:1,pageSize:30]");
		try {
				//第一个参数是App名,第二个参数是App密码,第三个参数是用户名,第四个参数是用户的密码, 
				client=new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
				GdspClient.setGdspBaseURL("http://"+servIp+"/gdsp/");// 
				GdspClient.setOauthBaseURL("http://"+oauIp+"/gdsp-oauth/");	
				//client.access();//这是个OAUTH认证,可以手动调用,默认访问第一个资源的时候会调用.
				//第一个参数是资源的URI,第二个参数是参数,如参数q=[a:1;b:2]参数o=[time:desc]则写成OAuth.newList("q","[a:1;b:2]","o","[time:desc]")
				String s=client.invoke(GdspClient.getGdspBaseURL()+"v1/public-opinion/topN", OAuth.newList(),
		    			"POST",new ByteArrayInputStream(json.toString().getBytes("utf-8")));
		
				strRtn=s;
		} catch (GdspException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
public void orgniseRlt() {
		
	maplist.clear();
		
		String newstr = strRtn.replace("},{", "\n}\n==============================================\n{\n");
		String[] mainStr = newstr.split("\"items\":\\[");

		String substr = mainStr[1];

		substr = substr.replace(",", "\n");

		String[] rltlist = substr.split("\n==============================================\n");
		for (int i = 0; i < rltlist.length; i++) {
			String tmpMap = rltlist[i];
			tmpMap = tmpMap.replace("{", "");
			tmpMap = tmpMap.replace("}", "");

			Map<String, String> rltmap = new HashMap<String, String>();
			String[] tmpLinelist = tmpMap.split("\n");
			for (int j = 0; j < tmpLinelist.length; j++) {

				String[] values = tmpLinelist[j].split(":");
				String value = "";
				if (values.length >= 2)
					value = values[1].replace("\"","");
				else
					value = "";

				String key = "";
				if (values.length >= 1)
					key = values[0].replace("\"", "");
				else
					key = values[0].replace("\"","");
				rltmap.put(key, value);
			}
			maplist.add(rltmap);
		}

		switch(did){
		case "1":
		{
			System.out.println("媒体关注度");
			break;
		}
		case "2":
		{
			System.out.println("网民关注度");
			break;
		}
		case "3":
		{
			System.out.println("舆情关注度");
			break;
		}
		case "4":
		{
			System.out.println("舆情预警");
			break;
		}
		case "5":
		{
			System.out.println("新闻热度");
			break;
		}
		case "6":
		{
			System.out.println("事件热度");
			break;
		}
		default:
			break;
		}
		 Iterator<Map<String, String>> mapit=maplist.iterator(); int i=0;
		 while(mapit.hasNext()) { Map<String, String>
		 tmpmp=((Map<String,String>)mapit.next());
		 System.out.println((i++)+":"+tmpmp.get("stkname")+"|"+tmpmp.get("stkcode")+"|"+tmpmp.get("val_diff")
		 .trim()); }
		 

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Opinionpost op=new Opinionpost();

//		op.setCodes("000001");
		op.setCodes("000001");
		op.setDid("2");
		op.getContent();
	    System.out.println(op.strRtn);
		op.orgniseRlt();
		op.setDid("3");
		op.getContent();
		op.orgniseRlt();
		op.setDid("4");
		op.getContent();
		op.orgniseRlt();
		op.setDid("1");
		op.setCodes("600000,601288");
		op.getContent();
		System.out.println(op.strRtn);
		op.orgniseRlt();	
		
	}

}

//package com.pb.demo;
/*
import net.oauth.OAuth;
import net.p5w.gdsp.client.base.GdspClient;
import net.p5w.gdsp.client.base.GdspException;
import java.io.ByteArrayInputStream;
import com.alibaba.fastjson.JSONObject;

//1闁靛棔鐒﹂弻濠囨⒒缂佹ê澶嶉柛娆欑秶缁辨┊OST闁哄倻鎳撶槐锟犳晬閿燂拷
public class Opinionpost {
	static Integer count=0;
	static GdspClient client;
	public Opinionpost(){
		
	}
	
	public static void init(){
		//try {
			if(client==null){
				//缂佹鍏涚粩瀛樼▔椤忓嫬妫橀柡浣哄濡茬pp闁告熬鎷风紒妤婂厸缁ㄢ晜绋夐鍕闁轰胶澧楀Σ绐p閻庨潧妫涢悥锟?缂佹鍏涚粭浣圭▔椤忓嫬妫橀柡浣哄濡叉悂鎮介妸锕€鐓曢柛姘炬嫹缂佹鍓欏ú鎾寸▔椤忓嫬妫橀柡浣哄濡叉悂鎮介妸锕€鐓曢柣銊ュ閻︽垿鎯嶉敓锟?
				client=new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
				GdspClient.setGdspBaseURL("http://1.85.59.245/gdsp/");//1闁靛棔鐒﹂弻濠囨⒒缂佹ê澶嶉柛娆欑秶缁辨┊OST闁哄倻鎳撶槐锟犳晬閿燂拷
				GdspClient.setOauthBaseURL("http://1.85.59.246/gdsp-oauth/");
			}
		} 
	
	public static String getNews(String sym){
		String s = "";
		try{
			GdspClient clientT;
			clientT=new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
			GdspClient.setGdspBaseURL("http://1.85.59.245/gdsp/");//1闁靛棔鐒﹂弻濠囨⒒缂佹ê澶嶉柛娆欑秶缁辨┊OST闁哄倻鎳撶槐锟犳晬閿燂拷
			GdspClient.setOauthBaseURL("http://1.85.59.246/gdsp-oauth/");
			s=clientT.invoke(GdspClient.getGdspBaseURL()+"v1/opinion/cmsproxy", 
					OAuth.newList("q","[para_name:org;para_value:"+sym+";cate:neg;s_date:-30;fcount:1;status:cpp;]",
						"p","[currentPageNo:1;pageSize:5]","o","[publishdate:decreasing]"));
		}
		catch (GdspException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
	public static String getMTNews(String sym){
		String s = "";
		JSONObject json1=new JSONObject();
		json1.put("did", "2");
		try{
			s=client.invoke(GdspClient.getGdspBaseURL()+"v1/public-opinion/topN", OAuth.newList("p","[currentPageNo:1;pageSize:3000]","q","[codes:"+sym+"]"),
					"POST",new ByteArrayInputStream(json1.toString().getBytes("utf-8")));
		}
		catch (GdspException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			
		}
		return s;
	}
	public static String getYJNews(String sym){
		String s = "";
		JSONObject json1=new JSONObject();
		json1.put("did", "4");
		try{
			s=client.invoke(GdspClient.getGdspBaseURL()+"v1/public-opinion/topN", OAuth.newList("p","[currentPageNo:1;pageSize:3000]","q","[codes:"+sym+"]"),
					"POST",new ByteArrayInputStream(json1.toString().getBytes("utf-8")));
		}
		catch (GdspException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e){
			
		}
		return s;
	}
	
	public static void main(String[] args) {
			Opinionpost t1=new Opinionpost();
			t1.init();
			String s1 = t1.getYJNews("000001,000002,600000,399001,000004,000005,000006,000007,000008,000009,000010,000011,000012,000014,000016,000017,000018,000019,000416,000421,000422,000423,000411");
			System.out.println("YJ:" + s1);
	}
}*/