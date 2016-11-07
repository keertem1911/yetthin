package com.yetthin.web.serviceImp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.httpclient.methods.PutMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yetthin.web.commit.JtdoaValueMarket;
import com.yetthin.web.commit.ValueFormatUtil;
import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.dao.JtdoaDao;
import com.yetthin.web.domain.barData;
import com.yetthin.web.service.JtdoaService;

import util.BarData;
import util.TickSort;

@Service("JtdoaService")
public class JtdoaServiceImp implements JtdoaValueMarket,ValueFormatUtil,JtdoaService{
	 private SimpleDateFormat ALL_format =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		
	/**
	 * 指数通用模板
	 * @return
	 * "index": [
		        {
		            "name": "上证指数", 
		            "price": "价格", 
		            "stockID": "股票代码",
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }, 
		        {
		            "name": "深证成指", 
		            "price": "价格",
		            "stockID": "股票代码", 
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }
		    ], 
	 
	 * 指数格式为 [{指数代码,指数名称,当前价格,点数,涨跌率},{},{}]
	 * 			  0      1      2    3    4
	 * @param index
	 * @return
	 */
	private String putIndex(String [] index,boolean master){
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		if(index.length!=0&&index!=null){
		for (int i = 0; i < index.length; i++) {
			String [] subStr=index[i].split(JTDOA_SPLIT_STR);
			sb.append("{\"name\":\"" +subStr[1]+"\","+ 
		            "\"price\":\""+subStr[2]+"\","+
		            "\"stockID\":\""+subStr[0]+"\",");
			boolean plus=true;
			 
			if(subStr[4].indexOf("-")!=-1){
				plus=false;
				subStr[4]=subStr[4].substring(1);
			}
			
			sb.append("\"increase\":\""+plus+"\","+
		            "\"growth\":\""+subStr[3]+"\","+ 
		            "\"growthRate\": \""+subStr[4]+"\"");
			if(master){
				 sb.append(",\"stockAmplitupe\":\""+subStr[5]+"\"");
				 sb.append(",\"totleMarketValue\":\""+subStr[6]+"\"");
				 sb.append(",\"totleNetWorth\":\""+subStr[7]+"\"");
				 

			}
			sb.append("}");
			if(i<index.length-1)
				sb.append(",");
		}
		}// if nullopint
		sb.append("]");
		
		return sb.toString();
	}
	/**
	 * 股票市场 l1 模板
	 *  [
		        {
		            "group": "组名", 
		            "index": [
		                {
		                    "name": "名字", 
		                    "stockID": "股票代码", 
		                    "increase": "ture|flase", 
		                    "price": "价格", 
		                    "value": "增长率"
		                }
		                
		            ]
		        }, 
		        {
		            "group": "组名", 
		            "index": [
		                {
		                    "name": "名字", 
		                    "stockID": "股票代码", 
		                    "increase": "ture|flase", 
		                    "price": "价格", 
		                    "value": "增长率"
		                }
		                 
		            ]
		        },
		         
		    ]
	 * @param params 
	 * @return
	 * @param Map<String,List<String>> market 
	 * @param master 是否详细显示 true
	 * 		格式
	 * 	"index": [
		                {
		                    "name": "名字", 
		                    "stockID": "股票代码", 
		                    "increase": "ture|flase", 
		                    "price": "价格", 
		                    "updown":"涨跌值",
		                    "value": "增长率",
		                    
		                }
		                 
		            ]
	 * 			 	组名    有序set 
	 * 			0		1	 2	  3			
	 * List  601595.SH:N上影:14.67:43.96
	 * 			  股票代码:名称:  价格:涨跌幅率
	 */
	private String putStockMarketData(Map<String,List<String>> market,boolean master){
		String newStr=null;
		StringBuffer sb=new StringBuffer();
		if(!market.isEmpty()){
		sb.append("[");
		Set<Entry<String, List<String>>> entrySet=market.entrySet(); 
		for (Entry<String, List<String>> entry2 : entrySet) {
			StringBuffer subSb=new StringBuffer();
			subSb.append("{\"group\":\""+NAME_MARKET[Integer.parseInt(entry2.getKey().split(JTDOA_SPLIT_STR)[1])]+"\",");
			subSb.append("\"marketId\":\""+entry2.getKey()+"\",");
			subSb.append("\"totleSum\":\""+jtdoaDao.getLevel1MarketNum(entry2.getKey())+"\",");
			subSb.append("\"index\":[");
			List<String> tu1=entry2.getValue();
			
			//属性  下标分类 0涨幅 1跌幅 2换手率 
			int marketValue=Integer.parseInt(entry2.getKey().split(":")[1]);
			 
			for (String tuple : tu1) {
				String []values = tuple.split(SPLIT_STR);//  股票代码:名称:价格:涨跌幅率
				 
				subSb.append(  "{\"name\":\""+values[NAME]+"\","+ 
		                    "\"stockID\":\" "+values[values.length-1]+"\",");
				boolean plus =true;
			 
					if(values[UP_DOWN_PRICE_RATE].indexOf("-")!=-1){
						plus=false;
						values[UP_DOWN_PRICE_RATE]=values[UP_DOWN_PRICE_RATE].substring(1);
					}
				subSb.append("\"increase\":\""+plus+"\","+ 
		                    "\"updown\": \""+values[UP_DOWN_PRICE]+"\","+
		                    "\"price\": \""+values[LAST_PRICE_INDEX]+"\","+
		                    "\"rate\": \""+values[UP_DOWN_PRICE_RATE]+"\","+
		                    "\"exchange\":\""+values[EXCHANGE_RATE]+"\"");
				if(master){
					 
					String lastDone=null;
//					System.out.println(values[LAST_DONE]);
					if(values[LAST_DONE]!=null&&!"".equals(values[LAST_DONE])){
					  String []subStr2= values[LAST_DONE].split("[/]");
//					System.out.println(Arrays.asList(subStr2));
					  if(subStr2.length>2)
					lastDone=subStr2[2];
					  else
						  lastDone="0";
					}else 
						lastDone="0";
					 subSb.append(",\"lastDone\":\""+lastDone+"\"");
					 subSb.append(",\"priceEaring\":\""+values[PRICE_EARING_RATIO]+"\"");
					 subSb.append(",\"stockAmplitupe\":\""+values[STOCK_AMPLITUPE]+"\"");
					 subSb.append(",\"famc\":\""+values[FAMC]+"\"");
					 subSb.append(",\"totleMarketValue\":\""+values[TOTLE_MAREKT_VALUE]+"\"");
					 subSb.append(",\"totleNetWorth\":\""+values[TOTLE_NET_WORTH]+"\"");
				}
				subSb.append("},");
			}
			
			String str=subSb.toString();
			str=str.substring(0,str.lastIndexOf(","))+"]},";
			sb.append(str);
			
		}
		
		
		newStr= sb.toString();
		 
		newStr=newStr.substring(0,newStr.lastIndexOf(","))+"]";
//		str=str.substring(0,str.lastIndexOf(","))+" ";
		
		return newStr;
		}
		return sb.toString();
	}
	/**
	 * [
		        {
		            "group": "涨幅榜",
		            "groupId":"0", 
		            "index": [
		                {
		                    "name": "名字", 
		                    "stockID": "股票代码", 
		                    "increase": "ture|flase", 
		                    "price": "价格", 
		                    "value": "增长率"
		                }
		            ]
		        }, 
		        {
		            "group": "组名", 
		            "index": [
		                {
		                    "name": "名字", 
		                    "stockID": "股票代码", 
		                    "increase": "ture|flase", 
		                    "price": "价格", 
		                    "value": "增长率"
		                },
		            ]
		        },
		    ]
	 */
	@Resource
	private JtdoaDao jtdoaDao;
	
