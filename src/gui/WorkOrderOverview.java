package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Controller.WorkOrderController;
import gui.components.DefaultTable;
import gui.components.JRoundedButton;
import gui.components.TableSwingWorker;

public class WorkOrderOverview extends JPanel {

	// TextField
	private JTextField searchTextField;
	private JTextField departmentTextField;
	private JTextField titleTextField;

	// Table
	private JScrollPane centerScrollPane;
	private DefaultTable workOrderTable;

	// Button
	private JButton createNewWorkOrder;
	private JButton openOrderButton;
	private JButton searchButton;

	// Panels
	private JPanel rightPanel;
	private JPanel rightTopPanel;
	private JPanel leftTopPriorityPanel;
	private JPanel leftTopPanel;
	private JPanel topPanel;

	// Label
	private JLabel searchLabel;
	private JLabel searchTitleLabel;
	private JLabel searchDepartmentLabel;
	private JLabel searchCriteriaLabel;
	private JLabel priorityLabel;

	// RadioButtons
	private JRadioButton lowJRadioButton;
	private JRadioButton mediumJRadioButton;
	private JRadioButton highJRadioButton;

	// Extra components
	private ButtonGroup priorityButtons;
	private Component rigidArea_1;
	private Component rigidArea;
	private Component rigidArea_2;
	private MainFrame frame;
	private WorkOrderController workOrderCtrl;

	/**
	 * Create the panel.
	 */
	public WorkOrderOverview(MainFrame frame) {
		this.frame = frame;
		setLayout(new BorderLayout(0, 0));

		setPanels();
		setLabelsAndTextFieldsAndRadioButtons();
		setTables();
		setButtons();
		setWorkOrderOnStartUp();
	}

	private void setLabelsAndTextFieldsAndRadioButtons() {
		priorityButtons = new ButtonGroup();

		priorityLabel = new JLabel("Prioritet:");
		leftTopPriorityPanel.add(priorityLabel);

		searchCriteriaLabel = new JLabel("Søgekriterier:");
		leftTopPriorityPanel.add(searchCriteriaLabel);

		rigidArea_2 = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea_2);

		highJRadioButton = new JRadioButton("Høj");
		leftTopPriorityPanel.add(highJRadioButton);
		priorityButtons.add(highJRadioButton);

		searchDepartmentLabel = new JLabel("Afdeling");
		leftTopPriorityPanel.add(searchDepartmentLabel);

		departmentTextField = new JTextField();
		leftTopPriorityPanel.add(departmentTextField);
		departmentTextField.setColumns(10);

		mediumJRadioButton = new JRadioButton("Mellem");
		leftTopPriorityPanel.add(mediumJRadioButton);
		priorityButtons.add(mediumJRadioButton);

		searchTitleLabel = new JLabel("Titel");
		leftTopPriorityPanel.add(searchTitleLabel);

		titleTextField = new JTextField();
		leftTopPriorityPanel.add(titleTextField);
		titleTextField.setColumns(10);

		lowJRadioButton = new JRadioButton("Lav");
		leftTopPriorityPanel.add(lowJRadioButton);
		priorityButtons.add(lowJRadioButton);
		rigidArea = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea);

		rigidArea_1 = Box.createRigidArea(new Dimension(20, 20));
		leftTopPriorityPanel.add(rigidArea_1);

		searchLabel = new JLabel("Søg på ID");
		rightTopPanel.add(searchLabel);

		searchTextField = new JTextField();
		rightTopPanel.add(searchTextField);
		searchTextField.setColumns(10);

	}

	private void setButtons() {
		searchButton = new JRoundedButton("Søg");
		rightTopPanel.add(searchButton);
		openOrderButton = new JRoundedButton("Åben opgave");
		openOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setNewCenterPanel(new ReadWorkOrder()); // TODO add workorder to constructor
			}
		});
		openOrderButton.setMinimumSize(new Dimension(145, 23));
		openOrderButton.setMaximumSize(new Dimension(145, 23));
		openOrderButton.setPreferredSize(new Dimension(145, 23));
		rightPanel.add(openOrderButton);
		rightPanel.add(Box.createVerticalStrut(4));
		createNewWorkOrder = new JRoundedButton("Ny Opgave");
		createNewWorkOrder.setPreferredSize(new Dimension(145, 23));
		createNewWorkOrder.setMinimumSize(new Dimension(145, 23));
		createNewWorkOrder.setMaximumSize(new Dimension(145, 23));
		createNewWorkOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setNewCenterPanel(new CreateWorkOrder());
			}
		});
		rightPanel.add(createNewWorkOrder);

	}

	private void setTables() {
		centerScrollPane = new JScrollPane();
		add(centerScrollPane, BorderLayout.CENTER);
		boolean[] activeColumns = new boolean[] { true, true, true, true, false, true, false, false, false, true };
		String[] columns2 = new String[] { "WorkOrderID", "Emne", "Type", "Start Dato", "Slut Dato", "Prioritet", "Beskrivelse", "Færdig", "AssetID", "Medarbejder"};
		workOrderTable = new DefaultTable(null, columns2, activeColumns);
		centerScrollPane.setViewportView(workOrderTable);
	}

	private void setPanels() {
		topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new BorderLayout(0, 0));

		leftTopPanel = new JPanel();
		topPanel.add(leftTopPanel);
		leftTopPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		leftTopPriorityPanel = new JPanel();
		leftTopPanel.add(leftTopPriorityPanel);
		leftTopPriorityPanel.setLayout(new GridLayout(4, 3, 0, 0));
		rightTopPanel = new JPanel();
		topPanel.add(rightTopPanel, BorderLayout.EAST);
		rightPanel = new JPanel();
		add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

	}

	public void setWorkOrderOnStartUp() {
		String[][] loadingStatus = { { "Henter arbejdsordrer..." } };
		workOrderTable.setNewData(loadingStatus);
		workOrderCtrl = new WorkOrderController();
		Thread workerThread = new Thread(() -> {
			TableSwingWorker dataFetcher = null;
			dataFetcher = new TableSwingWorker(workOrderTable, workOrderCtrl.getAllUnfinishedWorkOrders());
			dataFetcher.execute();
		});
		workerThread.start();
	}
}
