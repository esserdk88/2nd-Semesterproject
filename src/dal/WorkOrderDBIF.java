package dal;

import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.AssetStatus;
import model.Location;
import model.WorkorderStatus;

public interface WorkOrderDBIF {
	//TODO: How do we get the task and Asset assosiated in the database? - AssetID?

	//CRUD
	public boolean createTask(String title, Calendar startDate, Calendar finishDate, WorkorderStatus status, String description, int priority);
	public Asset readAssetByID(int id);
	public Asset updateAsset(String taskID, String title, Calendar startDate, Calendar finishDate, WorkorderStatus status, String description, int priority);
	public boolean deleteAssetByID(int id);
	
	//More
	public List<Asset> getAllAssets();

}
