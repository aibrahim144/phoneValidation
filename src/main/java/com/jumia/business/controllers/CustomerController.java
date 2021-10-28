package com.jumia.business.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jumia.business.dto.JumiaFullNumber;
import com.jumia.business.services.CustomerService;

@RestController
@RequestMapping("customer")
@CrossOrigin
public class CustomerController {

	@Autowired
	CustomerService customerService;

	@GetMapping(value = "/allPhones")
	public List<JumiaFullNumber> getActionsPerDay(@RequestParam(value = "country", required = false) String country,
			@RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "offset", required = false) String offset,
			@RequestParam(value = "next", required = false) String next) {

		List<JumiaFullNumber> allFullNumbers = null;
		List<JumiaFullNumber> allFullNumbersFiltered = null;

		// -validate params and setting default values.

		// --- filtered data pagination

		int filterdOffset = offset != null ? Integer.parseInt(offset) : 0;
		int filterdNext = next != null ? Integer.parseInt(next) : 0;

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
			
			if(allFullNumbers.size() < 1) {
				return allFullNumbers;
			}

			
			// for pagination of filtered data
			if (country != null && state != null && filterdNext != 0 ) {
				if(filterdNext > allFullNumbers.size() || (filterdOffset + filterdNext) > allFullNumbers.size()) {
					filterdNext = allFullNumbers.size();
				}
				if(filterdOffset >= allFullNumbers.size()) {
					filterdOffset = 0;
				}
				allFullNumbersFiltered = allFullNumbers.subList(filterdOffset, filterdNext);
				allFullNumbersFiltered.get(0).setTotal("" + allFullNumbers.size());
				
				return allFullNumbersFiltered;
			}

		} catch (Exception e) {
			System.out.println("there is an error while getting data.");
			e.printStackTrace();
		}

		return allFullNumbers;
	}

}
