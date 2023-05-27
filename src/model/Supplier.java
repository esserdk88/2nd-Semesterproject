package model;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Supplier class extends the MasterData class and adds additional attributes such as cvr and
 * contact.
 */
public class Supplier extends MasterData {
	
	
	private String cvr;
	private String contact;
	
	// This is a default constructor for the `Supplier` class that calls the constructor of its superclass
	// `MasterData` using the `super()` keyword with no arguments. This means that it initializes the
	// attributes inherited from `MasterData` to their default values.
	public Supplier() {
		super();
	}
	
	// This is a constructor for the `Supplier` class that takes in parameters for `cvr`, `contact`,
	// `name`, `phone`, `email`, and `address`. It calls the constructor of its superclass `MasterData`
	// using the `super()` keyword with arguments `name`, `phone`, `email`, and `address`. This means that
	// it initializes the attributes inherited from `MasterData` with the values passed in for `name`,
	// `phone`, `email`, and `address`. It also sets the `cvr` and `contact` attributes of the `Supplier`
	// object to the values passed in for `cvr` and `contact`, respectively.
	public Supplier(String cvr, String contact, String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.cvr = cvr;
		this.contact = contact;
	}

	/**
	 * The function returns a string representing a CVR number.
	 * 
	 * @return The method `getCvr()` is returning a `String` value, which is the value of the variable
	 * `cvr`.
	 */
	public String getCvr() {
		return cvr;
	}

	/**
	 * The function sets the value of the "cvr" variable.
	 * 
	 * @param cvr The parameter "cvr" is a String that represents the CVR (Central Business Register)
	 * number of a company. This method sets the value of the "cvr" instance variable to the value passed
	 * as an argument.
	 */
	public void setCvr(String cvr) {
		this.cvr = cvr;
	}

	/**
	 * The function returns a string representing a contact.
	 * 
	 * @return The method is returning a String value, which is the value of the variable "contact".
	 */
	public String getContact() {
		return contact;
	}

	/**
	 * This is a Java function that sets the value of a contact variable.
	 * 
	 * @param contact The parameter "contact" is a string that represents the contact information of an
	 * object. The "setContact" method is used to set the value of the "contact" variable of an object to
	 * the value passed as a parameter.
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	/**
	 * This function overrides the default toString method to return a string representation of an object
	 * with its CVR, contact, and other details.
	 * 
	 * @return A string representation of an object, which includes the CVR number, contact information,
	 * and the string representation of the object's superclass.
	 */
	@Override
	public String toString() {
		return "(" + getCvr() + ") " + getContact() + " " + super.toString();
	}

	
}
