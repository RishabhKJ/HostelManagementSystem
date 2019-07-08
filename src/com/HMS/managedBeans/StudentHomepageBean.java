package com.HMS.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.HMS.functions.AccessValidator;

@ManagedBean(name = "StudentHomepageBean")
@RequestScoped
public class StudentHomepageBean {

	@ManagedProperty("#{loginBean.userName}")
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String RedirectCoolerForm() {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser1(uname)) {

			navResult = "Register_Cooler";

		} else {
			navResult = "login";

		}

		return navResult;
	}

	public String RedirectVehicleForm() {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser1(uname)) {

			navResult = "Register_Vehicle";

		} else {
			navResult = "login";

		}

		return navResult;
	}

}
