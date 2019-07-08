package com.HMS.entity;

import java.io.*;

public class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String vehicle_type;
	private String vehicle_number;
	private String arrival_date;
	private String license_number;
	private String reason;
	private String curr_id;
	private String status;

	public long getId() {
		return id;
	}

	public String getCurr_id() {
		return curr_id;
	}

	public String getStatus() {
		return status;
	}

	public String getVehicle_type() {
		return vehicle_type;
	}

	public String getVehicle_number() {
		return vehicle_number;
	}

	public String getArrival_date() {
		return arrival_date;
	}

	public String getLicense_number() {
		return license_number;
	}

	public String getReason() {
		return reason;
	}

	public void setId(long string) {
		id = string;
	}

	public void setVehicle_type(String string) {
		vehicle_type = string;
	}

	public void setVehicle_number(String string) {
		vehicle_number = string;
	}

	public void setArrival_date(String string) {
		arrival_date = string;
	}

	public void setLicense_number(String string) {
		license_number = string;
	}

	public void setReason(String string) {
		reason = string;
	}

	public void setCurr_id(String string) {
		curr_id = string;
	}

	public void setStatus(String string) {
		status = string;
	}

}
