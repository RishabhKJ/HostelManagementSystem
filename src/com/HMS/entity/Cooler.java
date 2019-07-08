package com.HMS.entity;

import java.io.Serializable;

public class Cooler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String cooler_type;
	private String curr_id;
	private String company;
	private String arrival_date;
	private String depature_date;
	private String reason;
	private String status;

	public long getId() {
		return id;
	}

	public String getCooler_type() {
		return cooler_type;
	}

	public String getCurr_id() {
		return curr_id;
	}

	public String getCompany() {
		return company;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public String getDepature_date() {
		return depature_date;
	}

	public String getReason() {
		return reason;
	}

	public String getStatus() {
		return status;
	}

	public void setId(long string) {
		id = string;
	}

	public void setCooler_type(String string) {
		cooler_type = string;
	}

	public void setCurr_id(String string) {
		curr_id = string;
	}

	public void setCompany(String string) {
		company = string;
	}

	public void setArrival_date(String string) {
		arrival_date = string;
	}

	public void setDepature_date(String string) {
		depature_date = string;
	}

	public void setReason(String string) {
		reason = string;
	}

	public void setStatus(String string) {
		status = string;
	}

}
