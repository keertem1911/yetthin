package com.yetthin.web.commit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.yetthin.web.domain.BarDataOfDay;
import com.yetthin.web.domain.stockKempty;

public class strategyFuntion {
	public  static  List<BarDataOfDay> strategy(List<BarDataOfDay> list,int type,int limit){
		List<BarDataOfDay> filter =new ArrayList<>();
		for(BarDataOfDay bar :list){
			double open =bar.getOpen();
			double close=bar.getClose();
			double height=bar.getHeight();
			double low= bar.getLow();
			int passRate=0;
			if(height!=low)
			passRate =(int)(100*(close-open)/(height-low));
			
			int  fundRate =(int) (100*(close-open)/(open));
			bar.setPassRate(passRate);
			bar.setFundRate(fundRate);
			System.out.println(passRate+" "+fundRate);
		}	 
			
		Predicate<BarDataOfDay> pre =null;
		switch(type){
		case 0:
			pre =(BarDataOfDay e)-> e.getPassRate()>80&&e.getFundRate()<10;
			break;
		case 1: 
			pre =(BarDataOfDay e)->e.getPassRate()>70&&e.getFundRate()>10&&e.getFundRate()<50;
		break;
		case 2: 
			pre =(BarDataOfDay e)->e.getPassRate()<50&&e.getFundRate()>80;
		break;
		case 3: 
			pre =(BarDataOfDay e)->e.getPassRate()<60&&e.getFundRate()>10;
		break;
		case 4: 
			pre =(BarDataOfDay e)->e.getPassRate()>80&&e.getFundRate()>10;//
		
		
		}
		Comparator<BarDataOfDay> comparing=null;
//		
		if(type==0||type==3)
			comparing =Comparator.comparing(BarDataOfDay::getPassRate);
		else 
			comparing=Comparator.comparing(BarDataOfDay::getFundRate).reversed();
//		filter.sort(comparing);
		filter =list.stream().filter(pre).sorted(comparing).limit(limit).collect(Collectors.toList());
		
		return filter;
	}
//	public static void main(String[] args) {
//		List<stockKempty> list =new ArrayList<>();
//		for(int i=0;i<20;++i){
//			stockKempty e= new stockKempty();
//			e.setOpen("7.16");
//			e.setClose("9.22");
//			e.setHeight("9."+i);
//			e.setLow("7."+i);
//			e.setStockCode("0202"+i);
//			e.setStockName("xiaomi"+i);
//			list.add(e);
//		}
//		List<stockKempty> lists =strategy(list,0,20); 
//		lists.stream().forEach(System.out::print);
//	}
}
