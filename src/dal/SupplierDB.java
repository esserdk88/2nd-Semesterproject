package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AddressDBIF;
import dal.interfaces.SupplierDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Address;
import model.Supplier;

/**
 * The SupplierDB class is a Java implementation of the SupplierDBIF interface, providing methods for
 * retrieving Supplier objects from a database.
 */
public class SupplierDB implements SupplierDBIF {

	public static final String FIELDS = "supplier_CVR_PK, supplier_name, supplier_phone, supplier_email, supplier_contact, supplier_address_id_FK";
	public static final String SELECT_SUPPLIER_BY_CVR = "SELECT " + FIELDS + " FROM Supplier Where supplier_CVR_PK = ?";
	public static final String SELECT_ALL_SUPPLIERS = "SELECT " + FIELDS + " FROM Supplier";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

	/**
	 * This Java function retrieves a supplier from a database by their ID and returns a Supplier object.
	 * 
	 * @param supplierCVR The parameter supplierCVR is an integer representing the CVR (Central Business
	 * Register) number of a supplier.
	 * @return The method is returning a Supplier object.
	 */
	@Override
	public Supplier findSupplierByID(int supplierCVR) {

		Supplier supplier = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSupplier = con.prepareStatement(SELECT_SUPPLIER_BY_CVR)) {

			// prepare statement
			psFindSupplier.setInt(1, supplierCVR);

			// execute statement
			ResultSet rs = psFindSupplier.executeQuery();

			if (rs.next()) {
				// build Supplier object from result set
				supplier = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SUPPLIER:" + e.getMessage());
		}

		return supplier;
	}

	/**
	 * This function builds a Supplier object from a ResultSet if the ResultSet contains a non-null value
	 * for the supplier_CVR_PK column.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database
	 * @return The method `buildObjectFromResultset` is returning a `Supplier` object that is built from
	 * the `ResultSet` `rs`. If the `ResultSet` does not contain a valid primary key (`supplier_CVR_PK`),
	 * then `null` is returned.
	 */
	public Supplier buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "supplier_CVR_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

	/**
	 * This function builds a Supplier object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database query.
	 * @return A Supplier object is being returned.
	 */
	private Supplier buildObject(ResultSet rs) throws SQLException {
		// Create a new Supplier object
		Supplier result = new Supplier();

		// Set the properties of the Supplier object based on the values in the
		// ResultSet
		result.setCvr(rs.getString("supplier_CVR_PK"));
		result.setContact(rs.getString("supplier_contact"));
		result.setName(rs.getString("supplier_name"));
		result.setPhone(rs.getString("supplier_phone"));
		result.setEmail(rs.getString("supplier_email"));
		result.setAddress(addressDB.buildObjectFromResultset(rs, null));

		// return the Supplier object
		return result;
	}

	/**
	 * This Java function retrieves all suppliers from a database and returns them as a list of Supplier
	 * objects.
	 * 
	 * @return A list of all suppliers from the database.
	 */
	@Override
	public List<Supplier> getAllSuppliers() {
		List<Supplier> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSupplier = con.prepareStatement(SELECT_ALL_SUPPLIERS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindSupplier.executeQuery();

			// build Supplier object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SUPPLIER:" + e.getMessage());
		}
		return list;
	}

}
