package it.unirc.db.ecommerce.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	private static Connection conn = null;

//	private static final String DbDriver = "com.mysql.cj.jdbc.Driver"; // in base alla versione com.mysql.cj.jdbc.Driver
//	private static final String DbURL = "jdbc:mysql://localhost:3306/progetto_e-commerce?useSSL=false&serverTimezone=UTC";
//	private static final String username = "root";
//	private static final String password = "root";
	
	private static final String DbDriver = "oracle.jdbc.driver.OracleDriver";
	private static final String DbURL = "jdbc:oracle:thin:@localhost:1521:GlobalDB";
	private static final String username = "ADMIN_ECOMMERCE";
	private static final String password = "ecommerce";

	private DBManager() {
	}

	/**
	 * Metododo che restituisce true se la connessione è aperta.
	 */
	public static boolean isOpen() {
		// if (conn == null)
		// return false;
		// else
		// return true;
		return (conn != null);
	}

	public static Connection startConnection() {
		if (isOpen())
			return conn;
		try {
			Class.forName(DbDriver);// Carica il Driver del DBMS
			conn = DriverManager.getConnection(DbURL, username, password);// Apertura connessione
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public static boolean closeConnection() {
		if (!isOpen())
			return true;
		try {
			conn.close();
			conn = null;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
