package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import controller.interfaces.RepairControllerIF;
import dal.interfaces.WorkOrderDBIF;
import model.Asset;
import model.Reference;
import model.Repair;
import model.Service;
import model.Workorder;

/**
 * The RepairController class extends WorkOrderController and implements RepairControllerIF, providing
 * methods for creating and finding repair work orders.
 */
public class RepairController extends WorkOrderController implements RepairControllerIF {
	
	// A constructor for the `RepairController` class that calls the constructor of its superclass
	// (`WorkOrderController`) using the `super()` keyword. This ensures that any initialization done in
	// the superclass constructor is also performed for the `RepairController` object.
	public RepairController() {
		super();
	}
	
	/**
	 * The function creates a repair work order and throws an exception if the asset is null.
	 * 
	 * @param repair The parameter "repair" is an object of the class "Repair" which contains information
	 * about a repair work order, such as the asset to be repaired, the description of the problem, the
	 * priority level, etc. The method "createWorkOrder" takes this object as input and creates a new work
	 * @return The method is returning a boolean value, which indicates whether the creation of a repair
	 * work order was successful or not.
	 */
	@Override
	public boolean createWorkOrder(Repair repair) {
		// we have to check if the asset is null.
		if(repair.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
				
		return workOrderDB.addRepairWorkOrder(repair);
	}
	
	/**
	 * This function creates a work order for a given asset with specified details and adds it to the
	 * database.
	 * 
	 * @param assetID The ID of the asset for which the work order is being created.
	 * @param title A string representing the title of the work order. It should briefly describe the
	 * issue or task that needs to be addressed.
	 * @param startDate startDate is a Calendar object that represents the start date of the repair work
	 * order. It is used to specify when the repair work order should begin.
	 * @param priority A short value representing the priority of the work order. The higher the value,
	 * the higher the priority.
	 * @param description A brief description of the repair work order.
	 * @param referenceCVR referenceCVR is an integer parameter that represents the CVR (Central Business
	 * Register) number of the company that requested the repair work order.
	 * @return The method is returning a boolean value, which indicates whether the creation of a repair
	 * work order was successful or not.
	 */
	
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int referenceCVR) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Repair repair = new Repair();
		repair.setAsset(asset);
		repair.setTitle(title);
		repair.setStartDate(startDate);
		repair.setPriority(priority);
		repair.setDescription(description);
		
		//TODO fix this when Reference Controller is made
		Reference reference = new Reference();
		reference.setCvr(referenceCVR);
		repair.setReference(reference);
		
		return workOrderDB.addRepairWorkOrder(repair);
	}
	
	/**
	 * This Java function finds a repair work order by its ID.
	 * 
	 * @param workOrderID The parameter "workOrderID" is an integer value that represents the unique
	 * identifier of a work order. This method is used to find a repair work order by its ID.
	 * @return A Repair object is being returned.
	 */
	@Override
	public Repair findWorkOrderByID(int workOrderID) {
		return workOrderDB.findRepairWorkOrderByID(workOrderID);
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