package com.HMS.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import com.HMS.entity.Vehicle;
import com.HMS.functions.AccessValidator;
import com.HMS.functions.VehicleRequests;

@ManagedBean(name = "VehicleRequestBean")
@ViewScoped
public class VehicleRequestBean implements Serializable {
	/**
	 * 
	 */
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	private static final long serialVersionUID = 1L;
	List<Vehicle> results = new ArrayList<Vehicle>();

	@PostConstruct
	public void init() {
		VehicleRequests vehicle = new VehicleRequests();
		results = vehicle.get_tobeapproved();

	}

	public List<Vehicle> getResults() {
		return results;
	}

	public String onAllow(String id) {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {
			VehicleRequests vehicle = new VehicleRequests();
			vehicle.confirm_cooler(id);
			navResult = "Vehicle_Request";
		} else {

			navResult = "login";
		}

		return navResult;
	}

	public String onDeny(String id) {

		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			VehicleRequests cooler = new VehicleRequests();
			cooler.deny_cooler(id);
			navResult = "Vehicle_Request";

		} else {

			navResult = "login";
		}
		return navResult;

	}
}
