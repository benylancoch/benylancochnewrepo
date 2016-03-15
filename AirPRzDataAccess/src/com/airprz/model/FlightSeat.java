package com.airprz.model;

public class FlightSeat {
	
	private Long fsId;
	private Long fsClass;
	private Long seatNo;
	private String free;
	private String whereLoc;
	private Plane planeNo;
	private Flight flightId;
	
	public Long getFsId() {
		return fsId;
	}
	public void setFsId(Long fsId) {
		this.fsId = fsId;
	}
	public Long getFsClass() {
		return fsClass;
	}
	public void setFsClass(Long fsClass) {
		this.fsClass = fsClass;
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
	public Plane getPlaneNo() {
		return planeNo;
	}
	public void setPlaneNo(Plane planeNo) {
		this.planeNo = planeNo;
	}
	public Flight getFlightId() {
		return flightId;
	}
	public void setFlightId(Flight flightId) {
		this.flightId = flightId;
	}
	

}
