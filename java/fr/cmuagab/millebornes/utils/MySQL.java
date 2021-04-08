package fr.cmuagab.millebornes.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MySQL {
	
	private static String url = "jdbc:mysql://";
	private static String host;
	private static String base;
	private static String username;
	private static String password;
	private static Connection connection;

	public static Connection getConnection() {
		return connection;
	}

	public static boolean isConnected() {
		try {
			if ((connection == null) || (connection.isClosed()))
				return false;
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void reconnect() {
		if (!isConnected()) connect();
	}
	
	public static boolean connect() {
		if (!isConnected()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection = DriverManager.getConnection(url + host + base, username, password);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public static void disconnect() {
		if (isConnected())
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public static void prepareStatement(String statement) {
		MySQL.connect();
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement(statement);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			MySQL.disconnect();
		}
	}
	
	public static boolean contains(String table, String column, String arg, boolean connect, boolean disconnect) {
		if(connect) {
			MySQL.connect();
		}
		try {
			PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT * FROM "+table+" WHERE "+column+" = ?");
			ps.setString(1, arg);
			return ps.executeQuery().next();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(disconnect) {
				MySQL.disconnect();
			}
		}
		return false;
	}

	public static String getHost() {
		return host;
	}

	public static void setHost(String host) {
		MySQL.host = host;
	}

	public static String getBase() {
		return base;
	}

	public static void setBase(String base) {
		MySQL.base = base;
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		MySQL.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		MySQL.password = password;
	}
	
}
