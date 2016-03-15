package com.airprz.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Flight {
	
	private Long flightId;
	private String flightNo;
	private Timestamp starts;
	private Timestamp ends;
	private BigDecimal basePrice;
	private Airport departurePlace;
	private Airport arrivalPlace;
	private Plane planeNo;
	
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
	public Timestamp getStarts() {
		return starts;
	}
	public void setStarts(Timestamp starts) {
		this.starts = starts;
	}
	public Timestamp getEnds() {
		return ends;
	}
	public void setEnds(Timestamp ends) {
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
	
	

}
