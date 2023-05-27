package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - DCD vs. Code
 * Small adjustments done in code and DCD.
 */

/**
 * The Address class contains attributes and methods for storing and manipulating address information.
 */
public class Address {
	
	private int addressID;
	private String cityName;
	private String zipCode;
	private String streetName;
	private String streetNumber;
	
	// This is a default constructor for the Address class that initializes all the instance variables to
	// empty strings.
	public Address() {
		this.cityName = "";
		this.zipCode = "";
		this.streetName = "";
		this.streetNumber = "";
	}
	
	// This is a constructor for the Address class that takes in five parameters: addressID, cityName,
	// zipCode, streetName, and streetNumber. It initializes the instance variables of the Address object
	// with the values passed in as arguments.
	public Address(int addressID, String cityName, String zipCode, String streetName, String streetNumber) {
		this.addressID = addressID;
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}

	/**
	 * This function returns the address ID as an integer.
	 * 
	 * @return The method is returning an integer value, which is the value of the variable `addressID`.
	 */
	public int getAddressID() {
		return addressID;
	}

	/**
	 * This function sets the address ID of an object.
	 * 
	 * @param addressID The parameter "addressID" is an integer value that represents the unique
	 * identifier of an address. The method "setAddressID" sets the value of the addressID instance
	 * variable to the provided integer value.
	 */
	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	/**
	 * This function returns the name of a city as a string.
	 * 
	 * @return The method is returning the value of the variable `cityName`, which is a String
	 * representing the name of a city.
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * This function sets the name of a city.
	 * 
	 * @param cityName The parameter "cityName" is a String variable that represents the name of a city.
	 * The method "setCityName" sets the value of the instance variable "cityName" to the value passed as
	 * a parameter.
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * This function returns the zip code as a string.
	 * 
	 * @return The method is returning a String value which represents the zip code.
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * This function sets the value of the zipCode variable.
	 * 
	 * @param zipCode The parameter "zipCode" is a String variable that represents a postal code or zip
	 * code. The method "setZipCode" sets the value of the instance variable "zipCode" to the value passed
	 * as a parameter.
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * This function returns the street name as a string.
	 * 
	 * @return The method `getStreetName()` is returning the value of the `streetName` variable, which is
	 * a string representing the name of a street.
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * This function sets the street name.
	 * 
	 * @param streetName The parameter "streetName" is a String variable that represents the name of a
	 * street. The "setStreetName" method is used to set the value of the "streetName" variable to a new
	 * value passed as an argument to the method.
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * This function returns the street number as a string.
	 * 
	 * @return The method is returning a String value which represents the street number.
	 */
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * This is a Java function that sets the street number of an object.
	 * 
	 * @param streetNumber The parameter "streetNumber" is a String variable that represents the street
	 * number of an address. The "setStreetNumber" method is used to set the value of this variable.
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	
	/**
	 * This function returns a formatted string representation of an address object.
	 * 
	 * @return A string representation of an address object, including its ID, street name, street number,
	 * zip code, and city name.
	 */
	@Override
	public String toString() {
		
		return "(" + getAddressID() +") " + getStreetName() + " " + getStreetNumber() + " - " + getZipCode() + " " + getCityName();
	}
	
	/**
	 * This is an implementation of the equals() method in Java that checks if two Address objects are
	 * equal based on their city name, zip code, street name, and street number.
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
        if (!(o instanceof Address)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Address m = (Address) o;
         
        if (!this.getCityName().equals(m.getCityName())) {
        	return false;
        }
        if (!this.getZipCode().equals(m.getZipCode()) ) {
        	return false;
        }
        if (!this.getStreetName().equals(m.getStreetName())){
        	return false;
        }
        if (!this.getStreetNumber().equals(m.getStreetNumber())) {
        	return false;
        }
        return true;
	}

}