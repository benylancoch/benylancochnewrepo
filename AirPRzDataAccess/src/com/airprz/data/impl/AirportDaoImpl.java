package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.AirportDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Airport;

public class AirportDaoImpl implements AirportDao {
	
	@Override
	public Airport getAirport(Long airportId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Airport airport = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT A.AIRPORT_ID, A.NAME, A.CITY, A.COUNTRY, A.ADDRESS, A.LOCATION "
					+ "FROM BAZA.AIRPORTS A "
					+ "WHERE LOWER(A.AIRPORT_ID) = ?");
			stmt.setLong(1, airportId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				airport = new Airport();
				airport.setAirportId(rs.getLong("AIRPORT_ID"));
				airport.setName(rs.getString("NAME"));
				airport.setCity(rs.getString("CITY"));
				airport.setCountry(rs.getString("COUNTRY"));
				airport.setAddress(rs.getString("ADDRESS"));
				airport.setLocation(rs.getString("LOCATION"));
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
		
		return airport;
	}
	
	@Override
	public List<Airport> getAirports() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		List<Airport> airports = new ArrayList<Airport>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT A.AIRPORT_ID, A.NAME, A.CITY, A.COUNTRY, A.ADDRESS, A.LOCATION "
							+ "FROM BAZA.AIRPORTS A ");
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Airport airport = new Airport();
				airport.setAirportId(rs.getLong("AIRPORT_ID"));
				airport.setName(rs.getString("NAME"));
				airport.setCity(rs.getString("CITY"));
				airport.setCountry(rs.getString("COUNTRY"));
				airport.setAddress(rs.getString("ADDRESS"));
				airport.setLocation(rs.getString("LOCATION"));
				airports.add(airport);
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
		
		return airports;
	}
	
	@Override
	public Airport saveAirport(Airport airport) {
		if(airport.getAirportId() == null) {
			airport = insertAirport(airport);
		} else {
			airport = updateAirport(airport);
		}
		return airport;
	}
	
	@Override
	public void deleteAirport(Long airportId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.AIRPORTS WHERE AIRPORT_ID = ?");
			stmt.setLong(1, airportId);
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
	
	
	//-------------------------------Private-------------------------------
	
	private Airport insertAirport(Airport airport) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"INSERT INTO BAZA.AIRPORTS (NAME, CITY, COUNTRY, ADDRESS, LOCATION) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, airport.getName());
			stmt.setString(2, airport.getCity());
			stmt.setString(3, airport.getCountry());
			stmt.setString(4, airport.getAddress());
			stmt.setString(5, airport.getLocation());
			
			stmt.executeUpdate();
			stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.AIRPORTS");
			rs = stmt.executeQuery();
			
			
			if (rs.next()) {
				airport.setAirportId(rs.getLong("last_cod"));
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
		
		return airport;
	}
	
	private Airport updateAirport(Airport airport) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"UPDATE BAZA.AIRPORTS SET NAME = ?, CITY = ? , COUNTRY = ? , ADDRESS = ? , LOCATION = ? WHERE AIRPORT_ID = ?");
			stmt.setString(1, airport.getName());
			stmt.setString(2, airport.getCity());
			stmt.setString(3, airport.getCountry());
			stmt.setString(4, airport.getAddress());
			stmt.setString(5, airport.getLocation());
			stmt.setLong(6, airport.getAirportId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return airport;
		
	}

}
