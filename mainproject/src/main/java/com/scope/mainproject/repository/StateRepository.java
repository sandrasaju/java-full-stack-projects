package com.scope.mainproject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.mainproject.model.Country;
import com.scope.mainproject.model.State;

@Repository
public interface StateRepository extends JpaRepository<State,Integer> {
	List<State>findByCountry(Country countryid);

}
