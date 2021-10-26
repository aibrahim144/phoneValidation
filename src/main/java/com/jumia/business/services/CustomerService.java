package com.jumia.business.services;

import java.util.List;

import com.jumia.business.dto.JumiaFullNumber;

public interface CustomerService {

	public List<JumiaFullNumber> getCustomersPhones(String country, String state, String offset, String next);
}
