package com.HMS.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.HMS.entity.*;
import com.HMS.functions.ChartFunctions;

@ManagedBean(name="chart")
@SessionScoped
public class chart {
	
	List<Wing> wings = new ArrayList<Wing>();

	public List<Wing> getWings() {
		ChartFunctions cf = new ChartFunctions();
		wings = cf.getStudentsWingWise();
		return wings;

}
}
