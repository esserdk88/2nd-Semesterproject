package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class AssetOverview extends JPanel {
	private JTextField txtSg;
	private JTextField idTextField;
	private JTextField headgroupTextField;
	private JTextField nameTextField;
	private JTextField subgroupTextField;
	private JTextField serialNumberTextField;
	private JTextField departmentTextField;
	/**
	 * Create the panel.
	 */
	public AssetOverview() {
		setLayout(new BorderLayout(0, 0));
		JPanel topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(topPanel, BorderLayout.NORTH);
		
		JLabel lblNewLabel = new JLabel("Søg på ID");
		topPanel.add(lblNewLabel);
		
		txtSg = new JTextField();
		txtSg.setText("Søg efter ID");
		topPanel.add(txtSg);
		txtSg.setColumns(10);
		
		JButton searchButton = new JButton("Søg");
		topPanel.add(searchButton);
		
		JPanel leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		
		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		JScrollPane assetPanel = new JScrollPane();
		
		centerPanel.add(assetPanel);
		String[] columns1 = new String[] { "Column", "Column1", "Column2", "Column3" };
		DefaultTable assetTable = new DefaultTable(null, columns1);
		assetPanel.setViewportView(assetTable);
		JPanel textFieldPanel = new JPanel();
		centerPanel.add(textFieldPanel);
		textFieldPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel informationTextFieldPanel = new JPanel();
		textFieldPanel.add(informationTextFieldPanel);
		informationTextFieldPanel.setLayout(new GridLayout(3, 4, 6, 3));
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(idLabel);
		
		idTextField = new JTextField();
		informationTextFieldPanel.add(idTextField);
		idTextField.setColumns(10);
		
		JLabel headGroupLabel = new JLabel("Hovedgruppe");
		informationTextFieldPanel.add(headGroupLabel);
		
		headgroupTextField = new JTextField();
		informationTextFieldPanel.add(headgroupTextField);
		headgroupTextField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Navn");
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		informationTextFieldPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		JLabel subGroupLabel = new JLabel("Undergruppe");
		informationTextFieldPanel.add(subGroupLabel);
		
		subgroupTextField = new JTextField();
		informationTextFieldPanel.add(subgroupTextField);
		subgroupTextField.setColumns(10);
		
		JLabel serialNumberLabel = new JLabel("Serienr.");
		serialNumberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(serialNumberLabel);
		
		serialNumberTextField = new JTextField();
		informationTextFieldPanel.add(serialNumberTextField);
		serialNumberTextField.setColumns(10);
		
		JLabel departmentLabel = new JLabel("Afdeling");
		informationTextFieldPanel.add(departmentLabel);
		
		departmentTextField = new JTextField();
		informationTextFieldPanel.add(departmentTextField);
		departmentTextField.setColumns(10);
		
		JPanel extraPanel = new JPanel();
		textFieldPanel.add(extraPanel);
		extraPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel workOrderPanel = new JPanel();
		centerPanel.add(workOrderPanel);
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		workOrderPanel.setLayout(new BorderLayout(0, 0));
		
		
		JScrollPane workOrderScrollPanel = new JScrollPane();
		workOrderPanel.add(workOrderScrollPanel, BorderLayout.CENTER);
		DefaultTable workOrderTable = new DefaultTable(null, columns2);
		workOrderScrollPanel.setViewportView(workOrderTable);
		
		JPanel workOrderButtonPanel = new JPanel();
		workOrderPanel.add(workOrderButtonPanel, BorderLayout.EAST);
		workOrderButtonPanel.setLayout(new GridLayout(15, 1, 5, 5));
		
		JButton addNewButton = new JButton("Tilføj ny");
		workOrderButtonPanel.add(addNewButton);
		
		JButton editButton = new JButton("Rediger");
		workOrderButtonPanel.add(editButton);
		
		JButton deleteButton = new JButton("Slet");
		workOrderButtonPanel.add(deleteButton);
		
		
	}
}
