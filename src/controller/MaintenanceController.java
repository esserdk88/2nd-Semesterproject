package controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import controller.interfaces.MaintenanceControllerIF;
import dal.interfaces.WorkOrderDBIF;
import model.Asset;
import model.Maintenance;
import model.Workorder;

public class MaintenanceController extends WorkOrderController implements MaintenanceControllerIF {
	
	public MaintenanceController() {
		super();
	}
	
	@Override
	public boolean createWorkOrder(Maintenance maintenance) {
		// we have to check if the asset is null.
		if(maintenance.getAsset() == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		
		return workOrderDB.addMaintenanceWorkOrder(maintenance);
	}
	
	public boolean createWorkOrder(int assetID, String title, Calendar startDate, short priority, String description, int intervalDayCount, boolean repeated) throws SQLException {
		AssetController assetCtr = new AssetController();
		Asset asset = assetCtr.findAssetByID(assetID);
		if(asset == null) {
			throw new IllegalArgumentException("Asset cant be empty");
		}
		Maintenance maintenance = new Maintenance();
		maintenance.setAsset(asset);
		maintenance.setTitle(title);
		maintenance.setStartDate(startDate);
		maintenance.setPriority(priority);
		maintenance.setDescription(description);
		maintenance.setIntervalDayCount(intervalDayCount);
		maintenance.setRepeated(repeated);
		return workOrderDB.addMaintenanceWorkOrder(maintenance);
	}

	@Override
	public Maintenance findWorkOrderByID(int workOrderID) {
		return workOrderDB.findMaintenanceWorkOrderByID(workOrderID);
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		return super.getAllWorkOrdersByAssetID(assetID);
	}
}