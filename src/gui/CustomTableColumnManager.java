package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;


public class CustomTableColumnManager 
	implements MouseListener, ActionListener, TableColumnModelListener{
	
	private JTable table;
	private TableColumnModel tcm;
	private JPopupMenu popUp;
	private ArrayList<Boolean> isVisible;
	private ArrayList<String> columnNames;
	private List<TableColumn> allColumns;
	
	/**
	 * Constructor for ColumnManager
	 * @param table The table to Manage
	 */
	public CustomTableColumnManager(JTable table) {
		this.table = table;
		table.getTableHeader().addMouseListener(this);
		reset();
	}
	
	/**
	 * Reset the Manager, resets listener and adds Columns anew
	 */
	private void reset() {
		table.getColumnModel().removeColumnModelListener(this);
		tcm = table.getColumnModel();
		tcm.addColumnModelListener(this);
		
		int count = tcm.getColumnCount();
		allColumns = new ArrayList<>(count);
		for(int i = 0; i < count; i++) {
			allColumns.add(tcm.getColumn(i));
		}
	}
	
	/**
	 * Get a specific column from its Header
	 * @param name The header of the column to get
	 * @return The header, null if none found
	 */
	public TableColumn getColumn(String name) {

		TableColumn column = null;
		for(TableColumn c : allColumns) {
			if(c.getHeaderValue().equals(name)) {
				column = c;
				break;
			}
		}
		return column;
	}
	
	/**
	 * Hide a column from the table view
	 * If Column count is 0 The column on index 0 is displayed
	 * @param columnName The column to be hidden
	 */
	public void hideColumn(Object columnName) {
		if(columnName == null) return;
		
		for(int i = 0; i < tcm.getColumnCount(); i++) {
			TableColumn column = tcm.getColumn(i);
			if(columnName.equals(column.getHeaderValue())){
				if(tcm.getColumnCount() == 1) {
					showColumn(allColumns.get(0).getHeaderValue());
				}
				tcm.removeColumnModelListener(this);
				tcm.removeColumn(column);
				tcm.addColumnModelListener(this);
			}
		}
	}
	
	/**
	 * Show a column in the table
	 * @param columnName The Header of the column to be shown
	 */
	public void showColumn(Object columnName) {
		for(TableColumn column : allColumns) {
			if(column.getHeaderValue().equals(columnName)) {
				showColumn(column);
				break;
			}
		}
	}
	
	/**
	 * Adds a column to the table and resets column listener
	 * @param column The column to be shown
	 */
	private void showColumn(TableColumn column)
	{
		tcm.removeColumnModelListener(this);
		tcm.addColumn(column);
		int position = allColumns.indexOf( column );
		int from = tcm.getColumnCount() - 1;
		int to = 0;

		for(int i = position - 1; i > -1; i--){
			try
			{
				TableColumn visibleColumn = allColumns.get( i );
				to = tcm.getColumnIndex( visibleColumn.getHeaderValue() ) + 1;
				break;
			}
			catch(IllegalArgumentException e) {}
		}

		tcm.moveColumn(from, to);

		tcm.addColumnModelListener(this);
	}
	
	/**
	 *	Called if a Column was added to the table
	 *	If it doesn't already exist 
	 */
	public void columnAdded(TableColumnModelEvent e) {
		TableColumn toBeAdded = tcm.getColumn(e.getToIndex());
		if (!allColumns.contains(toBeAdded)){
			allColumns.add(toBeAdded);
		}
			
	}
	
	/**
	 *	Called if a column was moved
	 *	Adds column to List of columns, and sets its place in list
	 */
	public void columnMoved(TableColumnModelEvent e) {
		if (e.getFromIndex() == e.getToIndex()) return;
		
		int index = e.getToIndex();
		TableColumn column = tcm.getColumn(index);
		allColumns.remove(column);

		if (index == 0)
		{
			allColumns.add(0, column);
		}
		else
		{
			index--;
			TableColumn visibleColumn = tcm.getColumn(index);
			int insertionColumn = allColumns.indexOf(visibleColumn);
			allColumns.add(insertionColumn + 1, column);
		}
		
	}
	
	/**
	 *	Creates a Popup menu for the table header to open a Dialog
	 */
	private void createPopup(MouseEvent e) {
		popUp = new JPopupMenu();
		JMenuItem addColumn = new JMenuItem("Tilføj kolonne");
		popUp.add(addColumn);
		addColumn.addActionListener(this);
		ArrayList<String> columnNames = new ArrayList<>();
		ArrayList<Boolean> isVisible = new ArrayList<>();
		
		for(TableColumn c : allColumns){
			Object header = c.getHeaderValue();
			columnNames.add(header.toString());
			try
			{
				tcm.getColumnIndex(header);
				isVisible.add(true);
			}
			catch(IllegalArgumentException exception)
			{
				isVisible.add(false);
			}
		}
		this.columnNames = columnNames;
		this.isVisible = isVisible;
		popUp.show(e.getComponent(),e.getX(), e.getY());
		
	}
	
	/**
	 *	Mouselistener pressed
	 */
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == 3 && e.isPopupTrigger()) {
			createPopup(e);
		}
	}

	/**
	 *	Mouselistener released
	 */
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == 3 && e.isPopupTrigger()) {
			createPopup(e);
		}
	}
	
	/**
	 *	Create Dialog for hiding and showing columns
	 */
	public void actionPerformed(ActionEvent e) {
		ColumnSelecter cs = new ColumnSelecter(this);
		cs.newWindow(isVisible, columnNames);
		cs.setVisible(true);
		
	}

	//Extra interface methods
	public void columnRemoved(TableColumnModelEvent e) {}
	public void columnMarginChanged(ChangeEvent e) {}
	public void columnSelectionChanged(ListSelectionEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}

}
