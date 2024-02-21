package tester;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import dao.UserDaoImpl;
import pojos.User;

public class RegisterNewVoter {

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			UserDaoImpl dao = new UserDaoImpl();
			
			// Register a new voter
			System.out.println("Enter firstName, lastName, email, password, dob(yr-mon-day)");
			String firstName = sc.next();
			String lastName = sc.next();
			String email = sc.next();
			String password = sc.next();
			Date dob = Date.valueOf(sc.next());
			String mesg = dao.registerNewVoter(new User(firstName, lastName, email, password, dob));
			System.out.println(mesg);
			
			// Get selected users
			System.out.println("Enter role, begin date (yr-mon-day), end date (yr-mon-day)");
			String role = sc.next();
			Date begin = Date.valueOf(sc.next());
			Date end = Date.valueOf(sc.next());
			List<User> users = dao.getSelectedUsers(role, begin, end);
			System.out.println("Selected Users:");
			for (User user : users) {
				System.out.println(user);
			}
			
			// Delete user details
			System.out.println("Enter user ID to delete:");
			int userId = sc.nextInt();
			mesg = dao.deleteUserDetails(userId);
			System.out.println(mesg);
			
			dao.cleanUp();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
