package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import controller.interfaces.ServiceControllerIF;
import dal.interfaces.WorkOrderDBIF;
import model.Asset;
import model.Reference;
import model.Service;
import model.Workorder;

/**
 * The ServiceController class extends WorkOrderController and implements ServiceControllerIF,
 * providing methods to create, find, and retrieve work orders related to services.
 */
public class ServiceController extends WorkOrderController implements ServiceControllerIF {
	
	// A constructor for the ServiceController class that calls the constructor of its superclass
	// (WorkOrderController) using the `super()` keyword. This ensures that any initialization code in the
	// superclass constructor is executed before the ServiceController constructor.
	public ServiceController() {
		super();
	}
	
	/**
	 * This Java function creates a work order for a service and throws an exception if the asset is null.
	 * 
	 * @param service The parameter "service" is an object of the Service class, which contains
	 * information about a service request made by a customer. This method is responsible for creating a
	 * work order for the service request.
	 * @return The method is returning a boolean value, which indicates whether the creation of a work
	 * order for the given service was successful or not.
	 */
	@Override
	public boolean createWorkOrder(Service service) {
		// we have to check if the asset is null.
		if(service.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}			
		return workOrderDB.addServiceWorkOrder(service);
	}

	/**
	 * This function creates a work order for a given asset with specified details and a reference.
	 * 
	 * @param assetID The ID of the asset for which the work order is being created.
	 * @param title A string representing the title of the work order.
	 * @param startDate A Calendar object representing the start date of the work order.
	 * @param priority A short value representing the priority of the work order. The higher the value,
	 * the higher the priority.
	 * @param description A brief description of the work order that needs to be created.
	 * @param referenceCVR The referenceCVR parameter is an integer representing the CVR (Central Business
	 * Register) number of the company that the work order is associated with.
	 * @return The method is returning a boolean value, which indicates whether the creation of the work
	 * order was successful or not.
	 */
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int referenceCVR) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Service service = new Service();
		service.setAsset(asset);
		service.setTitle(title);
		service.setStartDate(startDate);
		service.setPriority(priority);
		service.setDescription(description);
		
		//TODO fix this when Reference Controller is made
		Reference reference = new Reference();
		reference.setCvr(referenceCVR);
		service.setReference(reference);
		
		return workOrderDB.addServiceWorkOrder(service);
	}

	/**
	 * This Java function finds a service work order by its ID using a work order database.
	 * 
	 * @param workOrderID The parameter "workOrderID" is an integer value that represents the unique
	 * identifier of a work order. This method is used to find a service work order by its ID.
	 * @return A Service object is being returned.
	 */
	@Override
	public Service findWorkOrderByID(int workOrderID) {
		return workOrderDB.findServiceWorkOrderByID(workOrderID);
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
