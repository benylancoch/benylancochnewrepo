package com.airprz.web.model;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Plane;
import com.airprz.service.PlaneService;
import com.airprz.service.impl.PlaneServiceImpl;

@ManagedBean
@SessionScoped
public class PlaneBean {
	
	private final PlaneService planeService;
	
	private String planeId;
	private String manufacturer;
	private String model;
	private Long classes;
	private Long seats;
	
	public PlaneBean() {
		planeService = new PlaneServiceImpl();
	}

	public String getPlaneId() {
		return planeId;
	}

	public void setPlaneId(String planeId) {
		this.planeId = planeId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Long getClasses() {
		return classes;
	}

	public void setClasses(Long classes) {
		this.classes = classes;
	}

	public Long getSeats() {
		return seats;
	}

	public void setSeats(Long seats) {
		this.seats = seats;
	}

	public PlaneService getPlaneService() {
		return planeService;
	}
	
	public List<Plane> getListPlanes() {
		
		return planeService.getPlanes();
	}

}
