package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Asset class represents an asset with various properties such as ID, name, description,
 * acquisition date, status, manufacturer, and location.
 */
public class Asset implements StringArrayConvertible{
	
	private int assetID;
	private String name;
	private String description;
	private Calendar aquisitionDate;
	private String status;
	private String manufacturer;
	private Location location;
	
	
	// This is a constructor method for the Asset class that initializes all the instance variables to
	// default values. The assetID is set to 0, name and description are set to empty strings,
	// aquisitionDate is set to the current date and time, status and manufacturer are set to empty
	// strings, and location is set to a new Location object with default values.
	public Asset() {
		this.assetID = 0;
		this.name = "";
		this.description = "";
		this.aquisitionDate = Calendar.getInstance();
		this.status = "";
		this.manufacturer = "";
		this.location = new Location();
	}
	
	// This is a constructor method for the Asset class that takes in parameters for all the instance
	// variables and initializes them to the values passed in. It sets the assetID to the value of the
	// assetID parameter, name to the value of the name parameter, description to the value of the
	// description parameter, aquisitionDate to the value of the aquisitionDate parameter, status to the
	// value of the status parameter, manufacturer to the value of the manufacturer parameter, and
	// location to the value of the location parameter.
	public Asset(int assetID, String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location) {
		this.assetID = assetID;
		this.name = name;
		this.description = description;
		this.aquisitionDate = aquisitionDate;
		this.status = status;
		this.manufacturer = manufacturer;
		this.location = location;
	}

	/**
	 * The function returns the asset ID as an integer.
	 * 
	 * @return The method is returning an integer value which represents the asset ID.
	 */
	public int getAssetID() {
		return assetID;
	}

	/**
	 * This function sets the asset ID of an object.
	 * 
	 * @param assetID The parameter `assetID` is an integer that represents the unique identifier of an
	 * asset. The `setAssetID` method is used to set the value of the `assetID` instance variable of an
	 * object to the specified integer value.
	 */
	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}

	/**
	 * The function returns the name.
	 * 
	 * @return The method `getName()` is returning a `String` value, which is the value of the variable
	 * `name`.
	 */
	public String getName() {
		return name;
	}

	/**
	 * This function sets the name of an object.
	 * 
	 * @param name The parameter "name" is a String type variable that represents the name of an asset 
	 * The method "setName" takes in a String value and sets it as the name of the asset.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This function returns the description of an object as a string.
	 * 
	 * @return The method `getDescription()` is returning a `String` value, which is the value of the
	 * variable `description`.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This function sets the description of an object.
	 * 
	 * @param description The parameter "description" is a String variable that represents the description
	 * of an asset. The method "setDescription" sets the value of the description variable to
	 * the value passed as an argument to the method.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * This function returns the acquisition date as a Calendar object.
	 * 
	 * @return The method is returning a Calendar object named "aquisitionDate".
	 */
	public Calendar getAquisitionDate() {
		return aquisitionDate;
	}
	
	/**
	 * This function returns a string representation of the acquisition date.
	 * 
	 * @return The method `getAquisitionDateString()` is returning a string representation of the
	 * `aquisitionDate` variable. The string is obtained by calling the `calendarToString()` method.
	 */
	public String getAquisitionDateString() {
		return calendarToString(aquisitionDate);
	}

	/**
	 * This function sets the acquisition date of an asset using a Calendar object.
	 * 
	 * @param aquisitionDate a variable of type Calendar that represents the date when an object was
	 * acquired. The method setAquisitionDate sets the value of this variable.
	 */
	public void setAquisitionDate(Calendar aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	/**
	 * The function returns the status as a string.
	 * 
	 * @return The method is returning a String value, which is the value of the variable "status".
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * This Java function sets the status of an asset.
	 * 
	 * @param status The parameter "status" is a String type variable that is used to set the status of an
	 * asset. The method "setStatus" takes in a String parameter and assigns it to the instance variable
	 * "status" of the asset.
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * The function returns the manufacturer of an asset.
	 * 
	 * @return The method is returning a String value which represents the manufacturer of an asset.
	 */
	public String getManufacturer() {
		return manufacturer;
	}

	/**
	 * This function sets the manufacturer of an asset.
	 * 
	 * @param manufacturer The parameter "manufacturer" is a String type variable that represents the name
	 * of the manufacturer of an asset. The "setManufacturer" method is used to set the value of this
	 * variable.
	 */
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	/**
	 * The function returns the location.
	 * 
	 * @return The method `getLocation()` is returning an object of type `Location`.
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * This Java function sets the location of an asset.
	 * 
	 * @param location The "location" parameter is an object of the "Location" class. The method
	 * "setLocation" sets the value of the instance variable "location" to the value passed as the
	 * parameter.
	 */
	public void setLocation(Location location) {
		this.location = location;
	}

	/**
	 * The function returns the asset ID as a string.
	 * 
	 * @return The method is returning a string representation of the asset ID.
	 */
	@Override
	public String toString() {
		
		return "" + getAssetID();
	}
	
	/**
	 * The function converts a Calendar object to a formatted string representing the date.
	 * 
	 * @param calendar A Calendar object representing a specific date and time.
	 * @return The method returns a string representation of the date in the format "dd/MM/yyyy". If the
	 * input calendar is null, the method returns the string "Dato ikke sat" (which means "Date not set"
	 * in Danish).
	 */
	public String calendarToString(Calendar calendar) {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String dateString = "Dato ikke sat";
        
        if(calendar != null) {
            dateString = dateFormat.format(calendar.getTime());
        }
        
         return dateString;
	}
	
	/**
	 * The function returns an array of strings representing various properties of an asset, including its
	 * ID, name, acquisition date, description, status, and manufacturer.
	 * 
	 * @return An array of strings representing the asset ID, name, acquisition date (formatted as
	 * "dd-MM-yyyy"), description, status, and manufacturer of an object.
	 */
	public String[] getObjectAsStringArray() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return new String[] {Integer.toString(getAssetID()), getName(), dateFormat.format(getAquisitionDate().getTime()), getDescription(), getStatus(), getManufacturer()};
	}
	
	/**
	 * This is an implementation of the equals method in Java that compares the data members of two Asset
	 * objects and returns true if they are equal.
	 * 
	 * @param o The object being compared to the current object for equality.
	 * @return A boolean value is being returned, either true or false, depending on whether the object
	 * being compared is equal to the current object or not.
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
        if (!(o instanceof Asset)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Asset m = (Asset) o;
         
        if (!this.getName().equals(m.getName())) {
        	return false;
        }
        if (!this.getDescription().equals(m.getDescription()) ) {
        	return false;
        }
        if (!this.getAquisitionDateString().equals(m.getAquisitionDateString())){
        	return false;
        }
        if (!this.getStatus().equals(m.getStatus())) {
        	return false;
        }
        if (!this.getManufacturer().equals(m.getManufacturer())) {
        	return false;
        }
        if (!this.getLocation().equals(m.getLocation())){
        	return false;
        }
        return true;
	}

	
}