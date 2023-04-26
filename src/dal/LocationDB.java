package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;
import model.Location;

public class LocationDB implements LocationDBIF {

	public static final String FIELDS = "location_id_PK, location_building, location_floor, location_room, location_address_id_FK";
	public static final String SELECT_LOCATION_BY_ID = "SELECT " + FIELDS + " FROM Location Where location_id_PK = ?";
	public static final String SELECT_ALL_LOCATIONS = "SELECT " + FIELDS + " FROM Location";
	
	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();
	
	@Override
	public Location findLocationByID(int locationID) {
		
		Location location = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindLocation = con.prepareStatement(SELECT_LOCATION_BY_ID)) {
			
			//prepare statement
			psFindLocation.setInt(1, locationID);
			
			//execute statement
			ResultSet rs = psFindLocation.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Location object from result set
				location = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING LOCATION:" + e.getMessage());
		}
		
		return location;
	}

	private Location buildObject(ResultSet rs) throws SQLException {
		
		// create a new Location object
		Location result = new Location();

		// set the properties of the address object based on the values in the ResultSet
		result.setLocationID(rs.getInt("location_id_PK"));
		result.setBuilding(rs.getString("location_building"));
		result.setFloor(rs.getString("location_floor"));
		result.setRoom(rs.getString("location_room"));
		result.setAddress(addressDB.findAddressByID(rs.getInt("location_address_id_FK")));

		// return the location object
		return result;
	}

	@Override
	public List<Location> getAllLocations() {
		
		List<Location> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindLocation = con.prepareStatement(SELECT_ALL_LOCATIONS)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindLocation.executeQuery();
			
			if (rs != null) {
				//build Address object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING LOCATIONS:" + e.getMessage());
		}
		return list;
	}
}