	@Resource
	private JtdoaAPIDao jtdoaAPIDao;
	
	
	/**
	 *  获取l1值 包括 股指 股票涨跌分类等
	 * @param id 
	 * @param begin 开始下标0开始
	 * @param end 结束下标
	 * @param market 市场代码
	 * @param indexMarket 是否请求指数
	 * @param master  是否请求详情数据
	 * @return 字符数组  1 状态码 2 指数的JSON 3 股票的分类排行L1 4 错误信息 
	 */
	@Override
	@Transactional
	public String []  getL1(int id,long begin,long end,String market,
			boolean indexMarket,boolean master){
		if(market.equals("null")) market="0:0,1,2";
		System.out.println("mat " +market);
		String [] subMarket=market.split("["+SPLIT_STR+"]");
		
		String [] subStr=new String [4];
		if(subMarket[0].equals("null")||subMarket[0]==null) 
			subMarket[0]="0";
		
		if(!subMarket[0].equals("null")){
		int marketIndex=Integer.parseInt(subMarket[0]);
		// level1 属性 涨跌幅及换手率等
		String [] params= subMarket[1].split("[,]");
		if(begin<end&&begin>=0&&end>0){
		switch(id){
		case HU_SHEN: 
			if(indexMarket){
			String [] index= jtdoaDao.getStockIndex(marketIndex,0,5,false);
			if(index!=null&&index.length!=0)
			subStr[1]=putIndex(index,false);// 指数解析
			
			}else{
				subStr[1]="{}";
			}
		 	Map<String,List<String>> marketS =jtdoaDao.getL1StockMarketData(HU_SHEN,begin,end,params,master);
			subStr[2]=putStockMarketData(marketS,master);
			if((subStr[1].equals("")||subStr[1]==null)&&(subStr[2].equals("")||subStr[2]==null)){
				subStr[0]="520";//
				subStr[3]="level1 data 查询失败";
			}else{
				subStr[0]="200";
				subStr[3]=" ";
			}
			break;
		}
		}else{
			subStr[0]="502";
			subStr[1]="[]";
			subStr[2]="输入错误";
		}
		}else{
			subStr[0]="502";
			subStr[1]="[]";
			subStr[2]="输入错误";
		}
		return subStr;
	}
	 
