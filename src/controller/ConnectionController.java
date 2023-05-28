package controller;

import controller.interfaces.ConnectionControllerIF;
import dao.DatabaseConnection;

/**
 * The ConnectionController class checks if there is a connection to the database.
 */
public class ConnectionController implements ConnectionControllerIF {

	/**
	 * The function checks if there is a connection to the database.
	 * 
	 * @return A boolean value indicating whether the database connection is currently established or not.
	 */
	@Override
	public boolean isConnected() {
		return DatabaseConnection.getInstance().isConnected();
	}

}
