package com.revature.project03.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Admin_Table")
public class Admin {
	 @Id
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mytable_id_seq")
	 @SequenceGenerator(name="mytable_id_seq", sequenceName="mytable_id_seq", allocationSize=1)
	 private int adminId;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String password;
 	 
	 
	
}
