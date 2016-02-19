package com.airprz.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Plane;
import com.airprz.model.ReferenceSeat;
import com.airprz.model.misc.Classes;
import com.airprz.service.PlaneService;
import com.airprz.service.ReferenceSeatService;
import com.airprz.service.impl.PlaneServiceImpl;
import com.airprz.service.impl.ReferenceSeatServiceImpl;
import com.airprz.web.model.PlaneBean;
import com.airprz.web.model.PlaneSeatsBean;
import com.airprz.web.model.ReferenceSeatsDefineBean;

@ManagedBean
@SessionScoped
public class PlaneController {
	
	private final PlaneService planeService;
	private final ReferenceSeatService referenceSeatService;
	
	@ManagedProperty("#{planeBean}")
	private PlaneBean planeBean;
	
	@ManagedProperty("#{referenceSeatsDefineBean}")
	private ReferenceSeatsDefineBean referenceSeatsDefineBean;
	
	@ManagedProperty("#{planeSeatsBean}")
	private PlaneSeatsBean planeSeatsBean;
	
	public PlaneController() {
		planeService = new PlaneServiceImpl();
		referenceSeatService = new ReferenceSeatServiceImpl();
	}
	
	public String addPlane() {
		
		planeService.addPlane(planeBean.getPlaneId(), planeBean.getManufacturer(), planeBean.getModel(), planeBean.getClasses(), planeBean.getSeats());
		
		return "index?faces-redirect=true";
	}
	
	public String editPlane() {
		
		planeService.updatePlane(planeBean.getPlaneId(), planeBean.getManufacturer(), planeBean.getModel(), planeBean.getClasses(), planeBean.getSeats());
		
		return "index?faces-redirect=true";
	}
	
	public String saveSeats() {
		
		referenceSeatService.addMultipleReferenceSeats(referenceSeatsDefineBean.getSeatClass(), 
				referenceSeatsDefineBean.getSeatNoStart(), referenceSeatsDefineBean.getSeatNoEnd(), planeBean.getPlaneId());
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
	
	public String redirectDefineSeats(Plane plane) {
		
		planeBean.setPlaneId(plane.getPlaneId());
		planeBean.setManufacturer(plane.getManufacturer());
		planeBean.setModel(plane.getModel());
		planeBean.setClasses(plane.getClasses());
		planeBean.setSeats(plane.getSeats());
		
		referenceSeatsDefineBean.setSeatClass(null);
		referenceSeatsDefineBean.setSeatNoStart(null);
		referenceSeatsDefineBean.setSeatNoEnd(null);

		return "addseats?faces-redirect=true";
	}
	
public String redirectEditSeats(Plane plane) {
		
		List<ReferenceSeat> tmpPlaneSeats = new ArrayList<ReferenceSeat>();
		List<ReferenceSeat> tmpPlaneSeats2 = new ArrayList<ReferenceSeat>();
		List<List<ReferenceSeat>> planeSeats = new ArrayList<List<ReferenceSeat>>();
		int rowSize = 0;
		int tableSize = 0;
				 
		planeBean.setPlaneId(plane.getPlaneId());
		planeBean.setManufacturer(plane.getManufacturer());
		planeBean.setModel(plane.getModel());
		planeBean.setClasses(plane.getClasses());
		planeBean.setSeats(plane.getSeats());
		
		tmpPlaneSeats = referenceSeatService.getSeats(planeBean.getPlaneId());
		
		tableSize = tmpPlaneSeats.size();
		rowSize = tableSize/10;
		if (tmpPlaneSeats.size()%10 > 0) {
			rowSize++;
		}
		
		for (int i = 0; i < rowSize; i++) {
			tmpPlaneSeats2 = new ArrayList<ReferenceSeat>();
			for (int j = 0; j < planeSeatsBean.getColumns(); j++)  {
				int temp = i*planeSeatsBean.getColumns()+j;
				if (temp < tableSize) {
					tmpPlaneSeats2.add(tmpPlaneSeats.get(temp));
				}
				else {
					tmpPlaneSeats2.add(null);
				}
			}
			planeSeats.add(tmpPlaneSeats2);
		}
		
		planeSeatsBean.setPlaneNo(plane.getPlaneId());
		planeSeatsBean.setPlaneSeats(planeSeats);

		return "editseats?faces-redirect=true";
	}
	
	public List<Classes> listClasses(Long classes) {
		List<Classes> temp = new ArrayList<Classes>();
		Classes tempClass = new Classes();
		
		tempClass.setClassNo(new Long(1));
		tempClass.setClassDescription("Economy class");
		temp.add(tempClass);
		
		
		if (classes == 3) {
			tempClass = new Classes();
			tempClass.setClassNo(new Long(2));
			tempClass.setClassDescription("Business class");
			temp.add(tempClass);
			
			tempClass = new Classes();
			tempClass.setClassNo(new Long(3));
			tempClass.setClassDescription("First class");
			temp.add(tempClass);
		}
		else if (classes == 2) {
			tempClass = new Classes();
			tempClass.setClassNo(new Long(2));
			tempClass.setClassDescription("Business class");
			temp.add(tempClass);
		}
		
		return temp;
	}
	
	public void setPlaneBean(PlaneBean planeBean) {
		this.planeBean = planeBean;
	}
	
	public void setReferenceSeatsDefineBean(ReferenceSeatsDefineBean referenceSeatsDefineBean) {
		this.referenceSeatsDefineBean = referenceSeatsDefineBean;
	}
	
	public void setPlaneSeatsBean(PlaneSeatsBean planeSeatsBean) {
		this.planeSeatsBean = planeSeatsBean;
	}

}
