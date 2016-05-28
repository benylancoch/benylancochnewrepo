package com.airprz.web.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Airport;
import com.airprz.model.Flight;
import com.airprz.model.Plane;
import com.airprz.service.FlightService;
import com.airprz.service.impl.AirportServiceImpl;
import com.airprz.service.impl.FlightServiceImpl;
import com.airprz.service.impl.PlaneServiceImpl;

@ManagedBean
@SessionScoped
public class FlightBean {
	
	private final FlightService flightService;
	
	private Long flightId;
	private String flightNo;
	private Date starts;
	private Date ends;
	private BigDecimal basePrice;
	private Airport departurePlace;
	private Airport arrivalPlace;
	private Plane planeNo;
	
	private Long departurePlaceId;
	private Long arrivalPlaceId;
	private String planeId;
	
	public FlightBean() {
		flightService = new FlightServiceImpl();
	}

	public Long getFlightId() {
		return flightId;
	}

	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public Date getStarts() {
		return starts;
	}

	public void setStarts(Date starts) {
		this.starts = starts;
	}

	public Date getEnds() {
		return ends;
	}

	public void setEnds(Date ends) {
		this.ends = ends;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public Airport getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(Airport departurePlace) {
		this.departurePlace = departurePlace;
	}

	public Airport getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(Airport arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public Plane getPlaneNo() {
		return planeNo;
	}

	public void setPlaneNo(Plane planeNo) {
		this.planeNo = planeNo;
	}
	
	public List<Flight> listFlights(Date start, Date end) {
		
		return flightService.getFlightsByDate(start, end);
	}
	
	public List<Airport> getListAirports() {
		
		return new AirportServiceImpl().getAirports();
	}
	
	public List<Plane> getListPlanes() {
		
		return new PlaneServiceImpl().getPlanes();
	}

	public Long getDeparturePlaceId() {
		return departurePlaceId;
	}

	public void setDeparturePlaceId(Long departurePlaceId) {
		this.departurePlaceId = departurePlaceId;
	}

	public Long getArrivalPlaceId() {
		return arrivalPlaceId;
	}

	public void setArrivalPlaceId(Long arrivalPlaceId) {
		this.arrivalPlaceId = arrivalPlaceId;
	}

	public String getPlaneId() {
		return planeId;
	}

	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}

}
