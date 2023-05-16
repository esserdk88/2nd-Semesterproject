package Controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import dal.Database;
import dal.DatabaseConnection;
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

	@Override
	public boolean switchEmployeeWorkorders(int[] workOrderIds) {
		boolean success = false;
		
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			con.setAutoCommit(false);
			try {
				List<Workorder> workorders = workOrderDB.getWorkordersById(workOrderIds);
				//if more or less than two workOrders are retrieved something is wrong and success will be false
				if(workorders.size() == 2) {
					Employee empOne = workorders.get(0).getEmployee();
					Employee empTwo = workorders.get(1).getEmployee();
					Workorder wkoOne = workorders.get(0);
					Workorder wkoTwo = workorders.get(1);
					
					//switch places
					wkoOne.setEmployee(empTwo);
					wkoTwo.setEmployee(empOne);
					
					workOrderDB.updateWorkorder(wkoOne);
					workOrderDB.updateWorkorder(wkoTwo);
					
					success = true;
				}
			}	
			catch (Exception e) {
				con.rollback();
				System.out.println("switchEmployeeWorkorders - ROLLBACK");
				e.printStackTrace();
			}
		}
		//Can remove double try-catch with throws.
		catch (Exception e) {
			e.printStackTrace();
		}
		return success;
	}

}
