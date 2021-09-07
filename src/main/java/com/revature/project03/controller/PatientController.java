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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Family;
import com.revature.project03.entities.Patient;
import com.revature.project03.exception.ResourceNotFoundException;
import com.revature.project03.service.PatientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/patient")

public class PatientController {
	
	@Autowired
	private PatientService patientService;
	

	

	@GetMapping("/family/{id}")
	public  Family getFamilyMemberById(@PathVariable(value = "id") Integer familyMemberId) throws ResourceNotFoundException
	{
		return patientService.getFamilyMemberById(familyMemberId);
	}

	@PostMapping("{patientid}/family/addmember")
	public Family createFamilyMember(@RequestBody Family familyMember, @PathVariable(value = "patientid") Integer patientId) throws ResourceNotFoundException {
		
		return patientService.createFamilyMember(familyMember, patientId);
	}

	@PutMapping("/family/{id}")
	public Family updateFamilyMember(@PathVariable(value = "id") Integer familyMemberId,
			@RequestBody Family familyMemberDetails) throws ResourceNotFoundException {

		return patientService.updateFamilyMember(familyMemberId, familyMemberDetails);
	}
	
	@DeleteMapping("/family/{id}")
	public void deleteFamilyMember(@PathVariable(value = "id") Integer familyMemberId)
			throws ResourceNotFoundException {
		
		patientService.deleteFamilyMember(familyMemberId);
	}
                                                       //http://localhost:9090/patient
    @GetMapping("/patients")
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }
   
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable(value = "id") Integer patientId)
        throws ResourceNotFoundException {
        
        return patientService.getPatientById(patientId);
    }
    
    @PostMapping("/add")
    public Patient createPatient(@RequestBody  Patient patient) {
        return patientService.createPatient(patient);
    }
         
    @PutMapping("/patient/{id}")
    public Patient updatePatient(@PathVariable(value = "id") Integer patientId,
         Patient patientDetails) throws ResourceNotFoundException {
       
        return patientService.updatePatient(patientId, patientDetails);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable(value = "id") Integer patientId)
         throws ResourceNotFoundException {
         
    	patientService.deletePatient(patientId);
    }
}
