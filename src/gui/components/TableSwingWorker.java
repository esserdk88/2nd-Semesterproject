package gui.components;

import java.util.List;

import javax.swing.SwingWorker;

public class TableSwingWorker<T extends StringArrayConvertible> extends SwingWorker<String[][], Void> {

	private DefaultTable table;
	private List<T> list;
	
	public TableSwingWorker(DefaultTable table, List<T> list) {
		this.table = table;
		this.list = list;
	}

	@Override
	protected String[][] doInBackground() throws Exception {
		int size = list.size();
		int secondSize = 0;
		if(size != 0) {
			secondSize = list.get(0).getObjectAsStringArray().length;
		}
		String[][] data = new String[size][secondSize];
		for (int i = 0; i < size; i++) {
			data[i] = list.get(i).getObjectAsStringArray();
		}
		return data;
	}
	
	 @Override
	    protected void done() {
	        try {
	            String[][] data = get();
	            table.setNewData(data);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}
