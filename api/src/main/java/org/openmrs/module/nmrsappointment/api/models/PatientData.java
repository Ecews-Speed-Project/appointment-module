/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.openmrs.module.nmrsappointment.api.models;

/**
 * @author MORRISON.I
 */
public class PatientData {
	
	private Integer patientId;
	
	private String name;
	
	private String apptType;
	
	private String status;
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getApptType() {
		return apptType;
	}
	
	public void setApptType(String apptType) {
		this.apptType = apptType;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getBaseline() {
		return baseline;
	}
	
	public void setBaseline(String baseline) {
		this.baseline = baseline;
	}
	
	public String getRecapture() {
		return recapture;
	}
	
	public void setRecapture(String recapture) {
		this.recapture = recapture;
	}
	
	private String date;
	
	private String baseline;
	
	private String recapture;
}
