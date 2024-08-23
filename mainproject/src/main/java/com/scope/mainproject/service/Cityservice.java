package com.scope.mainproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scope.mainproject.model.City;
import com.scope.mainproject.model.State;
import com.scope.mainproject.repository.CityRepository;

@Service
public class Cityservice {
	@Autowired
	private CityRepository cityRepository;
	public List<City> getcity(){
		return cityRepository.findAll();
	}
	public List<City> getCityBy(State stateid){
		return cityRepository.findByState(stateid);
	}

}
