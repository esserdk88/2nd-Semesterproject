package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controller.WorkOrderController;
import Controller.WorkOrderControllerIF;
import gui.components.GUIPopUpMessages;
import gui.components.JRoundedButton;
import gui.dialogBoxes.SimpleDialogBox;


public class SwitchEmployeeWorkorders extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldWoOne;
	private JTextField textFieldWoTwo;

	private JButton btnConfirm;
	private JButton btnNewButton;
	private JLabel lblHowTo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SwitchEmployeeWorkorders frame = new SwitchEmployeeWorkorders();
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
	public SwitchEmployeeWorkorders() {
		setTitle("Ombyt opgaver");
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				checkForValidWorkorder(textFieldWoOne);
				checkForValidWorkorder(textFieldWoTwo);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel west_panel = new JPanel();
		contentPane.add(west_panel, BorderLayout.WEST);
		GridBagLayout gbl_west_panel = new GridBagLayout();
		gbl_west_panel.columnWidths = new int[]{86, 0};
		gbl_west_panel.rowHeights = new int[]{20, 0, 0};
		gbl_west_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_west_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		west_panel.setLayout(gbl_west_panel);
		
		textFieldWoOne = new JTextField();
		textFieldWoOne.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkForValidWorkorder(textFieldWoOne);
				checkConfirmGreyout();
			}
		});
		GridBagConstraints gbc_textFieldWoOne = new GridBagConstraints();
		gbc_textFieldWoOne.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldWoOne.anchor = GridBagConstraints.NORTHWEST;
		gbc_textFieldWoOne.gridx = 0;
		gbc_textFieldWoOne.gridy = 0;
		west_panel.add(textFieldWoOne, gbc_textFieldWoOne);
		textFieldWoOne.setColumns(10);
		
		textFieldWoTwo = new JTextField();
		textFieldWoTwo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				checkForValidWorkorder(textFieldWoTwo);
				checkConfirmGreyout();
			}
		});
		GridBagConstraints gbc_textFieldWoTwo = new GridBagConstraints();
		gbc_textFieldWoTwo.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldWoTwo.gridx = 0;
		gbc_textFieldWoTwo.gridy = 1;
		west_panel.add(textFieldWoTwo, gbc_textFieldWoTwo);
		textFieldWoTwo.setColumns(10);
		
		JPanel east_panel = new JPanel();
		contentPane.add(east_panel, BorderLayout.EAST);
		
		JScrollPane scrollPane = new JScrollPane();
		east_panel.add(scrollPane);

		//TODO make work (maybe) - only if we want to be able to select assets to be switched between from a list
//		table_assets = new DefaultTable();
//		scrollPane.setViewport(table_assets);
//		east_panel.add(table_assets);
		
		JPanel south_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) south_panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(south_panel, BorderLayout.SOUTH);
		
		btnConfirm = new JRoundedButton("Bekræft");
		btnConfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConfirmPressed();
			}
		});
		south_panel.add(btnConfirm);
		
		btnNewButton = new JRoundedButton("Afbryd");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCanclePressed();
			}
		});
		south_panel.add(btnNewButton);
		
		JPanel center_panel = new JPanel();
		FlowLayout fl_center_panel = (FlowLayout) center_panel.getLayout();
		fl_center_panel.setAlignment(FlowLayout.LEFT);
		contentPane.add(center_panel, BorderLayout.CENTER);
		
		lblHowTo = new JLabel("Vælg to opgave id'er, hvor medearbejderne skal skifte opgaver");
		center_panel.add(lblHowTo);
		
		checkConfirmGreyout();
	}
	
	private boolean checkForValidWorkorder(JTextField textField) {
		boolean validId = false;
		if(!textField.getText().isBlank()) {
			int workorderId = -1;
			WorkOrderControllerIF workOrderController = new WorkOrderController();
			try {
				workorderId = Integer.valueOf(textField.getText());
				if(workOrderController.workorderHasEmployee(workorderId)) {
					validId = true;
					textField.setBackground(Color.green);
				}
				else {
					textField.setBackground(Color.red);
				}
			}
			catch(Exception e) {
				//temp needs JDialog
				System.out.println("could not convert textfield input to integer");
				e.printStackTrace();
			}
		}
		return validId;
	}
	
	private void checkConfirmGreyout() {
		if(checkForValidWorkorder(textFieldWoOne) && checkForValidWorkorder(textFieldWoTwo)) {
			btnConfirm.setEnabled(true);
		}
		else {
			btnConfirm.setEnabled(false);
		}
	}
	
	private void btnConfirmPressed() {
		WorkOrderControllerIF workOrderController = new WorkOrderController();
		try {
			int workorderIdOne = Integer.valueOf(textFieldWoOne.getText());
			int workorderIdTwo = Integer.valueOf(textFieldWoTwo.getText());
			if(workOrderController.switchEmployeeWorkorders(workOrderController.getWorkorderByID(workorderIdOne), workOrderController.getWorkorderByID(workorderIdTwo))) {
				this.dispose();
			}
			else {
				//give user error message
				GUIPopUpMessages.informationMessage("En eller flere værdier blev korruptet under handling" , "Database fejl");
			}
		}
		catch (Exception e) {
			GUIPopUpMessages.informationMessage("En eller flere værdier blev korruptet under handling" , "Database fejl");
			e.printStackTrace();
		}
	}

	private void btnCanclePressed() {
		this.dispose();
	}
}
