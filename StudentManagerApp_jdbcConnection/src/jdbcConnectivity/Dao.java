package jdbcConnectivity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.BooleanSupplier;

import static jdbcConnectivity.DButils.openConnection;

public class Dao {
	private static Connection con;
private static PreparedStatement pst1;
    public static boolean insertToDb(Student std) throws SQLException {
        boolean f=false;
        try {
             con = DButils.openConnection();
            String query = "insert into students(name, phoneno, city) values(?,?,?)";
             pst1 = con.prepareStatement(query);
            
            // Set the values of the parameters
            pst1.setString(1, std.getName());
            pst1.setString(2, std.getPhone());
            pst1.setString(3, std.getCity()); // Fix index for city
            pst1.executeUpdate();
            f = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }
	public static boolean deleteStudent(int studentId) {
		
		boolean f=false;
        try {
            con=DButils.openConnection();
            String query1="delete from students where id=?";
            PreparedStatement pst2=con.prepareStatement(query1);
            pst2.setInt(1,studentId);
            pst2.executeUpdate();
            
            f = true;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
	}
	public static void showAllStudents() {
      
		
		try {
			con=DButils.openConnection();
			String query3="select * from students";
		java.sql.Statement pst3=con.createStatement();
	   ResultSet set=pst3.executeQuery(query3);
			
			while(set.next())
			{
				int id=set.getInt(1);
				String name=set.getString(2);
				String phone=set.getString(3);
				String cityString=set.getString(4);//or("city")we can also give column name 
				System.out.println("-----------------------------------------------------");
				System.out.println("id : "+id);
				System.out.println("Name : "+name);
				System.out.println("Phone : "+phone);
				System.out.println("City : "+cityString);
				System.out.println("-----------------------------------------------------");

			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static Boolean UpdateName(int stuId,String name) {
		boolean f=false;
      try {
		con=DButils.openConnection();
		String query4="update students set name = ? where id = ?";
		PreparedStatement pst4=con.prepareStatement(query4);
		pst4.setString(1, name);
		pst4.setInt(2, stuId);
		pst4.execute();
		f=true;
		
		
	} catch (SQLException e) {
		e.printStackTrace();
	}
		
		return f;
		
	}
	
	
	
	
	
	
		
}
