package com.revature.project03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.LoginRoute;

public interface LoginRouteRepository extends JpaRepository<LoginRoute, Integer>{
	LoginRoute findByUserEmail(String email);
}
