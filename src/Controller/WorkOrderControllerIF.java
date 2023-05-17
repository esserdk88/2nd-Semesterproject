package Controller;

import java.sql.SQLException;
import java.util.List;

import model.Asset;
import model.Employee;
import model.Measurement;
import model.SparepartUsed;
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
	public boolean switchEmployeeWorkorders(Workorder workorderOne, Workorder workorderTwo) throws Exception;
	
	//probably needs to throw exceptions
	public boolean workorderHasEmployee(int workorderId) throws SQLException;
	
	public Workorder getWorkorderByID(int workorderId) throws SQLException;

	public List<Measurement> getAllMeasurementsUsedInWorkOrder(int workdOrderID);
	
	public List<SparepartUsed> getAllSparepartsUsedInWorkOrder(int workOrderID);
}
