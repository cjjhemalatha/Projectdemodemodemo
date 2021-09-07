package com.revature.project03.controller;

import org.hibernate.annotations.NaturalId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.project03.entities.Admin;
import com.revature.project03.entities.Doctor;
import com.revature.project03.entities.LoginRoute;
import com.revature.project03.entities.Patient;
import com.revature.project03.entities.Receptionist;
import com.revature.project03.model.Login;
import com.revature.project03.model.Status;
import com.revature.project03.service.AdminService;
import com.revature.project03.service.DoctorService;
import com.revature.project03.service.LoginRouteService;
import com.revature.project03.service.PatientService;
import com.revature.project03.service.ReceptionistService;

@RestController
@RequestMapping("/loginController")
public class LoginController {

	@Autowired
	private LoginRouteService loginRouteService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private PatientService patientService;
	@Autowired
	private AdminService adminService;
	@Autowired
	private ReceptionistService receptionistService;

	@PostMapping("/login")
	public Status loginmethod(@RequestBody Login login) {
		LoginRoute loginRoute = loginRouteService.findbyEmail(login.getEmail());
		String userPass = loginRoute.getPasswd();
		String enteredPass = login.getPassword();
		Status status = new Status();
		status.setStat(false);
		if (enteredPass.equals(userPass)) {
			if (loginRoute.getRole().equals("doctor")) {
				Doctor doctor1 = doctorService.getDoctorByEmail(login.getEmail());

				status.setUserId(doctor1.getDoctorId());
				status.setRole(loginRoute.getRole());
				status.setStat(true);
				return status;
			}
			else if (loginRoute.getRole().equals("patient")) {
				Patient patient1 = patientService.findbyEmail(login.getEmail());

				status.setUserId(patient1.getP_id());
				status.setRole(loginRoute.getRole());
				status.setStat(true);
				return status;
			}
			else if (loginRoute.getRole().equals("admin")) {
				Admin admin1 = adminService.findbyEmail(login.getEmail());

				status.setUserId(admin1.getAdminId());
				status.setRole(loginRoute.getRole());
				status.setStat(true);
				return status;
			}

			else if (loginRoute.getRole().equals("receptionist")) {
				 Receptionist receptionist =receptionistService.getReceptionistByEmail(login.getEmail());
			     status.setUserId(receptionist.getRId());
			     status.setRole(loginRoute.getRole());
				status.setStat(true);
				return status;
		}


		}
		return status;
	}

}

