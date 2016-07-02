package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.airprz.data.FlightSeatDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Flight;
import com.airprz.model.FlightSeat;
import com.airprz.model.Plane;

public class FlightSeatDaoImpl implements FlightSeatDao {
	
	@Override
	public FlightSeat checkIfFree(Long seatNo, Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;
		Flight flight = null;		
		FlightSeat flightSeat = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT FS.FS_ID, FS.CLASS, FS.SEAT_NO, FS.FREE, FS.WHERE_LOC, FS.PLANE_NO, FS.FLIGHT_ID, FS.RESERVED_TO "
					+ "FROM BAZA.FLIGHTS_SEATS FS "
					+ "WHERE FS.SEAT_NO = ? AND FS.FLIGHT_ID = ? AND (FS.RESERVED < ? OR FS.RESERVED_TO IS NULL)");
			stmt.setLong(1, seatNo);
			stmt.setLong(2, flightId);
			Date dNow = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -10);
			stmt.setTimestamp(3, new Timestamp(cal.getTime().getTime()));
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				flight = new Flight();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flightSeat = new FlightSeat();
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setFree(rs.getString("FREE"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				flightSeat.setPlane(plane);
				flightSeat.setFlight(flight);
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
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat getFlightSeat(Long flightSeatId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;
		Flight flight = null;		
		FlightSeat flightSeat = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT FS.FS_ID, FS.CLASS, FS.SEAT_NO, FS.FREE, FS.WHERE_LOC, FS.PLANE_NO, FS.FLIGHT_ID "
					+ "FROM BAZA.FLIGHTS_SEATS FS "
					+ "WHERE FS.FS_ID = ?");
			stmt.setLong(1, flightSeatId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				flight = new Flight();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flightSeat = new FlightSeat();
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setFree(rs.getString("FREE"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				flightSeat.setPlane(plane);
				flightSeat.setFlight(flight);
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
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat getFlightSeatBySeatNo(Long seatNo, Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;
		Flight flight = null;		
		FlightSeat flightSeat = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT FS.FS_ID, FS.CLASS, FS.SEAT_NO, FS.FREE, FS.WHERE_LOC, FS.PLANE_NO, FS.FLIGHT_ID "
					+ "FROM BAZA.FLIGHTS_SEATS FS "
					+ "WHERE FS.SEAT_NO = ? AND FS.FLIGHT_ID = ?");
			stmt.setLong(1, seatNo);
			stmt.setLong(2, flightId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				flight = new Flight();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flightSeat = new FlightSeat();
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setFree(rs.getString("FREE"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				flightSeat.setPlane(plane);
				flightSeat.setFlight(flight);
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
		
		return flightSeat;
	}
	
	@Override
	public List<FlightSeat> searchForFree(Long seatClass, String location, Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Flight flight = null;
		List<FlightSeat> flightSeats = new ArrayList<FlightSeat>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT FS.FS_ID, FS.CLASS, FS.SEAT_NO, FS.FREE, FS.WHERE_LOC, FS.PLANE_NO, FS.FLIGHT_ID, FS.RESERVED_TO "
							+ "FROM BAZA.FLIGHTS_SEATS FS "
							+ "WHERE FS.CLASS = ? AND FS.WHERE_LOC = ? AND FS.FLIGHT_ID = ? AND FS.FREE = 'Y' AND (FS.RESERVED_TO < ? OR CAST(FS.RESERVED_TO as CHAR) is NULL)");
			
			stmt.setLong(1, seatClass);
			stmt.setString(2, location);
			stmt.setLong(3, flightId);
			Date dNow = new Date();
			Calendar cal = Calendar.getInstance();
			cal.setTime(dNow);
			cal.add(Calendar.MINUTE, -10);
			stmt.setTimestamp(4, new Timestamp(cal.getTime().getTime()));
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				FlightSeat flightSeat = new FlightSeat();
				plane = new Plane();
				flight = new Flight();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flightSeat = new FlightSeat();
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setFree(rs.getString("FREE"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				flightSeat.setReservedTo(rs.getTimestamp("RESERVED_TO"));
				
				flightSeat.setPlane(plane);
				flightSeat.setFlight(flight);
				flightSeats.add(flightSeat);
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
		
		return flightSeats;
	}
	
	@Override
	public List<FlightSeat> getSeats(Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Flight flight = null;
		List<FlightSeat> flightSeats = new ArrayList<FlightSeat>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT FS.FS_ID, FS.CLASS, FS.SEAT_NO, FS.FREE, FS.WHERE_LOC, FS.PLANE_NO, FS.FLIGHT_ID, FS.RESERVED_TO "
							+ "FROM BAZA.FLIGHTS_SEATS FS "
							+ "WHERE FS.FLIGHT_ID = ?");
			
			stmt.setLong(1, flightId);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				FlightSeat flightSeat = new FlightSeat();
				plane = new Plane();
				flight = new Flight();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flightSeat = new FlightSeat();
				flightSeat.setFsId(rs.getLong("FS_ID"));
				flightSeat.setFsClass(rs.getLong("CLASS"));
				flightSeat.setSeatNo(rs.getLong("SEAT_NO"));
				flightSeat.setFree(rs.getString("FREE"));
				flightSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				flightSeat.setReservedTo(rs.getTimestamp("RESERVED_TO"));
				
				flightSeat.setPlane(plane);
				flightSeat.setFlight(flight);
				flightSeats.add(flightSeat);
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
		
		return flightSeats;
	}
	
	@Override
	public FlightSeat saveFlightSeat(FlightSeat flightSeat) {
		if(flightSeat.getFsId() == null) {
			flightSeat = insertFlightSeat(flightSeat);
		} else {
			flightSeat = updateFlightSeat(flightSeat);
		}
		return flightSeat;
	}
	
	@Override
	public FlightSeat bookSeat(Long seatNo, Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		FlightSeat flightSeat = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"UPDATE BAZA.FLIGHTS_SEATS SET FREE = ? WHERE SEAT_NO = ? AND FLIGHT_ID = ?");
			stmt.setString(1, "N");
			stmt.setLong(2, seatNo);
			stmt.setLong(3, flightId);
			stmt.executeUpdate();
			
			stmt.close();
			
			flightSeat = this.getFlightSeatBySeatNo(seatNo, flightId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return flightSeat;
	}
	
	@Override
	public FlightSeat reserveSeat(Long seatNo, Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		FlightSeat flightSeat = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"UPDATE BAZA.FLIGHTS_SEATS SET RESERVED_TO = current timestamp WHERE SEAT_NO = ? AND FLIGHT_ID = ?");
			stmt.setLong(1, seatNo);
			stmt.setLong(2, flightId);
			stmt.executeUpdate();
			
			stmt.close();
			
			flightSeat = this.getFlightSeatBySeatNo(seatNo, flightId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return flightSeat;
	}
	
	@Override
	public void deleteFlightSeat(Long flightSeatId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.FLIGHTS_SEATS WHERE FS_ID = ?");
			stmt.setLong(1, flightSeatId);
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
	
	private FlightSeat insertFlightSeat(FlightSeat flightSeat) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES (?, ?, ?, ?, ?, ?)");
				stmt.setLong(1, flightSeat.getFsClass());
				stmt.setLong(2, flightSeat.getSeatNo());
				stmt.setString(3, "Y");
				stmt.setString(4, flightSeat.getWhereLoc());
				stmt.setString(5, flightSeat.getPlane().getPlaneId());
				stmt.setLong(6, flightSeat.getFlight().getFlightId());
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.FLIGHTS_SEATS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					flightSeat.setFsId(rs.getLong("last_cod"));
					flightSeat.setFree("Y");
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return flightSeat;
		}
		
	private FlightSeat updateFlightSeat(FlightSeat flightSeat) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.FLIGHTS_SEATS SET CLASS = ?, SEAT_NO = ?, FREE = ?, WHERE_LOC = ?, PLANE_NO = ?, FLIGHT_ID = ? WHERE FS_ID = ?");
				stmt.setLong(1, flightSeat.getFsClass());
				stmt.setLong(2, flightSeat.getSeatNo());
				stmt.setString(3, flightSeat.getFree());
				stmt.setString(4, flightSeat.getWhereLoc());
				stmt.setString(5, flightSeat.getPlane().getPlaneId());
				stmt.setLong(6, flightSeat.getFlight().getFlightId());
				stmt.setLong(7, flightSeat.getFsId());
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return flightSeat;
			
		}

}
