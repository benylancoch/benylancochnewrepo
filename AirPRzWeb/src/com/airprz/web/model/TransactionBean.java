package com.airprz.web.model;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.PromoCode;
import com.airprz.model.Tax;
import com.airprz.model.Transaction;
import com.airprz.model.User;
import com.airprz.service.TransactionService;
import com.airprz.service.impl.TransactionServiceImpl;

@ManagedBean
@SessionScoped
public class TransactionBean {
	
	private final TransactionService transactionService;
	
	private Long transactionId;
	private Date date;
	private String paidUsing;
	private Date paidDate;
	private Tax tax;
	private User user;
	private PromoCode promoCode;
	
	public TransactionBean() {
		transactionService = new TransactionServiceImpl();
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getPaidUsing() {
		return paidUsing;
	}

	public void setPaidUsing(String paidUsing) {
		this.paidUsing = paidUsing;
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PromoCode getPromoCode() {
		return promoCode;
	}

	public void setPromoCode(PromoCode promoCode) {
		this.promoCode = promoCode;
	}
	
	public List<Transaction> listUserTransactions(Date start, Date end, Long userId) {
		java.util.Date currentDate = new java.util.Date();
		Calendar date = Calendar.getInstance();
		date.setTime(end);
		date.add(Calendar.DAY_OF_WEEK, 1);
		
		currentDate = date.getTime();
		
		List<Transaction> tmp = transactionService.getUserTransactions(start, currentDate, userId);
		return tmp;
	}

}
