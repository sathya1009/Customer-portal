package com.cgs.task1.bank.register.service;

import java.util.List;
import java.util.Optional;

import com.cgs.task1.bank.register.entity.UserComplaint;

public interface ComplaintService {

	UserComplaint getUserComplaintData(Integer id);

	UserComplaint addUserComplaint(UserComplaint userComplaint);

	List<UserComplaint> getUsertDetails();

	Optional<UserComplaint> deleteComplaintById(Integer id);

	UserComplaint updateComplaint(Integer id, UserComplaint complaint);

}
