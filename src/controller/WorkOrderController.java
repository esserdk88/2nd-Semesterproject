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

public class WorkOrderController implements WorkOrderControllerIF {
	
	protected WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	protected SparepartUsedDBIF sparePartUsedDB = Database.getInstance().getSparepartUsedDataBase();
	protected MeasurementDBIF measurementDB = Database.getInstance().getMeasurementDataBase();
	
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

	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Workorder workOrder) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Workorder> getAllUnfinishedWorkOrders() {
		List<Workorder> tempList = new ArrayList<>();
		tempList = workOrderDB.getAllUnfinishedWorkOrders();
		return tempList;
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return workOrderDB.getAllWorkOrdersByAssetID(assetID);
	}
	
	public Workorder getWorkorderByID(int workorderId) throws SQLException {
		Workorder workorder = workOrderDB.getWorkorderById(workorderId);
		return workorder;
	}

	@Override
	public boolean switchEmployeeWorkorders(Workorder firstWorkorder, Workorder secondWorkorder, boolean concurrencyTest) throws Exception {
		System.out.println("Switching Employee");
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
	
	public boolean workorderHasEmployee(int workorderId) throws SQLException {
		boolean hasEmployee = false;
		if(workOrderDB.getWorkorderById(workorderId) != null) {
			hasEmployee = true;
		}
		return hasEmployee;
	}
	
	@Override
	public List<Measurement> getAllMeasurementsUsedInWorkOrder(int workdOrderID) {
		List<Measurement> tempList = new ArrayList<>();
		tempList = measurementDB.findMeasurementsByWorkOrderID(workdOrderID);
		return tempList;
		
	}
	
	@Override
	public List<SparepartUsed> getAllSparepartsUsedInWorkOrder(int workOrderID) {
		List<SparepartUsed> tempList = new ArrayList<>();
		tempList = sparePartUsedDB.findSparepartListByWorkorderID(workOrderID);
		return tempList;
		
	}
}
