package com.revature.project03.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Login_Route_Table")
public class LoginRoute {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int routeId;
	private String role;
	private String userEmail;
	private String passwd;
	

}
