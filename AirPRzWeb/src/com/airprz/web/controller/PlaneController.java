package com.airprz.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Plane;
import com.airprz.service.PlaneService;
import com.airprz.service.impl.PlaneServiceImpl;
import com.airprz.web.model.PlaneBean;

@ManagedBean
@SessionScoped
public class PlaneController {
	
	private final PlaneService planeService;
	
	@ManagedProperty("#{planeBean}")
	private PlaneBean planeBean;
	
	public PlaneController() {
		planeService = new PlaneServiceImpl();
	}
	
	public String addPlane() {
		
		planeService.addPlane(planeBean.getPlaneId(), planeBean.getManufacturer(), planeBean.getModel(), planeBean.getClasses(), planeBean.getSeats());
		
		return "index?faces-redirect=true";
	}
	
	public String editPlane() {
		
		planeService.updatePlane(planeBean.getPlaneId(), planeBean.getManufacturer(), planeBean.getModel(), planeBean.getClasses(), planeBean.getSeats());
		
		return "index?faces-redirect=true";
	}
	
	public String deletePlane(Plane plane) {
		
		planeBean.setPlaneId(plane.getPlaneId());
		planeBean.setManufacturer(plane.getManufacturer());
		planeBean.setModel(plane.getModel());
		planeBean.setClasses(plane.getClasses());
		planeBean.setSeats(plane.getSeats());
		
		planeService.deletePlane(planeBean.getPlaneId());
		
		return "index?faces-redirect=true";
	}
	
	public String redirectNew() {
		planeBean.setPlaneId(null);
		planeBean.setManufacturer(null);
		planeBean.setModel(null);
		planeBean.setClasses(null);
		planeBean.setSeats(null);
		
		return "new?faces-redirect=true";
	}
	
	public String redirectEdit(Plane plane) {
		
		planeBean.setPlaneId(plane.getPlaneId());
		planeBean.setManufacturer(plane.getManufacturer());
		planeBean.setModel(plane.getModel());
		planeBean.setClasses(plane.getClasses());
		planeBean.setSeats(plane.getSeats());


		return "edit?faces-redirect=true";
	}
	
	public void setPlaneBean(PlaneBean planeBean) {
		this.planeBean = planeBean;
	}

}
