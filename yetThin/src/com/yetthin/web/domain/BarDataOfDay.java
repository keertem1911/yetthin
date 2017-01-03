package com.yetthin.web.domain;

public class BarDataOfDay {
    private String id;

    private String datetime;

    private String stockcode;

    private Double height;

    private Double low;

    private Double open;

    private Double close;

    private String stockname;
    
    private String volume;
    
    private Double m5;
    private Double m10;
    private Double m60;
    private Double m120;
	private int passRate;
	private int fundRate;
	
    
    
    public BarDataOfDay() {
		 
		 
		this.datetime = null;
		this.stockcode = null;
		this.height = 0.0;
		this.low = 0.0;
		this.open = 0.0;
		this.close = 0.0;
		this.stockname = null;
		this.volume = null;
		this.m5 = 0.0;
		this.m10 = 0.0;
		this.m60 = 0.0;
		this.m120 = 0.0;
		this.passRate = 0;
		this.fundRate = 0;
	}

	public Double getM5() {
		return m5;
	}

	public void setM5(Double m5) {
		this.m5 = m5;
	}

	public Double getM10() {
		return m10;
	}

	public void setM10(Double m10) {
		this.m10 = m10;
	}

	public Double getM60() {
		return m60;
	}

	public void setM60(Double m60) {
		this.m60 = m60;
	}

	public Double getM120() {
		return m120;
	}

	public void setM120(Double m120) {
		this.m120 = m120;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}



    public int getPassRate() {
		return passRate;
	}

	public void setPassRate(int passRate) {
		this.passRate = passRate;
	}

	public int getFundRate() {
		return fundRate;
	}

	public void setFundRate(int fundRate) {
		this.fundRate = fundRate;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public String getStockname() {
        return stockname;
    }

    public void setStockname(String stockname) {
        this.stockname = stockname;
    }

	@Override
	public String toString() {
		return "BarDataOfDay [datetime=" + datetime + ", stockcode=" + stockcode + ", m5=" + m5 + ", m10=" + m10
				+ ", m60=" + m60 + ", m120=" + m120 + "]";
	}

	 
}