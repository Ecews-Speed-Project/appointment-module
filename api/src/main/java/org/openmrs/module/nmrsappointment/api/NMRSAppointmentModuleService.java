/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.nmrsappointment.api;

import org.openmrs.Patient;
import org.openmrs.Person;
import org.openmrs.PersonName;
import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.nmrsappointment.NMRSAppointmentModuleConfig;
import org.openmrs.module.nmrsappointment.Item;
import org.openmrs.module.nmrsappointment.api.models.NDRExport;
import org.openmrs.module.nmrsappointment.api.models.NMRSAppointment;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface NMRSAppointmentModuleService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param nmrsAppointment
	 * @return
	 * @throws APIException
	 */
	@Authorized(NMRSAppointmentModuleConfig.MODULE_PRIVILEGE)
	@Transactional
	NMRSAppointment saveAppointment(NMRSAppointment nmrsAppointment) throws APIException;
	
	@Authorized(NMRSAppointmentModuleConfig.MODULE_PRIVILEGE)
	List<NMRSAppointment> getAppointments() throws APIException;
	
	@Authorized(NMRSAppointmentModuleConfig.MODULE_PRIVILEGE)
	List<Person> getPatients(List<Integer> patientIds) throws APIException;
	
	@Authorized(NMRSAppointmentModuleConfig.MODULE_PRIVILEGE)
	List<Person> getPatientsByDemoGraphics(String firstNameLastNameArtNumber) throws APIException;
	
}
