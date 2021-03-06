package com.airprz.model;

import java.sql.Timestamp;

public class FlightSeat {
	
	private Long fsId;
	private Long fsClass;
	private Long seatNo;
	private String free;
	private Timestamp reservedTo;
	private String whereLoc;
	private Plane plane;
	private Flight flight;
	
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
	public Plane getPlane() {
		return plane;
	}
	public void setPlane(Plane planeNo) {
		this.plane = planeNo;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flightId) {
		this.flight = flightId;
	}
	public Timestamp getReservedTo() {
		return reservedTo;
	}
	public void setReservedTo(Timestamp reservedTo) {
		this.reservedTo = reservedTo;
	}

}
