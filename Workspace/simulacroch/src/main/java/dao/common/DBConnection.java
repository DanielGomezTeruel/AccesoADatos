package dao.common;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import common.Configuration;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Inject;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *  @description Class for creating a connection to a DB using DriverManager class.
 *  Reads the information from a property file (mysql-properties.xml)
 */


public class DBConnection {

	private final Configuration config;
	private final DataSource hikariDataSource;

	@Inject
	public DBConnection(Configuration config) {
		this.config = config;
		hikariDataSource = getHikariPool();
	}

	private DataSource getHikariPool() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(config.getProperty("urlDB"));
		hikariConfig.setUsername(config.getProperty("user_name"));
		hikariConfig.setPassword(config.getProperty("password"));
		hikariConfig.setMaximumPoolSize(4);

		hikariConfig.addDataSourceProperty("cachePrepStmts", true);
		hikariConfig.addDataSourceProperty("prepStmtCacheSize", 250);
		hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);

		return new HikariDataSource(hikariConfig);
	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = hikariDataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@PreDestroy
	public void closePool() {
		((HikariDataSource) hikariDataSource).close();
	}
}

//	private Configuration config;
//
//	/**
//	 * Opens Database connection
//	 */
//	@Inject
//	public DBConnection(Configuration config) {
//		this.config = config;
//	}
//
//	public Connection getConnection() throws SQLException {
//
//		Connection conn = DriverManager
//				.getConnection(config.getProperty("urlDB"), config.getProperty("user_name"), config.getProperty("password"));
//                System.out.println("Connected to DB");
//		return conn;
//	}
//
//	/**
//	 * Closes connection
//	 */
//	public void closeConnection(Connection connArg) {
//		System.out.println("Releasing all open resources ...");
//		try {
//			if (connArg != null) {
//				connArg.close();
//			}
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//	}
//
//	public void releaseResource(PreparedStatement pstmt) {
//		try {
//			if (pstmt != null) {
//				pstmt.close();
//			}
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//	}
//
//        public void releaseResource(ResultSet rs) {
//		try {
//			if (rs != null) {
//				rs.close();
//			}
//
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//	}
//
//        public void releaseResource(Statement stmt) {
//		try {
//			if (stmt != null) {
//				stmt.close();
//			}
//		} catch (SQLException sqle) {
//			sqle.printStackTrace();
//		}
//	}
//}
