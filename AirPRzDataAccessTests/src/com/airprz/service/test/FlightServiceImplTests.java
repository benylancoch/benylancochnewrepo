package com.airprz.service.test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.Flight;
import com.airprz.service.FlightService;
import com.airprz.service.impl.FlightServiceImpl;

public class FlightServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenGettingFlight_givenValidFlightId_itShouldReturnFlightObject()  {
		final Long validFlightId = new Long(1);
		final String validFlightNo = "APRZ85";
		final Timestamp validStarts = Timestamp.valueOf("2016-04-01 21:00:00.0");
		final Timestamp validEnds = Timestamp.valueOf("2016-04-02 02:00:00.0");
		final BigDecimal validBasePrice = new BigDecimal("250.29");
		final Long validDeparturePlace = new Long(1);
		final Long validArrivalPlace = new Long(2);
		final String validPlaneNo = "AIR001";
		
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		Flight flight = flightService.getFlight(validFlightId);
		//Assert
		Assert.assertNotNull(flight);
		Assert.assertEquals(validFlightId, flight.getFlightId());
		Assert.assertEquals(validFlightNo, flight.getFlightNo());
		Assert.assertEquals(validBasePrice, flight.getBasePrice());
		Assert.assertEquals(validEnds, flight.getEnds());
		Assert.assertEquals(validStarts, flight.getStarts());
		Assert.assertEquals(validDeparturePlace, flight.getDeparturePlace().getAirportId());
		Assert.assertEquals(validArrivalPlace, flight.getArrivalPlace().getAirportId());
		Assert.assertEquals(validPlaneNo, flight.getPlaneNo().getPlaneId());

	}
	
	@Test
	public void whenSearchingForFlights_givenValidData_itShouldReturnFlightObject()  {
		final Timestamp validStarts = Timestamp.valueOf("2016-04-01 21:00:00.0");
		final Timestamp validEnds = Timestamp.valueOf("2016-04-03 21:00:00.0");
		final Long validDeparturePlace = new Long(1);
		final Long validArrivalPlace = new Long(2);
		
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		List<List<Flight>> flightsFlights = flightService.searchForFlights(validStarts, validEnds, validDeparturePlace, validArrivalPlace, 0);
		//Assert
		Assert.assertNotNull(flightsFlights);
		Assert.assertEquals(1, flightsFlights.size());
		Assert.assertEquals(1, flightsFlights.get(0).size());

	}
	
//	@Test
//	public void whenSearchingForFlights_givenValidData_Minus24h_itShouldReturnFlightObject()  {
//		final Timestamp validStarts = Timestamp.valueOf("2016-03-31 21:00:00.0");
//		final Long validDeparturePlace = new Long(1);
//		final Long validArrivalPlace = new Long(2);
//		
//		final FlightService flightService = new FlightServiceImpl();
//		
//		//Act
//		List<List<Flight>> flightsFlights = flightService.searchForFlights(validStarts, validDeparturePlace, validArrivalPlace, 0);
//		//Assert
//		Assert.assertNotNull(flightsFlights);
//		Assert.assertEquals(1, flightsFlights.size());
//		Assert.assertEquals(1, flightsFlights.get(0).size());
//
//	}
	
//	@Test
//	public void whenSearchingForFlights_givenValidData_Plus24h_itShouldReturnFlightObject()  {
//		final Timestamp validStarts = Timestamp.valueOf("2016-04-02 21:00:00.0");
//		final Long validDeparturePlace = new Long(1);
//		final Long validArrivalPlace = new Long(2);
//		
//		final FlightService flightService = new FlightServiceImpl();
//		
//		//Act
//		List<List<Flight>> flightsFlights = flightService.searchForFlights(validStarts, validDeparturePlace, validArrivalPlace, 0);
//		//Assert
//		Assert.assertNotNull(flightsFlights);
//		Assert.assertEquals(1, flightsFlights.size());
//		Assert.assertEquals(1, flightsFlights.get(0).size());
//
//	}
	
//	@Test
//	public void whenSearchingForFlights_givenValidData_Minus48h_itShouldReturnNull()  {
//		final Timestamp validStarts = Timestamp.valueOf("2016-03-30 21:00:00.0");
//		final Long validDeparturePlace = new Long(1);
//		final Long validArrivalPlace = new Long(2);
//		
//		final FlightService flightService = new FlightServiceImpl();
//		
//		//Act
//		List<List<Flight>> flightsFlights = flightService.searchForFlights(validStarts, validDeparturePlace, validArrivalPlace, 0);
//		//Assert
//		Assert.assertNotNull(flightsFlights);
//		Assert.assertEquals(1, flightsFlights.size());
//		Assert.assertEquals(0, flightsFlights.get(0).size());
//
//	}
	
