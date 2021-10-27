package com.jumia.Jumiapay;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jumia.business.dao.CustomerRepo;
import com.jumia.business.dto.JumiaFullNumber;
import com.jumia.business.dto.PhoneTotal;
import com.jumia.business.services.CustomerService;
import com.jumia.business.util.InternationalPhone;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class JumiapayApplicationTests {

	@Autowired
	CustomerService customerService;

	@Autowired
	InternationalPhone internationalPhoneHelper;

	@Autowired
	CustomerRepo customerRepository;

	@Test
	void allPhonesNoParams() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8090/customer/allPhones");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
	}

	@Test
	void allPhonesParamsNoOffset() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet("http://localhost:8090/customer/allPhones?state=valid&country=Cameroon");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
	}

	@Test
	void allPhonesParamsOffset() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(
				"http://localhost:8090/customer/allPhones?state=valid&country=Cameroon&offset=0&next=10");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
	}

	@Test
	void PhonesParamsNotValid() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(
				"http://localhost:8090/customer/allPhones?state=notValid&country=unknown&offset=0");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
		assertThat(httpResponse.getStatusLine().getStatusCode()).isEqualTo(200);
	}

	@Test
	void getCustomersPhones() {

		List<JumiaFullNumber> expectedFullNumbers = new ArrayList<JumiaFullNumber>();
		expectedFullNumbers.add(new JumiaFullNumber("Morocco", "valid", "698054317", "(212)"));
		expectedFullNumbers.add(new JumiaFullNumber("Morocco", "valid", "691933626", "(212)"));
		expectedFullNumbers.add(new JumiaFullNumber("Morocco", "valid", "633963130", "(212)"));
		expectedFullNumbers.add(new JumiaFullNumber("Morocco", "valid", "654642448", "(212)"));

		List<JumiaFullNumber> allFullNumbers = customerService.getCustomersPhones("Morocco", "valid", "0", "10");
		assertEquals(expectedFullNumbers.toArray().length, allFullNumbers.toArray().length);
	}

	@Test
	void getCountryByPhone() {

		String expectedCountryName = "Morocco";
		String countryName = internationalPhoneHelper.getCountryByPhone("(212) 654642448");
		assertEquals(expectedCountryName, countryName);
	}

	@Test
	void getAllCustomersPhones() {

		List<PhoneTotal> allPhones = customerRepository.getAllCustomersPhones("0", "max");
		assertTrue(!allPhones.isEmpty());
	}

}
