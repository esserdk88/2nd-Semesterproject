package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - DCD vs. Code
 * Small adjustments done in code and DCD.
 */

public class Address {
	
	private String cityName;
	private String zipCode;
	private String streetName;
	private String streetNumber;
	
	public Address(String cityName, String zipCode, String streetName, String streetNumber) {
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
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
	
}