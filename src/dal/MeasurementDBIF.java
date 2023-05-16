package dal;

import java.util.List;

import model.Measurement;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Measurements.
 */

public interface MeasurementDBIF {

	public Measurement findMeasurementByID(int measurementID);

	public List<Measurement> findMeasurementsByWorkOrderID(int workOrderID);

	public List<Measurement> getAllMeasurements();

}
