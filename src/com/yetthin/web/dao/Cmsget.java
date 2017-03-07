package com.yetthin.web.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import net.oauth.OAuth;
import net.p5w.gdsp.client.base.GdspClient;
import net.p5w.gdsp.client.base.GdspException;

public class Cmsget {

	static GdspClient client;

	String servIp="";
	String oauIp="";
	String s_date = "";// 开始时间 YYYY-MM-DD HH:mm:ss,支持按周 -7，按月 -30，按季 -90
	String cate = "";// 新闻情感 all,neg
	String para_name = "";// org(参数)
	String fcount = "";// 1
	String status = "";// cpp 处理的新闻
	String sort = "";// 排序
						// response|summary|publishdate|sourceinfo|storetime|reference|samecount|title:increasing|decreasing
	String para_value = "";// 股票代码列表 “XXXXXX,XXXXXX,XXXXXX"

	String strReturn = "";

	List<Map<String, String>> maplist;

	String currentPageNo = "";

	String pageSize = "";

	public Cmsget() {

		servIp="1.85.59.245";
		oauIp="1.85.59.246";
				
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式
		Date now = new Date();
		s_date = df.format(new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000));// 默认七天前

		cate = "all";// 默认所有

		para_name = "org";

		fcount = "1";

		status = "cpp";// (发布的)

		sort = "publishdate:decreasing";

		para_value = "000001";

		currentPageNo = "1";

		pageSize = "5";	

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

	public String getPara_name() {
		return para_name;
	}

	public void setPara_name(String para_name) {
		this.para_name = para_name;
	}

	public String getFcount() {
		return fcount;
	}

	public void setFcount(String fcount) {
		this.fcount = fcount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getCurrentPageNo() {
		return currentPageNo;
	}

	public void setCurrentPageNo(String currentPageNo) {
		this.currentPageNo = currentPageNo;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getServIp() {
		return servIp;
	}

	public void setServIp(String servIp) {
		this.servIp = servIp;
	}

	public String getOauIp() {
		return oauIp;
	}

	public void setOauIp(String oauIp) {
		this.oauIp = oauIp;
	}

	public void getContent() {
		try {
			// 第一个参数是App名,第二个参数是App密码,第三个参数是用户名,第四个参数是用户的密码,
			client = new GdspClient("egoandroid", "nkwMjrCq7F272w", "", "");
			GdspClient.setGdspBaseURL("http://"+servIp+"/gdsp/");// 1、新闻接口（GET方式）
			GdspClient.setOauthBaseURL("http://"+oauIp+"/gdsp-oauth/");	
			// client.access();//这是个OAUTH认证,可以手动调用,默认访问第一个资源的时候会调用.
			// 第一个参数是资源的URI,第二个参数是参数,如参数q=[a:1;b:2]参数o=[time:desc]则写成OAuth.newList("q","[a:1;b:2]","o","[time:desc]")
			String s = client.invoke(GdspClient.getGdspBaseURL() + "v1/opinion/cms", OAuth.newList("q",
					"[para_name:" + this.para_name + ";para_value:" + this.para_value + ";cate:" + this.cate
							+ ";s_date:" + this.s_date + ";fcount:" + this.fcount + ";status:" + this.status + ";]",
					"p", "[currentPageNo:" + this.currentPageNo + ";pageSize:" + this.pageSize + "]", "o",
					"[" + this.sort + "]"));
			this.strReturn = s;
		} catch (GdspException e) {
			e.printStackTrace();
			
		}
	}

	public void orgniseRlt() {
		
		maplist.clear();
		
		String newstr = strReturn.replace("},{", "\n}\n==============================================\n{\n");
		String[] mainStr = newstr.split("\"items\":\\[");

		String substr = mainStr[1];

		substr = substr.replace("\",\"", "\"\n\"");

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

		
		/* Iterator<Map<String, String>> mapit=maplist.iterator(); int i=0;
		 while(mapit.hasNext()) { Map<String, String>
		 tmpmp=((Map<String,String>)mapit.next());
		 System.out.println((i++)+":"+tmpmp.get("title")+"|"+tmpmp.get("id")+"|"+tmpmp.get("summary")
		 .trim()); }*/
		 

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Cmsget cms = new Cmsget();
		cms.setPageSize("10");
		cms.setPara_value("000001,000002");
		cms.getContent();
		cms.orgniseRlt();
		cms.setCurrentPageNo("2");
		cms.getContent();
		cms.orgniseRlt();
		System.out.println(JSON.toJSON(cms.maplist));
		System.out.println();
		for (int i = 0; i < cms.maplist.size(); i++) {
			System.out.println(cms.maplist.get(i));
		}
	}

}
