package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.SparepartDBIF;
import dal.interfaces.SparepartUsedDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.SparepartUsed;

/**
 * The SparepartUsedDB class is responsible for retrieving and managing data related to spare parts
 * used in work orders from a database.
 */
public class SparepartUsedDB implements SparepartUsedDBIF {

	public static final String FIELDS = "sparepart_used_amount, sparepart_used_workorder_id_FK, sparepart_used_sparepart_id_FK";
	public static final String SELECT_SPAREPART_USED_BY_PK = "SELECT " + FIELDS
			+ " FROM Sparepart_Used WHERE sparepart_used_workorder_id_FK = ? AND sparepart_used_sparepart_id_FK = ?";
	public static final String SELECT_SPAREPART_USED_BY_WORKORDER_ID = "SELECT " + FIELDS
			+ " FROM Sparepart_Used WHERE sparepart_used_workorder_id_FK = ?";
	public static final String SELECT_ALL_SPAREPART_USED = "SELECT " + FIELDS + " FROM Sparepart_Used";
	public static final String SELECT_SPAREPART_USED_BY_WORKORDER_ID_VIEW = "EXEC GetSparepartUsed @WorkOrderId = ?";
	private SparepartDBIF sparepartDB = Database.getInstance().getSparepartDataBase();

	/**
	 * This Java function retrieves a SparepartUsed object from a database based on a given work order ID
	 * and spare part ID.
	 * 
	 * @param workOrderID an integer representing the ID of a work order
	 * @param sparepartID The ID of the spare part being searched for in the database.
	 * @return The method is returning a SparepartUsed object.
	 */
	@Override
	public SparepartUsed findSparePartUsedByWorkorderIDAndSparepartID(int workOrderID, int sparepartID) {

		SparepartUsed sparepartUsed = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSparepartUsed = con.prepareStatement(SELECT_SPAREPART_USED_BY_PK)) {

			// prepare statement
			psFindSparepartUsed.setInt(1, workOrderID);
			psFindSparepartUsed.setInt(2, sparepartID);

			// execute statement
			ResultSet rs = psFindSparepartUsed.executeQuery();

			if (rs.next()) {
				// build Supplier object from result set
				sparepartUsed = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SPAREPART_USED:" + e.getMessage());
		}

		return sparepartUsed;
	}

	/**
	 * The function builds a SparepartUsed object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs ResultSet object that contains the data retrieved from the database. It is used to
	 * extract the values of the columns in the current row of the ResultSet.
	 * @return The method is returning a SparepartUsed object.
	 */
	private SparepartUsed buildObject(ResultSet rs) throws SQLException {
		// Create a new SparepartUsed object
		SparepartUsed result = new SparepartUsed();

		// Set the properties of the SparepartUsed object based on the values in the
		// ResultSet
		result.setAmount(rs.getInt("sparepart_used_amount"));
		result.setSparepart(sparepartDB.buildObjectFromResultset(rs));
		// return the SparepartUsed object
		return result;
	}

	/**
	 * This Java function retrieves all spare parts used from a database and returns them as a list of
	 * SparepartUsed objects.
	 * 
	 * @return A list of SparepartUsed objects.
	 */
	@Override
	public List<SparepartUsed> getAllSparepartUsed() {
		List<SparepartUsed> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSparepartUsed = con.prepareStatement(SELECT_ALL_SPAREPART_USED)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindSparepartUsed.executeQuery();

			// build Supplier object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SPAREPART_USED:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function retrieves a list of spare parts used in a work order from a database.
	 * 
	 * @param workOrderID an integer representing the ID of a work order for which the spare parts used
	 * need to be retrieved from the database.
	 * @return A list of SparepartUsed objects that are associated with a specific work order ID.
	 */
	@Override
	public List<SparepartUsed> findSparepartListByWorkorderID(int workOrderID) {
		List<SparepartUsed> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSparepartUsed = con.prepareStatement(SELECT_SPAREPART_USED_BY_WORKORDER_ID_VIEW)) {

			// prepare statement
			psFindSparepartUsed.setInt(1, workOrderID);

			// execute statement
			ResultSet rs = psFindSparepartUsed.executeQuery();

			// build Supplier object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SPAREPART_USED:" + e.getMessage());
		}
		return list;
	}
}
