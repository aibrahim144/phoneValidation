package com.jumia.business.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jumia.business.dao.CustomerRepo;
import com.jumia.business.dto.JumiaFullNumber;
import com.jumia.business.util.InternationalPhone;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerRepository;

	@Autowired
	InternationalPhone internationalPhoneHelper;

	@Override
	@Transactional
	public List<JumiaFullNumber> getCustomersPhones(String country, String state, String offset, String next) {

		// Initialize needed variables.
		JumiaFullNumber jumiaFullNumber = null;
		List<JumiaFullNumber> jumiaFullNumberList = new ArrayList<JumiaFullNumber>();
		List<JumiaFullNumber> jumiaFullNumberListFilterd = new ArrayList<JumiaFullNumber>();
		List<String> allPhones = null;

		// getting phones from database.
		try {
			allPhones = customerRepository.getAllCustomersPhones(offset, next);
		} catch (Exception e) {
			System.out.println("there is an error while getting data from database.");
			e.printStackTrace();
		}

		// setting up the DTO object.
		for (String phone : allPhones) {

			// getting country name based on REGEX.
			String countryName = internationalPhoneHelper.getCountryByPhone(phone);

			if (countryName != null) {
				jumiaFullNumber = new JumiaFullNumber();
				jumiaFullNumber.setCountry(countryName);
				jumiaFullNumber.setPhone(phone.split(" ")[1]);
				jumiaFullNumber.setState("valid");
				if (countryName == "unknown") {
					jumiaFullNumber.setState("notValid");
				}
				jumiaFullNumber.setCode(phone.split(" ")[0]);

				jumiaFullNumberList.add(jumiaFullNumber);

				if (jumiaFullNumber.getCountry().equals(country) && jumiaFullNumber.getState().equals(state)) {
					jumiaFullNumberListFilterd.add(jumiaFullNumber);
				}
			}
		}

		if (country == null && state == null) {
			return jumiaFullNumberList;
		}

		return jumiaFullNumberListFilterd;

	}

}
