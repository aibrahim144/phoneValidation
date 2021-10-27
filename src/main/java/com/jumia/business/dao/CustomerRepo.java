package com.jumia.business.dao;

import java.util.List;

import com.jumia.business.dto.PhoneTotal;

public interface CustomerRepo {

	public List<PhoneTotal> getAllCustomersPhones(String offset, String next);
}
