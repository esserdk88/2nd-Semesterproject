package Controller;

import java.sql.SQLException;

import dal.DatabaseConnection;

public class ConnectionController implements ConnectionControllerIF {

	@Override
	public boolean isConnected() {
		boolean connected = false;
		connected = DatabaseConnection.getInstance().getConnection() != null;
		return connected;
	}

}
