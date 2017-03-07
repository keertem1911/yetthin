package com.yetthin.web.domain;

import java.sql.Date;

public class barData {
    private Integer id;

    private Date datetime;

    private String open;

    private String height;

    private String low;

    private String close;

    private String ystclose;

    private String volume;

    private String matchitems;

    private Integer sid;

    

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getLow() {
		return low;
	}

	public void setLow(String low) {
		this.low = low;
	}

	public String getClose() {
		return close;
	}

	public void setClose(String close) {
		this.close = close;
	}

	public String getYstclose() {
		return ystclose;
	}

	public void setYstclose(String ystclose) {
		this.ystclose = ystclose;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	public String getMatchitems() {
		return matchitems;
	}

	public void setMatchitems(String matchitems) {
		this.matchitems = matchitems;
	}

	public Integer getSid() {
		return sid;
	}

	public void setSid(Integer sid) {
		this.sid = sid;
	}

	public barData() {
		 
		this.id = 0;
//		this.datetime = ;
		this.open = "0";
		this.height = "0";
		this.low = "0";
		this.close = "0";
		this.ystclose = "0";
		this.volume = "0";
		this.matchitems = "0";
		this.sid = 0;
	}

	@Override
	public String toString() {
		return "barData [id=" + id + ", datetime=" + datetime + ", open=" + open + ", height=" + height + ", low=" + low
				+ ", close=" + close + ", ystclose=" + ystclose + ", volume=" + volume + ", matchitems=" + matchitems
				+ ", sid=" + sid + "]";
	}
	
    
}