package com.airprz.data;

import java.util.List;

import com.airprz.model.Airport;

public interface AirportDao {
	
	Airport getAirport(Long airportId);
	
	List<Airport> getAirports();
	
	Airport saveAirport(Airport airport);
	
	void deleteAirport(Long airportId);

}
