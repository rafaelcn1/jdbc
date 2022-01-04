package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

	private static Connection conn = null;

	public static Connection getConnection() {
		try {
			if (conn == null) {
				Properties loadProperties = loadProperties();
				String url = loadProperties.getProperty("dburl");
				conn = DriverManager.getConnection(url, loadProperties);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
		System.out.println("Conectado....");
		return conn;
	}

	public static void closeConnection(Connection conn, Statement st, ResultSet rs) {

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}
		System.out.println("Desconectado!");

	}

	public static void closeConnection(Connection conn, PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void closeConnection(PreparedStatement ps) {

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void closeConnection(ResultSet rs, PreparedStatement ps) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}

		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}

		System.out.println("Desconectado!");

	}

	public static void closeConnection() {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				throw new DbException(e.getMessage());
			}
		}
		System.out.println("Desconectado!");

	}

	private static Properties loadProperties() {
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties properties = new Properties();
			properties.load(fs);
			return properties;
		} catch (IOException e) {
			// TODO: handle exception
			throw new DbException(e.getMessage());
		}
	}
}
