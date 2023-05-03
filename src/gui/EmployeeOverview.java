package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Controller.EmployeeController;
import gui.components.DefaultTable;
import gui.components.JRoundedButton;
import gui.components.TableSwingWorker;

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
		setLayout(new BorderLayout(0, 0));
		setPanels();
		setTables();
		setButtons();
		setEmployeeOnStartUp();
	}

	private void setEmployeeOnStartUp() {
		employeeCtr = new EmployeeController();
		Thread workerThread = new Thread(() -> {
		    TableSwingWorker dataFetcher = null;
			try {
				dataFetcher = new TableSwingWorker(employeeTable, employeeCtr.getAllEmployees());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    dataFetcher.execute();
		});
		workerThread.start();
	}
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
		
		//createButton.addActionListener(e -> AddMethodToCallHere);
		//editButton.addActionListener(e -> AddMethodToCallHere);
		//showButton.addActionListener(e -> AddMethodToCallHere);
		//deleteButton.addActionListener(e -> AddMethodToCallHere);
		
	}

	private void setTables() {
		centerPanel = new JScrollPane();
		add(centerPanel, BorderLayout.CENTER);
		String[] columns2 = new String[] { "EmployeeID", "CPR", "Start Date", "Position", "Name", "Phone Number", "Email" };
		boolean[] visibleColumns = new boolean[] {true, false, false, true, true, true, false};
		employeeTable = new DefaultTable(null, columns2, visibleColumns);
		centerPanel.setViewportView(employeeTable);
		
	}

	private void setPanels() {
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
	}

}
