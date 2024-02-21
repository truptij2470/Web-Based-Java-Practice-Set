package jdbcConnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButils {

	private static Connection cn;
	
 public static Connection openConnection() throws SQLException
 {
  try {
	  if(cn==null)
		  cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanager?useSSL=false&allowPublicKeyRetrieval=true","root", "Choclate#12");
} catch (SQLException e) {
	e.printStackTrace();
}
  return cn;
 }
	
 public static void closeConnection() throws SQLException
 {
	 if(cn!=null)
	 {
		 cn.close();
	 }
 }
	
	
	
}
