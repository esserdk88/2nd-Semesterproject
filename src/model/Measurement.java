package model;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Reworked with alignment from DCD
 */

/**
 * The Measurement class represents a measurement with an ID, title, value, and associated work order,
 * and implements the StringArrayConvertible interface.
 */
public class Measurement implements StringArrayConvertible {
	
	private int measurementID;
	private String title;
	private double value;
	private Workorder workorder;
	
	// This is a default constructor for the `Measurement` class that takes no arguments and does not
	// perform any actions. It is left empty intentionally to allow for instances of the `Measurement`
	// class to be created without any initial values.
	public Measurement() {
		//Left empty
	}

	// This is a constructor for the `Measurement` class that takes four arguments: an integer
	// `measurementID`, a string `title`, a double `value`, and a `Workorder` object `workorder`. It
	// initializes the instance variables of the `Measurement` object with the values passed as arguments.
	public Measurement(int measurementID, String title, double value, Workorder workorder) {
		this.measurementID = measurementID;
		this.title = title;
		this.value = value;
		this.workorder = workorder;
	}



	/**
	 * This function returns the measurement ID as an integer.
	 * 
	 * @return The method is returning an integer value which represents the measurement ID.
	 */
	public int getMeasurementID() {
		return measurementID;
	}



	/**
	 * This function sets the measurement ID for a Java object.
	 * 
	 * @param measurementID measurementID is an integer variable that represents the unique identifier of
	 * a measurement. The method setMeasurementID() sets the value of this variable to the provided
	 * integer parameter.
	 */
	public void setMeasurementID(int measurementID) {
		this.measurementID = measurementID;
	}



	/**
	 * The function returns the title of an object.
	 * 
	 * @return The method `getTitle()` is returning a `String` value, which is the value of the variable
	 * `title`.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This Java function sets the title of an object.
	 * 
	 * @param title The parameter "title" is a String type variable that represents the title of an
	 * object. The method "setTitle" sets the value of the title variable to the value passed as an
	 * argument to the method.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * The function returns a double value.
	 * 
	 * @return The method is returning a double value, which is the value of the variable "value".
	 */
	public double getValue() {
		return value;
	}

	/**
	 * This Java function sets the value of a double variable.
	 * 
	 * @param value value is a variable of type double that is being set to a new value passed as a
	 * parameter to the method.
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * This function returns a Workorder object.
	 * 
	 * @return The method is returning a Workorder object.
	 */
	public Workorder getWorkorder() {
		return workorder;
	}

	/**
	 * This function sets the workorder object for a given instance.
	 * 
	 * @param workorder The parameter "workorder" is an object of the class "Workorder". The method
	 * "setWorkorder" sets the value of the instance variable "workorder" to the value passed as the
	 * parameter.
	 */
	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

	/**
	 * This function returns a string representation of a measurement object, including its ID, title, and
	 * value.
	 * 
	 * @return A string representation of an object, which includes the measurement ID, title, and value.
	 */
	@Override
	public String toString() {
		return "(" + measurementID + ") " + getTitle() + " : " + getValue();
	}
	
	/**
	 * The function returns an array of strings representing the measurement ID, title, and value of an
	 * object.
	 * 
	 * @return An array of strings containing the measurement ID, title, and value of an object.
	 */
	@Override
	public String[] getObjectAsStringArray() {
		return new String[] {String.valueOf(getMeasurementID()), getTitle(), String.valueOf(getValue())};
	}
	
	

	
}
