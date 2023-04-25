package gui;

import java.awt.Color;

import javax.swing.JLabel;

import Controller.ConnectionController;
import Controller.ConnectionControllerIF;

public class ConnectionWatch extends Thread {
	
	private ConnectionControllerIF connectionController = new ConnectionController();
	
	private JLabel connectionLabel;
	private int keepAlive = 60000;
	private int tryAgain = 15000;
	
	public ConnectionWatch(JLabel connectionLabel) {
		this.connectionLabel = connectionLabel;
	}

	@Override
	public void run() {

		boolean connected = false;
		
		while(true) {
			connected = connectionController.isConnected();
			
			setConnectionLabel(connected);
			
			try {
				if(connected) {
					Thread.sleep(keepAlive);
//					System.out.println("Sleep for " + keepAlive);
				}
				else {
					Thread.sleep(tryAgain);
//					System.out.println("Sleep for " + tryAgain);
				}
			} catch (Exception e) {
				//Left empty
			}
		}
		
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

	
	
}
