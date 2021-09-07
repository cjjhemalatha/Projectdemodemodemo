package com.revature.project03.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.Prescriptions;

public interface PrescriptionsRepository extends JpaRepository<Prescriptions,Integer>{
	Prescriptions findByPresId(int id);
	List<Prescriptions> findAllByDoctorId(int doctorId);
	List<Prescriptions> findAllByPatientId(int patientId);
}
