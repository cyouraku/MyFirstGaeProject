package com.costuary.bean;

import java.io.Serializable;

public class MyLoginBean implements Serializable{

	private String userName;
	private String userPw;

	public MyLoginBean(){}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPw() {
		return userPw;
	}

	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}



}
