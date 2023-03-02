package model;

public abstract class Person {
	
	private String name;
	private String streetName;
	private String houseNumber;
	private String zipcode;
	private String cityName;
	private String phoneNumber;
	private String email;
	
	public Person(String name, String streetName, String houseNumber, String zipcode, String cityName,
			String phoneNumber, String email) {
		super();
		this.name = name;
		this.streetName = streetName;
		this.houseNumber = houseNumber;
		this.zipcode = zipcode;
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

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
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
