package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import controller.EmployeeController;
import controller.MaintenanceController;
import controller.WorkOrderController;
import controller.interfaces.EmployeeControllerIF;
import gui.components.DefaultTable;
import gui.components.JRoundedButton;
import gui.components.ValueCheckerIF;
import gui.components.VerifiableTextFieldValue;
import gui.components.VerifiedValueRecieverIF;
import model.Employee;
import model.Maintenance;
import model.Workorder;
import javax.swing.UIManager;
import java.awt.Color;

public class ReadWorkOrder extends JPanel implements VerifiedValueRecieverIF {

	// Textfields
	private JTextField txtTitle;
	private JTextField txtInterval;
	private JTextField txtAssetID;
	private JTextField txtName;
	private JTextField txtSerialNumber;
	private JTextField txtType;
	private JTextField txtRegNo;
	private JTextField txtPriority;
	private JTextField txtEmployeeID;

	// Panels
	private JPanel southPanel;
	private JPanel centerPanel;

	// Buttons
	private JButton btnCancel;
	private JButton btnSave;
	private JButton btnAdd;
	private JButton btnDelete;

	// Labels
	private JLabel lblDescription;
	private JLabel lblTitle;
	private JLabel lblAssetID;
	private JLabel lblName;
	private JLabel lblSerialNumber;
	private JLabel lblInterval;
	private JLabel lblType;
	private JLabel lblHistory;
	private JLabel lblRegNumber;
	private JLabel lblPrioritize;
	private JLabel lblClosedBy;
	private JLabel lblActionsPerformed;

	// Tabel
	private JScrollPane historyScollPane;
	private DefaultTable historyTable;
	private JScrollPane scrollPane;
	private DefaultTable completedActionsTable;

	// Extra
	private JScrollPane descriptionScrollPane;
	private JTextArea textArea;
	private JCheckBox checkDate;
	private JSpinner spinner;
	private Workorder current;
	
	//used in when assigning new employee or other employee to current
	private int employeeID;
	
	private MaintenanceController maintenanceController;
	private WorkOrderController workorderController;
	private EmployeeControllerIF employeeController;
	private JButton btnAssignEmployee;
	/**
	 * Create the panel.
	 */
	public ReadWorkOrder() {
		maintenanceController = new MaintenanceController();
		employeeController = new EmployeeController();
		workorderController = new WorkOrderController();
		setLayout(new BorderLayout(0, 0));
		this.setName("Se Arbejdsopgave");
		setPanels();
		setLabelsAndTextFields();
		setTables();
		setButtons();
		setSpinners();
		setCheckBoxes();
	}

	public void setCurrentWorkorderInfo(Workorder current) {
		this.current = current;
		txtTitle.setText(current.getTitle());
		txtAssetID.setText(Integer.toString(current.getAsset().getAssetID()));
		txtName.setText(current.getAsset().getName());
		txtType.setText(current.getType());
		textArea.setText(current.getDescription());
		txtRegNo.setText(String.valueOf(current.getWorkOrderID()));
		txtPriority.setText(current.formatPriority(current.getPriority()));

		if (current.getType().equals("Maintenance")) {
			Maintenance maintenanceorder = null;
			maintenanceorder = maintenanceController.findWorkOrderByID(current.getWorkOrderID());
			txtInterval.setText(maintenanceorder.getIntervalDayCount() + " dage");
		} else {
			txtInterval.setText("Ingen");
		}

		if (current.getEmployee() != null) {
			txtEmployeeID.setText(current.getEmployee().getName());
			employeeID = current.getEmployee().getEmployeeID();
		} else {
			txtEmployeeID.setText("Ingen medarbejder");
		}

//		private JTextField txtSerialNumber;
//		private JTextField txtRegNo;
		setAssignButtonText();
	}
	
	private void setAssignButtonText() {
		if(this.txtEmployeeID.getText().equals("Ingen medarbejder") || this.txtEmployeeID.getText().isBlank() || txtEmployeeID.getText().isEmpty()) {
			System.out.println("if: " + txtEmployeeID.getText());
		}
		else {
			this.btnAssignEmployee.setText("Skift");
		}
	}
	
