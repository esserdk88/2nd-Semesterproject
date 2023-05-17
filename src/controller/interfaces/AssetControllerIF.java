package controller.interfaces;

import java.sql.SQLException;
import java.util.List;

import model.Asset;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface AssetControllerIF {
	
	public Asset findAssetByID(int assetID) throws SQLException;
	
	public List<Asset> getAllAssets() throws SQLException;
}
