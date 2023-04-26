package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import gui.components.DefaultTable;
import gui.components.JRoundedButton;

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
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		workOrderTable = new DefaultTable(null, columns2);
		centerPanel.setViewportView(workOrderTable);
		
	}

	private void setPanels() {
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
	}

}
