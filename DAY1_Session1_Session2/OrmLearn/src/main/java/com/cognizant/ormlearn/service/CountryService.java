package com.cognizant.ormlearn.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.CountryRepository;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	
	@Autowired
	private CountryRepository countryRepository;
	
	
	@Transactional
	//METHOD TO GET ALL THE COUNTRIES PRESENT
		
	public List<Country> getAllCountries()
	{
		List<Country> countryList=(List<Country>)countryRepository.findAll();
		return countryList;
	}
	
	
	
	@Transactional
	//METHOD TO FIND A COUNTRY BY COUNTRY CODE
	
	public Country findCountryByCode(String countryCode) 
	{
		
		
		try
		{
			Optional<Country> result = countryRepository.findById(countryCode);
			if (result.isPresent()) {
				Country country = result.get();
				return country;
				
			}
			throw new CountryNotFoundException("Country Not Found");
		}
		catch(CountryNotFoundException e)
		{
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	
	@Transactional
	//METHOD TO ADD A COUNTRY
	public void addCountry(Country country)
	{
		countryRepository.save(country);
	}
	
	
	@Transactional
	
	//METHOD TO UPDATE THE NAME OF A COUNTRY BASED ON COUNTRY CODE
	public void updateCountry(String code, String name)
	{
		Optional<Country> findCountryById = countryRepository.findById(code);
		if(findCountryById.isPresent())
		{
			Country country = findCountryById.get();
			country.setName(name);
			countryRepository.save(country);
		}
	}
	
	@Transactional
	//METHOD TO DELETE A COUNTRY BASED ON  COUNTRY CODE
	public void deleteCountry(String code)
	{
		countryRepository.deleteById(code);
	}

}
