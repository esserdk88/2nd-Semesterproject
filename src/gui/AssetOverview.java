package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import Controller.AssetController;
import dal.AssetDB;
import model.Asset;

public class AssetOverview extends JPanel {
	
	//Textfields
	private JTextField txtSg;
	private JTextField idTextField;
	private JTextField headgroupTextField;
	private JTextField nameTextField;
	private JTextField subgroupTextField;
	private JTextField serialNumberTextField;
	private JTextField departmentTextField;
	
	//Tables
	private JPopupMenu popUp;
	private DefaultTable assetTable;
	private JScrollPane assetScrollPanel;
	private JScrollPane workOrderScrollPanel;
	private DefaultTable workOrderTable;
	
	//Panels
	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel assetPanel;
	private JPanel fillerPanel;
	private JPanel textFieldPanel;
	private JPanel informationTextFieldPanel;
	private JPanel workOrderPanel;
	private JPanel workOrderButtonPanel;
	
	//Labels
	private JLabel lblNewLabel;
	private JLabel idLabel;
	private JLabel headGroupLabel;
	private JLabel nameLabel;
	private JLabel subGroupLabel;
	private JLabel serialNumberLabel;
	private JLabel departmentLabel;
	private JPanel extraPanel;
	private JLabel maintenanceIntervalLabel;
	private JLabel extraInfoLabel;
	
	//Buttons
	private JButton searchButton;
	private JButton changeButton;
	private JButton addNewButton;
	private JButton editButton;
	private JButton deleteButton;
	
	//Extra Components
	private JSpinner spinner;
	private MainFrame mainFrame;
	private AssetDB assetDatabase;
	private AssetController assetCtrl;
	private ArrayList<Asset> list;	
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public AssetOverview(MainFrame mainFrame) throws SQLException{
		this.mainFrame = mainFrame;
		setLayout(new BorderLayout(0, 0));
		setPanels();
		setButtons();
		setLabelsAndTextfields();
		setTables();
		setPopUpMenu();
	}
	

