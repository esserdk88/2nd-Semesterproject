package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Reference;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Reference.
 */

public interface ReferenceDBIF {

	public Reference findReferenceByID(int referenceID);

	public List<Reference> getAllReferences();

	public Reference buildObjectFromResultset(ResultSet rs) throws SQLException;
}
