package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

import gui.components.JRoundedButton;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class WorkOrder extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField txtAssetID;
	private JTextField txtName;
	private JTextField textField_2;
	private JTextField txtWorkOrderID;

	/**
	 * Create the panel.
	 */
	public WorkOrder() {
setLayout(new BorderLayout(0, 0));
		
		JPanel centerLeftPanel = new JPanel();
		add(centerLeftPanel, BorderLayout.CENTER);
		GridBagLayout gbl_centerLeftPanel = new GridBagLayout();
		gbl_centerLeftPanel.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_centerLeftPanel.rowHeights = new int[] {30, 0, 0, 0, 0, 30, 0, 0, 0, 101, 0, 0};
		gbl_centerLeftPanel.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_centerLeftPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		centerLeftPanel.setLayout(gbl_centerLeftPanel);
		
		JLabel lblTitle = new JLabel("Emne");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.weighty = 0;
		gbc_lblTitle.weightx = 0;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 1;
		centerLeftPanel.add(lblTitle, gbc_lblTitle);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.weighty = 0;
		gbc_textField.weightx = 0;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		centerLeftPanel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tildel");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_1.weighty = 0;
		gbc_lblNewLabel_1.weightx = 0;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 4;
		gbc_lblNewLabel_1.gridy = 1;
		centerLeftPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox comboBox_2 = new JComboBox();
		GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
		gbc_comboBox_2.weighty = 0;
		gbc_comboBox_2.weightx = 0;
		gbc_comboBox_2.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_2.fill = GridBagConstraints.BOTH;
		gbc_comboBox_2.gridx = 5;
		gbc_comboBox_2.gridy = 1;
		centerLeftPanel.add(comboBox_2, gbc_comboBox_2);
		
		JButton btnFindEmployee = new JRoundedButton("Find medarbejder");
		GridBagConstraints gbc_btnFindEmployee = new GridBagConstraints();
		gbc_btnFindEmployee.fill = GridBagConstraints.BOTH;
		gbc_btnFindEmployee.weighty = 0;
		gbc_btnFindEmployee.weightx = 0;
		gbc_btnFindEmployee.insets = new Insets(0, 0, 5, 5);
		gbc_btnFindEmployee.gridx = 6;
		gbc_btnFindEmployee.gridy = 1;
		centerLeftPanel.add(btnFindEmployee, gbc_btnFindEmployee);
		
		JLabel lblAssetID = new JLabel("Maskine ID");
		GridBagConstraints gbc_lblAssetID = new GridBagConstraints();
		gbc_lblAssetID.fill = GridBagConstraints.BOTH;
		gbc_lblAssetID.weighty = 0;
		gbc_lblAssetID.weightx = 0;
		gbc_lblAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_lblAssetID.gridx = 1;
		gbc_lblAssetID.gridy = 2;
		centerLeftPanel.add(lblAssetID, gbc_lblAssetID);
		
		txtAssetID = new JTextField();
		txtAssetID.setColumns(10);
		GridBagConstraints gbc_txtAssetID = new GridBagConstraints();
		gbc_txtAssetID.weighty = 0;
		gbc_txtAssetID.weightx = 0;
		gbc_txtAssetID.insets = new Insets(0, 0, 5, 5);
		gbc_txtAssetID.fill = GridBagConstraints.BOTH;
		gbc_txtAssetID.gridx = 2;
		gbc_txtAssetID.gridy = 2;
		centerLeftPanel.add(txtAssetID, gbc_txtAssetID);
		SimpleDateFormat spinnerModel = new SimpleDateFormat("dd.MM.yyyy");
		
		JLabel lblNewLabel_5 = new JLabel("Skabeloner");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_5.weighty = 0;
		gbc_lblNewLabel_5.weightx = 0;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 2;
		centerLeftPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"2 uger", "1 måned", "3 måneder", "6 måneder", "12 måneder", "Andet"}));
		GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
		gbc_comboBox_3.weighty = 0;
		gbc_comboBox_3.weightx = 0;
		gbc_comboBox_3.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_3.fill = GridBagConstraints.BOTH;
		gbc_comboBox_3.gridx = 5;
		gbc_comboBox_3.gridy = 2;
		centerLeftPanel.add(comboBox_3, gbc_comboBox_3);
		
		JLabel lblNewLabel_2 = new JLabel("Navn");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_2.weighty = 0;
		gbc_lblNewLabel_2.weightx = 0;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 1;
		gbc_lblNewLabel_2.gridy = 3;
		centerLeftPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		GridBagConstraints gbc_txtName = new GridBagConstraints();
		gbc_txtName.weighty = 0;
		gbc_txtName.weightx = 0;
		gbc_txtName.insets = new Insets(0, 0, 5, 5);
		gbc_txtName.fill = GridBagConstraints.BOTH;
		gbc_txtName.gridx = 2;
		gbc_txtName.gridy = 3;
		centerLeftPanel.add(txtName, gbc_txtName);
		
		JLabel lblInterval = new JLabel("Interval");
		GridBagConstraints gbc_lblInterval = new GridBagConstraints();
		gbc_lblInterval.fill = GridBagConstraints.BOTH;
		gbc_lblInterval.weighty = 0;
		gbc_lblInterval.weightx = 0;
		gbc_lblInterval.insets = new Insets(0, 0, 5, 5);
		gbc_lblInterval.gridx = 4;
		gbc_lblInterval.gridy = 3;
		centerLeftPanel.add(lblInterval, gbc_lblInterval);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.weighty = 0;
		gbc_textField_1.weightx = 0;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.BOTH;
		gbc_textField_1.gridx = 5;
		gbc_textField_1.gridy = 3;
		centerLeftPanel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Serienr.");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_3.weighty = 0;
		gbc_lblNewLabel_3.weightx = 0;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 1;
		gbc_lblNewLabel_3.gridy = 4;
		centerLeftPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		textField_2 = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.weighty = 0;
		gbc_textField_2.weightx = 0;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.fill = GridBagConstraints.BOTH;
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 4;
		centerLeftPanel.add(textField_2, gbc_textField_2);
		textField_2.setColumns(10);
		
		JLabel lblStartDate = new JLabel("Startdato");
		GridBagConstraints gbc_lblStartDate = new GridBagConstraints();
		gbc_lblStartDate.fill = GridBagConstraints.BOTH;
		gbc_lblStartDate.weighty = 0;
		gbc_lblStartDate.weightx = 0;
		gbc_lblStartDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblStartDate.gridx = 1;
		gbc_lblStartDate.gridy = 6;
		centerLeftPanel.add(lblStartDate, gbc_lblStartDate);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerDateModel());
		spinner.setEditor(new JSpinner.DateEditor(spinner, spinnerModel.toPattern()));
		GridBagConstraints gbc_spinner = new GridBagConstraints();
		gbc_spinner.weighty = 0;
		gbc_spinner.weightx = 0;
		gbc_spinner.fill = GridBagConstraints.BOTH;
		gbc_spinner.insets = new Insets(0, 0, 5, 5);
		gbc_spinner.gridx = 2;
		gbc_spinner.gridy = 6;
		centerLeftPanel.add(spinner, gbc_spinner);
		
		JLabel lblNewLabel_4 = new JLabel("Reg nr.");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.fill = GridBagConstraints.BOTH;
		gbc_lblNewLabel_4.weighty = 0;
		gbc_lblNewLabel_4.weightx = 0;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 4;
		gbc_lblNewLabel_4.gridy = 6;
		centerLeftPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		txtWorkOrderID = new JTextField();
		GridBagConstraints gbc_txtWorkOrderID = new GridBagConstraints();
		gbc_txtWorkOrderID.weighty = 0;
		gbc_txtWorkOrderID.weightx = 0;
		gbc_txtWorkOrderID.insets = new Insets(0, 0, 5, 5);
		gbc_txtWorkOrderID.fill = GridBagConstraints.BOTH;
		gbc_txtWorkOrderID.gridx = 5;
		gbc_txtWorkOrderID.gridy = 6;
		centerLeftPanel.add(txtWorkOrderID, gbc_txtWorkOrderID);
		txtWorkOrderID.setColumns(10);
		
		JLabel lblEndDate = new JLabel("Slutdato");
		GridBagConstraints gbc_lblEndDate = new GridBagConstraints();
		gbc_lblEndDate.fill = GridBagConstraints.BOTH;
		gbc_lblEndDate.weighty = 0;
		gbc_lblEndDate.weightx = 0;
		gbc_lblEndDate.insets = new Insets(0, 0, 5, 5);
		gbc_lblEndDate.gridx = 1;
		gbc_lblEndDate.gridy = 7;
		centerLeftPanel.add(lblEndDate, gbc_lblEndDate);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setModel(new SpinnerDateModel());
		GridBagConstraints gbc_spinner_1 = new GridBagConstraints();
		gbc_spinner_1.weighty = 0;
		gbc_spinner_1.weightx = 0;
		gbc_spinner_1.fill = GridBagConstraints.BOTH;
		gbc_spinner_1.insets = new Insets(0, 0, 5, 5);
		gbc_spinner_1.gridx = 2;
		gbc_spinner_1.gridy = 7;
		centerLeftPanel.add(spinner_1, gbc_spinner_1);
		spinner_1.setEditor(new JSpinner.DateEditor(spinner, spinnerModel.toPattern()));
		
		JLabel lblStatus = new JLabel("Prioritet");
		GridBagConstraints gbc_lblStatus = new GridBagConstraints();
		gbc_lblStatus.fill = GridBagConstraints.BOTH;
		gbc_lblStatus.weighty = 0;
		gbc_lblStatus.weightx = 0;
		gbc_lblStatus.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatus.gridx = 4;
		gbc_lblStatus.gridy = 7;
		centerLeftPanel.add(lblStatus, gbc_lblStatus);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "Lav", "Mellem", "Høj"}));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.weighty = 0;
		gbc_comboBox.weightx = 0;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 5;
		gbc_comboBox.gridy = 7;
		centerLeftPanel.add(comboBox, gbc_comboBox);
		
		JLabel lblType = new JLabel("Type");
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.fill = GridBagConstraints.BOTH;
		gbc_lblType.weighty = 0;
		gbc_lblType.weightx = 0;
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.gridx = 1;
		gbc_lblType.gridy = 8;
		centerLeftPanel.add(lblType, gbc_lblType);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"", "Reparation", "Serviceaftale", "Vedligeholdelse"}));
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.weighty = 0;
		gbc_comboBox_1.weightx = 0;
		gbc_comboBox_1.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox_1.fill = GridBagConstraints.BOTH;
		gbc_comboBox_1.gridx = 2;
		gbc_comboBox_1.gridy = 8;
		centerLeftPanel.add(comboBox_1, gbc_comboBox_1);
		
		JLabel lblNewLabel_6 = new JLabel("Beskrivelse");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.NORTHWEST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 1;
		gbc_lblNewLabel_6.gridy = 9;
		centerLeftPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		JScrollPane scrollPane = new JScrollPane(textArea);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.insets = new Insets(0, 0, 5, 5);
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 2;
		gbc_scrollPane.gridy = 9;
		gbc_scrollPane.gridwidth = 4;
		centerLeftPanel.add(scrollPane, gbc_scrollPane);
		
		JButton btnNewButton = new JRoundedButton("Opret arbejdsordre");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.SOUTH;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 6;
		gbc_btnNewButton.gridy = 9;
		centerLeftPanel.add(btnNewButton, gbc_btnNewButton);

	}

}
