package com.revature.project03.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.revature.project03.entities.Appointment;
import com.revature.project03.entities.Doctor;
import com.revature.project03.entities.Family;
import com.revature.project03.entities.Patient;
import com.revature.project03.exception.ResourceNotFoundException;
import com.revature.project03.repository.AppointmentRepository;
import com.revature.project03.repository.FamilyRepository;
import com.revature.project03.repository.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;	
	@Autowired
	private PatientService patientservice;
	@Autowired
	private DoctorService doctorService;
	
	public Appointment createAppointment(Appointment appointment, int patientId) throws ResourceNotFoundException {
		Patient patient =patientservice.getPatientById(patientId);
//		Doctor currDoc = appointment.getDoctor();
//		Doctor doctor = doctorService.getDoctorById(currDoc.getDoctorId());
//		System.out.println("currDoc id:"+appointment.getDoctor().getDoctorId());
		//appointment.setDoctor(currDoc);
		appointment.setPatient(patient);
		return appointmentRepository.save(appointment);
	}
	
	public List<Appointment> getAppointmentByPatientId(int patientId) throws ResourceNotFoundException {
		
		Patient patient =patientservice.getPatientById(patientId);
		List<Appointment> appointment=patient.getAppointment();	
		return appointment;
	}
	
	public List<Appointment> getAppointmentByFamilyId(int familyId) throws ResourceNotFoundException {
		
		Family family =patientservice.getFamilyMemberById(familyId);
		List<Appointment> appointment=family.getAppointment();
		// use getAppointmentByDate(Appointment appt) to get current appointment
		return appointment;
	}

	public List<Appointment> getAppointmentByDate(Date date) {
		
		return appointmentRepository.findAllByapplicationDate(date);
	}
	//--------------------------------------------------------------------------------------
	public Appointment confirmAppointment(Appointment appointment, int patientId) throws ResourceNotFoundException {
		Patient patient =patientservice.getPatientById(patientId);
//		Doctor currDoc = appointment.getDoctor();
//		Doctor doctor = doctorService.getDoctorById(currDoc.getDoctorId());
//		appointment.setDoctor(doctor);
		appointment.setPatient(patient);
		appointment.setAvailability("confirmed");
		return appointmentRepository.save(appointment);
	}
	
	public List<Appointment> getAllAppointments(){
		return appointmentRepository.findAll();
	}

}