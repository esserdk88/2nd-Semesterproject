package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Employee extends MasterData implements StringArrayConvertible{	
	
	private int employeeID;
	private String cprNumber; 
	private Calendar startDate;
	private String position; 
	
	public Employee() {
		super();
		
	}

	public Employee(int employeeID, String cprNumber, Calendar startDate, String position,
					String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.employeeID = employeeID;
		this.cprNumber = cprNumber;
		this.startDate = startDate;
		this.position = position;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getCprNumber() {
		return cprNumber;
	}

	public void setCprNumber(String cprNumber) {
		this.cprNumber = cprNumber;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
    private String calendarToString(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(calendar.getTime());
    }

	@Override
	public String toString() {
		return "(" + getEmployeeID() + ") " + getCprNumber() + " " + calendarToString(getStartDate()) + " " + getPosition() + " - " + super.toString();
	}

	@Override
	public String[] getObjectAsStringArray() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return new String[] {Integer.toString(getEmployeeID()), getCprNumber(), dateFormat.format(getStartDate().getTime()), getPosition(), getName(), getPhone(), getEmail()};
	}
}