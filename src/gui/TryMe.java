package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Employee;
import model.Reference;

public class TryMe {

	public static void main(String[] args) {
		
		EmployeeDB employeeDB = new EmployeeDB();
		ReferenceDB referenceDB = new ReferenceDB();
		
//		System.out.println(referenceDB.findReferenceByID(12345678));
		for(Reference e : referenceDB.getAllReferences()) {
			System.out.println(e);
		}
		
	}

}
