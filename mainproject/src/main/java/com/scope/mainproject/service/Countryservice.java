package com.scope.mainproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scope.mainproject.model.Country;
import com.scope.mainproject.repository.CountryRepository;

@Service
public class Countryservice {
	@Autowired
	private CountryRepository countryRepository;
	
	public List<Country> countrylist(){
			return countryRepository.findAll();
	}

}
