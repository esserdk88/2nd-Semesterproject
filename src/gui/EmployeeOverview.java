package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.components.DefaultTable;

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
	private DefaultTable workOrderTable;

	/**
	 * Create the panel.
	 */
	public EmployeeOverview() {
		setLayout(new BorderLayout(0, 0));
		setPanels();
		setTables();
		setButtons();
	}

	private void setButtons() {
		createButton = new JButton("Opret");
		createButton.setMaximumSize(new Dimension(80, 23));
		createButton.setPreferredSize(new Dimension(80, 23));
		createButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(createButton);
		
		editButton = new JButton("Rediger");
		editButton.setMaximumSize(new Dimension(80, 23));
		editButton.setPreferredSize(new Dimension(80, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(editButton);
		
		showButton = new JButton("Se");
		showButton.setMaximumSize(new Dimension(80, 23));
		showButton.setPreferredSize(new Dimension(80, 23));
		showButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(showButton);
		
		deleteButton = new JButton("Slet");
		deleteButton.setMaximumSize(new Dimension(80, 23));
		deleteButton.setPreferredSize(new Dimension(80, 23));
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
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		workOrderTable = new DefaultTable(null, columns2);
		centerPanel.setViewportView(workOrderTable);
		
	}

	private void setPanels() {
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
	}

}
