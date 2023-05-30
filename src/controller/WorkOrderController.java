package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.WorkOrderControllerIF;
import dal.WorkOrderDB;
import dal.interfaces.MeasurementDBIF;
import dal.interfaces.SparepartUsedDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.Asset;
import model.Employee;
import model.Measurement;
import model.SparepartUsed;
import model.Workorder;

/**
 * The WorkOrderController class is responsible for managing work orders, assigning employees to work
 * orders, searching for work orders, and retrieving measurements and spare parts used in work orders.
 */
public class WorkOrderController implements WorkOrderControllerIF {
	
	protected WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	protected SparepartUsedDBIF sparePartUsedDB = Database.getInstance().getSparepartUsedDataBase();
	protected MeasurementDBIF measurementDB = Database.getInstance().getMeasurementDataBase();
	
	// A constructor for the WorkOrderController class. It is called when an object of the class is
	// created and initializes the WorkOrderDB, SparepartUsedDB, and MeasurementDB objects from the
	// Database singleton instance.
	public WorkOrderController() {
	}
	
	@Override
	public Asset findAssetByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * This Java function assigns an employee to a work order and updates the work order in the database.
	 * 
	 * @param employee An object of the Employee class representing the employee being assigned to the
	 * work order.
	 * @param workOrder A Workorder object representing a work order that needs to be assigned to an
	 * employee.
	 * @return The method is returning a boolean value, which is the result of calling the
	 * `updateWorkorder` method of the `workOrderDB` object with the updated `workOrder` object as a
	 * parameter.
	 */
	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Workorder workOrder) {
		workOrder.setEmployee(employee);
		return workOrderDB.updateWorkorder(workOrder);
	}
	
	/**
	 * This function returns a list of all unfinished work orders.
	 * 
	 * @return A list of unfinished work orders.
	 */
	public List<Workorder> getAllUnfinishedWorkOrders() {
		List<Workorder> tempList = new ArrayList<>();
		tempList = workOrderDB.getAllUnfinishedWorkOrders();
		return tempList;
	}

	/**
	 * This function searches a work order database based on name, priority, location, and finished status
	 * and returns a list of matching work orders.
	 * 
	 * @param name A string representing the name of the work order being searched for.
	 * @param priority A list of Short values representing the priority levels of the work orders to be
	 * searched. The method will return work orders that match any of the priority levels in the list.
	 * @param location The location parameter is a String that represents the location of the work order
	 * being searched for in the database. It could be a specific address, building, or room number.
	 * @param isFinished A boolean value indicating whether the work order has been finished or not. If
	 * set to true, the method will only return work orders that have been marked as finished. If set to
	 * false, the method will return both finished and unfinished work orders.
	 * @return A list of Workorder objects that match the search criteria specified by the parameters.
	 */
	public List<Workorder> searchWorkorderDataBase(String name, List<Short> priority, String location, boolean isFinished){
		List<Workorder> tempList;
		tempList = workOrderDB.searchWorkOrderDataBase(name, priority, location, isFinished);
		return tempList;
	}

	/**
	 * This function returns a list of all work orders associated with a given asset ID.
	 * 
	 * @param assetID The assetID parameter is an integer value that represents the unique identifier of
	 * an asset. This method retrieves all work orders associated with the specified assetID.
	 * @return A list of Workorder objects that correspond to a specific asset ID.
	 */
	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return workOrderDB.getAllWorkOrdersByAssetID(assetID);
	}
	
	/**
	 * This function retrieves all work orders from a database and returns them as a list, or an empty
	 * list if there is an SQL exception.
	 * 
	 * @return A list of Workorder objects is being returned. If there is an SQL exception, an empty list
	 * is returned instead.
	 */
	public List<Workorder> getAllWorkOrders() {
		try {
			return workOrderDB.getAllWorkorders();
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<Workorder>();
		}
	}
	
	/**
	 * This Java function retrieves a work order by its ID from a database and returns it.
	 * 
	 * @param workorderId The parameter "workorderId" is an integer value that represents the unique
	 * identifier of a work order. This method retrieves a work order from a database by its ID and
	 * returns it as a Workorder object.
	 * @return A Workorder object is being returned.
	 */
	public Workorder getWorkorderByID(int workorderId) throws SQLException {
		Workorder workorder = workOrderDB.getWorkorderById(workorderId);
		return workorder;
	}

	/**
	 * This Java function switches the employees assigned to two work orders and updates them in the
	 * database, with an optional concurrency test.
	 * 
	 * @param firstWorkorder A Workorder object representing the first workorder to be switched with the
	 * second workorder.
	 * @param secondWorkorder The second workorder object that will have its employee switched with the
	 * employee of the first workorder.
	 * @param concurrencyTest A boolean value that indicates whether or not to simulate a concurrency test
	 * by adding a delay of 3 seconds before updating the workorders in the database.
	 * @return The method is returning a boolean value which is the result of whether both workorders were
	 * successfully updated in the database or not.
	 */
	@Override
	public boolean switchEmployeeWorkorders(Workorder firstWorkorder, Workorder secondWorkorder, boolean concurrencyTest) throws Exception {
		//Make sure that both workorders aren't null
		if(firstWorkorder == null || secondWorkorder == null) {
			throw new Exception("One or both workorders are null"); 
		}
		//Make sure that both workorders have an employee
		if(firstWorkorder.getEmployee() == null || secondWorkorder.getEmployee() == null) {
			throw new Exception("One or both workorders are missing an employee");
		}
			//Creating temp Employee
			Employee tempEmployee = firstWorkorder.getEmployee();
			
			//Setting first Workorder Employee to Second Workorder Employee
		    firstWorkorder.setEmployee(secondWorkorder.getEmployee());
		    
		    //Setting Second Workorder to Temporay Employee From Workorder one
		    secondWorkorder.setEmployee(tempEmployee);
			
		    boolean first = false;
		    boolean second = false;
		    //Starting Transaction
		    try {
		    	if(concurrencyTest) {
		    		Thread.sleep(3000);
		    	}
		    	DatabaseConnection.getInstance().startTransaction();
				
				//Updating both workorders
				first = workOrderDB.updateWorkorder(firstWorkorder);
				second = workOrderDB.updateWorkorder(secondWorkorder);
				
				//If nothing failed commitTransaction;
				DatabaseConnection.getInstance().commitTransaction();
		    }
			catch (Exception e) {
				//if something failed rollbackTransaction
				DatabaseConnection.getInstance().rollbackTransaction();
			}
		return first && second;
	}
	
	/**
	 * The function checks if a work order has an employee assigned to it.
	 * 
	 * @param workorderId an integer representing the ID of a work order.
	 * @return A boolean value indicating whether a work order has an employee assigned to it or not.
	 */
	public boolean workorderHasEmployee(int workorderId) throws SQLException {
		boolean hasEmployee = false;
		//TODO: this method does not check to see if an employee is assigned to workorder. why not?
		if(workOrderDB.getWorkorderById(workorderId) != null) {
			hasEmployee = true;
		}
		return hasEmployee;
	}
	
	/**
	 * This Java function returns a list of measurements used in a specific work order.
	 * 
	 * @param workdOrderID The parameter "workdOrderID" is an integer value representing the ID of a work
	 * order. This method retrieves all the measurements that are associated with the specified work order
	 * ID.
	 * @return A list of measurements used in a work order with the specified ID.
	 */
	@Override
	public List<Measurement> getAllMeasurementsUsedInWorkOrder(int workdOrderID) {
		List<Measurement> tempList = new ArrayList<>();
		tempList = measurementDB.findMeasurementsByWorkOrderID(workdOrderID);
		return tempList;
		
	}
	
	/**
	 * This function retrieves a list of spare parts used in a specific work order.
	 * 
	 * @param workOrderID an integer representing the ID of a work order for which the list of spare parts
	 * used needs to be retrieved.
	 * @return A list of SparepartUsed objects that are associated with a specific work order ID.
	 */
	@Override
	public List<SparepartUsed> getAllSparepartsUsedInWorkOrder(int workOrderID) {
		List<SparepartUsed> tempList = new ArrayList<>();
		tempList = sparePartUsedDB.findSparepartListByWorkorderID(workOrderID);
		return tempList;
		
	}
}
