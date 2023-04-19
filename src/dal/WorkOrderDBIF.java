package dal;

import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Employee;
import model.Location;
import model.Measurement;
import model.SparepartUsed;


public interface WorkOrderDBIF {
	//TODO: How do we get the task and Asset assosiated in the database? - AssetID?

	//CRUD
	public boolean createWorkOrder(int workOrderID, String title, String type, Calendar startDate, Calendar endDate, short priority,
			String description, boolean finished, List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee,
			List<Measurement> measurements);
	public Asset readAssetByID(int id);
	
	//TODO updateWorkOrder
	
	public boolean deleteAssetByID(int id);
	
	//More
	public List<Asset> getAllAssets();

}
