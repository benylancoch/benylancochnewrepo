package com.listmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.h2.jdbcx.JdbcDataSource;

import com.listmanager.data.util.DbConnector;
import com.listmanager.model.User;

public class DataSourceSetupUtil {
	private static boolean hasSetupInitialContext;
	
	public static void setup() throws NamingException, SQLException {
		if (!hasSetupInitialContext) {
			setupInitialContext();
			hasSetupInitialContext = true;
		}
		
		setupDdl();
	}
	
	private static void setupInitialContext() throws NamingException {
		System.setProperty(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		System.setProperty(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
		InitialContext ctx = new InitialContext();
		
		//java:comp/env/jdbc/AppDb
		ctx.createSubcontext("java:");
		ctx.createSubcontext("java:comp");
		ctx.createSubcontext("java:comp/env");
		ctx.createSubcontext("java:comp/env/jdbc");
		
		JdbcDataSource dataSource = new JdbcDataSource();
		dataSource.setURL("jdbc:h2:mem:~/test;MODE=DB2;DB_CLOSE_DELAY=-1");
		
		ctx.bind("java:comp/env/jdbc/AppDb", dataSource);
	}
	
	private static void setupDdl() throws SQLException {
		Connection connection = DbConnector.getConnection();
		
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("CREATE SCHEMA TEST");
		stmt.executeUpdate("CREATE TABLE TEST.APP_USER ("
				+"ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"USERNAME VARCHAR(30), "
				+"DISPLAY_NAME VARCHAR(30)"
				+")");
		//stmt.executeUpdate("ALTER TABLE TEST.APP_USER ADD CONSTRAINT APP_USER_PK PRIMARY KEY(ID)");
		stmt.executeUpdate("INSERT INTO TEST.APP_USER (USERNAME, DISPLAY_NAME) VALUES ("
				+"'test', 'Test User')");
		
		stmt.executeUpdate("CREATE TABLE TEST.LIST_ITEM ("
		+"ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
		+"VALUE VARCHAR(4000), "
		+"USER_ID INTEGER NOT NULL)");
		//stmt.executeUpdate("ALTER TABLE TEST.LIST_ITEM ADD CONSTRAINT LIST_ITEM_PK PRIMARY KEY(ID)");
		stmt.executeUpdate("ALTER TABLE TEST.LIST_ITEM ADD CONSTRAINT LIST_IT_APP_US_FK FOREIGN KEY"
				+"(USER_ID) REFERENCES TEST.APP_USER (ID) ON DELETE CASCADE");
		
		stmt.close();
		connection.close();
	}
	
	public static void tearDown() throws SQLException {
		Connection connection = DbConnector.getConnection();
		Statement stmt = connection.createStatement();
		stmt.executeUpdate("DROP TABLE TEST.LIST_ITEM");
		stmt.executeUpdate("DROP TABLE TEST.APP_USER");
		stmt.executeUpdate("DROP SCHEMA TEST");
		stmt.close();
		
		connection.close();
	}
	
	public static void insertListItemForDefaultUser(String value) throws SQLException {
		Connection connection = DbConnector.getConnection();
		PreparedStatement stmt = connection.prepareStatement("INSERT INTO TEST.LIST_ITEM (USER_ID, VALUE) VALUES (1, ?)");
		stmt.setString(1, value);
		stmt.executeUpdate();
		stmt.close();
		connection.close();
	}
	
	public static long getListItemsCount() throws SQLException {
		Connection connection = DbConnector.getConnection();
		ResultSet rs = null;
		PreparedStatement stmt = connection.prepareStatement("SELECT COUNT(*) AS LIST_ITEMS_COUNT FROM TEST.LIST_ITEM");
		
		rs = stmt.executeQuery();
		if(rs.next()) {
			return rs.getLong("LIST_ITEMS_COUNT");
		}
		else {
			return 0L;
		}
		
	}
}
