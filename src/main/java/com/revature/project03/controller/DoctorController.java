package com.revature.project03.controller;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory;
import org.apache.logging.log4j.Logger;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
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

import com.revature.project03.entities.Doctor;
import com.revature.project03.entities.DoctorLeave;
import com.revature.project03.entities.LoginRoute;
import com.revature.project03.model.DateFetch;
import com.revature.project03.model.Login;
import com.revature.project03.service.DoctorLeaveService;
import com.revature.project03.service.DoctorService;
import com.revature.project03.service.LoginRouteService;

import lombok.extern.slf4j.Slf4j;
@RestController
@RequestMapping("/doctorController")
@CrossOrigin(origins = "*")
@Slf4j
public class DoctorController {
	@Autowired
    private DoctorService service;
	//private GeneratePasswordService passGen;
	
	@Autowired
	private DoctorLeaveService leaveService;
	@Autowired
	private LoginRouteService loginRouteService;
	
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

    @PostMapping("/addDoctor")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
    	String newPass = generateRandomPassword();
    	doctor.setPassword(newPass);
    	LoginRoute loginRoute = new LoginRoute();
    	loginRoute.setPasswd(newPass);
		loginRoute.setRole("receptionist");
		loginRoute.setUserEmail(doctor.getEmail());
		loginRouteService.saveLoginInfo(loginRoute);
    	log.trace("New Doctor Added with email: "+ doctor.getEmail());
        return service.saveDoctor(doctor);
    }

    @PostMapping("/addDoctors")
    public List<Doctor> addDoctors(@RequestBody List<Doctor> doctors) {
        return service.saveDoctors(doctors);
    }

    @GetMapping("/doctors")
    public List<Doctor> findAllDoctors() {
        return service.getDoctors();
    }

    @GetMapping("/doctorById/{id}")
    public Doctor findDoctorById(@PathVariable int id) {
        return service.getDoctorById(id);
    }
    @GetMapping("/doctorByEmail/{email}")
    public Doctor findDoctorByEmail(@PathVariable String email) {
        return service.getDoctorByEmail(email);
    }
    
    @PutMapping("/updateDoctor")
    public Doctor updateDoctor(@RequestBody Doctor doctor) {
        return service.updateDoctor(doctor);
    }

    @DeleteMapping("/deleteDoctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        return service.deleteDoctor(id);
    }
    
    @PostMapping("/bookleave")
    public DoctorLeave applyleave(@RequestBody DoctorLeave leave) {
		return leaveService.saveDoctorLeave(leave);
    	
    }
    
    @GetMapping("/getleavesbydate")
    public List<DoctorLeave> getleavesbydate(@RequestBody DateFetch dateFetch) {
		return leaveService.findbydates(dateFetch.getDate());
    	
    }
    
    @DeleteMapping("/deleteleave/{doctorId}")
    public String deleteleave(@PathVariable int doctorId) {
    	return leaveService.deleteDoctorLeave(doctorId);
    }
    
    @PostMapping("/getallbydocid")
    public List<DoctorLeave> getAllLeavesOfDoc(int id){
    	return leaveService.findAllLeavesOfDoctorById(id);
    }
    
   

}
