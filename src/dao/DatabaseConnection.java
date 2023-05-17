package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import other.Login;

public class DatabaseConnection {

	// Singleton instance variable
	private static DatabaseConnection instance;
	private static Connection connection;
	private static boolean testingEnvironment;

	// Connection string for connecting to SQL Server
	private String getConnectionString() {
	    return String.format(
	        "jdbc:sqlserver://%s:1433;encrypt=true;trustServerCertificate=true;databaseName=%s;user=%s;password=%s;",
	        Login.HOSTNAME,
	        testingEnvironment ? Login.TEST_DATABASE_NAME : Login.DATABASE_NAME,
	        testingEnvironment ? Login.TEST_USERNAME : Login.USERNAME,
	        Login.PASSWORD
	    );
	}
	
	
	/**
	 * Private constructor for the singleton pattern
	 * Sets a connection to the database
	 */
	private DatabaseConnection() {
		resetConnection();
	}

	
	/**
	 * The static Instance for the DatabaseConnection
	 * Used to access methods within the Singleton class
	 * @return
	 */
	public synchronized static DatabaseConnection getInstance() {
		if (instance == null) {
			instance = new DatabaseConnection();
		}
		return instance;
	}
	/**
	 * FOR TESTING ONLY
	 * The static instance for the DatabaseConnection
	 * Used to access methods within the Singleton class when testing
	 * Sets Database to testing database and commit is disabled
	 * @return The Instance
	 * @throws SQLException
	 */
	public synchronized static DatabaseConnection setTestingEnvironment() throws SQLException {
		if (instance == null) {
			testingEnvironment = true;
			instance = new DatabaseConnection();
			connection.setAutoCommit(false);
		}
		return instance;
	}

	
	/**
	 * Get the current connection to the database
	 * If the connection is closed a new connection is made automatically
	 * @return the connection
	 */
	public synchronized Connection getConnection() {
		try {
			if (connection.isClosed()) {
				System.out.println("resetting connection");
				resetConnection();
			}
		} catch (SQLException e) {
		}
		return connection;
	}

	/**
	 * Start a new transaction for your connection
	 * Sets Auto Commit to false for the connection 
	 * @throws SQLException
	 */
	public void startTransaction()  {
		try {
			connection.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Commit a transaction for your connection
	 * Commits current transaction and sets Auto Commit to true for your connection
	 * @throws SQLException
	 */
	public void commitTransaction() throws SQLException {
		try {
			if(!testingEnvironment) {
				connection.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(!testingEnvironment) {
				connection.setAutoCommit(true);
			}
		}
	}

	/**
	 * Rollback a transaction for your connection
	 * Rollsback your current transaction and sets Auto Commit to true
	 * @throws SQLException
	 */
	public void rollbackTransaction() throws SQLException {
		try {
			connection.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(!testingEnvironment) {
				connection.setAutoCommit(true);
			}
		}
	}
	/**
	 * Disconnected from the database
	 * Closes your current connection
	 */
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Resets the current connection to the database
	 * After Connection is closed a new one is opened
	 * Login timeout is set to 5 seconds
	 */
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
			System.out.println(getConnectionString());
			connection = DriverManager.getConnection(getConnectionString());
			if(testingEnvironment) {
				connection.setAutoCommit(false);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Set a new isolation level for your transactions
	 * @param level one of the following {@code Connection} constants:
     *        {@code Connection.TRANSACTION_READ_UNCOMMITTED},
     *        {@code Connection.TRANSACTION_READ_COMMITTED},
     *        {@code Connection.TRANSACTION_REPEATABLE_READ}, or
     *        {@code Connection.TRANSACTION_SERIALIZABLE}.
     *        (Note that {@code Connection.TRANSACTION_NONE} cannot be used
     *        because it specifies that transactions are not supported.)
	 * @throws SQLException
	 */
	public void setIsolationLevel(int level) throws SQLException {
		connection.setTransactionIsolation(level);
	}

	/**
	 * Check if the connection is still valid and connected to the database
	 * Sends a query to the database to check connection status
	 * @return boolean returned, true if connected, false if not
	 */
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
