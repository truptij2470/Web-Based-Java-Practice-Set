package jdbcConnectivity;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.function.BooleanSupplier;



public class MainTester {
	public static void main(String[] args) throws SQLException {
		System.out.println("Welcome to student manager application");
		try(Scanner sc=new Scanner(System.in)){
		  while(true) 
		  {
			System.out.println("1.Add Student");
			System.out.println("2.Delete a student info");
			System.out.println("3.Display Student");
			System.out.println("4.Update Name of the student");
			System.out.println("5.Exit");
			int choice=sc.nextInt();
			if(choice==1)
			{
				System.out.println("enter the name of the student  : ");
				String stuName=sc.next();
				System.out.println("enter the mobile number of the student :");
				String mobString=sc.next();
				System.out.println("enter the city:");
				String cityString=sc.next();
				Student student=new Student(stuName,mobString,cityString);
				boolean answer=Dao.insertToDb(student);
				if(answer==true)
				{
					System.out.println("Student is added Successfully..!");
				}else 
				{
					System.out.println("something went wrong....!");
					
				}
				
				
			}
			
			else if(choice==2) 
			{
				System.out.println("enter student id ");
				int studentId=sc.nextInt();
			boolean ans=Dao.deleteStudent(studentId);
			if(ans==true) 
			{
				System.out.println("the student with is "+studentId+" is deleted");
			}else
			{
				System.out.println("something went wrong..!");
			}
				
				
				
			}
			else if (choice==3)
			{ 
				Dao.showAllStudents();
			}else if(choice==4) {
				System.out.println("enter the student id : ");
				int stuId=sc.nextInt();
				System.out.println("enter the name : ");
				String nameString=sc.next();
				boolean ans2=Dao.UpdateName(stuId,nameString);
				   if(ans2==true) {
					System.out.println("name updated.!");
				   }else {
					System.out.println("something went wrong....!");
				}
			}
			else if(choice==5) 
			{
				break;
			}
			else {
				
			}
			
			
			
			
			
			
			
			
			
			  }
			
		  }
			}

}
