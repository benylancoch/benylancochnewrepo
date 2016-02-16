package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.UserPromoCodeDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.PromoCode;
import com.airprz.model.User;
import com.airprz.model.UserPromoCode;

public class UserPromoCodeDaoImpl implements UserPromoCodeDao {
	
	@Override
	public UserPromoCode checkIfUserUserPromoCode(Long userId, Long codeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		User user = null;
		PromoCode promoCode = null;		
		UserPromoCode userPromoCode = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT UPC.UPC_ID, UPC.USER_ID, UPC.CODE_ID "
					+ "FROM BAZA.USR_PCODES UPC "
					+ "WHERE UPC.USER_ID = ? AND UPC.CODE_ID = ?");
			stmt.setLong(1, userId);
			stmt.setLong(2, codeId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				promoCode = new PromoCode();
				user.setId(rs.getLong("USER_ID"));
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				userPromoCode = new UserPromoCode();
				userPromoCode.setUpcId(rs.getLong("UPC_ID"));
				
				userPromoCode.setUser(user);
				userPromoCode.setPromoCode(promoCode);
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
		
		return userPromoCode;
	}
	
	@Override
	public List<UserPromoCode> getUsers(Long codeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User user = null;
		PromoCode promoCode = null;	
		List<UserPromoCode> userPromoCodes = new ArrayList<UserPromoCode>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT UPC.UPC_ID, UPC.USER_ID, UPC.CODE_ID "
							+ "FROM BAZA.USR_PCODES UPC "
							+ "WHERE UPC.CODE_ID = ?");
			
			stmt.setLong(1, codeId);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserPromoCode userPromoCode = new UserPromoCode();
				user = new User();
				promoCode = new PromoCode();
				user.setId(rs.getLong("USER_ID"));
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				userPromoCode.setUpcId(rs.getLong("UPC_ID"));
				
				userPromoCode.setUser(user);
				userPromoCode.setPromoCode(promoCode);
				userPromoCodes.add(userPromoCode);
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
		
		return userPromoCodes;
	}

	@Override
	public List<UserPromoCode> getPromoCodes(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		User user = null;
		PromoCode promoCode = null;	
		List<UserPromoCode> userPromoCodes = new ArrayList<UserPromoCode>();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT UPC.UPC_ID, UPC.USER_ID, UPC.CODE_ID "
							+ "FROM BAZA.USR_PCODES UPC "
							+ "WHERE UPC.USER_ID = ?");
			
			stmt.setLong(1, userId);
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				UserPromoCode userPromoCode = new UserPromoCode();
				user = new User();
				promoCode = new PromoCode();
				user.setId(rs.getLong("USER_ID"));
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				userPromoCode.setUpcId(rs.getLong("UPC_ID"));
				
				userPromoCode.setUser(user);
				userPromoCode.setPromoCode(promoCode);
				userPromoCodes.add(userPromoCode);
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
		
		return userPromoCodes;
	}
	
	@Override
	public UserPromoCode saveUserPromoCode(UserPromoCode userPromoCode) {
		if(userPromoCode.getUpcId() == null) {
			userPromoCode = insertUserPromoCode(userPromoCode);
		} else {
			userPromoCode = updateUserPromoCode(userPromoCode);
		}
		return userPromoCode;
	}
	
	@Override
	public UserPromoCode getUserPromoCode(Long userPromoCodeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		User user = null;
		PromoCode promoCode = null;		
		UserPromoCode userPromoCode = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT UPC.UPC_ID, UPC.USER_ID, UPC.CODE_ID "
					+ "FROM BAZA.USR_PCODES UPC "
					+ "WHERE UPC.UPC_ID = ?");
			stmt.setLong(1, userPromoCodeId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				promoCode = new PromoCode();
				user.setId(rs.getLong("USER_ID"));
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				userPromoCode = new UserPromoCode();
				userPromoCode.setUpcId(rs.getLong("UPC_ID"));
				
				userPromoCode.setUser(user);
				userPromoCode.setPromoCode(promoCode);
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
		
		return userPromoCode;
	}
	
	@Override
	public void deleteUserPromoCode(Long userPromoCodeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.USR_PCODES WHERE UPC_ID = ?");
			stmt.setLong(1, userPromoCodeId);
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
	
			private UserPromoCode insertUserPromoCode(UserPromoCode userPromoCode) {
					
					Connection connection = null;
					PreparedStatement stmt = null;
					ResultSet rs = null;
					
					try {
						connection = DbConnector.getConnection();
						
						stmt = connection.prepareStatement(
								"INSERT INTO BAZA.USR_PCODES (USER_ID, CODE_ID) VALUES (?, ?)");
						stmt.setLong(1, userPromoCode.getUser().getId());
						stmt.setLong(2, userPromoCode.getPromoCode().getCodeId());
						stmt.executeUpdate();
						
						stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.USR_PCODES");
						rs = stmt.executeQuery();
						
						
						if (rs.next()) {
							userPromoCode.setUpcId(rs.getLong("last_cod"));
						}
						
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						JdbcCloses.closeIgnoreError(stmt);
						JdbcCloses.closeIgnoreError(connection);
					}
					
					return userPromoCode;
				}
				
			private UserPromoCode updateUserPromoCode(UserPromoCode userPromoCode) {
					
					Connection connection = null;
					PreparedStatement stmt = null;
					
					
					try {
						connection = DbConnector.getConnection();
						
						stmt = connection.prepareStatement(
								"UPDATE BAZA.USR_PCODES SET USER_ID = ? , CODE_ID = ? WHERE UPC_ID = ?");
						stmt.setLong(1, userPromoCode.getUser().getId());
						stmt.setLong(2, userPromoCode.getPromoCode().getCodeId());
						stmt.setLong(3, userPromoCode.getUpcId());
						stmt.executeUpdate();
						
						stmt.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						JdbcCloses.closeIgnoreError(stmt);
						JdbcCloses.closeIgnoreError(connection);
					}
					
					return userPromoCode;
					
				}


}
