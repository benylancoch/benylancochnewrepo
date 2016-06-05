package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.TaxDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Tax;

public class TaxDaoImpl implements TaxDao {
	
	@Override
	public Tax getTax(Long taxId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Tax tax = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT T.TAX_ID, T.VALUE, T.DESCRIPTION, T.VALID_FROM, T.VALID_TO "
					+ "FROM BAZA.TAXES T "
					+ "WHERE T.TAX_ID = ?");
			stmt.setLong(1, taxId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				tax = new Tax();
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				tax.setDescription(rs.getString("DESCRIPTION"));
				tax.setValidFrom(rs.getTimestamp("VALID_FROM"));
				tax.setValidTo(rs.getTimestamp("VALID_TO"));
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
		
		return tax;
	}
	
	public Tax getCurrentTax() {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Tax tax = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT T.TAX_ID, T.VALUE, T.DESCRIPTION, T.VALID_FROM, T.VALID_TO "
					+ "FROM BAZA.TAXES T "
					+ "WHERE CAST(T.VALID_TO as CHAR) is NULL");
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				tax = new Tax();
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				tax.setDescription(rs.getString("DESCRIPTION"));
				tax.setValidFrom(rs.getTimestamp("VALID_FROM"));
				tax.setValidTo(rs.getTimestamp("VALID_TO"));
				
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
		
		return tax;
		
	}
	
	@Override
	public List<Tax> getTaxes(boolean oldTaxes) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		List<Tax> taxes = new ArrayList<Tax>();
		try {
			connection = DbConnector.getConnection();
			
			if(!oldTaxes) {
				stmt = connection.prepareStatement(
						"SELECT T.TAX_ID, T.VALUE, T.DESCRIPTION, T.VALID_FROM, T.VALID_TO "
								+ "FROM BAZA.TAXES T "
								+ "WHERE T.VALID_TO > current timestamp OR T.VALID_TO IS NULL");
			} 
			else {
				stmt = connection.prepareStatement(
						"SELECT T.TAX_ID, T.VALUE, T.DESCRIPTION, T.VALID_FROM, T.VALID_TO "
								+ "FROM BAZA.TAXES T");
			}
			
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				Tax tax = new Tax();
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				tax.setDescription(rs.getString("DESCRIPTION"));
				tax.setValidFrom(rs.getTimestamp("VALID_FROM"));
				tax.setValidTo(rs.getTimestamp("VALID_TO"));
				taxes.add(tax);
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
		
		return taxes;
	}
	
	@Override
	public Tax saveTax(Tax tax) {
		if(tax.getTaxId() == null) {
			tax = insertTax(tax);
		} else {
			tax = updateTax(tax);
		}
		return tax;
	}
	
	@Override
	public void deleteTax(Long taxId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.TAXES WHERE TAX_ID = ?");
			stmt.setLong(1, taxId);
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
	
	private Tax insertTax(Tax tax) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.TAXES (VALUE, DESCRIPTION, VALID_FROM, VALID_TO) VALUES (?, ?, ?, ?)");
				stmt.setBigDecimal(1, tax.getValue());
				stmt.setString(2, tax.getDescription());
				stmt.setTimestamp(3, tax.getValidFrom());
				stmt.setTimestamp(4, tax.getValidTo());
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.TAXES");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					tax.setTaxId(rs.getLong("last_cod"));
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return tax;
		}
		
		private Tax updateTax(Tax tax) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.TAXES SET VALUE = ? , DESCRIPTION = ? , VALID_FROM = ? , VALID_TO = ? WHERE TAX_ID = ?");
				stmt.setBigDecimal(1, tax.getValue());
				stmt.setString(2, tax.getDescription());
				stmt.setTimestamp(3, tax.getValidFrom());
				stmt.setTimestamp(4, tax.getValidTo());
				stmt.setLong(5, tax.getTaxId());
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return tax;
			
		}

}
