package com.airprz.service.impl;

import java.util.List;

import com.airprz.model.Airport;

public class AirportServiceImpl {
	
	private final AirportDao airportDao;
	
	public AirportServiceImpl() {
		this.airportDao = new AirportDaoImpl();
	}
	
	public Airport getAirport(Long airportId) {
		
		Airport airport = null;
		
		
		if (airportId != null) {
			airport = airportDao.getAirport(airportId);
		}
		
		return airport;
	}
	
	
	public List<Airport> getAirports() {
		
		return airportDao.getAirports();
	}
	
	public Airport addAirport(String name, String city, String country, String address, String location) {
		
		Airport airport = null;
		if (name != null && city != null && country != null && address != null && location != null
				&& !"".equals(name) && !"".equals(city) && !"".equals(country) && !"".equals(address)) {
			airport = new Airport();
			airport.setName(name);
			airport.setCity(city);
			airport.setCountry(country);
			airport.setAddress(address);
			airport.setLocation(location);
			airport = airportDao.saveUser(airport);
		}
		
		return airport;
	}
	
	public Airport updateAirport(Long airportId, String name, String city, String country, String address, String location) {
		
		Airport airport = airportDao.getAirport(airportId);
		
		if (airport != null) {
			airport = new Airport();
			airport.setName(name);
			airport.setCity(city);
			airport.setCountry(country);
			airport.setAddress(address);
			airport.setLocation(location);
			airport = airportDao.saveUser(airport);
		}
		else {
			airport = null;
		}
		
		return airport;
	}
	
	public void deleteAirport(Long airportId) {
		
		Airport airport = airportDao.getAirport(airportId);
		if (airport != null) {
			airportDao.deleteAirport(airportId);
		}
	}

}
