package com.revature.project03.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	Doctor findByEmail(String email);
}
