package com.jumia.business.dto;

public class JumiaFullNumber {

	private String state;
	
	private String country;
	
	private String phone;
	
	private String code;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public JumiaFullNumber(String country, String state, String phone, String code) {
		this.country = country;
		this.state = state;
		this.phone = phone;
		this.code = code;
	}
	 
	public JumiaFullNumber() {
		
	}
		
	
	
	
}
