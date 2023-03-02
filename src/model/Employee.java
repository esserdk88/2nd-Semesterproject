package model;

import java.util.Calendar;
import java.util.List;

public class Employee extends Person {
	private String employeeNumber;
	private String socialSecurityNumber;
	private Calendar dateOfEmployment;
	private String jobTitle;
	private List<String> certificates;
	public Employee(String name, String streetName, String houseNumber, String zipcode, String cityName,
			String phoneNumber, String email, String employeeNumber, String socialSecurityNumber,
			Calendar dateOfEmployment, String jobTitle, List<String> certificates) {
		super(name, streetName, houseNumber, zipcode, cityName, phoneNumber, email);
		this.employeeNumber = employeeNumber;
		this.socialSecurityNumber = socialSecurityNumber;
		this.dateOfEmployment = dateOfEmployment;
		this.jobTitle = jobTitle;
		this.certificates = certificates;
	}
	
	
	
}
