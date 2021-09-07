package com.revature.project03.repository;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
	
	public List<Appointment> findAllByapplicationDate(Date date);
}