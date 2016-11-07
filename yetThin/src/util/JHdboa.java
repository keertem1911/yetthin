package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import com.yetthin.web.commit.RedisUtil;
import com.yetthin.web.commit.ValueFormatUtil;

import redis.clients.jedis.JedisPool;

public class JHdboa implements ValueFormatUtil{
	
	private static JedisPool poolM=RedisUtil.getInstanceMsater();
	
	private List<TickSort> tickSorts=null;
	
	private List<BarData> barDatas=null;
	
	public boolean isset=false;
	
	SimpleDateFormat mm_ddformat =new SimpleDateFormat("HH:mm:ss");
	public boolean connected=false;
	public JHdboa() {
		// TODO Auto-generated constructor stub
	}
	public native void HdboaInit(BarData bar,Contract contract,TickData tick);
	
	public native int HdboaConnect(String addr,int port);
	public native void HdboaDestory();
	public native void HdboaDisconnect();
	public native int HdboaReqHistoricalData(long tickId,Contract contract,long beginDatetime,long endDatetime,int cycType,int ncyctype,int userth);
	public native int HdboaReqHistoricalTickData(long tickId,Contract contract,long beginTime,long endTime,int useRth);
    static {
        System.loadLibrary("JHdboa");//
    }
    
    public void error(long tickId,long code,String message)
    {
    	System.out.println(tickId+" "+code+" "+message);
    }
    public static String getWeekOfDate(long time) {
    	Date date=new Date(time);
    	SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd hh-mm-ss");
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return simpleDateFormat.format(date)+"-"+weekDays[w];
    }
    private  String joinStringSplit(String [] array,String sp){
  		StringBuffer buffer=new StringBuffer();
  		for (int i = 0; i < array.length; i++) {
  			
  			buffer.append(array[i]);
  			if(i!=array.length-1)
  				buffer.append(sp);
  				
  		}
  		 
  		return buffer.toString();
  	}
    public void connected()
    {
    	System.out.println("connected");
    	connected=true;
    }
    public boolean end =false;
    public void disconnected()
    {
    	System.out.println(Thread.currentThread().getName());
    	System.out.println("disconnected");
    	 Thread.currentThread().interrupt();
    	 end=true;
    	 System.out.println("aaa");
    }
    public void UpdateHistoricalBarData(long tickId,BarData bar,int sendFlag)
    {
    	  
    	 if(barDatas!=null){	 
    	 barDatas.add(bar);
    	 isset=true;
    	 }
    	 
    }
    public List<TickSort> getTickSorts() {
    	 List<TickSort> list1= new LinkedList<TickSort>();
    	 isset=false;
    	 tickSorts=list1;
    	return list1;
	}
    
    public List<BarData> getBarDatas() {
		List<BarData> barDatas=new LinkedList<BarData>();
		isset=false;
		this.barDatas =barDatas;
    	return barDatas;
	}
    public void setTickSorts(List<TickSort> tickSorts) {
		this.tickSorts = tickSorts;
	}
    public void setBarDatas(List<BarData> barDatas) {
		this.barDatas = barDatas;
	}
    
