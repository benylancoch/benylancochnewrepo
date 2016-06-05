package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.ReferenceSeatDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.ReferenceSeat;
import com.airprz.model.Plane;

public class ReferenceSeatDaoImpl implements ReferenceSeatDao {
	
	@Override
	public ReferenceSeat getReferenceSeat(Long referenceSeatId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;	
		ReferenceSeat referenceSeat = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT RS.RS_ID, RS.CLASS, RS.SEAT_NO, RS.FREE, RS.WHERE_LOC, RS.PLANE_NO "
					+ "FROM BAZA.REFERENCE_SEATS RS "
					+ "WHERE RS.RS_ID = ?");
			stmt.setLong(1, referenceSeatId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				referenceSeat = new ReferenceSeat();
				referenceSeat.setRsId(rs.getLong("RS_ID"));
				referenceSeat.setRsClass(rs.getLong("CLASS"));
				referenceSeat.setSeatNo(rs.getLong("SEAT_NO"));
				referenceSeat.setFree(rs.getString("FREE"));
				referenceSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				referenceSeat.setPlane(plane);
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
		
		return referenceSeat;
	}
	
	@Override
	public ReferenceSeat getReferenceSeatBySeatNo(Long seatNo, Long planeNo) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Plane plane = null;		
		ReferenceSeat referenceSeat = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT RS.RS_ID, RS.CLASS, RS.SEAT_NO, RS.FREE, RS.WHERE_LOC, RS.PLANE_NO "
					+ "FROM BAZA.REFERENCE_SEATS RS "
					+ "WHERE RS.SEAT_NO = ? AND RS.PLANE_NO = ?");
			stmt.setLong(1, seatNo);
			stmt.setLong(2, planeNo);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				plane = new Plane();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				referenceSeat = new ReferenceSeat();
				referenceSeat.setRsId(rs.getLong("RS_ID"));
				referenceSeat.setRsClass(rs.getLong("CLASS"));
				referenceSeat.setSeatNo(rs.getLong("SEAT_NO"));
				referenceSeat.setFree(rs.getString("FREE"));
				referenceSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				referenceSeat.setPlane(plane);
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
		
		return referenceSeat;
	}
	
	@Override
	public List<ReferenceSeat> getSeats(String planeNo) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Plane plane = null;
		List<ReferenceSeat> referenceSeats = new ArrayList<ReferenceSeat>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT RS.RS_ID, RS.CLASS, RS.SEAT_NO, RS.FREE, RS.WHERE_LOC, RS.PLANE_NO "
							+ "FROM BAZA.REFERENCE_SEATS RS "
							+ "WHERE RS.PLANE_NO = ?");
			
			stmt.setString(1, planeNo);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				ReferenceSeat referenceSeat = new ReferenceSeat();
				plane = new Plane();
				plane.setPlaneId(rs.getString("PLANE_NO"));
				referenceSeat = new ReferenceSeat();
				referenceSeat.setRsId(rs.getLong("RS_ID"));
				referenceSeat.setRsClass(rs.getLong("CLASS"));
				referenceSeat.setSeatNo(rs.getLong("SEAT_NO"));
				referenceSeat.setFree(rs.getString("FREE"));
				referenceSeat.setWhereLoc(rs.getString("WHERE_LOC"));
				
				referenceSeat.setPlane(plane);
				referenceSeats.add(referenceSeat);
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
		
		return referenceSeats;
	}
	
	@Override
	public ReferenceSeat saveReferenceSeat(ReferenceSeat referenceSeat) {
		if(referenceSeat.getRsId() == null) {
			referenceSeat = insertReferenceSeat(referenceSeat);
		} else {
			referenceSeat = updateReferenceSeat(referenceSeat);
		}
		return referenceSeat;
	}
	
	@Override
	public Boolean addMultipleReferenceSeats(Long seatClass, Long seatNoStart, Long seatNoEnd, String planeNo) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		String insertString = null;
		
		try {
			connection = DbConnector.getConnection();
			
			
			
			insertString = "INSERT INTO BAZA.REFERENCE_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO) VALUES";
			for (Long i = seatNoStart; i <= seatNoEnd; i++) {
				insertString += " (" + seatClass + ", " + i + ", 'Y', 'S|M', '" + planeNo + "')";
				
				if (i.intValue() != seatNoEnd.intValue()) {
					insertString += ",";
				}
			}
			
			stmt = connection.prepareStatement(insertString);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return true;
	}
	
	
	@Override
	public Boolean copyReferenceSeatsToFlightSeats(String planeNo, Long flightId) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) "
					+ "SELECT CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, ? FROM BAZA.REFERENCE_SEATS "
					+ "WHERE PLANE_NO = ?");
			stmt.setLong(1, flightId);
			stmt.setString(2, planeNo);
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return true;
	}
	
	@Override
	public void deleteReferenceSeat(Long referenceSeatId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.REFERENCE_SEATS WHERE RS_ID = ?");
			stmt.setLong(1, referenceSeatId);
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
	
	private ReferenceSeat insertReferenceSeat(ReferenceSeat referenceSeat) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.REFERENCE_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO) VALUES (?, ?, ?, ?, ?)");
				stmt.setLong(1, referenceSeat.getRsClass());
				stmt.setLong(2, referenceSeat.getSeatNo());
				stmt.setString(3, "Y");
				stmt.setString(4, referenceSeat.getWhereLoc());
				stmt.setString(5, referenceSeat.getPlane().getPlaneId());
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.REFERENCE_SEATS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					referenceSeat.setRsId(rs.getLong("last_cod"));
					referenceSeat.setFree("Y");
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return referenceSeat;
		}
		
	private ReferenceSeat updateReferenceSeat(ReferenceSeat referenceSeat) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.REFERENCE_SEATS SET CLASS = ?, SEAT_NO = ?, FREE = ?, WHERE_LOC = ?, PLANE_NO = ? WHERE RS_ID = ?");
				stmt.setLong(1, referenceSeat.getRsClass());
				stmt.setLong(2, referenceSeat.getSeatNo());
				stmt.setString(3, referenceSeat.getFree());
				stmt.setString(4, referenceSeat.getWhereLoc());
				stmt.setString(5, referenceSeat.getPlane().getPlaneId());
				stmt.setLong(6, referenceSeat.getRsId());
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return referenceSeat;
			
		}

}
