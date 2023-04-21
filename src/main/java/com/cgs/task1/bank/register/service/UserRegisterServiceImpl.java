package com.cgs.task1.bank.register.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
//import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.cgs.task1.bank.register.customexception.NoSuchDataException;
import com.cgs.task1.bank.register.entity.UserLogin;
import com.cgs.task1.bank.register.entity.UserRegister;
import com.cgs.task1.bank.register.repository.UserRegisterRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

	@Autowired
	private UserRegisterRepo repo;
	
	@Autowired
	EntityManager entityManager;

	@Override
	public UserRegister addUser(UserRegister userRegister) {

//		String hassedpass =BCrypt.hashpw(userRegister.getPassword(), BCrypt.gensalt());
//		userRegister.setPassword(hassedpass);
		
	
			System.out.println("Save");
			UserRegister user = this.repo.save(userRegister); // data saved
			return user;
		

	}

//	@Override
//	public UserRegister findUserByEmail(String geteMail) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public UserRegister findUserByEmail(String geteMail) {
		return repo.findByEmail(geteMail);
	}
//
//	@Override
//	public UserRegister userLogin(String uName , String pass) {
//
//		System.out.println("User login data in impl :" + uName);
//
//		 UserRegister userReg = null;
//
//		//String uName = userRegister.getUserName();
//		//String pass = userRegister.getPassword();
//
//		if (uName.isEmpty() || uName.equalsIgnoreCase(null)) {
//
//			System.out.println("Invalid username");
//		}
//
//		else if (pass.isEmpty() || pass.equalsIgnoreCase(null)) {
//
//			System.out.println("Invalid password");
//			
//		}else {
//			
//				
//			try {
//				userReg = this.repo.findByUsername(uName, pass);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			
//			System.out.println("Fetched result :"+userReg);
//			
//			
//						
//			
//		}
//
//		// TODO Auto-generated method stub
//		return userReg;
//	}

}
