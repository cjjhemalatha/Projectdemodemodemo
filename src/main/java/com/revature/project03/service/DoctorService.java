package com.revature.project03.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.project03.entities.Doctor;
import com.revature.project03.repository.DoctorRepository;

@Service
public class DoctorService {
	@Autowired
    private DoctorRepository repository;
	@Autowired
	private EmailService emailService;
	
    public Doctor saveDoctor(Doctor doctor) {
    	String message = "hi, your login details are, UserName:  "+doctor.getEmail()+"  Password:  "+doctor.getPassword();
    	emailService.sendSimpleEmail(doctor.getEmail(), message, "your login credentials");
    
        return repository.save(doctor);
    }

    public List<Doctor> saveDoctors(List<Doctor> doctors) {
        return repository.saveAll(doctors);
    }

    public List<Doctor> getDoctors() {
        return repository.findAll();
    }

    public Doctor getDoctorById(int id) {
        return repository.findById(id).orElse(null);
    }
    public Doctor getDoctorByEmail(String email) {
        return repository.findByEmail(email);
    }
    

    public String deleteDoctor(int id) {
        repository.deleteById(id);
        return "Doctor removed !! " + id;
    }

    public Doctor updateDoctor(Doctor doctor) {
        Doctor existingDoctor = repository.findByEmail(doctor.getEmail());
        existingDoctor.setFirstName(doctor.getFirstName());
        existingDoctor.setLastName(doctor.getLastName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setPhno(doctor.getPhno());
        existingDoctor.setPassword(doctor.getPassword());
       

        return repository.save(existingDoctor);
    }
}