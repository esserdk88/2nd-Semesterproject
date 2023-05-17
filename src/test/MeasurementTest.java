package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Maintenance;
import model.Measurement;
import model.Workorder;

class MeasurementTest {
	
	private int measurementID = 12;
	private String title = "temperatur";
	private double value = 69d;
	private Workorder workorder;
	
	private Measurement measurement;

	@BeforeEach
	void setUp() throws Exception {
		workorder = new Maintenance();
		measurement = new Measurement(measurementID, title, value, workorder);
		
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int measurementID;
		String title;
		double value;
		Workorder workorder;
		
		
		//Act
		measurementID = measurement.getMeasurementID();
		title = measurement.getTitle();
		value = measurement.getValue();
		workorder = measurement.getWorkorder();
		
		//Assert
		assertEquals(this.measurementID, measurementID);
		assertEquals(this.title, title);
		assertEquals(this.value, value);
		assertEquals(this.workorder, workorder);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newMeasurementID = 42;
		String newTitle = "Tryk";
		double newValue = 2.3d;
		Workorder newWorkorder = new Maintenance();
		
		//Act
		measurement.setMeasurementID(newMeasurementID);
		measurement.setTitle(newTitle);
		measurement.setValue(newValue);
		measurement.setWorkorder(newWorkorder);
		
		//Assert
		assertEquals(newMeasurementID, measurement.getMeasurementID());
		assertEquals(newTitle, measurement.getTitle());
		assertEquals(newValue, measurement.getValue());
		assertEquals(newWorkorder, measurement.getWorkorder());
	}

}
