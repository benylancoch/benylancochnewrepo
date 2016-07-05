package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.AirportDao;
import com.airprz.data.PlaneDao;
import com.airprz.data.TicketDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Airport;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.Plane;
import com.airprz.model.Ticket;
import com.airprz.model.Transaction;
import com.airprz.model.User;

public class TicketDaoImpl implements TicketDao {
	
	@Override
	public Ticket getTicket(Long ticketId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Ticket ticket = null;
		User user = null;
		Flight flight = null;
		FlightSeat flightSeat = null;
		Transaction transaction = null;
		AirportDao airportDao = new AirportDaoImpl();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT T.TICKET_ID, T.PRICE, T.CHECKED_AT, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, U.NAME_3RD, U.PHONE_3RD, "
					+ "T.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO, "
					+ "T.FS_ID, FS.CLASS, FS.SEAT_NO, FS.WHERE_LOC, "
					+ "T.TRANSACTION_ID, T.OTHER_USER_NAME, T.OTHER_USER_PHONE, T.OTHER_USER_HONORIFIC "
					+ "FROM BAZA.TICKETS T "
					+ "INNER JOIN BAZA.USERS U "
					+ "ON T.USER_ID = U.USER_ID "
					+ "INNER JOIN BAZA.FLIGHTS F "
					+ "ON T.FLIGHT_ID = F.FLIGHT_ID "
					+ "INNER JOIN BAZA.FLIGHTS_SEATS FS "
					+ "ON T.FS_ID = FS.FS_ID "
					+ "WHERE T.TICKET_ID = ?");
			stmt.setLong(1, ticketId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				ticket = new Ticket();
				user = new User();
				flight = new Flight();
				flightSeat = new FlightSeat();
				transaction = new Transaction();
				Airport airport = new Airport();
				Plane plane = new Plane();
				PlaneDao planeDao = new PlaneDaoImpl();
				Airport tmpAirport = new Airport();
				
				ticket.setTicketId(rs.getLong("TICKET_ID"));
				ticket.setPrice(rs.getBigDecimal("PRICE"));
				ticket.setCheckedAt(rs.getTimestamp("CHECKED_AT"));
				
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				user.setName_3rd(rs.getString("NAME_3RD"));
				user.setPhone_3rd(rs.getString("PHONE_3RD"));
				ticket.setUser(user);
				
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				airport.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				
				tmpAirport = airportDao.getAirport(airport.getAirportId());
				
				flight.setDeparturePlace(tmpAirport);
				airport.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				
				tmpAirport = airportDao.getAirport(airport.getAirportId());
				
				flight.setArrivalPlace(tmpAirport);
				plane.setPlaneId(rs.getString("PLANE_NO"));
				plane = planeDao.getPlane(plane.getPlaneId());
				flight.setPlaneNo(plane);
				ticket.setFlight(flight);
				
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				ticket.setFlightSeat(flightSeat);
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				ticket.setTransaction(transaction);
				ticket.setOtherUserName(rs.getString("OTHER_USER_NAME"));
				ticket.setOtherUserPhone(rs.getString("OTHER_USER_PHONE"));
				ticket.setOtherUserHonorific(rs.getLong("OTHER_USER_HONORIFIC"));
				
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return ticket;
	}
	
	@Override
	public List<Ticket> getTicketesFromTransaction(Long transactionId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Ticket ticket = null;
		User user = null;
		Flight flight = null;
		FlightSeat flightSeat = null;
		Transaction transaction = null;
		List<Ticket> tickets = new ArrayList<Ticket>();
		AirportDao airportDao = new AirportDaoImpl();
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"SELECT T.TICKET_ID, T.PRICE, T.CHECKED_AT, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, U.NAME_3RD, U.PHONE_3RD, "
					+ "T.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO, "
					+ "T.FS_ID, FS.CLASS, FS.SEAT_NO, FS.WHERE_LOC, "
					+ "T.TRANSACTION_ID, T.OTHER_USER_NAME, T.OTHER_USER_PHONE, T.OTHER_USER_HONORIFIC "
					+ "FROM BAZA.TICKETS T "
					+ "INNER JOIN BAZA.USERS U "
					+ "ON T.USER_ID = U.USER_ID "
					+ "LEFT JOIN BAZA.FLIGHTS F "
					+ "ON T.FLIGHT_ID = F.FLIGHT_ID "
					+ "LEFT JOIN BAZA.FLIGHTS_SEATS FS "
					+ "ON T.FS_ID = FS.FS_ID "
					+ "WHERE T.TRANSACTION_ID = ? "
					+ "ORDER BY T.TICKET_ID");
			
