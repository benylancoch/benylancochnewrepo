package com.airprz.data;

import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Transaction;

public interface TransactionDao {
	
	Transaction getTransaction(Long transactionId);
	
	List<Transaction> getTransactions(Timestamp start, Timestamp end, Boolean paid);
	
	List<Transaction> getUserTransactions(Timestamp start, Timestamp end, Long userId);
	
	Transaction saveTransaction(Transaction transaction);
	
	Transaction markAsPaid(Transaction transaction);
	
	void deleteTransaction(Long transactionId);

}
