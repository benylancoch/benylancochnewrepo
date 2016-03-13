package com.listmanager.web.service.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import com.listmanager.model.ListItem;
import com.listmanager.model.User;
import com.listmanager.service.UserService;
import com.listmanager.service.impl.UserServiceImpl;

@WebService(serviceName = "UserSoapService")
public class UserSoapService {
	private final UserService userService;
	
	public UserSoapService() {
		userService = new UserServiceImpl();
	}
	
	@WebMethod
	public Long getUserId(@WebParam(name = "username") String username) {
		User user = userService.authenticateUser(username);
		if (user != null) {
			return user.getId();
		}
		return null;
	}
	
	@WebMethod
	public List<ListItem> getUserListItems(@WebParam(name = "userId") Long userId) {
		return userService.getListItems(userId);
	}

}
