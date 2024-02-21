package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static utils.DBUtils.*;

import pojos.User;

public class UserDaoImpl implements UserDao {
    // Database connection and prepared statements
    private Connection connection;
    private PreparedStatement selectUsersStmt, insertUserStmt, deleteUserStmt;

    // Default constructor
    public UserDaoImpl() throws SQLException {
        // Establish database connection and prepare statements
        connection = openConnection();
        selectUsersStmt = connection.prepareStatement(
            "SELECT first_name, last_name, dob, status FROM users WHERE role=? AND dob BETWEEN ? AND ?");
        insertUserStmt = connection.prepareStatement(
            "INSERT INTO users VALUES (DEFAULT,?,?,?,?,?,?,?)");
        deleteUserStmt = connection.prepareStatement(
            "DELETE FROM users WHERE id=?");
        System.out.println("User DAO created!");
    }

    // Get selected users based on role and date range
    @Override
    public List<User> getSelectedUsers(String role, Date begin, Date end) throws SQLException {
        // Set IN parameters
        selectUsersStmt.setString(1, role);
        selectUsersStmt.setDate(2, begin);
        selectUsersStmt.setDate(3, end);
        // Execute query and populate list of users
        List<User> users = new ArrayList<>();
        try (ResultSet rst = selectUsersStmt.executeQuery()) {
            while (rst.next())
                users.add(new User(rst.getString(1), rst.getString(2), rst.getDate(3), rst.getBoolean(4)));
        }
        return users;
    }

    // Register a new voter
    @Override
    public String registerNewVoter(User newVoter) throws SQLException {
        // Set IN parameters
        insertUserStmt.setString(1, newVoter.getFirstName());
        insertUserStmt.setString(2, newVoter.getLastName());
        insertUserStmt.setString(3, newVoter.getEmail());
        insertUserStmt.setString(4, newVoter.getPassword());
        insertUserStmt.setDate(5, newVoter.getDob());
        insertUserStmt.setBoolean(6, false);
        insertUserStmt.setString(7, "voter");
        // Execute update and return message
        int rowCount = insertUserStmt.executeUpdate();
        if (rowCount == 1)
            return "Voter registered successfully";
        return "Failed to register voter";
    }
    
    // Delete user details by user ID
    @Override
    public String deleteUserDetails(int userId) throws SQLException {
        // Set IN parameter
        deleteUserStmt.setInt(1, userId);
        int rowCount = deleteUserStmt.executeUpdate();
        if (rowCount == 1)
            return "User deleted successfully";
        return "Failed to delete user";
    }

    // Clean up resources
    public void cleanUp() throws SQLException {
        if (selectUsersStmt != null)
            selectUsersStmt.close();
        if (insertUserStmt != null)
            insertUserStmt.close();
        if (deleteUserStmt != null)
            deleteUserStmt.close();
        closeConnection();
        System.out.println("User DAO cleaned up!");
    }

}
