package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 * 03-05-2023: Added equals method
 */

/**
 * The Employee class extends MasterData and implements StringArrayConvertible, and contains attributes
 * such as employeeID, cprNumber, startDate, and position, as well as methods for getting and setting
 * these attributes, converting a calendar to a string, and comparing objects.
 */
public class Employee extends MasterData implements StringArrayConvertible {

	private int employeeID;
	private String cprNumber;
	private Calendar startDate;
	private String position;

	// This is a default constructor for the Employee class. It initializes the attributes of the Employee
	// object to default values. The cprNumber attribute is set to an empty string, the startDate
	// attribute is set to the current date and time using the Calendar.getInstance() method, and the
	// position attribute is set to an empty string. The super() method calls the constructor of the
	// superclass (MasterData) to initialize its attributes to default values as well.
	public Employee() {
		super();
		this.cprNumber = "";
		this.startDate = Calendar.getInstance();
		this.position = "";
	}

	// This is a constructor for the Employee class that takes in several parameters including employeeID,
	// cprNumber, startDate, position, name, phone, email, and address. It calls the constructor of the
	// superclass (MasterData) using the super() method to initialize its attributes to default values. It
	// then sets the values of the employeeID, cprNumber, startDate, and position attributes using the
	// values passed in as parameters.
	public Employee(int employeeID, String cprNumber, Calendar startDate, String position,
			String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.employeeID = employeeID;
		this.cprNumber = cprNumber;
		this.startDate = startDate;
		this.position = position;
	}

	/**
	 * The function returns the employee ID as an integer.
	 * 
	 * @return The method is returning an integer value which represents the employee ID.
	 */
	public int getEmployeeID() {
		return employeeID;
	}

	/**
	 * This function sets the employee ID for an employee.
	 * 
	 * @param employeeID employeeID is an integer variable that represents the unique identification
	 * number of an employee. The method setEmployeeID() is used to set the value of this variable.
	 */
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * This function returns the CPR number as a string.
	 * 
	 * @return The method `getCprNumber()` is returning a `String` value, which is the value of the
	 * variable `cprNumber`.
	 */
	public String getCprNumber() {
		return cprNumber;
	}

	/**
	 * This function sets the CPR number for an object.
	 * 
	 * @param cprNumber The parameter "cprNumber" is a String variable that represents a person's CPR
	 * number. The method "setCprNumber" sets the value of the instance variable "cprNumber" to the value
	 * passed as an argument to the method.
	 */
	public void setCprNumber(String cprNumber) {
		this.cprNumber = cprNumber;
	}

	/**
	 * The function returns the start date of a calendar.
	 * 
	 * @return The method `getStartDate()` is returning a `Calendar` object representing the start date.
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	/**
	 * The function returns a string representation of a calendar start date.
	 * 
	 * @return A string representation of the start date, which is obtained by calling the method
	 * `calendarToString()` on the `startDate` object.
	 */
	public String getStartDateString() {
		return calendarToString(startDate);
	}

	/**
	 * This function sets the start date of an object using a Calendar object.
	 * 
	 * @param startDate startDate is a variable of type Calendar that is being set by the method
	 * setStartDate. The value passed as an argument to this method will be assigned to the startDate
	 * variable.
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * The function returns a string representing the position.
	 * 
	 * @return The method `getPosition()` is returning a `String` value. The specific value being returned
	 * depends on the implementation of the `position` variable.
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * This Java function sets the position of an object.
	 * 
	 * @param position The parameter "position" is a String variable that represents the position of an
	 * object or entity. This method sets the value of the "position" variable to the value passed as an
	 * argument to the method.
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * The function converts a Calendar object to a formatted string representation of the date.
	 * 
	 * @param calendar The parameter "calendar" is an instance of the Calendar class in Java, which
	 * represents a specific date and time. It can be used to perform various operations related to date
	 * and time, such as getting or setting the year, month, day, hour, minute, second, etc.
	 * @return The method `calendarToString` returns a `String` representation of the `Calendar` object
	 * passed as a parameter, formatted as "dd-MM-yyyy".
	 */
	private String calendarToString(Calendar calendar) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(calendar.getTime());
	}

	/**
	 * This function returns a string representation of an employee's ID, CPR number, start date,
	 * position, and other information.
	 * 
	 * @return A string representation of an employee object, including their ID, CPR number, start date,
	 * position, and personal information (inherited from the superclass).
	 */
	@Override
	public String toString() {
		return "(" + getEmployeeID() + ") " + getCprNumber() + " " + calendarToString(getStartDate()) + " "
				+ getPosition() + " - " + super.toString();
	}

	/**
	 * This is an implementation of the equals() method in Java that compares the data members of two
	 * Employee objects to determine if they are equal.
	 * 
	 * @param o The object being compared to the current Employee object for equality.
	 * @return A boolean value is being returned, either true or false.
	 */
	@Override
	public boolean equals(Object o) {
		// object variables

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Employee or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof Employee)) {
			return false;
		}

		// typecast o to Employee so that we can compare data members
		Employee e = (Employee) o;

		if (!this.getCprNumber().equals(e.getCprNumber())) {
			return false;
		}
		if (!this.getStartDateString().equals(e.getStartDateString()) ) {
			return false;
		}
		if (!this.getPosition().equals(e.getPosition())) {
			return false;
		}
		if (!this.getName().equals(e.getName())) {
			return false;
		}
		if (!this.getPhone().equals(e.getPhone())) {
			return false;
		}
		if (!this.getEmail().equals(e.getEmail())) {
			return false;
		}
		if (!this.getAddress().equals(e.getAddress())) {
			return false;
		}
		return true;
	}

	/**
	 * The function returns an array of strings containing employee information formatted with a specific
	 * date format.
	 * 
	 * @return An array of strings containing the employee's ID, CPR number, start date (formatted as
	 * "dd-MM-yyyy"), position, name, phone number, and email address.
	 */
	@Override
	public String[] getObjectAsStringArray() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return new String[] { Integer.toString(getEmployeeID()), getCprNumber(),
				dateFormat.format(getStartDate().getTime()), getPosition(), getName(), getPhone(), getEmail() };

	}
}