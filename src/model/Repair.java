package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Repair class extends the Workorder class and implements the StringArrayConvertible interface,
 * and contains properties and methods related to repairs.
 */
public class Repair extends Workorder implements StringArrayConvertible {

	private double price;
	private Reference reference;

	// This is a default constructor for the `Repair` class that calls the default constructor of its
	// superclass `Workorder` using the `super()` keyword. It initializes a new instance of the `Repair`
	// class with default values for all its properties.
	public Repair() {
		super();
	}

	// This is a constructor for the `Repair` class that takes in several parameters and initializes a new
	// instance of the `Repair` class with the given values for its properties. It calls the constructor
	// of its superclass `Workorder` using the `super()` keyword to initialize the properties inherited
	// from `Workorder`. It also sets the `price` and `reference` properties of the `Repair` instance
	// using the values passed in as parameters.
	public Repair(double price, Reference reference, int workOrderID, String title, String type, Calendar startDate,
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed,
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
		this.price = price;
		this.reference = reference;
	}

	/**
	 * The function returns the price as a double data type.
	 * 
	 * @return The method `getPrice()` is returning a `double` value, which is the `price` of an object.
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * The function sets the price of an object.
	 * 
	 * @param price The parameter "price" is a double data type that represents the price of a product or
	 * service. The method "setPrice" sets the value of the price variable to the value passed as an
	 * argument to the method.
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * The function returns a reference object.
	 * 
	 * @return The method is returning a reference object. The specific reference object being returned is
	 * determined by the value of the instance variable `reference`.
	 */
	public Reference getReference() {
		return reference;
	}

	/**
	 * This function sets the reference of an object to a given reference.
	 * 
	 * @param reference The "reference" parameter is an object of the class "Reference". The method
	 * "setReference" sets the value of the instance variable "reference" to the value of the "reference"
	 * parameter.
	 */
	public void setReference(Reference reference) {
		this.reference = reference;
	}
	
	/**
	 * This is an implementation of the equals() method in Java that compares the attributes of two Repair
	 * objects to determine if they are equal.
	 * 
	 * @param o The object being compared to the current object for equality.
	 * @return A boolean value indicating whether the current object is equal to the object passed as a
	 * parameter.
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
        if (!(o instanceof Repair)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Repair r = (Repair) o;
         
        if (!this.getTitle().equals(r.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(r.getType()) ) {
        	return false;
        }
        if (!this.getStartDateString().equals(r.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(r.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != r.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(r.getDescription())){
        	return false;
        }
        if (this.isFinished() != r.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(r.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(r.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && r.getEmployee() == null) || (this.getEmployee() == null && r.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && r.getEmployee() != null) && !this.getEmployee().equals(r.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(r.getMeasurements())){
        	return false;
        }
        if(!this.getReference().equals(r.getReference())) {
        	return false;
        }
        if(this.getPrice() != r.getPrice()) {
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