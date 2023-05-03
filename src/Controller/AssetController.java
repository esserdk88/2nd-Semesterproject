package Controller;

import java.sql.SQLException;
import java.util.List;

import dal.AssetDBIF;
import dal.Database;
import model.Asset;

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
