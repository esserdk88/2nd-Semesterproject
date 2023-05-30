package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AddressDBIF;
import dal.interfaces.LocationDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Location;

/**
 * The LocationDB class is responsible for retrieving and building Location objects from a database.
 */
public class LocationDB implements LocationDBIF {

	public static final String FIELDS = "location_id_PK, location_building, location_floor, location_room, location_address_id_FK";
	public static final String SELECT_LOCATION_BY_ID = "SELECT " + FIELDS + " FROM Location Where location_id_PK = ?";
	public static final String SELECT_ALL_LOCATIONS = "SELECT " + FIELDS + " FROM Location";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

	/**
	 * This Java function retrieves a location from a database by its ID.
	 * 
	 * @param locationID an integer representing the unique identifier of a location in a database table.
	 * @return The method is returning a Location object.
	 */
	@Override
	public Location findLocationByID(int locationID) {

		Location location = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindLocation = con.prepareStatement(SELECT_LOCATION_BY_ID)) {

			// prepare statement
			psFindLocation.setInt(1, locationID);

			// execute statement
			ResultSet rs = psFindLocation.executeQuery();

			if (rs.next()) {
				// build Location object from result set
				location = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING LOCATION:" + e.getMessage());
		}

		return location;
	}

	/**
	 * This function builds a Location object from a ResultSet if the ResultSet contains a valid location
	 * ID.
	 * 
	 * @param rs The ResultSet object that contains the data retrieved from the database query.
	 * @return The method `buildObjectFromResultset` is returning a `Location` object or `null` if the
	 * `ResultSet` does not contain a valid `location_id_PK` value.
	 */
	public Location buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "location_id_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

	/**
	 * This function builds a Location object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database query
	 * @return The method is returning a Location object.
	 */
	private Location buildObject(ResultSet rs) throws SQLException {

		// create a new Location object
		Location result = new Location();

		// set the properties of the address object based on the values in the ResultSet
		result.setLocationID(rs.getInt("location_id_PK"));
		result.setBuilding(rs.getString("location_building"));
		result.setFloor(rs.getString("location_floor"));
		result.setRoom(rs.getString("location_room"));
		result.setAddress(addressDB.buildObjectFromResultset(rs, "location_"));

		// return the location object
		return result;
	}

	/**
	 * This Java function retrieves all locations from a database and returns them as a list of Location
	 * objects.
	 * 
	 * @return A list of Location objects.
	 */
	@Override
	public List<Location> getAllLocations() {

		List<Location> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindLocation = con.prepareStatement(SELECT_ALL_LOCATIONS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindLocation.executeQuery();

			// build Address object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING LOCATIONS:" + e.getMessage());
		}
		return list;
	}
}