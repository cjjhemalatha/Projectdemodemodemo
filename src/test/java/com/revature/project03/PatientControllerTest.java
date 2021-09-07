package com.revature.project03;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.project03.controller.PatientController;
import com.revature.project03.entities.Family;
import com.revature.project03.service.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

	@InjectMocks
	PatientController patientController;
	
	@Mock
	PatientService patientService;
	
	private MockMvc mockMvc;
	private ObjectMapper mapper;
	
	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(patientController).build();
		mapper = new ObjectMapper();
	}

	@Test
	public void testAddMembers() throws Exception {
		
		Family member=new Family();
		member.setFamily_id(4);
		member.setFirstName("Tester");
		
		Mockito.when(
			patientService.createFamilyMember(Mockito.any(Family.class), Mockito.anyInt())
		).thenReturn(member);

		String postData = "{"
				+ "    \"family_id\": 2,"
				+ "    \"firstName\": \"Sree\","
				+ "    \"lastName\": \"K\","
				+ "    \"age\": 21,"
				+ "    \"gender\": null,"
				+ "    \"mobileNo\": \"8529638527\","
				+ "    \"address\": \"Hyderabad\","
				+ "    \"email_id\": \"sree@gmail.com\","
				+ "    \"doctors\": []"
				+ "}";
		
		// Send course as body to /students/Student1/courses
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/patient/1/family/addmember")
				.accept(MediaType.APPLICATION_JSON).content(postData)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		Family actualResponse = mapper.readValue(result.getResponse().getContentAsString(), Family.class);
		assertEquals(member, actualResponse);
	}
}
