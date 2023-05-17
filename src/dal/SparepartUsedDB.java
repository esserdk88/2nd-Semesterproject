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

public class SparepartUsedDB implements SparepartUsedDBIF {

	public static final String FIELDS = "sparepart_used_amount, sparepart_used_workorder_id_FK, sparepart_used_sparepart_id_FK";
	public static final String SELECT_SPAREPART_USED_BY_PK = "SELECT " + FIELDS
			+ " FROM Sparepart_Used WHERE sparepart_used_workorder_id_FK = ? AND sparepart_used_sparepart_id_FK = ?";
	public static final String SELECT_SPAREPART_USED_BY_WORKORDER_ID = "SELECT " + FIELDS
			+ " FROM Sparepart_Used WHERE sparepart_used_workorder_id_FK = ?";
	public static final String SELECT_ALL_SPAREPART_USED = "SELECT " + FIELDS + " FROM Sparepart_Used";
	public static final String SELECT_SPAREPART_USED_BY_WORKORDER_ID_VIEW = "EXEC GetSparepartUsed @WorkOrderId = ?";
	private SparepartDBIF sparepartDB = Database.getInstance().getSparepartDataBase();

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
