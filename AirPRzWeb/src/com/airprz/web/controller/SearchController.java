package com.airprz.web.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Flight;
import com.airprz.service.FlightService;
import com.airprz.service.impl.FlightServiceImpl;
import com.airprz.web.model.SearchBean;


@ManagedBean
@SessionScoped
public class SearchController {
	
	private final FlightService flightService;
	
	@ManagedProperty("#{searchBean}")
	private SearchBean searchBean;
	
	public SearchController() {
		flightService = new FlightServiceImpl();
	}
	
	public String search() {
		
		return "/views/search/index?faces-redirect=true";
	}
	
	public List<List<Flight>> listSearchResults() {
		
		return flightService.searchForFlights(searchBean.getFlyOutDateStart(), searchBean.getFlyOutDateEnd(), 
				searchBean.getDeparturePlace(), searchBean.getArrivalPlace(), searchBean.getNoOfTransfers());

	}
	
	public Date estTime(Date starts, Date ends) {
		Date temp = new Date(starts.getTime() - ends.getTime());
		
		return temp;
	}
	
	public BigDecimal economyPrice(Flight flight) {
		return flight.getBasePrice().multiply(new BigDecimal("1.0"));
	}
	
	public BigDecimal businessPrice(Flight flight) {
		return flight.getBasePrice().multiply(new BigDecimal("1.5"));
	}
	
	public BigDecimal firstClassPrice(Flight flight) {
		return flight.getBasePrice().multiply(new BigDecimal("2.5"));
	}
	
	public void setSearchBean(SearchBean searchBean) {
		this.searchBean = searchBean;
	}

}
