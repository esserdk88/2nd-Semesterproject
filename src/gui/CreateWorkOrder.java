package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import Controller.MaintenanceController;
import Controller.RepairController;
import Controller.ServiceController;
import dal.AssetDBIF;
import dal.Database;
import gui.components.JRoundedButton;
import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Service;
import model.Workorder;

public class CreateWorkOrder extends JPanel {
	
	//TextFields
	private JTextField topicTextField;
	private JTextField txtAssetID;
	private JTextField txtName;
	private JTextField serieNrTextField;
	private JTextField txtWorkOrderID;
	private JScrollPane scrollPane;//For TextArea
	private JTextArea textArea;
	
	//Comboboxes
	private JComboBox<?> typeComboBox;
	private JComboBox<?> templatesComboBox;
	private JComboBox<?> priorityComboBox;
	private JComboBox<?> comboBox_2;
	
	//Labels
	private JLabel lblDescription;
	private JLabel lblInterval;
	private JLabel lblEndDate;
	private JLabel lblStartDate;
	private JLabel lblTemplates;
	private JLabel lblType;
	private JLabel lblSerieNr;
	private JLabel lblRegNr;
	private JLabel lblName;
	private JLabel lblStatus;
	private JLabel lblAssetID;
	private JLabel lblNewLabel_1;
	private JLabel lblTitle;
	
	//Buttons
	private JRoundedButton createWorkOrderButton;
	private JRoundedButton btnFindEmployee;
	
	//Spinners
	private JSpinner spinnerEndDate;
	private JSpinner spinnerStartDate;
	private SimpleDateFormat spinnerModel;//For Date Spinners
	private JSpinner intervalSpinner;
	
	//Panels
	private JPanel centerLeftPanel;
	
	//Extra
	private Asset asset;
	private Employee employee;
	private String assetID;
	private Asset currentAsset;
	

	/**
	 * Create the panel.
	 */
	public CreateWorkOrder() {
		setLayout(new BorderLayout(0, 0));
		this.setFocusable(true);
		
		setPanels();
		setButtons();
		setTextFields();
		setLabels();
		setSpinners();
		setComboBoxes();
		setMaintenanceFields();		
	}
	
	public CreateWorkOrder(Asset currentAsset) {
		this();
		this.currentAsset = currentAsset;
		setupTextFields();
	}
	
	private void setupTextFields() {
		txtAssetID.setEnabled(false);
		txtName.setEnabled(false);
		txtAssetID.setText(Integer.toString((currentAsset.getAssetID())));
		txtName.setText(currentAsset.getName());
	}
	
