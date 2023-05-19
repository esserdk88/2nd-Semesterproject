package gui.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class VerifiableTextFieldValue extends JDialog {

	private JPanel contentPane;
	private JLabel lblTextFieldInfo;
	private JTextField textFieldValue;
	private JTextArea textAreaContextInformation;
	private VerifiedValueRecieverIF parent;
	private ValueCheckerIF valueChecker;
	private boolean currentValueIsValid = false;
	private int storedValue = -1;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			VerifiableTextFieldValue dialog = new VerifiableTextFieldValue();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public VerifiableTextFieldValue(ValueCheckerIF valueChecker, String textFieldInfo, String contextInformation, VerifiedValueRecieverIF parent) {
		addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchForValidValue();
			}
		});
		this.valueChecker = valueChecker;
		this.parent = parent;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 345, 213);
		contentPane = new JPanel();
		contentPane.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchForValidValue();
			}
		});
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
	
		JPanel center_panel = new JPanel();
		center_panel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchForValidValue();
			}
		});
		contentPane.add(center_panel, BorderLayout.CENTER);
		GridBagLayout gbl_center_panel = new GridBagLayout();
		gbl_center_panel.columnWidths = new int[]{0, 0, 0, 197, 0};
		gbl_center_panel.rowHeights = new int[]{0, 0, 0};
		gbl_center_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_center_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		center_panel.setLayout(gbl_center_panel);
		
		lblTextFieldInfo = new JLabel("placeHolder");
		GridBagConstraints gbc_lblTextFieldInfo = new GridBagConstraints();
		gbc_lblTextFieldInfo.insets = new Insets(0, 0, 5, 5);
		gbc_lblTextFieldInfo.gridx = 1;
		gbc_lblTextFieldInfo.gridy = 0;
		center_panel.add(lblTextFieldInfo, gbc_lblTextFieldInfo);
		
		textFieldValue = new JTextField();
		textFieldValue.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchForValidValue();
			}
			@Override
			public void focusLost(FocusEvent e) {
				searchForValidValue();
			}
		});
		GridBagConstraints gbc_textFieldValue = new GridBagConstraints();
		gbc_textFieldValue.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldValue.gridx = 3;
		gbc_textFieldValue.gridy = 0;
		center_panel.add(textFieldValue, gbc_textFieldValue);
		textFieldValue.setColumns(10);
		
		JPanel south_Panel = new JPanel();
		south_Panel.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				searchForValidValue();
			}
		});
		FlowLayout flowLayout = (FlowLayout) south_Panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(south_Panel, BorderLayout.SOUTH);
		
		JButton btnOK = new JRoundedButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOKPressed();
			}
		});
		south_Panel.add(btnOK);
		
		JButton btnCancel = new JRoundedButton("Fortryd");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnCancelPressed();
			}
		});
		south_Panel.add(btnCancel);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		
		textAreaContextInformation = new JTextArea();
		panel.add(textAreaContextInformation);
		textAreaContextInformation.setEditable(false);
		textAreaContextInformation.setBackground(UIManager.getColor("Button.background"));
		
		setTextFields(textFieldInfo, contextInformation);
		this.setModal(true);
		this.setVisible(true);
	}
	
	private void btnOKPressed() {
		searchForValidValue();
		if(this.currentValueIsValid) {
			storedValue = Integer.valueOf(textFieldValue.getText());
		}
		else {
			storedValue = -1;
		}
		parent.receiveVerifiedValue(storedValue);
		closeWindow();
	}
	
	private void btnCancelPressed() {
		this.storedValue = -1;
		this.dispose();
	}
	
	private void searchForValidValue() {
		boolean valueIsValid = false;
		try {
			valueIsValid = valueChecker.validateValue(Integer.valueOf(textFieldValue.getText()));
			if(valueIsValid) {
				textFieldValue.setBackground(Color.green);
				currentValueIsValid = true;
			}
			else {
				textFieldValue.setBackground(Color.red);
				currentValueIsValid = false;
			}
		}
		catch(Exception e) {
			textFieldValue.setBackground(Color.red);
			currentValueIsValid = false;
		}
	}
	
	private void setTextFields(String textFieldInfo, String contextInformation) {
		this.lblTextFieldInfo.setText(textFieldInfo);
		this.textAreaContextInformation.setText(contextInformation);
	}
	
	public void closeWindow() {
		this.dispose();
	}

}
