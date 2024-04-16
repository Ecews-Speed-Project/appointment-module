package org.openmrs.module.nmrsappointment.api.models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "appointmentscheduling_appointment_type", schema = "openmrs", catalog = "")
public class AppointmentschedulingAppointmentType {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "appointment_type_id", nullable = false)
	private int appointmentTypeId;
	
	@Basic
	@Column(name = "name", nullable = false, length = 255)
	private String name;
	
	@Basic
	@Column(name = "description", nullable = true, length = 1024)
	private String description;
	
	@Basic
	@Column(name = "duration", nullable = false)
	private int duration;
	
	@Basic
	@Column(name = "uuid", nullable = false, length = 38)
	private String uuid;
	
	@Basic
	@Column(name = "creator", nullable = false)
	private int creator;
	
	@Basic
	@Column(name = "date_created", nullable = false)
	private Timestamp dateCreated;
	
	@Basic
	@Column(name = "changed_by", nullable = true)
	private Integer changedBy;
	
	@Basic
	@Column(name = "date_changed", nullable = true)
	private Timestamp dateChanged;
	
	@Basic
	@Column(name = "retired", nullable = false)
	private byte retired;
	
	@Basic
	@Column(name = "retired_by", nullable = true)
	private Integer retiredBy;
	
	@Basic
	@Column(name = "date_retired", nullable = true)
	private Timestamp dateRetired;
	
	@Basic
	@Column(name = "retire_reason", nullable = true, length = 255)
	private String retireReason;
	
	@Basic
	@Column(name = "confidential", nullable = false)
	private byte confidential;
	
	@Basic
	@Column(name = "visit_type_id", nullable = true)
	private Integer visitTypeId;
	
	public int getAppointmentTypeId() {
		return appointmentTypeId;
	}
	
	public void setAppointmentTypeId(Integer appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	
	public void setAppointmentTypeId(int appointmentTypeId) {
		this.appointmentTypeId = appointmentTypeId;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public int getCreator() {
		return creator;
	}
	
	public void setCreator(Integer creator) {
		this.creator = creator;
	}
	
	public void setCreator(int creator) {
		this.creator = creator;
	}
	
	public Timestamp getDateCreated() {
		return dateCreated;
	}
	
	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	public Integer getChangedBy() {
		return changedBy;
	}
	
	public void setChangedBy(Integer changedBy) {
		this.changedBy = changedBy;
	}
	
	public Timestamp getDateChanged() {
		return dateChanged;
	}
	
	public void setDateChanged(Timestamp dateChanged) {
		this.dateChanged = dateChanged;
	}
	
	public byte getRetired() {
		return retired;
	}
	
	public void setRetired(Byte retired) {
		this.retired = retired;
	}
	
	public void setRetired(byte retired) {
		this.retired = retired;
	}
	
	public Integer getRetiredBy() {
		return retiredBy;
	}
	
	public void setRetiredBy(Integer retiredBy) {
		this.retiredBy = retiredBy;
	}
	
	public Timestamp getDateRetired() {
		return dateRetired;
	}
	
	public void setDateRetired(Timestamp dateRetired) {
		this.dateRetired = dateRetired;
	}
	
	public String getRetireReason() {
		return retireReason;
	}
	
	public void setRetireReason(String retireReason) {
		this.retireReason = retireReason;
	}
	
	public byte getConfidential() {
		return confidential;
	}
	
	public void setConfidential(Byte confidential) {
		this.confidential = confidential;
	}
	
	public void setConfidential(byte confidential) {
		this.confidential = confidential;
	}
	
	public Integer getVisitTypeId() {
		return visitTypeId;
	}
	
	public void setVisitTypeId(Integer visitTypeId) {
		this.visitTypeId = visitTypeId;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		
		AppointmentschedulingAppointmentType that = (AppointmentschedulingAppointmentType) o;
		
		if (appointmentTypeId != that.appointmentTypeId)
			return false;
		if (duration != that.duration)
			return false;
		if (creator != that.creator)
			return false;
		if (retired != that.retired)
			return false;
		if (confidential != that.confidential)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null)
			return false;
		if (description != null ? !description.equals(that.description) : that.description != null)
			return false;
		if (uuid != null ? !uuid.equals(that.uuid) : that.uuid != null)
			return false;
		if (dateCreated != null ? !dateCreated.equals(that.dateCreated) : that.dateCreated != null)
			return false;
		if (changedBy != null ? !changedBy.equals(that.changedBy) : that.changedBy != null)
			return false;
		if (dateChanged != null ? !dateChanged.equals(that.dateChanged) : that.dateChanged != null)
			return false;
		if (retiredBy != null ? !retiredBy.equals(that.retiredBy) : that.retiredBy != null)
			return false;
		if (dateRetired != null ? !dateRetired.equals(that.dateRetired) : that.dateRetired != null)
			return false;
		if (retireReason != null ? !retireReason.equals(that.retireReason) : that.retireReason != null)
			return false;
		if (visitTypeId != null ? !visitTypeId.equals(that.visitTypeId) : that.visitTypeId != null)
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int result = appointmentTypeId;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + duration;
		result = 31 * result + (uuid != null ? uuid.hashCode() : 0);
		result = 31 * result + creator;
		result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
		result = 31 * result + (changedBy != null ? changedBy.hashCode() : 0);
		result = 31 * result + (dateChanged != null ? dateChanged.hashCode() : 0);
		result = 31 * result + (int) retired;
		result = 31 * result + (retiredBy != null ? retiredBy.hashCode() : 0);
		result = 31 * result + (dateRetired != null ? dateRetired.hashCode() : 0);
		result = 31 * result + (retireReason != null ? retireReason.hashCode() : 0);
		result = 31 * result + (int) confidential;
		result = 31 * result + (visitTypeId != null ? visitTypeId.hashCode() : 0);
		return result;
	}
}
