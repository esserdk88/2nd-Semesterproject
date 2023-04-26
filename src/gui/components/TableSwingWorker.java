package gui.components;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Controller.AssetController;
import dal.AssetDB;
import model.Asset;

public class TableSwingWorker extends SwingWorker<String[][], Void>{

	private DefaultTable table;
	
	public TableSwingWorker(DefaultTable table) {
		this.table = table;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected String[][] doInBackground() throws Exception {
		AssetDB assetDatabase = new AssetDB();
		AssetController assetCtrl = new AssetController(assetDatabase);
		List<Asset> list = new ArrayList<>();
		try {
			list = assetCtrl.getAllAssets();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		int size = list.size();
		String[][] data = new String[size][12];
		for (int i = 0; i < size; i++) {
			Asset current = list.get(i);
			String dateString = dateFormat.format(current.getAquisitionDate().getTime());
			data[i][0] = Integer.toString(current.getAssetID());
			data[i][1] = current.getName();
			data[i][2] = dateString;
			data[i][3] = current.getDescription();
			data[i][4] = current.getStatus();
			data[i][5] = current.getManufacturer();
		}
		String[] columns = new String[] { "AssetID", "Navn", "Anskfaffelsesdato", "Beskrivelse", "Status",
		"Producent" };
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
