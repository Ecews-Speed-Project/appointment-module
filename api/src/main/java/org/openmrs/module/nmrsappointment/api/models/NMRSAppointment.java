package org.openmrs.module.nmrsappointment.api.models;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.Provider;
import org.openmrs.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "nmrs_appointment")
@Table(name = "nmrs_appointment")
public class NMRSAppointment implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name = "appointment_id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonIgnore
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name = "provider_id")
	@JsonIgnore
	private Provider provider;
	
	@Column(name = "status")
	private String status;
	
	@Column(name = "visit_date")
	private Date visitDate;
	
	@Column(name = "appointment_date")
	private Date appointmentDate;
	
	@Column(name = "type")
	private String type;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column(name = "comments")
	private String comments;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Patient getPatient() {
		return patient;
	}
	
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	public Provider getProvider() {
		return provider;
	}
	
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
}
