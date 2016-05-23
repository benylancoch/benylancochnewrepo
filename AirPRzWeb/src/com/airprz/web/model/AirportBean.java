package com.airprz.web.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Airport;
import com.airprz.service.AirportService;
import com.airprz.service.impl.AirportServiceImpl;



@ManagedBean
@SessionScoped
public class AirportBean {
	
	private final AirportService airportService;
	
	private Long airportId;
	private String name;
	private String city;
	private String country;
	private String address;
	private String location;
	
	public AirportBean() {
		airportService = new AirportServiceImpl();
	}
	
	public Long getAirportId() {
		return airportId;
	}
	public void setAirportId(Long airportId) {
		this.airportId = airportId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public List<Airport> getlistAirports() {
		
		return airportService.getAirports();
	}
	
	

}
