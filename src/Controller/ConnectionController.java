package Controller;

import dal.DatabaseConnection;

public class ConnectionController implements ConnectionControllerIF {

	@Override
	public boolean isConnected() {
		return DatabaseConnection.getInstance().isConnected();
	}

}
