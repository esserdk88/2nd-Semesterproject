package Controller;

import model.Asset;
import model.Employee;
import model.Workorder;

public abstract class WorkOrderController implements WorkOrderControllerIF {
	
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

}
