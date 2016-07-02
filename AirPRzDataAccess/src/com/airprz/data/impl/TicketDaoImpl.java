package com.airprz.data.impl;

import java.sql.Timestamp;
import java.util.List;

import com.airprz.data.TicketDao;
import com.airprz.model.Ticket;

public class TicketDaoImpl implements TicketDao {
	
	@Override
	public Ticket getTicket(Long ticketId) {
		return null;
	}
	
	@Override
	public List<Ticket> getTicketes(Timestamp start, Timestamp end) {
		return null;
	}
	
	@Override
	public Ticket addTicket(Ticket ticket) {
		return null;
	}
	
	@Override
	public Ticket updateTicket(Ticket ticket) {
		return null;
	}
	
	@Override
	public void deleteTicket(Long ticketId) {
		
	}

}
