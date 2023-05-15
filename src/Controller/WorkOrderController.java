package Controller;

import java.util.ArrayList;
import java.util.List;

import dal.Database;
import dal.MeasurementDBIF;
import dal.SparepartUsedDBIF;
import dal.WorkOrderDBIF;
import model.Asset;
import model.Employee;
import model.Measurement;
import model.SparepartUsed;
import model.Workorder;

public class WorkOrderController implements WorkOrderControllerIF {
	
	protected WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	protected SparepartUsedDBIF sparePartUsedDB = Database.getInstance().getSparepartUsedDataBase();
	protected MeasurementDBIF measurementDB = Database.getInstance().getMeasurementDataBase();
	
	@Override
	public Asset findAssetByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee findEmployeeByID(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Workorder workOrder) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Workorder> getAllUnfinishedWorkOrders() {
		List<Workorder> tempList = new ArrayList<>();
		tempList = workOrderDB.getAllUnfinishedWorkOrders();
		return tempList;
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return workOrderDB.getAllWorkOrdersByAssetID(assetID);
	}
	
	@Override
	public List<Measurement> getAllMeasurementsUsedInWorkOrder(int workdOrderID) {
		List<Measurement> tempList = new ArrayList<>();
		tempList = measurementDB.findMeasurementsByWorkOrderID(workdOrderID);
		return tempList;
		
	}
	
	@Override
	public List<SparepartUsed> getAllSparepartsUsedInWorkOrder(int workOrderID) {
		List<SparepartUsed> tempList = new ArrayList<>();
		tempList = sparePartUsedDB.findSparepartListByWorkorderID(workOrderID);
		return tempList;
		
	}

}
