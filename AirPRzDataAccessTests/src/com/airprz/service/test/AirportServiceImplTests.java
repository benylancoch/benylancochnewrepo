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
import com.airprz.model.Airport;
import com.airprz.service.AirportService;
import com.airprz.service.impl.AirportServiceImpl;

public class AirportServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenGettingAirport_givenValidAirportId_itShouldReturnAirportObject()  {
		final Long validAirportId = new Long(1);
		final String validName = "KRAKOW INTERNATIONAL AIRPORT";
		final String validCity = "KRAKOW";
		final String validCountry = "POLAND";
		final String validAddress = "SOME STREET 2";
		final String validLocation = "";
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.getAirport(validAirportId);
		
		//Assert
		Assert.assertNotNull(airport);
		Assert.assertEquals(validAirportId, airport.getAirportId());
		Assert.assertEquals(validName, airport.getName());
		Assert.assertEquals(validCity, airport.getCity());
		Assert.assertEquals(validCountry, airport.getCountry());
		Assert.assertEquals(validAddress, airport.getAddress());
		Assert.assertEquals(null, airport.getLocation());
	}
	
	@Test
	public void whenGettingAirport_givenInvalidAirportId_itShouldReturnnull()  {
		final Long validAirportId = new Long(100);
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.getAirport(validAirportId);
		
		//Assert
		Assert.assertNull(airport);
	}
	
	@Test
	public void whenGettingAllAirports__itShouldReturnAirportsObject()  {
		List<Airport> validAirports = new ArrayList<Airport>();
		Airport validAirport1 = new Airport();
		final Long validAirport1Id = new Long(1);
		validAirport1.setAirportId(validAirport1Id);
		validAirport1.setName("KRAKOW INTERNATIONAL AIRPORT");
		validAirport1.setCity("KRAKOW");
		validAirport1.setCountry("POLAND");
		validAirport1.setAddress("SOME STREET 2");
		validAirport1.setLocation(null);
		
		Airport validAirport2 = new Airport();
		final Long validAirport2Id = new Long(2);
		validAirport2.setAirportId(validAirport2Id);
		validAirport2.setName("RZESZOW INTERNATIONAL AIRPORT");
		validAirport2.setCity("RZESZOW");
		validAirport2.setCountry("POLAND");
		validAirport2.setAddress("SOME STREET 2");
		validAirport2.setLocation(null);
		
		validAirports.add(validAirport1);
		validAirports.add(validAirport2);

		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		List<Airport> airports = airportService.getAirports();
		
		Airport airport1 = airports.get(0);
		Airport airport2 = airports.get(1);
		//Assert
		Assert.assertNotNull(airports);
		Assert.assertEquals(validAirports.size(), airports.size());
		Assert.assertNotNull(airport1);
		Assert.assertEquals(validAirport1.getAirportId(), airport1.getAirportId());
		Assert.assertEquals(validAirport1.getName(), airport1.getName());
		Assert.assertEquals(validAirport1.getCity(), airport1.getCity());
		Assert.assertEquals(validAirport1.getCountry(), airport1.getCountry());
		Assert.assertEquals(validAirport1.getAddress(), airport1.getAddress());
		Assert.assertEquals(null, airport1.getLocation());
		
		Assert.assertNotNull(airport2);
		Assert.assertEquals(validAirport2.getAirportId(), airport2.getAirportId());
		Assert.assertEquals(validAirport2.getName(), airport2.getName());
		Assert.assertEquals(validAirport2.getCity(), airport2.getCity());
		Assert.assertEquals(validAirport2.getCountry(), airport2.getCountry());
		Assert.assertEquals(validAirport2.getAddress(), airport2.getAddress());
		Assert.assertEquals(null, airport2.getLocation());
	}
	
	@Test
	public void whenAddingAirport_givenValidAirport_itShouldReturnAirportObject()  {
		final Long validAirportId = new Long(3);
		final String validName = "WROCLAW INTERNATIONAL AIRPORT";
		final String validCity = "WROCLAW";
		final String validCountry = "POLAND";
		final String validAddress = "SOME STREET 234567";
		final String validLocation = "123.123:123.123";
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.addAirport(validName, validCity, validCountry, validAddress, validLocation);
		
		//Assert
		Assert.assertNotNull(airport);
		Assert.assertEquals(validAirportId, airport.getAirportId());
		Assert.assertEquals(validName, airport.getName());
		Assert.assertEquals(validCity, airport.getCity());
		Assert.assertEquals(validCountry, airport.getCountry());
		Assert.assertEquals(validAddress, airport.getAddress());
		Assert.assertEquals(validLocation, airport.getLocation());
	}
	
	@Test
	public void whenAddingAirport_givenInvalidAirport_itShouldReturnNull()  {
		final String invalidName = "";
		final String invalidCity = "";
		final String invalidCountry = "";
		final String invalidAddress = "";
		final String invalidLocation = "";
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.addAirport(invalidName, invalidCity, invalidCountry, invalidAddress, invalidLocation);
		
		//Assert
		Assert.assertNull(airport);
	}
	
	@Test
	public void whenUpdatingAirport_givenValidAirportId_itShouldReturnAirportObject()  {
		final Long validAirportId = new Long(2);
		final String validName = "WROCLAW INTERNATIONAL AIRPORT";
		final String validCity = "WROCLAW";
		final String validCountry = "POLAND";
		final String validAddress = "SOME STREET 234567";
		final String validLocation = "123.123:123.123";
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.updateAirport(validAirportId, validName, validCity, validCountry, validAddress, validLocation);
		
		//Assert
		Assert.assertNotNull(airport);
		Assert.assertEquals(validAirportId, airport.getAirportId());
		Assert.assertEquals(validName, airport.getName());
		Assert.assertEquals(validCity, airport.getCity());
		Assert.assertEquals(validCountry, airport.getCountry());
		Assert.assertEquals(validAddress, airport.getAddress());
		Assert.assertEquals(validLocation, airport.getLocation());
	}
	
	@Test
	public void whenUpdatingAirport_givenInValidAirportId_itShouldReturnNull()  {
		final Long validAirportId = new Long(30);
		final String validName = "WROCLAW INTERNATIONAL AIRPORT";
		final String validCity = "WROCLAW";
		final String validCountry = "POLAND";
		final String validAddress = "SOME STREET 234567";
		final String validLocation = "123.123:123.123";
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		Airport airport = airportService.updateAirport(validAirportId, validName, validCity, validCountry, validAddress, validLocation);
		
		//Assert
		Assert.assertNull(airport);
	}
	
	@Test
	public void whenDeletingAirport_itShouldReturnNull()  {
		final Long validAirportId = new Long(30);
		final AirportService airportService = new AirportServiceImpl();
		
		//Act
		airportService.deleteAirport(validAirportId);
		
		Airport airport = airportService.getAirport(validAirportId);
		
		//Assert
		Assert.assertNull(airport);
	}

}
