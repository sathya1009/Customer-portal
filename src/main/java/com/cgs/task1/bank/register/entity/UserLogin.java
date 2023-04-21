package com.cgs.task1.bank.register.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task1_bank_login")
public class UserLogin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private int id;

	@Column(name = "MobileNo", nullable = false)
	private String mobileNo;
	
	@Column(name = "Password", nullable = false)
	private String password;
	
}
