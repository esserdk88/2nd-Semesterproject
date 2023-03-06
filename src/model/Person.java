package model;

public abstract class Person {
	
	private String name;
	private String streetName;
	private int streetNumber;
	private String postalcode;
	private String cityName;
	private String phoneNumber;
	private String email;
	
	public Person(String name, String streetName, int streetNumber, String postalcode, String cityName,
			String phoneNumber, String email) {
		this.name = name;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
		this.postalcode = postalcode;
		this.cityName = cityName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public int getHouseNumber() {
		return streetNumber;
	}

	public void setHouseNumber(int streetNumber) {
		this.streetNumber = streetNumber;
	}

	public String getZipcode() {
		return postalcode;
	}

	public void setZipcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
	
}
