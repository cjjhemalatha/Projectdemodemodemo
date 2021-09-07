package com.revature.project03.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.revature.project03.entities.DoctorLeave;

public interface DoctorLeaveRepository extends JpaRepository<DoctorLeave, Integer>{
	
	String deleteByDoctorId(int doctorId);
	
	List<DoctorLeave> findAllByDoctorId(int doctorId);
	
	List<DoctorLeave> findAllByLeaveDate(Date date);

}
