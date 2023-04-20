package Controller;

import model.Asset;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface AssetControllerIF {
	
	public Asset findAssetByID(int assetID);

}
