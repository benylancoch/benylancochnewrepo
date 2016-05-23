package com.airprz.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Airport;
import com.airprz.service.AirportService;
import com.airprz.service.impl.AirportServiceImpl;
import com.airprz.web.model.AirportBean;

@ManagedBean
@SessionScoped
public class AirportController {
	
	private final AirportService airportService;
	
	@ManagedProperty("#{airportBean}")
	private AirportBean airportBean;
	
	public AirportController() {
		airportService = new AirportServiceImpl();
	}
	
	public String saveAirport() {
		
		if(airportBean.getAirportId() == null) {
			airportService.addAirport(airportBean.getName(), airportBean.getCity(), airportBean.getCountry(), airportBean.getAddress(), airportBean.getLocation());
		}
		else {
			airportService.updateAirport(airportBean.getAirportId(), airportBean.getName(), airportBean.getCity(), airportBean.getCountry(), airportBean.getAddress(), airportBean.getLocation());
		}
		
		
		return "index?faces-redirect=true";
	}
	
	public String deleteAirport(Airport airport) {
		
		airportBean.setAirportId(airport.getAirportId());
		airportBean.setName(airport.getName());
		airportBean.setCity(airport.getCity());
		airportBean.setCountry(airport.getCountry());
		airportBean.setAddress(airport.getAddress());
		airportBean.setLocation(airport.getLocation());
		
		airportService.deleteAirport(airportBean.getAirportId());
		
		return "index?faces-redirect=true";
	}
	
	public String redirectNew() {
		airportBean.setAirportId(null);
		airportBean.setName(null);
		airportBean.setCity(null);
		airportBean.setCountry(null);
		airportBean.setAddress(null);
		airportBean.setLocation(null);
		
		return "new?faces-redirect=true";
	}
	
	public String redirectEdit(Airport airport) {
		
		airportBean.setAirportId(airport.getAirportId());
		airportBean.setName(airport.getName());
		airportBean.setCity(airport.getCity());
		airportBean.setCountry(airport.getCountry());
		airportBean.setAddress(airport.getAddress());
		airportBean.setLocation(airport.getLocation());


		return "new?faces-redirect=true";
	}
	
	public void setAirportBean(AirportBean airportBean) {
		this.airportBean = airportBean;
	}

}