	private boolean createWorkOrder() {
		if(asset == null) {GUIPopUpMessages.warningMessage("No Asset selected!", "Error!"); return false;}
		
		String type = typeComboBox.getSelectedItem().toString();
		//Set the WorkOrder type
		Workorder workOrder = createWorkOrderOfType(type);
		
		//Set common data
		workOrder.setTitle(topicTextField.getText());
		
		//Setting WorkOrder Dates
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) spinnerStartDate.getValue());
		workOrder.setStartDate(calendar);
		calendar.setTime((Date) spinnerEndDate.getValue());
		workOrder.setEndDate(calendar);
		
		workOrder.setPriority(getPriorityFromComboBox());
		workOrder.setDescription(textArea.getText());
		workOrder.setAsset(asset);
		
		
		boolean success = callControllerOfType(type, workOrder);
	    return success;
	}
	
	private boolean callControllerOfType(String type, Workorder workOrder) {
	    switch(type) {
	        case "Reparation":
	            RepairController repairController = new RepairController();
	            return repairController.createWorkOrder((Repair) workOrder);
	        case "Serviceaftale":
	            ServiceController serviceController = new ServiceController();
	            return serviceController.createWorkOrder((Service) workOrder);
	        case "Vedligeholdelse":
	            MaintenanceController maintenanceController = new MaintenanceController();
	            return maintenanceController.createWorkOrder((Maintenance) workOrder);
	        default:
	            return false;
	    }
	}
	private Workorder createWorkOrderOfType(String type) {
	    switch(type) {
	        case "Reparation":
	            Repair repair = new Repair();
	            // TODO: add specific values for repair
	            return repair;
	        case "Serviceaftale":
	            Service service = new Service();
	            // TODO: add specific values for service
	            return service;
	        case "Vedligeholdelse":
	            Maintenance maintenance = new Maintenance();
	            maintenance.setRepeated(true);
	            maintenance.setIntervalDayCount((int) intervalSpinner.getValue());
	            return maintenance;
	        default:
	            return null;
	    }
	}
	
	private short getPriorityFromComboBox() {
	    String priority = priorityComboBox.getSelectedItem().toString();
	    switch(priority) {
	        case "Høj":
	            return 3;
	        case "Mellem":
	            return 2;
	        default:
	            return 1;
	    }
	}

	
	private void setIntervalFromTemplate() {
		switch(templatesComboBox.getSelectedItem().toString()) {
		case"2 uger":
			intervalSpinner.setValue(14);
			break;	
		case"1 måned":
			intervalSpinner.setValue(30);
			break;
		case"3 måneder":
			intervalSpinner.setValue(90);
			break;
		case"6 måneder":
			intervalSpinner.setValue(180);
			break;
		case"12 måneder":
			intervalSpinner.setValue(365);
			break;
		}
	}

	private void checkAsset() {

		if(txtAssetID.getText().length()>0) {
			if(!txtAssetID.getText().equals(assetID)) {
				Thread workerThread = new Thread(() -> {
				assetID = txtAssetID.getText();
				Asset assetCheck = null;
				AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
					try {
						assetCheck = assetDB.findAssetByID(Integer.parseInt(assetID));
					} catch (NumberFormatException e1) {
						GUIPopUpMessages.warningMessage("Only letters in AssetID Field", "Error!");
						System.out.println("Letter in Asset Text Field");
					} catch (SQLException e1) {
						GUIPopUpMessages.warningMessage("Error retrieving data from database", "Error!");
					}

				if(assetCheck == null) {
					txtAssetID.setBackground(Color.red);
					txtName.setText("");
				}else {
					txtAssetID.setBackground(Color.green);
					txtName.setText(assetCheck.getName());
					asset = assetCheck;
					}
				});
				workerThread.start();
				
			}
		}
	}
	
	private void setMaintenanceFields() {
		if(typeComboBox.getSelectedItem().toString().equals("Vedligeholdelse")) {
			intervalSpinner.setEnabled(true);
			templatesComboBox.setEnabled(true);
			lblInterval.setEnabled(true);
			lblTemplates.setEnabled(true);
		}else {
			intervalSpinner.setEnabled(false);
			templatesComboBox.setEnabled(false);
			lblInterval.setEnabled(false);
			lblTemplates.setEnabled(false);
		}
	}
	
	private void createWorkOrderButtonMethod() {
		Thread workerThread = new Thread(() -> {
		    boolean success = createWorkOrder();
		    if(success) {
		    	GUIPopUpMessages.informationMessage("Creation was successfull", "Success!");
		    }else {
		    	GUIPopUpMessages.warningMessage("Creation error, reason unknown", "Error");
		    }
		});
		workerThread.start();
	}

	private void setPanels() {
		centerLeftPanel = new JPanel();
		add(centerLeftPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerLeftPanel = new GridBagLayout();
		gbl_centerLeftPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerLeftPanel.rowHeights = new int[] {30, 0, 0, 0, 0, 30, 0, 0, 0, 101, 0, 0};
		gbl_centerLeftPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerLeftPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centerLeftPanel.setLayout(gbl_centerLeftPanel);
		
	}
	private void setButtons() {
		
		btnFindEmployee = new JRoundedButton("Find medarbejder");
		GridBagConstraints gbc_btnFindEmployee = new GridBagConstraints();
		gbc_btnFindEmployee.fill = GridBagConstraints.BOTH;
		gbc_btnFindEmployee.weighty = 0;
		gbc_btnFindEmployee.weightx = 0;
		gbc_btnFindEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindEmployee.gridx = 6;
		gbc_btnFindEmployee.gridy = 1;
		centerLeftPanel.add(btnFindEmployee, gbc_btnFindEmployee);
		
		lblStartDate = new JLabel("Startdato");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.fill = GridBagConstraints.BOTH;
		gbc_lblStartDate.weighty = 0;
		gbc_lblStartDate.weightx = 0;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 4;
		centerLeftPanel.add(lblStartDate, gbc_lblStartDate);
		
		
		lblSerieNr = new JLabel("Serienr.");
		GridBagConstraints gbc_lblSerieNr = new GridBagConstraints();
		gbc_lblSerieNr.fill = GridBagConstraints.BOTH;
		gbc_lblSerieNr.weighty = 0;
		gbc_lblSerieNr.weightx = 0;
		gbc_lblSerieNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblSerieNr.gridx = 4;
		gbc_lblSerieNr.gridy = 4;
		centerLeftPanel.add(lblSerieNr, gbc_lblSerieNr);
		
		serieNrTextField = new JTextField();
		GridBagConstraints gbc_serieNrTextField = new GridBagConstraints();
		gbc_serieNrTextField.weighty = 0;
		gbc_serieNrTextField.weightx = 0;
		gbc_serieNrTextField.insets = new Insets(0, 0, 5, 5);
		gbc_serieNrTextField.fill = GridBagConstraints.BOTH;
		gbc_serieNrTextField.gridx = 5;
		gbc_serieNrTextField.gridy = 4;
		centerLeftPanel.add(serieNrTextField, gbc_serieNrTextField);
		serieNrTextField.setColumns(10);
		
		lblEndDate = new JLabel("Slutdato");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.fill = GridBagConstraints.BOTH;
		gbc_lblEndDate.weighty = 0;
		gbc_lblEndDate.weightx = 0;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 5;
		centerLeftPanel.add(lblEndDate, gbc_lblEndDate);
		
		
		lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.fill = GridBagConstraints.BOTH;
		gbc_lblType.weighty = 0;
		gbc_lblType.weightx = 0;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 7;
		centerLeftPanel.add(lblType, gbc_lblType);
		
		typeComboBox = new JComboBox();		
		typeComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Reparation", "Serviceaftale", "Vedligeholdelse"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.weighty = 0;
		gbc_comboBox_1.weightx = 0;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 7;
		centerLeftPanel.add(typeComboBox, gbc_comboBox_1);
		
		
		typeComboBox.addActionListener(e -> setMaintenanceFields());
		
		createWorkOrderButton = new JRoundedButton("Opret arbejdsordre");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 9;
		centerLeftPanel.add(createWorkOrderButton, gbc_btnNewButton);
		
		createWorkOrderButton.addActionListener(e -> createWorkOrderButtonMethod());
	}

	private void setTextFields() {

		topicTextField = new JTextField();
		GridBagConstraints gbc_topicTextField = new GridBagConstraints();
		gbc_topicTextField.weighty = 0;
		gbc_topicTextField.weightx = 0;
		gbc_topicTextField.insets = new Insets(0, 0, 5, 5);
		gbc_topicTextField.fill = GridBagConstraints.BOTH;
		gbc_topicTextField.gridx = 2;
		gbc_topicTextField.gridy = 1;
		centerLeftPanel.add(topicTextField, gbc_topicTextField);
		topicTextField.setColumns(10);
		
		txtName = new JTextField();
		txtName.setEnabled(false);
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.weighty = 0;
		gbc_txtName.weightx = 0;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.BOTH;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 3;
		centerLeftPanel.add(txtName, gbc_txtName);
		
		txtWorkOrderID = new JTextField();
		GridBagConstraints gbc_txtWorkOrderID = new GridBagConstraints();
		gbc_txtWorkOrderID.weighty = 0;
		gbc_txtWorkOrderID.weightx = 0;
		gbc_txtWorkOrderID.insets = new Insets(0, 0, 5, 5);
		gbc_txtWorkOrderID.fill = GridBagConstraints.BOTH;
		gbc_txtWorkOrderID.gridx = 5;
		gbc_txtWorkOrderID.gridy = 3;
		centerLeftPanel.add(txtWorkOrderID, gbc_txtWorkOrderID);
		txtWorkOrderID.setColumns(10);
		
		txtAssetID = new JTextField();
		txtAssetID.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkAsset();
			}
		});

		txtAssetID.setColumns(10);
		GridBagConstraints gbc_txtAssetID = new GridBagConstraints();
		gbc_txtAssetID.weighty = 0;
		gbc_txtAssetID.weightx = 0;
		gbc_txtAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_txtAssetID.fill = GridBagConstraints.BOTH;
		gbc_txtAssetID.gridx = 2;
		gbc_txtAssetID.gridy = 2;
		centerLeftPanel.add(txtAssetID, gbc_txtAssetID);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		scrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 9;
		gbc_scrollPane.gridwidth = 4;
		centerLeftPanel.add(scrollPane, gbc_scrollPane);
		
	}
	private void setSpinners() {
		
		spinnerModel = new SimpleDateFormat("dd.MM.yyyy");
		spinnerStartDate = new JSpinner();
		spinnerStartDate.setModel(new SpinnerDateModel());
		spinnerStartDate.setEditor(new JSpinner.DateEditor(spinnerStartDate, spinnerModel.toPattern()));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.weighty = 0;
		gbc_spinner.weightx = 0;
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 4;
		centerLeftPanel.add(spinnerStartDate, gbc_spinner);
		
		spinnerEndDate = new JSpinner();
		spinnerEndDate.setModel(new SpinnerDateModel());
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.weighty = 0;
		gbc_spinner_1.weightx = 0;
		gbc_spinner_1.fill = GridBagConstraints.BOTH;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 5;
		centerLeftPanel.add(spinnerEndDate, gbc_spinner_1);
		spinnerEndDate.setEditor(new JSpinner.DateEditor(spinnerStartDate, spinnerModel.toPattern()));
		
		intervalSpinner = new JSpinner();
		intervalSpinner.setModel(new SpinnerNumberModel(0, 0, 1000000000, 1));
		GridBagConstraints gbc_intervalTextField = new GridBagConstraints();
		gbc_intervalTextField.weighty = 0;
		gbc_intervalTextField.weightx = 0;
		gbc_intervalTextField.insets = new Insets(0, 0, 5, 5);
		gbc_intervalTextField.fill = GridBagConstraints.BOTH;
		gbc_intervalTextField.gridx = 5;
		gbc_intervalTextField.gridy = 8;
		centerLeftPanel.add(intervalSpinner, gbc_intervalTextField);
		
	}
	private void setComboBoxes() {
		
		comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.weighty = 0;
		gbc_comboBox_2.weightx = 0;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.gridx = 5;
		gbc_comboBox_2.gridy = 1;
		centerLeftPanel.add(comboBox_2, gbc_comboBox_2);
		
		priorityComboBox = new JComboBox();
		priorityComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Høj", "Mellem", "Lav"}));
		GridBagConstraints gbc_priorityComboBox = new GridBagConstraints();
		gbc_priorityComboBox.weighty = 0;
		gbc_priorityComboBox.weightx = 0;
		gbc_priorityComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityComboBox.fill = GridBagConstraints.BOTH;
		gbc_priorityComboBox.gridx = 5;
		gbc_priorityComboBox.gridy = 2;
		centerLeftPanel.add(priorityComboBox, gbc_priorityComboBox);
		
		templatesComboBox = new JComboBox();
		templatesComboBox.setModel(new DefaultComboBoxModel(new String[] {"2 uger", "1 måned", "3 måneder", "6 måneder", "12 måneder"}));
		GridBagConstraints gbc_templatesComboBox = new GridBagConstraints();
		gbc_templatesComboBox.weighty = 0;
		gbc_templatesComboBox.weightx = 0;
		gbc_templatesComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_templatesComboBox.fill = GridBagConstraints.BOTH;
		gbc_templatesComboBox.gridx = 5;
		gbc_templatesComboBox.gridy = 7;
		centerLeftPanel.add(templatesComboBox, gbc_templatesComboBox);
		templatesComboBox.addActionListener(e -> setIntervalFromTemplate());
	}
	private void setLabels() {
		
		lblTitle = new JLabel("Emne");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.weighty = 0;
		gbc_lblTitle.weightx = 0;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		centerLeftPanel.add(lblTitle, gbc_lblTitle);
		
		lblNewLabel_1 = new JLabel("Tildel");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.weighty = 0;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		centerLeftPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblAssetID = new JLabel("Maskine ID");
		GridBagConstraints gbc_lblAssetID = new GridBagConstraints();
		gbc_lblAssetID.fill = GridBagConstraints.BOTH;
		gbc_lblAssetID.weighty = 0;
		gbc_lblAssetID.weightx = 0;
		gbc_lblAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssetID.gridx = 1;
		gbc_lblAssetID.gridy = 2;
		centerLeftPanel.add(lblAssetID, gbc_lblAssetID);

		lblStatus = new JLabel("Prioritet");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.fill = GridBagConstraints.BOTH;
		gbc_lblStatus.weighty = 0;
		gbc_lblStatus.weightx = 0;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 4;
		gbc_lblStatus.gridy = 2;
		centerLeftPanel.add(lblStatus, gbc_lblStatus);
		
		lblName = new JLabel("Asset Navn");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.weighty = 0;
		gbc_lblName.weightx = 0;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		centerLeftPanel.add(lblName, gbc_lblName);
		
		lblRegNr = new JLabel("Reg nr.");
		GridBagConstraints gbc_lblRegNr = new GridBagConstraints();
		gbc_lblRegNr.fill = GridBagConstraints.BOTH;
		gbc_lblRegNr.weighty = 0;
		gbc_lblRegNr.weightx = 0;
		gbc_lblRegNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegNr.gridx = 4;
		gbc_lblRegNr.gridy = 3;
		centerLeftPanel.add(lblRegNr, gbc_lblRegNr);
		
		lblTemplates = new JLabel("Skabeloner");
		GridBagConstraints gbc_lblTemplates = new GridBagConstraints();
		gbc_lblTemplates.fill = GridBagConstraints.BOTH;
		gbc_lblTemplates.weighty = 0;
		gbc_lblTemplates.weightx = 0;
		gbc_lblTemplates.insets = new Insets(0, 0, 5, 5);
		gbc_lblTemplates.gridx = 4;
		gbc_lblTemplates.gridy = 7;
		centerLeftPanel.add(lblTemplates, gbc_lblTemplates);
		
		lblInterval = new JLabel("Interval");
		GridBagConstraints gbc_lblInterval = new GridBagConstraints();
		gbc_lblInterval.fill = GridBagConstraints.BOTH;
		gbc_lblInterval.weighty = 0;
		gbc_lblInterval.weightx = 0;
		gbc_lblInterval.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterval.gridx = 4;
		gbc_lblInterval.gridy = 8;
		centerLeftPanel.add(lblInterval, gbc_lblInterval);
		
		lblDescription = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 9;
		centerLeftPanel.add(lblDescription, gbc_lblDescription);
		
	}

}
