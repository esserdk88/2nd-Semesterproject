package dal;

import java.lang.module.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // Private constructor to prevent instantiation outside the class
    private DatabaseConnection() {}

    // Singleton instance variable
    private static DatabaseConnection instance;

    // Connection string for connecting to SQL Server
    private static final String CONNECTION_STRING = String.format("jdbc:sqlserver://%s:1433;encrypt=true;trustServerCertificate=true;databaseName=%s;user=%s;password=%s;",
    		other.Login.HOSTNAME,
    		other.Login.DATABASENAME,
    		other.Login.USERNAME,
    		other.Login.PASSWORD);
    // Method for getting the singleton instance of the class
    public static  DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method for getting the database connection
    public Connection getConnection() throws SQLException {
    	Connection connection;
        	connection = DriverManager.getConnection(CONNECTION_STRING);
        return connection;
    }

}
