package com.airprz.web.model;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FlightSearchBean {
	
	private Date startDate;
	private Date endDate;
	
	public FlightSearchBean() {
		Calendar date1monthPlus = Calendar.getInstance();
		date1monthPlus.add(Calendar.MONTH, 1);
		
		startDate = new Date();
		endDate = date1monthPlus.getTime();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	

}
