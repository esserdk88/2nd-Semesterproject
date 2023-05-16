package Controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.Database;
import dal.DatabaseConnection;
import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Asset;
import model.Employee;
import model.Workorder;

public class WorkOrderController implements WorkOrderControllerIF {
	
	protected WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	
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
		tempList = Database.getInstance().getWorkOrderDataBase().getAllUnfinishedWorkOrders();
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
	public boolean switchEmployeeWorkorders(Workorder firstWorkorder, Workorder secondWorkorder) throws Exception {
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
			
		    
		    //Starting Transaction
			DatabaseConnection.getInstance().startTransaction();
			
			//Updating both workorders
			boolean first = workOrderDB.updateWorkorder(firstWorkorder);
			boolean second = workOrderDB.updateWorkorder(secondWorkorder);
			
			//If nothing failed commitTransaction;
			DatabaseConnection.getInstance().commitTransaction();
		return first && second;
	}
	
	public boolean workorderHasEmployee(int workorderId) throws SQLException {
		boolean hasEmployee = false;
		if(workOrderDB.getWorkorderById(workorderId) != null) {
			hasEmployee = true;
		}
		return hasEmployee;
	}
	
	

}
