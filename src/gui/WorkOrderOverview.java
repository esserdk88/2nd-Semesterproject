package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.MaintenanceController;
import controller.RepairController;
import controller.ServiceController;
import controller.WorkOrderController;
import gui.components.DefaultTable;
import gui.components.JRoundedButton;
import gui.components.TableSwingWorker;
import model.Workorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class WorkOrderOverview extends JPanel {

	// TextField
	private JTextField searchTextField;
	private JTextField departmentTextField;
	private JTextField titleTextField;

	// Table
	private JScrollPane centerScrollPane;
	private DefaultTable workOrderTable;
	private final String[] columns = { "WorkOrderID", "Emne", "Type", "Start Dato", "Slut Dato", "Prioritet", "Beskrivelse", "Færdig", "AssetID", "Medarbejder"};

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
	private Component rigidArea_2;
	private MainFrame frame;
	
	//Controllers
	private WorkOrderController workorderController;
	private MaintenanceController  maintenanceController; 
	private ServiceController serviceController;
	private RepairController repairController;
	private JLabel lblUnfinishedWorkorders;
	private JRadioButton unfinishedRadioButton;

	/**
	 * Create the panel.
	 */
	public WorkOrderOverview(MainFrame frame) {
		this.frame = frame;
		workorderController = new WorkOrderController();
		maintenanceController = new MaintenanceController();
		serviceController = new ServiceController();
		repairController = new RepairController();
		setLayout(new BorderLayout(0, 0));
		this.setName("Arbejdsordre Oversigt");
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
		highJRadioButton.setName("3");
		highJRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 //TODO updateWorkOrderTable();
			}
		});
		leftTopPriorityPanel.add(highJRadioButton);
		priorityButtons.add(highJRadioButton);

		searchDepartmentLabel = new JLabel("Afdeling");
		leftTopPriorityPanel.add(searchDepartmentLabel);

		departmentTextField = new JTextField();
		leftTopPriorityPanel.add(departmentTextField);
		departmentTextField.setColumns(10);

		mediumJRadioButton = new JRadioButton("Mellem");
		mediumJRadioButton.setName("2");
		mediumJRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 //TODO updateWorkOrderTable();
			}
		});
		mediumJRadioButton.setSelected(true);
		leftTopPriorityPanel.add(mediumJRadioButton);
		priorityButtons.add(mediumJRadioButton);

		searchTitleLabel = new JLabel("Titel");
		leftTopPriorityPanel.add(searchTitleLabel);

		titleTextField = new JTextField();
		titleTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//TODO event
			}
		});
		titleTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				 //TODO updateWorkOrderTable();
			}
		});
		leftTopPriorityPanel.add(titleTextField);
		titleTextField.setColumns(10);

		lowJRadioButton = new JRadioButton("Lav");
		lowJRadioButton.setName("1");
		lowJRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 //TODO updateWorkOrderTable();
			}
		});
		lowJRadioButton.setSelected(true);
		leftTopPriorityPanel.add(lowJRadioButton);
		priorityButtons.add(lowJRadioButton);
		
		lblUnfinishedWorkorders = new JLabel("Ikke færdige");
		leftTopPriorityPanel.add(lblUnfinishedWorkorders);
		
		unfinishedRadioButton = new JRadioButton("");
		unfinishedRadioButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				 //TODO updateWorkOrderTable();
			}
		});
		leftTopPriorityPanel.add(unfinishedRadioButton);

		searchLabel = new JLabel("Søg på ID");
		rightTopPanel.add(searchLabel);

		searchTextField = new JTextField();
		rightTopPanel.add(searchTextField);
		searchTextField.setColumns(10);

	}
	private void btnSearchWorkOrdersPressed() {
		String name = titleTextField.getText();
		String location = departmentTextField.getText();
		boolean isFinished = !unfinishedRadioButton.isSelected();
		Enumeration<AbstractButton> buttons = priorityButtons.getElements();
		List<Short> priority = new ArrayList<>();
		buttons.asIterator().forEachRemaining(button -> {
		    if (button.isSelected()) {
		        priority.add(Short.valueOf(button.getName()));
		    }
		});
		
		String[][] loadingStatus = { { "Henter arbejdsordrer..." } };
		workOrderTable.setNewData(loadingStatus);
		Thread workerThread = new Thread(() -> {
			TableSwingWorker dataFetcher = null;
			dataFetcher = new TableSwingWorker(workOrderTable, workorderController.searchWorkorderDataBase(name, priority, location, isFinished));
			dataFetcher.execute();
		});
		workerThread.start();
	}
	
	private void readWorkOrderButton() {
		ReadWorkOrder panel = new ReadWorkOrder(); //TODO: Change to field and instantiate in constructor
		frame.setNewCenterPanel(panel);
		Thread workerThread = new Thread(() -> {
			panel.setCurrentWorkorderInfo(getController(workOrderTable.getCellData(columns[2])));
		});
		workerThread.start();
	}
	private void setButtons() {
		searchButton = new JRoundedButton("Søg");
		searchButton.addActionListener((e) -> btnSearchWorkOrdersPressed());
		rightTopPanel.add(searchButton);
		openOrderButton = new JRoundedButton("Åben opgave");
		openOrderButton.setEnabled(false);
		openOrderButton.addActionListener((e) -> readWorkOrderButton());
		
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
	
	private Workorder getController(String type) {
		int workOrderID = Integer.valueOf(workOrderTable.getCellData(columns[0]));
		switch(type) {
		case"Maintenance":
			return maintenanceController.findWorkOrderByID(workOrderID); //TODO: Change to field and instantiate in constructor
		case"Repair":
			return  repairController.findWorkOrderByID(workOrderID); //TODO: Change to field and instantiate in constructor
		case"Service":
			return serviceController.findWorkOrderByID(workOrderID); //TODO: Change to field and instantiate in constructor
		}
		return null;
	}

	private void setTables() {
		centerScrollPane = new JScrollPane();
		add(centerScrollPane, BorderLayout.CENTER);
		boolean[] activeColumns = new boolean[] { true, true, true, true, false, true, false, false, false, true };
		workOrderTable = new DefaultTable(null, columns, activeColumns);
		centerScrollPane.setViewportView(workOrderTable);
		workOrderTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int[] rows = workOrderTable.getSelectedRows();
				if(rows.length != 0) {
					openOrderButton.setEnabled(true);
				} else {
					openOrderButton.setEnabled(false);
				}
			}
		});
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
		Thread workerThread = new Thread(() -> {
			TableSwingWorker dataFetcher = null;
			dataFetcher = new TableSwingWorker(workOrderTable, workorderController.getAllUnfinishedWorkOrders());
			dataFetcher.execute();
		});
		workerThread.start();
	}
	
