package util;

public class Order {

	//order identifier
	public long orderId;
	public long clientId;
	
	//main order fields
	public long totalQuantity;
	public double lmtPrice;
	public double auxPrice;
	public String action;
	public String orderType;
	
	public int tradeManner;//0collect,1usual
	public int tradeType;//Atradetype
	public int quotationType;//
	public int accountType;
	public Order() {
		// TODO Auto-generated constructor stub
	}

}
