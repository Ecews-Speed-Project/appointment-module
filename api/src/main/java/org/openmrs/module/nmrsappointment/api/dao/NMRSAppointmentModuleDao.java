/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nmrsappointment.api.dao;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.nmrsappointment.Item;
import org.openmrs.module.nmrsappointment.api.models.NDRExport;
import org.openmrs.module.nmrsappointment.api.models.NMRSAppointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("nmrsappointment.NMRSAppointmentModuleDao")
public class NMRSAppointmentModuleDao {
	
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void setSessionFactory(DbSessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public NMRSAppointment saveAppointment(NMRSAppointment nmrsAppointment) {
		getSession().saveOrUpdate(nmrsAppointment);
		return nmrsAppointment;
	}
	
	@Transactional(readOnly = true)
	public List<NMRSAppointment> getAppointments() throws DAOException {
		Criteria criteria = getSession().createCriteria(NMRSAppointment.class);
		return criteria.list();
	}
	
	public List<Person> getPatients(List<Integer> patientIds, boolean includeVoided) throws DAOException {
		Criteria criteria = getSession().createCriteria(Person.class);
		if (!includeVoided)
			criteria.add(Restrictions.eq("voided", false));
		criteria.add(Restrictions.in("personId", patientIds));
		return criteria.list();
	}
	
	public List<Person> getPatientsByDemoGraphics(String firstNameLastNameArtNumber, boolean includeVoided)
	        throws DAOException {
		List<Person> patients = new ArrayList<Person>();
		List<Integer> patientIds;
		
		String query = "SELECT " + "DISTINCT(person_name.`person_id`) " + "FROM person_name "
		        + "LEFT JOIN patient_identifier  ON person_name.`person_id` =  patient_identifier.`patient_id` \n"
		        + "AND person_name.`voided` = false AND patient_identifier.`voided` = false "
		        + "WHERE ( person_name.`family_name` LIKE :search OR  " + "person_name.`given_name` LIKE :search OR "
		        + "patient_identifier.`identifier` LIKE :search " + ")";
		SQLQuery sql = getSession().createSQLQuery(query);
		if (firstNameLastNameArtNumber != null) {
			sql.setString("search", "%" + firstNameLastNameArtNumber + "%");
		}
		patientIds = sql.list();
		if (patientIds.size() >= 1) {
			patients = this.getPatients(patientIds, includeVoided);
		}
		
		return patients;
	}
	
}
