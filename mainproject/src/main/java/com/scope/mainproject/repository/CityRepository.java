package com.scope.mainproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.mainproject.model.City;
import com.scope.mainproject.model.State;

@Repository
public interface CityRepository extends JpaRepository<City,Integer>{
		List<City>findByState(State stateid);
}
