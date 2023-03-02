package dal;

import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.AssetStatus;
import model.Location;

public interface AssetDBIF {

	//CRUD
	public boolean createAsset(String name, Calendar dateOfAcqire, String description, AssetStatus status, String manufacturerName, Location location);
	public Asset readAssetByID(int id);
	public Asset updateAsset(int id, String name, Calendar dateOfAcqire, String description, AssetStatus status, String manufacturerName, Location location);
	public boolean deleteAssetByID(int id);
	
	//More
	public List<Asset> getAllAssets();
}
