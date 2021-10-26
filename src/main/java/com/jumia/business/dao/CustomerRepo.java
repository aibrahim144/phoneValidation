package com.jumia.business.dao;

import java.util.List;

public interface CustomerRepo {

	public List<String> getAllCustomersPhones(String offset, String next);
}
