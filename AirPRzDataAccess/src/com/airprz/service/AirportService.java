package com.airprz.service;

import java.util.List;

import com.airprz.model.Airport;

public interface AirportService {
	
	Airport getAirport(Long airportId);
	
	List<Airport> getAirports();
	
	Airport addAirport(String name, String city, String country, String address, String location);
	
	Airport updateAirport(Long airportId, String name, String city, String country, String address, String location);
	
	void deleteAirport(Long airportId);

}
