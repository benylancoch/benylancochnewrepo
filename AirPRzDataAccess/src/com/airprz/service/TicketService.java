package com.airprz.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Ticket;

public interface TicketService {
	
	Ticket getTicket(Long ticketId);
	
	List<Ticket> getTicketes(Timestamp start, Timestamp end);
	
	Ticket addTicket(BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific);
	
	Ticket updateTicket(Long ticketId, BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific);
	
	void deleteTicket(Long ticketId);


}
