package com.HMS.functions;

public class AccessValidator {

	public boolean validateuser(String uname) {
		
		if (uname != null && !uname.isEmpty() && uname.equals("admin")) {
			
			return true;
		}
		else
			return false;
		
	}

	public boolean validateuser1(String uname) {
		// TODO Auto-generated method stub

		if (uname != null && !uname.isEmpty() && uname != "admin") {
			
			return true;
		}
		else
			return false;
	}
	
	
	
	
	

}
