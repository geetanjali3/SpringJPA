package com.cognizant.ormlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;

	public static void main(String[] args) throws CountryNotFoundException {
		// SpringApplication.run(OrmLearnApplication.class, args);
		// LOGGER.info("Inside main");
		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		testGetAllCountries();
		testGetAllCountriesTest();
		testAddCountry();
		testUpdateCountry();
		testDeleteCountry();
	}

	// METHOD TO TEST GETALLCOUNTRIES() IN COUNTRY SERVICE
	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

	}

	// METHOD TO TEST FINDCOUNTRYBYCODE() IN COUNTRY SERVICE
	private static void testGetAllCountriesTest() throws CountryNotFoundException {

		LOGGER.info("Start");

		Country country = countryService.findCountryByCode("IN");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	// METHOD TO TEST ADDCOUNTRY() IN COUNTRY SERVICE
	private static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("start");
		Country country = new Country("JP", "Japan");
		countryService.addCountry(country);
		Country countryFound = countryService.findCountryByCode("JP");
		LOGGER.debug("Country:{}", countryFound);
		LOGGER.info("End");

	}

	// METHOD TO TEST UPDATECOUNTRY() IN COUNTRY SERVICE
	private static void testUpdateCountry() throws CountryNotFoundException {
		LOGGER.info("start");

		countryService.updateCountry("KR", "Republic of Korea");
		Country countryFound = countryService.findCountryByCode("KR");
		LOGGER.debug("Country:{}", countryFound);
		LOGGER.info("End");

	}

	// METHOD TO TEST DELETECOUNTRY() IN COUNTRY SERVICE
	private static void testDeleteCountry() throws CountryNotFoundException {
		LOGGER.info("start");

		Country countryFound = countryService.findCountryByCode("JP");
		LOGGER.debug("Country:{}", countryFound);
		countryService.deleteCountry("JP");
		testGetAllCountries();
		LOGGER.info("End");

	}

}