//	@Test
//	public void whenGettingFlightsInTimestamp_givenValidData_itShouldReturnFlightsObject()  {
//		final Timestamp validStarts = Timestamp.valueOf("2016-03-01 21:00:00.0");
//		final Timestamp validEnds = Timestamp.valueOf("2016-10-01 21:00:00.0");
//		
//		final FlightService flightService = new FlightServiceImpl();
//		
//		//Act
//		List<Flight> flights = flightService.getFlightsByTimestamp(validStarts, validEnds);
//		//Assert
//		Assert.assertNotNull(flights);
//		Assert.assertEquals(4, flights.size());
//
//	}
	
	@Test
	public void whenGettingAllFlightsForDeparturePlace_givenValidData__itShouldReturnFlightObject()  {
		final Long validDeparturePlace = new Long(1);
		
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		List<Flight> flights = flightService.getFlightsByDeparturePlace(validDeparturePlace);
		//Assert
		Assert.assertNotNull(flights);
		Assert.assertEquals(4, flights.size());

	}
	
	@Test
	public void whenGettingAllFlightsForArrivalPlace_givenValidData__itShouldReturnFlightObject()  {
		final Long validArrivalPlace = new Long(1);
		
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		List<Flight> flights = flightService.getFlightsByArrivalPlace(validArrivalPlace);
		//Assert
		Assert.assertNotNull(flights);
		Assert.assertEquals(1, flights.size());

	}
	
	@Test
	public void whenAddingValidFlight__itShouldReturnFlightObject()  {
		final Long validFlightId = new Long(6);
		final String validFlightNo = "APRZ12";
		final Timestamp validStarts = Timestamp.valueOf("2016-09-01 21:00:00.0");
		final Timestamp validEnds = Timestamp.valueOf("2016-09-02 02:00:00.0");
		final BigDecimal validBasePrice = new BigDecimal("290.29");
		final Long validDeparturePlace = new Long(1);
		final Long validArrivalPlace = new Long(2);
		final String validPlaneNo = "AIR001";
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		Flight flight = flightService.addFlight(validFlightNo, validStarts, validEnds, validBasePrice, validDeparturePlace, validArrivalPlace, validPlaneNo);
		//Assert
		Assert.assertNotNull(flight);
		Assert.assertEquals(validFlightId, flight.getFlightId());
		Assert.assertEquals(validFlightNo, flight.getFlightNo());
		Assert.assertEquals(validBasePrice, flight.getBasePrice());
		Assert.assertEquals(validEnds, flight.getEnds());
		Assert.assertEquals(validStarts, flight.getStarts());
		Assert.assertEquals(validDeparturePlace, flight.getDeparturePlace().getAirportId());
		Assert.assertEquals(validArrivalPlace, flight.getArrivalPlace().getAirportId());
		Assert.assertEquals(validPlaneNo, flight.getPlaneNo().getPlaneId());

	}
	
	@Test
	public void whenAddingInValidFlight__itShouldReturnnull()  {
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		Flight flight = flightService.addFlight(null, null, null, null, null, null, null);
		Assert.assertNull(flight);
	}
	
	@Test
	public void whenUpdatingValidFlight__itShouldReturnFlightObject()  {
		final Long validFlightId = new Long(1);
		final String validFlightNo = "APRZ12";
		final Timestamp validStarts = Timestamp.valueOf("2016-09-01 21:00:00.0");
		final Timestamp validEnds = Timestamp.valueOf("2016-09-02 02:00:00.0");
		final BigDecimal validBasePrice = new BigDecimal("290.29");
		final Long validDeparturePlace = new Long(1);
		final Long validArrivalPlace = new Long(2);
		final String validPlaneNo = "AIR001";
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		Flight flight = flightService.updateFlight(validFlightId, validFlightNo, validStarts, validEnds, validBasePrice, validDeparturePlace, validArrivalPlace, validPlaneNo);
		//Assert
		Assert.assertNotNull(flight);
		Assert.assertEquals(validFlightId, flight.getFlightId());
		Assert.assertEquals(validFlightNo, flight.getFlightNo());
		Assert.assertEquals(validBasePrice, flight.getBasePrice());
		Assert.assertEquals(validEnds, flight.getEnds());
		Assert.assertEquals(validStarts, flight.getStarts());
		Assert.assertEquals(validDeparturePlace, flight.getDeparturePlace().getAirportId());
		Assert.assertEquals(validArrivalPlace, flight.getArrivalPlace().getAirportId());
		Assert.assertEquals(validPlaneNo, flight.getPlaneNo().getPlaneId());

	}
	
	@Test
	public void whenDeletingFlight_itShouldReturnNull()  {
		final Long validFlightId = new Long(1);
		final FlightService flightService = new FlightServiceImpl();
		
		//Act
		flightService.deleteFlight(validFlightId);
		Flight flight = flightService.getFlight(validFlightId);
		//Assert
		Assert.assertNull(flight);
	}


}
