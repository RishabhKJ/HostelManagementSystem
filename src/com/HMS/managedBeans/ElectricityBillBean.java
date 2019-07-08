package com.HMS.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import com.HMS.entity.Room;

import com.HMS.functions.RoomDetails;

@ManagedBean(name = "ElectricityBillBean")
@ViewScoped
public class ElectricityBillBean implements Serializable {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	public String hor_type_wing = "hor_men";
	private String hor_type_hor = "hor_men";
	public String wing_type_wing = "A";
	public String floor_type_wing = "gnd";

	private String stu_wing;
	private int numofstudents = 0;
	private String fee_id;

	private int totalbill = 0;
	private int totalbill_wing = 0;
	private float billvalue;
	private float finalbill = (float) 0.0;
	private float finalbill_wingwise = (float) 0.0;
	private int numofstudents_wing = 0;
	private int numofstudents_floor = 0;
	List<Room> stulist = new ArrayList<Room>();

	public List<Room> getStulist() {

		RoomDetails room = new RoomDetails();
		stulist = room.getStuList(wing_type_wing);

		return stulist;
	}

	public float getFinalbill() {
		return finalbill;
	}

	public void setFinalbill(float finalbill) {
		this.finalbill = finalbill;
	}

	public float getFinalbill_wingwise() {
		return finalbill_wingwise;
	}

	public void setFinalbill_wingwise(float finalbill_wingwise) {
		this.finalbill_wingwise = finalbill_wingwise;
	}

	List<String> hor_wings = new ArrayList<String>();

	public List<String> getHor_wings() {

		return hor_wings;

	}

	public String getHor_type_wing() {
		return hor_type_wing;
	}

	public void setHor_type_wing(String hor_type_wing) {
		this.hor_type_wing = hor_type_wing;
	}

	public String getFloor_type_wing() {
		return floor_type_wing;
	}

	public void setFloor_type_wing(String floor_type_wing) {
		this.floor_type_wing = floor_type_wing;
	}

	public String getHor_type_hor() {
		return hor_type_hor;
	}

	public void setHor_type_hor(String hor_type_hor) {
		this.hor_type_hor = hor_type_hor;
	}

	public String getWing_type_wing() {
		return wing_type_wing;
	}

	public void setWing_type_wing(String wing_type_wing) {
		this.wing_type_wing = wing_type_wing;
	}

	public String getStu_wing() {
		return stu_wing;
	}

	public void setStu_wing(String stu_wing) {
		this.stu_wing = stu_wing;
	}

	public int getNumofstudents() {
		return numofstudents;
	}

	public void setNumofstudents(int numofstudents) {
		this.numofstudents = numofstudents;
	}

	public int getNumofstudents_wing() {
		return numofstudents_wing;
	}

	public void setNumofstudents_wing(int numofstudents_wing) {
		this.numofstudents_wing = numofstudents_wing;
	}

	public int getNumofstudents_floor() {
		return numofstudents_floor;
	}

	public void setNumofstudents_floor(int numofstudents_floor) {
		this.numofstudents_floor = numofstudents_floor;
	}

	public int getTotalbill() {
		return totalbill;
	}

	public void setTotalbill(int totalbill) {
		this.totalbill = totalbill;
	}

	public int getTotalbill_wing() {
		return totalbill_wing;
	}

	public void setTotalbill_wing(int totalbill_wing) {
		this.totalbill_wing = totalbill_wing;
	}

	public float getBillvalue() {
		return billvalue;
	}

	public void setBillvalue(float billvalue) {
		this.billvalue = billvalue;
	}

	public String getFee_id() {
		return fee_id;
	}

	public void setFee_id(String fee_id) {
		this.fee_id = fee_id;
	}

	public List<String> hor_wings_pop() {
		// System.out.println(hor_type);
		if (hor_type_wing.equals("hor_men")) {
			hor_wings.clear();
			hor_wings.add("A");
			hor_wings.add("B");
			hor_wings.add("C");
			hor_wings.add("D");
			hor_wings.add("E");
			hor_wings.add("F");
			hor_wings.add("G");
			hor_wings.add("H");
			hor_wings.add("I");
		} else if (hor_type_wing.equals("hor_women")) {
			hor_wings.clear();
			hor_wings.add("J");
			hor_wings.add("K");
		} else
			hor_wings.add("NAN");

		return hor_wings;
	}

	public String RedirectHor() {

		String navResult = "HOR";
		System.out.println(navResult);
		return navResult;
	}

	public String RedirectWing() {

		String navResult = "WING";
		System.out.println(navResult);
		return navResult;
	}

	public String RedirectStu() {

		String navResult = "STUDENT";
		System.out.println(navResult);
		return navResult;
	}

	public void WingChange_hor(ValueChangeEvent e) {
		hor_type_wing = e.getNewValue().toString();

		System.out.println("New Hor is " + hor_type_wing);
	}

	public void WingChange_wing(ValueChangeEvent e) {
		wing_type_wing = e.getNewValue().toString();

		System.out.println(wing_type_wing);

	}

