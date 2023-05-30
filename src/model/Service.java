package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Service class extends the Workorder class and implements the StringArrayConvertible interface,
 * and includes methods for getting and setting a reference, checking for equality with another object,
 * and formatting priority.
 */
public class Service extends Workorder implements StringArrayConvertible {

	private Reference reference;

	// This is a default constructor for the Service class that calls the constructor of its superclass,
	// Workorder, with no arguments using the `super()` keyword. This initializes the inherited fields of
	// the Service object with default values.
	public Service() {
		super();
	}

	// This is a constructor for the Service class that takes in several parameters and initializes the
	// fields of the Service object with the values of those parameters. The constructor calls the
	// constructor of its superclass, Workorder, with the same parameters using the `super()` keyword, and
	// then sets the value of the `reference` field to the value of the `reference` parameter using the
	// `this` keyword.
	public Service(Reference reference, int workOrderID, String title, String type, Calendar startDate,
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed,
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
		this.reference = reference;
	}

	/**
	 * The function returns a reference object.
	 * 
	 * @return The method `getReference()` is returning an object of type `Reference`.
	 */
	public Reference getReference() {
		return reference;
	}

	/**
	 * This function sets the reference of an object to a given reference.
	 * 
	 * @param reference The "reference" parameter is an object of the class "Reference". The method
	 * "setReference" sets the value of the instance variable "reference" to the value of the parameter
	 * "reference".
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	/**
	 * This is an implementation of the equals() method in Java that compares the attributes of two
	 * Service objects to determine if they are equal.
	 * 
	 * @param o The object being compared to the current object for equality.
	 * @return A boolean value indicating whether the object being compared to is equal to the current
	 * object or not.
	 */
	@Override
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Service)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Service s = (Service) o;
         
        if (!this.getTitle().equals(s.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(s.getType()) ) {
        	return false;
        }
        if (!this.getStartDateString().equals(s.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(s.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != s.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(s.getDescription())){
        	return false;
        }
        if (this.isFinished() != s.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(s.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(s.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && s.getEmployee() == null) || (this.getEmployee() == null && s.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && s.getEmployee() != null) && !this.getEmployee().equals(s.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(s.getMeasurements())){
        	return false;
        }
        if(!this.getReference().equals(s.getReference())) {
        	return false;
        }
        return true;
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
}