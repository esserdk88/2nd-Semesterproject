package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.AssetDBIF;
import dal.Database;
import dal.DatabaseConnection;
import dal.ReferenceDBIF;
import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.SparepartUsed;
import model.Workorder;

class WorkOrderDBTest {
	
	//Common fields for use 
	private int workOrderID = 42;
	private String title = "Test opgave";
	private String type = "Maintenance";
	private Calendar startDate = Calendar.getInstance();
	private Calendar endDate = Calendar.getInstance();
	private short priority = 6;
	private String description = "Test beskrivelse";
	private boolean finished = false;
	
	
	private List<SparepartUsed> sparepartsUsed = new ArrayList<>();
	private Asset asset;
	private Employee employee;
	private List<Measurement> measurements = new ArrayList<>();
	
	//Maintenance fields
	private boolean repeated = true;
	private int intervalDayCount = 7;
	
	//Service fields
	private Reference reference;
	
	//Repair fields
	private double price = 100.43;
	
	private Maintenance maintenance;
	private Service service;
	private Repair repair;
	
	//Instances
	private WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
	private AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
	private ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
	
	//Other
	Random random = new Random();
	short testID;
	
	@BeforeEach
	void setUp() throws Exception {
		testID = (short) random.nextInt(50,100);
		asset = assetDB.findAssetByID(1);
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, workOrderID, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		service = new Service(reference, workOrderID, title, "Service", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, workOrderID, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
	}
	
	@BeforeAll
	static void startUp() throws SQLException {
		DatabaseConnection.getInstance().startTransaction();
	}
	@AfterAll
	static void tearDown() throws SQLException {
		DatabaseConnection.getInstance().rollbackTransaction();
	}
			
	@Test
	void addMaintenanceWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		Random random = new Random();
		short testID = (short) random.nextInt(50,100);
		maintenance.setPriority(testID);
		
		//Act
		success = workOrderDB.addMaintenanceWorkOrder(maintenance);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addServiceWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		Random random = new Random();
		short testID = (short) random.nextInt(50,100);
		service.setPriority(testID);
		
		//Act
		success = workOrderDB.addServiceWorkOrder(service);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addRepairWorkOrderTest() {
		
		//Arrange
		boolean success = false;
		Random random = new Random();
		short testID = (short) random.nextInt(50,100);
		repair.setPriority(testID);
		
		//Act
		success = workOrderDB.addRepairWorkOrder(repair);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void findMaintenanceWorkOrderByIDTest() {
		//TODO Finish this test
		//Arrange
		maintenance.setPriority(testID);
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
		service.setPriority(testID);
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
		repair.setPriority(testID);
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
		List<Short> priorities = new ArrayList<>();
		WorkOrderDB wdb = new WorkOrderDB();
		
		//Very ugly
		maintenance.setPriority((short)60);
		wdb.addMaintenanceWorkOrder(maintenance);
		priorities.add((short)60);
		ids[0] = wdb.getLatestKey();
		System.out.println(wdb.getLatestKey());
		
		repair.setPriority((short)61);
		priorities.add((short)61);
		wdb.addRepairWorkOrder(repair);
		ids[1] = wdb.getLatestKey();
		System.out.println(wdb.getLatestKey());
		
		service.setPriority((short) 62);
		priorities.add((short)62);
		wdb.addServiceWorkOrder(service);
		ids[2] = wdb.getLatestKey();
		System.out.println(wdb.getLatestKey());
		
		//Act
		workorders = wdb.getWorkordersById(ids);
		
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