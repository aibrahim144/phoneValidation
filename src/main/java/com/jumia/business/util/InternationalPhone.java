package com.jumia.business.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

@Component
public class InternationalPhone {
	
	private Map<String, String> countryCodesLookUp
    = new HashMap<String, String>();
	
	public InternationalPhone() {
		
		//setting up REGEX.

		countryCodesLookUp.put("\\(237\\)\\ ?[2368]\\d{7,8}$", "Cameroon");
		countryCodesLookUp.put("\\(251\\)\\ ?[1-59]\\d{8}$", "Ethiopia");
		countryCodesLookUp.put("\\(212\\)\\ ?[5-9]\\d{8}$", "Morocco");
		countryCodesLookUp.put("\\(258\\)\\ ?[28]\\d{7,8}$", "Mozambique");
		countryCodesLookUp.put("\\(256\\)\\ ?\\d{9}$", "Uganda");
	}
	
	// validate phone and getting country name.
	public String getCountryByPhone(String phoneNumber) {
		
	    for (int i = 0; i < 5; i++) {
	    	
	    	String key = (String) countryCodesLookUp.keySet().toArray()[i];
	    	Pattern pattern = Pattern.compile((String) countryCodesLookUp.keySet().toArray()[i], Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(phoneNumber);
		    boolean matchFound = matcher.find();
		    if(matchFound)
		    {
		    	return countryCodesLookUp.get(key);
		    }
		}
	    return "unknown";
	}

}
