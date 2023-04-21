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
import model.Location;

public class AssetDB implements AssetDBIF {

	private ArrayList<String> ASSET_COLUMNS_LIST = new ArrayList<String>(
			Arrays.asList("asset_id_PK", "asset_name", "asset_acquisitiondate", "asset_description", "asset_status",
					"asset_manufacturer", "asset_location_id_FK"));
	private String ASSET_COLUMNS = listToString(ASSET_COLUMNS_LIST, false);
	private String ASSET_TABLE = "Asset";
	private final String QUERY_ASSET_BY_ID = "SELECT * From " + ASSET_TABLE + " where asset_id_PK = ?";
	private final String SELECT_ALL_ASSETS = "SELECT * FROM " + ASSET_TABLE;
	private final String INSERT_ASSET = "INSERT INTO " + ASSET_TABLE + "(" + ASSET_COLUMNS + ")"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private String listToString(ArrayList<String> list, boolean keepID) {
		String outputString = "";

		if (!keepID) {
			// Remove the ID of the Asset
			list.remove(0);
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size() - 1) {
				// Last one
				outputString += list.get(i);
			} else {
				// Others
				outputString += list.get(i) + ",";
			}
		}
		return outputString;
	}

	@Override
	public Asset findAssetByID(int assetID) throws SQLException {
		// Get a database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		Asset tempAsset = null;
		try (PreparedStatement prepS = con.prepareStatement(QUERY_ASSET_BY_ID)) {

			// Set parameters for the prepared statement
			prepS.setInt(1, assetID);

			// Execute the prepared statement
			ResultSet rs = prepS.executeQuery();
			if (rs != null && rs.next()) {
				// Set the identity to the generated ID
				tempAsset = buildObject(rs);
			}
			rs.close();
			prepS.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot insert record " + e.getMessage());
		}

		return tempAsset;
	}

	@Override
	public Asset createNewAsset(String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location) throws SQLException {
		// Get a database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		int identity = -1;
		Asset tempAsset = null;
		aquisitionDate = aquisitionDate.getInstance();
		java.util.Date utilDate = aquisitionDate.getTime();
		Date sqlDate = new Date(utilDate.getTime());
		try (PreparedStatement prepS = con.prepareStatement(INSERT_ASSET, Statement.RETURN_GENERATED_KEYS)) {

			// Set parameters for the prepared statement
			prepS.setString(1, name);
			prepS.setDate(2, sqlDate);
			prepS.setString(3, description);
			prepS.setString(4, status);
			prepS.setString(5, manufacturer);
			prepS.setInt(6, location.getLocationID());

			// Execute the prepared statement
			prepS.executeUpdate();

			// Get the generated keys
			ResultSet rs = prepS.getGeneratedKeys();
			if (rs.next()) {
				// Set the identity to the generated ID
				identity = rs.getInt(1);
			}
			rs.close();
			prepS.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Cannot insert record " + e.getMessage());
		}
		if (identity != -1) {
			tempAsset = new Asset(identity, name, description, aquisitionDate, status, manufacturer, location);
		}

		return tempAsset;
	}

	@Override
	public List<Asset> getAllAssets() throws SQLException {
		// Initialize the productList variable to null
		List<Asset> assetList = null;
		// Get a database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement prepS = con.prepareStatement(SELECT_ALL_ASSETS)) {
			// Set the location ID parameter for the prepared statement
			// Execute the prepared statement
			// prepS.executeUpdate();
			// Get the result set from the executed query
			ResultSet rs = prepS.executeQuery();
			if (rs != null && rs.next()) {
				// If the result set is not null and contains a row, build a List of Product
				// objects from the rows
				assetList = buildListObjects(rs);
			}
			rs.close();
			prepS.close();
		} catch (SQLException e) {
			// If an SQLException is thrown, print an error message
			System.out.println("Some kind of error " + e.getMessage());
		}
		// Return the productList variable (which will be null if no products were found
		// at the specified location)
		return assetList;
	}

	private List<Asset> buildListObjects(ResultSet rs) {
		List<Asset> res = new ArrayList<>();
		try {
			do {
				Asset m = buildObject(rs);
				res.add(m);
			} while (rs.next());
		} catch (SQLException e) {
			System.out.println("Could not iterate ResultSet" + e.getMessage());
		}
		return res;
	}

	private Asset buildObject(ResultSet rs) {
		Asset asset = null;
		int identity;
		int loco;
		Calendar date = Calendar.getInstance();
		Date sqlDate;
		try {
			sqlDate = rs.getDate("asset_acquisitiondate");
			date.setTime(sqlDate);
			loco = rs.getInt("asset_location_id_FK");
			Location location = new Location(loco, "Test1", "Test2", "Test3", null);
			identity = rs.getInt("asset_id_PK");
			asset = new Asset(identity, rs.getString("asset_name"), rs.getString("asset_description"), date,
					rs.getString("asset_status"), rs.getString("asset_manufacturer"), location);
		} catch (SQLException e) {
			System.out.println("Cannot convert from ResultSet " + e.getMessage());
		}
		return asset;
	}
}
