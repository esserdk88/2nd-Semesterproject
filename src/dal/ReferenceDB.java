package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AddressDBIF;
import dal.interfaces.ReferenceDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Reference;

/**
 * The ReferenceDB class implements the ReferenceDBIF interface and provides methods for retrieving
 * references from a database.
 */
public class ReferenceDB implements ReferenceDBIF {

	public static final String SELECT_REFERENCE_BY_CVR = "SELECT * FROM ReferenceView Where reference_CVR_PK = ?";
	public static final String SELECT_ALL_REFERENCES = "SELECT * FROM ReferenceView";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

	/**
	 * This Java function retrieves a reference object from a database by its ID.
	 * 
	 * @param referenceCVR The parameter referenceCVR is an integer that represents the CVR (Central
	 * Business Register) number of a reference.
	 * @return The method is returning a Reference object.
	 */
	@Override
	public Reference findReferenceByID(int referenceCVR) {

		Reference reference = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindReference = con.prepareStatement(SELECT_REFERENCE_BY_CVR)) {

			// prepare statement
			psFindReference.setInt(1, referenceCVR);

			// execute statement
			ResultSet rs = psFindReference.executeQuery();

			if (rs.next()) {
				// build Reference object from result set
				reference = buildObject(rs, null);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING REFERENCE:" + e.getMessage());
		}

		return reference;
	}

	/**
	 * This function builds a reference object from a result set if the result set contains a primary key.
	 * 
	 * @param rs The ResultSet object containing the data retrieved from the database.
	 * @return The method `buildObjectFromResultset` is returning a `Reference` object that is built from
	 * the `ResultSet` `rs` if the `rs` contains a non-null value for the column `reference_CVR_PK`. If
	 * the `rs` is null or the `reference_CVR_PK` column is null, then the method returns null.
	 */
	public Reference buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "reference_CVR_PK")) {
			return buildObject(rs, "reference_");
		} else
			return null;
	}

	/**
	 * This Java function builds a Reference object from a ResultSet and sets its properties based on the
	 * values in the ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database
	 * @param prefix The prefix parameter is a string that is used to specify a prefix for the column
	 * names in the ResultSet. This is useful when the ResultSet contains columns with the same name from
	 * multiple tables, and the prefix can be used to differentiate between them.
	 * @return The method `buildObject` is returning a `Reference` object.
	 */
	private Reference buildObject(ResultSet rs, String prefix) throws SQLException {
		// Create a new Reference object
		Reference result = new Reference();

		// Set the properties of the Reference object based on the values in the
		// ResultSet
		result.setCvr(rs.getInt("reference_CVR_PK"));
		result.setContact(rs.getString("reference_contact"));
		result.setName(rs.getString("reference_name"));
		result.setPhone(rs.getString("reference_phone"));
		result.setEmail(rs.getString("reference_email"));
		result.setAddress(addressDB.buildObjectFromResultset(rs, prefix));

		// return the Reference object
		return result;
	}

	/**
	 * This function retrieves all references from a database and returns them as a list of Reference
	 * objects.
	 * 
	 * @return A list of all references from the database.
	 */
	@Override
	public List<Reference> getAllReferences() {
		List<Reference> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindReference = con.prepareStatement(SELECT_ALL_REFERENCES)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindReference.executeQuery();

			// build Reference object from result set
			while (rs.next()) {
				list.add(buildObject(rs, null));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING REFERENCE:" + e.getMessage());
		}
		return list;
	}

}
