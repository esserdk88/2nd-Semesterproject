package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import model.Asset;
import model.Employee;
import model.Location;

public class AssetDB implements AssetDBIF {
	
	public static final String FIELDS = "asset_id_PK, asset_name, asset_acquisitiondate, asset_description, asset_status,"
			+ "asset_manufacturer, asset_location_id_FK";
	public static final String SELECT_ASSET_BY_ID = "SELECT " + FIELDS + " FROM Asset Where asset_id_PK = ?";
	public static final String SELECT_ALL_ASSETS = "SELECT " + FIELDS + " FROM Asset";

	private LocationDBIF locationDB = new LocationDB();
	
	@Override
	public Asset findAssetByID(int assetID) {
		Asset asset = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindAsset = con.prepareStatement(SELECT_ASSET_BY_ID)) {
			
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
		result.setLocation(locationDB.findLocationByID(rs.getInt("asset_location_id_FK")));
		
		// return the Asset object
		return result;
	}



	@Override
	public List<Asset> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Converter
	private Calendar convertSqlDateToCalendar(Date sqlDate) { 
		Calendar calendar = Calendar.getInstance(); 
		calendar.setTime(sqlDate); 
		
		return calendar; 
	}
}