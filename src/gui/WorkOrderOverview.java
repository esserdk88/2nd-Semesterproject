package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class WorkOrderOverview extends JPanel {
	private JTextField searchTextField;
	private JTextField departmentTextField;
	private JTextField titleTextField;

	/**
	 * Create the panel.
	 */
	public WorkOrderOverview(MainFrame frame) {
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel leftTopPanel = new JPanel();
		topPanel.add(leftTopPanel);
		leftTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		
		ButtonGroup priorityButtons = new ButtonGroup();
		
		JPanel leftTopPriorityPanel = new JPanel();
		leftTopPanel.add(leftTopPriorityPanel);
		leftTopPriorityPanel.setLayout(new GridLayout(4, 3, 0, 0));
		
		JLabel priorityLabel = new JLabel("Prioritet:");
		leftTopPriorityPanel.add(priorityLabel);
		
		JLabel searchCriteriaLabel = new JLabel("Søgekriterier:");
		leftTopPriorityPanel.add(searchCriteriaLabel);
				
		Component rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea_2);
		
		JRadioButton highJRadioButton = new JRadioButton("Høj");
		leftTopPriorityPanel.add(highJRadioButton);
		priorityButtons.add(highJRadioButton);
		
		JLabel searchDepartmentLabel = new JLabel("Afdeling");
		leftTopPriorityPanel.add(searchDepartmentLabel);
		
		departmentTextField = new JTextField();
		leftTopPriorityPanel.add(departmentTextField);
		departmentTextField.setColumns(10);
		
		JRadioButton mediumJRadioButton = new JRadioButton("Mellem");
		leftTopPriorityPanel.add(mediumJRadioButton);
		priorityButtons.add(mediumJRadioButton);
		
		JLabel searchTitleLabel = new JLabel("Titel");
		leftTopPriorityPanel.add(searchTitleLabel);
		
		titleTextField = new JTextField();
		leftTopPriorityPanel.add(titleTextField);
		titleTextField.setColumns(10);
		
		JRadioButton lowJRadioButton = new JRadioButton("Lav");
		leftTopPriorityPanel.add(lowJRadioButton);
		priorityButtons.add(lowJRadioButton);
		
		Component rigidArea = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea);
		
		Component rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea_1);
		
		
		JPanel rightTopPanel = new JPanel();
		topPanel.add(rightTopPanel, BorderLayout.EAST);
		
		JLabel searchLabel = new JLabel("Søg på ID");
		rightTopPanel.add(searchLabel);
		
		searchTextField = new JTextField();
		rightTopPanel.add(searchTextField);
		searchTextField.setColumns(10);
		
		JButton searchButton = new JButton("Søg");
		rightTopPanel.add(searchButton);
		
		JPanel rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
		
		JButton openOrderButton = new JButton("Åben opgave");
		openOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setNewCenterPanel(new ReadWorkOrder()); //TODO add workorder to constructor
			}
		});
		openOrderButton.setMinimumSize(new Dimension(110, 23));
		openOrderButton.setMaximumSize(new Dimension(110, 23));
		openOrderButton.setPreferredSize(new Dimension(110, 23));
		rightPanel.add(openOrderButton);
		
		JButton createNewWorkOrder = new JButton("Ny Opgave");
		createNewWorkOrder.setPreferredSize(new Dimension(110, 23));
		createNewWorkOrder.setMinimumSize(new Dimension(110, 23));
		createNewWorkOrder.setMaximumSize(new Dimension(110, 23));
		createNewWorkOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setNewCenterPanel(new WorkOrder());
			}
		});
		rightPanel.add(createNewWorkOrder);
		
		JScrollPane centerScrollPane = new JScrollPane();
		add(centerScrollPane, BorderLayout.CENTER);
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		DefaultTable workOrderTable = new DefaultTable(null, columns2);
		centerScrollPane.setViewportView(workOrderTable);

	}

}
