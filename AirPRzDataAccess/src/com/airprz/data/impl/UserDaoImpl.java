package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.airprz.data.UserDao;
import com.airprz.model.User;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;

public class UserDaoImpl implements UserDao {
	
	@Override
	public User getUser(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		User user = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT U.USER_ID, U.EMAIL, U.LEVEL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, U.NAME_3RD, U.PHONE_3RD "
					+ "FROM BAZA.USERS U "
					+ "WHERE LOWER(U.USER_ID) = ?");
			stmt.setLong(1, userId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setLevel(rs.getLong("LEVEL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				user.setName_3rd(rs.getString("NAME_3RD"));
				user.setPhone_3rd(rs.getString("PHONE_3RD"));
				
				user.setPassword(null);
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
		
		return user;
	}
	
	@Override
	public Long authenticateUser(String email, String password) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Long UserId = new Long(0);
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT U.USER_ID, U.EMAIL, U.PASSWORD "
					+ "FROM BAZA.USERS U "
					+ "WHERE LOWER(U.EMAIL) = ? AND U.PASSWORD = ?");
			stmt.setString(1, email.toLowerCase());
			stmt.setString(2, password);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				UserId = rs.getLong("USER_ID");
			}
			else {
				UserId = (long) 0;
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
		
		return UserId;
	}

	@Override
	public User saveUser(User user) {
		if(user.getId() == null) {
			user = insertUser(user);
		} else {
			user = updateUser(user);
		}
		return user;
	}
	
	private User insertUser(User user) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement("SELECT EMAIL FROM BAZA.USERS WHERE EMAIL = ?");
			stmt.setString(1, user.getEmail());
			
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, user.getEmail());
				stmt.setString(2, user.getPassword());
				stmt.setLong(3, user.getLevel());
				stmt.setString(4, user.getFirstname());
				stmt.setString(5, user.getLastname());
				stmt.setLong(6, user.getHonorific());
				stmt.setString(7, user.getPhone());
				stmt.setString(8, user.getName_3rd());
				stmt.setString(9, user.getPhone_3rd());
				
				stmt.executeUpdate();
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.USERS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					user.setId(rs.getLong("last_cod"));
				}
			}
			else {
				user = null;
				
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
		
		return user;
		
	}
	
	private User updateUser(User user) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement("SELECT EMAIL FROM BAZA.USERS WHERE EMAIL = ?");
			stmt.setString(1, user.getEmail());
			
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				stmt = connection.prepareStatement(
						"UPDATE BAZA.USERS SET EMAIL = ?, PASSWORD = ? , LEVEL = ? , FIRSTNAME = ? , LASTNAME = ? , HONORIFIC = ? , PHONE = ? , NAME_3RD = ? , PHONE_3RD = ? WHERE USER_ID = ?");
				stmt.setString(1, user.getEmail());
				stmt.setString(2, user.getPassword());
				stmt.setLong(3, user.getLevel());
				stmt.setString(4, user.getFirstname());
				stmt.setString(5, user.getLastname());
				stmt.setLong(6, user.getHonorific());
				stmt.setString(7, user.getPhone());
				stmt.setString(8, user.getName_3rd());
				stmt.setString(9, user.getPhone_3rd());
				stmt.setLong(10, user.getId());
				stmt.executeUpdate();
			}
			else {
				user = null;
				
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return user;
		
	}
	
	@Override
	public void deleteUser(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.USERS WHERE USER_ID = ?");
			stmt.setLong(1, userId);
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
}
