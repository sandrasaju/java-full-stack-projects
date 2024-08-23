package com.scope.mainproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.mainproject.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer>{
	

}
