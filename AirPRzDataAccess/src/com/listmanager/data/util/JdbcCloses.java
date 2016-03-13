package com.listmanager.data.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCloses {
	
	public static void closeIgnoreError(ResultSet rs) {
		try {
			if (rs != null && !rs.isClosed()) {
				rs.close();
			}
		} catch (SQLException se) {
			//ignore
		}
	}
	
	public static void closeIgnoreError(PreparedStatement stmt) {
		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException se) {
			//ignore
		}
	}
	
	public static void closeIgnoreError(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException se) {
			//ignore
		}
	}

}
