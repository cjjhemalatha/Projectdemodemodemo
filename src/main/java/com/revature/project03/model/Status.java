package com.revature.project03.model;

import java.sql.Date;


import com.revature.project03.entities.DoctorLeave;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Status {
	private String role;
	private boolean stat;
	private int userId;
	

}
