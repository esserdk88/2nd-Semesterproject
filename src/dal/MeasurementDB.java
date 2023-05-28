package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.MeasurementDBIF;
import dao.DatabaseConnection;
import model.Measurement;

/*
 * 24-04-2023: Rasmus/Mikkel - Implemented class from interface
 */


/**
 * The MeasurementDB class implements methods to retrieve measurements from a database by ID, work
 * order ID, and all measurements.
 */
public class MeasurementDB implements MeasurementDBIF {
	
	public static final String FIELDS = "measurement_id_PK, measurement_type, measurement_value, measurement_workorder_id_FK";
	public static final String SELECT_MEASUREMENT_BY_ID = "SELECT " + FIELDS + " FROM Measurement Where measurement_id_PK = ?";
	public static final String SELECT_MEASUREMENTS_BY_WORKORDER_ID = "SELECT " + FIELDS + " FROM Measurement Where measurement_workorder_id_FK = ?";
	public static final String SELECT_ALL_MEASUREMENTS = "SELECT " + FIELDS + " FROM Measurement";

	/**
	 * This Java function retrieves a measurement object from a database by its ID.
	 * 
	 * @param measurementID an integer representing the unique identifier of a measurement record in a
	 * database table.
	 * @return The method is returning a Measurement object.
	 */
	@Override
	public Measurement findMeasurementByID(int measurementID) {

		Measurement measurement = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_MEASUREMENT_BY_ID)) {

			// prepare statement
			psFindMeasurement.setInt(1, measurementID);

			// execute statement
			ResultSet rs = psFindMeasurement.executeQuery();

			if (rs.next()) {
				// build Measurement object from result set
				measurement = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}

		return measurement;
	}

	/**
	 * The function builds a Measurement object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs The "rs" parameter is a ResultSet object, which contains the results of a database query.
	 * It is used to retrieve the values of the columns in the current row of the ResultSet.
	 * @return The method is returning a Measurement object.
	 */
	private Measurement buildObject(ResultSet rs) throws SQLException {
		// Create a new Measurement object
		Measurement result = new Measurement();

		// Set the properties of the Measurement object based on the values in the
		// ResultSet
		result.setMeasurementID(rs.getInt("measurement_id_PK"));
		result.setTitle(rs.getString("measurement_type"));
		result.setValue(rs.getDouble("measurement_value"));

		// return the Measurement object
		return result;
	}

	/**
	 * This function retrieves all measurements from a database and returns them as a list of Measurement
	 * objects.
	 * 
	 * @return A list of Measurement objects.
	 */
	@Override
	public List<Measurement> getAllMeasurements() {

		List<Measurement> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_ALL_MEASUREMENTS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindMeasurement.executeQuery();

			// build Measurement object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function retrieves a list of measurements from a database based on a given work order ID.
	 * 
	 * @param workOrderID an integer representing the ID of a work order for which measurements need to be
	 * retrieved from the database.
	 * @return A list of Measurement objects that are associated with a specific work order ID.
	 */
	@Override
	public List<Measurement> findMeasurementsByWorkOrderID(int workOrderID) {

		List<Measurement> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_MEASUREMENTS_BY_WORKORDER_ID)) {

			// prepare statement
			psFindMeasurement.setInt(1, workOrderID);

			// execute statement
			ResultSet rs = psFindMeasurement.executeQuery();

			// build Measurement object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}
		return list;
	}

}
