package com.cgs.task1.bank.register.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
@Table(name = "TASK1_REGISTER")
public class UserRegister {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "User_Id")
	private int userId;

	@Column(name = "FirstName")
	@NotNull()
	@Size(min = 3, message = "pls enter the firstname")
	private String firstName;

	@Column(name = "LastName")
	@NotEmpty()
	@Size(min = 3, message = "pls enter the last name")
	private String lastName;

	@Column(name = "Mobile_No")
	@NotBlank()
	@Size(min = 10, max = 10, message = "pls enter the valid mobile number")
	private String mobileNo;

	@Column(name = "EMAIL")
	@NotBlank(message = "pls enter the E-Mail")
	@Email
	private String email;

	@Column(name = "Date_of_Birth")
	@NotBlank(message = "pls enter the date of birth")
	private String dob;

	@Column(name = "UserName")
	@NotBlank(message = "pls enter the username")
	private String userName;

	@Column(name = "Password")
	@NotBlank()
//	@Size(min = 6, max = 16, message = "pls enter the password")
	private String password;

}
