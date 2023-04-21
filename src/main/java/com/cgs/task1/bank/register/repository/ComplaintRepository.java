package com.cgs.task1.bank.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cgs.task1.bank.register.entity.UserComplaint;

@Repository
public interface ComplaintRepository extends JpaRepository<UserComplaint, Integer> {

}