    public void UpdateHistoricalTick(long tickId,TickData tick,int sendFlag)
    {
    	if(tickSorts!=null){
    	isset=true;
    	String date=mm_ddformat.format(tick.dateTime*1000);
    	TickSort tickSort = new TickSort(tick, date);
    	tickSorts.add(tickSort);
    	}
    }
    public boolean isIsset() {
    	System.out.println(isset);
		return isset;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		  SimpleDateFormat yy_MM_ddformat =new SimpleDateFormat("yyyy:MM:dd");
		 SimpleDateFormat ALL_format =new SimpleDateFormat("yyyy:MM:dd HH:mm:ss");
		 List<TickSort> list=null;
		 Contract contract=new Contract();
		 contract.symbol="000022";
		 contract.currency="CNY";
		 contract.exchange="SZ";
		 contract.secType="STK";
		 long cntTime=0l;
		 
//		 try {
//			 long current_Time=System.currentTimeMillis();
//		 String dateStr=yy_MM_ddformat.format(current_Time);
//		 Date endTime =ALL_format.parse(dateStr+" 15:01:00");
//		 Date beginTime =ALL_format.parse(dateStr+" 09:30:00");
//		 long fromTime=0l;
//		 long toTime=0l;
//		 // 起始时间与终止时间之差 10分钟
//		 long index_time=1000*60*30;
//		 // 第二天请求昨天的
//		 long yestday=1000*60*60*24;
//		 do{
//			 jhd.setTickSorts(null);
//				 if(endTime.getTime()<current_Time){// 超过 收盘时间
//					 fromTime=endTime.getTime()-index_time-cntTime;
//					 toTime=endTime.getTime();
//				 }else{
//					 if(beginTime.getTime()>current_Time){// 先于  开盘时间
//						 fromTime=endTime.getTime()-index_time-cntTime-yestday;
//						 toTime=endTime.getTime()-yestday;
//					 }else{
//						 toTime=current_Time;
//						 fromTime=current_Time-index_time-cntTime;
//					 }
//				 }
//				 
//				 list=jhd.getTickSorts();
//				 System.out.println(Thread.currentThread().getName());
//				 System.out.println("from "+new Date(System.currentTimeMillis()-1000*60*60*20)+"  to "+new Date(toTime));
////				 jhd.HdboaReqHistoricalTickData(3, contract, fromTime/1000, toTime/1000,UseRTH.USE_RTH.ordinal());
//				 jhd.HdboaReqHistoricalData(8002, contract,(System.currentTimeMillis()-1000*60*60*20)/1000, (System.currentTimeMillis())/1000,CYCTYPE.CYC_DAY.ordinal(), 1, UseRTH.USE_RTH.ordinal());
//				 int cnt=0;
//				 while(!jhd.isset){
//					Thread.sleep(100);
//					cnt++;
//					if(cnt==50)
//						break;
////				 	System.out.println(jhd.isset);
//				}
//					 Thread.sleep(1000);
//					 System.out.println(list.size());
//					 cntTime+=1000*60;
//		 }while(false);
//		 if(jhd.isset==false){
//			 System.out.println("time errir");
//		 }
//		 } catch (Exception e) {
//			 // TODO Auto-generated catch block
//			 e.printStackTrace();
//		 } 
		 
		 String [] params={"10","2016:08:22 11:00:00","0","2"};
//		 [barNum,currenyTime,cycType,cycNum]
		 if(params.length>3){
			 int barNum=Integer.parseInt(params[0]);
			 String currenyTime=params[1];
			 int  cycType=Integer.parseInt(params[2]);
			 int cycNum=Integer.parseInt(params[3]);
			 Date date = null;
			 long longDate=0l;
			try {
				date = ALL_format.parse(currenyTime);
				 longDate=date.getTime();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			String []day_hourse=currenyTime.split("[\\s]");
			String day=day_hourse[0];
			String hourse=day_hourse[1];
			long beginTime=0L;
			int [][] SEASONS={{01,01,03,31},{04,01,06,30},{07,01,9,30},{10,01,12,31}};
			int [][] HalfYears ={{6,30},{12,31}};
			Calendar cal =Calendar.getInstance();
			 int yeartemp=Integer.parseInt(day.split("[:]")[0]);
			 int monthtemp=Integer.parseInt(day.split("[:]")[1]);
			 int daytemp=Integer.parseInt(day.split("[:]")[2]);
			cal.setTime(date);
		//	(0:SECOND,1:MINUTE,2:DAY,3:WEEK,4:MONTH,5:SEASON,6:HAFLYEAR,7:YEAR) 
			switch(cycType){
			case 0: cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)-(barNum*cycNum+1)); // second
				break;
			case 1: // mintue
				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-(barNum*cycNum+1));
				break;
			case 2: //cntTime=1000*60*60*24;// day 
					//currenyTime= currenyTime.split("[\\s]")[0]+"  15:30:00";

				 cal.set(Calendar.HOUR, 15);
				 cal.set(Calendar.MINUTE, 15);
				 cal.set(Calendar.SECOND, 15);
				 longDate=cal.getTimeInMillis();
				 cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-(barNum*cycNum+1));
				 
				break;
			case 3: //cntTime=1000*60*60*24*7;//weekend
				cal.set(Calendar.WEEK_OF_MONTH,cal.get(Calendar.WEEK_OF_MONTH) -(barNum*cycNum+1));
				break; 
			case 4: // month
					for (int i = 0; i < barNum*cycNum; i++) {
						
						int month = cal.get(Calendar.MONTH);
						if(month<0){
							int year =cal.get(Calendar.YEAR);
							year--;
							cal.set(Calendar.YEAR, year);
							cal.set(Calendar.MONTH, 12);
						}else{
							cal.set(Calendar.MONDAY, month-1);
						}
					}
					
