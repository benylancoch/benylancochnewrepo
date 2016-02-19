package com.airprz.web.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.ReferenceSeat;

@ManagedBean
@SessionScoped
public class PlaneSeatsBean {
	
	private List<List<ReferenceSeat>> planeSeats;
	private int columns;
	private String planeNo;
	
	public PlaneSeatsBean() {
		columns = 10;
	}

	public List<List<ReferenceSeat>> getPlaneSeats() {
		return planeSeats;
	}

	public void setPlaneSeats(List<List<ReferenceSeat>> planeSeats) {
		this.planeSeats = planeSeats;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public String getPlaneNo() {
		return planeNo;
	}

	public void setPlaneNo(String planeNo) {
		this.planeNo = planeNo;
	}
	
	

}
