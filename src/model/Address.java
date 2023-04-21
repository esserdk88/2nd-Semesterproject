package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - DCD vs. Code
 * Small adjustments done in code and DCD.
 */

public class Address {
	//TODO: does this need a field for the id from the DB
	
	private int addressID;
	private String cityName;
	private String zipCode;
	private String streetName;
	private String streetNumber;
	
	public Address() {
		
	}
	
	public Address(String cityName, String zipCode, String streetName, String streetNumber) {
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	
	@Override
	public String toString() {
		
		return "(" + getAddressID() +") " + getStreetName() + " " + getStreetNumber() + " - " + getZipCode() + " " + getCityName();
	}

}