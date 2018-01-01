package com.costuary.bean;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType( propOrder = { "id", "cpu", "gpu", "fan" , "timestampStr"} )
public class XmlBean implements Serializable{

	private String id;
	private String cpu;
	private String gpu;
	private String fan;
	private String timestampStr;

	public String getId() {
		return id;
	}
	@XmlAttribute( name = "id" )
	public void setId(String id) {
		this.id = id;
	}
	public String getCpu() {
		return cpu;
	}
	@XmlAttribute( name = "cpu" )
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getGpu() {
		return gpu;
	}
	@XmlAttribute( name = "gpu" )
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getFan() {
		return fan;
	}
	@XmlAttribute( name = "fan" )
	public void setFan(String fan) {
		this.fan = fan;
	}
	public String getTimestampStr() {
		return timestampStr;
	}
	@XmlAttribute( name = "timestampStr" )
	public void setTimestampStr(String timestampStr) {
		this.timestampStr = timestampStr;
	}

}
