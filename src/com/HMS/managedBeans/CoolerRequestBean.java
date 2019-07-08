package com.HMS.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.HMS.entity.Cooler;
import com.HMS.functions.AccessValidator;
import com.HMS.functions.CoolerRequests;

@ManagedBean(name = "CoolerRequestBean")
@ViewScoped
public class CoolerRequestBean implements Serializable {
	/**
		 * 
		 */
	@ManagedProperty("#{loginBean.userName}")
	private String uname;

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}
	private static final long serialVersionUID = 1L;
	List<Cooler> results = new ArrayList<Cooler>();

	@PostConstruct
	public void init() {
		CoolerRequests cooler = new CoolerRequests();
		results = cooler.get_tobeapproved();

	}

	public List<Cooler> getResults() {
		return results;
	}

	public String onAllow(String id) {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {
			CoolerRequests cooler = new CoolerRequests();
			cooler.confirm_cooler(id);
			navResult="Cooler_Request";
			
		}
		else
		{
			
			navResult="login";
		}
		
		
		return navResult;
	}

	public String onDeny(String id) {
		String navResult = "";

		AccessValidator validate = new AccessValidator();
		if (validate.validateuser(uname)) {

		CoolerRequests cooler = new CoolerRequests();
		cooler.deny_cooler(id);
		navResult="Cooler_Request";
		}
		else
		{
			navResult="login";
			
		}
		System.out.println(navResult);
		return navResult;

	}
}
