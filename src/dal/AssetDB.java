package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Asset;

public class AssetDB implements AssetDBIF {
	
	
	public static final String SELECT_ASSET_BY_ID = "SELECT * FROM Asset_Location_Address Where asset_id_PK = ?";
	public static final String SELECT_ALL_ASSETS = "SELECT * FROM Asset_Location_Address";

	private LocationDBIF locationDB = Database.getInstance().getLocationDataBase();
	private Connection con = DatabaseConnection.getInstance().getConnection();
	
	@Override
	public Asset findAssetByID(int assetID) {
		Asset asset = null;
		
		// establish database connection
		try (PreparedStatement psFindAsset = con.prepareStatement(SELECT_ASSET_BY_ID)) {
			
			//prepare statement
			psFindAsset.setInt(1, assetID);
			
			//execute statement
			ResultSet rs = psFindAsset.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Asset object from result set
				asset = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ASSET:" + e.getMessage());
		}
		
		return asset;
	}

	public Asset buildObjectFromResultset(ResultSet rs) throws SQLException {
		if(DataBaseUtilities.check(rs, null, "asset_id_PK")){
			return buildObject(rs);
		}else return null;
	}
	private Asset buildObject(ResultSet rs) throws SQLException {
		
		// Create a new Asset object
		Asset result = new Asset();

		// Set the properties of the Asset object based on the values in the ResultSet
		result.setAssetID(rs.getInt("asset_id_PK"));
		result.setName(rs.getString("asset_name"));
		result.setAquisitionDate(convertSqlDateToCalendar(rs.getDate("asset_acquisitiondate")));
		result.setDescription(rs.getString("asset_description"));
		result.setStatus(rs.getString("asset_status"));
		result.setManufacturer(rs.getString("asset_manufacturer"));
		result.setLocation(locationDB.buildObjectFromResultset(rs));
		
		// return the Asset object
		return result;
	}

	@Override
	public List<Asset> getAllAssets() {
		
		List<Asset> list = new ArrayList<>();
		
		// establish database connection
		try (PreparedStatement psFindAsset = con.prepareStatement(SELECT_ALL_ASSETS)) {
			
			//prepare statement
			// Left empty.
			
			//execute statement
			ResultSet rs = psFindAsset.executeQuery();
			
			if (rs != null) {
				//build Asset object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ASSET:" + e.getMessage());
		}
		
		return list;
	}
	
	//Converter
	private Calendar convertSqlDateToCalendar(Date sqlDate) { 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sqlDate); 
		
		return calendar; 
	}
}