	//Verified value is an employeeID which will then be assigned to current workorder
		@Override
		public void receiveVerifiedValue(int verifiedValue) {
			if(verifiedValue > 0) {
				boolean successbool = false;
				Employee assignedEmployee = employeeController.findEmployeeByID(verifiedValue);
				successbool = workorderController.assignEmployeeToWorkOrder(assignedEmployee, current);
				this.txtEmployeeID.setText(assignedEmployee.getName());
//				System.out.println("Employee was assigned: " + successbool);
			}
			else {
				this.txtEmployeeID.setText("Ingen medarbejder");
			}
			setAssignButtonText();
		}
	
	private void btnAssignEmployeePressed() {
		//Open window to retrieve an employeeID, employeeIdSelected uses this instanses implementation of recieveVerifiedValue through the VerifiedValueRecieverIF interface
		//I know it's stupid, but i don't know how else to do it. 
		VerifiableTextFieldValue employeeIdSelecter = new VerifiableTextFieldValue((ValueCheckerIF) employeeController, "indtast et medarbejder id", "indtast et medarbejder id", this);
	}

	private void setCheckBoxes() {
		checkDate = new JCheckBox("Lukkes dato");
		checkDate.setHorizontalAlignment(SwingConstants.TRAILING);
		checkDate.setSelected(true);
		GridBagConstraints gbc_checkDate = new GridBagConstraints();
		gbc_checkDate.anchor = GridBagConstraints.WEST;
		gbc_checkDate.insets = new Insets(0, 0, 5, 5);
		gbc_checkDate.gridx = 7;
		gbc_checkDate.gridy = 6;
		centerPanel.add(checkDate, gbc_checkDate);
	}

