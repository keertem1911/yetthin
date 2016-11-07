package util;

public class Contract {

	public long contractId;
	public String symbol;//symbol name
	public String secType;//trade type
	public String exchange;//exchange
	public String currency;//currency
	public String localSymbol;//
	public Contract() {
		// TODO Auto-generated constructor stub
	}
	public long getContractId() {
		return contractId;
	}
	public void setContractId(long contractId) {
		this.contractId = contractId;
	}
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
	public String getLocalSymbol() {
		return localSymbol;
	}
	public void setLocalSymbol(String localSymbol) {
		this.localSymbol = localSymbol;
	}
	@Override
	public String toString() {
		return "Contract [contractId=" + contractId + ", symbol=" + symbol + ", secType=" + secType + ", exchange="
				+ exchange + ", currency=" + currency + ", localSymbol=" + localSymbol + "]";
	}
	

}
