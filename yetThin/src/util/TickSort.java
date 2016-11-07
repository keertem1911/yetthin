package util;
/**
 * 排序的tickdate
 * @author Administrator
 *
 */
public class TickSort implements Comparable<TickSort>{

	private String dateTime;
	private double tickPrice;
	private long volume;
	
	public TickSort(TickData tick,String dateTime) {
		super();
		this.dateTime=dateTime;
		this.tickPrice = tick.tickPrice;
		this.volume = tick.volume;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public double getTickPrice() {
		return tickPrice;
	}
	public void setTickPrice(double tickPrice) {
		this.tickPrice = tickPrice;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	@Override
	public String toString() {
		return "TickSort [dateTime=" + dateTime + ", tickPrice=" + tickPrice + ", volume=" + volume + "]";
	}
	@Override
	public int compareTo(TickSort o) {
		// TODO Auto-generated method stub
	 		int plus=1;
			// 09:31  09:30
			if(o.getDateTime().split("-")[0].compareTo(this.getDateTime().split("-")[0])>0){
				plus=-1;
			}else{
				if(o.getDateTime().split("-")[0].compareTo(this.getDateTime().split("-")[0])==0){
					if(o.getDateTime().split("-")[1].compareTo(this.getDateTime().split("-")[1])>0){
						plus=-1;
					}else{
						if(o.getDateTime().split("-")[1].compareTo(this.getDateTime().split("-")[1])==0){
							if(o.getDateTime().split("-")[2].compareTo(this.getDateTime().split("-")[2])>0){
								plus=-1;
							}
						
					}
				}
			}
			}
		
		return plus;
	}
	
}