	private void setSpinners() {
		SimpleDateFormat spinnerModel = new SimpleDateFormat("dd.MM.yyyy");
		spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, spinnerModel.toPattern()));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 8;
		gbc_spinner.gridy = 6;
		centerPanel.add(spinner, gbc_spinner);
	}

	private void setButtons() {

		btnCancel = new JRoundedButton("Udskyd arbejdsordre");
		southPanel.add(btnCancel);

		btnSave = new JRoundedButton("Færddiggør arbejdsordre");
		southPanel.add(btnSave);

		btnAdd = new JRoundedButton("Tilføj ny");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 5);
		gbc_btnAdd.gridx = 9;
		gbc_btnAdd.gridy = 9;
		centerPanel.add(btnAdd, gbc_btnAdd);

		btnDelete = new JRoundedButton("Slet");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 5);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 9;
		gbc_btnDelete.gridy = 10;
		centerPanel.add(btnDelete, gbc_btnDelete);

		// btnCancel.addActionListener(e -> AddMethodToCallHere);
		// btnSave.addActionListener(e -> AddMethodToCallHere);
		// btnAdd.addActionListener(e -> AddMethodToCallHere);
		// btnDelete.addActionListener(e -> AddMethodToCallHere);
	}

	private void setTables() {
		historyScollPane = new JScrollPane();
		historyScollPane.setPreferredSize(new Dimension(10, 0)); // Changes size of table
		GridBagConstraints gbc_historyScollPane = new GridBagConstraints();
		gbc_historyScollPane.gridheight = 3;
		gbc_historyScollPane.gridwidth = 2;
		gbc_historyScollPane.insets = new Insets(0, 0, 0, 5);
		gbc_historyScollPane.fill = GridBagConstraints.BOTH;
		gbc_historyScollPane.gridx = 1;
		gbc_historyScollPane.gridy = 9;
		centerPanel.add(historyScollPane, gbc_historyScollPane);

		String[] columnsHistory = new String[] { "ID", "Åbnet", "Lukket" };
		historyTable = new DefaultTable(null, columnsHistory);
		historyScollPane.setViewportView(historyTable);

		scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 9;
		centerPanel.add(scrollPane, gbc_scrollPane);

		String[] columnsCompletedActions = new String[] { "Reg. af", "Dato", "Bemærkning" };
		completedActionsTable = new DefaultTable(null, columnsCompletedActions);
		scrollPane.setViewportView(completedActionsTable);
	}

	private void setLabelsAndTextFields() {

		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		descriptionScrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.gridheight = 4;
		gbc_descriptionScrollPane.gridwidth = 5;
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 4;
		gbc_descriptionScrollPane.gridy = 2;
		centerPanel.add(descriptionScrollPane, gbc_descriptionScrollPane);

		lblDescription = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.WEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 4;
		gbc_lblDescription.gridy = 1;
		centerPanel.add(lblDescription, gbc_lblDescription);

		lblTitle = new JLabel("Emne");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.WEST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 2;
		centerPanel.add(lblTitle, gbc_lblTitle);

		txtTitle = new JTextField();
		GridBagConstraints gbc_txtTitle = new GridBagConstraints();
		gbc_txtTitle.insets = new Insets(0, 0, 5, 5);
		gbc_txtTitle.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtTitle.gridx = 2;
		gbc_txtTitle.gridy = 2;
		centerPanel.add(txtTitle, gbc_txtTitle);
		txtTitle.setColumns(10);

		lblAssetID = new JLabel("Maskine ID");
		GridBagConstraints gbc_lblAssetID = new GridBagConstraints();
		gbc_lblAssetID.anchor = GridBagConstraints.WEST;
		gbc_lblAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssetID.gridx = 1;
		gbc_lblAssetID.gridy = 3;
		centerPanel.add(lblAssetID, gbc_lblAssetID);

		txtAssetID = new JTextField();
		txtAssetID.setColumns(10);
		txtAssetID.setEnabled(false);
		GridBagConstraints gbc_txtAssetID = new GridBagConstraints();
		gbc_txtAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_txtAssetID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAssetID.gridx = 2;
		gbc_txtAssetID.gridy = 3;
		centerPanel.add(txtAssetID, gbc_txtAssetID);

		lblName = new JLabel("Navn");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.WEST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 4;
		centerPanel.add(lblName, gbc_lblName);

		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 4;
		centerPanel.add(txtName, gbc_txtName);

		lblSerialNumber = new JLabel("Serienr.");
		GridBagConstraints gbc_lblSerialNumber = new GridBagConstraints();
		gbc_lblSerialNumber.anchor = GridBagConstraints.WEST;
		gbc_lblSerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblSerialNumber.gridx = 1;
		gbc_lblSerialNumber.gridy = 5;
		centerPanel.add(lblSerialNumber, gbc_lblSerialNumber);

		txtSerialNumber = new JTextField();
		GridBagConstraints gbc_txtSerialNumber = new GridBagConstraints();
		gbc_txtSerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtSerialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSerialNumber.gridx = 2;
		gbc_txtSerialNumber.gridy = 5;
		centerPanel.add(txtSerialNumber, gbc_txtSerialNumber);
		txtSerialNumber.setColumns(10);

		lblPrioritize = new JLabel("Prioritering");
		GridBagConstraints gbc_lblPrioritize = new GridBagConstraints();
		gbc_lblPrioritize.anchor = GridBagConstraints.WEST;
		gbc_lblPrioritize.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrioritize.gridx = 1;
		gbc_lblPrioritize.gridy = 6;
		centerPanel.add(lblPrioritize, gbc_lblPrioritize);

		txtPriority = new JTextField();
		GridBagConstraints gbc_txtPriority = new GridBagConstraints();
		gbc_txtPriority.insets = new Insets(0, 0, 5, 5);
		gbc_txtPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPriority.gridx = 2;
		gbc_txtPriority.gridy = 6;
		centerPanel.add(txtPriority, gbc_txtPriority);
		txtPriority.setColumns(10);

		lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.anchor = GridBagConstraints.WEST;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 4;
		gbc_lblType.gridy = 6;
		centerPanel.add(lblType, gbc_lblType);

		txtType = new JTextField();
		GridBagConstraints gbc_txtType = new GridBagConstraints();
		gbc_txtType.insets = new Insets(0, 0, 5, 5);
		gbc_txtType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtType.gridx = 5;
		gbc_txtType.gridy = 6;
		centerPanel.add(txtType, gbc_txtType);
		txtType.setColumns(10);

		lblRegNumber = new JLabel("Reg nr.");
		GridBagConstraints gbc_lblRegNumber = new GridBagConstraints();
		gbc_lblRegNumber.anchor = GridBagConstraints.WEST;
		gbc_lblRegNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegNumber.gridx = 1;
		gbc_lblRegNumber.gridy = 7;
		centerPanel.add(lblRegNumber, gbc_lblRegNumber);

		txtRegNo = new JTextField();
		GridBagConstraints gbc_txtRegNo = new GridBagConstraints();
		gbc_txtRegNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtRegNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRegNo.gridx = 2;
		gbc_txtRegNo.gridy = 7;
		centerPanel.add(txtRegNo, gbc_txtRegNo);
		txtRegNo.setColumns(10);

		lblInterval = new JLabel("Interval");
		GridBagConstraints gbc_lblInterval = new GridBagConstraints();
		gbc_lblInterval.anchor = GridBagConstraints.WEST;
		gbc_lblInterval.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterval.gridx = 4;
		gbc_lblInterval.gridy = 7;
		centerPanel.add(lblInterval, gbc_lblInterval);

		txtInterval = new JTextField();
		GridBagConstraints gbc_txtInterval = new GridBagConstraints();
		gbc_txtInterval.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterval.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterval.gridx = 5;
		gbc_txtInterval.gridy = 7;
		centerPanel.add(txtInterval, gbc_txtInterval);
		txtInterval.setColumns(10);

		lblClosedBy = new JLabel("Lukkes af");
		GridBagConstraints gbc_lblClosedBy = new GridBagConstraints();
		gbc_lblClosedBy.anchor = GridBagConstraints.WEST;
		gbc_lblClosedBy.insets = new Insets(0, 0, 5, 5);
		gbc_lblClosedBy.gridx = 7;
		gbc_lblClosedBy.gridy = 7;
		centerPanel.add(lblClosedBy, gbc_lblClosedBy);

		txtEmployeeID = new JTextField();
		txtEmployeeID.setBackground(new Color(255, 255, 255));
		txtEmployeeID.setEditable(false);
		GridBagConstraints gbc_txtEmployeeID = new GridBagConstraints();
		gbc_txtEmployeeID.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmployeeID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmployeeID.gridx = 8;
		gbc_txtEmployeeID.gridy = 7;
		centerPanel.add(txtEmployeeID, gbc_txtEmployeeID);
		txtEmployeeID.setColumns(10);
		
		btnAssignEmployee = new JRoundedButton("Tildel");
		btnAssignEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAssignEmployeePressed();
			}
		});
		GridBagConstraints gbc_btnAssignEmployee = new GridBagConstraints();
		gbc_btnAssignEmployee.fill = GridBagConstraints.BOTH;
		gbc_btnAssignEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnAssignEmployee.gridx = 9;
		gbc_btnAssignEmployee.gridy = 7;
		centerPanel.add(btnAssignEmployee, gbc_btnAssignEmployee);

		lblHistory = new JLabel("Historik");
		lblHistory.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblHistory = new GridBagConstraints();
		gbc_lblHistory.gridwidth = 2;
		gbc_lblHistory.insets = new Insets(0, 0, 5, 5);
		gbc_lblHistory.gridx = 1;
		gbc_lblHistory.gridy = 8;
		centerPanel.add(lblHistory, gbc_lblHistory);

		lblActionsPerformed = new JLabel("Aktioner udført");
		lblActionsPerformed.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblActionsPerformed = new GridBagConstraints();
		gbc_lblActionsPerformed.gridwidth = 5;
		gbc_lblActionsPerformed.insets = new Insets(0, 0, 5, 5);
		gbc_lblActionsPerformed.gridx = 4;
		gbc_lblActionsPerformed.gridy = 8;
		centerPanel.add(lblActionsPerformed, gbc_lblActionsPerformed);
	}

	private void setPanels() {
		southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		add(southPanel, BorderLayout.SOUTH);

		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 101, 39, 55, 101, 59, 88, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 30, 0, 0, 0, 33, 0, 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0 };
		centerPanel.setLayout(gbl_centerPanel);

	}

}