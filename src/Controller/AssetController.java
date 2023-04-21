package Controller;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import dal.AssetDBIF;
import model.Asset;
import model.Location;

public class AssetController implements AssetControllerIF {
	
	private AssetDBIF assetDatabase;

	public AssetController(AssetDBIF assetDatabase) {
			this.assetDatabase = assetDatabase;
	}

	@Override
	public Asset findAssetByID(int assetID) throws SQLException {
		Asset tempAsset = null;
		
		tempAsset = assetDatabase.findAssetByID(assetID);
		
		return tempAsset;
	}
	
	public Asset createNewAsset(String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location) throws SQLException {
		Asset tempAsset = null;
		tempAsset = assetDatabase.createNewAsset(name, description, aquisitionDate, status, manufacturer, location);
		
		return tempAsset;
	}
	
	@Override
	public List<Asset> getAllAssets() throws SQLException {
		List<Asset> assetList = null;
		
		assetList = assetDatabase.getAllAssets();
		
		return assetList;
	}

}
