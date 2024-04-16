/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 * <p>
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nmrsappointment.fragment.controller;

import com.google.gson.Gson;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.PatientService;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.nmrsappointment.api.NMRSAppointmentModuleService;
import org.openmrs.module.nmrsappointment.api.models.NDRExport;
import org.openmrs.module.nmrsappointment.api.models.NMRSAppointment;
import org.openmrs.module.nmrsappointment.api.models.PatientData;
import org.openmrs.module.nmrsappointment.api.models.Response;
import org.openmrs.module.nmrsappointment.api.viewModel.Appointments;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  * Controller for a fragment that shows all users  
 */
public class UsersFragmentController {
	
	ObjectMapper mapper = new ObjectMapper();
	
	PatientService patientService;
	
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		model.addAttribute("users", service.getAllUsers());
	}
	
	public List<Appointments> fetchAppointments(HttpServletRequest request,
	        @RequestParam(value = "searchText") String searchText) throws Exception {
		List<Appointments> appointmentsList = new ArrayList<Appointments>();
		
		List<NMRSAppointment> appointments = Context.getService(NMRSAppointmentModuleService.class).getAppointments();
		
		for (NMRSAppointment nmrsAppt : appointments) {
			String name = String
			        .format("%s %s", nmrsAppt.getPatient().getFamilyName(), nmrsAppt.getPatient().getGivenName());
			
			Appointments appointment = new Appointments();
			appointment.setId(nmrsAppt.getId());
			appointment.setPatientName(name);
			appointment.setIdentifier(nmrsAppt.getPatient().getPatientIdentifier().getIdentifier());
			appointment.setStatus(nmrsAppt.getStatus());
			appointment.setNextAppointmentDate(nmrsAppt.getVisitDate());
			appointment.setComment(nmrsAppt.getComments());
			appointment.setAppointmentType(nmrsAppt.getType());
			appointment.setBaseline("Yes");
			appointment.setRecapture("Yes");
			appointmentsList.add(appointment);
		}
		
		return appointmentsList;
	}
	
	public String saveAppointments(HttpServletRequest request, @RequestParam(value = "patientId") String patientId,
	        @RequestParam(value = "visitDate") String visitDate, @RequestParam(value = "comments") String comments,
	        @RequestParam(value = "type") String type, @RequestParam(value = "appointmentDate") String appointmentDate
	
	) throws Exception {
		
		NMRSAppointment nmrsAppointment = new NMRSAppointment();
		Patient patient = Context.getPatientService().getPatient(Integer.parseInt(patientId));
		nmrsAppointment.setPatient(patient);
		nmrsAppointment.setType(type);
		nmrsAppointment.setComments(comments);
		nmrsAppointment.setStatus("Pending");
		Date visitDateFilled = new SimpleDateFormat("yyyy-MM-dd").parse(visitDate);
		nmrsAppointment.setVisitDate(visitDateFilled);
		Date appointmentDateFilled = new SimpleDateFormat("yyyy-MM-dd").parse(appointmentDate);
		nmrsAppointment.setVisitDate(appointmentDateFilled);
		Context.getService(NMRSAppointmentModuleService.class).saveAppointment(nmrsAppointment);
		return "success";
	}
	
	public List<PatientData> fetchPatient(HttpServletRequest request, @RequestParam(value = "searchText") String searchText)
	        throws Exception {
		//Gson gson = new Gson();
		List<PatientData> patientDataList = new ArrayList<PatientData>();
		List<Person> person = Context.getService(NMRSAppointmentModuleService.class).getPatientsByDemoGraphics(searchText);
		String response = "";
		
		for (Person patient : person) {
			PatientData patientData = new PatientData();
			patientData.setPatientId(patient.getPersonId());
			patientData.setName(patient.getGivenName() + " " + patient.getFamilyName());
			patientDataList.add(patientData);
		}
		return patientDataList;
	}
}
