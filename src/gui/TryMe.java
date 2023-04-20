package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Employee;
import model.Reference;
import model.Sparepart;
import model.Supplier;

public class TryMe {

	public static void main(String[] args) {
		
		EmployeeDB employeeDB = new EmployeeDB();
		ReferenceDB referenceDB = new ReferenceDB();
		SupplierDB supplierDB = new SupplierDB();
		SparepartDB sparepartDB = new SparepartDB();
		
//		System.out.println(sparepartDB.findSparePartbyID(1));
		for(Sparepart e : sparepartDB.getAllSpareparts()) {
			System.out.println(e);
		}
		
	}

}
