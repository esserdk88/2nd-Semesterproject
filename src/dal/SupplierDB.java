package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Supplier;

public class SupplierDB implements SupplierDBIF {

	public static final String FIELDS = "supplier_CVR_PK, supplier_name, supplier_phone, supplier_email, supplier_contact, supplier_address_id_FK";
	public static final String SELECT_SUPPLIER_BY_CVR = "SELECT " + FIELDS + " FROM Supplier Where supplier_CVR_PK = ?";
	public static final String SELECT_ALL_SUPPLIERS = "SELECT " + FIELDS + " FROM Supplier";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

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

	public Supplier buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "supplier_CVR_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

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
