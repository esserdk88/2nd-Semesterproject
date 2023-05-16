package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Measurement;

/*
 * 24-04-2023: Rasmus/Mikkel - Implemented class from interface
 */


public class MeasurementDB implements MeasurementDBIF {
	
	public static final String FIELDS = "measurement_id_PK, measurement_type, measurement_value, measurement_workorder_id_FK";
	public static final String SELECT_MEASUREMENT_BY_ID = "SELECT " + FIELDS + " FROM Measurement Where measurement_id_PK = ?";
	public static final String SELECT_MEASUREMENTS_BY_WORKORDER_ID = "SELECT " + FIELDS + " FROM Measurement Where measurement_workorder_id_FK = ?";
	public static final String SELECT_ALL_MEASUREMENTS = "SELECT " + FIELDS + " FROM Measurement";

	private Connection con = DatabaseConnection.getInstance().getConnection();

	@Override
	public Measurement findMeasurementByID(int measurementID) {

		Measurement measurement = null;
		
		// establish database connection
		try (PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_MEASUREMENT_BY_ID)) {
			
			//prepare statement
			psFindMeasurement.setInt(1, measurementID);
			
			//execute statement
			ResultSet rs = psFindMeasurement.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Measurement object from result set
				measurement = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}
		
		return measurement;
	}

	private Measurement buildObject(ResultSet rs) throws SQLException {
		// Create a new Measurement object
		Measurement result = new Measurement();

		// Set the properties of the Measurement object based on the values in the ResultSet
		result.setMeasurementID(rs.getInt("measurement_id_PK"));
		result.setTitle(rs.getString("measurement_type"));
		result.setValue(rs.getDouble("measurement_value"));
		
		// return the Measurement object
		return result;
	}

	@Override
	public List<Measurement> getAllMeasurements() {
		
		List<Measurement> list = new ArrayList<>();
		
		// establish database connection
		try (PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_ALL_MEASUREMENTS)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindMeasurement.executeQuery();
			
			if (rs != null) {
				//build Measurement object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Measurement> findMeasurementsByWorkOrderID(int workOrderID) {
		
		List<Measurement> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindMeasurement = con.prepareStatement(SELECT_MEASUREMENTS_BY_WORKORDER_ID)) {
			
			//prepare statement
			psFindMeasurement.setInt(1, workOrderID);
			
			//execute statement
			ResultSet rs = psFindMeasurement.executeQuery();
			
			if (rs != null) {
				//build Measurement object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING MEASUREMENT:" + e.getMessage());
		}
		return list;
	}

}
