package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.EmployeeController;
import gui.components.DefaultTable;
import gui.components.GUIPopUpMessages;
import gui.components.JRoundedButton;
import gui.components.TableSwingWorker;

/**
 * The EmployeeOverview class creates a JPanel that displays employee data in a table and provides
 * buttons for creating, editing, showing, and deleting employees.
 */
public class EmployeeOverview extends JPanel {

	//Panels
	private JPanel rightPanel;
	
	//Buttons
	private JButton createButton;
	private JButton editButton;
	private JButton showButton;
	private JButton deleteButton;
	
	//Tables
	private JScrollPane centerPanel;
	private DefaultTable employeeTable;

	private EmployeeController employeeCtr;

	/**
	 * Create the panel.
	 */
	public EmployeeOverview() {
		this.setName("Medarbejder Oversigt");
		employeeCtr = new EmployeeController();
		setLayout(new BorderLayout(0, 0));
		setPanels();
		setTables();
		setButtons();
		setEmployeeOnStartUp();
	}

	/**
	 * This function sets up a worker thread to fetch employee data from a database and display it in a
	 * table on startup.
	 */
	private void setEmployeeOnStartUp() {
		Thread workerThread = new Thread(() -> {
		    TableSwingWorker dataFetcher = null;
			try {
				dataFetcher = new TableSwingWorker(employeeTable, employeeCtr.getAllEmployees());
			} catch (SQLException e) {
				GUIPopUpMessages.warningMessage("Error Connecting to database", "Error");
				e.printStackTrace();
			}
		    dataFetcher.execute();
		});
		workerThread.start();
	}

	/**
	 * This function sets up four buttons with specific dimensions and adds them to a panel.
	 */
	private void setButtons() {
		createButton = new JRoundedButton("Opret");
		createButton.setMaximumSize(new Dimension(110, 23));
		createButton.setPreferredSize(new Dimension(110, 23));
		createButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(createButton);
		rightPanel.add(Box.createVerticalStrut(4));
		
		editButton = new JRoundedButton("Rediger");
		editButton.setMaximumSize(new Dimension(110, 23));
		editButton.setPreferredSize(new Dimension(110, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(editButton);
		rightPanel.add(Box.createVerticalStrut(4));
		
		showButton = new JRoundedButton("Se");
		showButton.setMaximumSize(new Dimension(110, 23));
		showButton.setPreferredSize(new Dimension(110, 23));
		showButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(showButton);
		rightPanel.add(Box.createVerticalStrut(4));
		
		deleteButton = new JRoundedButton("Slet");
		deleteButton.setMaximumSize(new Dimension(110, 23));
		deleteButton.setPreferredSize(new Dimension(110, 23));
		deleteButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(deleteButton);
		
	}

	/**
	 * This function sets up a table with specific columns and visibility settings for an employee list.
	 */
	private void setTables() {
		centerPanel = new JScrollPane();
		add(centerPanel, BorderLayout.CENTER);
		String[] columns2 = new String[] { "EmployeeID", "CPR", "Start Date", "Position", "Name", "Phone Number", "Email" };
		boolean[] visibleColumns = new boolean[] {true, false, false, true, true, true, false};
		employeeTable = new DefaultTable(null, columns2, visibleColumns);
		centerPanel.setViewportView(employeeTable);
		
	}

	/**
	 * This function sets up a JPanel on the right side of the container.
	 */
	private void setPanels() {
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
	}

}
