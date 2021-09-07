package com.revature.project03.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Prescriptions;

import com.revature.project03.service.PrescriptionService;
@RestController 
@CrossOrigin(origins = "*")
public class PrescriptionController {
	@Autowired
    private PrescriptionService service;

    @PostMapping("/addPrescription")
    public Prescriptions addPrescriptions(@RequestBody Prescriptions prescription) {
        return service.savePrescriptions(prescription);
    }

    @PostMapping("/addPrescriptions")
    public List<Prescriptions> addPrescriptionss(@RequestBody List<Prescriptions> prescriptions) {
        return service.savePrescriptionss(prescriptions);
    }

    @GetMapping("/allPrescriptions")
    public List<Prescriptions> findAllPrescriptionss() {
        return service.getPrescriptionss();
    }

    @GetMapping("/prescriptionById/{id}")
    public Prescriptions findPrescriptionsById(@PathVariable int id) {
        return service.getPrescriptionsById(id);
    }
    @GetMapping("/prescriptionByDoctorId/{doctorId}")
    public List<Prescriptions> findPrescriptionsByEmail(@PathVariable int doctorId) {
        return service.getPrescriptionsByDoctorId(doctorId);
    }
    @GetMapping("/allPrescriptionsByPatientId/{patientId}")
    public List<Prescriptions> findAllPrescriptionsByPatientId(@PathVariable int patientId) {
        return service.getPrescriptionsByPatientId(patientId);
    }
    
    @PutMapping("/updatePrescriptions")
    public Prescriptions updatePrescriptions(@RequestBody Prescriptions prescription) {
        return service.updatePrescriptions(prescription);
    }

    @DeleteMapping("/deletePrescriptions/{id}")
    public String deletePrescriptions(@PathVariable int id) {
        return service.deletePrescriptions(id);
    }
    

}
