package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Address;

/*
 * 20-04-2023: Rasmus/Mikkel - Created function to get one Address by ID and all Address
 */

public class AddressDB implements AddressDBIF {
	
	public static final String FIELDS = "address_id_PK, address_streetname, address_streetnumber, address_zipcode, address_cityname";
	public static final String SELECT_ADDRESS_BY_ID = "SELECT " + FIELDS + " FROM Address Where address_id_PK = ?";
	public static final String SELECT_ALL_ADDRESS = "SELECT " + FIELDS + " FROM Address";

	@Override
	public Address findAddressByID(int addressID) {
		
		Address address = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindAddress = con.prepareStatement(SELECT_ADDRESS_BY_ID)) {
			
			//prepare statement
			psFindAddress.setInt(1, addressID);
			
			//execute statement
			ResultSet rs = psFindAddress.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Address object from result set
				address = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}
		
		return address;
	
	}
	public Address buildObjectFromResultset(ResultSet rs) throws SQLException {
		if(rs.getInt("address_id_PK") == 0) {
			return null;
		}else return buildObject(rs);
	}
	
	private Address buildObject(ResultSet rs) throws SQLException {
		
		// create a new Address object
		Address result = new Address();

		// set the properties of the address object based on the values in the ResultSet
		result.setAddressID(rs.getInt("address_id_PK"));
		result.setStreetName(rs.getString("address_streetname"));
		result.setStreetNumber(rs.getString("address_streetnumber"));
		result.setZipCode(rs.getString("address_zipcode"));
		result.setCityName(rs.getString("address_cityname"));
		
		// return the address object
				
		return result;
	}

	@Override
	public List<Address> getAllAddresses() {
		
		List<Address> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindAddress = con.prepareStatement(SELECT_ALL_ADDRESS)) {
			
			//prepare statement
			// Left empty.
			
			//execute statement
			ResultSet rs = psFindAddress.executeQuery();
			
			if (rs != null) {
				//build Address object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}
		return list;
	}
}
