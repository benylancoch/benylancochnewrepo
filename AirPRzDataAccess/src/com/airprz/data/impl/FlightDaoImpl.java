package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.FlightDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Airport;
import com.airprz.model.Flight;
import com.airprz.model.Plane;

public class FlightDaoImpl implements FlightDao {
	
	@Override
	public Flight getFlight(Long flightId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;
		Airport departurePlace = null;
		Airport arrivalPlace = null;
		Flight flight = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT F.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.BASE_PRICE, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO "
					+ "FROM BAZA.FLIGHTS F "
					+ "WHERE F.FLIGHT_ID = ?");
			stmt.setLong(1, flightId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				departurePlace = new Airport();
				arrivalPlace = new Airport();
				flight = new Flight();
				
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				flight.setBasePrice(rs.getBigDecimal("BASE_PRICE"));
				departurePlace.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				arrivalPlace.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				plane.setPlaneId(rs.getString("PLANE_NO"));
				
				flight.setDeparturePlace(departurePlace);
				flight.setArrivalPlace(arrivalPlace);
				flight.setPlaneNo(plane);
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
		
		return flight;
	}
	
	@Override
	public List<List<Flight>> searchForFlights(Timestamp starts, Timestamp ends,
			Long departurePlace, Long arrivalPlace,
			int noOfTransfers) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Airport departurePlaceObject = null;
		Airport arrivalPlaceObject = null;
		Flight flight = null;
		List<Flight> flights = new ArrayList<Flight>();
		List<List<Flight>> flightsFlights = new ArrayList<List<Flight>>();
		
		//For 0 transfers
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT F.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.BASE_PRICE, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO, "
					+ "P.MANUFACTURER, P.MODEL, P.CLASSES, P.SEATS "
					+ "FROM BAZA.FLIGHTS F "
					+ "INNER JOIN BAZA.PLANES P "
					+ "ON F.PLANE_NO = P.PLANE_NO "
					+ "WHERE (F.STARTS BETWEEN ? AND ?) AND F.DEPARTURE_PLACE = ? AND F.ARRIVAL_PLACE = ?");
			
			//stmt.setTimestamp(1, new Timestamp(starts.getTime() - 86400000));
			//stmt.setTimestamp(2, new Timestamp(starts.getTime() + 86400000));
			stmt.setTimestamp(1, starts);
			stmt.setTimestamp(2, ends);
			stmt.setLong(3, departurePlace);
			stmt.setLong(4, arrivalPlace);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				flights = new ArrayList<Flight>();
				plane = new Plane();
				departurePlaceObject = new Airport();
				arrivalPlaceObject = new Airport();
				
				flight = new Flight();
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				flight.setBasePrice(rs.getBigDecimal("BASE_PRICE"));
				departurePlaceObject.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				arrivalPlaceObject.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				plane.setPlaneId(rs.getString("PLANE_NO"));
				plane.setManufacturer(rs.getString("MANUFACTURER"));
				plane.setModel(rs.getString("MODEL"));
				plane.setClasses(rs.getLong("CLASSES"));
				plane.setSeats(rs.getLong("SEATS"));
				
				flight.setDeparturePlace(departurePlaceObject);
				flight.setArrivalPlace(arrivalPlaceObject);
				flight.setPlaneNo(plane);
				flights.add(flight);
				flightsFlights.add(flights);
			}
			
			//Check if there is more no of transfers
