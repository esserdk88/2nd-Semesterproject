package model;

import java.util.Calendar;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Employee extends MasterData {	
	
	private int employeeID; 
	private String departmentNumber; 
	private String cprNumber; 
	private Calendar startDate;
	private String position; 
	
	public Employee() {
		super();
		
	}

	public Employee(int employeeID, String departmentNumber, String cprNumber, Calendar startDate, String position,
					String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.employeeID = employeeID;
		this.departmentNumber = departmentNumber;
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

	public String getDepartmentNumber() {
		return departmentNumber;
	}

	public void setDepartmentNumber(String departmentNumber) {
		this.departmentNumber = departmentNumber;
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
	
}