package com.airprz.web.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.model.Transaction;

@ManagedBean
@SessionScoped
public class FinalizedTransactionBean {
	
	private Transaction transaction;
	
	public FinalizedTransactionBean() {
		transaction = new Transaction();
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

}
