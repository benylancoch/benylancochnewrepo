package com.airprz.data;

import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Ticket;

public interface TicketDao {
	
	Ticket getTicket(Long ticketId);
	
	List<Ticket> getTicketes(Timestamp start, Timestamp end);
	
	Ticket addTicket(Ticket ticket);
	
	Ticket updateTicket(Ticket ticket);
	
	void deleteTicket(Long ticketId);

}
