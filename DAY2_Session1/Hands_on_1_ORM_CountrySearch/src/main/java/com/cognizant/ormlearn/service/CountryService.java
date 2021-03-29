package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getAllCountries(){
		
		return countryRepository.findAll();
		
	}

	
	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException{
		Optional<Country> result = countryRepository.findById(countryCode);
		
		if (!result.isPresent()) {
			throw new CountryNotFoundException("Country not found");
		}
		
		Country country = result.get();
		return country;
	}
	
	/*
	 * @Transactional public List<Country> findCountryByName(String name) {
	 * List<Country> result = countryRepository.findByNameContaining(name);
	 * 
	 * return result; }
	 * 
	 * @Transactional public List<Country> findCountryByNameAsc(String name) {
	 * List<Country> result =
	 * countryRepository.findByNameContainingOrderByNameAsc(name);
	 * 
	 * return result; }
	 * 
	 * @Transactional public List<Country> findCountryByNameStartingWith(String
	 * name) { List<Country> result = countryRepository.findByNameStartsWith(name);
	 * 
	 * return result; }
	 */
	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}
	
	@Transactional
	public void updateCountry(String code, String name) {
		Optional<Country> updateCountry = countryRepository.findById(code);
		
		Country country = updateCountry.get();
		country.setName(name);
		
		countryRepository.save(country);
	}
	
	@Transactional
	public void deleteCountry(String code) {
		countryRepository.deleteById(code);
		
	}
	
}