	/**
	 * @param 	String symbol 股票代码 000001.SH
	 * @return  字符数组  0 状态码 1 股票对应的level2的JSON 2 错误信息
	 */
	@Override
	@Transactional 
	public String[] getL2(String symbol) {
		String [] subStr=new String [3];
		// TODO Auto-generated method stub
			String level2= jtdoaDao.getL2(symbol);
			if(level2==null||"".equals(level2.trim())){
				subStr[0]="520";
				subStr[2]="level2 代码为空";
			}else{
				String [] subValue=level2.split(SPLIT_STR);
				subStr[1]=putLevel2Depth(subValue,symbol);
				subStr[0]="200";
				subStr[2]="";
				
			}
		return subStr;
	}
	/**
	 * level2模板格式 
	 * { 	"level1":{"last":"11.2","open":"12.1",
	 * 			"updown":"12.1","rate":"1.1%","increate":"false",
	 * 			"low":"12.11","height":"13.11","exchange":"1.1%"
	 * 			"volum":"11112","totlesum":"121122(万)"},
	 * 		"level2":{"buy5":["1.1","200"],"buy4":["2.1","500"]}
	 * }
	 * 
	 * 将level2 的字符串转换为 json  的 item格式
	 * @param subValue
	 * @return
	 */
	private String putLevel2Depth(String[] subValue,String symbol) {
		// TODO Auto-generated method stub
		StringBuffer sb1=new StringBuffer();
		sb1.append("\"level1\":{");
		sb1.append("\"open\":\""+subValue[OPEN_INDEX]+"\",");
		sb1.append("\"last\":\""+subValue[LAST_PRICE_INDEX]+"\",");
		sb1.append("\"updown\":\""+subValue[UP_DOWN_PRICE]+"\",");
		sb1.append("\"exchange\":\""+subValue[EXCHANGE_RATE]+"\",");
		boolean plus=true;
		String plusPrice=subValue[UP_DOWN_PRICE_RATE];
		if(subValue[UP_DOWN_PRICE_RATE].indexOf("-")!=-1){
			plus=false;
			plusPrice=plusPrice.substring(1);
		}
		sb1.append("\"rate\":\""+plusPrice+"\",");
		sb1.append("\"increate\":\""+plus+"\",");
		
		sb1.append("\"low\":\""+subValue[LOW_INDEX]+"\",");
		sb1.append("\"height\":\""+subValue[HEIGHT_INDEX]+"\",");
		sb1.append("\"totlesum\":\""+subValue[TOTLE_SUM_INDEX]+"\",");
		sb1.append("\"volume\":\""+subValue[VOLUME_INDEX]+"\"");
		sb1.append("}");
		/**
		 * "level2":{"buy5":["1.1","200"],"buy4":["2.1","500"]}
		 */
		StringBuffer sb=new StringBuffer();
		
		sb.append("{");
		for (int i = 0; i < 5; i++) {
			sb.append("\"sell"+(5-i)+"\":");
			sb.append("[");
			sb.append("\""+subValue[LEVEL2_INDEX_SIDE1+i]+"\",");
			sb.append("\""+subValue[LEVEL2_INDEX_SIDE1+10+i]+"\"");
			sb.append("],");
		}
 
		for (int i = 1; i < 6; i++) {
			sb.append("\"buy"+i+"\":");
			sb.append("[");
			sb.append("\""+subValue[LEVEL2_INDEX_SIDE0+i-1]+"\",");
			sb.append("\""+subValue[LEVEL2_INDEX_SIDE0+10+i-1]+"\"");
			sb.append("],");
		}
		sb.append("}");
		String value=sb.toString();
		value = value.substring(0,value.lastIndexOf(","))+"}";
		value="{\"symbol\":\""+symbol+"\",\"name\":\""+subValue[NAME]+"\","+sb1.toString()+",\"level2\":"+value+"}";
		return value;
	}
 
