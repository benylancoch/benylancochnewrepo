package com.airprz.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.airprz.data.FlightDao;
import com.airprz.data.impl.FlightDaoImpl;
import com.airprz.model.Airport;
import com.airprz.model.Flight;
import com.airprz.model.Plane;
import com.airprz.service.FlightService;

public class FlightServiceImpl implements FlightService {
	
	private final FlightDao flightDao;
	
	public FlightServiceImpl() {
		this.flightDao = new FlightDaoImpl();
	}
	
	@Override
	public Flight getFlight(Long flightId) {
		return flightDao.getFlight(flightId);
	}
	
	@Override
	public List<List<Flight>> searchForFlights(Date starts, Date ends,
			Long departurePlace, Long arrivalPlace, int noOfTransfers) {
		java.util.Date currentDate = new java.util.Date();
		Calendar date1dayback = Calendar.getInstance();
		date1dayback.add(Calendar.DAY_OF_WEEK, -1);
		
		currentDate = date1dayback.getTime();
		
		if(starts.after(currentDate)) {
			
			return flightDao.searchForFlights(new Timestamp(starts.getTime()), new Timestamp(ends.getTime()), departurePlace, arrivalPlace, noOfTransfers);
		}
		else {
			
			return flightDao.searchForFlights(new Timestamp(new Date().getTime()), new Timestamp(ends.getTime()), departurePlace, arrivalPlace, noOfTransfers);
		}
		
	}
	
	
	@Override
	public List<Flight> getFlightsByDate(Date starts, Date ends) {
		Timestamp startsT = null;
		Timestamp endsT = null;
		
		if (starts != null) {
			startsT = new Timestamp(starts.getTime());
		}
		
		if (ends != null) {
			endsT = new Timestamp(ends.getTime());
		}
		return flightDao.getFlightsByTimestamp(startsT, endsT);
	}
	
	@Override
	public List<Flight> getFlightsByDeparturePlace(Long departurePlace) {
		return flightDao.getFlightsByDeparturePlace(departurePlace);
	}
	
	@Override
	public List<Flight> getFlightsByArrivalPlace(Long arrivalPlace) {
		return flightDao.getFlightsByArrivalPlace(arrivalPlace);
	}
	
	@Override
	public Flight addFlight(String flightNo, Date starts, Date ends,
			BigDecimal basePrice, Long departurePlace, Long arrivalPlace,
			String planeNo) {
		
		Flight flight = null;
		Airport departurePlaceObject = null;
		Airport arrivalPlaceObject = null;
		Plane planeObject = null;
		
		
		if (flightNo != null && starts != null && ends != null && basePrice != null && departurePlace != null && arrivalPlace != null && planeNo != null
				&& !"".equals(flightNo) && !"".equals(planeNo)) {
			flight = new Flight();
			departurePlaceObject = new Airport();
			arrivalPlaceObject = new Airport();
			planeObject = new Plane();
			
			flight.setFlightNo(flightNo);
			flight.setStarts(new Timestamp(starts.getTime()));
			flight.setEnds(new Timestamp(ends.getTime()));
			flight.setBasePrice(basePrice);
			
			departurePlaceObject.setAirportId(departurePlace);
			arrivalPlaceObject.setAirportId(arrivalPlace);
			planeObject.setPlaneId(planeNo);
			
			flight.setDeparturePlace(departurePlaceObject);
			flight.setArrivalPlace(arrivalPlaceObject);
			flight.setPlaneNo(planeObject);
			flight = flightDao.saveFlight(flight);
		}
		else {
			flight = null;
		}
		
		return flight;
	}
	
	@Override
	public Flight updateFlight(Long flightId, String flightNo,
			Date starts, Date ends, BigDecimal basePrice,
			Long departurePlace, Long arrivalPlace, String planeNo) {
		
		Flight flight = flightDao.getFlight(flightId);
		
		if(flight != null) {
			
			flight = new Flight();
			Airport departurePlaceObject = new Airport();
			Airport arrivalPlaceObject = new Airport();
			Plane planeObject = new Plane();
			
			flight.setFlightId(flightId);
			flight.setFlightNo(flightNo);
			flight.setStarts(new Timestamp(starts.getTime()));
			flight.setEnds(new Timestamp(ends.getTime()));
			flight.setBasePrice(basePrice);
			
			departurePlaceObject.setAirportId(departurePlace);
			arrivalPlaceObject.setAirportId(arrivalPlace);
			planeObject.setPlaneId(planeNo);
			
			flight.setDeparturePlace(departurePlaceObject);
			flight.setArrivalPlace(arrivalPlaceObject);
			flight.setPlaneNo(planeObject);
			flight = flightDao.saveFlight(flight);
		}
		else {
			
			flight = null;
		}
		
		return flight;
	}
	
	@Override
	public void deleteFlight(Long flightId) {
		
		Flight flight = flightDao.getFlight(flightId);
		if (flight != null) {
			flightDao.deleteFlight(flightId);
		}
		
	}

}
