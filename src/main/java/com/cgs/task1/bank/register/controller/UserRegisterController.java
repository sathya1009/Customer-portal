package com.cgs.task1.bank.register.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cgs.task1.bank.register.entity.UserComplaint;
import com.cgs.task1.bank.register.entity.UserLogin;
import com.cgs.task1.bank.register.entity.UserRegister;
import com.cgs.task1.bank.register.repository.LoginRepository;
import com.cgs.task1.bank.register.repository.UserRegisterRepo;
import com.cgs.task1.bank.register.service.ComplaintService;
import com.cgs.task1.bank.register.service.UserLoginService;
import com.cgs.task1.bank.register.service.UserRegisterService;

import lombok.extern.slf4j.Slf4j;

@Controller
public class UserRegisterController {

	@Autowired
	private UserRegisterService userService;

	@Autowired
	private ComplaintService complaintService;

	@Autowired
	private UserRegisterRepo registerRepo;

	@Autowired
	private LoginRepository loginRepo;

	@Autowired
	private UserLoginService userLoginService;
	
	
  //Screen Navigation

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homepage() {
		return "home";
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String registerpage() {
		return "register";
	}

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public String dashboardpage() {
		return "dashboard";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginpage() {
		return "login";
	}

	@RequestMapping(value = "/complaintregister", method = RequestMethod.GET)
	public String complaintregisterpage() {
		return "complaintregister";
	}

	@GetMapping("/viewcomplaint")
	public String viewcomplaintregister() {
		return "viewcomplaint";
	}
	
	
	

	
	//Register

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/addNewUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody UserRegister userRegister) {
		if (registerRepo.existsByMobileNo(userRegister.getMobileNo())) {
//			return new ResponseEntity<>("Mobile Number is already exist!", HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(Collections.singletonMap("message", "Mobile Number is already exist!"), HttpStatus.BAD_REQUEST);

		}

		JSONObject response = new JSONObject();
		Map<String, Object> responseData = null;
		ResponseEntity<?> responseEntity = null;

		UserRegister userAdded = this.userService.addUser(userRegister); // service invoked

		response = new JSONObject();
		if (userAdded != null) {
			response.put("RESP_STATUS", "SUCCESS");
			response.put("RESP_CODE", "000");
			response.put("FirstName", userAdded.getFirstName());
			response.put("LastName", userAdded.getLastName());
			response.put("MobileNo", userAdded.getMobileNo());
			response.put("E-Mail", userAdded.getEmail());
			response.put("Date_of_Birth", userAdded.getDob());
			response.put("UserName", userAdded.getUserName());
			response.put("Password", userAdded.getPassword());
			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.OK);

		} else {
			response.put("RESP_STATUS", "FAIL");
			response.put("RESP_CODE", "001");
			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;

	}

	
		//Login using mail
	
		@PostMapping("/userlogin")
		public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, 
				Model model) {
	
			System.out.println("User login data in controller :" + email);
	
	
			   try {
			        UserRegister existingUser = userService.findUserByEmail(email);
			        if (existingUser != null && existingUser.getPassword().equals(password)) {
			        	
			        	
			        	System.err.println("no issue");
			            return "dashboard"; 
			        } else {
			            model.addAttribute("errorMessage", "Invalid username or password");
			            System.err.println("have issue in backend");
			            return "login";
			        }
			    } catch (Exception e) {
			        model.addAttribute("errorMessage", "There was an error processing your request. Please try again later.");
			        System.err.println("have issue in server");
			        
			        return "login"; 
			    }
		}
	
//	
//	@PostMapping("/userlogin")
//	public String loginUser(@RequestParam("email") String email,  @RequestParam("hashedPasswordValue") String hashedPassword, Model model) {
//	    
//	    // Check if email and hashedPassword are not empty
////	    if (email.isEmpty() || hashedPassword.isEmpty()) {
////	        model.addAttribute("errorMessage", "Invalid username or password");
////	        return "login";
////	    }
//
//		String number ="8cedea94d3d2878f2318d49c1feeca5d9cf1115656451af9537b449fb31a7e18";
//	    // Get the user from the database by email
//	    UserRegister existingUser = userService.findUserByEmail(email);
//	    // Check if user exists and the hashed password matches
//
//	    System.err.println("hashedpassword: " + hashedPassword);
//	    System.err.println("databasehashedpassword: " +  existingUser.getPassword());
//
//		System.err.println("no issue");
//		
//	    
//	    if (existingUser != null && existingUser.getPassword().equals(number)) {
//	   
//	        // Set the user's session here
//	        return "dashboard";
//	    } else {
//	        model.addAttribute("errorMessage", "Invalid username or password");
//	        return "login";
//	    }
//	}

	
	
