package com.airprz.data;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.airprz.model.User;

public interface UserDao {
	
	User getUser(Long userId);
	
	Long authenticateUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User saveUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	User saveUserNoPassword(User user);
	
	User saveUserPasswordOnly(User user) throws NoSuchAlgorithmException, InvalidKeySpecException;
	
	void deleteUser(Long userId);
	

}
