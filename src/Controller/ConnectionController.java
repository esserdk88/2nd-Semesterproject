package Controller;

import java.sql.SQLException;

import dal.DatabaseConnection;

public class ConnectionController implements ConnectionControllerIF {

	@Override
	public boolean isConnected() {
		boolean connected = false;
		
		try {
			connected = DatabaseConnection.getInstance().getConnection() != null;
		} catch (SQLException e) {
			
		}
		return connected;
	}

}
