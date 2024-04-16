package org.openmrs.module.nmrsappointment.api.viewModel;

import org.openmrs.Patient;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public class Appointments implements Serializable {
	
	private Integer id;
	
	private String identifier;
	
	private String patientName;
	
	private String status;
	
	private String appointmentType;
	
	private Date nextAppointmentDate;
	
	private String comment;
	
	private String baseline;
	
	private String lunchView;
	
	public String getLunchView() {
		return lunchView;
	}
	
	public void setLunchView(String lunchView) {
		this.lunchView = lunchView;
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
	
	private String recapture;
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getIdentifier() {
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	public String getPatientName() {
		return patientName;
	}
	
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getAppointmentType() {
		return appointmentType;
	}
	
	public void setAppointmentType(String appointmentType) {
		this.appointmentType = appointmentType;
	}
	
	public Date getNextAppointmentDate() {
		return nextAppointmentDate;
	}
	
	public void setNextAppointmentDate(Date nextAppointmentDate) {
		this.nextAppointmentDate = nextAppointmentDate;
	}
}
