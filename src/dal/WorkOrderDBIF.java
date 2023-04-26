package dal;


import java.util.List;

import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Workorder;
import model.Service;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderDBIF {
	
	//Added from communication diagram Create Work Order
	public boolean addMaintenanceWorkOrder (Maintenance workOrder);
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	public Service findServiceWorkOrderByID(int workOrderID);
	public Repair findRepairWorkOrderByID(int workOrderID);
	
	public List<Maintenance> getAllMaintenanceWorkOrders();
	public List<Service> getAllServiceWorkOrders();
	public List<Repair> getAllRepairWorkOrders();
	public List<Workorder> getAllUnfinishedWorkOrders();
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
}
