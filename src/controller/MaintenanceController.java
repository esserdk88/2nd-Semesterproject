package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import controller.interfaces.MaintenanceControllerIF;
import dal.interfaces.WorkOrderDBIF;
import model.Asset;
import model.Maintenance;
import model.Workorder;

/**
 * The MaintenanceController class extends WorkOrderController and implements MaintenanceControllerIF,
 * providing methods to create and find maintenance work orders.
 */
public class MaintenanceController extends WorkOrderController implements MaintenanceControllerIF {
	
	// A constructor for the MaintenanceController class that calls the constructor of its superclass
	// (WorkOrderController) using the `super()` keyword. This ensures that any initialization code in the
	// superclass constructor is executed before the initialization code in the MaintenanceController
	// constructor.
	public MaintenanceController() {
		super();
	}
	
	/**
	 * The function creates a maintenance work order and throws an exception if the asset is null.
	 * 
	 * @param maintenance an object of the Maintenance class that contains information about a maintenance
	 * work order, such as the asset, description, and priority.
	 * @return The method is returning a boolean value, which indicates whether the maintenance work order
	 * was successfully added to the work order database or not.
	 */
	@Override
	public boolean createWorkOrder(Maintenance maintenance) {
		// we have to check if the asset is null.
		if(maintenance.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		
		return workOrderDB.addMaintenanceWorkOrder(maintenance);
	}
	
	/**
	 * This function creates a maintenance work order for a given asset with specified details and adds it
	 * to the database.
	 * 
	 * @param assetID The ID of the asset for which the maintenance work order is being created.
	 * @param title A string representing the title of the maintenance work order.
	 * @param startDate A Calendar object representing the start date of the maintenance work order.
	 * @param priority a short value representing the priority level of the maintenance work order. The
	 * lower the value, the higher the priority.
	 * @param description A brief description of the maintenance work that needs to be done on the asset.
	 * @param intervalDayCount The number of days between each maintenance work order for the asset.
	 * @param repeated A boolean value indicating whether the maintenance work order is a repeated task or
	 * not. If it is a repeated task, the maintenance work order will be scheduled to occur at regular
	 * intervals specified by the intervalDayCount parameter.
	 * @return The method is returning a boolean value, which indicates whether the maintenance work order
	 * was successfully added to the database or not.
	 */
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int intervalDayCount, boolean repeated) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Maintenance maintenance = new Maintenance();
		maintenance.setAsset(asset);
		maintenance.setTitle(title);
		maintenance.setStartDate(startDate);
		maintenance.setPriority(priority);
		maintenance.setDescription(description);
		maintenance.setIntervalDayCount(intervalDayCount);
		maintenance.setRepeated(repeated);
		return workOrderDB.addMaintenanceWorkOrder(maintenance);
	}

	/**
	 * This Java function finds a maintenance work order by its ID.
	 * 
	 * @param workOrderID The parameter "workOrderID" is an integer value that represents the unique
	 * identifier of a maintenance work order. This method is used to find a maintenance work order by its
	 * ID.
	 * @return A Maintenance object is being returned.
	 */
	@Override
	public Maintenance findWorkOrderByID(int workOrderID) {
		return workOrderDB.findMaintenanceWorkOrderByID(workOrderID);
	}

	/**
	 * This function returns a list of all work orders associated with a given asset ID.
	 * 
	 * @param assetID The assetID parameter is an integer value that represents the unique identifier of
	 * an asset for which we want to retrieve all the work orders.
	 * @return A list of Workorder objects that correspond to a specific asset ID.
	 */
	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return super.getAllWorkOrdersByAssetID(assetID);
	}
}