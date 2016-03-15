package com.airprz.web.model;

//import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.airprz.service.UserService;
import com.airprz.service.impl.UserServiceImpl;

/*import com.listmanager.model.ListItem;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;*/

@ManagedBean
@SessionScoped
public class UserBean {
	
	private final UserService userService;
	
	private Long userId;
	private String email;
	private String password;
	private String cPassword;
	private Long level;
	private String firstname;
	private String lastname;
	private Long honorific;
	private String phone;
	private String name3rd;
	private String phone3rd;
	
	public UserBean() {
		userService = new UserServiceImpl();
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getcPassword() {
		return cPassword;
	}
	
	public void setcPassword(String cPassword) {
		this.cPassword = cPassword;
	}
	
	public Long getLevel() {
		return level;
	}
	
	public void setLevel(Long level) {
		this.level = level;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public Long getHonorific() {
		return honorific;
	}
	
	public void setHonorific(Long honorific) {
		this.honorific = honorific;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getName3rd() {
		return name3rd;
	}
	
	public void setName3rd(String name3rd) {
		this.name3rd = name3rd;
	}
	
	public String getPhone3rd() {
		return phone3rd;
	}
	
	public void setPhone3rd(String phone3rd) {
		this.phone3rd = phone3rd;
	}
	
	/*private final UserService userService;
	
	private Long userId;
	private String username;
	
	public UserBean() {
		userService = new UserServiceImpl();
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<ListItem> getListItems() {
		return userService.getListItems(userId);
	}*/

}
