package Controller;

import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Repair;

public class RepairController extends WorkOrderController implements RepairControllerIF {
	
	@Override
	public boolean createWorkOrder(Repair repair) {
		// we have to check if the asset is null.
		if(repair.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
				
		return workOrderDB.addRepairWorkOrder(repair);
	}

	@Override
	public Repair findWorkOrderByID(int workOrderID) {
		return workOrderDB.findRepairWorkOrderByID(workOrderID);
	}

}