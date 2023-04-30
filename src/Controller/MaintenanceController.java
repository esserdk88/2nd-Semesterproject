package Controller;

import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Maintenance;

public class MaintenanceController extends WorkOrderController implements MaintenanceControllerIF {
	
	private WorkOrderDBIF workOrderDB = new WorkOrderDB();

	@Override
	public boolean createWorkOrder(Maintenance maintenance) {
		// we have to check if the asset is null.
		if(maintenance.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		
		return workOrderDB.addMaintenanceWorkOrder(maintenance);
	}

	@Override
	public Maintenance findWorkOrderByID(int workOrderID) {
		return workOrderDB.findMaintenanceWorkOrderByID(workOrderID);
	}

}
