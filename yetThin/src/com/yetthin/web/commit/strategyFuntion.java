package com.yetthin.web.commit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.yetthin.web.domain.stockKempty;

public class strategyFuntion {
	public  static  List<stockKempty> strategy(List<stockKempty> list,int type,int limit){
		List<stockKempty> filter =new ArrayList<>();
		for(stockKempty bar :list){
			double open =Double.parseDouble(bar.getOpen());
			double close=Double.parseDouble(bar.getClose());
			double height=Double.parseDouble(bar.getHeight());
			double low= Double.parseDouble(bar.getLow());
			int passRate =(int)(100*(open-low)/(height-low+0.0));
			int fundRate =(int)(100*(close-open)/(open+0.0));
			bar.setPassRate(passRate);
			bar.setFundRate(fundRate);
		}	 
			
		Predicate<stockKempty> pre =null;
		switch(type){
		case 0:
			pre =(stockKempty e)-> e.getPassRate()<30&&e.getFundRate()>10&&e.getFundRate()<60;
			break;
		case 1: 
			pre =(stockKempty e)->e.getPassRate()>50&&e.getFundRate()>70;
		break;
		case 2: 
			pre =(stockKempty e)->e.getPassRate()>40&&e.getFundRate()>15;
		break;
		case 3: 
			pre =(stockKempty e)->e.getPassRate()<20&&e.getFundRate()>15;
		break;
		
		}
		Comparator<stockKempty> comparing=null;
//		
		if(type==0||type==3)
			comparing =Comparator.comparing(stockKempty::getPassRate);
		else 
			comparing=Comparator.comparing(stockKempty::getFundRate).reversed();
//		filter.sort(comparing);
		filter =list.stream().filter(pre).sorted(comparing).limit(limit).collect(Collectors.toList());
		
		return filter;
	}
	public static void main(String[] args) {
		List<stockKempty> list =new ArrayList<>();
		for(int i=0;i<20;++i){
			stockKempty e= new stockKempty();
			e.setOpen("7.16");
			e.setClose("9.22");
			e.setHeight("9."+i);
			e.setLow("7."+i);
			e.setStockCode("0202"+i);
			e.setStockName("xiaomi"+i);
			list.add(e);
		}
		List<stockKempty> lists =strategy(list,0,20); 
		lists.stream().forEach(System.out::print);
	}
}
