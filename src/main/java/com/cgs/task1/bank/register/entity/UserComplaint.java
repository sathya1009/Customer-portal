package com.cgs.task1.bank.register.entity;

import javax.validation.constraints.Size;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "task1_user_complaint")
public class UserComplaint {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "UserId")
	private int userId;

	@Column(name = "E_Mail")
	private String email;

	@Column(name = "User_Complaints")
	@Size(max = 300)
	private String complaints;
	
	
}
