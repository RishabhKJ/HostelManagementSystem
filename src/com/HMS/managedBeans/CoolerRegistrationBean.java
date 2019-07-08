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

@ManagedBean(name = "CoolerRegistrationBean")
@RequestScoped
public class CoolerRegistrationBean {

	@ManagedProperty("#{loginBean.userName}")
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	private String coolertype;
	private String company;
	private String arrivaldate;
	private String depaturedate;
	private String reason;

	public String getCoolertype() {
		return coolertype;
	}

	public void setCoolertype(String coolertype) {
		this.coolertype = coolertype;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getArrivaldate() {
		return arrivaldate;
	}

	public void setArrivaldate(String arrivaldate) {
		this.arrivaldate = arrivaldate;
	}

	public String getDepaturedate() {
		return depaturedate;
	}

	public void setDepaturedate(String depaturedate) {
		this.depaturedate = depaturedate;
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

			if ((coolertype == null && coolertype.isBlank()) || company == null || arrivaldate == null
					|| depaturedate == null || reason == null) {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Please fill all the fields in the form.", "Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult = "Register_Cooler";

				return navResult;

			}

			if (checkdate(arrivaldate) && checkdate(depaturedate)) {

				System.out.println(coolertype + "" + company + "" + arrivaldate + "" + depaturedate + "" + reason);

				String status = "To_Be_approved";
				CoolerDetails ctrl = new CoolerDetails();
				FacesContext facesContext = FacesContext.getCurrentInstance();
				HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
				String curr_id = (String) session.getAttribute("curr_id");
				int result = ctrl.savecooler(curr_id, coolertype, company, arrivaldate, depaturedate, reason, status);
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
					navResult = "Register_Cooler";

				} else if (result == 2) {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Your previous request is already approved", "Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult = "Register_Cooler";

				} else {
					FacesContext context = FacesContext.getCurrentInstance();
					FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Arrival date should be prior to depature date", "Message details");
					context.addMessage(null, message);
					context.validationFailed();
					navResult = "Register_Cooler";

				}

			} else {
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Please enter Arrival date and Depature date in MM/DD/YYYY", "Message details");
				context.addMessage(null, message);
				context.validationFailed();
				navResult = "Register_Cooler";
			}
		} else {

			navResult = "login";
		}
		return navResult;
	}

	private boolean checkdate(String depaturedate2) {
		// TODO Auto-generated method stub
		String date = depaturedate2;
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
