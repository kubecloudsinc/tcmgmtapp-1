package com.cisco.cstg.autotools.tests.params;

public class AppSettingsPageParameter {
	
	private String userName;
	
	private String password;
	
	private String customerName;
	
	private String expectedRole;
	
	private String url;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpectedRole() {
		return expectedRole;
	}

	public void setExpectedRole(String expectedRole) {
		this.expectedRole = expectedRole;
	}
}