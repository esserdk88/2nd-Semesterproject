package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Reference;

public class ReferenceDB implements ReferenceDBIF {

	public static final String SELECT_REFERENCE_BY_CVR = "SELECT * FROM ReferenceView Where reference_CVR_PK = ?";
	public static final String SELECT_ALL_REFERENCES = "SELECT * FROM ReferenceView";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

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

	public Reference buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "reference_CVR_PK")) {
			return buildObject(rs, "reference_");
		} else
			return null;
	}

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
