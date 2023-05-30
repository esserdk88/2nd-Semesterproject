package model;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The MasterData class defines a set of attributes and methods for storing and retrieving personal
 * information such as name, phone number, email, and address.
 */
public abstract class MasterData {
	
	private String name; 
	private String phone;
	private String email;
	private Address address;
	

	// This is a constructor method for the MasterData class that initializes the instance variables name,
	// phone, email, and address to empty strings and a new Address object with default values.
	public MasterData() {
		this.name = "";
		this.phone = "";
		this.email = "";
		this.address = new Address();
	}
	
	// This is a constructor method for the MasterData class that takes in four parameters: name, phone,
	// email, and address. It initializes the instance variables name, phone, email, and address with the
	// values passed in as arguments.
	public MasterData(String name, String phone, String email, Address address) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
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
	 * @param name The parameter "name" is a String type variable that represents the name of an object or
	 * entity. The method "setName" takes in a String value and sets it as the name of the object.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * This function returns a string representing a phone number.
	 * 
	 * @return The method is returning a String value, which is the phone number.
	 */
	public String getPhone() {
		return phone;
	}


	/**
	 * This is a Java function that sets the value of a phone variable.
	 * 
	 * @param phone The parameter "phone" is a String variable that represents the phone number of an
	 * object. The "setPhone" method is used to set the value of the phone number for that object.
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}


	/**
	 * This function returns the email address as a string.
	 * 
	 * @return The method `getEmail()` is returning a `String` value, which is the email address.
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * This function sets the email address for a given object.
	 * 
	 * @param email The parameter "email" is a String that represents the email address of an object. The
	 * method "setEmail" sets the value of the email address for that object.
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	/**
	 * The function returns an object of type Address.
	 * 
	 * @return The method `getAddress()` is returning an object of the `Address` class.
	 */
	public Address getAddress() {
		return address;
	}


	/**
	 * This function sets the address of an object.
	 * 
	 * @param address The parameter "address" is an object of the class "Address". The method "setAddress"
	 * sets the value of the instance variable "address" to the value of the parameter "address".
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * This function returns a string representation of an object that includes the name, phone number,
	 * email, and address.
	 * 
	 * @return A string representation of an object that includes the name, phone number, email, and
	 * address of the object.
	 */
	@Override
	public String toString() {
		return getName() + " " + getPhone() + " " + getEmail() + " - " + getAddress().toString();
	}
	
	

	
}
