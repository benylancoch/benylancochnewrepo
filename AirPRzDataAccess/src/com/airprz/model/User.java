package com.airprz.model;

public class User {

	private Long id;
	private String email;
	private String password;
	private Long level;
	private String firstname;
	private String lastname;
	private Long honorific;
	private String phone;
	private String name_3rd;
	private String phone_3rd;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getName_3rd() {
		return name_3rd;
	}
	public void setName_3rd(String name_3rd) {
		this.name_3rd = name_3rd;
	}
	public String getPhone_3rd() {
		return phone_3rd;
	}
	public void setPhone_3rd(String phone_3rd) {
		this.phone_3rd = phone_3rd;
	}
}
