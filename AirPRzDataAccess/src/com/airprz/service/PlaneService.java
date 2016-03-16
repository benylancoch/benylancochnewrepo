package com.airprz.service;

import java.util.List;

import com.airprz.model.Plane;

public interface PlaneService {
	
	Plane getPlane(String planeNo);
	
	List<Plane> getPlanes();
	
	Plane addPlane(String planeNo, String manufacturer, String model, Long classes, Long seats);
	
	Plane updatePlane(String planeNo, String manufacturer, String model, Long classes, Long seats);
	
	void deletePlane(String planeNo);
	

}
