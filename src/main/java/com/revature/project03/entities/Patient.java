package com.revature.project03.entities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.JoinColumn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int p_id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
	private String mobileNo;
	private String address;
	@Column(name = "email_id")
	private String email;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "patient")
	private List<Family> members=new ArrayList<>();
	
	@ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "Doctor_Patient", 
        joinColumns = { @JoinColumn(name = "p_id") }, 
        inverseJoinColumns = { @JoinColumn(name = "doctorId") }
    )
    Set<Doctor> doctors = new HashSet<>();
	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, mappedBy = "patient")
	private List<Appointment> appointment=new ArrayList<>();


	
}
