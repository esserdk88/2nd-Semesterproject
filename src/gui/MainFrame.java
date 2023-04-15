package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JPanel currentCenterPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(5, 5));
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		currentCenterPanel = centerPanel;
		JLabel menulabel = new JLabel("Mainmenu");
		menulabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		topPanel.add(menulabel);
		
		JPanel leftPanel = new JPanel();
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
			        JPanel center = new AssetOverview();
			        setNewCenterPanel(center);
			    }
		});
		assetButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_assetButton = new GridBagConstraints();
		gbc_assetButton.gridwidth = 2;
		gbc_assetButton.fill = GridBagConstraints.BOTH;
		gbc_assetButton.insets = new Insets(0, 0, 5, 0);
		gbc_assetButton.gridx = 0;
		gbc_assetButton.gridy = 0;
		leftPanel.add(assetButton, gbc_assetButton);
		
		JButton workOrderButton = new JButton("Arbejdsordre");
		workOrderButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_workOrderButton = new GridBagConstraints();
		gbc_workOrderButton.fill = GridBagConstraints.BOTH;
		gbc_workOrderButton.insets = new Insets(0, 0, 5, 0);
		gbc_workOrderButton.gridx = 0;
		gbc_workOrderButton.gridy = 1;
		leftPanel.add(workOrderButton, gbc_workOrderButton);
		
		JButton employeeButton = new JButton("Ansatte");
		employeeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel center = new EmployeeOverview();
		        setNewCenterPanel(center);
			}
		});
		employeeButton.setMargin(new Insets(4, 26, 4, 26));
		GridBagConstraints gbc_employeeButton = new GridBagConstraints();
		gbc_employeeButton.fill = GridBagConstraints.BOTH;
		gbc_employeeButton.gridx = 0;
		gbc_employeeButton.gridy = 2;
		leftPanel.add(employeeButton, gbc_employeeButton);
		
		JPanel bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		GridBagLayout gbl_bottomPanel = new GridBagLayout();
		gbl_bottomPanel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_bottomPanel.rowHeights = new int[]{0, 0};
		gbl_bottomPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_bottomPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		bottomPanel.setLayout(gbl_bottomPanel);
		
		JButton returnButton = new JButton("Tilbage");
		GridBagConstraints gbc_returnButton = new GridBagConstraints();
		gbc_returnButton.fill = GridBagConstraints.BOTH;
		gbc_returnButton.insets = new Insets(0, 0, 0, 5);
		gbc_returnButton.gridx = 57;
		gbc_returnButton.gridy = 0;
		bottomPanel.add(returnButton, gbc_returnButton);
		
		JButton exitButton = new JButton("Afslut");
		GridBagConstraints gbc_exitButton = new GridBagConstraints();
		gbc_exitButton.insets = new Insets(0, 0, 0, 5);
		gbc_exitButton.fill = GridBagConstraints.BOTH;
		gbc_exitButton.gridx = 58;
		gbc_exitButton.gridy = 0;
		bottomPanel.add(exitButton, gbc_exitButton);	
	}
	private void setNewCenterPanel(JPanel newPanel) {
        contentPane.remove(currentCenterPanel);
        contentPane.add(newPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
        currentCenterPanel = newPanel;
	}
}
