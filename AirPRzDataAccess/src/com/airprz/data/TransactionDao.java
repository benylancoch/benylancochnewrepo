package com.airprz.data;

import java.sql.Timestamp;
import java.util.List;

import com.airprz.model.Transaction;

public interface TransactionDao {
	
	Transaction getTransaction(Long transactionId);
	
	List<Transaction> getTransactions(Timestamp start, Timestamp end);
	
	Transaction addTransaction(Transaction transaction);
	
	Transaction updateTransaction(Transaction transaction);
	
	void deleteTransaction(Long transactionId);

}
