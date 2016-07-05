package com.airprz.web.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.airprz.model.Ticket;
import com.airprz.model.Transaction;
import com.airprz.model.User;
import com.airprz.service.TicketService;
import com.airprz.service.UserService;
import com.airprz.service.impl.TicketServiceImpl;
import com.airprz.service.impl.UserServiceImpl;
import com.airprz.web.model.TransactionBean;
/*import com.listmanager.model.User;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;*/
import com.airprz.web.model.UserBean;
import com.airprz.web.model.UserTransactionSearchBean;

@ManagedBean
@RequestScoped
public class LoginController {
	
	private final UserService userService;
	private final TicketService ticketService;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	@ManagedProperty("#{userTransactionSearchBean}")
	private UserTransactionSearchBean userTransactionSearchBean;
	
	@ManagedProperty("#{transactionBean}")
	private TransactionBean transactionBean;
	
	public LoginController() {
		userService = new UserServiceImpl();
		ticketService = new TicketServiceImpl();
	}
	
	public String login() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String outcome = null;
		
		User user = userService.authenticateUser(userBean.getEmail(), userBean.getPassword());
		
		if (user != null) {
			userBean.setUserId(user.getId());
			userBean.setEmail(user.getEmail());
			userBean.setLevel(user.getLevel());
			userBean.setFirstname(user.getFirstname());
			userBean.setLastname(user.getLastname());
			userBean.setHonorific(user.getHonorific());
			userBean.setPhone(user.getPhone());
			userBean.setName3rd(user.getName_3rd());
			userBean.setPhone3rd(user.getPhone_3rd());
			
			userBean.setPassword("");
			
			outcome = "index?faces-redirect=true";
		}
		
		return outcome;
	}
	
	public String register() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String outcome = null;
		
		User user = null;
		if (userBean.getPassword().equalsIgnoreCase(userBean.getcPassword())) {
			user = userService.addUser(userBean.getEmail(), userBean.getPassword(), userBean.getLevel(), userBean.getFirstname(), 
					userBean.getLastname(), userBean.getHonorific(), userBean.getPhone(), userBean.getName3rd(), userBean.getPhone3rd());
			outcome = "login?faces-redirect=true";
		}
		else {
			outcome = "register?faces-redirect=true";
		}

		return outcome;
	}
	
	public String update() {
		userService.updateUserNoPassword(userBean.getUserId(), userBean.getEmail(), userBean.getFirstname(), 
				userBean.getLastname(), userBean.getHonorific(), userBean.getPhone(), userBean.getName3rd(), userBean.getPhone3rd());
		return "profile?faces-redirect=true";
	}
	
	public String updatePass() throws NoSuchAlgorithmException, InvalidKeySpecException {
		userService.changePassword(userBean.getUserId(), userBean.getPassword());
		return "profile?faces-redirect=true";
	}
	
	public String redirectTicketList(Transaction transaction) {
		transactionBean.setTransactionId(transaction.getTransactionId());
		transactionBean.setDate(transaction.getDate());
		transactionBean.setPaidDate(transaction.getPaidDate());
		transactionBean.setPaidUsing(transaction.getPaidUsing());
		transactionBean.setPromoCode(transaction.getPromoCode());
		transactionBean.setTax(transaction.getTax());
		transactionBean.setUser(transaction.getUser());
		return "tickets?faces-redirect=true";
	}
	
	public List<Ticket> getListTickets(Long transactionId) {
		List<Ticket> tmp = ticketService.getTicketesFromTransaction(transactionId);
		System.out.println("************************************************ddddww**");
		System.out.println(transactionBean.getTransactionId());
		System.out.println(transactionId);
		System.out.println(tmp.size());
		return tmp;
	}
	
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	public void setUserTransactionSearchBean(UserTransactionSearchBean userTransactionSearchBean) {
		this.userTransactionSearchBean = userTransactionSearchBean;
	}
	
	public void setTransactionBean(TransactionBean transactionBean) {
		this.transactionBean = transactionBean;
	}

}
