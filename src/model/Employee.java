package model;

import java.util.Calendar;
import java.util.List;

public class Employee extends Person {
	private int id;
	private String employeeNumber;
	private String socialSecurityNumber;
	private Calendar dateOfEmployment;
	private String jobTitle;
	private List<String> certificates;
	public Employee(int id,String name, String streetName, int streetNumber, String postalcode, String cityName,
			String phoneNumber, String email, String employeeNumber, String socialSecurityNumber,
			Calendar dateOfEmployment, String jobTitle, List<String> certificates) {
		super(name, streetName, streetNumber, postalcode, cityName, phoneNumber, email);
		this.id = id;
		this.employeeNumber = employeeNumber;
		this.socialSecurityNumber = socialSecurityNumber;
		this.dateOfEmployment = dateOfEmployment;
		this.jobTitle = jobTitle;
		this.certificates = certificates;
	}
	
	
	
}
