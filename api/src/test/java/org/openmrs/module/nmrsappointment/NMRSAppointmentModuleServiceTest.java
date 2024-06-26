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

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.module.nmrsappointment.Item;
import org.openmrs.module.nmrsappointment.api.dao.NMRSAppointmentModuleDao;
import org.openmrs.module.nmrsappointment.api.impl.NMRSAppointmentModuleServiceImpl;
import org.openmrs.module.nmrsappointment.api.models.NMRSAppointment;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * This is a unit test, which verifies logic in NMRSAppointmentModuleService. It doesn't extend
 * BaseModuleContextSensitiveTest, thus it is run without the in-memory DB and Spring context.
 */
public class NMRSAppointmentModuleServiceTest {
	
	@InjectMocks
	NMRSAppointmentModuleServiceImpl basicModuleService;
	
	@Mock
	NMRSAppointmentModuleDao dao;
	
	@Mock
	UserService userService;
	
	@Before
	public void setupMocks() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void saveItem_shouldSetOwnerIfNotSet() {
		//Given
		NMRSAppointment item = new NMRSAppointment();
		item.setComments("some description");
		
	}
}
