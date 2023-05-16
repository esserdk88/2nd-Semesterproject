package dal;


import java.util.List;

import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Service;
import model.Workorder;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface WorkOrderDBIF {
	
	//Added from communication diagram Create Work Order
	public boolean addMaintenanceWorkOrder (Maintenance workOrder);
	public boolean addServiceWorkOrder (Service workOrder);
	public boolean addRepairWorkOrder (Repair workOrder);
	
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
	public Service findServiceWorkOrderByID(int workOrderID);
	public Repair findRepairWorkOrderByID(int workOrderID);
	
	public List<Maintenance> getAllMaintenanceWorkOrders();
	public List<Service> getAllServiceWorkOrders();
	public List<Repair> getAllRepairWorkOrders();
	public List<Workorder> getAllUnfinishedWorkOrders();
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID);
	//Swtich employee Workorders
	public List<Workorder> getWorkordersById(int[] workorderIds);
	
	public boolean updateWorkorder(Workorder workorder);
	
	public boolean deleteWorkOrderByID(int workOrderID);
	public boolean deleteWorkOrderTestData(short ID);
	
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
	public int getLatestKey();
	
}
