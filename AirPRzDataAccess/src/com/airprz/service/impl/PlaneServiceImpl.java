package com.airprz.service.impl;

import java.util.List;

import com.airprz.data.PlaneDao;
import com.airprz.data.impl.PlaneDaoImpl;
import com.airprz.model.Plane;
import com.airprz.service.PlaneService;

public class PlaneServiceImpl implements PlaneService {
	
	private final PlaneDao planeDao;
	
	public PlaneServiceImpl() {
		this.planeDao = new PlaneDaoImpl();
	}
	
	public Plane getPlane(String planeNo) {
		Plane plane = null;
		
		
		if (planeNo != null) {
			plane = planeDao.getPlane(planeNo);
		}
		
		return plane;
	}
	
	public List<Plane> getPlanes() {
		
		return planeDao.getPlanes();
	}
	
	public Plane addPlane(String planeNo, String manufacturer, String model, Long classes, Long seats) {
		
		Plane plane = planeDao.getPlane(planeNo);
		
		if (plane == null && planeNo != null && manufacturer != null && model != null
				&& !"".equals(planeNo) && !"".equals(manufacturer) && !"".equals(model)) {
			plane = new Plane();
			plane.setPlaneId(planeNo);
			plane.setManufacturer(manufacturer);
			plane.setModel(model);
			plane.setClasses(classes);
			plane.setSeats(seats);
			plane = planeDao.savePlane(plane, false);
		}
		else {
			plane = null;
		}
		
		return plane;
		
	}
	
	public Plane updatePlane(String planeNo, String manufacturer, String model, Long classes, Long seats) {
		
		Plane plane = planeDao.getPlane(planeNo);
		
		if (plane != null) {
			plane = new Plane();
			plane.setPlaneId(planeNo);
			plane.setManufacturer(manufacturer);
			plane.setModel(model);
			plane.setClasses(classes);
			plane.setSeats(seats);
			plane = planeDao.savePlane(plane, true);
		}
		else {
			plane = null;
		}
		
		return plane;
		
	}
	
	public void deletePlane(String planeNo) {
		
		Plane plane = planeDao.getPlane(planeNo);
		if (plane != null) {
			planeDao.deletePlane(planeNo);
		}
		
	}

}
