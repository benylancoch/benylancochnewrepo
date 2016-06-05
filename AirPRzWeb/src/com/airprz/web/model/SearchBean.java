package com.airprz.web.model;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class SearchBean {
	
	private Boolean oneWay;
	private Long departurePlace;
	private Long arrivalPlace;
	private Date flyOutDateStart;
	private Date flyOutDateEnd;
	private Date flyBackDateStart;
	private Date flyBackDateEnd;
	private int noOfTransfers;
	
	public SearchBean() {
		oneWay = false;
		
		Calendar dateCalendar = Calendar.getInstance();
		dateCalendar.add(Calendar.DAY_OF_WEEK, 7);
		
		flyOutDateStart = new Date(); //currentDay
		flyOutDateEnd = dateCalendar.getTime(); //1 week plus
		
		
		dateCalendar.add(Calendar.DAY_OF_WEEK, 7); //2 weeks plus
		flyBackDateStart = dateCalendar.getTime();
		
		dateCalendar.add(Calendar.DAY_OF_WEEK, 7); //3 weeks plus
		flyBackDateEnd = dateCalendar.getTime();
	}

	public Boolean getOneWay() {
		return oneWay;
	}

	public void setOneWay(Boolean oneWay) {
		this.oneWay = oneWay;
	}
	
	public void changeOneWay() {
		if(this.oneWay) {
			this.oneWay = false;
		}
		else {
			this.oneWay = true;
		}
	}

	public Long getDeparturePlace() {
		return departurePlace;
	}

	public void setDeparturePlace(Long departurePlace) {
		this.departurePlace = departurePlace;
	}

	public Long getArrivalPlace() {
		return arrivalPlace;
	}

	public void setArrivalPlace(Long arrivalPlace) {
		this.arrivalPlace = arrivalPlace;
	}

	public Date getFlyOutDateStart() {
		return flyOutDateStart;
	}

	public void setFlyOutDateStart(Date flyOutDateStart) {
		this.flyOutDateStart = flyOutDateStart;
	}

	public Date getFlyOutDateEnd() {
		return flyOutDateEnd;
	}

	public void setFlyOutDateEnd(Date flyOutDateEnd) {
		this.flyOutDateEnd = flyOutDateEnd;
	}

	public Date getFlyBackDateStart() {
		return flyBackDateStart;
	}

	public void setFlyBackDateStart(Date flyBackDateStart) {
		this.flyBackDateStart = flyBackDateStart;
	}

	public Date getFlyBackDateEnd() {
		return flyBackDateEnd;
	}

	public void setFlyBackDateEnd(Date flyBackDateEnd) {
		this.flyBackDateEnd = flyBackDateEnd;
	}

	public int getNoOfTransfers() {
		return noOfTransfers;
	}

	public void setNoOfTransfers(int noOfTransfers) {
		this.noOfTransfers = noOfTransfers;
	}
	
	

}
