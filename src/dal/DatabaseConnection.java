package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    
    // Singleton instance variable
    private static DatabaseConnection instance;
	private static Connection connection;

    // Connection string for connecting to SQL Server
    private static final String CONNECTION_STRING = String.format("jdbc:sqlserver://%s:1433;encrypt=true;trustServerCertificate=true;databaseName=%s;user=%s;password=%s;",
    		other.Login.HOSTNAME,
    		other.Login.DATABASENAME,
    		other.Login.USERNAME,
    		other.Login.PASSWORD);
    
    // Private constructor to prevent instantiation outside the class
    private DatabaseConnection() {
		resetConnection();
    }
     
    // Method for getting the singleton instance of the class
    public synchronized static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    // Method for getting the database connection
    public synchronized Connection getConnection(){
    	try {
			if(connection.isClosed()) {
				System.out.println("resetting connection");
				resetConnection();
			}
		} catch (SQLException e) {}
        return connection;
    }
    
    public void startTransaction() throws SQLException{
			connection.setAutoCommit(false);
	}

	public void commitTransaction() throws SQLException{
			try {
				connection.commit();
			} catch (SQLException e) {} finally {
				connection.setAutoCommit(true);
			}
	}

	public void rollbackTransaction() throws SQLException{
			try {
				connection.rollback();
			} catch (SQLException e) {} finally {
				connection.setAutoCommit(true);
			}
	}
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void resetConnection() {
	    try {
	        // Close the old connection if it's not already closed
	        if (connection != null && !connection.isClosed()) {
	            connection.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    DriverManager.setLoginTimeout(5);
	    try {
	        connection = DriverManager.getConnection(CONNECTION_STRING);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

    public boolean isConnected() {
		boolean isOpen = false;
		try {
			isOpen = (connection.isValid(2));
		} catch (Exception sclExc) {
			isOpen = false;
		}
		return isOpen;
	}

}
