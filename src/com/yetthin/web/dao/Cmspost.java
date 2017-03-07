package com.yetthin.web.dao;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import net.oauth.OAuth;
import net.p5w.gdsp.client.base.GdspClient;
import net.p5w.gdsp.client.base.GdspException;

public class Cmspost {

	static GdspClient client;
	
	String servIp="";
	String oauIp="";
	
	String s_date = "";// 开始时间 YYYY-MM-DD HH:mm:ss,支持按周 -7，按月 -30，按季 -90
	String cate = "";// 新闻情感 all,neg
	String para_name = "";// org(参数)
	String num = "";// 每页数量
	String page = "";// 分页
	String fcount = "";// 1
	String format = "";// json 格式返回
	String status = "";// cpp 处理的新闻
	String sort = "";// 排序
						// response|summary|publishdate|sourceinfo|storetime|reference|samecount|title:increasing|decreasing
	String para_value = "";// 股票代码列表 “XXXXXX,XXXXXX,XXXXXX"
	String did = "";// 1：媒体关注度 2：网民关注度 3：舆情关注度 4：舆情预警 5：新闻热度 6：事件热度

	String strReturn = "";
	List<Map<String, String>> maplist;

	public Cmspost() {
		
		servIp="1.85.59.245";
		oauIp="1.85.59.246";
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式
		Date now = new Date();
		s_date = df.format(new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000));// 默认七天前

		cate = "all";// 默认所有

		para_name = "org";

		num = "5";// 默认每页5条

		page = "10";// 默认分10页

		fcount = "1";

		format = "json";

		status = "cpp";// (发布的)

		sort = "publishdate:decreasing";

		para_value = "600034";

		did = "2";
		
		maplist = new ArrayList<Map<String, String>>();
	}

	public String getS_date() {
		return s_date;
	}

	public void setS_date(String s_date) {
		this.s_date = s_date;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getPara_value() {
		return para_value;
	}

	public void setPara_value(String para_value) {
		this.para_value = para_value;
	}

	public String getDid() {
		return did;
	}

	public void setDid(String did) {
		this.did = did;
	}

	public String getContent() {
		try {

			JSONObject json = new JSONObject();
			json.put("s_date", this.s_date);
			json.put("cate", this.cate);
			json.put("para_name", this.para_name);
			json.put("num", this.num);
			json.put("page", this.page);
			json.put("fcount", this.fcount);
			json.put("format", this.format);
			json.put("status", this.status);
			json.put("sort", this.sort);
			json.put("para_value", this.para_value);
			// json.put("did", this.did);
			
			// 第一个参数是App名,第二个参数是App密码,第三个参数是用户名,第四个参数是用户的密码,
			client = new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
			GdspClient.setGdspBaseURL("http://"+servIp+"/gdsp/");// 1、新闻接口（POST方式）
			GdspClient.setOauthBaseURL("http://"+oauIp+"/gdsp-oauth/");	
			
			// client.access();//这是个OAUTH认证,可以手动调用,默认访问第一个资源的时候会调用.
			// 第一个参数是资源的URI,第二个参数是参数,如参数q=[a:1;b:2]参数o=[time:desc]则写成OAuth.newList("q","[a:1;b:2]","o","[time:desc]")
			String s = client.invoke(GdspClient.getGdspBaseURL() + "v1/opinion/cmsproxy", OAuth.newList(), "POST",
					new ByteArrayInputStream(json.toString().getBytes("utf-8")));

			strReturn = s;
			return s;
		} catch (GdspException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public void orgniseRlt() {
		String newstr = strReturn.replace("},{", "\n}\n==============================================\n{\n");
		String[] mainStr = newstr.split("__KEY_DATAS\":\\[");

		String substr = mainStr[1].replace("\",\"", "\"\n\"");

		String[] rltlist = substr.split("\n==============================================\n");
		for (int i = 0; i < rltlist.length; i++) {
			String tmpMap = rltlist[i];
			tmpMap = tmpMap.replace("{", "");
			tmpMap = tmpMap.replace("}", "");

			Map<String, String> rltmap = new HashMap<String, String>();
			String[] tmpLinelist = tmpMap.split("\n");
			for (int j = 0; j < tmpLinelist.length; j++) {

				String[] values = tmpLinelist[j].split("\"");
				String value = "";
				if (values.length >= 4)
					value = values[3];
				else
					value = "";

				String key = "";
				if (values.length >= 2)
					key = values[1];
				else
					key = values[0];
				rltmap.put(key, value);
			}
			maplist.add(rltmap);
		}

		
		 Iterator<Map<String, String>> mapit=maplist.iterator(); int i=0;
		 while(mapit.hasNext()) { Map<String, String>
		  tmpmp=((Map<String,String>)mapit.next());
		  System.out.println((i++)+":"+tmpmp.get("summary").trim()); }
		 
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cmspost cms = new Cmspost();
		cms.setPara_value("000001,000002");
		cms.getContent();
		cms.orgniseRlt();

	}

}
