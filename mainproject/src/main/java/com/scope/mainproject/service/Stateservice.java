package com.scope.mainproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scope.mainproject.model.Country;
import com.scope.mainproject.model.State;
import com.scope.mainproject.repository.StateRepository;

@Service
public class Stateservice {
	@Autowired
	private StateRepository stateRepository;
	public List<State>getstate() {
		return stateRepository.findAll();
		
	}
	public List<State> getStateBy(Country countryid){
		return stateRepository.findByCountry(countryid);
	}
	

}
