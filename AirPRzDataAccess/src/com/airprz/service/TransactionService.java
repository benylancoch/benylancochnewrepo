package com.airprz.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.airprz.model.Transaction;

public interface TransactionService {
	
	Transaction getTransaction(Long transactionId);
	
	List<Transaction> getTransactions(Timestamp start, Timestamp end);
	
	Transaction addTransaction(Date date, String paidUsing);
	
	Transaction updateTransaction(Long transactionId, Date date, String paidUsing, Date paidDate);
	
	void deleteTransaction(Long transactionId);

}
