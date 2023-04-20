package dal;

import model.Employee;
import model.Maintenance;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderDBIF {
	
	//Added from communication diagram Create Work Order
	public boolean addWorkOrder (Maintenance workOrder); 
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
}
