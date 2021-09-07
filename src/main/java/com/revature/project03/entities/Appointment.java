package com.revature.project03.entities;

import static javax.persistence.GenerationType.IDENTITY;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="Appointment_table")
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_generator")
	@SequenceGenerator(name="app_generator", sequenceName = "app_seq")
	private int applicationId;
	
	private Date applicationDate;
	private String purpose;
	private String availability;
	
	
	
	@ManyToOne
	@JoinColumn(name="p_id")
	private Patient patient;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="family_id")
	private Family member;
	
	@ManyToOne
	@JoinColumn(name="doctorId")
	private Doctor doctor;
	
	
	
	
	
//	@OneToMany(cascade= CascadeType.ALL,mappedBy="doctor")
	
//	
     

	
//	@ManyToMany
//	@Column(name="family")
//	private List<Family> family=new ArrayList<Family>();
	
//	@ManyToOne
//	@JoinColumn(name = "rId")
//	private Receptionist receptionist;
	
	
}