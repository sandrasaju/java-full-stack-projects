package com.scope.mainproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.mainproject.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer>{
}
