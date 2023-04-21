package dal;


import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Location;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface AssetDBIF {

	public Asset findAssetByID(int assetID) throws SQLException;
	
	public Asset createNewAsset(String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location) throws SQLException;
	
	public List<Asset> getAllAssets() throws SQLException;

	
}