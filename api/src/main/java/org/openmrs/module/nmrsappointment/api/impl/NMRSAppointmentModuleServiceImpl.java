/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nmrsappointment.api.impl;

import org.openmrs.Patient;
import org.openmrs.PersonName;
import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.db.DAOException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.nmrsappointment.Item;
import org.openmrs.module.nmrsappointment.api.NMRSAppointmentModuleService;
import org.openmrs.module.nmrsappointment.api.dao.NMRSAppointmentModuleDao;

import java.util.Date;
import java.util.List;

public class NMRSAppointmentModuleServiceImpl extends BaseOpenmrsService implements NMRSAppointmentModuleService {
	
	NMRSAppointmentModuleDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(NMRSAppointmentModuleDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}

	@Override
	public List<PersonName> getPatients(List<Integer> patientIds) throws APIException {
		return dao.getPatients(patientIds, false);
	}

	@Override
	public List<PersonName> getPatientsByDemoGraphics(String firstNameLastNameArtNumber) throws APIException {
		return dao.getPatientsByDemoGraphics(firstNameLastNameArtNumber, false);
	}
}
