package com.airprz.data.impl;

import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.airprz.data.UserDao;
import com.airprz.data.util.DbConnector;
import com.airprz.data.util.JdbcCloses;
import com.airprz.model.User;

public class UserDaoImpl implements UserDao {
	
	@Override
	public User getUser(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		User user = null;
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT U.USER_ID, U.EMAIL, U.LEVEL, U.FIRSTNAME, U.LASTNAME, U.HONORIFIC, U.PHONE, U.NAME_3RD, U.PHONE_3RD "
					+ "FROM BAZA.USERS U "
					+ "WHERE LOWER(U.USER_ID) = ?");
			stmt.setLong(1, userId);
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new User();
				user.setId(rs.getLong("USER_ID"));
				user.setEmail(rs.getString("EMAIL"));
				user.setLevel(rs.getLong("LEVEL"));
				user.setFirstname(rs.getString("FIRSTNAME"));
				user.setLastname(rs.getString("LASTNAME"));
				user.setHonorific(rs.getLong("HONORIFIC"));
				user.setPhone(rs.getString("PHONE"));
				user.setName_3rd(rs.getString("NAME_3RD"));
				user.setPhone_3rd(rs.getString("PHONE_3RD"));
				
				user.setPassword(null);
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return user;
	}
	
	@Override
	public Long authenticateUser(String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		Long userId = new Long(0);
		String hashedPassword = new String();
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"SELECT U.USER_ID, U.PASSWORD "
					+ "FROM BAZA.USERS U "
					+ "WHERE LOWER(U.EMAIL) = ?");
			stmt.setString(1, email.toLowerCase());
			
			rs = stmt.executeQuery();
			if(rs.next()) {
				userId = rs.getLong("USER_ID");
				hashedPassword = rs.getString("PASSWORD");
			}
			else {
				userId = (long) 0;
			}
			
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		if(userId != (long) 0 && !validatePassword(password, hashedPassword)) {
			userId = (long) 0;
		}
		
			return userId;
	}

	@Override
	public User saveUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		if(user.getId() == null) {
			user = insertUser(user);
		} else {
			user = updateUser(user);
		}
		return user;
	}
	
	private User insertUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement("SELECT EMAIL FROM BAZA.USERS WHERE EMAIL = ?");
			stmt.setString(1, user.getEmail());
			
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				stmt = connection.prepareStatement(
						"INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				stmt.setString(1, user.getEmail());
				stmt.setString(2, generatePasswordHash(user.getPassword()));
				stmt.setLong(3, user.getLevel());
				stmt.setString(4, user.getFirstname());
				stmt.setString(5, user.getLastname());
				stmt.setLong(6, user.getHonorific());
				stmt.setString(7, user.getPhone());
				stmt.setString(8, user.getName_3rd());
				stmt.setString(9, user.getPhone_3rd());
				
				stmt.executeUpdate();
				stmt = connection.prepareStatement("SELECT SYSIBM.IDENTITY_VAL_LOCAL() AS last_cod FROM BAZA.USERS");
				rs = stmt.executeQuery();
				
				
				if (rs.next()) {
					user.setId(rs.getLong("last_cod"));
				}
			}
			else {
				user = null;
				
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(rs);
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return user;
		
	}
	
	private User updateUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		
		try {
			connection = DbConnector.getConnection();
			
			stmt = connection.prepareStatement("SELECT EMAIL FROM BAZA.USERS WHERE EMAIL = ?");
			stmt.setString(1, user.getEmail());
			
			rs = stmt.executeQuery();
			
			if (!rs.next()) {
				stmt = connection.prepareStatement(
						"UPDATE BAZA.USERS SET EMAIL = ?, PASSWORD = ? , LEVEL = ? , FIRSTNAME = ? , LASTNAME = ? , HONORIFIC = ? , PHONE = ? , NAME_3RD = ? , PHONE_3RD = ? WHERE USER_ID = ?");
				stmt.setString(1, user.getEmail());
				stmt.setString(2, generatePasswordHash(user.getPassword()));
				stmt.setLong(3, user.getLevel());
				stmt.setString(4, user.getFirstname());
				stmt.setString(5, user.getLastname());
				stmt.setLong(6, user.getHonorific());
				stmt.setString(7, user.getPhone());
				stmt.setString(8, user.getName_3rd());
				stmt.setString(9, user.getPhone_3rd());
				stmt.setLong(10, user.getId());
				stmt.executeUpdate();
			}
			else {
				user = null;
				
			}
			
			stmt.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
		return user;
		
	}
	
	@Override
	public void deleteUser(Long userId) {
		Connection connection = null;
		PreparedStatement stmt = null;
		
		
		try {
			connection = DbConnector.getConnection();
			stmt = connection.prepareStatement(
					"DELETE FROM BAZA.USERS WHERE USER_ID = ?");
			stmt.setLong(1, userId);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcCloses.closeIgnoreError(stmt);
			JdbcCloses.closeIgnoreError(connection);
		}
		
	}
	
	
	private static String generatePasswordHash(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		int iterations = 1000;
		char[] chars = password.toCharArray();
		byte[] salt = getSalt().getBytes();
		
		PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64*8);
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = skf.generateSecret(spec).getEncoded();
		return iterations + ":" + toHex(salt) + ":" + toHex(hash);
		
	}
	
	private static String getSalt() throws NoSuchAlgorithmException
	{
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt.toString();
	}
	
	private static String toHex(byte[] array) throws NoSuchAlgorithmException
	{
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if(paddingLength > 0)
		{
			return String.format("%0"  +paddingLength + "d", 0) + hex;
		}else{
			return hex;
		}
	}
	
	
	private static boolean validatePassword(String originalPassword, String storedPassword) throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String[] parts = storedPassword.split(":");
		int iterations = Integer.parseInt(parts[0]);
		byte[] salt = fromHex(parts[1]);
		byte[] hash = fromHex(parts[2]);
		
		PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
		
		SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] testHash = skf.generateSecret(spec).getEncoded();
		
		int diff = hash.length ^ testHash.length;
		
		for(int i = 0; i < hash.length && i < testHash.length; i++)
		{
			diff |= hash[i] ^ testHash[i];
		}
		
		return diff == 0;
	}
	
	private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
	{
		byte[] bytes = new byte[hex.length() / 2];
		for(int i = 0; i<bytes.length ;i++)
		{
			bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
		}
		return bytes;
	}
	
}
