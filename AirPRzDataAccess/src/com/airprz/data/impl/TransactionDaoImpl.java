package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.airprz.data.TransactionDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.PromoCode;
import com.airprz.model.Tax;
import com.airprz.model.Transaction;
import com.airprz.model.User;

public class TransactionDaoImpl implements TransactionDao {
	
	@Override
	public Transaction getTransaction(Long transactionId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Tax tax = null;
		Transaction transaction = null;
		User user = null;
		PromoCode promoCode = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT T.TRANSACTION_ID, T.DATE, T.PAID_USING, T.PAID_DATE, T.TAX_ID, TX.VALUE, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, "
					+ "T.CODE_ID, PC.CODE, PC.DISCOUNT "
					+ "FROM BAZA.TRANSACTIONS T "
					+ "INNER JOIN BAZA.TAXES TX "
					+ "ON T.TAX_ID = TX.TAX_ID "
					+ "INNER JOIN BAZA.USERS U "
					+ "ON T.USER_ID = U.USER_ID "
					+ "INNER JOIN BAZA.PROMO_CODES PC "
					+ "ON T.CODE_ID = PC.CODE_ID "
					+ "WHERE T.TRANSACTION_ID = ?");
			stmt.setLong(1, transactionId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				tax = new Tax();
				transaction = new Transaction();
				user = new User();
				promoCode = new PromoCode();
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				transaction.setDate(rs.getTimestamp("DATE"));
				transaction.setPaidUsing(rs.getString("PAID_USING"));
				transaction.setPaidDate(rs.getTimestamp("PAID_DATE"));
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				transaction.setTax(tax);
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				transaction.setUser(user);
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				promoCode.setCode(rs.getString("CODE"));
				promoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				transaction.setPromoCode(promoCode);
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
		
		return transaction;
	}
	
	@Override
	public List<Transaction> getTransactions(Timestamp start, Timestamp end, Boolean includePaid) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Tax tax = null;
		Transaction transaction = null;
		User user = null;
		PromoCode promoCode = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			connection = DbConnector.getConnection();
			
			if (!includePaid) {
				stmt = connection.prepareStatement(
						"SELECT T.TRANSACTION_ID, T.DATE, T.PAID_USING, T.PAID_DATE, T.TAX_ID, TX.VALUE, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, "
						+ "T.CODE_ID, PC.CODE, PC.DISCOUNT "
						+ "FROM BAZA.TRANSACTIONS T "
						+ "INNER JOIN BAZA.TAXES TX "
						+ "ON T.TAX_ID = TX.TAX_ID "
						+ "INNER JOIN BAZA.USERS U "
						+ "ON T.USER_ID = U.USER_ID "
						+ "INNER JOIN BAZA.PROMO_CODES PC "
						+ "ON T.CODE_ID = PC.CODE_ID "
						+ "WHERE T.DATE BETWEEN ? AND ? AND T.PAID_DATE IS NULL "
						+ "ORDER BY T.DATE DESC");
				
				stmt.setTimestamp(1, start);
				stmt.setTimestamp(2, end);
			}
			else {
				stmt = connection.prepareStatement(
						"SELECT T.TRANSACTION_ID, T.DATE, T.PAID_USING, T.PAID_DATE, T.TAX_ID, TX.VALUE, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, "
						+ "T.CODE_ID, PC.CODE, PC.DISCOUNT "
						+ "FROM BAZA.TRANSACTIONS T "
						+ "INNER JOIN BAZA.TAXES TX "
						+ "ON T.TAX_ID = TX.TAX_ID "
						+ "INNER JOIN BAZA.USERS U "
						+ "ON T.USER_ID = U.USER_ID "
						+ "INNER JOIN BAZA.PROMO_CODES PC "
						+ "ON T.CODE_ID = PC.CODE_ID "
						+ "WHERE T.DATE BETWEEN ? AND ? "
						+ "ORDER BY T.DATE DESC");
				
				stmt.setTimestamp(1, start);
				stmt.setTimestamp(2, end);
			}
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				tax = new Tax();
				transaction = new Transaction();
				user = new User();
				promoCode = new PromoCode();
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				transaction.setDate(rs.getTimestamp("DATE"));
				transaction.setPaidUsing(rs.getString("PAID_USING"));
				transaction.setPaidDate(rs.getTimestamp("PAID_DATE"));
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				transaction.setUser(user);
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				promoCode.setCode(rs.getString("CODE"));
				promoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				transaction.setPromoCode(promoCode);
				
				transactions.add(transaction);
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
		
