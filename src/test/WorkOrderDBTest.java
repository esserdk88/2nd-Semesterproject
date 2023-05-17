package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dal.interfaces.AssetDBIF;
import dal.interfaces.ReferenceDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.Asset;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.SparepartUsed;
import model.Workorder;

class WorkOrderDBTest {
	
	//Common fields for use 
	private static String title = "Test opgave";
	private static Calendar startDate = Calendar.getInstance();
	private static Calendar endDate = Calendar.getInstance();
	private static short priority = 3;
	private static String description = "Test beskrivelse";
	private static boolean finished = false;
	
	
	private static List<SparepartUsed> sparepartsUsed = new ArrayList<>();
	private static Asset asset;
	private static List<Measurement> measurements = new ArrayList<>();
	
	//Maintenance fields
	private static boolean repeated = true;
	private static int intervalDayCount = 7;
	
	//Service fields
	private static Reference reference;
	
	//Repair fields
	private static double price = 100.43;
	
	private static Maintenance maintenance;
	private static Service service;
	private static Repair repair;
	
	//Instances
	private WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	private static AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
	private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
	
	@BeforeAll
	static void startUp() throws SQLException {
		DatabaseConnection.getInstance().startTransaction();
		asset = assetDB.findAssetByID(1);
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		service = new Service(reference, 0, title, "Service", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
	}
	@AfterAll
	static void tearDown() throws SQLException {
		DatabaseConnection.getInstance().rollbackTransaction();
	}
			
	@Test
	void addMaintenanceWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		
		//Act
		success = workOrderDB.addMaintenanceWorkOrder(maintenance);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addServiceWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		
		//Act
		success = workOrderDB.addServiceWorkOrder(service);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addRepairWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		
		//Act
		success = workOrderDB.addRepairWorkOrder(repair);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void findMaintenanceWorkOrderByIDTest() {
		//TODO Finish this test
		//Arrange
		workOrderDB.addMaintenanceWorkOrder(maintenance);
		Maintenance foundMaintenance = null;
		int latestKey = workOrderDB.getLatestKey();
		
		//Act
		foundMaintenance = workOrderDB.findMaintenanceWorkOrderByID(latestKey);
		
		//Assert
		assertEquals(true, foundMaintenance.equals(maintenance));
	}
	
	@Test
	void findServiceWorkOrderByIDTest() {
		//Arrange
		workOrderDB.addServiceWorkOrder(service);
		Service foundService = null;
		int latestKey = workOrderDB.getLatestKey();
		
		//Act
		foundService = workOrderDB.findServiceWorkOrderByID(latestKey);
		
		//Assert
		assertEquals(true, foundService.equals(service));
	}
	
	@Test
	void findRepairWorkOrderByIDTest() {
		//TODO: this test will fail as long as the database workorder_price holde decimal number with 0 decimal points
		//Arrange
		workOrderDB.addRepairWorkOrder(repair);
		Repair foundRepair = null;
		int latestKey = workOrderDB.getLatestKey();
		
		//Act
		foundRepair = workOrderDB.findRepairWorkOrderByID(latestKey);
		
		//Assert
		assertEquals(true, foundRepair.equals(repair));
	}
	
	@Test
	void getWorkordersByIdTest() {
		//Arrange
		int[] ids = new int[3];
		List<Workorder> workorders = new ArrayList<>();
		
		workOrderDB.addMaintenanceWorkOrder(maintenance);
		ids[0] = workOrderDB.getLatestKey();
		
		workOrderDB.addRepairWorkOrder(repair);
		ids[1] = workOrderDB.getLatestKey();
		
		workOrderDB.addServiceWorkOrder(service);
		ids[2] = workOrderDB.getLatestKey();
		
		//Act
		workorders = workOrderDB.getWorkordersById(ids);
		
		//Assert
		assertEquals(maintenance.equals(workorders.get(0)), true);
		assertEquals(repair.equals(workorders.get(1)), true);
		assertEquals(service.equals(workorders.get(2)), true);
	}
	
	@Test
	void getAllMaintenanceWorkOrdersTest() {
		//TODO: Write test case
		//Arrange
				
		//Act
		
		//Assert
		
		//Clean up
	}
	
	@Test
	void getAllServiceWorkOrdersTest() {
		//TODO: Write test case
		//Arrange
		
		//Act
		
		//Assert
		
		//Clean up
	}
	
	@Test
	void getAllRepairWorkOrdersTest() {
		//TODO: Write test case
		//Arrange
		
		//Act
		
		//Assert
		
		//Clean up
	}
	
	@Test
	void getAllUnfinishedWorkOrdersTest() {
		//TODO: Write test case
		//Arrange
		
		//Act
		
		//Assert
		
		//Clean up
	}
	
	@Test
	void deleteWorkOrderByIDTest() {
		//TODO: Write test case
		//Arrange
		
		//Act
		
		//Assert
		
		//Clean up
	}
	
}