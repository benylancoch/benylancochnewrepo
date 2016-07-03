package com.airprz.service.impl;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.airprz.data.TaxDao;
import com.airprz.data.TransactionDao;
import com.airprz.data.impl.TaxDaoImpl;
import com.airprz.data.impl.TransactionDaoImpl;
import com.airprz.model.PromoCode;
import com.airprz.model.Tax;
import com.airprz.model.Transaction;
import com.airprz.model.User;
import com.airprz.service.TransactionService;

public class TransactionServiceImpl implements TransactionService {
	
	private final TransactionDao transactionDao; 
	
	public TransactionServiceImpl() {
		this.transactionDao = new TransactionDaoImpl();
	}
	
	@Override
	public Transaction getTransaction(Long transactionId) {
		Transaction transaction = null;
		if (transactionId != null) {

			transaction = transactionDao.getTransaction(transactionId);
		}
		
		return transaction;
	}
	
	@Override
	public List<Transaction> getTransactions(Timestamp start, Timestamp end, Boolean paid) {
		return transactionDao.getTransactions(start, end, paid);
	}
	
	@Override
	public List<Transaction> getUserTransactions(Timestamp start, Timestamp end, Long userId) {
		return transactionDao.getUserTransactions(start, end, userId);
	}
	
	@Override
	public Transaction addTransaction(Date date, String paidUsing, Long userId, Long codeId) {
		TaxDao taxDao = new TaxDaoImpl();
		Tax tax = null;
		Transaction transaction = new Transaction();
		User user = new User();
		
		if (date != null && paidUsing != null && codeId != null && !"".equals(paidUsing)) {
			tax = new Tax();
			tax = taxDao.getCurrentTax();
			PromoCode promoCode = new PromoCode();
			transaction.setDate(new Timestamp(date.getTime()));
			transaction.setPaidUsing(paidUsing);
			user.setId(userId);
			transaction.setUser(user);
			transaction.setTax(tax);
			promoCode.setCodeId(codeId);
			transaction.setPromoCode(promoCode);
			
			transaction = transactionDao.saveTransaction(transaction);
		}
		else {
			transaction = null;
		}
		
		return transaction;
	}
	
	@Override
	public Transaction updateTransaction(Long transactionId, Date date, String paidUsing, Date paidDate, Long userId, Long codeId) {
		
		Transaction transaction = transactionDao.getTransaction(transactionId);
		TaxDao taxDao = new TaxDaoImpl();
		Tax tax = null;
		User user = new User();
		
		if (transaction != null) {
			tax = new Tax();
			tax = taxDao.getCurrentTax();
			PromoCode promoCode = new PromoCode();
			transaction.setDate(new Timestamp(date.getTime()));
			transaction.setPaidUsing(paidUsing);
			user.setId(userId);
			transaction.setUser(user);
			transaction.setTax(tax);
			promoCode.setCodeId(codeId);
			transaction.setPromoCode(promoCode);
			
			transaction = transactionDao.saveTransaction(transaction);
		}
		else {
			transaction = null;
		}
		
		return transaction;
	}
	
	@Override
	public Transaction markAsPaid(Transaction transaction) {
		
		return transactionDao.markAsPaid(transaction);
	}
	
	@Override
	public void deleteTransaction(Long transactionId) {

		Transaction transaction = transactionDao.getTransaction(transactionId);
		if (transaction != null) {
			transactionDao.deleteTransaction(transactionId);
		}
	}

}
