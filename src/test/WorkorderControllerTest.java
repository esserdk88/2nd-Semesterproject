package test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.*;
import dal.interfaces.AssetDBIF;
import dal.interfaces.ReferenceDBIF;
import dal.interfaces.WorkOrderDBIF;
import dalStubs.StubAssetDB;
import dalStubs.StubWorkOrderDB;
import dao.Database;
import dao.DatabaseConnection;
import model.*;

class WorkorderControllerTest {
	
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
		
		//Employee Fields 1
		private static int employeeID = 1000000;
		private static String cpr = "00000001";
		private static Calendar startDateEmp = Calendar.getInstance();
		private static String position = "Testpos";
		private static String name = "TestName";
		private static String phone = "00000001";
		private static String email = "test@test.com";
		//pls dont fuck up
		private static Address address = null;
		
		//Employee Fields 1
		private static int employeeID2 = 2000000;
		private static String cpr2 = "00000002";
		private static Calendar startDateEmp2 = Calendar.getInstance();
		private static String position2 = "Testpos2";
		private static String name2 = "TestNameTheThird";
		private static String phone2 = "00000002";
		private static String email2 = "test@test.com";
		//pls dont fuck up
		private static Address address2 = null;
		
//		public Employee(int employeeID, String cprNumber, Calendar startDate, String position,
//				String name, String phone, String email, Address address) {
//			super(name, phone, email, address);
//			this.employeeID = employeeID;
//			this.cprNumber = cprNumber;
//			this.startDate = startDate;
//			this.position = position;
//		}
		
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
		private static WorkOrderDBIF workOrderDB;
		private static AssetDBIF assetDB;
		private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
		
		private static WorkOrderController workorderController;
		private static MaintenanceController maintenanceController;
		private static RepairController repairController;
		private static ServiceController serviceController;
		
		//#############################################
		//true = use stubs
		//false = use database
		private static boolean useStubs = true;
		//#############################################

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseConnection.setTestingEnvironment();
		if(useStubs) {
			Database.getInstance().setWorkOrderDataBase(new StubWorkOrderDB());
			Database.getInstance().setAssetDataBase(new StubAssetDB());
		}
		workOrderDB = Database.getInstance().getWorkOrderDataBase();
		assetDB = Database.getInstance().getAssetDataBase();
		workorderController = new WorkOrderController();
		maintenanceController = new MaintenanceController();
		repairController = new RepairController();
		serviceController = new ServiceController();
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		service = new Service(reference, 0, title, "Service", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		maintenance.setEmployee(new Employee(employeeID, cpr, startDateEmp, position, name, phone, email, address));
		repair.setEmployee(new Employee(employeeID2, cpr2, startDateEmp2, position2, name2, phone2, email2, address2));
	}
	
	@AfterAll
	static void tearDown() throws SQLException {
		//good practice
		DatabaseConnection.getInstance().rollbackTransaction();
	}

	@Test
	void switchEmployeeWorkordersTest() {
		//Arrange
		workOrderDB.addMaintenanceWorkOrder(maintenance);
		workOrderDB.addRepairWorkOrder(repair);
		
		repair.setWorkOrderID(2);
		maintenance.setWorkOrderID(1);
		
		Employee emp1 = maintenance.getEmployee();
		Employee emp2 = service.getEmployee();
		
		boolean success1 = false;
		boolean success2 = false;
		//made to make sure that if both switches fails, that the assert equals does not return true. because they're both false thus the same.
		boolean switchedEmployees = false;
		
		//Act
		//try catch segment only there because stub uses DBInterface
		try {
			workorderController.switchEmployeeWorkorders(repair, maintenance, false);
			switchedEmployees = true;
		} catch (Exception e) {
			//Some error has occured 
			fail();
			e.printStackTrace();
		}
		
		//Assert
		try {
			Workorder tempWorkorder1 = workOrderDB.getWorkorderById(maintenance.getWorkOrderID());
			Workorder tempWorkorder2 = workOrderDB.getWorkorderById(repair.getWorkOrderID());
			success1 = tempWorkorder1.getEmployee().equals(emp2);
			success2 = tempWorkorder2.getEmployee().equals(emp1);
			if(switchedEmployees) {
				assertEquals(success1, success2);
			}
			else {
				fail();
			}
					
		} catch (SQLException e) {
			//in case of error when using actual DB
			fail();
			e.printStackTrace();
		}
	}
	
