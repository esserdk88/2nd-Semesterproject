package model;

public class Address {
	
	private String cityName;
	private String zipCode;
	private String roadName;
	private String streetNumber;
	
	public Address(String cityName, String zipCode, String roadName, String streetNumber) {
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.roadName = roadName;
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

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	
}