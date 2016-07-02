package com.airprz.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.airprz.model.Transaction;
import com.airprz.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	@Override
	public Transaction getTransaction(Long transactionId) {
		return null;
	}
	
	@Override
	public List<Transaction> getTransactions(Timestamp start, Timestamp end) {
		return null;
	}
	
	@Override
	public Transaction addTransaction(Date date, String paidUsing) {
		return null;
	}
	
	@Override
	public Transaction updateTransaction(Long transactionId, Date date, String paidUsing, Date paidDate) {
		return null;
	}
	
	@Override
	public void deleteTransaction(Long transactionId) {

	}

}
