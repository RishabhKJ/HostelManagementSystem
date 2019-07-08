package com.HMS.entity;


import java.util.List;



public class Wing {
	private String type;
	public List<Floor> floors;
	public List<Floor> getFloors() {
		return floors;
	}
	public void setFloors(List<Floor> floors) {
		this.floors = floors;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}