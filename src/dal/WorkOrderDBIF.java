package dal;

import java.util.List;

import model.Employee;
import model.Maintenance;
import model.Workorder;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderDBIF {
	
	//Added from communication diagram Create Work Order
	public boolean addWorkOrder (Maintenance workOrder); 
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	
	public List<Maintenance> getAllMaintenanceWorkOrders();
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
}