//			if( noOfTransfers == 0 ) {
//				
//				flightsFlights.add(flights);
//				
//			}
//			else {
//				
//				//TO DO!!!!!!!!!!!!!!!!!!!!
//				
//			}
			
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
		
		return flightsFlights;
	}
	
	@Override
	public List<Flight> getFlightsByTimestamp(Timestamp starts, Timestamp ends) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Airport departurePlaceObject = null;
		Airport arrivalPlaceObject = null;
		Flight flight = null;
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT F.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.BASE_PRICE, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO "
					+ "FROM BAZA.FLIGHTS F "
					+ "WHERE F.STARTS BETWEEN ? AND ?");
			
			stmt.setTimestamp(1, starts);
			stmt.setTimestamp(2, ends);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				plane = new Plane();
				departurePlaceObject = new Airport();
				arrivalPlaceObject = new Airport();
				
				flight = new Flight();
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				flight.setBasePrice(rs.getBigDecimal("BASE_PRICE"));
				departurePlaceObject.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				arrivalPlaceObject.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				plane.setPlaneId(rs.getString("PLANE_NO"));
				
				flight.setDeparturePlace(departurePlaceObject);
				flight.setArrivalPlace(arrivalPlaceObject);
				flight.setPlaneNo(plane);
				flights.add(flight);
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
		
		return flights;
	}
	
	@Override
	public List<Flight> getFlightsByDeparturePlace(Long departurePlace) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Airport departurePlaceObject = null;
		Airport arrivalPlaceObject = null;
		Flight flight = null;
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT F.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.BASE_PRICE, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO "
					+ "FROM BAZA.FLIGHTS F "
					+ "WHERE F.DEPARTURE_PLACE = ?");
			
			stmt.setLong(1, departurePlace);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				plane = new Plane();
				departurePlaceObject = new Airport();
				arrivalPlaceObject = new Airport();
				
				flight = new Flight();
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				flight.setBasePrice(rs.getBigDecimal("BASE_PRICE"));
				departurePlaceObject.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				arrivalPlaceObject.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				plane.setPlaneId(rs.getString("PLANE_NO"));
				
				flight.setDeparturePlace(departurePlaceObject);
				flight.setArrivalPlace(arrivalPlaceObject);
				flight.setPlaneNo(plane);
				flights.add(flight);
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
		
		return flights;
	}
	
	@Override
	public List<Flight> getFlightsByArrivalPlace(Long arrivalPlace) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		Airport departurePlaceObject = null;
		Airport arrivalPlaceObject = null;
		Flight flight = null;
		List<Flight> flights = new ArrayList<Flight>();
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT F.FLIGHT_ID, F.FLIGHT_NO, F.STARTS, F.ENDS, F.BASE_PRICE, F.DEPARTURE_PLACE, F.ARRIVAL_PLACE, F.PLANE_NO "
					+ "FROM BAZA.FLIGHTS F "
					+ "WHERE F.ARRIVAL_PLACE = ?");
			
			stmt.setLong(1, arrivalPlace);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				plane = new Plane();
				departurePlaceObject = new Airport();
				arrivalPlaceObject = new Airport();
				
				flight = new Flight();
				flight.setFlightId(rs.getLong("FLIGHT_ID"));
				flight.setFlightNo(rs.getString("FLIGHT_NO"));
				flight.setStarts(rs.getTimestamp("STARTS"));
				flight.setEnds(rs.getTimestamp("ENDS"));
				flight.setBasePrice(rs.getBigDecimal("BASE_PRICE"));
				departurePlaceObject.setAirportId(rs.getLong("DEPARTURE_PLACE"));
				arrivalPlaceObject.setAirportId(rs.getLong("ARRIVAL_PLACE"));
				plane.setPlaneId(rs.getString("PLANE_NO"));
				
				flight.setDeparturePlace(departurePlaceObject);
				flight.setArrivalPlace(arrivalPlaceObject);
				flight.setPlaneNo(plane);
				flights.add(flight);
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
		
		return flights;
	}
	
	@Override
	public Flight saveFlight(Flight flight) {
		
		if(flight.getFlightId() == null) {
			
			flight = insertFlight(flight);
			
		} else {
			
			flight = updateFlight(flight);
			
		}
		return flight;
	}
	
	@Override
	public void deleteFlight(Long flightId) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.FLIGHTS WHERE FLIGHT_ID = ?");
			stmt.setLong(1, flightId);
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
	
	private Flight insertFlight(Flight flight) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.FLIGHTS (FLIGHT_NO, STARTS, ENDS, BASE_PRICE, DEPARTURE_PLACE, ARRIVAL_PLACE, PLANE_NO) VALUES (?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, flight.getFlightNo());
				stmt.setTimestamp(2, flight.getStarts());
				stmt.setTimestamp(3, flight.getEnds());
				stmt.setBigDecimal(4, flight.getBasePrice());
				stmt.setLong(5, flight.getDeparturePlace().getAirportId());
				stmt.setLong(6, flight.getArrivalPlace().getAirportId());
				stmt.setString(7, flight.getPlaneNo().getPlaneId());
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.FLIGHTS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					flight.setFlightId(rs.getLong("last_cod"));
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return flight;
		}
		
	private Flight updateFlight(Flight flight) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.FLIGHTS SET FLIGHT_NO = ?, STARTS = ?, ENDS = ?, BASE_PRICE = ?, DEPARTURE_PLACE = ?, ARRIVAL_PLACE = ?, PLANE_NO = ? WHERE FLIGHT_ID = ?");
				stmt.setString(1, flight.getFlightNo());
				stmt.setTimestamp(2, flight.getStarts());
				stmt.setTimestamp(3, flight.getEnds());
				stmt.setBigDecimal(4, flight.getBasePrice());
				stmt.setLong(5, flight.getDeparturePlace().getAirportId());
				stmt.setLong(6, flight.getArrivalPlace().getAirportId());
				stmt.setString(7, flight.getPlaneNo().getPlaneId());
				stmt.setLong(8, flight.getFlightId());
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return flight;
			
		}


}