				break;
			case 5: //season
			{
				int cnt = SEASONS.length-1;
				 
				 
			
				 cal.set(Calendar.HOUR, 15);
				 cal.set(Calendar.MINUTE, 15);
				 cal.set(Calendar.SECOND, 15);
				 int index=0;
				 for(int i=0;i<SEASONS.length;++i){
					 if(monthtemp>=SEASONS[i][0]&&monthtemp<=SEASONS[i][2]){
						 index=i;
						 break;
					 }
				 }
				 cal.set(Calendar.MONDAY, SEASONS[index][2]-1);
				 cal.set(Calendar.DAY_OF_MONTH, SEASONS[index][3]);
				 longDate=cal.getTimeInMillis();
				 int endcnt=barNum-1+(cnt-index);
				 int yearcnt=endcnt/SEASONS.length;
				 int indexcnt=cnt-(endcnt%SEASONS.length);
				 index= indexcnt;
				 cal.set(Calendar.YEAR, yeartemp-yearcnt);
				 cal.set(Calendar.MONDAY, SEASONS[index][0]-1);
				 cal.set(Calendar.DAY_OF_MONTH, SEASONS[index][1]);
				 cal.set(Calendar.HOUR, 9);
				 cal.set(Calendar.MINUTE, 00);
				 cal.set(Calendar.SECOND, 00);
				 }
				break;
			case 6://halfyear
				{
					int [] halfYear=HalfYears[cycNum];
				int index=0;
				cal.set(Calendar.HOUR, 15);
				 cal.set(Calendar.MINUTE, 15);
				 cal.set(Calendar.SECOND, 15);
				for(int i=0;i<HalfYears.length;++i){
					 if(monthtemp<=HalfYears[i][0]&&daytemp<=HalfYears[i][1]){
						 index=i;
						 break;
					 }
				 }
				cal.set(Calendar.MONTH, HalfYears[index][0]-1);
				cal.set(Calendar.DAY_OF_MONTH, HalfYears[index][1]);
				longDate=cal.getTimeInMillis();
				int cnt=HalfYears.length-1;
				int endcnt=barNum-1+(cnt-index);
				 int yearcnt=endcnt/HalfYears.length;
				 int indexcnt=cnt-(endcnt%HalfYears.length);
				 index=indexcnt;
				 cal.set(Calendar.YEAR, yeartemp-yearcnt);
				 cal.set(Calendar.MONDAY, HalfYears[index][0]-1);
				 cal.set(Calendar.DAY_OF_MONTH, HalfYears[index][1]);
				 cal.set(Calendar.HOUR, 9);
				 cal.set(Calendar.MINUTE, 00);
				 cal.set(Calendar.SECOND, 00);
				}
				break;
			case 7://year
				 cal.set(Calendar.MONDAY, 11);
				 cal.set(Calendar.DAY_OF_MONTH, 31);
				 cal.set(Calendar.HOUR, 16);
				 cal.set(Calendar.MINUTE, 00);
				 cal.set(Calendar.SECOND, 00);
				 longDate=cal.getTimeInMillis();
				 
				int year=cal.get(Calendar.YEAR);
				cal.set(Calendar.YEAR, year-(barNum*cycNum));
				break;	
			}
			 
			beginTime =cal.getTimeInMillis();
			 List<BarData> barDatas=null;
			 do{
			 jhd.setBarDatas(null);
			 barDatas=null;
			 barDatas=jhd.getBarDatas();
			 System.out.println(ALL_format.format(new Date(beginTime))+" : "+ALL_format.format(new Date(longDate)));
			 int j =jhd.HdboaReqHistoricalData(12,contract,
					 beginTime/1000,longDate/1000,cycType,cycNum,UseRTH.USE_RTH.ordinal());
			 if(j==1){
//				 beginTime-=cntTime1;
				 try {
				 while(!jhd.isIsset()){
					 
						Thread.sleep(50);
				
				 }// while end
				 		Thread.sleep(2000);
				 		
				 } catch (InterruptedException e) {
					 // TODO Auto-generated catch block
					 e.printStackTrace();
				 }
			 }//if end 
				 	 
//				 		jhd.HdboaDisconnect();
				 		if(barDatas.size()<barNum){
				 			switch(cycType){
				 			case 0: 
				 				cal.set(Calendar.SECOND, cal.get(Calendar.SECOND)-((barNum*cycNum)/2));
				 				break;
				 			case 1:
				 				cal.set(Calendar.MINUTE, cal.get(Calendar.MINUTE)-((barNum*cycNum)/2));
				 				break;
				 			case 2:
				 			 cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-(((barNum*cycNum)/7)*2));
				 			 break;
				 			}
				 			beginTime=cal.getTimeInMillis();
				 			 
				 		}
				 		System.out.println("size ="+barDatas.size());
				 }while(barDatas.size()<barNum);
			 Collections.sort(barDatas);
			 System.out.println(Arrays.asList(barDatas));
			 }// params.length ==3 
			 
		 System.out.println("ceoom i   =---------------------------");
		 
			jhd.setTickSorts(null);
			jhd.HdboaDisconnect();
			jhd.HdboaDestory();
		 
		System.out.println("sa");
		jhd=null;
		 
	}
	private static String getDateStr(long millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		Formatter ft=new Formatter(Locale.CHINA);
		return ft.format("%1$tY年%1$tm月%1$td日%1$tA，%1$tT %1$tp", cal).toString();
		}

}
