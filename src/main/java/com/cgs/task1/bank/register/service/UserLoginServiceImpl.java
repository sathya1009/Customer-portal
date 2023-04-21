package com.cgs.task1.bank.register.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgs.task1.bank.register.entity.UserLogin;
import com.cgs.task1.bank.register.repository.LoginRepository;

@Service
public class UserLoginServiceImpl implements UserLoginService{
	
	@Autowired
	private LoginRepository loginRepo;

	@Override
	public UserLogin addLoginUser(UserLogin userLogin) {
		UserLogin usr = this.loginRepo.save(userLogin);
		return usr;
	}

}
