package gui.components;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import controller.ConnectionController;

public class ConnectionWatch extends SwingWorker<Void, Boolean> {
	
	private ConnectionController connectionController = new ConnectionController();
	
	private JLabel connectionLabel;
	private int keepAlive = 1500;
	private int tryAgain = 3000;
	
	// This is a constructor for the `ConnectionWatch` class that takes a `JLabel` object as a parameter
	// and assigns it to the `connectionLabel` instance variable of the class. This allows the
	// `ConnectionWatch` object to update the text and color of the `JLabel` based on the status of the
	// database connection.
	public ConnectionWatch(JLabel connectionLabel) {
		this.connectionLabel = connectionLabel;
	}
	
	/**
	 * This function sets the text and color of a label based on a boolean value representing a connection
	 * status.
	 * 
	 * @param connected A boolean variable that indicates whether the connection is established or not. If
	 * it is true, it means that the connection is established, and if it is false, it means that there is
	 * no connection.
	 */
	private void setConnectionLabel(boolean connected) {
		if(!connected) {
			connectionLabel.setText("No Connection");
			connectionLabel.setForeground(Color.RED);
		} else {
			connectionLabel.setText("Connected");
			connectionLabel.setForeground(Color.GREEN);
		}
	}

	/**
	 * This function continuously checks for a database connection and displays a pop-up message if the
	 * connection is lost.
	 * 
	 * @return The method is returning a `Void` object.
	 */
	@Override
	protected Void doInBackground() throws Exception {
		boolean connected = false;
		
		while(true) {
			connected = connectionController.isConnected();
			
			publish(connected);
			
			try {
				if(connected) {
					Thread.sleep(keepAlive);
				}
				else {
					GUIPopUpMessages.informationMessage("Lost Connection to database!", "Lost connection");
					Thread.sleep(tryAgain);
				}
			} catch (Exception e) {
				//Left empty
			}
		}
	}

	/**
	 * This function updates a connection label based on the last boolean value in a list of boolean
	 * values.
	 * 
	 * @param chunks A list of Boolean values that are being processed. The method is called each time a
	 * chunk of data is processed. The last value in the list (chunks.get(chunks.size()-1)) is used to
	 * update a connection label.
	 */
	@Override
	protected void process(List<Boolean> chunks) {
		setConnectionLabel(chunks.get(chunks.size()-1));
	}
	

	
	
}
