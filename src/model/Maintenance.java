package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Maintenance class extends the Workorder class and implements the StringArrayConvertible
 * interface, and contains variables and methods related to maintenance work orders.
 */
public class Maintenance extends Workorder implements StringArrayConvertible {

	private boolean repeated;
	private int intervalDayCount;

	// This is a constructor for the Maintenance class that initializes the repeated and intervalDayCount
	// variables to false and 0 respectively. It also calls the constructor of the superclass (Workorder)
	// with no arguments using the super() method. However, the local variables repeated and
	// intervalDayCount are not actually used to set the values of the instance variables with the same
	// names, so they will remain at their default values of false and 0.
	public Maintenance() {
		super();
		boolean repeated = false;
		int intervalDayCount = 0;
	}

	// This is a constructor for the Maintenance class that takes in several parameters and initializes
	// the instance variables of the class with the values of these parameters. It also calls the
	// constructor of the superclass (Workorder) with the same parameters using the super() method. The
	// local variables repeated and intervalDayCount are used to set the values of the instance variables
	// with the same names.
	public Maintenance(boolean repeated, int intervalDayCount, int workOrderID, String title, String type,
			Calendar startDate, Calendar endDate, short priority, String description, boolean finished,
			List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
		this.repeated = repeated;
		this.intervalDayCount = intervalDayCount;
	}

	/**
	 * The function returns a boolean value indicating whether a certain condition is repeated or not.
	 * 
	 * @return A boolean value representing whether a certain condition
	 * is true or false.
	 */
	public boolean isRepeated() {
		return repeated;
	}

	/**
	 * This function sets a boolean value for whether a task is repeated or not.
	 * 
	 * @param repeated a boolean variable that indicates whether a certain action or event is repeated or
	 * not. If the value is true, it means that the action or event is repeated, and if the value is
	 * false, it means that the action or event is not repeated. This method sets the value of the
	 * "repeated
	 */
	public void setRepeated(boolean repeated) {
		this.repeated = repeated;
	}

	/**
	 * This function returns the interval day count.
	 * 
	 * @return The method is returning an integer value which represents the interval day count.
	 */
	public int getIntervalDayCount() {
		return intervalDayCount;
	}

	/**
	 * This function sets the interval day count for a certain object.
	 * 
	 * @param intervalDayCount The parameter "intervalDayCount" is an integer value that represents the
	 * number of days between intervals. This method sets the value of the instance variable
	 * "intervalDayCount" to the value passed as a parameter.
	 */
	public void setIntervalDayCount(int intervalDayCount) {
		this.intervalDayCount = intervalDayCount;
	}
	
	/**
	 * The function takes a short input and returns a string indicating the priority level.
	 * 
	 * @param input a short integer representing the priority level of a task, where 1 is low, 2 is
	 * medium, and 3 is high.
	 * @return The method is returning a String value that represents the priority level based on the
	 * input parameter. The returned value could be "Lav", "Mellem", or "Høj" depending on the value of
	 * the input parameter. If the input parameter is not 1, 2, or 3, then an empty String will be
	 * returned.
	 */
	public String formatPriority(short input) {
		String type = "";
		switch (input) {
		case 1:
			type = "Lav";
			break;
		case 2:
			type = "Mellem";
			break;
			
		case 3:
			type = "Høj";
			break;
		default:
		}
		return type;
	}

	/**
	 * The function returns an array of strings representing various properties of a work order object.
	 * 
	 * @return An array of strings representing various properties of a work order object, including its
	 * ID, title, type, start date, priority, description, finished status, associated asset ID, and
	 * assigned employee name (if any).
	 */
	@Override
	public String[] getObjectAsStringArray() {
		return new String[] { Integer.toString(getWorkOrderID()), getTitle(), getType(), calendarToString(getStartDate()), "",
				formatPriority(getPriority()), getDescription(), Boolean.toString(isFinished()),
				Integer.toString(getAsset().getAssetID()),
				(getEmployee() == null) ? "Ingen medarbejder" : getEmployee().getName() };
	}

	/**
	 * This is an implementation of the equals method in Java that compares the attributes of two
	 * Maintenance objects and returns true if they are equal.
	 * 
	 * @param o The object being compared to the current object for equality.
	 * @return A boolean value is being returned, indicating whether the object being compared to is equal
	 * to the current object or not.
	 */
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Maintenance)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Maintenance m = (Maintenance) o;
         
        if (!this.getTitle().equals(m.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(m.getType()) ) {
        	System.out.println(this.getType() + " " + m.getType());
        	return false;
        }
        if (!this.getStartDateString().equals(m.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(m.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != m.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(m.getDescription())){
        	return false;
        }
        if (this.isFinished() != m.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(m.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(m.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && m.getEmployee() == null) || (this.getEmployee() == null && m.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && m.getEmployee() != null) && !this.getEmployee().equals(m.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(m.getMeasurements())){
        	return false;
        }
        if (this.isRepeated() != m.isRepeated()) {
        	return false;
        }
        if (this.getIntervalDayCount() != m.getIntervalDayCount()) {
        	return false;
        }
        return true;
	}

}