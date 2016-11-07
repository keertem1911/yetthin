package com.yetthin.web.dao;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;

import com.alibaba.fastjson.JSONObject;

import net.oauth.OAuth;
import net.p5w.gdsp.client.base.GdspClient;
import net.p5w.gdsp.client.base.GdspException;
import java.util.*;

public class Opinionget {

	static GdspClient client;
	public Opinionget() {
		codes="000001,000002,600000";
		did="1";//1：媒体关注度 2：网民关注度 3：舆情关注度 4：舆情预警 5：新闻热度 6：事件热度;5.6无效
		
		maplist=new ArrayList<Map<String,String>>();
		
	}

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
		try {
			//if(client==null){
				//第一个参数是App名,第二个参数是App密码,第三个参数是用户名,第四个参数是用户的密码, 
				client=new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
				GdspClient.setGdspBaseURL("http://1.85.59.245/gdsp/");//1、新闻接口（POST方式）
				GdspClient.setOauthBaseURL("http://1.85.59.246/gdsp-oauth/");
				//client.access();//这是个OAUTH认证,可以手动调用,默认访问第一个资源的时候会调用.
				//第一个参数是资源的URI,第二个参数是参数,如参数q=[a:1;b:2]参数o=[time:desc]则写成OAuth.newList("q","[a:1;b:2]","o","[time:desc]")
				String s=client.invoke(GdspClient.getGdspBaseURL()+"v1/public-opinion/topN", OAuth.newList(),
		    			"POST",new ByteArrayInputStream(json.toString().getBytes("utf-8")));
				//System.out.println(s);
				strRtn=s;
				//this.writeJson(JSONObject.parse(s));
			//}
		} catch (GdspException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
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

		Opinionget op=new Opinionget();
		
		try {
			//	if(client==null){
					//第一个参数是App名,第二个参数是App密码,第三个参数是用户名,第四个参数是用户的密码, 
					client=new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
					GdspClient.setGdspBaseURL("http://1.85.59.245/gdsp/");//1、新闻接口（POST方式）
					GdspClient.setOauthBaseURL("http://1.85.59.246/gdsp-oauth/");
					//client.access();//这是个OAUTH认证,可以手动调用,默认访问第一个资源的时候会调用.
					//第一个参数是资源的URI,第二个参数是参数,如参数q=[a:1;b:2]参数o=[time:desc]则写成OAuth.newList("q","[a:1;b:2]","o","[time:desc]")
					String s=client.invoke(GdspClient.getGdspBaseURL()+"v1/000002/opinion-index", 
							OAuth.newList()
					);
					System.out.println(s);
					//this.writeJson(JSONObject.parse(s));
					/*s=client.invoke(GdspClient.getGdspBaseURL()+"v1/opinion/cms", 
							OAuth.newList("q","[para_name:org;para_value:000001;cate:neg;s_date:-30;fcount:1;status:cpp;]",
								"p","[currentPageNo:1;pageSize:5]","o","[publishdate:decreasing]")
					);
					System.out.println(s);*/
			//	}
			} catch (GdspException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
