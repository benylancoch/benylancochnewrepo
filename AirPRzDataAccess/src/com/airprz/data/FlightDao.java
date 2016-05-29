package com.airprz.data;

import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Flight;

public interface FlightDao {
	
	public Flight getFlight(Long flightId);
	
	public List<List<Flight>> searchForFlights(Timestamp starts, Timestamp ends, Long departurePlace, Long arrivalPlace, int noOfTransfers);
	
	public List<Flight> getFlightsByTimestamp(Timestamp starts, Timestamp ends);
	
	public List<Flight> getFlightsByDeparturePlace(Long departurePlace);
	
	public List<Flight> getFlightsByArrivalPlace(Long arrivalPlace);
	
	public Flight saveFlight(Flight flight);
	
	public void deleteFlight(Long flightId);

}
