package dao;

import java.sql.*;
import static utils.DBUtils.*;

public class BankAccountDaoImpl implements BankAccountDao {
    // Database connection and callable statement
    private Connection connection;
    private CallableStatement callableStatement;

    // Default constructor
    public BankAccountDaoImpl() throws SQLException {
        // Establish database connection and prepare callable statement
        connection = openConnection();
        callableStatement = connection.prepareCall("{call transfer_funds_proc(?,?,?,?,?)}");
        // Register OUT parameters to specify JDBC data types
        callableStatement.registerOutParameter(4, Types.DOUBLE);
        callableStatement.registerOutParameter(5, Types.DOUBLE);
        System.out.println("Bank account DAO created...");
    }

    // Method to transfer funds between accounts
    @Override
    public String transferFunds(int srcAcNo, int destAcNo, double amount) throws SQLException {
        // Set IN parameters
        callableStatement.setInt(1, srcAcNo);
        callableStatement.setInt(2, destAcNo);
        callableStatement.setDouble(3, amount);
        // Execute the procedure
        callableStatement.execute();
        // Return updated balance information
        return "Updated source balance: " + callableStatement.getDouble(4) +
                ", destination balance: " + callableStatement.getDouble(5);
    }

    // Clean up resources
    public void cleanUp() throws SQLException {
        if (callableStatement != null)
            callableStatement.close();
        closeConnection();
        System.out.println("Bank account DAO cleaned up!");
    }
}