	@Test
	void addNewMaintenanceWorkOrderTest() throws SQLException {
		//Arrange
		boolean success = false;
		
		//Act
		success = maintenanceController.createWorkOrder(1, title, startDate, priority, description, intervalDayCount, repeated);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addNewMaintenanceWorkOrderExpectExceptionTest() throws SQLException {
		//Arrange
		
		//Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
	        maintenanceController.createWorkOrder(2, title, startDate, priority, description, intervalDayCount, repeated);
	    });
	}
	
	@Test
	void addNewRepairWorkOrderTest() throws SQLException {
		//Arrange
		boolean success = false;
				
		//Act
		success = repairController.createWorkOrder(1, title, startDate, priority, description, 1);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addNewRepairWorkOrderExpectExceptionTest() throws SQLException {
		//Arrange
		
		//Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			repairController.createWorkOrder(2, title, startDate, priority, description, 1);
	    });
	}
	
	@Test
	void addNewServiceWorkOrderTest() throws SQLException {
		//Arrange
		boolean success = false;
		
		//Act
		success = serviceController.createWorkOrder(1, title, startDate, priority, description, 1);
		
		//Assert
		assertEquals(true, success);
	}
	
	@Test
	void addNewServiceWorkOrderExpectExceptionTest() throws SQLException {
		//Arrange
		
		//Act & Assert
		assertThrows(IllegalArgumentException.class, () -> {
			serviceController.createWorkOrder(2, title, startDate, priority, description, 1);
	    });
		//Arrange
	}
	@Test
	void findMaintenanceWorkOrderByIDTest() {
		//Arrange
		int workOrderID = 1;
		Maintenance maintenanceTest;
		
		//Act
		maintenanceTest = maintenanceController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, maintenanceTest != null);
	}
	
	@Test
	void findMaintenanceWorkOrderByIDExpectExceptionTest() {
		//Arrange
		int workOrderID = 2;
		Maintenance maintenanceTest;
		
		//Act
		maintenanceTest = maintenanceController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, maintenanceTest == null);
	}
	@Test
	void findServiceWorkOrderByIDTest() {
		//Arrange
		int workOrderID = 1;
		Service serviceTest;
		
		//Act
		serviceTest = serviceController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, serviceTest != null);
	}
	
	@Test
	void findServiceWorkOrderByIDExpectExceptionTest() {
		//Arrange
		int workOrderID = 2;
		Service serviceTest;
		
		//Act
		serviceTest = serviceController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, serviceTest == null);
	}
	@Test
	void findRepairWorkOrderByIDTest() {
		//Arrange
		int workOrderID = 1;
		Repair repairTest;
		
		//Act
		repairTest = repairController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, repairTest != null);
	}
	
	@Test
	void findRepairWorkOrderByIDExpectExceptionTest() {
		//Arrange
		int workOrderID = 2;
		Repair repairTest;
		
		//Act
		repairTest = repairController.findWorkOrderByID(workOrderID);
		
		//Assert
		assertEquals(true, repairTest == null);
	}
	
	@Test
	void assignEmployeeToWorkorderTest() {
		//Arrange
		Workorder workorder = maintenance;
		Employee employee = new Employee(employeeID2, cpr2, startDateEmp2, position2, name2, phone2, email2, address2);
		int workorderId = maintenance.getWorkOrderID();
		Workorder workorderConfirm = null;
		
		//Act
		workorderController.assignEmployeeToWorkOrder(employee, workorder);
		
		
		//Assert
		try {
			workorderConfirm = workOrderDB.getWorkorderById(workorderId);
		} catch (SQLException e) {
			System.out.println("WorkorderControllerTest - assignEmployeeToWorkorder - Some conncetion thingy failed");
			e.printStackTrace();
		}
		
		if(workorderConfirm != null) {
			assertEquals(workorderConfirm.getEmployee().equals(employee), true);
		}
		else {
			fail();
		}
	}

}
