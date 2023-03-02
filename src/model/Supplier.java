package model;

public class Supplier extends Person {
	private String contactName;

	public Supplier(String name, String streetName, String houseNumber, String zipcode, String cityName,
			String phoneNumber, String email, String contactName) {
		super(name, streetName, houseNumber, zipcode, cityName, phoneNumber, email);
		this.contactName = contactName;
	}
	
}
