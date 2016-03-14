package com.airprz.service.impl;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.airprz.data.UserDao;
import com.airprz.data.impl.UserDaoImpl;
import com.airprz.model.User;
import com.airprz.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserDao userDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}
	
	@Override
	public User authenticateUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = null;
		Long userId = null;
		
		if(email != null && !"".equals(email.trim()) && 
				password != null && !"".equals(password.trim()) && (userId = userDao.authenticateUser(email, password)) != (long) 0) {
			user = userDao.getUser(userId);
		}
		return user;
	}
	
	@Override
	public User getUser(Long userId) {
		User user = null;
		
		if (userId != null) {
			user = userDao.getUser(userId);
		}
		
		return user;
	}
	
	@Override
	public User addUser(String email, String password, Long level,
			String firstname, String lastname, Long honorific, String phone,
			String name3rd, String phone3rd) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		User user = null;
		if (email != null && password != null && level != null && firstname != null && lastname != null && honorific != null
				&& phone != null && !"".equals(email) && !"".equals(password) && !"".equals(firstname) && !"".equals(lastname)
				&& !"".equals(phone)) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setLevel(level);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setHonorific(honorific);
			user.setPhone(phone);
			if (name3rd != null && phone3rd != null && !"".equals(name3rd) && !"".equals(phone3rd)) {
				user.setName_3rd(name3rd);
				user.setPhone_3rd(phone3rd);
			}
			user = userDao.saveUser(user);
		}
		
		return user;
	}
	
	@Override
	public User addUser(String email, String password, Long level,
			String firstname, String lastname, Long honorific, String phone) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = null;
		if (email != null && password != null && level != null && firstname != null && lastname != null && honorific != null
				&& phone != null && !"".equals(email) && !"".equals(password) && !"".equals(firstname) && !"".equals(lastname)
				&& !"".equals(phone)) {
			user = new User();
			user.setEmail(email);
			user.setPassword(password);
			user.setLevel(level);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setHonorific(honorific);
			user.setPhone(phone);
			}
			user = userDao.saveUser(user);
		return user;
	}
	
	@Override
	public User updateUser(Long userId, String email, String password,
			Long level, String firstname, String lastname, Long honorific,
			String phone, String name3rd, String phone3rd) throws NoSuchAlgorithmException, InvalidKeySpecException {
	
	
	User user = userDao.getUser(userId);
	if (user != null) {
		user = new User();
		user.setId(userId);
		user.setEmail(email);
		user.setPassword(password);
		user.setLevel(level);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setHonorific(honorific);
		user.setPhone(phone);
		if (name3rd != null && phone3rd != null && !"".equals(name3rd) && !"".equals(phone3rd)) {
			user.setName_3rd(name3rd);
			user.setPhone_3rd(phone3rd);
		}
		user = userDao.saveUser(user);
	}
	else {
		user = null;
	}
	
	return user;
	}

	@Override
	public User updateUser(Long userId, String email, String password,
			Long level, String firstname, String lastname, Long honorific,
			String phone) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = userDao.getUser(userId);
		if (user != null) {
			user = new User();
			user.setId(userId);
			user.setEmail(email);
			user.setPassword(password);
			user.setLevel(level);
			user.setFirstname(firstname);
			user.setLastname(lastname);
			user.setHonorific(honorific);
			user.setPhone(phone);
			user = userDao.saveUser(user);
		}
		else {
			user = null;
		}
		
		return user;
	}

	@Override
	public void deleteUser(Long userId) {
		
		User user = userDao.getUser(userId);
		if (user != null) {
			userDao.deleteUser(userId);
		}
	}


}
