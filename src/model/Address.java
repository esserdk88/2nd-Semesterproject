package model;

public class Address {
	
	private String cityName;
	private String zipcode;
	private String roadName;
	private String streetNumber;
	
	public Address(String cityName, String zipcode, String roadName, String streetNumber) {
		this.cityName = cityName;
		this.zipcode = zipcode;
		this.roadName = roadName;
		this.streetNumber = streetNumber;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
