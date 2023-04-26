package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class DefaultTable extends JTable {
	
	//Table Fields
	private DefaultTableModel tabelModel;
	private int[] rows;
	private CustomTableColumnManager tcm;
	
	//Data
	private String[] columns;
	private boolean[] visibleColumns;
	
	
	/**
	 * Basic Table constructor, Data and column names
	 * @param data The Data to be displayed
	 * @param columns The column headers
	 */
	public DefaultTable(String[][] data, String[] columns) {
		this.columns = columns;
		boolean[] visibleColumns = new boolean[columns.length];
		for(int i = 0; i<columns.length;i++) {
			visibleColumns[i] = true;
		}
		this.visibleColumns = visibleColumns;
		initializeTable(data, columns, visibleColumns);
	}
	
	/**
	 * Basic Table constructor with specefications for visibleColumns on initialization
	 * @param data The Data to be displayed
	 * @param columns The column headers
	 * @param visibleColumns The column you want visible set to true and false to be hidden
	 */
	public DefaultTable(String[][] data, String[] columns, boolean[] visibleColumns) {
		this.columns = columns;
		this.visibleColumns = visibleColumns;
		initializeTable(data, columns, visibleColumns);
	}
	/**
	 * Initializes the class
	 * @param data The Data to be displayed
	 * @param columns The column headers
	 * @param visibleColumns The column you want visible set to true and false to be hidden
	 */
	private void initializeTable(String[][] data, String[] columns, boolean[] visibleColumns) {
		tabelModel = new DefaultTableModel(data, columns);
		this.setCellSelectionEnabled(false);
		this.setRowSelectionAllowed(true);
		this.getTableHeader().setOpaque(false);
		this.getTableHeader().setBackground(new Color(68, 114, 196));
		this.getTableHeader().setForeground(Color.WHITE);
		this.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		tcm = new CustomTableColumnManager(this);
		setModel(tabelModel);
		setDefaultEditor(Object.class, null);
		rows = new int[0];
		
		getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				rows = getSelectedRows();
			}
		});
		for(int i = 0; i < visibleColumns.length; i++) {
			if(!visibleColumns[i]) {
				tcm.hideColumn(columns[i]);
			}
		}
	}
	
	/**
	 *	Renderer for table, changes every other rows color
	 */
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);
		if (!isRowSelected(row)) {
			c.setBackground(row % 2 == 0 ? getBackground() : new Color(205,212,234));
			c.setBackground(row % 2 == 1 ? getBackground() : new Color(233, 236, 245));
		}else {
			c.setBackground(new Color(235,177,129));
		}
		return c;
	}
	
	/**
	 * Insert new data into the existing array using a Multidimensional array
	 * @param data Data to be displayed
	 */
	public void setNewData(Object[][] data) {
		tabelModel.setRowCount(0);
		if(data == null) {
			tabelModel.addRow(new Object[] {null});
		}
		int len = data.length;
		for(int i = 0; i < len; i++) {
			tabelModel.addRow(data[i]);
		}
	}
	/**
	 * Get index from selected row 0
	 * @return The first row that was selected
	 */
	public int findElement() {
		if(rows.length != 0) {
			return this.getSelectedRows()[0];
		}
		else {
			return -1;
		}
	}
	
	/**
	 * Add just one row to the table
	 * @param data Data for the row to be added in 
	 */
	public void addRow(Object[] data) {
		tabelModel.addRow(data);
	}
	
	/**
	 * Clear the table columns
	 */
	public void clear() {
		tabelModel.setRowCount(0);
	}
	/**
	 * Delete selected items from the table, requires confirmation from user
	 * @param column The column data you wish to receive
	 * @param columnsConfirm Columns to show the user during confirmation
	 * @return The data that was delete as a ArrayList string array
	 */
	public ArrayList<String> deleteData(String column, int[] columnsConfirm) {
		ArrayList<String> dataToDelete = new ArrayList<>();
		if(rows.length != 0) {			
			boolean check = deleteItems(rows.length, columnsConfirm);
			if(check == true) {
				for(int i = rows.length-1; i>= 0;i--) {
					dataToDelete.add(tabelModel.getValueAt(rows[i],tcm.getColumn(column).getModelIndex()).toString());
					tabelModel.removeRow(rows[i]);
				}
			}
		}
		else {
			GUIPopUpMessages.informationMessage("Marker én linje der skal slettes", "Bekræft slet");
		}
		return dataToDelete;
	}
		
	/**
	 * Confirmation for deleting elements
	 * @param amountQty Amount to delete
	 * @param columns Used to display for the user items being deleted
	 * @return returns true if the user wishes to delete, false if not
	 */
	private boolean deleteItems(int amountQty, int[] columns) {
		
		StringBuilder products = new StringBuilder();
		
		for(int i = 0; i < amountQty; i++) {
			for(int x = 0; x < columns.length; x++) {
				if(columns[x] <= tabelModel.getColumnCount() && columns[x] >= 0) {
				products.append(tabelModel.getValueAt(rows[i],columns[x])+ "\t");
				}
			}
			
			products.append("\n");
		}
		
		JLabel label = new JLabel("Følgende valgte slettes:");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(label, BorderLayout.NORTH);
		
		JTextArea textArea = new JTextArea(products.toString());
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.WEST);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true); 
		scrollPane.setPreferredSize(new Dimension(350, 150));
		
		int input = JOptionPane.showOptionDialog(new JFrame(), panel, "Slet valgte",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null,
					new Object[] {Box.createHorizontalStrut(250), "OK", "Afbryd"}, JOptionPane.YES_OPTION);
					
					
		return input == 1;
	}
	
	
	/**
	 * Sets which Columns should be visible to the user
	 * @param newColumn Index of columns to be shown or hidden from the user
	 */
	public void setVisibleColumns(boolean[] newColumn) {
		
		for(int i = 0; i < newColumn.length; i++) {
			if(!visibleColumns[i] && newColumn[i] != visibleColumns[i]) {
				tcm.showColumn(columns[i]);
			}else if(visibleColumns[i] && newColumn[i] != visibleColumns[i]){
				tcm.hideColumn(columns[i]);
			}
		}
		visibleColumns = newColumn;
	}
}