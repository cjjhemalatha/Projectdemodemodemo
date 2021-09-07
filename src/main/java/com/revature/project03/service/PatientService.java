package com.revature.project03.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.project03.entities.Family;
import com.revature.project03.entities.Patient;
import com.revature.project03.exception.ResourceNotFoundException;
import com.revature.project03.repository.FamilyRepository;
import com.revature.project03.repository.PatientRepository;
@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private FamilyRepository familyRepository;
	
	public Patient findbyEmail(String email) {
		return patientRepository.findByEmail(email);
	}

	public Family getFamilyMemberById(int familyMemberId)
			throws ResourceNotFoundException {
		Family familyMember = familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));
		return familyMember;
	}

	
	public Family createFamilyMember(Family familyMember, int patientId) throws ResourceNotFoundException {
		Patient patient = patientRepository.findById(patientId)
		          .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
		familyMember.setPatient(patient);
		return familyRepository.save(familyMember);
	}

	
	public Family updateFamilyMember(int familyMemberId, Family familyMemberDetails) throws ResourceNotFoundException {
		Family familyMember =familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));

		familyMember.setEmail_id(familyMemberDetails.getEmail_id());
		familyMember.setLastName(familyMemberDetails.getLastName());
		familyMember.setFirstName(familyMemberDetails.getFirstName());
		familyMember.setAge(familyMemberDetails.getAge());
		familyMember.setAddress(familyMemberDetails.getAddress());
		familyMember.setMobileNo(familyMemberDetails.getMobileNo());

		final Family updatedFamilyMember = familyRepository.save(familyMember);
		return updatedFamilyMember;
	}
	
	public void deleteFamilyMember(int familyMemberId)
			throws ResourceNotFoundException {
		Family famiyMember = familyRepository.findById(familyMemberId)
				.orElseThrow(() -> new ResourceNotFoundException("FamilyMember not found for this id :: " + familyMemberId));
		
		familyRepository.delete(famiyMember);
	}
                                                       //http://localhost:9090/patient
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }
   
    
    public Patient getPatientById(int patientId)
        throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
          .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        return patient;
    }
    
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }
         
    
    public Patient updatePatient(int patientId, Patient patientDetails) throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
        .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));
        
        patient.setUserName(patientDetails.getUserName());
        patient.setPassword(patientDetails.getPassword());
        patient.setEmail(patientDetails.getEmail());
        patient.setLastName(patientDetails.getLastName());
        patient.setFirstName(patientDetails.getFirstName());
        patient.setAge(patientDetails.getAge());
        patient.setAddress(patientDetails.getAddress());
        patient.setMobileNo(patientDetails.getMobileNo());
        final Patient updatedPatient = patientRepository.save(patient);
        return updatedPatient;
    }

    
    public void deletePatient(int patientId)
         throws ResourceNotFoundException {
        Patient patient = patientRepository.findById(patientId)
       .orElseThrow(() -> new ResourceNotFoundException("Patient not found for this id :: " + patientId));

        patientRepository.delete(patient);
    }
    
}
