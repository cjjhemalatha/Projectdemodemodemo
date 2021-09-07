package com.revature.project03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.project03.entities.Admin;
import com.revature.project03.repository.AdminRepository;
@Service
public class AdminService {
	
	@Autowired
	private AdminRepository repository;
	
	public Admin findbyEmail(String email) {
		return repository.findByEmail(email);
	}

}
