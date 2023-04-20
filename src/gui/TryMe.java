package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Employee;
import model.Reference;
import model.Supplier;

public class TryMe {

	public static void main(String[] args) {
		
		EmployeeDB employeeDB = new EmployeeDB();
		ReferenceDB referenceDB = new ReferenceDB();
		SupplierDB supplierDB = new SupplierDB();
		
//		System.out.println(supplierDB.findSupplierByID(12345678));
		for(Supplier e : supplierDB.getAllSuppliers()) {
			System.out.println(e);
		}
		
	}

}
