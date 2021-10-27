package com.jumia.business.dto;

public class PhoneTotal {
	
	private String phone;
	private String total;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	public PhoneTotal(String phone, String total) {
		this.phone = phone;
		this.total = total;
	}
	public PhoneTotal() {
		
	}

	
}
