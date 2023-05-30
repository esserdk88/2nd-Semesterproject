package gui;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Stack;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import dao.Database;
import gui.components.ConnectionWatch;
import gui.components.JRoundedButton;
import gui.components.PageTracker;

/**
 * The MainFrame class creates the main GUI window for an application and handles navigation between
 * different panels.
 */
public class MainFrame extends JFrame {

	//The Frame
	private static MainFrame frame;

	//Stacks
	private Stack<JPanel> forward;
	private Stack<JPanel> backwards;

	//Buttons
	private JButton assetButton;
	private JButton workOrderButton;
	private JButton employeeButton;
	private JButton exitButton;
	private JButton nextButton;
	private JButton returnButton;
	
	//Panels
	private JPanel leftPanel;
	private JPanel topPanel;
	private JPanel centerPanel;
	private JPanel bottomPanel;
	private JPanel contentPane;
	private JPanel currentCenterPanel;
	
	//Labels
	private JLabel menulabel;
	private JLabel connectionLabel;
	
	//Connection
	ConnectionWatch connectionWatch;
	
	//Page Tracker
	private PageTracker pageTracker;
	private JLabel frequentLabel;
	private JSeparator separator;

	private JLabel primaryMenu;

	private JSeparator separator2;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setName("Hovedmenu");
		pageTracker = new PageTracker();
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int dpi = toolkit.getScreenResolution();
		double scaleFactor = dpi / 96.0;
		int minWidth = (int) (1000 * scaleFactor);
		int minHeight = (int) (500 * scaleFactor);
		setMinimumSize(new Dimension(minWidth, minHeight));
		
