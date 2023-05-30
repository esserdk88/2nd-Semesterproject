package controller;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.AssetControllerIF;
import dal.interfaces.AssetDBIF;
import dao.Database;
import model.Asset;
import model.Workorder;

/**
 * The AssetController class retrieves and manages assets and their associated work orders from a
 * database.
 */
public class AssetController implements AssetControllerIF {
	
	private AssetDBIF assetDatabase;
	private WorkOrderController workorderController;

	// This is the constructor of the `AssetController` class. It initializes the `assetDatabase` variable
	// by getting the instance of the asset database from the `Database` class and assigning it to
	// `assetDatabase`. It also initializes the `workorderController` variable by creating a new instance
	// of the `WorkOrderController` class and assigning it to `workorderController`.
	public AssetController() {
			this.assetDatabase = Database.getInstance().getAssetDataBase();
			this.workorderController = new WorkOrderController();
	}

	/**
	 * This Java function finds an asset by its ID in a database and returns it.
	 * 
	 * @param assetID The parameter assetID is an integer that represents the unique identifier of an
	 * asset. This method is used to find an asset in the database by its ID.
	 * @return The method is returning an object of type Asset.
	 */
	@Override
	public Asset findAssetByID(int assetID) throws SQLException {
		Asset tempAsset = null;
		
		tempAsset = assetDatabase.findAssetByID(assetID);
		
		return tempAsset;
	}
	
	
	/**
	 * This function retrieves a list of all assets from a database.
	 * 
	 * @return A list of Asset objects is being returned.
	 */
	@Override
	public List<Asset> getAllAssets() throws SQLException {
		List<Asset> assetList = null;
		
		assetList = assetDatabase.getAllAssets();
		
		return assetList;
	}
	
	/**
	 * This function returns a list of all work orders associated with a given asset ID.
	 * 
	 * @param assetID The ID of the asset for which all work orders are being retrieved.
	 * @return A list of work orders associated with a specific asset ID.
	 */
	public List<Workorder> getAllWorkOrdersForAssetByID(int assetID){
		return workorderController.getAllWorkOrdersByAssetID(assetID);
	}

}
