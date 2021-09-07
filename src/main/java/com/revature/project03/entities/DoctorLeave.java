package com.revature.project03.entities;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "Doctor_Leave_Table")
public class DoctorLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int leaveId;
	private int doctorId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date leaveDate;

}
