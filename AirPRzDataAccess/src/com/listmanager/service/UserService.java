package com.listmanager.service;

import java.util.List;

import com.listmanager.model.ListItem;
import com.listmanager.model.User;

public interface UserService {
	
	User authenticateUser(String username);
	
	List<ListItem> getListItems(Long userId);
	

}
