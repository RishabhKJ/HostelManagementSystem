package com.HMS.managedBeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "LogoutBean")
@SessionScoped
public class LogoutBean {
	
	public String logout()
	{
		String navResult="";
		FacesContext context = FacesContext.getCurrentInstance();
     	context.getExternalContext().invalidateSession();
     	navResult= "login";		
     	return navResult;
	}

}
