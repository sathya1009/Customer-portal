package com.cgs.task1.bank.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgs.task1.bank.register.entity.UserRegister;

@Repository
public interface UserRegisterRepo extends JpaRepository<UserRegister, Integer> {

	UserRegister findByEmail(String email);

	boolean existsByMobileNo(String mobileNo);

	boolean existsByPassword(String password);

	
}