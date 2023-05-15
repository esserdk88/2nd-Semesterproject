package Controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Reference;
import model.Repair;
import model.Service;
import model.Workorder;

public class RepairController extends WorkOrderController implements RepairControllerIF {
	
	@Override
	public boolean createWorkOrder(Repair repair) {
		// we have to check if the asset is null.
		if(repair.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
				
		return workOrderDB.addRepairWorkOrder(repair);
	}
	
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int referenceCVR) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Repair repair = new Repair();
		repair.setAsset(asset);
		repair.setTitle(title);
		repair.setStartDate(startDate);
		repair.setPriority(priority);
		repair.setDescription(description);
		
		//TODO fix this when Reference Controller is made
		Reference reference = new Reference();
		reference.setCvr(referenceCVR);
		repair.setReference(reference);
		
		return workOrderDB.addRepairWorkOrder(repair);
	}
	@Override
	public Repair findWorkOrderByID(int workOrderID) {
		return workOrderDB.findRepairWorkOrderByID(workOrderID);
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return super.getAllWorkOrdersByAssetID(assetID);
	}

}