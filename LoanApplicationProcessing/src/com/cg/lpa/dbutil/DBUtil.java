package com.cg.lpa.dbutil;

/**
 * @author rabbhi
 *
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	static Connection conn = null;

	// Returns connection object;
	public static Connection establishConnection() throws SQLException {

		conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@10.219.34.3:1521:orcl", "trg224",
				"training224");
		return conn;
	}

}