		forward = new Stack<>();
		backwards = new Stack<>();
		Database.getInstance();
		setPanels();
		setButtons();
		setButtonStatus();
		setBackAndForwardsListeners();
		frequentlyVisitedPagesButtons();
		//This will start a new Thread that will run a connection test and change the parsed label
		connectionWatch = new ConnectionWatch(connectionLabel);
		connectionWatch.execute();
	}

	/**
	 * The function sets listeners for keyboard and mouse events to trigger backwards and forwards
	 * navigation in a program.
	 */
	private void setBackAndForwardsListeners() {
		AWTEventListener keyEventListener = event -> {
            KeyEvent keyEvent = (KeyEvent) event;
            if (keyEvent.getID() == KeyEvent.KEY_PRESSED) {
                if (backwards.size() > 0 && keyEvent.isControlDown() && keyEvent.isAltDown() && keyEvent.getKeyCode() == KeyEvent.VK_LEFT) {
                    backwardsButton();
                }
                else if (forward.size() > 0 && keyEvent.isControlDown() && keyEvent.isAltDown() && keyEvent.getKeyCode() == KeyEvent.VK_RIGHT) {
                    forwardButton();
                }
            }
            
        };

        AWTEventListener mouseEventListener = event -> {
            InputEvent inputEvent = (InputEvent) event;
            if (inputEvent instanceof MouseEvent) {
                MouseEvent mouseEvent = (MouseEvent) inputEvent;
                if (mouseEvent.getID() == MouseEvent.MOUSE_PRESSED) {
                	if(backwards.size() > 0 && mouseEvent.getButton() == 4) {
                		backwardsButton();
                	}else if(forward.size() > 0 && mouseEvent.getButton() == 5) {
                		forwardButton();
                	}
                }
            }
        };

        Toolkit.getDefaultToolkit().addAWTEventListener(keyEventListener, AWTEvent.KEY_EVENT_MASK);
        Toolkit.getDefaultToolkit().addAWTEventListener(mouseEventListener, AWTEvent.MOUSE_EVENT_MASK);
		
	}

	/**
	 * This function removes the current center panel, adds it to a backwards stack, sets the current
	 * center panel to the top of a forward stack, adds it to the content pane, updates the menu label,
	 * and sets the button status.
	 */
	private void forwardButton() {
		contentPane.remove(currentCenterPanel);
		backwards.add(currentCenterPanel);
		currentCenterPanel = forward.peek();
		contentPane.add(forward.pop(), BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        menulabel.setText(currentCenterPanel.getName());
        addPageVisit(currentCenterPanel);
        setButtonStatus();
	}

	/**
	 * This function removes the current center panel from the content pane, adds it to a forward stack,
	 * sets the current center panel to the top of a backwards stack, adds it to the content pane, updates
	 * the menu label, and sets the button status.
	 */
	private void backwardsButton() {
		contentPane.remove(currentCenterPanel);
		forward.add(currentCenterPanel);
		currentCenterPanel = backwards.peek();
		contentPane.add(backwards.pop(), BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        menulabel.setText(currentCenterPanel.getName());
        addPageVisit(currentCenterPanel);
        setButtonStatus();
	}
	
	/**
	 * This function sets a new panel as the center panel of a content pane and updates the navigation
	 * stack accordingly.
	 * 
	 * @param newPanel a JPanel that will replace the current center panel in the contentPane.
	 */
	public void setNewCenterPanel(JPanel newPanel) {
        contentPane.remove(currentCenterPanel);
        backwards.add(currentCenterPanel);
        forward = new Stack<>();
        contentPane.add(newPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        menulabel.setText(newPanel.getName());
        currentCenterPanel = newPanel;
        addPageVisit(currentCenterPanel);
        setButtonStatus();
	}

	/**
	 * This function sets the status of two buttons based on whether two lists are empty or not.
	 */
	private void setButtonStatus() {
		if(forward.isEmpty() == true) {
			nextButton.setEnabled(false);
		}else if(forward.isEmpty() == false) {
			nextButton.setEnabled(true);
		}
		if(backwards.isEmpty() == true) {
			returnButton.setEnabled(false);
		}else if(backwards.isEmpty() == false) {
			returnButton.setEnabled(true);
		}
	}
	
	/**
	 * This function sets the text of a menu label to a new title.
	 * 
	 * @param newTitle a String variable that represents the new title to be set for a page.
	 */
	public void setPageTitle(String newTitle) {
		menulabel.setText(newTitle);
	}
	
	/**
	 * This function sets up the layout and panels for a Java GUI.
	 */
	private void setPanels() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		centerPanel.setName("HovedMenu");
		contentPane.add(centerPanel, BorderLayout.CENTER);
		currentCenterPanel = centerPanel;
		menulabel = new JLabel("Hovedmenu");
		menulabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		topPanel.add(menulabel);
		
		leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{0, 0};
		gbl_leftPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		
		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
	}

	/**
	 * This function sets a new center panel to display an asset overview.
	 */
	private void assetButtonMethod() {
		AssetOverview panel = new AssetOverview(frame);
		setNewCenterPanel(panel);
	}

	/**
	 * This function sets up and adds various buttons and labels to the left and bottom panels of a
	 * graphical user interface.
	 */
	private void setButtons() {
		assetButton = new JRoundedButton("Asset");
		assetButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_assetButton = new GridBagConstraints();
		gbc_assetButton.fill = GridBagConstraints.BOTH;
		gbc_assetButton.insets = new Insets(0, 0, 5, 0);
		gbc_assetButton.gridx = 0;
		gbc_assetButton.gridy = 2;
		leftPanel.add(assetButton, gbc_assetButton);

		workOrderButton = new JRoundedButton("Arbejdsordre");

		workOrderButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_workOrderButton = new GridBagConstraints();
		gbc_workOrderButton.fill = GridBagConstraints.BOTH;
		gbc_workOrderButton.insets = new Insets(0, 0, 5, 0);
		gbc_workOrderButton.gridx = 0;
		gbc_workOrderButton.gridy = 3;
		leftPanel.add(workOrderButton, gbc_workOrderButton);
		
		employeeButton = new JRoundedButton("Ansatte");
		employeeButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_employeeButton = new GridBagConstraints();
		gbc_employeeButton.insets = new Insets(0, 0, 5, 0);
		gbc_employeeButton.fill = GridBagConstraints.BOTH;
		gbc_employeeButton.gridx = 0;
		gbc_employeeButton.gridy = 4;
		leftPanel.add(employeeButton, gbc_employeeButton);
		
		returnButton = new JRoundedButton("Tilbage");
		GridBagConstraints gbc_returnButton = new GridBagConstraints();
		gbc_returnButton.insets = new Insets(0, 0, 0, 5);
		gbc_returnButton.gridx = 4;
		gbc_returnButton.gridy = 0;
		bottomPanel.add(returnButton, gbc_returnButton);
		
		connectionLabel = new JLabel("No connection");
		GridBagConstraints gbc_connectionLabel = new GridBagConstraints();
		gbc_connectionLabel.insets = new Insets(0, 0, 0, 5);
		gbc_connectionLabel.gridx = 1;
		gbc_connectionLabel.gridy = 0;
		bottomPanel.add(connectionLabel, gbc_connectionLabel);
		
		separator = new JSeparator();
		Border paddingBorder = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels of padding on all sides
		Border lineBorder = BorderFactory.createLineBorder(Color.BLACK);
		separator.setBorder(BorderFactory.createCompoundBorder(lineBorder, paddingBorder));
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 7;
		leftPanel.add(separator, gbc_separator);
		
		separator2 = new JSeparator();
		Border paddingBorder2 = BorderFactory.createEmptyBorder(10, 10, 10, 10); // 10 pixels of padding on all sides
		Border lineBorder2 = BorderFactory.createLineBorder(Color.BLACK);
		separator2.setBorder(BorderFactory.createCompoundBorder(lineBorder2, paddingBorder2));
		GridBagConstraints gbc_separator2 = new GridBagConstraints();
		gbc_separator2.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator2.insets = new Insets(0, 0, 5, 0);
		gbc_separator2.gridx = 0;
		gbc_separator2.gridy = 1;
		leftPanel.add(separator2, gbc_separator2);
		
		primaryMenu = new JLabel("Menu");
		primaryMenu.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_primaryMenu = new GridBagConstraints();
		gbc_primaryMenu.insets = new Insets(4, 26, 5, 26);
		gbc_primaryMenu.gridx = 0;
		gbc_primaryMenu.gridy = 0;
		leftPanel.add(primaryMenu, gbc_primaryMenu);
		
		frequentLabel = new JLabel("Mest Besøgte");
		frequentLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_frequentLabel = new GridBagConstraints();
		gbc_frequentLabel.insets = new Insets(4, 26, 5, 26);
		gbc_frequentLabel.gridx = 0;
		gbc_frequentLabel.gridy = 6;
		leftPanel.add(frequentLabel, gbc_frequentLabel);
		
		
		nextButton = new JRoundedButton("Næste");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 0, 5);
		gbc_nextButton.gridx = 5;
		gbc_nextButton.gridy = 0;
		bottomPanel.add(nextButton, gbc_nextButton);
		
		exitButton = new JRoundedButton("Afslut");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(0, 0, 0, 5);
		gbc_exitButton.fill = GridBagConstraints.BOTH;
		gbc_exitButton.gridx = 6;
		gbc_exitButton.gridy = 0;
		bottomPanel.add(exitButton, gbc_exitButton);
				
		assetButton.addActionListener(e -> assetButtonMethod());
		workOrderButton.addActionListener(e -> setNewCenterPanel(new WorkOrderOverview(frame)));
		employeeButton.addActionListener(e -> setNewCenterPanel(new EmployeeOverview()));
		returnButton.addActionListener(e -> {if(backwards.peek() != null) {backwardsButton();}});
		nextButton.addActionListener(e -> {if(forward.isEmpty() != true) {forwardButton();}});
		exitButton.addActionListener(e -> closeWindow());
	}

	/**
	 * The function adds a page visit to a page tracker if the current panel is not the main menu.
	 * 
	 * @param panel A JPanel object that represents the panel that the user has visited.
	 */
	private void addPageVisit(JPanel panel) {
		if(!panel.getName().equals("HovedMenu")) {
			pageTracker.pageVisited(currentCenterPanel);
		}
	}

	/**
	 * This function creates buttons for the top 5 frequently visited pages and hides the frequent label
	 * and separator if there are no frequently visited pages.
	 */
	private void frequentlyVisitedPagesButtons() {
		List<String> panels = pageTracker.getTop5VisitedPages();
		for(int i = 0;i<panels.size();i++) {
			createNewFrequentButton(panels.get(i), i);
		}
		if(panels.size() == 0) {
			frequentLabel.setVisible(false);
			separator.setVisible(false);
		}
	}
	
	/**
	 * This function creates a new button with a given name and position, adds it to a panel, and sets an
	 * action listener to change the center panel when clicked.
	 * 
	 * @param name The name of the frequent button that will be created.
	 * @param position The position parameter is an integer value that determines the vertical position of
	 * the button in the leftPanel. It is used to calculate the value of the gridy parameter in the
	 * GridBagConstraints object, which is then used to add the button to the leftPanel at the specified
	 * position.
	 */
	private void createNewFrequentButton(String name, int position) {
		JButton button = new JRoundedButton(name);
		button.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_button1 = new GridBagConstraints();
		gbc_button1.fill = GridBagConstraints.BOTH;
		gbc_button1.insets = new Insets(0, 0, 5, 5);
		gbc_button1.gridx = 0;
		gbc_button1.gridy = 8 + position;
		leftPanel.add(button, gbc_button1);
		
		button.addActionListener((e) -> setNewCenterPanel(findPanelFromName(name)));
	}

	/**
	 * The function finds a JPanel based on a given name and returns it.
	 * 
	 * @param name A string representing the name of a panel to be found. The method searches for a panel
	 * with a matching name and returns it. If no matching panel is found, it returns the default center
	 * panel.
	 * @return The method is returning a JPanel object. The specific JPanel object being returned depends
	 * on the value of the input parameter "name". If "name" matches one of the cases in the switch
	 * statement, a new JPanel object is created and returned. If "name" does not match any of the cases,
	 * the method returns the JPanel object "centerPanel".
	 */
	private JPanel findPanelFromName(String name) {
		JPanel panel = null;
		switch(name) {
		case"Aktiv Oversigt":
			panel = new AssetOverview(this);
			break;
		case"Opret Arbejdsodre":
			panel = new CreateWorkOrder();
			break;
		case"Medarbejder Oversigt":
			panel = new EmployeeOverview();
			break;
		case"Se Aktiv":
			panel = new ReadAsset(this);
			break;
		case"Se Arbejdsopgave":
			panel = new ReadWorkOrder();
			break;
		case"Arbejdsordre Oversigt":
			panel = new WorkOrderOverview(this);
			break;
		default:
			panel = centerPanel;
			break;
		}
		return panel;
	}

	/**
	 * This function displays a confirmation dialog and closes the window if the user chooses to do so.
	 */
	private void closeWindow(){
		int input = JOptionPane.showOptionDialog(this, "Er du sikkert på at du vil lukke programmet?", "Afslut program",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				new Object[] { "Ja", "Nej" }, JOptionPane.YES_OPTION);
		if(input == 0) {			
			frame.dispose();
			System.exit(0);
		}
	}
}
