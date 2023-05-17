package controller;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.AssetControllerIF;
import dal.interfaces.AssetDBIF;
import dao.Database;
import model.Asset;
import model.Workorder;

public class AssetController implements AssetControllerIF {
	
	private AssetDBIF assetDatabase;
	private WorkOrderController workorderController;

	public AssetController() {
			this.assetDatabase = Database.getInstance().getAssetDataBase();
			this.workorderController = new WorkOrderController(Database.getInstance().getWorkOrderDataBase());
	}

	@Override
	public Asset findAssetByID(int assetID) throws SQLException {
		Asset tempAsset = null;
		
		tempAsset = assetDatabase.findAssetByID(assetID);
		
		return tempAsset;
	}
	
	
	@Override
	public List<Asset> getAllAssets() throws SQLException {
		List<Asset> assetList = null;
		
		assetList = assetDatabase.getAllAssets();
		
		return assetList;
	}
	
	public List<Workorder> getAllWorkOrdersForAssetByID(int assetID){
		return workorderController.getAllWorkOrdersByAssetID(assetID);
	}

}
