package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class AssetOverview extends JPanel {
	private JTextField txtSg;
	private JTextField idTextField;
	private JTextField headgroupTextField;
	private JTextField nameTextField;
	private JTextField subgroupTextField;
	private JTextField serialNumberTextField;
	private JTextField departmentTextField;
	private JTextField textField;
	private JPopupMenu popUp;
	private DefaultTable assetTable;
	private MainFrame mainFrame;
	/**
	 * Create the panel.
	 */
	public AssetOverview(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
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
		
		JPanel assetPanel = new JPanel();
		centerPanel.add(assetPanel);
		assetPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane assetSrollPanel = new JScrollPane();
		assetPanel.add(assetSrollPanel, BorderLayout.CENTER);
		String[] columns1 = new String[] { "Column", "Column1", "Column2", "Column3" };
		assetTable = new DefaultTable(null, columns1);
		assetSrollPanel.setViewportView(assetTable);
		assetTable.addRow(null);
		
		JPanel fillerPanel = new JPanel();
		fillerPanel.setLayout(new GridLayout(15, 1, 5, 5));
		assetPanel.add(fillerPanel, BorderLayout.EAST);
		JPanel textFieldPanel = new JPanel();
		centerPanel.add(textFieldPanel);
		FlowLayout fl_textFieldPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		textFieldPanel.setLayout(fl_textFieldPanel);
		
		JPanel informationTextFieldPanel = new JPanel();
		textFieldPanel.add(informationTextFieldPanel);
		informationTextFieldPanel.setLayout(new GridLayout(3, 4, 6, 3));
		
		JLabel idLabel = new JLabel("ID");
		idLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(idLabel);
		
		idTextField = new JTextField();
		informationTextFieldPanel.add(idTextField);
		idTextField.setEnabled(false);
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
		serialNumberLabel.setOpaque(true);
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
		extraPanel.setLayout(new GridLayout(2, 2, 6, 3));
		
		JLabel maintenanceIntervalLabel = new JLabel("Vedligeholdelsesinterval");
		extraPanel.add(maintenanceIntervalLabel);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		extraPanel.add(spinner);
		
		JLabel extraInfoLabel = new JLabel("Interval er i dage");
		extraPanel.add(extraInfoLabel);
		
		JButton changeButton = new JButton("Ændre");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		extraPanel.add(changeButton);
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
		workOrderButtonPanel.setLayout(new BoxLayout(workOrderButtonPanel, BoxLayout.Y_AXIS));
		
		JButton addNewButton = new JButton("Tilføj ny");
		workOrderButtonPanel.add(addNewButton);
		addNewButton.setMaximumSize(new Dimension(80, 23));
		addNewButton.setPreferredSize(new Dimension(80, 23));
		addNewButton.setMinimumSize(new Dimension(30, 5));
		
		JButton editButton = new JButton("Rediger");
		workOrderButtonPanel.add(editButton);
		editButton.setMaximumSize(new Dimension(80, 23));
		editButton.setPreferredSize(new Dimension(80, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		
		JButton deleteButton = new JButton("Slet");
		workOrderButtonPanel.add(deleteButton);
		deleteButton.setMaximumSize(new Dimension(80, 23));
		deleteButton.setPreferredSize(new Dimension(80, 23));
		deleteButton.setMinimumSize(new Dimension(30, 5));
		
		setPopUpMenu();
		
	}
	
	private void showPopUp(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popUp.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void popUpMenuAction(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Se maskine")) {
        	JPanel center = new ReadAsset();
	        mainFrame.setNewCenterPanel(center);
        }
    }
	
	private void setPopUpMenu() {
        popUp = new JPopupMenu();
        JMenuItem details = new JMenuItem("Se maskine");

        popUp.add(details);

        ActionListener alDetails = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                popUpMenuAction(e);
            }
        };

        details.addActionListener(alDetails);
        MouseAdapter ma = new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == 3) {
                    showPopUp(e);
                }
            }
        };
        assetTable.addMouseListener(ma);
    }

}
