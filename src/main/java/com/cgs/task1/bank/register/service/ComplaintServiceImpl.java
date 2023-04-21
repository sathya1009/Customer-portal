package com.cgs.task1.bank.register.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgs.task1.bank.register.customexception.NoSuchDataException;
import com.cgs.task1.bank.register.entity.UserComplaint;
import com.cgs.task1.bank.register.repository.ComplaintRepository;

@Service
public class ComplaintServiceImpl implements ComplaintService {

	@Autowired
	private ComplaintRepository complaintRepo;

	List<UserComplaint> list = new ArrayList<UserComplaint>();


	@Override
	public UserComplaint addUserComplaint(UserComplaint userComplaint) {
		UserComplaint user = this.complaintRepo.save(userComplaint);
		return user;
	}

	@Override
	public List<UserComplaint> getUsertDetails() {
		List<UserComplaint> user = this.complaintRepo.findAll();
		if (user.isEmpty()) {
			throw new NoSuchDataException("complaints is empty");
		}
		return user;
	}

	@Override
	public UserComplaint getUserComplaintData(Integer id) {

		return this.complaintRepo.findById(id).orElseThrow(() -> new NoSuchDataException("Invalid userId"));
	}
	public UserComplaint updateComplaint(Integer id, UserComplaint complaint) {
		complaint.setUserId(id);
		UserComplaint userComplaint = complaintRepo.save(complaint);
		return userComplaint;
	}

	@Override
	public Optional<UserComplaint> deleteComplaintById(Integer id) {
		Optional<UserComplaint> complaint = complaintRepo.findById(id);
		complaintRepo.deleteById(id);
		return complaint;
		
}
}
