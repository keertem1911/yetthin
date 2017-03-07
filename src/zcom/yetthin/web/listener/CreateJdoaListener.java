package zcom.yetthin.web.listener;

import java.io.IOException;
import java.net.URL;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yetthin.web.commit.JtdoaValueMarket;
import com.yetthin.web.commit.QQMarketLevelUtilByMaster;
import com.yetthin.web.commit.QQMarketLevelUtilBySimple;
import com.yetthin.web.commit.RedisOfReader;
import com.yetthin.web.commit.SinaMarketIndex;
import com.yetthin.web.dao.JtdoaAPIDao;
import com.yetthin.web.dao.UrlRequestDao;
import com.yetthin.web.test.ReadTextSymbol;

import util.Contract;


public class CreateJdoaListener implements ServletContextListener,
QQMarketLevelUtilBySimple,QQMarketLevelUtilByMaster,
SinaMarketIndex,JtdoaValueMarket{
	
	private JtdoaAPIDao jtdoaAPIDao=new JtdoaAPIDao();
	
	
	private static final int dev_num=20;
	private  String FILE_NAME_PATH;
	private UrlRequestDao urlRequestDao=new UrlRequestDao();
	private final static long SECOND=1000;
	private boolean initFlag;
	
	public CreateJdoaListener() {
		// TODO Auto-generated constructor stub
	}
	private Executor executor = Executors.newFixedThreadPool(200);
	private static final String [] TIME1 ={"09:20:00","11:10:00"};
	private static final String [] TIME2={"13:05:00","15:10:00"};  
	private static final String [] SAVE_DAY_K={"19:40:00","20:55:00"};
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}
	private static DateTimeFormatter formatter_hh_mm=DateTimeFormatter.ofPattern("HH:mm:ss");
	 /**
		 *  [20:00:00,21:20:00] now time is 21:00:12  return false else true
		 */
		public  boolean TimeOut(String[] timeStr){
			boolean isTimeOut=true;
			LocalTime time1= LocalTime.parse(timeStr[0],formatter_hh_mm );
			LocalTime time2= LocalTime.parse(timeStr[1],formatter_hh_mm );
			LocalTime timeNow= LocalTime.now();
			LocalDate date= LocalDate.now();
			Duration dut= Duration.between(time1, timeNow);
			Duration dut2=Duration.between(timeNow, time2);
			if(date.getDayOfWeek()!=DayOfWeek.SATURDAY&&date.getDayOfWeek()!=DayOfWeek.SUNDAY)
			if(dut.getSeconds()>0&&dut2.getSeconds()>0){
				isTimeOut=false;
			}
			return isTimeOut;
		}
	private void init() {
		System.out.println("come into --------------------");
		try {
			URL listers =CreateJdoaListener.class.getResource("");
			 
			 String systems = listers.getPath().split("zcom")[0]+"symbol.txt";
			 
			 FILE_NAME_PATH=systems;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		executor.execute(new Runnable() {
			 
			public void run() {
				ReadTextSymbol test = new ReadTextSymbol();
				boolean flushProfit=false;
				List<String> symbols = test.readSymolByString(FILE_NAME_PATH);
				 
				List<Contract> list = test.readTextByContract(FILE_NAME_PATH);
				long cnt1 =0; 
				if(initFlag)
   				RedisOfReader.initReadInredisKeyLevel1(list);
				 //股票 更新 详细信息   开盘价  收盘价 摆单情况
				while(true){
					System.out.println(" begin ===========");
					list=test.readTextByContract(FILE_NAME_PATH);
				LocalDate localdate= LocalDate.now();
 				 
				if(!TimeOut(TIME1)||!TimeOut(TIME2)){
					System.out.println("stcok ");
					 try {	
				for(int j=0;j<symbols.size()/dev_num;++j){
					StringBuffer sb=new StringBuffer();
					int cnt=dev_num;
					if(j==((symbols.size()/dev_num))&&symbols.size()%1000!=0)
						cnt=symbols.size()%dev_num;
				 for (int i = 0; i < cnt; i++) {
					sb.append(symbols.get(j*dev_num+i));
					 
					 if(i!=symbols.size()-1)
						sb.append(",");
				}
					 List<String> values=urlRequestDao.readContentFromGet(QQ_M_REQUEST_URL+sb.toString());
					 jtdoaAPIDao.saveQQ_M_REQUEST_URL(values,false);
				}// end in  for(int j=0;j<symbols.size()/dev_num;++j){ 股票更新完成 一轮
				//余数更新
			 
				StringBuffer sb=new StringBuffer(); 
				for (int i = 0; i < symbols.size()%dev_num; i++) {
					sb.append(symbols.get((symbols.size()/dev_num)*dev_num+i));
					if(i!=symbols.size()-1)
						sb.append(",");
				}//for
				 List<String> values=	urlRequestDao.readContentFromGet(QQ_M_REQUEST_URL+sb.toString());
				 
				 jtdoaAPIDao.saveQQ_M_REQUEST_URL(values,false);
				 flushProfit=false;
						Thread.sleep(SECOND*9);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 				 }else{//if timeOut
 					 System.out.println("time out ");
 					  
 					 if(localdate.getDayOfWeek().getValue()!=7&&localdate.getDayOfWeek().getValue()!=6){
 					 if(!TimeOut(SAVE_DAY_K)&&flushProfit==false){
 						flushProfit=true;
 						System.out.println(LocalTime.now()+"   "+flushProfit);
 						jtdoaAPIDao.updateProfitData();
 						System.out.println("update progitdata over !!!!!!!!!!!!!!!");
 					} 
 					 }
 					 System.out.println("time out "+flushProfit+" "+localdate);
 					 try {
						Thread.sleep(1000*60*60);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
 					}
				}//while
			}
		});
		/*
		 *  指数发送
		 */
		executor.execute(new Runnable() {
			public void run() {
				String [] [] husheng=HU_SHEN_STOCK_INDEX;
				if(initFlag)
					RedisOfReader.initReadInredisKeyLevel1Index(HU_SHEN_STOCK_INDEX);
				int single=0;
				while(true){

					if(!TimeOut(TIME1)||!TimeOut(TIME2)){
		    	StringBuffer sb=new StringBuffer();
		    	for (int i = 0; i < husheng.length; i++) {
					sb.append(""+husheng[i][0].substring(7).toLowerCase()+husheng[i][0].substring(0, 6));
					if(i<husheng.length-1)
						sb.append(",");
		    	}
		    	try {
					List<String> values=urlRequestDao.readContentFromGet(QQ_M_REQUEST_URL+sb.toString());
				    single =jtdoaAPIDao.saveQQ_M_REQUEST_URL(values,true);
		    	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
		    	try {
					Thread.sleep(SECOND*10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					}else{ //if
						System.out.println("time out ");
						 try {
								Thread.sleep(1000*60*60);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
					 
			}//while 
				 
				}
		});
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		 
		String initFlag=(arg0.getServletContext().getInitParameter("initFlag"));
		 
		this.initFlag=Boolean.parseBoolean(initFlag);
		 
		// TODO Auto-generated method stub
		Runnable run=new Runnable() {
			public void run() {
			 
			 	init();
			}
		};
		Thread thread=new Thread(run);
		
		thread.setDaemon(true);
		thread.start();
	}

}
