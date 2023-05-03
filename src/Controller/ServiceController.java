package Controller;

import java.util.List;

import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import gui.WorkOrder;
import model.Service;

public class ServiceController extends WorkOrderController implements ServiceControllerIF {
	
	@Override
	public boolean createWorkOrder(Service service) {
		// we have to check if the asset is null.
		if(service.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}			
		return workOrderDB.addServiceWorkOrder(service);
	}

	@Override
	public Service findWorkOrderByID(int workOrderID) {
		return workOrderDB.findServiceWorkOrderByID(workOrderID);
	}

	@Override
	public List<WorkOrder> getAllWorkOrdersByAssetID() {
		// TODO Auto-generated method stub
		return null;
	}
}