		return transactions;
	}
	
	@Override
	public List<Transaction> getUserTransactions(Timestamp start, Timestamp end, Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Tax tax = null;
		Transaction transaction = null;
		User user = null;
		PromoCode promoCode = null;
		List<Transaction> transactions = new ArrayList<Transaction>();
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"SELECT T.TRANSACTION_ID, T.DATE, T.PAID_USING, T.PAID_DATE, T.TAX_ID, TX.VALUE, T.USER_ID, U.EMAIL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, "
					+ "T.CODE_ID, PC.CODE, PC.DISCOUNT "
					+ "FROM BAZA.TRANSACTIONS T "
					+ "INNER JOIN BAZA.TAXES TX "
					+ "ON T.TAX_ID = TX.TAX_ID "
					+ "INNER JOIN BAZA.USERS U "
					+ "ON T.USER_ID = U.USER_ID "
					+ "INNER JOIN BAZA.PROMO_CODES PC "
					+ "ON T.CODE_ID = PC.CODE_ID "
					+ "WHERE T.DATE BETWEEN ? AND ? AND T.USER_ID = ? "
					+ "ORDER BY T.DATE DESC");
			
			stmt.setTimestamp(1, start);
			stmt.setTimestamp(2, end);
			stmt.setLong(3, userId);
			
			rs = stmt.executeQuery();
			while (rs.next()) {
				tax = new Tax();
				transaction = new Transaction();
				user = new User();
				promoCode = new PromoCode();
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				transaction.setDate(rs.getTimestamp("DATE"));
				transaction.setPaidUsing(rs.getString("PAID_USING"));
				transaction.setPaidDate(rs.getTimestamp("PAID_DATE"));
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				transaction.setUser(user);
				promoCode.setCodeId(rs.getLong("CODE_ID"));
				promoCode.setCode(rs.getString("CODE"));
				promoCode.setDiscount(rs.getBigDecimal("DISCOUNT"));
				transaction.setPromoCode(promoCode);
				
				transactions.add(transaction);
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
		
		return transactions;
	}
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		if(transaction.getTransactionId() == null) {
			
			transaction = insertTransaction(transaction);
			
		} else {
			
			transaction = updateTransaction(transaction);
			
		}
		return transaction;
	}
	
	@Override
	public Transaction markAsPaid(Transaction transaction) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement(
					"UPDATE BAZA.TRANSACTIONS SET PAID_DATE = ?");
			stmt.setTimestamp(1, transaction.getPaidDate());
			stmt.executeUpdate();
			
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return transaction;
	}
	
	
	@Override
	public void deleteTransaction(Long transactionId) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.TRANSACTIONS WHERE TRANSACTION_ID = ?");
			stmt.setLong(1, transactionId);
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
	
	private Transaction insertTransaction(Transaction transaction) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				connection = DbConnector.getConnection();
				
				if (transaction.getPromoCode().getCodeId() != 0) {
					stmt = connection.prepareStatement(
							"INSERT INTO BAZA.TRANSACTIONS (DATE, PAID_USING, TAX_ID, USER_ID, CODE_ID) VALUES (?, ?, ?, ?, ?)");
					stmt.setTimestamp(1, transaction.getDate());
					stmt.setString(2, transaction.getPaidUsing());
					stmt.setLong(3, transaction.getTax().getTaxId());
					stmt.setLong(4, transaction.getUser().getId());
					stmt.setLong(5, transaction.getPromoCode().getCodeId());
				}
				else {
					stmt = connection.prepareStatement(
							"INSERT INTO BAZA.TRANSACTIONS (DATE, PAID_USING, TAX_ID, USER_ID) VALUES (?, ?, ?, ?)");
					stmt.setTimestamp(1, transaction.getDate());
					stmt.setString(2, transaction.getPaidUsing());
					stmt.setLong(3, transaction.getTax().getTaxId());
					stmt.setLong(4, transaction.getUser().getId());
				}
				stmt.executeUpdate();
				
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.TRANSACTIONS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					transaction.setTransactionId(rs.getLong("last_cod"));
				}
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return transaction;
		}
		
	private Transaction updateTransaction(Transaction transaction) {
			
			Connection connection = null;
			PreparedStatement stmt = null;
			
			
			try {
				connection = DbConnector.getConnection();
				
				stmt = connection.prepareStatement(
						"UPDATE BAZA.TRANSACTIONS SET DATE = ?, PAID_USING = ?, PAID_DATE = ?, TAX_ID = ?, USER_ID = ?, CODE_ID = ?");
				stmt.setTimestamp(1, transaction.getDate());
				stmt.setString(2, transaction.getPaidUsing());
				stmt.setTimestamp(3, transaction.getPaidDate());
				stmt.setLong(4, transaction.getTax().getTaxId());
				stmt.setLong(5, transaction.getUser().getId());
				stmt.setLong(6, transaction.getPromoCode().getCodeId());
				stmt.executeUpdate();
				
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				JdbcCloses.closeIgnoreError(stmt);
				JdbcCloses.closeIgnoreError(connection);
			}
			
			return transaction;
			
		}

}
