package util;

public class Level2Value implements Comparable<Level2Value>{
	  
	private String symbol;
	private  String exchange;
	private String currency;
	 
	private int side  ;
	private double price;
	private int size;
	private int checksum;
	public Level2Value() {
		// TODO Auto-generated constructor stub
		 symbol="";
		 exchange="";
		 	 currency="";
		 	 
		 	 side  =0;
		 	 price=0.0;
		 	 size=0;
		 	 checksum=0;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getExchange() {
		return exchange;
	}
	public void setExchange(String exchange) {
		this.exchange = exchange;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	 
	public int getSide() {
		return side;
	}
	public void setSide(int side) {
		this.side = side;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getChecksum() {
		return checksum;
	}
	public void setChecksum(int checksum) {
		this.checksum = checksum;
	}
	@Override
	public String toString() {
		return "Level2Value [symbol=" + symbol + ", exchange=" + exchange + ", currency=" + currency  + ", side=" + side + ", price=" + price + ", size=" + size + ", checksum=" + checksum + "]";
	}
	@Override
	public int compareTo(Level2Value o) {
		// TODO Auto-generated method stub
		if(this.getSide()>o.getSide()){
			return 1;
		}else{
			if(this.getSide()==o.getSide()){
				if(this.getChecksum()>o.getChecksum())
					return 1;
			}
		}
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
