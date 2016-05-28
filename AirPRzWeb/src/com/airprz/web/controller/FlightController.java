package com.airprz.web.controller;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Flight;
import com.airprz.service.FlightService;
import com.airprz.service.impl.FlightServiceImpl;
import com.airprz.web.model.FlightBean;

@ManagedBean
@SessionScoped
public class FlightController {
	
private final FlightService flightService;
	
	@ManagedProperty("#{flightBean}")
	private FlightBean flightBean;
	
	public FlightController() {
		flightService = new FlightServiceImpl();
	}
	
	public String saveFlight() {
		
		if(flightBean.getFlightId() == null) {
			flightService.addFlight(flightBean.getFlightNo(), flightBean.getStarts(), flightBean.getEnds(), flightBean.getBasePrice(), 
					flightBean.getDeparturePlaceId(), flightBean.getArrivalPlaceId(), flightBean.getPlaneId());
		}
		else {
			flightService.updateFlight(flightBean.getFlightId(), flightBean.getFlightNo(), flightBean.getStarts(), flightBean.getEnds(), flightBean.getBasePrice(), 
					flightBean.getDeparturePlaceId(), flightBean.getArrivalPlaceId(), flightBean.getPlaneId());
		}
		
		
		return "management?faces-redirect=true";
	}
	
	public String deleteFlight(Flight flight) {
		
		flightBean.setFlightId(flight.getFlightId());
		flightBean.setFlightNo(flight.getFlightNo());
		flightBean.setStarts(flight.getStarts());
		flightBean.setEnds(flight.getEnds());
		flightBean.setBasePrice(flight.getBasePrice());
		flightBean.setDeparturePlace(flight.getDeparturePlace());
		flightBean.setArrivalPlace(flight.getArrivalPlace());
		flightBean.setPlaneNo(flight.getPlaneNo());
		flightBean.setArrivalPlaceId(flight.getArrivalPlace().getAirportId());
		flightBean.setDeparturePlaceId(flight.getDeparturePlace().getAirportId());
		flightBean.setPlaneId(flight.getPlaneNo().getPlaneId());
		
		flightService.deleteFlight(flightBean.getFlightId());
		
		return "index?faces-redirect=true";
	}
	
	public String redirectNew() {
		
		flightBean.setFlightId(null);
		flightBean.setFlightNo(null);
		flightBean.setStarts(null);
		flightBean.setEnds(null);
		flightBean.setBasePrice(null);
		flightBean.setDeparturePlace(null);
		flightBean.setArrivalPlace(null);
		flightBean.setPlaneNo(null);
		flightBean.setArrivalPlaceId(null);
		flightBean.setDeparturePlaceId(null);
		flightBean.setPlaneId(null);
		
		return "new?faces-redirect=true";
	}
	
	public String redirectEdit(Flight flight) {
		
		flightBean.setFlightId(flight.getFlightId());
		flightBean.setFlightNo(flight.getFlightNo());
		flightBean.setStarts(flight.getStarts());
		flightBean.setEnds(flight.getEnds());
		flightBean.setBasePrice(flight.getBasePrice());
		flightBean.setDeparturePlace(flight.getDeparturePlace());
		flightBean.setArrivalPlace(flight.getArrivalPlace());
		flightBean.setPlaneNo(flight.getPlaneNo());
		flightBean.setArrivalPlaceId(flight.getArrivalPlace().getAirportId());
		flightBean.setDeparturePlaceId(flight.getDeparturePlace().getAirportId());
		flightBean.setPlaneId(flight.getPlaneNo().getPlaneId());


		return "new?faces-redirect=true";
	}
	
	public void setFlightBean(FlightBean flightBean) {
		this.flightBean = flightBean;
	}
	

}
