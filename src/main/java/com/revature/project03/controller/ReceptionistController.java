package com.revature.project03.controller;

import java.util.ArrayList;
import java.util.List;

import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Appointment;
import com.revature.project03.entities.DoctorLeave;
import com.revature.project03.entities.LoginRoute;
import com.revature.project03.entities.Receptionist;
import com.revature.project03.exception.ResourceNotFoundException;
import com.revature.project03.model.DateFetch;
import com.revature.project03.model.ReceptionistDto;
import com.revature.project03.repository.AppointmentRepository;
import com.revature.project03.service.AppointmentService;
import com.revature.project03.service.DoctorLeaveService;
import com.revature.project03.service.LoginRouteService;
import com.revature.project03.service.ReceptionistService;






@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/receptionistController")
public class ReceptionistController {
	
	@Autowired
    private ReceptionistService service;
	@Autowired
	private LoginRouteService loginRouteService;
	@Autowired
	private AppointmentService appointmentService;
	@Autowired
	private DoctorLeaveService doctorLeaveService;
	@Autowired
	private AppointmentRepository appointmentRepository;
	public String generateRandomPassword() {
	    PasswordGenerator gen = new PasswordGenerator();
	    org.passay.CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
	    CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
	    lowerCaseRule.setNumberOfCharacters(2);

	    CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
	    CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
	    upperCaseRule.setNumberOfCharacters(2);

	    CharacterData digitChars = EnglishCharacterData.Digit;
	    CharacterRule digitRule = new CharacterRule(digitChars);
	    digitRule.setNumberOfCharacters(2);

	    CharacterData specialChars = new CharacterData() {
	        public String getErrorCode() {
	            return null;
	        }

	        public String getCharacters() {
	            return "!@#$%^&*()_+";
	        }
	    };
	    CharacterRule splCharRule = new CharacterRule(specialChars);
	    splCharRule.setNumberOfCharacters(2);
	    List l1 = new ArrayList<>();
	    l1.add(splCharRule);
	    l1.add(lowerCaseRule);
	    l1.add(upperCaseRule);
	    l1.add(digitRule);

	    String password = gen.generatePassword(10, l1);
	    return password;
	}
	
	 @PostMapping("/addReceptionist")
	    public Receptionist addReceptionist(@RequestBody Receptionist recep) {
		 String newPass = generateRandomPassword();
		 LoginRoute loginRoute = new LoginRoute();
		 System.out.println(recep.getREmail());
		 recep.setRPassword(newPass);
		 loginRoute.setPasswd(newPass);
		 loginRoute.setRole("receptionist");
		 loginRoute.setUserEmail(recep.getREmail());
		 loginRouteService.saveLoginInfo(loginRoute);
		 	
	        return service.saveReceptionist(recep);
	    }
	 
	 @PostMapping("/confirmAppointment/{patientId}")
	 public Appointment confirmAppointment(@RequestBody Appointment appointment,@PathVariable (value = "patientId") Integer patientId) throws ResourceNotFoundException {
		System.out.println(patientId);
		 return appointmentService.confirmAppointment(appointment, patientId);
	 }
	 
	 @PostMapping("/cancelAllAppointments")
	 public List<Appointment> cancelAllAppointments(@RequestBody Appointment appointment){
		 List<Appointment> appointments = appointmentService.getAppointmentByDate(appointment.getApplicationDate());
		 List<DoctorLeave> doctorLeaves = doctorLeaveService.findbydates(appointment.getApplicationDate());
		 for(DoctorLeave doctorL:doctorLeaves) {
			 for(Appointment appointmentL:appointments) {
				 if(doctorL.getLeaveDate().compareTo(appointmentL.getApplicationDate())== 0) {
					 appointmentL.setApplicationDate(appointmentL.getApplicationDate());
					 appointmentL.setApplicationId(appointmentL.getApplicationId());
					 appointmentL.setDoctor(appointmentL.getDoctor());
					 appointmentL.setMember(appointmentL.getMember());
					 appointmentL.setPurpose(appointmentL.getPurpose());
					 appointmentL.setAvailability("cancelled");
					 appointmentRepository.save(appointmentL);
				 }
			 }
		 }
		 
		 
		 
		return appointments;
		 
	 }

}