	private void showPopUp(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popUp.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void popUpMenuAction(ActionEvent e) {
        String s = e.getActionCommand();

        if (s.equals("Se asset")) {
        	showAsset(false);
        }
    }
	
	private void setPopUpMenu() {
        popUp = new JPopupMenu();
        JMenuItem details = new JMenuItem("Se asset");

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
	
	public ArrayList<Asset> fetchAllAssets() throws SQLException {
		assetDatabase = new AssetDB();
		assetCtrl = new AssetController(assetDatabase);
		List<Asset> list = assetCtrl.getAllAssets();
        ArrayList<Asset> arrayList = new ArrayList<Asset>(list);
		
		return arrayList;
	}

	private void setTable(ArrayList<Asset> list) {
		assetScrollPanel = new JScrollPane();
		assetPanel.add(assetScrollPanel, BorderLayout.CENTER);
		boolean[] activeColumns = new boolean[] { true, true, true, false, true, true };
		String[] columns = new String[] { "AssetID", "Navn", "Anskfaffelsesdato", "Beskrivelse", "Status",
				"Producent" };
 
		String[][] data = convertToStringArray(list);
		
		assetTable = new DefaultTable(data, columns, activeColumns);

		assetScrollPanel.setViewportView(assetTable);
	}
	
	private String[][] convertToStringArray(ArrayList<Asset> dataArrayList) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int size = dataArrayList.size();
		String[][] data = new String[size][12];
		for (int i = 0; i < size; i++) {
			Asset current = dataArrayList.get(i);
			String dateString = dateFormat.format(current.getAquisitionDate().getTime());
			data[i][0] = Integer.toString(current.getAssetID());
			data[i][1] = current.getName();
			data[i][2] = dateString;
			data[i][3] = current.getDescription();
			data[i][4] = current.getStatus();
			data[i][5] = current.getManufacturer();
		}
		return data;
	}
	
	private void showAsset(boolean editMode) {
		int index = assetTable.findElement();

		if (index == -1) {
			GUIPopUpMessages.informationMessage("Intet produkt valgt", "Fejl");
		} else {
			Asset tempAsset = null;
			assetDatabase = new AssetDB();
			assetCtrl = new AssetController(assetDatabase);
			Object value = assetTable.getModel().getValueAt(index, 0);
			try {
				tempAsset = assetCtrl.findAssetByID(Integer.parseInt(value.toString()));
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (tempAsset != null) {
				ReadAsset readAsset = new ReadAsset(tempAsset);
				mainFrame.setNewCenterPanel(readAsset);
			}
		}
	}
	private void setTables() throws SQLException {
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3" };
		workOrderScrollPanel = new JScrollPane();
		workOrderPanel.add(workOrderScrollPanel, BorderLayout.CENTER);
		workOrderTable = new DefaultTable(null, columns2);
		workOrderScrollPanel.setViewportView(workOrderTable);
		list = fetchAllAssets();
		setTable(list);
	}

	private void setLabelsAndTextfields() {
		lblNewLabel = new JLabel("Søg på ID");
		topPanel.add(lblNewLabel);
		
		txtSg = new JTextField();
		txtSg.setText("Søg efter ID");
		topPanel.add(txtSg);
		txtSg.setColumns(10);
		idLabel = new JLabel("ID");
		idLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(idLabel);
		
		idTextField = new JTextField();
		informationTextFieldPanel.add(idTextField);
		idTextField.setEnabled(false);
		idTextField.setColumns(10);
		
		headGroupLabel = new JLabel("Hovedgruppe");
		informationTextFieldPanel.add(headGroupLabel);
		
		headgroupTextField = new JTextField();
		informationTextFieldPanel.add(headgroupTextField);
		headgroupTextField.setColumns(10);
		
		nameLabel = new JLabel("Navn");
		nameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(nameLabel);
		
		nameTextField = new JTextField();
		informationTextFieldPanel.add(nameTextField);
		nameTextField.setColumns(10);
		
		subGroupLabel = new JLabel("Undergruppe");
		informationTextFieldPanel.add(subGroupLabel);
		
		subgroupTextField = new JTextField();
		informationTextFieldPanel.add(subgroupTextField);
		subgroupTextField.setColumns(10);
		
		serialNumberLabel = new JLabel("Serienr.");
		serialNumberLabel.setOpaque(true);
		serialNumberLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		informationTextFieldPanel.add(serialNumberLabel);
		
		serialNumberTextField = new JTextField();
		informationTextFieldPanel.add(serialNumberTextField);
		serialNumberTextField.setColumns(10);
		
		departmentLabel = new JLabel("Afdeling");
		informationTextFieldPanel.add(departmentLabel);
		
		departmentTextField = new JTextField();
		informationTextFieldPanel.add(departmentTextField);
		departmentTextField.setColumns(10);
		
		maintenanceIntervalLabel = new JLabel("Vedligeholdelsesinterval");
		extraPanel.add(maintenanceIntervalLabel);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
		extraPanel.add(spinner);
		
		extraInfoLabel = new JLabel("Interval er i dage");
		extraPanel.add(extraInfoLabel);
		
	}

	private void setButtons() {
		searchButton = new JButton("Søg");
		topPanel.add(searchButton);
		changeButton = new JButton("Ændre");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		extraPanel.add(changeButton);
		
		addNewButton = new JButton("Tilføj ny");
		workOrderButtonPanel.add(addNewButton);
		addNewButton.setMaximumSize(new Dimension(80, 23));
		addNewButton.setPreferredSize(new Dimension(80, 23));
		addNewButton.setMinimumSize(new Dimension(30, 5));
		
		editButton = new JButton("Rediger");
		workOrderButtonPanel.add(editButton);
		editButton.setMaximumSize(new Dimension(80, 23));
		editButton.setPreferredSize(new Dimension(80, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		
		deleteButton = new JButton("Slet");
		workOrderButtonPanel.add(deleteButton);
		deleteButton.setMaximumSize(new Dimension(80, 23));
		deleteButton.setPreferredSize(new Dimension(80, 23));
		deleteButton.setMinimumSize(new Dimension(30, 5));
	}

	private void setPanels() {
		topPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) topPanel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		add(topPanel, BorderLayout.NORTH);
		
		
		leftPanel = new JPanel();
		add(leftPanel, BorderLayout.WEST);
		
		centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
		
		assetPanel = new JPanel();
		centerPanel.add(assetPanel);
		assetPanel.setLayout(new BorderLayout(0, 0));
		fillerPanel = new JPanel();
		fillerPanel.setLayout(new GridLayout(15, 1, 5, 5));
		assetPanel.add(fillerPanel, BorderLayout.EAST);
		textFieldPanel = new JPanel();
		centerPanel.add(textFieldPanel);
		FlowLayout fl_textFieldPanel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		textFieldPanel.setLayout(fl_textFieldPanel);
		
		informationTextFieldPanel = new JPanel();
		textFieldPanel.add(informationTextFieldPanel);
		informationTextFieldPanel.setLayout(new GridLayout(3, 4, 6, 3));

		extraPanel = new JPanel();
		textFieldPanel.add(extraPanel);
		extraPanel.setLayout(new GridLayout(2, 2, 6, 3));	
		
		workOrderPanel = new JPanel();
		centerPanel.add(workOrderPanel);
		workOrderPanel.setLayout(new BorderLayout(0, 0));
		
		
		workOrderButtonPanel = new JPanel();
		workOrderPanel.add(workOrderButtonPanel, BorderLayout.EAST);
		workOrderButtonPanel.setLayout(new BoxLayout(workOrderButtonPanel, BoxLayout.Y_AXIS));
		
	}

}
