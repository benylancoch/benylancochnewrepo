package com.airprz.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.airprz.model.Flight;

public interface FlightService {
	
	public Flight getFlight(Long flightId);
	
	public List<List<Flight>> searchForFlights(Date starts, Date ends, Long departurePlace, Long arrivalPlace, int noOfTransfers);
	
	public List<Flight> getFlightsByDate(Date starts, Date ends);
	
	public List<Flight> getFlightsByDeparturePlace(Long departurePlace);
	
	public List<Flight> getFlightsByArrivalPlace(Long arrivalPlace);
	
	public Flight addFlight(String flightNo, Date starts, Date ends, BigDecimal basePrice, Long departurePlace, Long arrivalPlace, String planeNo);
	
	public Flight updateFlight(Long flightId, String flightNo, Date starts, Date ends, BigDecimal basePrice, Long departurePlace, Long arrivalPlace, String planeNo);
	
	public void deleteFlight(Long flightId);

}
