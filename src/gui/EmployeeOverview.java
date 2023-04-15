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

public class EmployeeOverview extends JPanel {

	/**
	 * Create the panel.
	 */
	public EmployeeOverview() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new GridLayout(30, 1, 1, 5));
		
		JButton createButton = new JButton("Opret");
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		rightPanel.add(createButton);
		
		JButton editButton = new JButton("Rediger");
		rightPanel.add(editButton);
		
		JButton showButton = new JButton("Se");
		rightPanel.add(showButton);
		
		JButton deleteButton = new JButton("Slet");
		rightPanel.add(deleteButton);
		
		
		
		
		JScrollPane centerPanel = new JScrollPane();
		add(centerPanel, BorderLayout.CENTER);
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		DefaultTable workOrderTable = new DefaultTable(null, columns2);
		centerPanel.setViewportView(workOrderTable);
	}

}
