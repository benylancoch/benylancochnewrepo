package com.airprz.data;

import java.util.List;

import com.airprz.model.ReferenceSeat;

public interface ReferenceSeatDao {
	
	public ReferenceSeat getReferenceSeat(Long referenceSeatId);
	
	public ReferenceSeat getReferenceSeatBySeatNo(Long seatNo, Long planeNo);
	
	public List<ReferenceSeat> getSeats(String planeNo);
	
	public ReferenceSeat saveReferenceSeat(ReferenceSeat referenceSeat);
	
	public Boolean addMultipleReferenceSeats(Long seatClass, Long seatNoStart, Long seatNoEnd, String planeNo);
	
	public Boolean copyReferenceSeatsToFlightSeats(String planeNo, Long flightId);
	
	public void deleteReferenceSeat(Long referenceSeatId);

}
