package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 * 03-05-2023: Added equals method
 */

/**
 * The "Reference" class extends the "MasterData" class and adds additional fields and methods for
 * storing and comparing references.
 */
public class Reference extends MasterData {
	
	private int cvr;
	private String contact;
	
	// This is a default constructor for the `Reference` class that calls the default constructor of its
	// superclass `MasterData` using the `super()` keyword. It initializes a new `Reference` object with
	// default values for all its fields.
	public Reference() {
		super();
	}
	
	// This is a constructor for the `Reference` class that takes in six parameters: `cvr`, `contact`,
	// `name`, `phone`, `email`, and `address`. It calls the constructor of its superclass `MasterData`
	// using the `super()` keyword and passes in `name`, `phone`, `email`, and `address` as arguments. It
	// then sets the values of `cvr` and `contact` using the `this` keyword. This constructor is used to
	// create a new `Reference` object with the specified values for its fields.
	public Reference(int cvr, String contact, String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.cvr = cvr;
		this.contact = contact;
	}

	/**
	 * The function returns the value of the variable "cvr".
	 * 
	 * @return The method is returning an integer value, which is the value of the variable "cvr".
	 */
	public int getCvr() {
		return cvr;
	}

	/**
	 * The function sets the value of the "cvr" variable.
	 * 
	 * @param cvr The parameter "cvr" is an integer value that represents the CVR (Central Business
	 * Register) number of a company. This method sets the value of the "cvr" instance variable to the
	 * provided value.
	 */
	public void setCvr(int cvr) {
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
	
	/**
	 * This is an implementation of the equals() method in Java that compares the data members of two
	 * objects of the Reference class to determine if they are equal.
	 * 
	 * @param o The object being compared to the current object for equality.
	 * @return A boolean value is being returned, either true or false, depending on whether the object
	 * being compared is equal to the current object or not.
	 */
	@Override
	public boolean equals(Object o) {
		// object variables

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Reference or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof Reference)) {
			return false;
		}

		// typecast o to Reference so that we can compare data members
		Reference r = (Reference) o;

		if (this.getCvr() != r.getCvr()) {
			return false;
		}
		if (!this.getContact().equals(r.getContact())) {
			return false;
		}
		if (!this.getName().equals(r.getName())) {
			return false;
		}
		if (!this.getPhone().equals(r.getPhone())) {
			return false;
		}
		if (!this.getEmail().equals(r.getEmail())){
			return false;
		}
		if (!this.getAddress().equals(r.getAddress())) {
			return false;
		}
		return true;
	}

	
	
}