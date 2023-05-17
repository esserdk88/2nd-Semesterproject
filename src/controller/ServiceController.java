package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import controller.interfaces.ServiceControllerIF;
import model.Asset;
import model.Reference;
import model.Service;
import model.Workorder;

public class ServiceController extends WorkOrderController implements ServiceControllerIF {
	
	@Override
	public boolean createWorkOrder(Service service) {
		// we have to check if the asset is null.
		if(service.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}			
		return workOrderDB.addServiceWorkOrder(service);
	}
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int referenceCVR) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Service service = new Service();
		service.setAsset(asset);
		service.setTitle(title);
		service.setStartDate(startDate);
		service.setPriority(priority);
		service.setDescription(description);
		
		//TODO fix this when Reference Controller is made
		Reference reference = new Reference();
		reference.setCvr(referenceCVR);
		service.setReference(reference);
		
		return workOrderDB.addServiceWorkOrder(service);
	}

	@Override
	public Service findWorkOrderByID(int workOrderID) {
		return workOrderDB.findServiceWorkOrderByID(workOrderID);
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return super.getAllWorkOrdersByAssetID(assetID);
	}
}
