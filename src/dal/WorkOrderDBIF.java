package dal;

import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Employee;
import model.Location;
import model.Maintenance;
import model.Measurement;
import model.SparepartUsed;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderDBIF {
	
	//Added from communication diagram Create Work Order
	public boolean addWorkOrder (Maintenance workOrder); 
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
}
