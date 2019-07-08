package com.HMS.entity;

import java.util.List;

import com.HMS.functions.RoomString;

public class Floor {
	private String type;
	public List<RoomString> rooms;
	public List<RoomString> getRooms() {
		return rooms;
	}
	public void setRooms(List<RoomString> rooms) {
		this.rooms = rooms;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}