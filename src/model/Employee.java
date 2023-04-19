package model;

import java.util.Calendar;

public class Employee extends Person {	
	
	private int employeeID;
	private String name;
	private String phone;
	private String email;
	private String departmentNumber;
	private String cprNumber;
	private String position;
	private Address address;
	private Calendar startDate;
	
	public Employee(String name, String streetName, int streetNumber, String postalcode, String cityName,
			String phoneNumber, String email, int employeeID, String name2, String phone, String email2,
			String departmentNumber, String cprNumber, String position, Address address, Calendar startDate) {
		super(name, streetName, streetNumber, postalcode, cityName, phoneNumber, email);
		this.employeeID = employeeID;
		name = name2;
		this.phone = phone;
		email = email2;
		this.departmentNumber = departmentNumber;
		this.cprNumber = cprNumber;
		this.position = position;
		this.address = address;
		this.startDate = startDate;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	
}
