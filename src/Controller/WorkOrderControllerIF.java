package Controller;

import java.util.List;

import model.Asset;
import model.Employee;
import model.Workorder;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderControllerIF {
	
	public Asset findAssetByID(int id);
	
	public Employee findEmployeeByID(int employeeID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Workorder workOrder);

	public List<Workorder> getAllWorkOrdersByAssetID(int assetID);
	
	//Switch Employee workOrders
	public boolean switchEmployeeWorkorders(int[] workOrderIds);
	
}
