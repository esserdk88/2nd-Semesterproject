package Controller;

import java.util.Calendar;

import model.Asset;
import model.Employee;
import model.Maintenance;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderControllerIF {
	
	public Asset findAssetByID(int id);
	
	public boolean createWorkOrder(Asset asset, String title, String description,
		      Calendar startDate, boolean repeated, int intervalDayCount, 
		      short priority);
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	
	public Employee findEmployeeByID(int employeeID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
	
}
