package gui;

import java.awt.Color;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.SwingWorker;

import Controller.ConnectionController;
import Controller.ConnectionControllerIF;

public class ConnectionWatch extends SwingWorker<Void, Boolean> {
	
	private ConnectionControllerIF connectionController = new ConnectionController();
	
	private JLabel connectionLabel;
	private int keepAlive = 1500;
	private int tryAgain = 3000;
	
	public ConnectionWatch(JLabel connectionLabel) {
		this.connectionLabel = connectionLabel;
	}
	
	private void setConnectionLabel(boolean connected) {
		if(!connected) {
			connectionLabel.setText("No Connection");
			connectionLabel.setForeground(Color.RED);
		} else {
			connectionLabel.setText("Connected");
			connectionLabel.setForeground(Color.GREEN);
		}
	}

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

	@Override
	protected void process(List<Boolean> chunks) {
		setConnectionLabel(chunks.get(chunks.size()-1));
	}
	

	
	
}
