package com.airprz.web.controller;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import com.airprz.model.User;
import com.airprz.service.UserService;
import com.airprz.service.impl.UserServiceImpl;
/*import com.listmanager.model.User;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;*/
import com.airprz.web.model.UserBean;

@ManagedBean
@RequestScoped
public class LoginController {
	
	private final UserService userService;
	
	@ManagedProperty("#{userBean}")
	private UserBean userBean;
	
	public LoginController() {
		userService = new UserServiceImpl();
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
			
			outcome = "users?faces-redirect=true";
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
	
	
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	/*private final UserService userService;
	
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
	}*/

}
