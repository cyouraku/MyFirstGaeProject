package com.costuary.bean;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement( name = "beans" )
public class RootBean {


    private List<XmlBean> beanList;

	public List<XmlBean> getBeanList() {
		return beanList;
	}

	@XmlElement( name = "bean" )
	public void setBeanList(List<XmlBean> beanList) {
		this.beanList = beanList;
	}


}
