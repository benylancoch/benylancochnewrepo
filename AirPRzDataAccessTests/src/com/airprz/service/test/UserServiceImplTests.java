package com.airprz.service.test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;

import javax.naming.NamingException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.airprz.DataSourceSetupUtil;
import com.airprz.model.User;
import com.airprz.service.UserService;
import com.airprz.service.impl.UserServiceImpl;

public class UserServiceImplTests {
	
	@Before
	public void setup() throws NamingException, SQLException {
		DataSourceSetupUtil.setup();
	}
	
	@After
	public void tearDown() throws SQLException {
		DataSourceSetupUtil.tearDown();
	}
	
	@Test
	public void whenAuthenticating_givenValidEmailAndPassword_itShouldReturnUserObject() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId;
		final String validEmail = "test123456@mail.com";
		final String validPassword = "123";
		final Long validLevel = new Long(0);
		final String validFirstName = "Sebastian";
		final String validLastName = "Wcislo";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.addUser(validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd); 
		validUserId = user.getId();
		user = userService.authenticateUser(validEmail, validPassword);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(validName3rd, user.getName_3rd());
		Assert.assertEquals(validPhone3rd, user.getPhone_3rd());
	}
	
	@Test
	public void whenAuthentication_givenInvalidEmail_itShouldReturnNull() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		final String invalidEmail = "tester@invalid.com";
		final String invalidPassword = "123456";
		final UserService userService = new UserServiceImpl();
				
		//Act
		User user = userService.authenticateUser(invalidEmail, invalidPassword);
			
		//Assert
		Assert.assertNull(user);
	}
	
	@Test
	public void whenAuthentication_givenInvalidPassword_itShouldReturnNull() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		final String validEmail = "test123456@mail.com";
		final String validPassword = "123";
		final Long validLevel = new Long(0);
		final String validFirstName = "Sebastian";
		final String validLastName = "Wcislo";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final String invalidPassword = "invalid";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.addUser(validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd); 
		user = userService.authenticateUser(validEmail, invalidPassword);
			
		//Assert
		Assert.assertNull(user);
	}
	
	@Test
	public void whenAddingNewUser_givenValidData_itShouldReturnUserObject() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(2);
		final String validEmail = "some@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.addUser(validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validPassword, user.getPassword());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(validName3rd, user.getName_3rd());
		Assert.assertEquals(validPhone3rd, user.getPhone_3rd());
	}
	
	@Test
	public void whenAddingNewUser_givenValidData_NoOptionalData_itShouldReturnUserObject() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(2);
		final String validEmail = "some@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.addUser(validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validPassword, user.getPassword());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(null, user.getName_3rd());
		Assert.assertEquals(null, user.getPhone_3rd());
	}
	
	@Test
	public void whenAddingNewUser_givenUsedEmail_itShouldReturnNull() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(2);
		final String validEmail = "test@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.addUser(validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd);
		
		//Assert
		Assert.assertNull(user);
	}
	
	@Test
	public void whenUpdatingUser_givenValidData_itShouldReturnUserObject() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(1);
		final String validEmail = "some.updated@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.updateUser(validUserId, validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validPassword, user.getPassword());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(validName3rd, user.getName_3rd());
		Assert.assertEquals(validPhone3rd, user.getPhone_3rd());
	}
	
	@Test
	public void whenUpdatingUser_givenValidData_NoOptionalData_itShouldReturnUserObject() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(1);
		final String validEmail = "some.updated@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.updateUser(validUserId, validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validPassword, user.getPassword());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(null, user.getName_3rd());
		Assert.assertEquals(null, user.getPhone_3rd());
	}
	
	@Test
	public void whenUpdatingUser_givenUsedEmail_itShouldReturnNull() throws NoSuchAlgorithmException, InvalidKeySpecException {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(1);
		final String validEmail = "test@mail.com";
		final String validPassword = "1234";
		final Long validLevel = new Long(0);
		final String validFirstName = "Mateusz";
		final String validLastName = "Doe";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.updateUser(validUserId, validEmail, validPassword, validLevel, validFirstName, validLastName, validHonorific, validPhone, validName3rd, validPhone3rd);
		
		//Assert
		Assert.assertNull(user);
	}
	
	
	@Test
	public void whenSearchingExistingUser_ById_itShouldReturnObject() {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(1);
		final String validEmail = "test@mail.com";
		final Long validLevel = new Long(0);
		final String validFirstName = "Sebastian";
		final String validLastName = "Wcislo";
		final Long validHonorific = new Long(0);
		final String validPhone = "1234567890";
		final String validName3rd = "John Doe";
		final String validPhone3rd = "0987654321";
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.getUser(validUserId);
		
		//Assert
		Assert.assertNotNull(user);
		Assert.assertEquals(validUserId, user.getId());
		Assert.assertEquals(validEmail, user.getEmail());
		Assert.assertEquals(validLevel, user.getLevel());
		Assert.assertEquals(validFirstName, user.getFirstname());
		Assert.assertEquals(validLastName, user.getLastname());
		Assert.assertEquals(validHonorific, user.getHonorific());
		Assert.assertEquals(validPhone, user.getPhone());
		Assert.assertEquals(validName3rd, user.getName_3rd());
		Assert.assertEquals(validPhone3rd, user.getPhone_3rd());
	}
	
	@Test
	public void whenSearchingNonExistingUser_ById_itShouldReturnNull() {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(10);
		final UserService userService = new UserServiceImpl();
		
		//Act
		User user = userService.getUser(validUserId);
		
		//Assert
		Assert.assertNull(user);
	}
	
	@Test
	public void whendeletingUser_itShouldReturnNull() {
		//Arrange
		//INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
		//+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		
		final Long validUserId = new Long(1);
		final UserService userService = new UserServiceImpl();
		
		//Act
		userService.deleteUser(validUserId);
		User user = userService.getUser(validUserId);
		
		//Assert
		Assert.assertNull(user);
		
	}

}
