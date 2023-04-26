package gui;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import dal.*;
import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.Sparepart;
import model.SparepartUsed;
import model.Supplier;
import model.Workorder;

public class TryMe {

	public static void main(String[] args) {
		
		SparepartUsedDBIF sparepartUsedDB = new SparepartUsedDB();
		MeasurementDBIF measurementDB = new MeasurementDB();
		WorkOrderDBIF workOrderDB = new WorkOrderDB();
		AssetDBIF assetDB = new AssetDB();
		
		Calendar start = Calendar.getInstance();
		start.add(Calendar.DAY_OF_MONTH, 7);
		
		Maintenance test = new Maintenance();
		try {
			test.setAsset(assetDB.findAssetByID(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.setDescription("HelloWorld");
//		test.setEmployee(null);
		test.setEndDate(start);
		test.setFinished(false);
		test.setIntervalDayCount(7);
		test.setMeasurements(null);
		test.setPriority((short) 1);
		test.setRepeated(true);
		test.setSparepartsUsed(null);
		test.setStartDate(start);
		test.setTitle("TestOrder");
		test.setType("Test");
		test.setWorkOrderID(0);
		
		
//		public Maintenance(boolean repeated, int intervalDayCount, int workOrderID, String title, String type, 
//				Calendar startDate, Calendar endDate, short priority, String description, boolean finished, 
//				List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee,
//				List<Measurement> measurements) {
//			super(workOrderID, title, type, startDate, endDate, priority, description, 
//					finished, sparepartsUsed, asset, employee, measurements);
//			this.repeated = repeated;
//			this.intervalDayCount = intervalDayCount;
//		}
		
//		System.out.println(workOrderDB.findMaintenanceWorkOrderByID(1));
//		for(Workorder e : workOrderDB.getAllUnfinishedWorkOrders()) {
//			System.out.println(e);
//		}
		
		System.out.println(workOrderDB.deleteWorkOrderByID(7));
		
//		System.out.println(workOrderDB.addMaintenanceWorkOrder(test));
		
	}
}
