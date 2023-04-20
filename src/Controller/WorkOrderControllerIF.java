package Controller;

import java.util.Calendar;

import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Workorder;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderControllerIF {
	
	public Asset findAssetByID(int id);
	
	public Employee findEmployeeByID(int employeeID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Workorder workOrder);
	
}
