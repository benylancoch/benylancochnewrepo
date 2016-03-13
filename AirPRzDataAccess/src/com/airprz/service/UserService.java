package com.airprz.service;

import com.airprz.model.User;

public interface UserService {
	
	User authenticateUser(String email, String password);
	
	User getUser(Long userId);
	
	User addUser(String email, String password, Long level, String firstname, String lastname, Long honorific, String phone, String name3rd, String phone3rd);
	
	User addUser(String email, String password, Long level, String firstname, String lastname, Long honorific, String phone);
	
	User updateUser(Long userId, String email, String password, Long level, String firstname, String lastname, Long honorific, String phone, String name3rd, String phone3rd);
	
	User updateUser(Long userId, String email, String password, Long level, String firstname, String lastname, Long honorific, String phone);
	
	void deleteUser(Long userId);
	

}
