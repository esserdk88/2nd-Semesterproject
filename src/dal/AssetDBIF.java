package dal;


import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Location;

public interface AssetDBIF {

	//CRUD
	public boolean createAsset(int assetID, String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location);
	
	public Asset readAssetByID(int assetID);
	
	//TODO updateAssetByID
	public boolean deleteAssetByID(int assetID);
	
	//More
	public List<Asset> getAllAssets();
}
