package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.Sparepart;
import model.SparepartUsed;
import model.Supplier;

public class TryMe {

	public static void main(String[] args) {
		
		SparepartUsedDBIF sparepartUsedDB = new SparepartUsedDB();
		MeasurementDBIF measurementDB = new MeasurementDB();
		WorkOrderDBIF workOrderDB = new WorkOrderDB();
		
//		System.out.println(workOrderDB.findMaintenanceWorkOrderByID(1));
		for(Maintenance e : workOrderDB.getAllMaintenanceWorkOrders()) {
			System.out.println(e);
		}
		
	}

}