	//Login using phonenumber

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/banklogin")
	public ResponseEntity<?> addLoginUser(@RequestBody UserLogin userLogin, BindingResult result, Model model) {

		if (loginRepo.existsByMobileNo(userLogin.getMobileNo())) {
			return new ResponseEntity<>("Mobile Number is already exist!", HttpStatus.BAD_REQUEST);
		}

		if (!(registerRepo.existsByMobileNo(userLogin.getMobileNo()))) {
			return new ResponseEntity<>("Mobile Number is not registered pls register mobile number",
					HttpStatus.BAD_REQUEST);
		}

		if (!(registerRepo.existsByPassword(userLogin.getPassword()))) {
			return new ResponseEntity<>("password is not correct pls enter valid valid password",
					HttpStatus.BAD_REQUEST);
		}

		JSONObject response = null;
		Map<String, Object> responseData = null;
		ResponseEntity<?> responseEntity = null;

		if (result.hasErrors()) {
			System.out.println("errors");
		}

		UserLogin userAdded = this.userLoginService.addLoginUser(userLogin); // service invoked

		response = new JSONObject();
		if (userAdded != null) {
			model.addAttribute("msg", "User Login done Successfully!");
			response.put("RESP_STATUS", "SUCCESS");
			response.put("RESP_CODE", "000");
			response.put("mobile number", userAdded.getMobileNo());
			response.put("password", userAdded.getPassword());

			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.OK);
		} else {
			model.addAttribute("msg", "User Login Failed!");
			response.put("RESP_STATUS", "FAIL");
			response.put("RESP_CODE", "001");
			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	//submitting complaint

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/uerscomplaint")
	public ResponseEntity<?> addUserComplaint(@Valid @RequestBody UserComplaint userComplaint, Model model) {

		JSONObject response = null;
		Map<String, Object> responseData = null;
		ResponseEntity<?> responseEntity = null;

		UserComplaint userAdded = this.complaintService.addUserComplaint(userComplaint);

		response = new JSONObject();
		if (userAdded != null) {
			model.addAttribute("msg", "User complaint done Successfully!");
			response.put("RESP_STATUS", "SUCCESS");
			response.put("RESP_CODE", "000");
			response.put("UserId", userAdded.getUserId());
			response.put("E_mail", userAdded.getEmail());
			response.put("UserComplaint", userAdded.getComplaints());
			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.OK);

		} else {
			model.addAttribute("msg", "User complaint Failed!");
			response.put("RESP_STATUS", "FAIL");
			response.put("RESP_CODE", "001");
			responseData = response.toMap();
			responseEntity = new ResponseEntity(responseData, HttpStatus.BAD_REQUEST);
		}
		return responseEntity;
	}
	
	
	//viewing complaints

	@GetMapping("/getusercomplaints")
	public ResponseEntity<List<UserComplaint>> getUsertDetails() {
		return ResponseEntity.ok(complaintService.getUsertDetails());
	}

	@GetMapping("/getUserData/{id}")
	public UserComplaint getUserComplaintData(@PathVariable Integer id) {
		return this.complaintService.getUserComplaintData(id);
	}
//	@PutMapping("updateComplaint/{id}")
//	public ResponseEntity<?> updateComplaint(@PathVariable Integer id, @RequestBody UserComplaint userComplaint) {
//		UserComplaint complaint = complaintService.updateComplaint(id, userComplaint);
//		return new ResponseEntity<UserComplaint>(complaint,HttpStatus.OK);
//	}
	
	@PutMapping("updateComplaint/{id}")
	public ResponseEntity<?> updateComplaint(@PathVariable Integer id, @RequestBody UserComplaint userComplaint) {
	    UserComplaint updatedComplaint = complaintService.updateComplaint(id, userComplaint);
	    if (updatedComplaint == null) {
	        return new ResponseEntity<>("No complaint found for id: " + id, HttpStatus.NOT_FOUND);
	    } else {
	        return new ResponseEntity<>(updatedComplaint, HttpStatus.OK);
	    }
	}


//	@DeleteMapping("deleteComplaint/{id}")
//	public ResponseEntity<?> deleteComplaintById(@PathVariable Integer id) {
//		Optional<UserComplaint> complaint =  complaintService.deleteComplaintById(id);
//		return new ResponseEntity<Optional<UserComplaint>>(complaint,HttpStatus.OK);
//				}
//			
//			}
	
	@DeleteMapping("deleteComplaint/{id}")
	public ResponseEntity<String> deleteComplaintById(@PathVariable Integer id) {
	    Optional<UserComplaint> complaint =  complaintService.deleteComplaintById(id);
	    if (complaint.isPresent()) {
	        return ResponseEntity.ok("User complaint with ID " + id + " was deleted.");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User complaint with ID " + id + " was not found.");
	    }
	}
}



