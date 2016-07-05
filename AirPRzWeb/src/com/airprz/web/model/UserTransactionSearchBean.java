package com.airprz.web.model;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserTransactionSearchBean {
	
	private Date startDate;
	private Date endDate;
	
	public UserTransactionSearchBean() {
		Calendar date1monthMinus = Calendar.getInstance();
		date1monthMinus.add(Calendar.MONTH, -1);
		
		endDate = new Date();
		startDate = date1monthMinus.getTime();
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
