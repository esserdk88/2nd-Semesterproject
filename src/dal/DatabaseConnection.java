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
    private static final String CONNECTION_STRING = String.format("jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;databaseName=%s;user=%s;password=%s;",
    		other.Login.SQL_DATABASE,
    		other.Login.SQL_USERNAME,
    		other.Login.SQL_PASSWORD);
    
    private String url = String.format("jdbc:sqlserver://localhost:1433;databaseName=%s;encrypt=true;trustServerCertificate=true;",other.Login.SQL_DATABASE);

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
            connection = DriverManager.getConnection(url,other.Login.SQL_USERNAME,other.Login.SQL_PASSWORD);
//        	connection = DriverManager.getConnection(CONNECTION_STRING);
        return connection;
    }

}
