package com.HMS.entity;

import java.io.*;

public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String stuid;
	private String stupassword;

	public long getId() {
		return id;
	}

	public String getStuid() {
		return stuid;
	}

	public String getStupassword() {
		return stupassword;
	}

	public void setId(long string) {
		id = string;
	}

	public void setStuid(String string) {
		stuid = string;
	}

	public void setStupassword(String string) {
		stupassword = string;
	}

}
