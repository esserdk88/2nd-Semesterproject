package Controller;

import java.util.ArrayList;
import java.util.List;

import dal.Database;
import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Asset;
import model.Employee;
import model.Workorder;

public class WorkOrderController implements WorkOrderControllerIF {
	
	protected WorkOrderDBIF workOrderDB = new WorkOrderDB();
	
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
	public List<Workorder> getAllWorkOrdersByAssetID() {
		// TODO Auto-generated method stub
		return null;
	}

}
