package Controller;

import java.sql.Date;
import java.sql.SQLException;
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
	public Asset findAssetByID(int assetID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Asset> getAllAssets() throws SQLException {
		List<Asset> assetList = null;
		
		assetList = assetDatabase.getAllAssets();
		
		return assetList;
	}

}
