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
	
	private Connection con = DatabaseConnection.getInstance().getConnection();

	@Override
	public Address findAddressByID(int addressID) {
		
		Address address = null;
		
		// establish database connection
		try (PreparedStatement psFindAddress = con.prepareStatement(SELECT_ADDRESS_BY_ID)) {
			
			//prepare statement
			psFindAddress.setInt(1, addressID);
			
			//execute statement
			ResultSet rs = psFindAddress.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Address object from result set
				address = buildObject(rs,null);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}
		
		return address;
	
	}

	public Address buildObjectFromResultset(ResultSet rs, String prefix) throws SQLException {
		if(prefix == null) {
			prefix = "";
		}
		
		boolean check = false;
        int count = rs.getMetaData().getColumnCount();
        for (int i = 1; i <=count; i++) {
               if(rs.getMetaData().getColumnName(i).equals(prefix+"address_id_PK")) {
                  check = true;
               }
            }
        if(!check || rs.getInt(prefix+"address_id_PK") == 0) {
            return new Address();
        }else return buildObject(rs,prefix);
    }
	
	private Address buildObject(ResultSet rs, String prefix) throws SQLException {
		if(prefix == null) {
			prefix = "";
		}
		
		// create a new Address object
		Address result = new Address();

		// set the properties of the address object based on the values in the ResultSet
		result.setAddressID(rs.getInt(prefix+"address_id_PK"));
		result.setStreetName(rs.getString(prefix+"address_streetname"));
		result.setStreetNumber(rs.getString(prefix+"address_streetnumber"));
		result.setZipCode(rs.getString(prefix+"address_zipcode"));
		result.setCityName(rs.getString(prefix+"address_cityname"));
		
		// return the address object
				
		return result;
	}

	@Override
	public List<Address> getAllAddresses() {
		
		List<Address> list = new ArrayList<>();
		
		try (PreparedStatement psFindAddress = con.prepareStatement(SELECT_ALL_ADDRESS)) {
			
			//prepare statement
			// Left empty.
			
			//execute statement
			ResultSet rs = psFindAddress.executeQuery();
			
			if (rs != null) {
				//build Address object from result set
				while(rs.next()) {
					list.add(buildObject(rs,null));
				}
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}
		return list;
	}
}
