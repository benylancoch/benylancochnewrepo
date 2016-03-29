package com.airprz.data;

import java.util.List;

import com.airprz.model.FlightSeat;

public interface FlightSeatDao {
	
	public FlightSeat checkIfFree(Long seatNo, Long flightId);
	
	public FlightSeat getFlightSeat(Long flightSeatId);
	
	public FlightSeat getFlightSeatBySeatNo(Long seatNo, Long flightId);
	
	public List<FlightSeat> searchForFree(Long seatClass, String location, Long flightId);
	
	public List<FlightSeat> getSeats(Long flightId);
	
	public FlightSeat saveFlightSeat(FlightSeat flightSeat);
	
	public FlightSeat bookSeat(Long seatNo, Long flightId);
	
	public void deleteFlightSeat(Long flightSeatId);

}
