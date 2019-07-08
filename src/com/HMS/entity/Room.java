package com.HMS.entity;

import java.io.Serializable;

public class Room implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String stu_id;
	private String hor;
	private String room;
	private String floor;
	private String wing;

	private float billvalue = (float) 0.0;

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	private float ele_bill;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStu_id() {
		return stu_id;
	}

	public void setStu_id(String stu_id) {
		this.stu_id = stu_id;
	}

	public String getHor() {
		return hor;
	}

	public void setHor(String hor) {
		this.hor = hor;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public float getEle_bill() {
		return ele_bill;
	}

	public void setEle_bill(float ele_bill) {
		this.ele_bill = ele_bill;
	}

	public float getBillvalue() {
		return billvalue;
	}

	public void setBillvalue(float billvalue) {
		this.billvalue = billvalue;
	}

}
