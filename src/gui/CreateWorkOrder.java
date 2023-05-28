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
import java.util.function.BiConsumer;
import java.util.function.Function;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;

import controller.AssetController;
import controller.MaintenanceController;
import controller.RepairController;
import controller.ServiceController;
import dao.Database;
import gui.components.GUIPopUpMessages;
import gui.components.JRoundedButton;
import model.Asset;
import model.Employee;
import model.Reference;
import java.awt.event.ActionListener;


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
	private String assetID;
	private String referenceCVR;
	private Reference reference;
	private String employeeID;
	private Employee employee;
	private JRoundedButton btnFindEmployee_1;
	private JLabel referenceLabel;
	private JTextField employeeTextField;
	private JTextField referenceTextField;
	private JLabel repeatedLabel;
	private JCheckBox repeatedCheckBox;
	
	//Controller
	MaintenanceController maintenanceController;
	RepairController repairController;
	ServiceController serviceController;

	/**
	 * Create the panel.
	 */
	public CreateWorkOrder() {
		maintenanceController = new MaintenanceController();
		repairController = new RepairController();
		serviceController = new ServiceController();
		setLayout(new BorderLayout(0, 0));
		this.setFocusable(true);
		this.setName("Opret Arbejdsodre");
		setPanels();
		setButtons();
		setTextFields();
		setLabels();
		setSpinners();
		setComboBoxes();
		setMaintenanceFields();	
		reference = new Reference();
	}
	
	// The above code is a constructor method for a class that creates a work order for a given asset. It
	// takes in an instance of the Asset class as a parameter and sets it as the current asset for the
	// work order. It also initializes some variables and calls a method called setupTextFields().
	public CreateWorkOrder(Asset currentAsset) {
		this();
		asset = currentAsset;
		assetID = Integer.toString(currentAsset.getAssetID());
		setupTextFields();
	}
	
	/**
	 * The function sets up text fields for an asset by disabling them and populating them with the
	 * asset's ID and name.
	 */
	private void setupTextFields() {
		txtAssetID.setEnabled(false);
		txtName.setEnabled(false);
		txtAssetID.setText(Integer.toString((asset.getAssetID())));
		txtName.setText(asset.getName());
	}
	
	/**
	 * This function creates a work order and checks if an asset and reference are selected before calling
	 * a controller of a certain type.
	 * 
	 * @return The method is returning a boolean value, which is either true or false depending on the
	 * success of calling the controller of a certain type.
	 */
	private boolean createWorkOrder() {
		if(asset == null) {
			GUIPopUpMessages.warningMessage("No Asset selected!", "Error!"); return false;
		}
		String type = typeComboBox.getSelectedItem().toString();
		if(!type.equals("Vedligeholdelse") && reference.getCvr() == 0) {
			GUIPopUpMessages.warningMessage("No Reference selected!", "Error!");
			return false;
		}
		boolean success = callControllerOfType(type);
	    return success;
	}
	
	/**
	 * This function creates a work order of a specific type (repair, service, or maintenance) based on
	 * user input and returns a boolean indicating success or failure.
	 * 
	 * @param type A string representing the type of work order to be created (either "Reparation",
	 * "Serviceaftale", or "Vedligeholdelse").
	 * @return The method is returning a boolean value.
	 */
	private boolean callControllerOfType(String type) {
		int intAssetID = Integer.valueOf(assetID);
		String topic = topicTextField.getText();
		Calendar startDate = Calendar.getInstance();
		startDate.setTime((Date) spinnerStartDate.getValue());
		short priority = getPriorityFromComboBox();
		String description = textArea.getText();
		
	    switch(type) {
	    
	    	//Creating WorkOrder of type Repair/Reperation
	        case "Reparation":
			try {
				return repairController.createWorkOrder(intAssetID, topic, startDate, priority, description,
														Integer.valueOf(referenceCVR));
				
			} catch (NumberFormatException e2) {} catch (SQLException e2) {}
			
			//Creating WorkOrder of type Service/Serviceaftale
	        case "Serviceaftale":
			try {
				return serviceController.createWorkOrder(intAssetID, topic, startDate, priority, description,
	            										Integer.valueOf(referenceCVR));
				
			} catch (NumberFormatException e1) {} catch (SQLException e1) {}
			
			//Creating WorkOrder of type Maintenance/Vedligeholdelse
	        case "Vedligeholdelse":
			try {
				return maintenanceController.createWorkOrder(intAssetID, topic, startDate, priority, description,
	            											(int) intervalSpinner.getValue(),
	            											repeatedCheckBox.isSelected());
				
			} catch (SQLException e) {}
			
			//If no type has been picked fail(return false)
	        default:
	            return false;
	    }
	}
	
	/**
	 * This function returns a short value representing the priority selected from a combo box.
	 * 
	 * @return The method is returning a short data type, which represents the priority level based on the
	 * selected item in the priorityComboBox. The value returned depends on the selected item: 3 for
	 * "Høj", 2 for "Mellem", and 1 for any other value.
	 */
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

	
	/**
	 * The function sets the value of a spinner based on the selected item in a combo box.
	 */
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
	/**
	 * A generic method to check an entity based on user input.
	 *
	 * @param <T> The type of the entity.
	 * @param textField The text field to check.
	 * @param storedId The stored ID to compare the input to.
	 * @param fetcher A function that fetches the entity based on an integer ID.
	 * @param setter A consumer that sets the entity and storedId.
	 * @param errorMsg The error message to display if a NumberFormatException occurs.
	 */
	private <T> void checkEntity(JTextField textField, String storedId, Function<Integer, T> fetcher, BiConsumer<T, Integer> setter, String errorMsg) {
	    if (textField.getText().length() > 0) {
	        if (!textField.getText().equals(storedId)) {
	            storedId = textField.getText();
	            int id = Integer.parseInt(textField.getText());
	            Thread workerThread = new Thread(() -> {
	                T entityCheck = null;
	                try {
	                    entityCheck = fetcher.apply(id);
	                } catch (NumberFormatException e1) {
	                    GUIPopUpMessages.warningMessage(errorMsg, "Error!");
	                }
	                if (entityCheck == null) {
	                    textField.setBackground(Color.red);
	                } else {
	                    textField.setBackground(Color.green);
	                    setter.accept(entityCheck, id);
	                }
	            });
	            workerThread.start();
	        }
	    }
	}

	/**
	 * Checks the asset id field for a valid Asset
	 */
	private void checkAsset() {
	    checkEntity(txtAssetID, assetID, id -> {
	        try {
	            return new AssetController().findAssetByID(id);
	        } catch (SQLException e1) {
	            GUIPopUpMessages.warningMessage("Error retrieving data from database", "Error!");
	            return null;
	        }
	    }, (assetCheck, newAssetID) -> {
	        txtName.setText(assetCheck.getName());
	        asset = assetCheck;
	        assetID = newAssetID.toString();
	    }, "Only Number in AssetID Field");
	}

	/**
	 * Checks the reference text field for a valid Reference
	 */
	private void checkReference() {
	    checkEntity(referenceTextField, referenceCVR, id -> Database.getInstance().getReferenceDataBase().findReferenceByID(id), (referenceCheck, newReferenceCVR) -> {
	        reference = referenceCheck;
	        reference.setCvr(newReferenceCVR);
	        referenceCVR = newReferenceCVR.toString();
	    }, "Only Number in reference Field");
	}

	/**
	 * Checks the employee text field for a valid Employee
	 */
	private void checkEmployee() {
	    checkEntity(employeeTextField, employeeID, id -> Database.getInstance().getEmployeeDataBase().findEmployeeByID(id), (employeeCheck, newEmployeeID) -> {
	        employee = employeeCheck;
	        employee.setCprNumber(newEmployeeID.toString());
	        employeeID = newEmployeeID.toString();
	    }, "Only Number in employee Field");
	}


	/**
	 * This function enables or disables certain fields based on the selected item in a combo box.
	 */
	private void setMaintenanceFields() {
		if(typeComboBox.getSelectedItem().toString().equals("Vedligeholdelse")) {
			intervalSpinner.setEnabled(true);
			templatesComboBox.setEnabled(true);
			lblInterval.setEnabled(true);
			lblTemplates.setEnabled(true);
			repeatedLabel.setEnabled(true);
			repeatedCheckBox.setEnabled(true);
		}else {
			intervalSpinner.setEnabled(false);
			templatesComboBox.setEnabled(false);
			lblInterval.setEnabled(false);
			lblTemplates.setEnabled(false);
			repeatedLabel.setEnabled(false);
			repeatedCheckBox.setEnabled(false);
		}
	}
	
	/**
	 * This function creates a new thread to execute the "createWorkOrder" method and displays a success
	 * message if the method returns true.
	 */
	private void createWorkOrderButtonMethod() {
		Thread workerThread = new Thread(() -> {
		    boolean success = createWorkOrder();
		    if(success) {
		    	resetTextFields();
		    	resetSpinners();
		    	resetComboboxes();
		    	GUIPopUpMessages.informationMessage("Opgaven er blevet oprettet", "Success!");
		    }
		});
		workerThread.start();
	}
	
	/**
	 * The function resets the values and background colors of several text fields.
	 */
	private void resetTextFields() {
		this.txtAssetID.setText("");
		this.txtAssetID.setBackground(Color.white);
		this.txtName.setText("");
		this.txtWorkOrderID.setText("");
		this.employeeTextField.setText("");
		this.employeeTextField.setBackground(Color.white);
		this.referenceTextField.setText("");
		this.referenceTextField.setBackground(Color.white);
		this.serieNrTextField.setText("");
		this.topicTextField.setText("");
		this.textArea.setText("");
	}
	
	/**
	 * The function resets spinners by calling the setSpinners() function.
	 */
	private void resetSpinners() {
		setSpinners();
	}
	
	/**
	 * The function resets the selected index of three different comboboxes to zero.
	 */
	private void resetComboboxes() {
		priorityComboBox.setSelectedIndex(0);
		typeComboBox.setSelectedIndex(0);
		templatesComboBox.setSelectedIndex(0);
	}

	/**
	 * This function sets up a JPanel with a GridBagLayout and adds it to the center of the main panel.
	 */
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

	/**
	 * This function sets up the GUI components for creating a work order, including text fields, buttons,
	 * and labels.
	 */
	private void setButtons() {
		employeeTextField = new JTextField();
		employeeTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkEmployee();
			}
		});
		GridBagConstraints gbc_employeeTextField = new GridBagConstraints();
		gbc_employeeTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_employeeTextField.insets = new Insets(0, 0, 5, 5);
		gbc_employeeTextField.gridx = 5;
		gbc_employeeTextField.gridy = 1;
		centerLeftPanel.add(employeeTextField, gbc_employeeTextField);
		employeeTextField.setColumns(10);
		
		btnFindEmployee = new JRoundedButton("Find medarbejder");
		GridBagConstraints gbc_btnFindEmployee = new GridBagConstraints();
		gbc_btnFindEmployee.fill = GridBagConstraints.BOTH;
		gbc_btnFindEmployee.weighty = 0;
		gbc_btnFindEmployee.weightx = 0;
		gbc_btnFindEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindEmployee.gridx = 6;
		gbc_btnFindEmployee.gridy = 1;
		centerLeftPanel.add(btnFindEmployee, gbc_btnFindEmployee);
		
		referenceLabel = new JLabel("Reference");
		GridBagConstraints gbc_referenceLabel = new GridBagConstraints();
		gbc_referenceLabel.anchor = GridBagConstraints.WEST;
		gbc_referenceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_referenceLabel.gridx = 4;
		gbc_referenceLabel.gridy = 2;
		centerLeftPanel.add(referenceLabel, gbc_referenceLabel);
		
		referenceTextField = new JTextField();
		referenceTextField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkReference();
			}
		});
		GridBagConstraints gbc_referenceTextField = new GridBagConstraints();
		gbc_referenceTextField.fill = GridBagConstraints.HORIZONTAL;
		gbc_referenceTextField.insets = new Insets(0, 0, 5, 5);
		gbc_referenceTextField.gridx = 5;
		gbc_referenceTextField.gridy = 2;
		centerLeftPanel.add(referenceTextField, gbc_referenceTextField);
		referenceTextField.setColumns(10);
		
		btnFindEmployee_1 = new JRoundedButton("Find Reference");
		GridBagConstraints gbc_btnFindEmployee_1 = new GridBagConstraints();
		gbc_btnFindEmployee_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFindEmployee_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindEmployee_1.gridx = 6;
		gbc_btnFindEmployee_1.gridy = 2;
		centerLeftPanel.add(btnFindEmployee_1, gbc_btnFindEmployee_1);
		
				lblStatus = new JLabel("Prioritet");
				GridBagConstraints gbc_lblStatus = new GridBagConstraints();
				gbc_lblStatus.fill = GridBagConstraints.BOTH;
				gbc_lblStatus.weighty = 0;
				gbc_lblStatus.weightx = 0;
				gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
				gbc_lblStatus.gridx = 4;
				gbc_lblStatus.gridy = 3;
				centerLeftPanel.add(lblStatus, gbc_lblStatus);
		
		priorityComboBox = new JComboBox();
		priorityComboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Høj", "Mellem", "Lav"}));
		GridBagConstraints gbc_priorityComboBox = new GridBagConstraints();
		gbc_priorityComboBox.weighty = 0;
		gbc_priorityComboBox.weightx = 0;
		gbc_priorityComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_priorityComboBox.fill = GridBagConstraints.BOTH;
		gbc_priorityComboBox.gridx = 5;
		gbc_priorityComboBox.gridy = 3;
		centerLeftPanel.add(priorityComboBox, gbc_priorityComboBox);
		
		lblStartDate = new JLabel("Startdato");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.fill = GridBagConstraints.BOTH;
		gbc_lblStartDate.weighty = 0;
		gbc_lblStartDate.weightx = 0;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 4;
		centerLeftPanel.add(lblStartDate, gbc_lblStartDate);
		
		lblRegNr = new JLabel("Reg nr.");
		GridBagConstraints gbc_lblRegNr = new GridBagConstraints();
		gbc_lblRegNr.fill = GridBagConstraints.BOTH;
		gbc_lblRegNr.weighty = 0;
		gbc_lblRegNr.weightx = 0;
		gbc_lblRegNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblRegNr.gridx = 4;
		gbc_lblRegNr.gridy = 4;
		centerLeftPanel.add(lblRegNr, gbc_lblRegNr);
		
		txtWorkOrderID = new JTextField();
		txtWorkOrderID.setEnabled(false);
		txtWorkOrderID.setText("ikke implementeret");
		GridBagConstraints gbc_txtWorkOrderID = new GridBagConstraints();
		gbc_txtWorkOrderID.weighty = 0;
		gbc_txtWorkOrderID.weightx = 0;
		gbc_txtWorkOrderID.insets = new Insets(0, 0, 5, 5);
		gbc_txtWorkOrderID.fill = GridBagConstraints.BOTH;
		gbc_txtWorkOrderID.gridx = 5;
		gbc_txtWorkOrderID.gridy = 4;
		centerLeftPanel.add(txtWorkOrderID, gbc_txtWorkOrderID);
		txtWorkOrderID.setColumns(10);
		
		lblEndDate = new JLabel("Slutdato");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.fill = GridBagConstraints.BOTH;
		gbc_lblEndDate.weighty = 0;
		gbc_lblEndDate.weightx = 0;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 5;
		centerLeftPanel.add(lblEndDate, gbc_lblEndDate);
		
		
		lblSerieNr = new JLabel("Serienr.");
		GridBagConstraints gbc_lblSerieNr = new GridBagConstraints();
		gbc_lblSerieNr.fill = GridBagConstraints.BOTH;
		gbc_lblSerieNr.weighty = 0;
		gbc_lblSerieNr.weightx = 0;
		gbc_lblSerieNr.insets = new Insets(0, 0, 5, 5);
		gbc_lblSerieNr.gridx = 4;
		gbc_lblSerieNr.gridy = 5;
		centerLeftPanel.add(lblSerieNr, gbc_lblSerieNr);
		
		serieNrTextField = new JTextField();
		serieNrTextField.setEnabled(false);
		serieNrTextField.setText("ikke implementeret");
		GridBagConstraints gbc_serieNrTextField = new GridBagConstraints();
		gbc_serieNrTextField.weighty = 0;
		gbc_serieNrTextField.weightx = 0;
		gbc_serieNrTextField.insets = new Insets(0, 0, 5, 5);
		gbc_serieNrTextField.fill = GridBagConstraints.BOTH;
		gbc_serieNrTextField.gridx = 5;
		gbc_serieNrTextField.gridy = 5;
		centerLeftPanel.add(serieNrTextField, gbc_serieNrTextField);
		serieNrTextField.setColumns(10);
		
		
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
		
		repeatedLabel = new JLabel("Repeated");
		GridBagConstraints gbc_repeatedLabel = new GridBagConstraints();
		gbc_repeatedLabel.anchor = GridBagConstraints.WEST;
		gbc_repeatedLabel.insets = new Insets(0, 0, 5, 5);
		gbc_repeatedLabel.gridx = 1;
		gbc_repeatedLabel.gridy = 8;
		centerLeftPanel.add(repeatedLabel, gbc_repeatedLabel);
		
		repeatedCheckBox = new JCheckBox("");
		GridBagConstraints gbc_repeatedCheckBox = new GridBagConstraints();
		gbc_repeatedCheckBox.anchor = GridBagConstraints.WEST;
		gbc_repeatedCheckBox.insets = new Insets(0, 0, 5, 5);
		gbc_repeatedCheckBox.gridx = 2;
		gbc_repeatedCheckBox.gridy = 8;
		centerLeftPanel.add(repeatedCheckBox, gbc_repeatedCheckBox);
		
		createWorkOrderButton = new JRoundedButton("Opret arbejdsordre");
