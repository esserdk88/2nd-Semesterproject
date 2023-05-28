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

import javax.swing.Box;
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

import controller.AssetController;
import controller.WorkOrderController;
import gui.components.DefaultTable;
import gui.components.GUIPopUpMessages;
import gui.components.JRoundedButton;
import gui.components.TableSwingWorker;
import model.Asset;
import java.awt.Component;

/**
 * The AssetOverview class is a JPanel that displays a table of assets and allows users to search, add,
 * edit, and delete assets.
 */
public class AssetOverview extends JPanel {

	// Textfields
	private JTextField txtSg;
	private JTextField idTextField;
	private JTextField headgroupTextField;
	private JTextField nameTextField;
	private JTextField subgroupTextField;
	private JTextField serialNumberTextField;
	private JTextField departmentTextField;

	// Tables
	private JPopupMenu popUp;
	private DefaultTable assetTable;
	private JScrollPane assetScrollPanel;
	private JScrollPane workOrderScrollPanel;
	private DefaultTable workOrderTable;

	// Panels
	private JPanel topPanel;
	private JPanel leftPanel;
	private JPanel centerPanel;
	private JPanel assetPanel;
	private JPanel fillerPanel;
	private JPanel textFieldPanel;
	private JPanel informationTextFieldPanel;
	private JPanel workOrderPanel;
	private JPanel workOrderButtonPanel;

	// Labels
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

	// Buttons
	private JButton searchButton;
	private JButton changeButton;
	private JButton addNewButton;
	private JButton editButton;
	private JButton deleteButton;

	// Extra Components
	private JSpinner spinner;
	private MainFrame mainFrame;
	private AssetController assetCtrl;
	private Component verticalStrut;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public AssetOverview(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.setName("Aktiv Oversigt");
		assetCtrl = new AssetController();
		setLayout(new BorderLayout(0, 0));
		setPanels();
		setLabelsAndTextfields();
		setButtons();
		setTables();
		setPopUpMenu();
		setAssetOnStartUp();
	}

	/**
	 * This function shows a pop-up menu when a mouse event is triggered.
	 * 
	 * @param e MouseEvent object that contains information about the mouse event that triggered the
	 * method.
	 */
	private void showPopUp(MouseEvent e) {
		if (e.isPopupTrigger()) {
			popUp.show(e.getComponent(), e.getX(), e.getY());
		}
	}

	/**
	 * The function handles the action performed when a specific option is selected from a pop-up menu.
	 * 
	 * @param e An ActionEvent object, which represents the user's action that triggered the event.
	 */
	private void popUpMenuAction(ActionEvent e) {
		String s = e.getActionCommand();

		if (s.equals("Se asset")) {
			showAsset(false);
		}
	}

	/**
	 * This function sets up a pop-up menu with a "Se asset" option that can be triggered by
	 * right-clicking on a table.
	 */
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

