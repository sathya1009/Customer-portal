package com.cgs.task1.bank.register.service;

import com.cgs.task1.bank.register.entity.UserRegister;

public interface UserRegisterService {

	UserRegister addUser(UserRegister userRegister);

	UserRegister findUserByEmail(String geteMail);
	
	//UserRegister userLogin(String a, String b);

}
