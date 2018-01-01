package com.costuary.bean;

public class VisitorBean {

	private String visitIP;
	private String timeStamp;
	private String addressType;

	public VisitorBean(){}

	public String getVisitIP() {
		return visitIP;
	}
	public void setVisitIP(String visitIP) {
		this.visitIP = visitIP;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getAddressType() {
		return addressType;
	}
	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}
}
