package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.AssetController;
import dal.AssetDB;
import dal.DatabaseConnection;
import model.Asset;

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
		
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int dpi = toolkit.getScreenResolution();
		double scaleFactor = dpi / 96.0;
		int minWidth = (int) (958 * scaleFactor);
		int minHeight = (int) (500 * scaleFactor);
		setMinimumSize(new Dimension(minWidth, minHeight));
		
		forward = new Stack<>();
		backwards = new Stack<>();
		
		setPanels();
		setButtons();
		setButtonStatus();
		
		//This will start a new Thread that will run a connection test and change the parsed label
		connectionWatch = new ConnectionWatch(connectionLabel);
		connectionWatch.execute();
	}
	private void forwardButton() {
		contentPane.remove(currentCenterPanel);
		backwards.add(currentCenterPanel);
		currentCenterPanel = forward.peek();
		contentPane.add(forward.pop(), BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        setButtonStatus();
	}
	private void backwardsButton() {
		contentPane.remove(currentCenterPanel);
		forward.add(currentCenterPanel);
		currentCenterPanel = backwards.peek();
		contentPane.add(backwards.pop(), BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        setButtonStatus();
	}
	
	public void setNewCenterPanel(JPanel newPanel) {
        contentPane.remove(currentCenterPanel);
        backwards.add(currentCenterPanel);
        forward = new Stack<>();
        contentPane.add(newPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        currentCenterPanel = newPanel;
        setButtonStatus();
	}
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
	
	public void setPageTitle(String newTitle) {
		menulabel.setText(newTitle);
	}
	
	private void setPanels() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		currentCenterPanel = centerPanel;
		menulabel = new JLabel("Mainmenu");
		menulabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		topPanel.add(menulabel);
		
		leftPanel = new JPanel();
		contentPane.add(leftPanel, BorderLayout.WEST);
		GridBagLayout gbl_leftPanel = new GridBagLayout();
		gbl_leftPanel.columnWidths = new int[]{0, 0};
		gbl_leftPanel.rowHeights = new int[]{0, 0, 0, 0};
		gbl_leftPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_leftPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		leftPanel.setLayout(gbl_leftPanel);
		

		JButton assetButton = new JButton("Asset");
		assetButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
			        JPanel center = null;
					center = new AssetOverview(frame);
			        setNewCenterPanel(center);
			    }
		});

		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
	}
	private void setButtons() {
		assetButton = new JRoundedButton("Asset");

		assetButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_assetButton = new GridBagConstraints();
		gbc_assetButton.gridwidth = 2;
		gbc_assetButton.fill = GridBagConstraints.BOTH;
		gbc_assetButton.insets = new Insets(0, 0, 5, 0);
		gbc_assetButton.gridx = 0;
		gbc_assetButton.gridy = 0;
		leftPanel.add(assetButton, gbc_assetButton);

		workOrderButton = new JButton("Arbejdsordre");
		workOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel center = null;
				center = new WorkOrderOverview(frame);
				setNewCenterPanel(center);
			}
		});

		workOrderButton = new JButton("Arbejdsordre");

		workOrderButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_workOrderButton = new GridBagConstraints();
		gbc_workOrderButton.fill = GridBagConstraints.BOTH;
		gbc_workOrderButton.insets = new Insets(0, 0, 5, 0);
		gbc_workOrderButton.gridx = 0;
		gbc_workOrderButton.gridy = 1;
		leftPanel.add(workOrderButton, gbc_workOrderButton);
		
		employeeButton = new JButton("Ansatte");
		employeeButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_employeeButton = new GridBagConstraints();
		gbc_employeeButton.fill = GridBagConstraints.BOTH;
		gbc_employeeButton.gridx = 0;
		gbc_employeeButton.gridy = 2;
		leftPanel.add(employeeButton, gbc_employeeButton);
		
		returnButton = new JButton("Tilbage");
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
		
		
		nextButton = new JButton("Næste");
		GridBagConstraints gbc_nextButton = new GridBagConstraints();
		gbc_nextButton.insets = new Insets(0, 0, 0, 5);
		gbc_nextButton.gridx = 5;
		gbc_nextButton.gridy = 0;
		bottomPanel.add(nextButton, gbc_nextButton);
		
		exitButton = new JButton("Afslut");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(0, 0, 0, 5);
		gbc_exitButton.fill = GridBagConstraints.BOTH;
		gbc_exitButton.gridx = 6;
		gbc_exitButton.gridy = 0;
		bottomPanel.add(exitButton, gbc_exitButton);
		
		assetButton.addActionListener(e -> setNewCenterPanel(new AssetOverview(frame)));
		workOrderButton.addActionListener(e -> setNewCenterPanel(new WorkOrderOverview(frame)));
		employeeButton.addActionListener(e -> setNewCenterPanel(new EmployeeOverview()));
		returnButton.addActionListener(e -> {if(backwards.peek() != null) {backwardsButton();}});
		nextButton.addActionListener(e -> {if(forward.isEmpty() != true) {forwardButton();}});
		
		
		
	}
}
