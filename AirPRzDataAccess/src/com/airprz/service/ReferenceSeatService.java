package com.airprz.service;

import java.util.List;

import com.airprz.model.ReferenceSeat;

public interface ReferenceSeatService {
	
	public ReferenceSeat getReferenceSeat(Long referenceSeatId);
	
	public List<ReferenceSeat> getSeats(String planeNo);
	
	public ReferenceSeat addReferenceSeat(Long SeatClass, Long seatNo, String whereLocation, String planeNo);
	
	public Boolean addMultipleReferenceSeats(Long seatClass, Long seatNoStart, Long seatNoEnd, String planeNo);
	
	//public Boolean updateMultipleSeatsLocations()
	
	public Boolean copyReferenceSeatsToFlightSeats(String planeNo, Long flightId);
	
	public ReferenceSeat updateReferenceSeat(Long referenceSeatId, Long SeatClass, Long seatNo, String free, String whereLocation, String planeNo);
	
	public void deleteReferenceSeat(Long referenceSeatId);

}
