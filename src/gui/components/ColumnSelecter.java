package gui.components;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class ColumnSelecter extends JDialog {

	//Windowbuilder components
	private final JPanel contentPanel = new JPanel();
	
	//Other
	private ArrayList<JCheckBox> checkBox;
	private ArrayList<Boolean> isVisible;
	private ArrayList<String> columns;
	private CustomTableColumnManager manager;

	/**
	 * Create the dialog.
	 * 
	 */
	public ColumnSelecter(CustomTableColumnManager manager) {
		this.manager = manager;
	}
	/**
	 * The constructor of the ColumnSelecter-Object
	 * 
	 * @param isVisible an ArrayList of equal size to the columns parameter, that will describe if that index will be shown
	 * @param columns an ArrayList of equal size to the isVisible parameter, that will contain the headers of the columns
	 */
	public void newWindow(ArrayList<Boolean> isVisible, ArrayList<String> columns) {
		windowSetup(isVisible, columns);
		pack();
		setCheckBoxes();
	}
	/**
	 * This method will setup all the windowbuilder components
	 * 
	 * @param isVisible isVisible an ArrayList of equal size to the columns parameter, that will describe if that index will be shown
	 * @param columns an ArrayList of equal size to the isVisible parameter, that will contain the headers of the columns
	 */
	private void windowSetup(ArrayList<Boolean> isVisible, ArrayList<String> columns) {
		checkBox = new ArrayList<>();
		setTitle("Aktive kolonner");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		for(int i = 0; i < columns.size();i++) {
			JCheckBox temp = new JCheckBox(columns.get(i));
			contentPanel.add(temp);
			checkBox.add(temp);
		}
		
		this.columns = columns;
		this.isVisible = isVisible;
		setModal(true);
		
		
		{
			
			int rowCount = 5;
			if(columns.size() > 5) {
				rowCount = columns.size()/2;
			}
			contentPanel.setLayout(new GridLayout(rowCount,4));
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnNewButton = new JButton("Vis alt");
				btnNewButton.setMaximumSize(new Dimension(20, 20));
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						selectAll();
					}
				});
				;
				btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
				buttonPane.add(btnNewButton);
			}
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setColumnFromcheckBoxes();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						closeWindow();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	/**
	 * This method will loop through a list of checkboxes and will set each element true/false according to a list of booleans of equal size
	 */
	private void setCheckBoxes() {
			for(int i = 0; i < isVisible.size(); i++) {
					checkBox.get(i).setSelected(isVisible.get(i));
			}
			
		}
	/**
	 * This method will loop through a list of checkboxes and set all to true
	 */
	private void selectAll() {
			for(int i = 0; i < isVisible.size(); i++) {
					checkBox.get(i).setSelected(true);
			}
		}
	/**
	 * This method will show or hide a column, depending on the state of the checkboxes
	 */
	private void setColumnFromcheckBoxes() {
			for(int i = 0; i < isVisible.size(); i++) {
				if(checkBox.get(i).isSelected() != isVisible.get(i)) {
					if (checkBox.get(i).isSelected()) {
						manager.showColumn(columns.get(i));
					} else {
						manager.hideColumn(columns.get(i));
					}
				}
			}
			this.dispose();
		}
	/**
	 * This method will close the current window
	 */
	private void closeWindow() {
			this.dispose();
		}
}