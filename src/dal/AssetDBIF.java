package dal;


import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Location;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface AssetDBIF {

	public Asset findAssetByID(int assetID);
	
}