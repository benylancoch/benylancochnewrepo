package com.airprz.service.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Ticket;
import com.airprz.service.TicketService;

public class TicketServiceImpl implements TicketService {
	
	@Override
	public Ticket getTicket(Long ticketId) {
		return null;
	}
	
	@Override
	public List<Ticket> getTicketes(Timestamp start, Timestamp end) {
		return null;
	}
	
	@Override
	public Ticket addTicket(BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific) {
		return null;
	}
	
	@Override
	public Ticket updateTicket(Long ticketId, BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific) {
		return null;
	}
	
	@Override
	public void deleteTicket(Long taxId) {
		
	}


}
