package com.airprz.service.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.Plane;
import com.airprz.service.FlightSeatService;
import com.airprz.service.impl.FlightSeatServiceImpl;

public class FlightSeatServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenCheckingIfSeatIsFree_givenValidSeatNoAndFlightId_itShouldReturnFlightSeatObject()  {
		final Long validSeatNo = new Long(101);
		final Long validFlightId = new Long(1);
		final Long validFlightSeatId = new Long(1);
		final Long validSeatClass = new Long(1);
		final String validFree = "Y";
		final String validLocation = "WF";
		final String validPlaneNo = "AIR001";
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.checkIfFree(validSeatNo, validFlightId);
		//Assert
		Assert.assertNotNull(flightSeat);
		Assert.assertEquals(validFlightSeatId, flightSeat.getFsId());
		Assert.assertEquals(validSeatNo, flightSeat.getSeatNo());
		Assert.assertEquals(validFlightId, flightSeat.getFlight().getFlightId());
		Assert.assertEquals(validSeatClass, flightSeat.getFsClass());
		Assert.assertEquals(validFree, flightSeat.getFree());
		Assert.assertEquals(validLocation, flightSeat.getWhereLoc());
		Assert.assertEquals(validPlaneNo, flightSeat.getPlane().getPlaneId());

	}
	
	@Test
	public void whenCheckingIfSeatIsFree_givenValidSeatNoAndInvalidFlightId_itShouldReturnNull()  {
		final Long validSeatNo = new Long(101);
		final Long validFlightId = new Long(1000);
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.checkIfFree(validSeatNo, validFlightId);
		//Assert
		Assert.assertNull(flightSeat);
	}
	
	@Test
	public void whenCheckingIfSeatIsFree_givenInValidSeatNoAndValidFlightId_itShouldReturnNull()  {
		final Long validSeatNo = new Long(10132);
		final Long validFlightId = new Long(1);
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.checkIfFree(validSeatNo, validFlightId);
		//Assert
		Assert.assertNull(flightSeat);
	}
	
	@Test
	public void whenSearchingForFreeSeats_GivenValidData_itShouldReturnFlightSeatsObject()  {
		List<FlightSeat> validFlightSeats = new ArrayList<FlightSeat>();
		final Long validSeatNo1 = new Long(101);
		final Long validFlightId1 = new Long(1);
		final Long validFlightSeatId1 = new Long(1);
		final Long validSeatClass1 = new Long(1);
		final String validFree1 = "Y";
		final String validLocation1 = "WF";
		final String validPlaneNo1 = "AIR001";
		Flight flight1 = new Flight();
		Plane plane1 = new Plane();
		flight1.setFlightId(validFlightId1);
		plane1.setPlaneId(validPlaneNo1);
		FlightSeat validFlightSeat1 = new FlightSeat();
		validFlightSeat1.setFsId(validFlightSeatId1);
		validFlightSeat1.setSeatNo(validSeatNo1);
		validFlightSeat1.setFlight(flight1);
		validFlightSeat1.setFsClass(validSeatClass1);
		validFlightSeat1.setFree(validFree1);
		validFlightSeat1.setWhereLoc(validLocation1);
		validFlightSeat1.setPlane(plane1);
		
		final Long validSeatNo2 = new Long(102);
		final Long validFlightId2 = new Long(1);
		final Long validFlightSeatId2 = new Long(1);
		final Long validSeatClass2 = new Long(1);
		final String validFree2 = "Y";
		final String validLocation2 = "WF";
		final String validPlaneNo2 = "AIR001";
		Flight flight2 = new Flight();
		Plane plane2 = new Plane();
		flight2.setFlightId(validFlightId2);
		plane2.setPlaneId(validPlaneNo2);
		FlightSeat validFlightSeat2 = new FlightSeat();
		validFlightSeat2.setFsId(validFlightSeatId2);
		validFlightSeat2.setSeatNo(validSeatNo2);
		validFlightSeat2.setFlight(flight2);
		validFlightSeat2.setFsClass(validSeatClass2);
		validFlightSeat2.setFree(validFree2);
		validFlightSeat2.setWhereLoc(validLocation2);
		validFlightSeat2.setPlane(plane2);
		
		
		validFlightSeats.add(validFlightSeat1);
		validFlightSeats.add(validFlightSeat2);

		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		List<FlightSeat> flightSeats = flightSeatService.searchForFree(validSeatClass1, validLocation1, validFlightId1);
		
		FlightSeat flightSeat1 = flightSeats.get(0);
		FlightSeat flightSeat2 = flightSeats.get(1);
		//Assert
		Assert.assertNotNull(flightSeats);
		Assert.assertEquals(validFlightSeats.size(), flightSeats.size());
		Assert.assertNotNull(flightSeat1);
		Assert.assertNotNull(flightSeat2);
	

	}
	
	@Test
	public void whenGettingAllSeatsForFlight__itShouldReturnFlightSeatsObject()  {
		final Long validFlightId = new Long(1);

		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		List<FlightSeat> flightSeats = flightSeatService.getSeats(validFlightId);
		
		//Assert
		Assert.assertNotNull(flightSeats);
		Assert.assertEquals(4, flightSeats.size());

	}
	
	@Test
	public void whenGettingAllUsersForInvalidCode__itShouldReturnNull()  {
		final Long validFlightId = new Long(1000);

		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		List<FlightSeat> flightSeats = flightSeatService.getSeats(validFlightId);
		
		//Assert
		Assert.assertNotNull(flightSeats);
		Assert.assertEquals(0, flightSeats.size());

	}
	
	@Test
	public void whenGettingFlightSeat_givenValidFlightSeatId_itShouldReturnFlightSeatObject()  {
		final Long validSeatNo = new Long(101);
		final Long validFlightId = new Long(1);
		final Long validFlightSeatId = new Long(1);
		final Long validSeatClass = new Long(1);
		final String validFree = "Y";
		final String validLocation = "WF";
		final String validPlaneNo = "AIR001";
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.getFlightSeat(validFlightSeatId);
		//Assert
		Assert.assertNotNull(flightSeat);
		Assert.assertEquals(validFlightSeatId, flightSeat.getFsId());
		Assert.assertEquals(validSeatNo, flightSeat.getSeatNo());
		Assert.assertEquals(validFlightId, flightSeat.getFlight().getFlightId());
		Assert.assertEquals(validSeatClass, flightSeat.getFsClass());
		Assert.assertEquals(validFree, flightSeat.getFree());
		Assert.assertEquals(validLocation, flightSeat.getWhereLoc());
		Assert.assertEquals(validPlaneNo, flightSeat.getPlane().getPlaneId());

	}
	
	
	@Test
	public void whenAddingValidFlightSeat__itShouldReturnFlightSeatObject()  {
		final Long validSeatNo = new Long(109);
		final Long validFlightId = new Long(1);
		final Long validFlightSeatId = new Long(6);
		final Long validSeatClass = new Long(1);
		final String validFree = "Y";
		final String validLocation = "CB";
		final String validPlaneNo = "AIR001";
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.addFlightSeat(validSeatClass, validSeatNo, validLocation, validPlaneNo, validFlightId);
		//Assert
		Assert.assertNotNull(flightSeat);
		Assert.assertEquals(validFlightSeatId, flightSeat.getFsId());
		Assert.assertEquals(validSeatNo, flightSeat.getSeatNo());
		Assert.assertEquals(validFlightId, flightSeat.getFlight().getFlightId());
		Assert.assertEquals(validSeatClass, flightSeat.getFsClass());
		Assert.assertEquals(validFree, flightSeat.getFree());
		Assert.assertEquals(validLocation, flightSeat.getWhereLoc());
		Assert.assertEquals(validPlaneNo, flightSeat.getPlane().getPlaneId());

	}
	
	@Test
	public void whenAddingInValidFlightSeat__itShouldReturnnull()  {
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.addFlightSeat(null, null, null, null, null);
		//Assert
		Assert.assertNull(flightSeat);
	}
	
	@Test
	public void whenUpdatingValidFlightSeat__itShouldReturnFlightSeatObject()  {
		final Long validSeatNo = new Long(209);
		final Long validFlightId = new Long(1);
		final Long validFlightSeatId = new Long(1);
		final Long validSeatClass = new Long(2);
		final String validFree = "Y";
		final String validLocation = "CB";
		final String validPlaneNo = "AIR001";
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.updateFlightSeat(validFlightSeatId, validSeatClass, validSeatNo, validFree, validLocation, validPlaneNo, validFlightId);
		//Assert
		Assert.assertNotNull(flightSeat);
		Assert.assertEquals(validFlightSeatId, flightSeat.getFsId());
		Assert.assertEquals(validSeatNo, flightSeat.getSeatNo());
		Assert.assertEquals(validFlightId, flightSeat.getFlight().getFlightId());
		Assert.assertEquals(validSeatClass, flightSeat.getFsClass());
		Assert.assertEquals(validFree, flightSeat.getFree());
		Assert.assertEquals(validLocation, flightSeat.getWhereLoc());
		Assert.assertEquals(validPlaneNo, flightSeat.getPlane().getPlaneId());

	}
	
	@Test
	public void whenBookingValidFlightSeat__itShouldReturnFlightSeatObject()  {
		final Long validSeatNo = new Long(101);
		final Long validFlightId = new Long(1);
		final Long validFlightSeatId = new Long(1);
		final Long validSeatClass = new Long(1);
		final String validFree = "N";
		final String validLocation = "WF";
		final String validPlaneNo = "AIR001";
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		FlightSeat flightSeat = flightSeatService.bookSeat(validSeatNo, validFlightId);
		//Assert
		Assert.assertNotNull(flightSeat);
		Assert.assertEquals(validFlightSeatId, flightSeat.getFsId());
		Assert.assertEquals(validSeatNo, flightSeat.getSeatNo());
		Assert.assertEquals(validFlightId, flightSeat.getFlight().getFlightId());
		Assert.assertEquals(validSeatClass, flightSeat.getFsClass());
		Assert.assertEquals(validFree, flightSeat.getFree());
		Assert.assertEquals(validLocation, flightSeat.getWhereLoc());
		Assert.assertEquals(validPlaneNo, flightSeat.getPlane().getPlaneId());

	}
	
	@Test
	public void whenDeletingFlightSeat_itShouldReturnNull()  {
		final Long validFlightSeatId = new Long(6);
		final FlightSeatService flightSeatService = new FlightSeatServiceImpl();
		
		//Act
		flightSeatService.deleteFlightSeat(validFlightSeatId);
		FlightSeat flightSeat = flightSeatService.getFlightSeat(validFlightSeatId);
		//Assert
		Assert.assertNull(flightSeat);
	}

}