	@Override
	public String [] getLevel2Detail(String symbol) {
		 
		List<TickSort> list=jtdoaDao.getLevel2Detail(symbol);
		String [] subStr=new String[3];
		String json =putLevel2Detail(list);
		if(json==null||"".equals(json)){
			subStr[0]="520";
			subStr[2]="level2交易记录失败";
			subStr[1]="{}";		
		}else{
			subStr[0]="200";
			subStr[2]="";
			subStr[1]=json;		
			
		}
		return subStr;
	}
	/**
	 * level2 交易详细 模板
	 * {"14:21:00":["4.3","122221"],"14:20:11":["4.2","322212"]}
	 * @param list
	 * @return
	 */
	private String putLevel2Detail(List<TickSort> list) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for (int i = 0; i < list.size(); i++) {
			TickSort tick=list.get(i);
			sb.append("\""+tick.getDateTime()+"\":[");
			sb.append("\""+tick.getTickPrice()+"\",\""+tick.getVolume()+"\"]");
			if(i<list.size()-1)
				sb.append(",");
		}
		
		sb.append("}");
	 	return sb.toString();
	}
	private String putIndexDetail(String []subValue){
		StringBuffer sb1=new StringBuffer();
		sb1.append("{\"level1\":{");
		sb1.append("\"open\":\""+subValue[OPEN_INDEX]+"\",");
		sb1.append("\"last\":\""+subValue[LAST_PRICE_INDEX]+"\",");
		sb1.append("\"updown\":\""+subValue[UP_DOWN_PRICE]+"\",");
		sb1.append("\"exchange\":\""+subValue[EXCHANGE_RATE]+"\",");
		boolean plus=true;
		String plusPrice=subValue[UP_DOWN_PRICE_RATE];
		if(subValue[UP_DOWN_PRICE_RATE].indexOf("-")!=-1){
			plus=false;
			plusPrice=plusPrice.substring(1);
		}
		sb1.append("\"rate\":\""+plusPrice+"\",");
		sb1.append("\"increate\":\""+plus+"\",");
		
		sb1.append("\"low\":\""+subValue[LOW_INDEX]+"\",");
		sb1.append("\"height\":\""+subValue[HEIGHT_INDEX]+"\",");
		sb1.append("\"totlesum\":\""+subValue[TOTLE_SUM_INDEX]+"\",");
		sb1.append("\"volume\":\""+subValue[VOLUME_INDEX]+"\"");
		sb1.append("}}");
		return sb1.toString();
	}
	@Override
	public String[] getIndexDetail(String indexCode) {
		// TODO Auto-generated method stub
		String [] subValue=jtdoaDao.getIndexDetail(indexCode);
		String [] subStr=new String[3];
		subStr[1]=putIndexDetail(subValue);
		if(subStr[1]==null||"".equals(subStr[1].trim())){
			subStr[0]="520";
			subStr[1]="{}";
			subStr[2]="股票指数不存在";
		}else{
			subStr[0]="200";
			subStr[2]="";
		}
		return subStr;
	}
	/**
	 * [{"index0": [
		        {
		            "name": "上证指数", 
		            "price": "价格", 
		            "stockID": "股票代码",
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }, 
		        {
		            "name": "深证成指", 
		            "price": "价格",
		            "stockID": "股票代码", 
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }
		    ],
		"marketCode":"0"},
		{"index0": [
		        {
		            "name": "上证指数", 
		            "price": "价格", 
		            "stockID": "股票代码",
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }, 
		        {
		            "name": "深证成指", 
		            "price": "价格",
		            "stockID": "股票代码", 
		            "increase":"ture|flase",
		            "growth": "增长量", 
		            "growthRate": "增长率"
		        }
		    ],
		"marketCode":"0"}]   
	 */
	@Override
	public String[] getStockIndexList(int begin, int end, String marketCode, boolean master) {
		// TODO Auto-generated method stub
		String [] subStr=new String[3];
		boolean isNull=false;
		subStr[0]="200";
		subStr[2]="";
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		if(marketCode!=null){
			String [] indexes=marketCode.split(SPLIT_STR);
			for (int i = 0; i < indexes.length; i++) {
				sb.append("{\"index"+i+"\":");
				int index= Integer.parseInt(indexes[i]);
					String [] index1= jtdoaDao.getStockIndex(index,begin,end,master);
					String returnValue=putIndex(index1,master);
					sb.append(returnValue);
					switch(index){
					case 0:
						sb.append(",\"marketCode\":\""+marketCode+"\"}");
						break;
					}
					if(i<indexes.length-1)
						sb.append(",");
					if(returnValue==null||"".equals(returnValue))
						isNull=true;
					 
			}
			if(isNull){
				subStr[0]="520";
				subStr[1]="{}";
			}
		}
		sb.append("]");
		subStr[1]=sb.toString();
		return subStr;
	}
	/**
	 * 股票K线数据查询
	 * @param barNum K线最小个数
	 * @param currenyTime   当前时间
	 * @param type     K线周期 数字
	 * @param cycNum   周期基准
	 * 例 :
	 * @param barNum   50 
	 * @param currenyTime   2016-08-22 11:30:11
	 * @param type     0   
	 * @param cycNum   3 
	 * 注释: type 值取值 -> (0:SECOND,1:MINUTE,2:DAY,3:WEEK,4:MONTH,5:SEASON,6:HAFLYEAR,7:YEAR) 
	 * 请求  2016-08-22 11:30:11之前 3秒为单位 的50 条 K线数据
	 * 
	 * @return  返回格式 
	 * 		[
	 * 		 {"open":"1.2","close":"21.2","height":"2.1","low":"1.2","time":"12221233221"},
	 * 		 {"open":"1.2","close":"21.2","height":"2.1","low":"1.2","time":"12221233221"},
	 * 		 {"open":"1.2","close":"21.2","height":"2.1","low":"1.2","time":"12221233221"},
	 * 		 {"open":"1.2","close":"21.2","height":"2.1","low":"1.2","time":"12221233221"}
	 * 		]
	 */
	@Override
	public String[] getHistoryBar(String symbol,String barNum, String currenyTime, int type,int cycNum) {
		// TODO Auto-generated method stub
		String [] subStr= new String[3];
		 SimpleDateFormat ALL_format =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		 String month=currenyTime.split("[\\s]")[0];
		 try {
			long middleEnd = ALL_format.parse(month+" 11:00:00").getTime();
			 long middleBegin = ALL_format.parse(month+" 13:00:00").getTime();
			 long openTime = ALL_format.parse(month+" 09:00:00").getTime();
			 long closeTime = ALL_format.parse(month+" 15:15:00").getTime();
			 long currenyLong= ALL_format.parse(currenyTime).getTime();
			 if(currenyLong>middleEnd&&currenyLong<middleBegin)
				 currenyTime=ALL_format.format(new Date(middleEnd));
			 else if(currenyLong>closeTime)
				 currenyTime=ALL_format.format(new Date(closeTime));
			 else if(currenyLong<openTime)
				 currenyTime=ALL_format.format(new Date(closeTime-(1000*60*60*24)));
//				System.out.println(currenyTime); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<BarData> list = jtdoaDao.getHistoryBar(symbol,barNum,currenyTime,type,cycNum);
		subStr[1]=putHistoryBar(list);
		if(subStr[1]==null||"".equals(subStr[1].trim())){
			subStr[0]="520";
			subStr[2]="K线请求失败";
			subStr[1]="{}";
		}else{
			subStr[0]="200";
			subStr[2]="";
		}
		return subStr;
	}
	/**
	 * K线  模板
	 * {"时间":["最低","最高","开盘","收盘","成交量"],"时间":["最低","最高","开盘","收盘","成交量"]}
	 * @param list
	 * @return
	 */
	private String putHistoryBar(List<BarData> list) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer();
		sb.append("{");
		for (int i = 0; i < list.size(); i++) {
			BarData tick=list.get(i);
			sb.append("\""+ALL_format.format(tick.dateTime*1000)+"\":[");
			sb.append("\""+Double.toString(tick.low)+"\",\""+Double.toString(tick.high)+"\","
					+ "\""+Double.toString(tick.open)+"\",\""+Double.toString(tick.close)+"\","
							+ "\""+Double.toString(tick.volume)+"\"]");
			if(i<list.size()-1)
				sb.append(",");
		}
		
		sb.append("}");
	 	return sb.toString();
	}
	@Override
	public String[] getLevel1MarketNum(String marketCode) {
		// TODO Auto-generated method stub
		String [] subStr= new String[3];
		subStr[0]="200";
		subStr[2]="";
		subStr[1]=jtdoaDao.getLevel1MarketNum(marketCode);
		if(subStr[1]==null||"".equals(subStr[1].trim())){
			subStr[0]="520";
			subStr[2]="查询错误";
			subStr[1]="\"0\"";
		}
		subStr[1]="{\"marketCode\":\""+marketCode+"\",\"num\":\""+subStr[1]+"\"}";
		return subStr;
	}
	/**
	 * 获取行业指数集合
	 * @return  0 stateCode 1 item 2 msg
	 */
	@Override
	public String[] getStockIndustry(String beginIndex, String endIndex,String time,String size) {
		// TODO Auto-generated method stub
		List<barData> barList=jtdoaDao.getStockIndex(beginIndex,endIndex,time,size);
		
		System.out.println(Arrays.asList(barList));
		return null;
	}
	/**
	 * K线 json 格式转换
	 * {data:[{date:'',open:'',low:'',height:'',close:''},
	 * 		{date:'',open:'',low:'',height:'',close:''}
	 * ]}
	 */
	private String KMdata2String(List<barData> list){
		StringBuffer sb=new StringBuffer();
		sb.append("{\"data\":[");
		for (int i = 0; i < list.size(); i++) {
			barData bar=list.get(i);
			sb.append("{");
			sb.append("\"date\":\""+bar.getDatetime().toString()+"\",");
			sb.append("\"open\":\""+bar.getOpen()+"\",");
			sb.append("\"low\":\""+bar.getLow()+"\",");
			sb.append("\"height\":\""+bar.getHeight()+"\",");
			sb.append("\"close\":\""+bar.getClose()+"\"");
			sb.append("}");
			if(i<list.size()-1)
				sb.append(",");
		}
		sb.append("]}");
		
		return sb.toString(); 
	}
	@Override
	public String[] getStockIndustryDK(String id, String time, String size) {
		// TODO Auto-generated method stub
		Map<String,Object> map= new HashMap<>();
		map.put("id", Integer.parseInt(id));
		map.put("time", time);
		map.put("size",Integer.parseInt(size));
		List<barData> list =jtdoaDao.getStockIndustryDK(map);
		System.out.println(Arrays.asList(list));
		String str=KMdata2String(list);
		String [] s=new String[3];
		if(str!=null&&!"".equals(str.trim())){
			s[2]=str;
			s[0]="200";
			s[1]="\"\"";
		}else{
			s[2]="\"\"";
			s[0]="420";
			s[1]="日K线错误";
			
		}
		return s;
	}
}


