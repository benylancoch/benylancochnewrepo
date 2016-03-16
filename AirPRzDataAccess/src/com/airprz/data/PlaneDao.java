package com.airprz.data;

import java.util.List;

import com.airprz.model.Plane;

public interface PlaneDao {
	
	Plane getPlane(String planeNo);
	
	List<Plane> getPlanes();
	
	Plane savePlane(Plane plane, boolean update);
	
	void deletePlane(String planeNo);

}
