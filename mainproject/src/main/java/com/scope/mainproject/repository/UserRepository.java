package com.scope.mainproject.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scope.mainproject.model.User;

@Repository
public interface  UserRepository extends JpaRepository<User,Integer> {
	public User findByVerificationcode(String code);
		public  User findByEmail(String email);
		public User findByEmailAndPassword(String email,String password);

}
