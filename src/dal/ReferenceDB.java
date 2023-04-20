package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.Reference;



public class ReferenceDB implements ReferenceDBIF {
	
	public static final String FIELDS = "reference_CVR_PK, reference_name, reference_phone, reference_email, reference_contact, reference_address_id_FK";
	public static final String SELECT_REFERENCE_BY_CVR = "SELECT " + FIELDS + " FROM Reference Where reference_CVR_PK = ?";
	public static final String SELECT_ALL_REFERENCES = "SELECT " + FIELDS + " FROM Reference";

	private AddressDB addressDB = new AddressDB();

	@Override
	public Reference findReferenceByID(int referenceCVR) {
		
		Reference reference = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindReference = con.prepareStatement(SELECT_REFERENCE_BY_CVR)) {
			
			//prepare statement
			psFindReference.setInt(1, referenceCVR);
			
			//execute statement
			ResultSet rs = psFindReference.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Reference object from result set
				reference = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING REFERENCE:" + e.getMessage());
		}
		
		return reference;
	}

	private Reference buildObject(ResultSet rs) throws SQLException {
		// Create a new Reference object
		Reference result = new Reference();

		// Set the properties of the Reference object based on the values in the ResultSet
		result.setCvr(rs.getString("reference_CVR_PK"));
		result.setContact(rs.getString("reference_contact"));
		result.setName(rs.getString("reference_name"));
		result.setPhone(rs.getString("reference_phone"));
		result.setEmail(rs.getString("reference_email"));
		result.setAddress(addressDB.findAddressByID(rs.getInt("reference_address_id_FK")));
		
		// return the Reference object
		return result;
	}

	@Override
	public List<Reference> getAllReferences() {
		List<Reference> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindReference = con.prepareStatement(SELECT_ALL_REFERENCES)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindReference.executeQuery();
			
			if (rs != null) {
				//build Reference object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING REFERENCE:" + e.getMessage());
		}
		return list;
	}

}
