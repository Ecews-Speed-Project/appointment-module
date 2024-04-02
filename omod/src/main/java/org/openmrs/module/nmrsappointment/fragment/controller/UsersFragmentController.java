/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nmrsappointment.fragment.controller;

import com.google.gson.Gson;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.module.nmrsappointment.api.NMRSAppointmentModuleService;
import org.openmrs.module.nmrsappointment.api.models.PatientData;
import org.openmrs.module.nmrsappointment.api.models.Response;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *  * Controller for a fragment that shows all users  
 */
public class UsersFragmentController {
	
	ObjectMapper mapper = new ObjectMapper();
	
	public void controller(FragmentModel model, @SpringBean("userService") UserService service) {
		model.addAttribute("users", service.getAllUsers());
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
