package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;

import gui.components.DefaultTable;
import gui.components.JRoundedButton;
import model.Asset;

public class ReadAsset extends JPanel {
	
	//TextFields
	private JTextField txtTitle;
	private JTextField txtInterval;
	private JTextField txtAssetID;
	private JTextField txtName;
	private JTextField txtSerialNumber;
	private JTextField txtType;
	private JTextField txtRegNo;
	private JTextField txtPriority;
	private JTextField txtEmployeeID;
	
	//Button
	private JRoundedButton btnNewWorkOrder;
	private JRoundedButton btnDelete;
	private JRoundedButton btnAdd;
	
	//Tables
	private JTable test;
	private JScrollPane scrollPane;
	private JScrollPane historyScollPane;
	private DefaultTable historyTable;
	
	//Labels
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JLabel lblInterval;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_2;
	private JLabel lblAssetID;
	private JLabel lblTitle;
	private JLabel lblNewLabel;
	
	//Panels
	private JPanel centerPanel;
	private JPanel southPanel;
	
	//Extra
	private Asset currentAsset;
	private JTextArea textAreaDescription;
	private JSpinner spinnerDate;
	private JCheckBox chckDateBox;
	private MainFrame mainFrame;
	

	/**
	 * Create the panel.
	 */
	public ReadAsset(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		setLayout(new BorderLayout(0, 0));

		setPanels();
		setLabelsAndTextFields();
		setTables();
		setSpinnerAndComboBox();
		setButtons();

	}

	public void createNewWorkOrder() {
		mainFrame.setNewCenterPanel(new CreateWorkOrder(this.currentAsset));
	}
	
	public void initialize(Asset currentAsset) {
		this.currentAsset = currentAsset;
		txtAssetID.setText(Integer.toString(currentAsset.getAssetID()));
		txtName.setText(currentAsset.getName());
		textAreaDescription.setText(currentAsset.getDescription());


		// Change text color to gray
		txtAssetID.setDisabledTextColor(Color.gray);
		txtName.setDisabledTextColor(Color.gray);
		textAreaDescription.setDisabledTextColor(Color.gray);
		txtSerialNumber.setDisabledTextColor(Color.gray);
		txtTitle.setDisabledTextColor(Color.gray);
		txtInterval.setDisabledTextColor(Color.gray);
		txtType.setDisabledTextColor(Color.gray);
		txtRegNo.setDisabledTextColor(Color.gray);
		txtPriority.setDisabledTextColor(Color.gray);
		txtEmployeeID.setDisabledTextColor(Color.gray);
		spinnerDate.setForeground(Color.gray);
		chckDateBox.setForeground(Color.gray);

		// Disable editing of text
		txtAssetID.setEnabled(false);
		txtName.setEnabled(false);
		textAreaDescription.setEnabled(false);
		txtSerialNumber.setEnabled(false);
		txtTitle.setEnabled(false);
		txtInterval.setEnabled(false);
		txtType.setEnabled(false);
		txtRegNo.setEnabled(false);
		txtPriority.setEnabled(false);
		txtEmployeeID.setEnabled(false);
		spinnerDate.setEnabled(false);
		chckDateBox.setEnabled(false);
	}
	public DefaultTable getHistoryTable() {
		return historyTable;
	}
	private void setButtons() {
		btnNewWorkOrder = new JRoundedButton("Opret ny arbejdsordre");
		btnNewWorkOrder.addActionListener(e -> createNewWorkOrder());
		southPanel.add(btnNewWorkOrder);
		
		btnAdd = new JRoundedButton("Tilføj ny");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 9;
		gbc_btnAdd.gridy = 9;
		centerPanel.add(btnAdd, gbc_btnAdd);

		btnDelete = new JRoundedButton("Slet");
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnDelete.gridx = 9;
		gbc_btnDelete.gridy = 10;
		centerPanel.add(btnDelete, gbc_btnDelete);
		
	}

