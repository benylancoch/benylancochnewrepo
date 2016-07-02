package com.airprz.service;

import java.util.List;

import com.airprz.model.FlightSeat;

public interface FlightSeatService {
	
	public FlightSeat checkIfFree(Long seatNo, Long flightId);
	
	public FlightSeat getFlightSeat(Long flightSeatId);
	
	public List<FlightSeat> searchForFree(Long seatClass, String location, Long flightId);
	
	public List<FlightSeat> getSeats(Long flightId);
	
	public FlightSeat addFlightSeat(Long SeatClass, Long seatNo, String whereLocation, String planeNo, Long flightId);
	
	public FlightSeat updateFlightSeat(Long flightSeatId, Long SeatClass, Long seatNo, String free, String whereLocation, String planeNo, Long flightId);
	
	public FlightSeat bookSeat(Long seatNo, Long flightId);
	
	public FlightSeat reserveSeat(Long seatNo, Long flightId);
	
	public void deleteFlightSeat(Long flightSeatId);

}
