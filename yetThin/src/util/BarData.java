package util;

import java.util.Date;

public class BarData implements Comparable<BarData>{

	public Contract contract;
	public long dateTime;
	public double open;
	public double high;
	public double low;
	public double close;
	public double ystClose;//昨收
	public long volume;
	public long iTurover;
	public int matchItems;//成交笔数
	public BarData() {
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BarData [ dateTime=" + new Date(dateTime*1000) + ", open=" + open + ", high=" + high
				+ ", low=" + low + ", close=" + close + ", ystClose=" + ystClose + ", volume=" + volume + ", iTurover="
				+ iTurover + ", matchItems=" + matchItems + "]";
	}
	@Override
	public int compareTo(BarData o) {
		// TODO Auto-generated method stub
		if(o.dateTime>this.dateTime)
			return -1;
		else
			return 1;
	}

}
