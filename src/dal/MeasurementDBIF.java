package dal;

import model.Measurement;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Measurements.
 */

public interface MeasurementDBIF {
	
	public Measurement findMeasurementByID(int measurementID);
	
}
