package com.airprz.service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.airprz.model.User;

public interface UserService {
	
	User authenticateUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User getUser(Long userId);
	
	User addUser(String email, String password, Long level, String firstname, String lastname, Long honorific, String phone, String name3rd, String phone3rd) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User addUser(String email, String password, Long level, String firstname, String lastname, Long honorific, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User updateUser(Long userId, String email, String password, Long level, String firstname, String lastname, Long honorific, String phone, String name3rd, String phone3rd) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User updateUser(Long userId, String email, String password, Long level, String firstname, String lastname, Long honorific, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	void deleteUser(Long userId);
	

}
