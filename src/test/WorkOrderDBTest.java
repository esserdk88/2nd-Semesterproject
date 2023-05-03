package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.AssetDB;
import dal.AssetDBIF;
import dal.EmployeeDB;
import dal.EmployeeDBIF;
import dal.WorkOrderDB;
import dal.WorkOrderDBIF;
import model.Address;
import model.Asset;
import model.Employee;
import model.Location;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.SparepartUsed;
import model.Workorder;

class WorkOrderDBTest {
	
	
//		public Maintenance findMaintenanceWorkOrderByID(int workOrderID);
//		public Service findServiceWorkOrderByID(int workOrderID);
//		public Repair findRepairWorkOrderByID(int workOrderID);
//		
//		public List<Maintenance> getAllMaintenanceWorkOrders();
//		public List<Service> getAllServiceWorkOrders();
//		public List<Repair> getAllRepairWorkOrders();
//		public List<Workorder> getAllUnfinishedWorkOrders();
//		
//		public boolean deleteWorkOrderByID(int workOrderID);
//		
//		public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder);
	
	//Asset fields
//	private Address assetAddress = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
//	private Location location = new Location(69, "Hoved bygningen", "2.sal", "HB242", assetAddress);
//	Calendar aquisitionDate = Calendar.getInstance();
//	private String assetName = "";
//	private String assetDescription ="";
//	private String assetStatus = "";
//	private String assetManufacturer = "";
	
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
	private int cvr = 12345678;
	private String contact = "Mikkel Hansen";
	private String name = "Rasmus"; 
	private String phone = "28697610";
	private String email = "rasmus.lyngberg@gmail.com";
	private Address address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
	private Reference reference = new Reference(cvr, contact, name, phone, email, address);
	
	//Repair fields
	private double price = 100.43;
	
	private Maintenance maintenance;
	private Service service;
	private Repair repair;
	
	//Instances
	private WorkOrderDBIF workOrderDB = new WorkOrderDB();
	private AssetDBIF assetDB = new AssetDB();
	
	@BeforeEach
	void setUp() throws Exception {
		asset = assetDB.findAssetByID(1);
		maintenance = new Maintenance(repeated, intervalDayCount, workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		service = new Service(reference, workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
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
		
		//Clean up
		workOrderDB.deleteWorkOrderTestData(testID);
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
		
		//Clean up
		workOrderDB.deleteWorkOrderTestData(testID);
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
		
		//Clean up
		workOrderDB.deleteWorkOrderTestData(testID);
	}
	
	@Test
	void findMaintenanceWorkOrderByIDTest() {
		//TODO Finish this test
		//Arrange
		Random random = new Random();
		short testID = (short) random.nextInt(50,100);
		maintenance.setPriority(testID);
		workOrderDB.addMaintenanceWorkOrder(maintenance);
		Maintenance foundMaintenance = null;
		int latestKey = workOrderDB.getLatestKey();
		
		//Act
		foundMaintenance = workOrderDB.findMaintenanceWorkOrderByID(latestKey);
		
		//Assert
		assertEquals(true, foundMaintenance.equals(maintenance));
		
		//Clean up
		workOrderDB.deleteWorkOrderTestData(testID);
		
	}
	
}