package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.PlaneDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Plane;

public class PlaneDaoImpl implements PlaneDao {
	
	@Override
	public Plane getPlane(String planeNo) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT P.PLANE_NO, P.MANUFACTURER, P.MODEL, P.CLASSES, P.SEATS "
					+ "FROM BAZA.PLANES P "
					+ "WHERE P.PLANE_NO = ?");
			stmt.setString(1, planeNo);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				plane.setManufacturer(rs.getString("MANUFACTURER"));
				plane.setModel(rs.getString("MODEL"));
				plane.setClasses(rs.getLong("CLASSES"));
				plane.setSeats(rs.getLong("SEATS"));
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
		
		return plane;
	}
	
	@Override
	public List<Plane> getPlanes() {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		List<Plane> planes = new ArrayList<Plane>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT P.PLANE_NO, P.MANUFACTURER, P.MODEL, P.CLASSES, P.SEATS "
							+ "FROM BAZA.PLANES P");
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Plane plane = new Plane();
				plane = new Plane();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				plane.setManufacturer(rs.getString("MANUFACTURER"));
				plane.setModel(rs.getString("MODEL"));
				plane.setClasses(rs.getLong("CLASSES"));
				plane.setSeats(rs.getLong("SEATS"));
				planes.add(plane);
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
		
		return planes;
	}
	
	@Override
	public Plane savePlane(Plane plane, boolean update) {
		
		if(!update) {
			plane = insertPlane(plane);
		} else {
			plane = updatePlane(plane);
		}
		return plane;
	}
	
	@Override
	public void deletePlane(String planeNo) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.PLANES WHERE PLANE_NO = ?");
			stmt.setString(1, planeNo);
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
	
private Plane insertPlane(Plane plane) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"INSERT INTO BAZA.PLANES (PLANE_NO, MANUFACTURER, MODEL, CLASSES, SEATS) VALUES (?, ?, ?, ?, ?)");
			stmt.setString(1, plane.getPlaneId());
			stmt.setString(2, plane.getManufacturer());
			stmt.setString(3, plane.getModel());
			stmt.setLong(4, plane.getClasses());
			stmt.setLong(5, plane.getSeats());
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return plane;
	}
	
	private Plane updatePlane(Plane plane) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"UPDATE BAZA.PLANES SET MANUFACTURER = ? , MODEL = ? , CLASSES = ? , SEATS = ? WHERE PLANE_NO = ?");
			stmt.setString(1, plane.getManufacturer());
			stmt.setString(2, plane.getModel());
			stmt.setLong(3, plane.getClasses());
			stmt.setLong(4, plane.getSeats());
			stmt.setString(5, plane.getPlaneId());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return plane;
		
	}

}
