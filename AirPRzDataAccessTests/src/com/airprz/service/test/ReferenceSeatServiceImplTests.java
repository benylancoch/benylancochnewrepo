package com.airprz.service.test;

import java.sql.SQLException;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.ReferenceSeat;
import com.airprz.service.ReferenceSeatService;
import com.airprz.service.impl.ReferenceSeatServiceImpl;

public class ReferenceSeatServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenaddingMultipleSeats_givenValidData_itShouldReturnTrue()  {
		final Long validClass = new Long(1);
		final Long validSeatStartNo = new Long(1);
		final Long validSeatEndNo = new Long(10);
		final String validPlaneNo = "AIR001";
		final ReferenceSeatService referenceSeatService = new ReferenceSeatServiceImpl();
		
		//Act
		Boolean success = referenceSeatService.addMultipleReferenceSeats(validClass, validSeatStartNo, validSeatEndNo, validPlaneNo);
		List<ReferenceSeat> list = referenceSeatService.getSeats(validPlaneNo);
		//Assert
		Assert.assertTrue(success);
		Assert.assertNotNull(list);
		Assert.assertEquals(10, list.size());

	}

}
