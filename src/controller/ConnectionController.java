package controller;

import controller.interfaces.ConnectionControllerIF;
import dao.DatabaseConnection;

public class ConnectionController implements ConnectionControllerIF {

	@Override
	public boolean isConnected() {
		return DatabaseConnection.getInstance().isConnected();
	}

}
