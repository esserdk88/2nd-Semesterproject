package controller;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.AssetControllerIF;
import dal.interfaces.AssetDBIF;
import dao.Database;
import model.Asset;
import model.Measurement;

public class AssetController implements AssetControllerIF {
	
	private AssetDBIF assetDatabase;

	public AssetController() {
			this.assetDatabase = Database.getInstance().getAssetDataBase();
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

}
