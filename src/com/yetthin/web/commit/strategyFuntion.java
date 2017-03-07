package com.yetthin.web.commit;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.yetthin.web.domain.BarDataOfDay;

public class strategyFuntion {
	/**
	 * 涨跌 限制
	 */
	private static double STRATEGY_UPDATE=5.5;
	/**
	 * 根据选择的 策略 进行股票的筛选
	 * @param list
	 * @param type
	 * @param limit
	 * @return
	 */
	public  static  List<BarDataOfDay> strategy(List<BarDataOfDay> list,int type,int limit){
		
		/**
		 * 按照 股票代码进行分类 并且按照 时间晚早 排序
		 */
		Map<String, List<BarDataOfDay>> mapByStockCode= 
				list.parallelStream().sorted(Comparator.comparing(BarDataOfDay::getDatetime).reversed()).
				collect(Collectors.groupingBy(BarDataOfDay::getStockcode));
		List<BarDataOfDay> oneStockCode =new ArrayList<>();
		Predicate<BarDataOfDay> pre =null;
		Set<String> keys = mapByStockCode.keySet();

		switch(type){
		case 0: return new ArrayList<BarDataOfDay>();
			 
		case 1: 
		case 3:
			if(type==1)
				pre=(BarDataOfDay barday)->barday.getPassRate()>0.6&&barday.getFundRate()>0.15;
			else
				pre=(BarDataOfDay barday)->barday.getPassRate()<0.6&&barday.getFundRate()>0.10;
			/**
			 * 计算 均线的收益 及胜率
			 */
			keys.forEach(key->{
				List<BarDataOfDay> liststockCodeChild = mapByStockCode.get(key);
				if(liststockCodeChild.size()>2){
					 Double ma51 = liststockCodeChild.get(0).getM5();
					 Double ma52 = liststockCodeChild.get(1).getM5();
					 Double ma53 = liststockCodeChild.get(2).getM5();
					 double pass= (ma51-ma52)/(ma51-ma53);
					 double fund =(ma51-ma52)/(ma52-ma53);
//					 pass =Math.round(100*pass)/100;
//					 fund =Math.round(100*fund)/100;
					BarDataOfDay firstStockCode = liststockCodeChild.get(0);
					firstStockCode.setPassRate(pass);
					firstStockCode.setFundRate(fund);
//					System.out.println(pass+" ------------- "+ fund);
				}
			});
			/**
			 * 将每个 key 中list 的最新值取出来
			 */
			keys.forEach(key->{
				List<BarDataOfDay> listStockCodeChild = mapByStockCode.get(key);
				if(listStockCodeChild.size()>0)
					oneStockCode.add(listStockCodeChild.get(0));
			});
			break;
		case 2:
			keys.forEach(key->{
				List<BarDataOfDay> listStockCodeChild = mapByStockCode.get(key);
				if(listStockCodeChild.size()>0)
					oneStockCode.add(listStockCodeChild.get(0));
			});
			for(BarDataOfDay bar :oneStockCode){
				double open =bar.getOpen();
				double close=bar.getClose();
				double height=bar.getHeight();
				double low= bar.getLow();
				double passRate=0;
				if(height!=low)
				passRate =(100*(height-open)/open);
//				System.out.println(passRate+" ----------------");
				bar.setPassRate(passRate);
			}
			pre =(BarDataOfDay e)->e.getPassRate()> STRATEGY_UPDATE;
			
			break;
		default: return new ArrayList<BarDataOfDay>();
				
		}
		List<BarDataOfDay> filter =new ArrayList<>();
			 
		Comparator<BarDataOfDay> comparing=null;
//		
		if(type==0||type==3)
			comparing =Comparator.comparing(BarDataOfDay::getPassRate);
		else 
			comparing=Comparator.comparing(BarDataOfDay::getFundRate).reversed();
		filter =list.stream().filter(pre).sorted(comparing).collect(Collectors.toList());
		
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