	/**
	 * This function sets the loading status of assets and fetches asset data in a separate thread using a
	 * SwingWorker.
	 */
	private void setAssetOnStartUp() {
		String[][] loadingStatus = { { "Henter assets..." } };
		assetTable.setNewData(loadingStatus);
		// The above code is creating a new thread and defining its behavior using a lambda expression.
		// Inside the thread, a new instance of the `TableSwingWorker` class is created with the `assetTable`
		// and the result of calling the `getAllAssets()` method on the `assetCtrl` object as parameters. If
		// a `SQLException` is thrown during this process, it will be printed to the console. Finally, the
		// `execute()` method is called on the `dataFetcher` object to start the background task of fetching
		// data and updating the `assetTable`.
		Thread workerThread = new Thread(() -> {
			TableSwingWorker dataFetcher = null;
			try {
				dataFetcher = new TableSwingWorker(assetTable, assetCtrl.getAllAssets());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dataFetcher.execute();
		});
		workerThread.start();
	}

	/**
	 * The function sets up two tables with specified columns and adds them to their respective panels.
	 */
	private void setTables() {
		String[] columns2 = new String[] { "Column", "Column1", "Column2", "Column3", "Column3" };
		workOrderScrollPanel = new JScrollPane();
		workOrderPanel.add(workOrderScrollPanel, BorderLayout.CENTER);
		workOrderTable = new DefaultTable(null, columns2);
		workOrderScrollPanel.setViewportView(workOrderTable);

		assetScrollPanel = new JScrollPane();
		assetPanel.add(assetScrollPanel, BorderLayout.CENTER);
		boolean[] activeColumns = new boolean[] { true, true, true, false, true };
		String[] columns = new String[] { "AssetID", "Navn", "Anskfaffelsesdato", "Beskrivelse", "Status",
				"Producent" };
		assetTable = new DefaultTable(null, columns, activeColumns);
		assetScrollPanel.setViewportView(assetTable);
	}

	/**
	 * This function shows information about a selected asset and its work orders in a GUI.
	 * 
	 * @param editMode The editMode parameter is not used in the method and is therefore irrelevant. It is
	 * likely a leftover parameter from previous versions of the code.
	 */
	@SuppressWarnings("unused")
	private void showAsset(boolean editMode) {
		int index = assetTable.findElement();

		if (index == -1) {
			GUIPopUpMessages.informationMessage("Intet asset valgt", "Fejl");
		} else {
			Object value = assetTable.getModel().getValueAt(index, 0);
			ReadAsset readAsset = new ReadAsset(mainFrame); //TODO: Change to field and instantiate in constructor
			int assetID = Integer.parseInt(value.toString());
			mainFrame.setNewCenterPanel(readAsset);
			Thread workerThread = new Thread(() -> {
				try {
					readAsset.initialize(assetCtrl.findAssetByID(assetID));
				} catch (NumberFormatException e) {
					GUIPopUpMessages.warningMessage("Only letters in ID textbox", "Error!");
				} catch (SQLException e) {
					GUIPopUpMessages.warningMessage("Database connection problems", "Error!");
				}
				TableSwingWorker dataFetcher = null;
				dataFetcher = new TableSwingWorker(readAsset.getHistoryTable(),
						assetCtrl.getAllWorkOrdersForAssetByID(assetID));
				dataFetcher.execute();
			});
			workerThread.start();

		}
	}

	/**
	 * This function sets up labels and text fields for a user interface in Java.
	 */
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

	/**
	 * This function sets up and adds various buttons to different panels in a Java GUI.
	 */
	private void setButtons() {
		searchButton = new JRoundedButton("Søg");
		topPanel.add(searchButton);
		changeButton = new JRoundedButton("Ændre");
		changeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		extraPanel.add(changeButton);

		addNewButton = new JRoundedButton("Tilføj ny");
		workOrderButtonPanel.add(addNewButton);
		addNewButton.setMaximumSize(new Dimension(110, 23));
		addNewButton.setPreferredSize(new Dimension(110, 23));
		addNewButton.setMinimumSize(new Dimension(30, 5));

		verticalStrut = Box.createVerticalStrut(4);
		workOrderButtonPanel.add(verticalStrut);

		editButton = new JRoundedButton("Rediger");
		workOrderButtonPanel.add(editButton);
		editButton.setMaximumSize(new Dimension(110, 23));
		editButton.setPreferredSize(new Dimension(110, 23));
		editButton.setMinimumSize(new Dimension(30, 5));
		workOrderButtonPanel.add(Box.createVerticalStrut(4));

		deleteButton = new JRoundedButton("Slet");
		workOrderButtonPanel.add(deleteButton);
		deleteButton.setMaximumSize(new Dimension(110, 23));
		deleteButton.setPreferredSize(new Dimension(110, 23));
		deleteButton.setMinimumSize(new Dimension(30, 5));
	}

	/**
	 * This function sets up various panels with different layouts and adds them to the main frame.
	 */
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
