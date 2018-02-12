package br.com.kevin.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost/db_clinica?useSSL=false";
	private static final String USER = "root";
	private static final String PASSWORD = "kevi1810";

	public static Connection getConnection() {
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (ClassNotFoundException | SQLException ex) {
			throw new RuntimeException("Erro de conexão", ex);
		}
	}

	public static void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				System.err.println("Connection.close() - Erro: " + ex);
			}
		}
	}

	public static void closeConnection(Connection con, PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException ex) {
				System.err.println("PreparedStatement.close() - Erro: " + ex);
			}
		}

		closeConnection(con);
	}

	public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException ex) {
				System.err.println("ResultSet.close() - Erro: " + ex);
			}
		}

		closeConnection(con, stmt);
	}

}