	private void setSpinnerAndComboBox() {
		chckDateBox = new JCheckBox("Lukket dato");
		chckDateBox.setHorizontalAlignment(SwingConstants.TRAILING);
		chckDateBox.setSelected(true);
		GridBagConstraints gbc_chckDateBox = new GridBagConstraints();
		gbc_chckDateBox.anchor = GridBagConstraints.WEST;
		gbc_chckDateBox.insets = new Insets(0, 0, 5, 5);
		gbc_chckDateBox.gridx = 7;
		gbc_chckDateBox.gridy = 6;
		centerPanel.add(chckDateBox, gbc_chckDateBox);

		SimpleDateFormat spinnerModel = new SimpleDateFormat("dd.MM.yyyy");
		spinnerDate = new JSpinner();
		spinnerDate.setModel(new SpinnerDateModel());
		spinnerDate.setEditor(new JSpinner.DateEditor(spinnerDate, spinnerModel.toPattern()));
		GridBagConstraints gbc_spinnerDate = new GridBagConstraints();
		gbc_spinnerDate.fill = GridBagConstraints.HORIZONTAL;
		gbc_spinnerDate.insets = new Insets(0, 0, 5, 5);
		gbc_spinnerDate.gridx = 8;
		gbc_spinnerDate.gridy = 6;
		centerPanel.add(spinnerDate, gbc_spinnerDate);
		
	}

	private void setTables() {
		
		historyScollPane = new JScrollPane();
		historyScollPane.setPreferredSize(new Dimension(10, 0)); // Changes size of table
		GridBagConstraints gbc_historyScollPane = new GridBagConstraints();
		gbc_historyScollPane.gridheight = 5;
		gbc_historyScollPane.gridwidth = 2;
		gbc_historyScollPane.insets = new Insets(0, 0, 5, 5);
		gbc_historyScollPane.fill = GridBagConstraints.BOTH;
		gbc_historyScollPane.gridx = 1;
		gbc_historyScollPane.gridy = 7;
		centerPanel.add(historyScollPane, gbc_historyScollPane);
		
		String[] columnsHistory = { "WorkOrderID", "Emne", "Type", "Start Dato", "Slut Dato", "Prioritet", "Beskrivelse", "Færdig", "AssetID", "Medarbejder"};
		boolean[] activeColumns = new boolean[] { true, true, false, true, false, false, false, false, false, true };
		historyTable = new DefaultTable(null, columnsHistory, activeColumns);
		historyScollPane.setViewportView(historyTable);
		
		test = new JTable();
		scrollPane = new JScrollPane(test);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridheight = 3;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 4;
		gbc_scrollPane.gridy = 9;
		centerPanel.add(scrollPane, gbc_scrollPane);

		// TODO update completedActionsTable
		String[] columnsCompletedActions = new String[] { "Reg. af", "Dato", "Bemærkning" };
		DefaultTable completedActionsTable = new DefaultTable(null, columnsCompletedActions);
		scrollPane.setViewportView(completedActionsTable);
		
	}

	

