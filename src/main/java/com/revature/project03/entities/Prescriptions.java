package com.revature.project03.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.revature.project03.model.DoctorAvailability;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Prescriptions_Table")
public class Prescriptions {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int presId;
	
//	@ManyToOne
//	@JoinColumn(name = "doctorId", referencedColumnName = "doctorId")
	private int doctorId;
	
//	@ManyToOne
//	@JoinColumn(name = "patientId", referencedColumnName = "patientId")
	private int patientId;
	
	private String description;
	private String medicines;
	
//	@ManyToOne
//	@JoinColumn(name = "doctorFees", referencedColumnName = "doctorFees")
	private int doctorFees;
	private int appointmentId;
}
