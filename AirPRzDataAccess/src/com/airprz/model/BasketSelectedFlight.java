package com.airprz.model;

import java.math.BigDecimal;
import java.util.List;

public class BasketSelectedFlight {
	
	private List<Flight> selectedFlight;
	private String classSelected;
	private BigDecimal classPrice;
	private Long noOfTickets;
	
	public List<Flight> getSelectedFlight() {
		return selectedFlight;
	}
	public void setSelectedFlight(List<Flight> selectedFlight) {
		this.selectedFlight = selectedFlight;
	}
	public String getClassSelected() {
		return classSelected;
	}
	public void setClassSelected(String classSelected) {
		this.classSelected = classSelected;
	}
	public BigDecimal getClassPrice() {
		return classPrice;
	}
	public void setClassPrice(BigDecimal classPrice) {
		this.classPrice = classPrice;
	}
	public Long getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Long noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	
}
