package com.jumia.business.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.juma.business.models.Customer;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// getting all phones from database.
	@Override
	public List<String> getAllCustomersPhones(String offset, String next) {
		List<String> phones = null;

		String statement = "SELECT phone FROM customer LIMIT " + offset + "," + next;

		if (next.equals("max")) {
			statement = "SELECT phone FROM customer";
		}
		try {
			phones = jdbcTemplate.query(statement, (rs, rowNum) -> new String(rs.getString(1)));
		} catch (Exception e) {
			System.out.println("there is an error while getting data from customer repository.");
			e.printStackTrace();
		}

		return phones;
	}
}
