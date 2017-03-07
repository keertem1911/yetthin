package util;
/**
 * 
 * @author keerte
 *
 */
public class Level1Value implements Comparable<Level1Value>{
	 private String symbol;
	 private String secType;
	 private String currency;
	 private String tickType;
	 private String l1Value;
	  
	 private String size;
	
	 
	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getSecType() {
		return secType;
	}

	public void setSecType(String secType) {
		this.secType = secType;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getTickType() {
		return tickType;
	}

	public void setTickType(String tickType) {
		this.tickType = tickType;
	}

	public String getL1Value() {
		return l1Value;
	}

	public void setL1Value(String l1Value) {
		this.l1Value = l1Value;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Level1Value() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Level1Value [symbol=" + symbol + ", secType=" + secType + ", currency=" + currency + ", tickType="
				+ tickType + ", l1Value=" + l1Value + ", size=" + size + "]";
	}

	@Override
	public int compareTo(Level1Value o) {
		// TODO Auto-generated method stub
		if(o!=null)
		if(o.getL1Value()!=null&&!o.getL1Value().equals("")){
			if(this.getL1Value()!=null&&!this.getL1Value().equals("")){
		double dou1=Double.parseDouble(this.getL1Value());
		double dou2=Double.parseDouble(o.getL1Value());
		
		if(dou1>dou2)
			return 1;
		}
		}
		return -1;
	}

	 

	 

}
