package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.PromoCodeDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.PromoCode;

public class PromoCodeDaoImpl implements PromoCodeDao {
	
	@Override
	public PromoCode getPromoCode(Long codeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		PromoCode promoCode = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT PC.CODE_ID, PC.CODE, PC.DESCRIPTION, PC.DISCOUNT, PC.MULTIPLE, PC.USED, PC.VALID_FROM, PC.VALID_TO "
					+ "FROM BAZA.PROMO_CODES PC "
					+ "WHERE PC.CODE_ID = ?");
			stmt.setLong(1, codeId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				promoCode = new PromoCode();
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				promoCode.setCode(rs.getString("CODE"));
				promoCode.setDescription(rs.getString("DESCRIPTION"));
				promoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				promoCode.setMultiple(rs.getString("MULTIPLE"));
				promoCode.setUsed(rs.getString("USED"));
				promoCode.setValidFrom(rs.getTimestamp("VALID_FROM"));
				promoCode.setValidTo(rs.getTimestamp("VALID_TO"));
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
		
		return promoCode;
	}
	
	@Override
	public PromoCode searchForPromoCode(String promoCode) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		PromoCode tmpPromoCode = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT PC.CODE_ID, PC.CODE, PC.DESCRIPTION, PC.DISCOUNT, PC.MULTIPLE, PC.USED, PC.VALID_FROM, PC.VALID_TO "
					+ "FROM BAZA.PROMO_CODES PC "
					+ "WHERE PC.CODE = ? AND PC.USED = 'N'");
			stmt.setString(1, promoCode);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				tmpPromoCode = new PromoCode();
				tmpPromoCode.setCodeId(rs.getLong("CODE_ID"));
				tmpPromoCode.setCode(rs.getString("CODE"));
				tmpPromoCode.setDescription(rs.getString("DESCRIPTION"));
				tmpPromoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				tmpPromoCode.setMultiple(rs.getString("MULTIPLE"));
				tmpPromoCode.setUsed(rs.getString("USED"));
				tmpPromoCode.setValidFrom(rs.getTimestamp("VALID_FROM"));
				tmpPromoCode.setValidTo(rs.getTimestamp("VALID_TO"));
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
		
		return tmpPromoCode;
	}
	
	@Override
	public List<PromoCode> getPromoCodes(boolean valid) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		List<PromoCode> promoCodes = new ArrayList<PromoCode>();
		try {
			connection = DbConnector.getConnection();
			
			if(!valid) {
				stmt = connection.prepareStatement(
						"SELECT PC.CODE_ID, PC.CODE, PC.DESCRIPTION, PC.DISCOUNT, PC.MULTIPLE, PC.USED, PC.VALID_FROM, PC.VALID_TO "
								+ "FROM BAZA.PROMO_CODES PC "
								+ "WHERE (PC.VALID_TO > current timestamp OR PC.VALID_TO IS NULL) AND PC.USED = 'N'");
			} 
			else {
				stmt = connection.prepareStatement(
						"SELECT PC.CODE_ID, PC.CODE, PC.DESCRIPTION, PC.DISCOUNT, PC.MULTIPLE, PC.USED, PC.VALID_FROM, PC.VALID_TO "
								+ "FROM BAZA.PROMO_CODES PC");
			}
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				PromoCode promoCode = new PromoCode();
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				promoCode.setCode(rs.getString("CODE"));
				promoCode.setDescription(rs.getString("DESCRIPTION"));
				promoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				promoCode.setMultiple(rs.getString("MULTIPLE"));
				promoCode.setUsed(rs.getString("USED"));
				promoCode.setValidFrom(rs.getTimestamp("VALID_FROM"));
				promoCode.setValidTo(rs.getTimestamp("VALID_TO"));
				promoCodes.add(promoCode);
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
		
		return promoCodes;
	}
	
	@Override
	public PromoCode savePromoCode(PromoCode promoCode) {
		if(promoCode.getCodeId() == null) {
			promoCode = insertPromoCode(promoCode);
		} else {
			promoCode = updatePromoCode(promoCode);
		}
		return promoCode;
	}
	
	@Override
	public void deletePromoCode(Long codeId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.PROMO_CODES WHERE CODE_ID = ?");
			stmt.setLong(1, codeId);
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
	
		private PromoCode insertPromoCode(PromoCode promoCode) {
				
				Connection connection = null;
				PreparedStatement stmt = null;
				ResultSet rs = null;
				
				try {
					connection = DbConnector.getConnection();
					
					stmt = connection.prepareStatement(
							"INSERT INTO BAZA.PROMO_CODES (CODE, DESCRIPTION, DISCOUNT, MULTIPLE, USED, VALID_FROM, VALID_TO) VALUES (?, ?, ?, ?, ?, ?, ?)");
					stmt.setString(1, promoCode.getCode());
					stmt.setString(2, promoCode.getDescription());
					stmt.setBigDecimal(3, promoCode.getDiscount());
					stmt.setString(4, promoCode.getMultiple());
					stmt.setString(5, promoCode.getUsed());
					stmt.setTimestamp(6, promoCode.getValidFrom());
					stmt.setTimestamp(7, promoCode.getValidTo());
					stmt.executeUpdate();
					
					stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.PROMO_CODES");
					rs = stmt.executeQuery();
					
					
					if (rs.next()) {
						promoCode.setCodeId(rs.getLong("last_cod"));
					}
					
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JdbcCloses.closeIgnoreError(stmt);
					JdbcCloses.closeIgnoreError(connection);
				}
				
				return promoCode;
			}
			
			private PromoCode updatePromoCode(PromoCode promoCode) {
				
				Connection connection = null;
				PreparedStatement stmt = null;
				
				
				try {
					connection = DbConnector.getConnection();
					
					stmt = connection.prepareStatement(
							"UPDATE BAZA.PROMO_CODES SET CODE = ? , DESCRIPTION = ? , DISCOUNT = ? , MULTIPLE = ?, USED = ?, VALID_FROM = ? , VALID_TO = ? WHERE CODE_ID = ?");
					stmt.setString(1, promoCode.getCode());
					stmt.setString(2, promoCode.getDescription());
					stmt.setBigDecimal(3, promoCode.getDiscount());
					stmt.setString(4, promoCode.getMultiple());
					stmt.setString(5, promoCode.getUsed());
					stmt.setTimestamp(6, promoCode.getValidFrom());
					stmt.setTimestamp(7, promoCode.getValidTo());
					stmt.setLong(8, promoCode.getCodeId());
					stmt.executeUpdate();
					
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					JdbcCloses.closeIgnoreError(stmt);
					JdbcCloses.closeIgnoreError(connection);
				}
				
				return promoCode;
				
			}

}
