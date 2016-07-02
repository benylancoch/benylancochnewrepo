package com.airprz.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Flight;


@ManagedBean
@SessionScoped
public class SeatSearchBean {
	
	private String rowPlace;
	private String columnPlace;
	private boolean nextToEachOther;
	private boolean differnetPerson;
	private String otherPersonName;
	private String otherPersonPhone;
	private Long otherPersonHonorific;
	private Flight selectedFlight;
	private Long classSelected;
	
	public SeatSearchBean() {
		rowPlace = new String("S");
		columnPlace = new String("M");
		nextToEachOther = false;
		differnetPerson = false;
		otherPersonHonorific = new Long(0);
		otherPersonName = null;
		otherPersonPhone = null;
	}

	public String getRowPlace() {
		return rowPlace;
	}

	public void setRowPlace(String rowPlace) {
		this.rowPlace = rowPlace;
	}

	public String getColumnPlace() {
		return columnPlace;
	}

	public void setColumnPlace(String columnPlace) {
		this.columnPlace = columnPlace;
	}

	public boolean isNextToEachOther() {
		return nextToEachOther;
	}

	public void setNextToEachOther(boolean nextToEachOther) {
		this.nextToEachOther = nextToEachOther;
	}

	public boolean isDiffernetPerson() {
		return differnetPerson;
	}

	public void setDiffernetPerson(boolean differnetPerson) {
		this.differnetPerson = differnetPerson;
	}

	public String getOtherPersonName() {
		return otherPersonName;
	}

	public void setOtherPersonName(String otherPersonName) {
		this.otherPersonName = otherPersonName;
	}

	public String getOtherPersonPhone() {
		return otherPersonPhone;
	}

	public void setOtherPersonPhone(String otherPersonPhone) {
		this.otherPersonPhone = otherPersonPhone;
	}

	public Long getOtherPersonHonorific() {
		return otherPersonHonorific;
	}

	public void setOtherPersonHonorific(Long otherPErsonHonorific) {
		this.otherPersonHonorific = otherPErsonHonorific;
	}
	
	public void changeOtherPerson() {
		if(this.differnetPerson) {
			this.differnetPerson = false;
		}
		else {
			this.differnetPerson = true;
		}
	}

	public Flight getSelectedFlight() {
		return selectedFlight;
	}

	public void setSelectedFlight(Flight selectedFlight) {
		this.selectedFlight = selectedFlight;
	}

	public Long getClassSelected() {
		return classSelected;
	}

	public void setClassSelected(Long classSelected) {
		this.classSelected = classSelected;
	}

}
