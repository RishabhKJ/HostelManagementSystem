package com.HMS.managedBeans;

import com.HMS.functions.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean {

	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateUserLogin() {
		String navResult = "";
		System.out.println("Entered Username is= " + userName + ", password is= " + password);

		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("curr_id", userName);
		StudentCredentials ctrl = new StudentCredentials();
		if (ctrl.CheckStudent(userName, password)) {
			if (userName.equals("admin")) {
				navResult = "Admin_Homepage";
			} else
				navResult = "Student_Homepage";
		} else {
			{
				FacesContext context = FacesContext.getCurrentInstance();
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Please enter correct username and password", "Message details");
				context.addMessage(null, message);
				context.validationFailed();

				navResult = "index";
			}
		}
		return navResult;
	}
}