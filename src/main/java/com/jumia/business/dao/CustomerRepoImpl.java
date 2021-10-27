package com.jumia.business.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.juma.business.models.Customer;
import com.jumia.business.dto.PhoneTotal;

@Repository
public class CustomerRepoImpl implements CustomerRepo {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// getting all phones from database.
	@Override
	public List<PhoneTotal> getAllCustomersPhones(String offset, String next) {
		List<PhoneTotal> phones = null;

		String statement = "SELECT phone, COUNT(*) OVER() AS TOTAL_ROWS FROM customer LIMIT " + offset + "," + next;

		if (next.equals("max")) {
			statement = "SELECT phone, COUNT(*) OVER() AS TOTAL_ROWS FROM customer";
		}
		try {
			phones = jdbcTemplate.query(statement, (rs, rowNum) -> new PhoneTotal(rs.getString(1), rs.getString(2)));
		} catch (Exception e) {
			System.out.println("there is an error while getting data from customer repository.");
			e.printStackTrace();
		}

		return phones;
	}
}
