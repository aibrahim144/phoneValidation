package com.jumia.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.business.dto.JumiaFullNumber;
import com.jumia.business.services.CustomerService;

@RestController
@RequestMapping("customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping(value = "/allPhones")
	public List<JumiaFullNumber> getActionsPerDay(@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "next", required = false) String next) {

		List<JumiaFullNumber> allFullNumbers = null;

		// -validate params and setting default values.

		country = country != null ? country : null;
		state = state != null ? state : null;
		offset = offset != null ? offset : "0";
		next = next != null ? next : "max";

		if (country != null && state != null) {
			offset = "0";
			next = "max";
		}
		
		// getting data from customer service

		try {
			allFullNumbers = customerService.getCustomersPhones(country, state, offset, next);
		} catch (Exception e) {
			System.out.println("there is an error while getting data.");
			e.printStackTrace();
		}

		return allFullNumbers;
	}

}
