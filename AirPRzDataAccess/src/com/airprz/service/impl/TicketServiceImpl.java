package com.airprz.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.airprz.data.TicketDao;
import com.airprz.data.impl.TicketDaoImpl;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.Ticket;
import com.airprz.model.Transaction;
import com.airprz.model.User;
import com.airprz.service.TicketService;

public class TicketServiceImpl implements TicketService {
	
	private final TicketDao ticketDao;
	
	public TicketServiceImpl() {
		ticketDao = new TicketDaoImpl();
	}
	
	@Override
	public Ticket getTicket(Long ticketId) {
		Ticket ticket = null;
		if (ticketId != null) {

			ticket = ticketDao.getTicket(ticketId);
		}
		
		return ticket;
	}
	
	@Override
	public List<Ticket> getTicketesFromTransaction(Long transactionId) {
		return ticketDao.getTicketesFromTransaction(transactionId);
	}
	
	@Override
	public Ticket addTicket(BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific) {
		
		Ticket ticket = null;
		User user = null;
		Flight flight = null;
		FlightSeat flightSeat = null;
		Transaction transaction = null;
		
		if (price != null && userId != null && flightId != null && flightSeatId != null && transactionId != null) {
			ticket = new Ticket();
			user = new User();
			flight = new Flight();
			flightSeat = new FlightSeat();
			transaction = new Transaction();
			
			ticket.setPrice(price);
			user.setId(userId);
			ticket.setUser(user);
			flight.setFlightId(flightId);
			ticket.setFlight(flight);
			flightSeat.setFsId(flightSeatId);
			ticket.setFlightSeat(flightSeat);
			transaction.setTransactionId(transactionId);
			ticket.setTransaction(transaction);
			ticket.setOtherUserName(otherUserName);
			ticket.setOtherUserPhone(otherUserPhone);
			ticket.setOtherUserHonorific(otherUserHonorific);
			
			ticket = ticketDao.saveTicket(ticket);
		}
		else {
			ticket = null;
		}
		return ticket;
	}
	
	@Override
	public Ticket updateTicket(Long ticketId, BigDecimal price, Long userId, Long flightId, Long flightSeatId, Long transactionId, 
			String otherUserName, String otherUserPhone, Long otherUserHonorific) {
		
		Ticket ticket = ticketDao.getTicket(ticketId);
		
		User user = null;
		Flight flight = null;
		FlightSeat flightSeat = null;
		Transaction transaction = null;
		
		if (ticket != null) {
			ticket = new Ticket();
			user = new User();
			flight = new Flight();
			flightSeat = new FlightSeat();
			transaction = new Transaction();
			
			ticket.setPrice(price);
			user.setId(userId);
			ticket.setUser(user);
			flight.setFlightId(flightId);
			ticket.setFlight(flight);
			flightSeat.setFsId(flightSeatId);
			ticket.setFlightSeat(flightSeat);
			transaction.setTransactionId(transactionId);
			ticket.setTransaction(transaction);
			ticket.setOtherUserName(otherUserName);
			ticket.setOtherUserPhone(otherUserPhone);
			ticket.setOtherUserHonorific(otherUserHonorific);
			
			ticket = ticketDao.saveTicket(ticket);
		}
		else {
			
			ticket = null;
		}
		
		return null;
	}
	
	@Override
	public void deleteTicket(Long ticketId) {
		
		Ticket ticket = ticketDao.getTicket(ticketId);
		
		if (ticket != null) {
			ticketDao.deleteTicket(ticketId);
		}
	}


}
