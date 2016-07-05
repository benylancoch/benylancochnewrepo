package com.airprz.service;

import java.util.Date;
import java.util.List;

import com.airprz.model.Transaction;

public interface TransactionService {
	
	Transaction getTransaction(Long transactionId);
	
	List<Transaction> getTransactions(Date start, Date end, Boolean paid);
	
	List<Transaction> getUserTransactions(Date start, Date end, Long userId);
	
	Transaction addTransaction(Date date, String paidUsing, Long userId, Long codeId);
	
	Transaction updateTransaction(Long transactionId, Date date, String paidUsing, Date paidDate, Long userId, Long codeId);
	
	Transaction markAsPaid(Transaction transaction);
	
	void deleteTransaction(Long transactionId);

}
