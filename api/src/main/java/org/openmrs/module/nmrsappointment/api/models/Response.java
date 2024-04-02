/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsappointment.api.models;

import java.util.List;

/**
 * @author MORRISON.I
 */
public class Response {
	
	private List<PatientData> patientData;
	
	public List<PatientData> getPatientData() {
		return patientData;
	}
	
	public void setPatientData(List<PatientData> patientData) {
		this.patientData = patientData;
	}
}
