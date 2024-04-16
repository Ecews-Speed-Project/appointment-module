package org.openmrs.module.nmrsappointment;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "nmrs_appointment", schema = "openmrs", catalog = "")
public class NmrsAppointment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "appointment_id")
	private int appointmentId;
	
	@Basic
	@Column(name = "patient_Id")
	private Integer patientId;
	
	@Basic
	@Column(name = "appointment_date")
	private Date appointmentDate;
	
	@Basic
	@Column(name = "appointment_type_id")
	private Integer appointmentTypeId;
	
	@Basic
	@Column(name = "comments")
	private String comments;
	
	@Basic
	@Column(name = "visit_date:")
	private Date visitDate;
	
	@Basic
	@Column(name = "provider_id")
	private Integer providerId;
	
	public int getAppointmentId() {
		return appointmentId;
	}
	
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	public Integer getPatientId() {
		return patientId;
	}
	
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	
	public Date getAppointmentDate() {
		return appointmentDate;
	}
	
	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}
	
	public Integer getAppointmentTypeId() {
		return appointmentTypeId;
	}
	
	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public Date getVisitDate() {
		return visitDate;
	}
	
	public void setVisitDate(Date visitDate) {
		this.visitDate = visitDate;
	}
	
	public Integer getProviderId() {
		return providerId;
	}
	
	public void setProviderId(Integer providerId) {
		this.providerId = providerId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		NmrsAppointment that = (NmrsAppointment) o;
		
		if (appointmentId != that.appointmentId)
			return false;
		if (patientId != null ? !patientId.equals(that.patientId) : that.patientId != null)
			return false;
		if (appointmentDate != null ? !appointmentDate.equals(that.appointmentDate) : that.appointmentDate != null)
			return false;
		if (appointmentTypeId != null ? !appointmentTypeId.equals(that.appointmentTypeId) : that.appointmentTypeId != null)
			return false;
		if (comments != null ? !comments.equals(that.comments) : that.comments != null)
			return false;
		if (visitDate != null ? !visitDate.equals(that.visitDate) : that.visitDate != null)
			return false;
		if (providerId != null ? !providerId.equals(that.providerId) : that.providerId != null)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = appointmentId;
		result = 31 * result + (patientId != null ? patientId.hashCode() : 0);
		result = 31 * result + (appointmentDate != null ? appointmentDate.hashCode() : 0);
		result = 31 * result + (appointmentTypeId != null ? appointmentTypeId.hashCode() : 0);
		result = 31 * result + (comments != null ? comments.hashCode() : 0);
		result = 31 * result + (visitDate != null ? visitDate.hashCode() : 0);
		result = 31 * result + (providerId != null ? providerId.hashCode() : 0);
		return result;
	}
}