			stmt.setLong(1, transactionId);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				ticket = new Ticket();
				user = new User();
				flight = new Flight();
				flightSeat = new FlightSeat();
				transaction = new Transaction();
				Airport airport = new Airport();
				Plane plane = new Plane();
				PlaneDao planeDao = new PlaneDaoImpl();
				Airport tmpAirport = new Airport();
				
				ticket.setTicketId(rs.getLong("TICKET_ID"));
				ticket.setPrice(rs.getBigDecimal("PRICE"));
				ticket.setCheckedAt(rs.getTimestamp("CHECKED_AT"));
				
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				user.setName_3rd(rs.getString("NAME_3RD"));
				user.setPhone_3rd(rs.getString("PHONE_3RD"));
				ticket.setUser(user);
				
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				airport.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				
				tmpAirport = airportDao.getAirport(airport.getAirportId());
				
				flight.setDeparturePlace(tmpAirport);
				airport.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				
				tmpAirport = airportDao.getAirport(airport.getAirportId());
				
				flight.setArrivalPlace(tmpAirport);
				plane.setPlaneId(rs.getString("PLANE_NO"));
				plane = planeDao.getPlane(plane.getPlaneId());
				flight.setPlaneNo(plane);
				ticket.setFlight(flight);
				
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				ticket.setFlightSeat(flightSeat);
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				ticket.setTransaction(transaction);
				ticket.setOtherUserName(rs.getString("OTHER_USER_NAME"));
				ticket.setOtherUserPhone(rs.getString("OTHER_USER_PHONE"));
				ticket.setOtherUserHonorific(rs.getLong("OTHER_USER_HONORIFIC"));
				
				tickets.add(ticket);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return tickets;
	}
	
	@Override
	public Ticket saveTicket(Ticket ticket) {
		
		if(ticket.getTicketId() == null) {
			
			ticket = insertTicket(ticket);
			
		} else {
			
			ticket = updateTicket(ticket);
			
		}
		return ticket;
	}
	
	@Override
	public void deleteTicket(Long ticketId) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.TICKETS WHERE TICEKT_ID = ?");
			stmt.setLong(1, ticketId);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}
	
	//---------------------Private---------------------------------------------------
	
	private Ticket insertTicket(Ticket ticket) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.TICKETS (PRICE, USER_ID, FLIGHT_ID, FS_ID, TRANSACTION_ID, OTHER_USER_NAME, "
						+ "OTHER_USER_PHONE, OTHER_USER_HONORIFIC) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setBigDecimal(1, ticket.getPrice());
				stmt.setLong(2, ticket.getUser().getId());
				stmt.setLong(3, ticket.getFlight().getFlightId());
				stmt.setLong(4, ticket.getFlightSeat().getFsId());
				stmt.setLong(5, ticket.getTransaction().getTransactionId());
				stmt.setString(6, ticket.getOtherUserName());
				stmt.setString(7, ticket.getOtherUserPhone());
				if (ticket.getOtherUserHonorific() == null) {
					stmt.setLong(8, new Long(0));
				}
				else {
					stmt.setLong(8, ticket.getOtherUserHonorific());
				}
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.TICKETS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					ticket.setTicketId(rs.getLong("last_cod"));
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return ticket;
		}
		
	private Ticket updateTicket(Ticket ticket) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.TICKETS SET PRICE = ?, CHECKED_AT = ?, USER_ID = ?, FLIGHT_ID = ?, FS_ID = ?, TRANSACTION_ID = ?, "
						+ "OTHER_USER_NAME = ?, OTHER_USER_PHONE = ?, OTHER_USER_HONORIFIC = ?");
				stmt.setBigDecimal(1, ticket.getPrice());
				stmt.setTimestamp(2, ticket.getCheckedAt());
				stmt.setLong(3, ticket.getUser().getId());
				stmt.setLong(4, ticket.getFlight().getFlightId());
				stmt.setLong(5, ticket.getFlightSeat().getFsId());
				stmt.setLong(6, ticket.getTransaction().getTransactionId());
				stmt.setString(7, ticket.getOtherUserName());
				stmt.setString(8, ticket.getOtherUserPhone());
				if (ticket.getOtherUserHonorific() == null) {
					stmt.setLong(9, new Long(0));
				}
				else {
					stmt.setLong(9, ticket.getOtherUserHonorific());
				}
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return ticket;
			
		}

}
