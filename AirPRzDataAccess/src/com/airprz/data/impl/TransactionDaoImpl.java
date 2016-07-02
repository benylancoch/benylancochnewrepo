package com.airprz.data.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import com.airprz.data.TransactionDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.Tax;
import com.airprz.model.Transaction;

public class TransactionDaoImpl implements TransactionDao {
	
	@Override
	public Transaction getTransaction(Long transactionId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		Tax tax = null;
		Transaction transaction = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT T.TRANSACTION_ID, T.DATE, T.PAID_USING, T.PAID_DATE, T.TAX_ID, TX.VALUE "
					+ "FROM BAZA.TRANSACTIONS T "
					+ "INNER JOIN BAZA.TAXES TX "
					+ "ON T.TAX_ID = TX.TAX_ID "
					+ "WHERE T.TRANSACTION_ID = ?");
			stmt.setLong(1, transactionId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				tax = new Tax();
				transaction = new Transaction();
				
				transaction.setTransactionId(rs.getLong("TRANSACTION_ID"));
				transaction.setDate(rs.getTimestamp("DATE"));
				transaction.setPaidUsing(rs.getString("PAID_USING"));
				transaction.setPaidDate(rs.getTimestamp("PAID_DATE"));
				tax.setTaxId(rs.getLong("TAX_ID"));
				tax.setValue(rs.getBigDecimal("VALUE"));
				transaction.setTax(tax);
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
	public List<Transaction> getTransactions(Timestamp start, Timestamp end) {
		return null;
	}
	
	@Override
	public Transaction addTransaction(Transaction transaction) {
		return null;
	}
	
	@Override
	public Transaction updateTransaction(Transaction transaction) {
		return null;
	}
	
	@Override
	public void deleteTransaction(Long transactionId) {
		
	}

}
