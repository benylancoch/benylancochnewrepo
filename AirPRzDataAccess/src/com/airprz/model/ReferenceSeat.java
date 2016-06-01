package com.airprz.model;

public class ReferenceSeat {
	
	private Long rsId;
	private Long rsClass;
	private Long seatNo;
	private String free;
	private String whereLoc;
	private Plane plane;
	
	public Long getRsId() {
		return rsId;
	}
	public void setRsId(Long rsId) {
		this.rsId = rsId;
	}
	public Long getRsClass() {
		return rsClass;
	}
	public void setRsClass(Long rsClass) {
		this.rsClass = rsClass;
	}
	public Long getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(Long seatNo) {
		this.seatNo = seatNo;
	}
	public String getFree() {
		return free;
	}
	public void setFree(String free) {
		this.free = free;
	}
	public String getWhereLoc() {
		return whereLoc;
	}
	public void setWhereLoc(String whereLoc) {
		this.whereLoc = whereLoc;
	}
	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane plane) {
		this.plane = plane;
	}

}