//		createWorkOrderButton.addActionListener(new ActionListener() {
//		});

		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 9;
		centerLeftPanel.add(createWorkOrderButton, gbc_btnNewButton);
		
		createWorkOrderButton.addActionListener(e -> createWorkOrderButtonMethod());
	}

	/**
	 * This function sets up various text fields and a text area with specific constraints in a Java GUI.
	 */
	private void setTextFields() {

		topicTextField = new JTextField();
		GridBagConstraints gbc_topicTextField = new GridBagConstraints();
		gbc_topicTextField.weighty = 0;
		gbc_topicTextField.weightx = 0;
		gbc_topicTextField.insets = new Insets(0, 0, 5, 5);
		gbc_topicTextField.fill = GridBagConstraints.HORIZONTAL;
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
		gbc_txtAssetID.fill = GridBagConstraints.HORIZONTAL;
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

	/**
	 * This function sets up JSpinners for selecting start and end dates and an interval.
	 */
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
		spinnerEndDate.setEnabled(false);
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

	/**
	 * This function sets up a JComboBox with predefined options and adds it to a panel.
	 */
	private void setComboBoxes() {
		
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

	/**
	 * This function sets the labels for a GUI interface using GridBagConstraints.
	 */
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
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
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
		
		lblName = new JLabel("Asset Navn");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.fill = GridBagConstraints.BOTH;
		gbc_lblName.weighty = 0;
		gbc_lblName.weightx = 0;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 1;
		gbc_lblName.gridy = 3;
		centerLeftPanel.add(lblName, gbc_lblName);
		
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
