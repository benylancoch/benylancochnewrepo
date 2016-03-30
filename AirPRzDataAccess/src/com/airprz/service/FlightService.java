package com.airprz.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Flight;

public interface FlightService {
	
	public Flight getFlight(Long flightId);
	
	public List<List<Flight>> searchForFlights(Timestamp starts, Long departurePlace, Long arrivalPlace, int noOfTransfers);
	
	public List<Flight> getFlightsByTimestamp(Timestamp starts, Timestamp ends);
	
	public List<Flight> getFlightsByDeparturePlace(Long departurePlace);
	
	public List<Flight> getFlightsByArrivalPlace(Long arrivalPlace);
	
	public Flight addFlight(String flightNo, Timestamp starts, Timestamp ends, BigDecimal basePrice, Long departurePlace, Long arrivalPlace, String planeNo);
	
	public Flight updateFlight(Long flightId, String flightNo, Timestamp starts, Timestamp ends, BigDecimal basePrice, Long departurePlace, Long arrivalPlace, String planeNo);
	
	public void deleteFlight(Long flightId);

}
