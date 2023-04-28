package model;

/*
 * 19-04-2023: Reworked with alignment from DCD
 */

public class Measurement {
	
	private int measurementID;
	private String title;
	private double value;
	private Workorder workorder;
	
	public Measurement() {
		//Left empty
	}

	public Measurement(int measurementID, String title, double value, Workorder workorder) {
		this.measurementID = measurementID;
		this.title = title;
		this.value = value;
		this.workorder = workorder;
	}



	public int getMeasurementID() {
		return measurementID;
	}



	public void setMeasurementID(int measurementID) {
		this.measurementID = measurementID;
	}



	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Workorder getWorkorder() {
		return workorder;
	}

	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

	@Override
	public String toString() {
		return "(" + measurementID + ") " + getTitle() + " : " + getValue();
	}
	
	

	
}
