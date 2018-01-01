package com.costuary;

public class TemperatureRecordBean {

	private int id;
	private float cpu;
	private float gpu;
	private int fan;
	private String timestampStr;

	public TemperatureRecordBean(){}

	public TemperatureRecordBean(int id,float cpu,float gpu,int fan,String timestampStr){
		this.id = id;
		this.cpu = cpu;
		this.gpu = gpu;
		this.fan = fan;
		this.timestampStr = timestampStr;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public float getCpu() {
		return cpu;
	}


	public void setCpu(float cpu) {
		this.cpu = cpu;
	}

	public float getGpu() {
		return gpu;
	}


	public void setGpu(float gpu) {
		this.gpu = gpu;
	}

	public int getFan() {
		return fan;
	}


	public void setFan(int fan) {
		this.fan = fan;
	}

	public String getTimestampStr() {
		return timestampStr;
	}


	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}







}
