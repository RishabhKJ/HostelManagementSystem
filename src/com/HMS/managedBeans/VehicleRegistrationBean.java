package com.HMS.managedBeans;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.HMS.functions.*;

@ManagedBean(name = "VehicleRegistrationBean")
@RequestScoped
public class VehicleRegistrationBean {
	@ManagedProperty("#{loginBean.userName}")
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	private String vehicletype;
	private String number;
	private String arrivaldate;
	private String license;
	private String reason;

	public String getVehicletype() {
		return vehicletype;
	}

	public void setVehicletype(String vehicletype) {
		this.vehicletype = vehicletype;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String submit() {
		String navResult = "";
		AccessValidator validate = new AccessValidator();
		if (validate.validateuser1(uname)) {
			if (checkdate(arrivaldate)) {

				System.out.println(vehicletype + "" + number + "" + arrivaldate + "" + license + "" + reason);

				String status = "To_Be_approved";
				VehicleDetails ctrl = new VehicleDetails();
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				String curr_id = (String) session.getAttribute("curr_id");
				int result = ctrl.savevehicle(curr_id, vehicletype, number, arrivaldate, license, reason, status);
				System.out.println(result + " " + curr_id);
				if (result == 0) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Your form is Submitted successfully!", "Message details");

					context.addMessage(null, message);
					context.validationFailed();
					navResult = "Student_Homepage";

				} else if (result == 1) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Your previous request is already under consideration", "Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult = "Register_Vehicle";

				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Your previous request is already approved", "Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult = "Register_Vehicle";

				}

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Please enter Arrival date  in MM/DD/YYYY", "Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult = "Register_Cooler";
			}
		} else {

			navResult = "login";
		}
		return navResult;
	}

	private boolean checkdate(String arrivaldate) {
		// TODO Auto-generated method stub
		String date = arrivaldate;
		SimpleDateFormat sdfrmt = new SimpleDateFormat("MM/dd/yyyy");
		sdfrmt.setLenient(false);
		try {
			@SuppressWarnings("unused")
			Date javaDate = sdfrmt.parse(date);
			System.out.println(date + " is valid date format");
		}
		/* Date format is invalid */
		catch (Exception e) {
			System.out.println(date + " is Invalid Date format");
			return false;
		}
		/* Return true if date format is valid */
		return true;
	}

}
