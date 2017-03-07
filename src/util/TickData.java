package util;

public class TickData {

	public Contract contract;
	public long dateTime;
	public double tickPrice;
	public long volume;
	public double turover;//成交额
	public int matchItems;//成交笔数
	public TickData() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "TickData [  tickPrice=" + tickPrice + ", volume="
				+ volume + ", turover=" + turover + ", matchItems=" + matchItems + "]";
	}
	
}
