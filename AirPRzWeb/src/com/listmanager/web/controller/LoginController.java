package com.listmanager.web.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.listmanager.model.User;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;
import com.listmanager.web.model.UserBean;

@ManagedBean
@RequestScoped
public class LoginController {
	
	private final UserService userService;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public LoginController() {
		userService = new UserServiceImpl();
	}
	
	public String login() {
		String outcome = null;
		
		User user = userService.authenticateUser(userBean.getUsername());
		if(user !=null) {
			userBean.setUserId(user.getId());
			outcome = "list?faces-redirect=true";
		}
		
		return outcome;
	}
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}
