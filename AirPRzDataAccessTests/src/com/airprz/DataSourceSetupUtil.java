package com.airprz;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.h2.jdbcx.JdbcDataSource;

import com.airprz.data.util.DbConnector;

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
		stmt.executeUpdate("CREATE SCHEMA BAZA");
		
		//----------------AIRPORTS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.AIRPORTS ("
				+"AIRPORT_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"NAME VARCHAR(255) NOT NULL, "
				+"CITY VARCHAR(255) NOT NULL, "
				+"COUNTRY VARCHAR(255) NOT NULL, "
				+"ADDRESS VARCHAR(255) NOT NULL, "
				+"LOCATION VARCHAR(19))");

		//----------------AIRPORTS-------------------------------------------
		
		//----------------FLIGHTS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.FLIGHTS ("
				+"FLIGHT_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"FLIGHT_NO VARCHAR(6) NOT NULL, "
				+"STARTS TIMESTAMP NOT NULL, "
				+"ENDS TIMESTAMP NOT NULL, "
				+"BASE_PRICE DECIMAL(5 , 2) NOT NULL, "
				+"DEPARTURE_PLACE INTEGER NOT NULL, "
				+"ARRIVAL_PLACE INTEGER NOT NULL, "
				+"PLANE_NO VARCHAR(10) NOT NULL)");

		//----------------FLIGHTS-------------------------------------------
		
		//----------------FLIGHTS_SEATS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.FLIGHTS_SEATS ("
				+"FS_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"CLASS INTEGER NOT NULL, "
				+"SEAT_NO INTEGER NOT NULL, "
				+"FREE VARCHAR(1) NOT NULL, "
				+"WHERE_LOC VARCHAR(10) NOT NULL, "
				+"PLANE_NO VARCHAR(10) NOT NULL, "
				+"FLIGHT_ID INTEGER NOT NULL)");

		//----------------FLIGHTS_SEATS-------------------------------------------

		//----------------PLANES-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.PLANES ("
				+"PLANE_NO VARCHAR(10) NOT NULL, "
				+"MANUFACTURER VARCHAR(255) NOT NULL, "
				+"MODEL VARCHAR(255) NOT NULL, "
				+"CLASSES INTEGER NOT NULL, "
				+"SEATS INTEGER NOT NULL)");

		//----------------PLANES-------------------------------------------
		
		//----------------PROMO_CODES-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.PROMO_CODES ("
				+"CODE_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"CODE VARCHAR(10) NOT NULL, "
				+"DESCRIPTION VARCHAR(255) NOT NULL, "
				+"DISCOUNT DECIMAL(5 , 2) NOT NULL, "
				+"MULTIPLE VARCHAR(1) NOT NULL, "
				+"USED VARCHAR(1) NOT NULL, "
				+"VALID_FROM TIMESTAMP NOT NULL, "
				+"VALID_TO TIMESTAMP)");

		//----------------PROMO_CODES-------------------------------------------

		//----------------TAXES-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.TAXES ("
				+"TAX_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"VALUE DECIMAL(5 , 2) NOT NULL, "
				+"DESCRIPTION VARCHAR(255) NOT NULL, "
				+"VALID_FROM TIMESTAMP NOT NULL, "
				+"VALID_TO TIMESTAMP)");

		//----------------TAX-------------------------------------------
		
		//----------------TICKETS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.TICKETS ("
				+"TICKET_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"PRICE DECIMAL(5,2) NOT NULL, "
				+"CHECKED TIMESTAMP NOT NULL, "
				+"USER_ID INTEGER NOT NULL, "
				+"FLIGHT_ID INTEGER NOT NULL," 
				+"FS_ID INTEGER NOT NULL, "
				+"NEXT_FLIGHT INTEGER, "
				+"TRANSACTION_ID INTEGER NOT NULL)");

		//----------------TICKETS-------------------------------------------

		//----------------TRANSACTIONS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.TRANSACTIONS ("
				+"TRANSACTION_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"DATE TIMESTAMP NOT NULL, "
				+"PAID_USING VARCHAR(2) NOT NULL, "
				+"PAID_DATE TIMESTAMP, "
				+"TAX_ID INTEGER NOT NULL)");
		
		//----------------TRANSACTIONS-------------------------------------------
		
		//----------------USERS-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.USERS ("
				+"USER_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"EMAIL VARCHAR(70) NOT NULL, "
				+"PASSWORD VARCHAR(255) NOT NULL, "
				+"LEVEL INTEGER NOT NULL, "
				+"FIRSTNAME VARCHAR(255) NOT NULL, "
				+"LASTNAME VARCHAR(255) NOT NULL, "
				+"HONORIFIC INTEGER NOT NULL, "
				+"PHONE VARCHAR(20) NOT NULL, "
				+"NAME_3RD VARCHAR(255), "
				+"PHONE_3RD VARCHAR(20))");

		//----------------USERS-------------------------------------------
		
		//----------------USER_PROMOTIONAL_CODES-------------------------------------------
		stmt.executeUpdate("CREATE TABLE BAZA.USR_PCODES ("
				+"UPC_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY , "
				+"USER_ID INTEGER NOT NULL, "
				+"CODE_ID INTEGER NOT NULL)");
		//----------------USER_PROMOTIONAL_CODES-------------------------------------------
		
		
		//---------------------------------CONSTRAINTS-------------------------------------
		stmt.executeUpdate("ALTER TABLE BAZA.USERS add unique (EMAIL)");
		
		stmt.executeUpdate("ALTER TABLE BAZA.FLIGHTS ADD CONSTRAINT arrival_place_FK FOREIGN KEY"
				+"(ARRIVAL_PLACE) REFERENCES BAZA.AIRPORTS (AIRPORT_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.FLIGHTS ADD CONSTRAINT departure_place_FK FOREIGN KEY"
				+"(DEPARTURE_PLACE) REFERENCES BAZA.AIRPORTS (AIRPORT_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.FLIGHTS ADD CONSTRAINT flights_planes_FK FOREIGN KEY"
				+"(PLANE_NO) REFERENCES BAZA.PLANES (PLANE_NO) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.FLIGHTS_SEATS ADD CONSTRAINT flights_seats_flights_FK FOREIGN KEY"
				+"(FLIGHT_ID) REFERENCES BAZA.FLIGHTS (FLIGHT_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.FLIGHTS_SEATS ADD CONSTRAINT flights_seats_planes_FK FOREIGN KEY"
				+"(PLANE_NO) REFERENCES BAZA.PLANES (PLANE_NO) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TICKETS ADD CONSTRAINT TICKETS_TICKETS_FK FOREIGN KEY"
				+"(NEXT_FLIGHT) REFERENCES BAZA.TICKETS (TICKET_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TICKETS ADD CONSTRAINT TICKETS_TRANSACTIONS_FK FOREIGN KEY"
				+"(TRANSACTION_ID) REFERENCES BAZA.TRANSACTIONS (TRANSACTION_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TICKETS ADD CONSTRAINT flights_users_flights_FK FOREIGN KEY"
				+"(FLIGHT_ID) REFERENCES BAZA.FLIGHTS (FLIGHT_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TICKETS ADD CONSTRAINT flights_users_flights_seats_FK FOREIGN KEY"
				+"(FS_ID) REFERENCES BAZA.FLIGHTS_SEATS (FS_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TICKETS ADD CONSTRAINT flights_users_users_FK FOREIGN KEY"
				+"(USER_ID) REFERENCES BAZA.USERS (USER_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.TRANSACTIONS ADD CONSTRAINT TRANSACTIONS_TAX_FK FOREIGN KEY"
				+"(TAX_ID) REFERENCES BAZA.TAXES (TAX_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.USR_PCODES ADD CONSTRAINT USR_PCODES_PROMO_CODES_FK FOREIGN KEY"
				+"(CODE_ID) REFERENCES BAZA.PROMO_CODES (CODE_ID) ON DELETE CASCADE");
		
		stmt.executeUpdate("ALTER TABLE BAZA.USR_PCODES ADD CONSTRAINT USR_PCODES_USERS_FK FOREIGN KEY"
				+"(USER_ID) REFERENCES BAZA.USERS (USER_ID) ON DELETE CASCADE");
		//---------------------------------CONSTRAINTS-------------------------------------
		
		//---------------------------------DATA-ADDITION-----------------------------------
		
		//Airports
		stmt.executeUpdate("INSERT INTO BAZA.AIRPORTS (NAME, CITY, COUNTRY, ADDRESS) VALUES ("
				+"'KRAKOW INTERNATIONAL AIRPORT', 'KRAKOW', 'POLAND', 'SOME STREET 2')");
		stmt.executeUpdate("INSERT INTO BAZA.AIRPORTS (NAME, CITY, COUNTRY, ADDRESS) VALUES ("
				+"'RZESZOW INTERNATIONAL AIRPORT', 'RZESZOW', 'POLAND', 'SOME STREET 2')");
		//Airports
		
		//Planes
		stmt.executeUpdate("INSERT INTO BAZA.PLANES (PLANE_NO, MANUFACTURER, MODEL, CLASSES, SEATS) VALUES ("
				+"'AIR001', 'AIRBUS', 'A380', 3, 100)");
		stmt.executeUpdate("INSERT INTO BAZA.PLANES (PLANE_NO, MANUFACTURER, MODEL, CLASSES, SEATS) VALUES ("
				+"'AIR002', 'AIRBUS', 'A380', 3, 200)");
		//Planes
		
		//PromoCodes
		stmt.executeUpdate("INSERT INTO BAZA.PROMO_CODES (CODE, DESCRIPTION, DISCOUNT, MULTIPLE, USED, VALID_FROM) VALUES ("
				+"'ABC', 'ABC DESCRIPTION', 0.25, 'N', 'N', parseDateTime('2016-01-01-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		stmt.executeUpdate("INSERT INTO BAZA.PROMO_CODES (CODE, DESCRIPTION, DISCOUNT, MULTIPLE, USED, VALID_FROM) VALUES ("
				+"'DFGH', 'DFGH DESCRIPTION', 0.25, 'N', 'N', parseDateTime('2016-01-02-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		stmt.executeUpdate("INSERT INTO BAZA.PROMO_CODES (CODE, DESCRIPTION, DISCOUNT, MULTIPLE, USED, VALID_FROM) VALUES ("
				+"'123', '123 DESCRIPTION', 0.25, 'N', 'U', parseDateTime('2016-01-02-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		stmt.executeUpdate("INSERT INTO BAZA.PROMO_CODES (CODE, DESCRIPTION, DISCOUNT, MULTIPLE, USED, VALID_FROM, VALID_TO) VALUES ("
				+"'456', '456 DESCRIPTION', 0.25, 'N', 'N', parseDateTime('2016-01-02-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), parseDateTime('2016-01-03-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		//PromoCodes
		
		//Taxes
		stmt.executeUpdate("INSERT INTO BAZA.TAXES (VALUE, DESCRIPTION, VALID_FROM) VALUES ("
				+"TRUNCATE_VALUE(0.23, 2, TRUE), 'POLISH VAT 23%', parseDateTime('2016-01-01-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		stmt.executeUpdate("INSERT INTO BAZA.TAXES (VALUE, DESCRIPTION, VALID_FROM) VALUES ("
				+"TRUNCATE_VALUE(0.09, 2, TRUE), 'POLISH VAT 9%', parseDateTime('2016-02-02-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		stmt.executeUpdate("INSERT INTO BAZA.TAXES (VALUE, DESCRIPTION, VALID_FROM, VALID_TO) VALUES ("
				+"TRUNCATE_VALUE(0.09, 2, TRUE), 'POLISH VAT 9%', parseDateTime('2016-02-02-00.00.00', 'yyyy-MM-dd-hh.mm.ss'), "
				+"parseDateTime('2016-02-03-00.00.00', 'yyyy-MM-dd-hh.mm.ss'))");
		//Taxes
		
		//Users
		stmt.executeUpdate("INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
				+"'test@mail.com', '123', 0, 'Sebastian', 'Wcislo', '0', '1234567890', 'John Doe', '0987654321')");
		stmt.executeUpdate("INSERT INTO BAZA.USERS (EMAIL, PASSWORD, LEVEL, FIRSTNAME, LASTNAME, HONORIFIC, PHONE, NAME_3RD, PHONE_3RD) VALUES ("
				+"'test2@mail.com', '1233', 0, 'Sebastian2', 'Wcislo2', '0', '1234567890', 'John Doe2', '0987654321')");
		//Users
		
		//UserPromoCode
		stmt.executeUpdate("INSERT INTO BAZA.USR_PCODES (USER_ID, CODE_ID) VALUES ("
				+"1, 1)");
		stmt.executeUpdate("INSERT INTO BAZA.USR_PCODES (USER_ID, CODE_ID) VALUES ("
				+"1, 2)");
		stmt.executeUpdate("INSERT INTO BAZA.USR_PCODES (USER_ID, CODE_ID) VALUES ("
				+"2, 1)");
		stmt.executeUpdate("INSERT INTO BAZA.USR_PCODES (USER_ID, CODE_ID) VALUES ("
				+"2, 3)");
		//UserPromoCode
		
		//Flight
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS (FLIGHT_NO, STARTS, ENDS, BASE_PRICE, DEPARTURE_PLACE, ARRIVAL_PLACE, PLANE_NO) VALUES ("
				+"'APRZ85', parseDateTime('2016-04-01-21.00.00', 'yyyy-MM-dd-hh.mm.ss'), parseDateTime('2016-04-02-02.00.00', 'yyyy-MM-dd-hh.mm.ss'), "
				+ "250.29, 1, 2, 'AIR001')");
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS (FLIGHT_NO, STARTS, ENDS, BASE_PRICE, DEPARTURE_PLACE, ARRIVAL_PLACE, PLANE_NO) VALUES ("
				+"'APRZ95', parseDateTime('2016-05-01-21.00.00', 'yyyy-MM-dd-hh.mm.ss'), parseDateTime('2016-05-02-02.00.00', 'yyyy-MM-dd-hh.mm.ss'), "
				+ "250.29, 1, 2, 'AIR001')");
		//Flight
		
		//FlightSeat
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES ("
				+"1, 101, 'Y','WF', 'AIR001', 1)");
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES ("
				+"1, 102, 'Y','WF', 'AIR001', 1)");
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES ("
				+"2, 201, 'Y','WF', 'AIR001', 1)");
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES ("
				+"1, 103, 'N','WF', 'AIR001', 1)");
		stmt.executeUpdate("INSERT INTO BAZA.FLIGHTS_SEATS (CLASS, SEAT_NO, FREE, WHERE_LOC, PLANE_NO, FLIGHT_ID) VALUES ("
				+"1, 101, 'Y','WF', 'AIR001', 2)");
		//FlightSeat
		//---------------------------------DATA-ADDITION-----------------------------------
		
		stmt.close();
		connection.close();
	}
	
	public static void tearDown() throws SQLException {
		Connection connection = DbConnector.getConnection();
		Statement stmt = connection.createStatement();
		
		stmt.executeUpdate("DROP TABLE BAZA.AIRPORTS");

		stmt.executeUpdate("DROP TABLE BAZA.FLIGHTS");

		stmt.executeUpdate("DROP TABLE BAZA.FLIGHTS_SEATS");

		stmt.executeUpdate("DROP TABLE BAZA.PLANES");

		stmt.executeUpdate("DROP TABLE BAZA.PROMO_CODES");

		stmt.executeUpdate("DROP TABLE BAZA.TAXES");

		stmt.executeUpdate("DROP TABLE BAZA.TICKETS");

		stmt.executeUpdate("DROP TABLE BAZA.TRANSACTIONS");

		stmt.executeUpdate("DROP TABLE BAZA.USERS");

		stmt.executeUpdate("DROP TABLE BAZA.USR_PCODES");

		stmt.executeUpdate("DROP SCHEMA BAZA");

		stmt.close();
		
		connection.close();
	}
	
	/*public static void insertListItemForDefaultUser(String value) throws SQLException {
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
		
	}*/
}
