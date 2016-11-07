package com.yetthin.web.commit;

import java.util.ArrayList;
import java.util.ListIterator;

import util.BarData;
import util.Contract;
import util.JHdboa;
import util.JTdoa;
import util.TickData;

public class JtdoaUtil {
	 
	private JtdoaUtil(){
	 
	};
	private static volatile JTdoa jTdoa=null;
	 
	public static JTdoa getInstanceJTdoa(){
		if(jTdoa==null){
			synchronized(JtdoaUtil.class){
				if(jTdoa==null){
					jTdoa =new JTdoa();
					jTdoa.TDOACreate();
					jTdoa.TDOAConnect("222.173.29.210", 7005);
					while(!jTdoa.isConnected()){
						try {
							Thread.sleep(200);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					//	System.out.println("wait connect----------");
					}
					if(jTdoa.isConnected()){
						System.out.println("login has ready ------------");
						int ls=jTdoa.TDOALoginServer("td7", "td7",  "90-b1-1c-80-a4-78", 0, "1");
						System.out.println("ls ls ="+ls);
					}
				 
				while(!jTdoa.isLogined()){
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//System.out.println("wait login ----------------");
				}
				if(jTdoa.isLogined()){
					System.out.println("login has ready ===================");
				//	jtdoa.TDOADestory();
				}
				}
			}
		}
		return jTdoa;
	}
	public static JHdboa getInstanceJHdboa(){
		 JHdboa jHdboa=null;
		if(jHdboa ==null){
		synchronized (JtdoaUtil.class){
			if(jHdboa ==null){
				 jHdboa=new JHdboa();
			 	 jHdboa.HdboaInit(new BarData(),new Contract(),new TickData());
					
				jHdboa.HdboaConnect("222.173.29.210", 7008);
				while(!jHdboa.connected)
				{
					System.out.println("wait");
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}
		}
		return jHdboa;
	}
	public static void main(String[] args) {
		JHdboa j=JtdoaUtil.getInstanceJHdboa();
		System.out.println(j);
	}
}
