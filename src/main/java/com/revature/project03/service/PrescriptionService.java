package com.revature.project03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.revature.project03.entities.Prescriptions;
import com.revature.project03.repository.PrescriptionsRepository;

@Service
public class PrescriptionService {
	@Autowired
    private PrescriptionsRepository repository;
	
    public Prescriptions savePrescriptions(Prescriptions prescription) {
    	
        return repository.save(prescription);
    }

    public List<Prescriptions> savePrescriptionss(List<Prescriptions> prescriptions) {
        return repository.saveAll(prescriptions);
    }

    public List<Prescriptions> getPrescriptionss() {
        return repository.findAll();
    }

    public Prescriptions getPrescriptionsById(int id) {
        return repository.findById(id).orElse(null);
    }
    public List<Prescriptions> getPrescriptionsByDoctorId(int doctorId) {
        return repository.findAllByDoctorId(doctorId);
    }
    
    public List<Prescriptions> getPrescriptionsByPatientId(int patientId){
    	return repository.findAllByPatientId(patientId);
    }

    public String deletePrescriptions(int id) {
        repository.deleteById(id);
        return "Prescriptions removed !! " + id;
    }

    public Prescriptions updatePrescriptions(Prescriptions prescription) {
        Prescriptions existingPrescriptions = repository.findByPresId(prescription.getPresId());
        return repository.save(existingPrescriptions);
    }
}
