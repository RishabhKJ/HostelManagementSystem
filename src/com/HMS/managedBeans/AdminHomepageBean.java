package com.HMS.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.HMS.functions.AccessValidator;

@ManagedBean(name = "AdminHomepageBean")
@RequestScoped

public class AdminHomepageBean {

	@ManagedProperty("#{loginBean.userName}")
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String RedirectCoolerRequest() {

		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			navResult = "Cooler_Request";

		} else {
			navResult = "login";

		}

		return navResult;

	}

	public String RedirectVehicleRequest() {

		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			navResult = "Vehicle_Request";

		} else {
			navResult = "login";

		}

		return navResult;
	}

	public String RedirectElectricityBill() {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			navResult = "Electricity_Bill";

		} else {
			navResult = "login";

		}

		return navResult;
	}

	public String RedirectAllotRoom() {

		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			navResult = "Allot_Room";

		} else {
			navResult = "login";

		}

		return navResult;
	}

	public String RedirectRoomChart() {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

			navResult = "Chart";

		} else {
			navResult = "login";

		}

		return navResult;
	}

}
