package com.revature.project03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin,Integer>{
	Admin findByEmail(String email);
}
