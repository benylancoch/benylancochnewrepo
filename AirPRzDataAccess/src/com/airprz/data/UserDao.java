package com.airprz.data;

import com.airprz.model.User;

public interface UserDao {
	
	User getUser(Long userId);
	
	Long authenticateUser(String email, String password);
	
	User saveUser(User user);
	
	void deleteUser(Long userId);
	

}
