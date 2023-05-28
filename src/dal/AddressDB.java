package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AddressDBIF;
import dao.DataBaseUtilities;
import dao.DatabaseConnection;
import model.Address;

/*
 * 20-04-2023: Rasmus/Mikkel - Created function to get one Address by ID and all Address
 */

/**
 * The AddressDB class implements the AddressDBIF interface and provides methods for finding an address
 * by ID and getting a list of all addresses.
 */
public class AddressDB implements AddressDBIF {

	public static final String FIELDS = "address_id_PK, address_streetname, address_streetnumber, address_zipcode, address_cityname";
	public static final String SELECT_ADDRESS_BY_ID = "SELECT " + FIELDS + " FROM Address Where address_id_PK = ?";
	public static final String SELECT_ALL_ADDRESS = "SELECT " + FIELDS + " FROM Address";

	/**
	 * This Java function retrieves an Address object from a database by its ID.
	 * 
	 * @param addressID an integer representing the unique identifier of the address to be retrieved from
	 * the database.
	 * @return An Address object is being returned.
	 */
	@Override
	public Address findAddressByID(int addressID) {

		Address address = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindAddress = con.prepareStatement(SELECT_ADDRESS_BY_ID)) {

			// prepare statement
			psFindAddress.setInt(1, addressID);

			// execute statement
			ResultSet rs = psFindAddress.executeQuery();

			if (rs.next()) {
				// build Address object from result set
				address = buildObject(rs, null);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}

		return address;

	}

	/**
	 * This function builds an Address object from a ResultSet if the ResultSet contains the necessary
	 * data, otherwise it returns a new Address object.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database query.
	 * @param prefix The prefix parameter is a string that is used to identify the columns in the
	 * ResultSet that correspond to the Address object's properties. It is typically used when multiple
	 * tables are joined in a query and there are columns with the same name in different tables. By
	 * specifying a prefix, the method can distinguish between the
	 * @return The method `buildObjectFromResultset` is returning an object of type `Address`. If the
	 * `ResultSet` object `rs` contains a non-null value for the column with the name `prefix +
	 * "address_id_PK"`, then the method `buildObject` is called to create an `Address` object from the
	 * data in the `ResultSet`. Otherwise, a new empty `Address` object
	 */
	public Address buildObjectFromResultset(ResultSet rs, String prefix) throws SQLException {
		if (DataBaseUtilities.check(rs, prefix, "address_id_PK")) {
			return buildObject(rs, prefix);
		} else
			return new Address();
	}

	/**
	 * This function builds an Address object by setting its properties based on values in a ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database
	 * @param prefix A string that is used as a prefix for the column names in the ResultSet. This is
	 * useful when joining tables with similar column names to avoid ambiguity. If no prefix is provided,
	 * an empty string is used as the default.
	 * @return An Address object is being returned.
	 */
	private Address buildObject(ResultSet rs, String prefix) throws SQLException {
		if (prefix == null) {
			prefix = "";
		}

		// create a new Address object
		Address result = new Address();

		// set the properties of the address object based on the values in the ResultSet
		result.setAddressID(rs.getInt(prefix + "address_id_PK"));
		result.setStreetName(rs.getString(prefix + "address_streetname"));
		result.setStreetNumber(rs.getString(prefix + "address_streetnumber"));
		result.setZipCode(rs.getString(prefix + "address_zipcode"));
		result.setCityName(rs.getString(prefix + "address_cityname"));

		// return the address object

		return result;
	}

	/**
	 * This function retrieves all addresses from a database and returns them as a list of Address
	 * objects.
	 * 
	 * @return A list of Address objects.
	 */
	@Override
	public List<Address> getAllAddresses() {

		List<Address> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindAddress = con.prepareStatement(SELECT_ALL_ADDRESS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindAddress.executeQuery();

			// build Address object from result set
			while (rs.next()) {
				list.add(buildObject(rs, null));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING ADDRESS:" + e.getMessage());
		}
		return list;
	}
}
