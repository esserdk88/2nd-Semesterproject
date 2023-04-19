package gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Component;
import javax.swing.Box;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import java.awt.Dimension;

public class EmployeeOverview extends JPanel {

	/**
	 * Create the panel.
	 */
	public EmployeeOverview() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		
		JButton createButton = new JButton("Opret");
		createButton.setMaximumSize(new Dimension(80, 23));
		createButton.setPreferredSize(new Dimension(80, 23));
		createButton.setMinimumSize(new Dimension(30, 5));
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		rightPanel.add(createButton);
		
		JButton editButton = new JButton("Rediger");
		editButton.setMaximumSize(new Dimension(80, 23));
		editButton.setPreferredSize(new Dimension(80, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(editButton);
		
		JButton showButton = new JButton("Se");
		showButton.setMaximumSize(new Dimension(80, 23));
		showButton.setPreferredSize(new Dimension(80, 23));
		showButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(showButton);
		
		JButton deleteButton = new JButton("Slet");
		deleteButton.setMaximumSize(new Dimension(80, 23));
		deleteButton.setPreferredSize(new Dimension(80, 23));
		deleteButton.setMinimumSize(new Dimension(30, 5));
		rightPanel.add(deleteButton);
		
		JScrollPane centerPanel = new JScrollPane();
		add(centerPanel, BorderLayout.CENTER);
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		DefaultTable workOrderTable = new DefaultTable(null, columns2);
		centerPanel.setViewportView(workOrderTable);
	}

}