	public void HorChange_hor(ValueChangeEvent e) {
		hor_type_hor = e.getNewValue().toString();
	}

	public void FloorChange_hor(ValueChangeEvent e) {
		floor_type_wing = e.getNewValue().toString();
	}

	public int numofstudents() {
		int num = 0;
		RoomDetails r = new RoomDetails();
		if (hor_type_hor.equals("hor_men")) {
			num = r.getHorMen();
			numofstudents = num;
			return num;
		} else {
			num = r.getHorWomen();
			numofstudents = num;
			return num;
		}
	}

	public int numofstudents_HOR_wingwise() {
		int num = 0;
		RoomDetails r2 = new RoomDetails();
		if (hor_type_wing.equals("hor_men")) {
			num = r2.getHorMen();
			numofstudents = num;
			return num;
		} else {
			num = r2.getHorWomen();
			numofstudents = num;
			return num;
		}

	}

	public int numofstudents_Wing_wingwise() {
		int num = 0;
		if (hor_type_wing.equals("hor_men") && (wing_type_wing.equals("J") || wing_type_wing.equals("K")))
			wing_type_wing = "A";
		if (hor_type_wing.equals("hor_women")
				&& (wing_type_wing.equals("A") || wing_type_wing.equals("B") || wing_type_wing.equals("C")
						|| wing_type_wing.equals("D") || wing_type_wing.equals("E") || wing_type_wing.equals("F")
						|| wing_type_wing.equals("G") || wing_type_wing.equals("H") || wing_type_wing.equals("I")))
			wing_type_wing = "J";
		RoomDetails r2 = new RoomDetails();
		System.out.println("New wing is " + wing_type_wing);
		num = r2.calculateStudentsWing(wing_type_wing);
		numofstudents_wing = num;

		return num;

	}

	public int numofstudents_Floor_wingwise() {

		int num = 0;
		if (hor_type_wing.equals("hor_men") && (wing_type_wing.equals("J") || wing_type_wing.equals("K"))) {
			wing_type_wing = "A";
			floor_type_wing = "gnd";
		}
		if (hor_type_wing.equals("hor_women")
				&& (wing_type_wing.equals("A") || wing_type_wing.equals("B") || wing_type_wing.equals("C")
						|| wing_type_wing.equals("D") || wing_type_wing.equals("E") || wing_type_wing.equals("F")
						|| wing_type_wing.equals("G") || wing_type_wing.equals("H") || wing_type_wing.equals("I"))) {
			wing_type_wing = "J";
			floor_type_wing = "gnd";
		}
		RoomDetails r3 = new RoomDetails();
		System.out.println("New Floor is " + floor_type_wing);
		num = r3.calculateStudentsWingFloor(floor_type_wing, wing_type_wing);
		numofstudents_floor = num;

		return num;

	}

	public float calculateBill() {
		finalbill = (float) totalbill / numofstudents;
		return finalbill;
	}

	public float calculatebill_wingwise() {
		if (numofstudents_floor == 0) {
			finalbill_wingwise = (float) 0.0;
		} else {
			finalbill_wingwise = (float) totalbill_wing / numofstudents_floor;
		}
		System.out.println(numofstudents_floor + "  " + totalbill_wing);
		return finalbill_wingwise;
	}

	public String addHorBill() {
		String navResult = "Electricity_Hor";
		if (totalbill == 0 || finalbill == 0 || numofstudents == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Your Total Bill is 0 or you have not clicked on generate fees or number of students is 0.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();

		} else {
			RoomDetails r1 = new RoomDetails();
			r1.addFees(hor_type_hor, finalbill);
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fees added successfully.",
					"Message details");

			context.addMessage(null, message);
			context.validationFailed();
		}
		return navResult;

	}

	public String addWingBill() {
		String navResult = "Electricity_Wing";
		if (totalbill_wing == 0 || finalbill_wingwise == 0 || numofstudents_floor == 0) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Your Total Bill is 0 or you have not clicked on generate fees or number of students is 0.",
					"Message details");
			context.addMessage(null, message);
			context.validationFailed();

		} else {
			RoomDetails r1 = new RoomDetails();
			System.out.println(floor_type_wing + " is the floor where fees is added.");
			r1.addFeesWing(wing_type_wing, finalbill_wingwise, floor_type_wing);

			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fees added successfully.",
					"Message details");

			context.addMessage(null, message);
			context.validationFailed();
		}
		return navResult;
	}

	public String AddStuFee() {
		String navResult = "Electricity_Student";
		System.out.println(" For id " + fee_id + " fee added is " + billvalue);
		RoomDetails room2 = new RoomDetails();
		int result = room2.addStudentFee(fee_id, billvalue);
		if (result == 1) {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Fees added successfully.",
					"Message details");

			context.addMessage(null, message);
			context.validationFailed();
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Student Id not found.",
					"Message details");

			context.addMessage(null, message);

			context.validationFailed();
		}

		return navResult;
	}

}
