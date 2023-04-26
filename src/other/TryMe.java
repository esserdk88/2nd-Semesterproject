package other;

import java.util.Iterator;

import dal.AddressDB;
import dal.LocationDB;
import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import gui.EmployeeOverview;
import model.Address;
import model.Location;
import model.Workorder;

public class TryMe {

	public static void main(String[] args) {
		
//		AddressDB aDB = new AddressDB();
//		LocationDB locationDB = new LocationDB();
		WorkOrderDBIF workOrderDB = new WorkOrderDB();
		
		
//		for(Workorder e : workOrderDB.getAllMaintenanceWorkOrders()) {
//			System.out.println(e);
//		}
		
		System.out.println(workOrderDB.findMaintenanceWorkOrderByID(1));
		
	}
}