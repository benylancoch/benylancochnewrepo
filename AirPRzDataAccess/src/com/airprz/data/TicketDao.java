package com.airprz.data;

import java.util.List;

import com.airprz.model.Ticket;

public interface TicketDao {
	
	Ticket getTicket(Long ticketId);
	
	List<Ticket> getTicketesFromTransaction(Long transactionId);
	
	Ticket saveTicket(Ticket ticket);
	
	void deleteTicket(Long ticketId);

}
