package com.airprz.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Ticket {
	
	private Long ticketId;
	private BigDecimal price;
	private Timestamp checkedAt;
	private User user;
	private Flight flight;
	private FlightSeat flightSeat;
	private Ticket nextFlight;
	private Transaction transaction;
	private String otherUserName;
	private String otherUserPhone;
	private Long otherUserHonorific;
	
	public Long getTicketId() {
		return ticketId;
	}
	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Timestamp getCheckedAt() {
		return checkedAt;
	}
	public void setCheckedAt(Timestamp checkedAt) {
		this.checkedAt = checkedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public FlightSeat getFlightSeat() {
		return flightSeat;
	}
	public void setFlightSeat(FlightSeat flightSeat) {
		this.flightSeat = flightSeat;
	}
	public Ticket getNextFlight() {
		return nextFlight;
	}
	public void setNextFlight(Ticket nextFlight) {
		this.nextFlight = nextFlight;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	public String getOtherUserName() {
		return otherUserName;
	}
	public void setOtherUserName(String otherUserName) {
		this.otherUserName = otherUserName;
	}
	public String getOtherUserPhone() {
		return otherUserPhone;
	}
	public void setOtherUserPhone(String otherUserPhone) {
		this.otherUserPhone = otherUserPhone;
	}
	public Long getOtherUserHonorific() {
		return otherUserHonorific;
	}
	public void setOtherUserHonorific(Long otherUserHonorific) {
		this.otherUserHonorific = otherUserHonorific;
	}
	

}
