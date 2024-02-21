package utils;

import java.sql.*;

public class DBUtils {
	private static Connection cn;

	public static Connection openConnection() throws  SQLException {
		// optional load JDBC driver class
	//	Class.forName("com.mysql.cj.jdbc.Driver");
		cn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/advjava?useSSL=false&allowPublicKeyRetrieval=true", "root", "root");
		return cn;
	}
	public static void closeConnection() throws SQLException
	{
		if(cn != null)
			cn.close();
	}

}
