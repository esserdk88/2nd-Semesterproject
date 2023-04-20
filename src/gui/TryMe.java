package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Employee;

public class TryMe {

	public static void main(String[] args) {
		
		EmployeeDB employeeDB = new EmployeeDB();
		
//		System.out.println(employeeDB.findEmployeeByID(2));
		for(Employee e : employeeDB.getAllEmployees()) {
			System.out.println(e);
		}
		
	}

}
