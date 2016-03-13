package com.listmanager.service.impl;

import java.util.List;

import com.listmanager.data.ListItemDao;
import com.listmanager.data.UserDao;
import com.listmanager.data.impl.ListItemDaoImpl;
import com.listmanager.data.impl.UserDaoImpl;
import com.listmanager.model.ListItem;
import com.listmanager.model.User;
import com.listmanager.service.UserService;

public class UserServiceImpl implements UserService {
	private final UserDao userDao;
	private final ListItemDao listItemDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
		this.listItemDao = new ListItemDaoImpl();
	}
	
	@Override
	public User authenticateUser(String username) {
		User user = null;
		
		if(username != null && !"".equals(username.trim())) {
			user = userDao.getUser(username);
		}
		
		return user;
	}
	
	@Override
	public List<ListItem> getListItems(Long userId) {
		return listItemDao.getListItemsByUserId(userId);
	}

}
