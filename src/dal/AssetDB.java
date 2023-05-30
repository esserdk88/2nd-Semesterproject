package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AssetDBIF;
import dal.interfaces.LocationDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Asset;

/**
 * The AssetDB class implements the AssetDBIF interface and provides methods for retrieving assets from
 * a database.
 */
public class AssetDB implements AssetDBIF {

	public static final String SELECT_ASSET_BY_ID = "SELECT * FROM Asset_Location_Address Where asset_id_PK = ?";
	public static final String SELECT_ALL_ASSETS = "SELECT * FROM Asset_Location_Address";

	private LocationDBIF locationDB = Database.getInstance().getLocationDataBase();

	/**
	 * This Java function retrieves an Asset object from a database by its ID.
	 * 
	 * @param assetID an integer representing the ID of the asset to be retrieved from the database.
	 * @return The method is returning an Asset object.
	 */
	@Override
	public Asset findAssetByID(int assetID) {
		Asset asset = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindAsset = con.prepareStatement(SELECT_ASSET_BY_ID)) {

			// prepare statement
			psFindAsset.setInt(1, assetID);

			// execute statement
			ResultSet rs = psFindAsset.executeQuery();

			if (rs.next()) {
				// build Asset object from result set
				asset = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING ASSET:" + e.getMessage());
		}

		return asset;
	}

	/**
	 * This function builds an Asset object from a ResultSet if the ResultSet contains a valid
	 * asset_id_PK.
	 * 
	 * @param rs The ResultSet object that contains the data retrieved from the database.
	 * @return The method `buildObjectFromResultset` is returning an object of type `Asset` or `null`.
	 */
	public Asset buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "asset_id_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

	/**
	 * This Java function builds an Asset object by setting its properties based on values from a
	 * ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database query.
	 * @return The method `buildObject` is returning an object of type `Asset`.
	 */
	private Asset buildObject(ResultSet rs) throws SQLException {

		// Create a new Asset object
		Asset result = new Asset();

		// Set the properties of the Asset object based on the values in the ResultSet
		result.setAssetID(rs.getInt("asset_id_PK"));
		result.setName(rs.getString("asset_name"));
		result.setAquisitionDate(DataBaseUtilities.convertSqlDateToCalendar(rs.getDate("asset_acquisitiondate")));
		result.setDescription(rs.getString("asset_description"));
		result.setStatus(rs.getString("asset_status"));
		result.setManufacturer(rs.getString("asset_manufacturer"));
		result.setLocation(locationDB.buildObjectFromResultset(rs));

		// return the Asset object
		return result;
	}

	/**
	 * This Java function retrieves all assets from a database and returns them as a list of Asset
	 * objects.
	 * 
	 * @return A list of all assets from the database.
	 */
	@Override
	public List<Asset> getAllAssets() {

		List<Asset> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindAsset = con.prepareStatement(SELECT_ALL_ASSETS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindAsset.executeQuery();

			// build Asset object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING ASSET:" + e.getMessage());
		}

		return list;
	}

}