	private void setPanels() {
		southPanel = new JPanel();
		FlowLayout fl_southPanel = (FlowLayout) southPanel.getLayout();
		fl_southPanel.setAlignment(FlowLayout.RIGHT);
		add(southPanel, BorderLayout.SOUTH);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.NORTH);
		GridBagLayout gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 0, 0, 101, 39, 55, 101, 59, 88, 0, 0, 0 };
		gbl_centerPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0,
				Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0,
				Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);
		
	}

	
	

	
	private void setLabelsAndTextFields() {
		lblNewLabel = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 4;
		gbc_lblNewLabel.gridy = 1;
		centerPanel.add(lblNewLabel, gbc_lblNewLabel);

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
		GridBagConstraints gbc_txtAssetID = new GridBagConstraints();
		gbc_txtAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_txtAssetID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtAssetID.gridx = 2;
		gbc_txtAssetID.gridy = 3;
		centerPanel.add(txtAssetID, gbc_txtAssetID);

		textAreaDescription = new JTextArea();
		textAreaDescription.setWrapStyleWord(true);
		textAreaDescription.setLineWrap(true);
		JScrollPane descriptionScrollPane = new JScrollPane(textAreaDescription);
		GridBagConstraints gbc_descriptionScrollPane = new GridBagConstraints();
		gbc_descriptionScrollPane.gridheight = 3;
		gbc_descriptionScrollPane.gridwidth = 5;
		gbc_descriptionScrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_descriptionScrollPane.fill = GridBagConstraints.BOTH;
		gbc_descriptionScrollPane.gridx = 4;
		gbc_descriptionScrollPane.gridy = 2;
		centerPanel.add(descriptionScrollPane, gbc_descriptionScrollPane);
		
		lblNewLabel_2 = new JLabel("Navn");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 4;
		centerPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);

		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 4;
		centerPanel.add(txtName, gbc_txtName);

		lblNewLabel_3 = new JLabel("Serienr.");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 5;
		centerPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);

		txtSerialNumber = new JTextField();
		GridBagConstraints gbc_txtSerialNumber = new GridBagConstraints();
		gbc_txtSerialNumber.insets = new Insets(0, 0, 5, 5);
		gbc_txtSerialNumber.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSerialNumber.gridx = 2;
		gbc_txtSerialNumber.gridy = 5;
		centerPanel.add(txtSerialNumber, gbc_txtSerialNumber);
		txtSerialNumber.setColumns(10);

		lblInterval = new JLabel("Interval");
		GridBagConstraints gbc_lblInterval = new GridBagConstraints();
		gbc_lblInterval.anchor = GridBagConstraints.WEST;
		gbc_lblInterval.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterval.gridx = 4;
		gbc_lblInterval.gridy = 5;
		centerPanel.add(lblInterval, gbc_lblInterval);

		txtInterval = new JTextField();
		GridBagConstraints gbc_txtInterval = new GridBagConstraints();
		gbc_txtInterval.insets = new Insets(0, 0, 5, 5);
		gbc_txtInterval.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtInterval.gridx = 5;
		gbc_txtInterval.gridy = 5;
		centerPanel.add(txtInterval, gbc_txtInterval);
		txtInterval.setColumns(10);

		lblNewLabel_6 = new JLabel("Type");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 7;
		gbc_lblNewLabel_6.gridy = 5;
		centerPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);

		txtType = new JTextField();
		GridBagConstraints gbc_txtType = new GridBagConstraints();
		gbc_txtType.insets = new Insets(0, 0, 5, 5);
		gbc_txtType.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtType.gridx = 8;
		gbc_txtType.gridy = 5;
		centerPanel.add(txtType, gbc_txtType);
		txtType.setColumns(10);

		lblNewLabel_4 = new JLabel("Historik");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.gridwidth = 2;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 1;
		gbc_lblNewLabel_4.gridy = 6;
		centerPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);

		lblNewLabel_7 = new JLabel("Reg nr.");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 6;
		centerPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);

		txtRegNo = new JTextField();
		GridBagConstraints gbc_txtRegNo = new GridBagConstraints();
		gbc_txtRegNo.insets = new Insets(0, 0, 5, 5);
		gbc_txtRegNo.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtRegNo.gridx = 5;
		gbc_txtRegNo.gridy = 6;
		centerPanel.add(txtRegNo, gbc_txtRegNo);
		txtRegNo.setColumns(10);
		
		lblNewLabel_8 = new JLabel("Prioritering");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 4;
		gbc_lblNewLabel_8.gridy = 7;
		centerPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);

		txtPriority = new JTextField();
		GridBagConstraints gbc_txtPriority = new GridBagConstraints();
		gbc_txtPriority.insets = new Insets(0, 0, 5, 5);
		gbc_txtPriority.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtPriority.gridx = 5;
		gbc_txtPriority.gridy = 7;
		centerPanel.add(txtPriority, gbc_txtPriority);
		txtPriority.setColumns(10);

		lblNewLabel_1 = new JLabel("Lukket af");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 7;
		gbc_lblNewLabel_1.gridy = 7;
		centerPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

		txtEmployeeID = new JTextField();
		GridBagConstraints gbc_txtEmployeeID = new GridBagConstraints();
		gbc_txtEmployeeID.insets = new Insets(0, 0, 5, 5);
		gbc_txtEmployeeID.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtEmployeeID.gridx = 8;
		gbc_txtEmployeeID.gridy = 7;
		centerPanel.add(txtEmployeeID, gbc_txtEmployeeID);
		txtEmployeeID.setColumns(10);

		lblNewLabel_5 = new JLabel("Aktioner udført");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.gridwidth = 5;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 8;
		centerPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
	}
}