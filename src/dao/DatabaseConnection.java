package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	// Singleton instance variable
	private static DatabaseConnection instance;
	private static Connection connection;
	private boolean testingEnvironment;

	// Connection string for connecting to SQL Server
	private static final String CONNECTION_STRING = String.format(
			"jdbc:sqlserver://%s:1433;encrypt=true;trustServerCertificate=true;databaseName=%s;user=%s;password=%s;",
			other.Login.HOSTNAME,
			other.Login.DATABASENAME,
			other.Login.USERNAME,
			other.Login.PASSWORD);

	
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
	public void startTransaction() throws SQLException {
		connection.setAutoCommit(false);
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
			connection = DriverManager.getConnection(CONNECTION_STRING);
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
	
	/**
	 * This is ONLY for testing and shouldn't be used for anything else
	 * @throws SQLException 
	 */
	public void setTestingEnvironment() throws SQLException {
		testingEnvironment = true;
		connection.setAutoCommit(false);
	}

}
