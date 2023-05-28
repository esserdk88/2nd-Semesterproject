package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.SparepartDBIF;
import dal.interfaces.SupplierDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Sparepart;

/**
 * The SparepartDB class implements the SparepartDBIF interface and provides methods for retrieving
 * spare parts from a database.
 */
public class SparepartDB implements SparepartDBIF {
	
	public static final String FIELDS = "sparepart_id_PK, sparepart_name, sparepart_stock_amount, sparepart_price, sparepart_supplier_CVR_FK";
	public static final String SELECT_SPAREPART_BY_ID = "SELECT " + FIELDS + " FROM Sparepart Where sparepart_id_PK = ?";
	public static final String SELECT_ALL_SPAREPARTS = "SELECT " + FIELDS + " FROM Sparepart";

	private SupplierDBIF supplierDB = Database.getInstance().getSupplierDataBase();

	/**
	 * This Java function retrieves a Sparepart object from a database based on its ID.
	 * 
	 * @param sparePartID an integer representing the ID of the spare part that needs to be retrieved from
	 * the database.
	 * @return The method is returning a Sparepart object.
	 */
	@Override
	public Sparepart findSparePartbyID(int sparePartID) {
		Sparepart sparepart = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSparepart = con.prepareStatement(SELECT_SPAREPART_BY_ID)) {

			// prepare statement
			psFindSparepart.setInt(1, sparePartID);

			// execute statement
			ResultSet rs = psFindSparepart.executeQuery();

			if (rs.next()) {
				// build Sparepart object from result set
				sparepart = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SPAREPART:" + e.getMessage());
		}

		return sparepart;
	}

	/**
	 * This function builds a Sparepart object from a ResultSet if the ResultSet contains a
	 * sparepart_id_PK column.
	 * 
	 * @param rs The ResultSet object containing the data retrieved from the database query.
	 * @return The method `buildObjectFromResultset` is returning an object of type `Sparepart` or `null`
	 * if the `ResultSet` does not contain a valid `sparepart_id_PK` value.
	 */
	public Sparepart buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "sparepart_id_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

	/**
	 * This function builds a Sparepart object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs ResultSet object containing the data retrieved from the database query. It contains the
	 * values of the columns specified in the SELECT statement of the query.
	 * @return The method is returning a Sparepart object.
	 */
	private Sparepart buildObject(ResultSet rs) throws SQLException {
		// Create a new Sparepart object
		Sparepart result = new Sparepart();

		// Set the properties of the Sparepart object based on the values in the
		// ResultSet
		result.setSparepartID(rs.getInt("sparepart_id_PK"));
		result.setName(rs.getString("sparepart_name"));
		result.setStockAmount(rs.getInt("sparepart_stock_amount"));
		result.setPrice(rs.getDouble("sparepart_price"));
		result.setSupplier(supplierDB.buildObjectFromResultset(rs));

		// return the Sparepart object
		return result;
	}

	/**
	 * This Java function retrieves all spareparts from a database and returns them as a list.
	 * 
	 * @return A list of all Sparepart objects retrieved from the database.
	 */
	@Override
	public List<Sparepart> getAllSpareparts() {
		List<Sparepart> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindSparepart = con.prepareStatement(SELECT_ALL_SPAREPARTS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindSparepart.executeQuery();

			// build Sparepart object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SPAREPART:" + e.getMessage());
		}
		return list;
	}

}
