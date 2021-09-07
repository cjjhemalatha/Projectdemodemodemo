package com.revature.project03.model;

import java.sql.Time;
import java.time.LocalTime;

import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Embeddable

public class DoctorAvailability {
	
	private String toDay;
	private String fromDay;
	private Time fromTime;
	private Time toTime;
	private boolean availabilityStatus;
	
	

}
