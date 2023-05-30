package dal.interfaces;

import java.sql.SQLException;
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

	// Added from communication diagram Create Work Order
	public boolean addMaintenanceWorkOrder(Maintenance workOrder);

	public boolean addServiceWorkOrder(Service workOrder);

	public boolean addRepairWorkOrder(Repair workOrder);

	public Maintenance findMaintenanceWorkOrderByID(int workOrderID);

	public Service findServiceWorkOrderByID(int workOrderID);

	public Repair findRepairWorkOrderByID(int workOrderID);

	public List<Maintenance> getAllMaintenanceWorkOrders();

	public List<Service> getAllServiceWorkOrders();

	public List<Repair> getAllRepairWorkOrders();

	public List<Workorder> getAllUnfinishedWorkOrders();

	public List<Workorder> getAllWorkOrdersByAssetID(int assetID);
	
	public List<Workorder> getAllWorkorders() throws SQLException;

	// Swtich employee Workorders
	public List<Workorder> getWorkordersById(int[] workorderIds);

	public Workorder getWorkorderById(int workorderId) throws SQLException;

	public boolean updateWorkorder(Workorder workorder);

	public boolean deleteWorkOrderByID(int workOrderID);

	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);

	public int getLatestKey();
	
	public List<Workorder> searchWorkOrderDataBase(String name, List<Short> priority, String location, boolean isFinished);

}
