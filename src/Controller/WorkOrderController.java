package Controller;

import java.sql.Connection;
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
	
	//TODO we need a method to return a single generic workorder
	public Workorder getWorkorderByID(int workorderId) {
		return workOrderDB.getWorkordersById(new int[] {workorderId}).get(0);
	}

	//TODO use throws instead. handle in gui
	@Override
	public boolean switchEmployeeWorkorders(Workorder firstWorkorder, Workorder secoundWorkorder) throws Exception {
		boolean success = false;
		Workorder workorderOne = null;
		Workorder workorderTwo = null;
		if(firstWorkorder != null && secoundWorkorder != null) {
			workorderOne = firstWorkorder;
			workorderTwo = secoundWorkorder;
		}
		else {
			throw new Exception("One or both workorders are null"); 
		}
		
		//Make sure that both workorders have an employee
		if(workorderOne.getEmployee() != null && workorderTwo.getEmployee() != null) {
			System.out.println("Inside switch with employees");
			Employee employeeOne = workorderOne.getEmployee();
			Employee employeeTwo = workorderTwo.getEmployee();
			
			workorderOne.setEmployee(employeeTwo);
			workorderTwo.setEmployee(employeeOne);
			
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			workOrderDB.updateWorkorder(workorderOne);
			workOrderDB.updateWorkorder(workorderTwo);	
			success = true;
		}
		else {
			throw new Exception("One or both workorders are missing an employee");
		}
		
		return success;
	}
	
	public boolean workorderHasEmployee(int workorderId) {
		boolean hasEmployee = false;
		if(workOrderDB.getWorkordersById(new int[] {workorderId}).get(0).getEmployee() != null) {
			hasEmployee = true;
		}
		return hasEmployee;
	}
	
	

}