//	private void updateWorkOrderTable() {
//		String[][] loadingStatus = { { "Henter arbejdsordrer..." } };
//		workOrderTable.setNewData(loadingStatus);
//		Thread workerThread = new Thread(() -> {
//			TableSwingWorker dataFetcher = null;
//			//TODO finish when select is made
//			dataFetcher = new TableSwingWorker(workOrderTable, filterWorkorderList(workorderController.getAllWorkOrders()));
//			dataFetcher.execute();
//		});
//		workerThread.start();
//	}
	
	
	//FILTERING IS NOT SUPPOSED TO WORK LIKE THIS
	//THIS IS TEMPORARY AND IS ONLY USED BECAUSE IMPLEMENTING IT WOULD TAKE WAYY TOO LONG FOR IT TO MAKE SENCE FOR ONE USE CASE
	//NORMALLY THIS WOULD BE IMPLEMENTED AS A PROCEDUALLY GENERATED SQL SCRIPT THAT WILL SPECIFY CHRITERIA FOR THE SELECTED ITEMS
	//DOING IT LIKE THIS WONT WORK FOR THAT LONG BECAUSE OF THE SHERE AMOUNT OF OBJECT RETRIEVED INTO MEMORY AND NEEDS TO BE PROCESSES LOCALLY
//	private List<Workorder> filterWorkorderList(List<Workorder> workorderList) {
//		List<Workorder> filteredList = new ArrayList<>();
//		
//		boolean notFinished = unfinishedRadioButton.isSelected();
//		
//		for(Workorder w: workorderList) {
//			if(!unfinishedRadioButton.isSelected() || !w.isFinished() == unfinishedRadioButton.isSelected()) {
//				if(titleTextField.getText().isBlank() || titleTextField.getText().contains(w.getTitle())); {
//					if(highJRadioButton.isSelected() && w.getPriority() == 3) {
//						filteredList.add(w);
//					}
//					if (mediumJRadioButton.isSelected() && w.getPriority() == 2) {
//						filteredList.add(w);
//					}
//					if(lowJRadioButton.isSelected() && w.getPriority() == 1) {
//						filteredList.add(w);
//					}
//				}
//			}
//		}
//		return filteredList;
//	}
}
