package other;

import dal.WorkOrderDB;
import dal.interfaces.WorkOrderDBIF;

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