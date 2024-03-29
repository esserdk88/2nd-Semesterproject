package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import controller.WorkOrderController;
import dal.AssetDB;
import dal.WorkOrderDB;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.Address;
import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Service;
import model.Workorder;

class WorkOrderDBTest {
	
	private static Maintenance maintenance;
	private static Service service;
	private static Repair repair;
	
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
	
	//Instances
	private static WorkOrderDBIF workOrderDB;
	private static WorkOrderController workorderController;
	
	@BeforeAll
	static void startUp() throws SQLException {
		DatabaseConnection.setTestingEnvironment();
		Database.getInstance().setWorkOrderDataBase(new WorkOrderDB());
		Database.getInstance().setAssetDataBase(new AssetDB());
		workOrderDB = Database.getInstance().getWorkOrderDataBase();
		workorderController = new WorkOrderController();
		maintenance = TestingUtilities.getMaintenanceWorkOrder();
		service = TestingUtilities.getServiceWorkOrder();
		repair = TestingUtilities.getRepairWorkOrder();
	}
	
	@AfterAll
	static void tearDown() throws SQLException {
		DatabaseConnection.getInstance().rollbackTransaction();
	}
	
	@BeforeEach
	void beforeEach() throws SQLException {
		DatabaseConnection.getInstance().startTransaction();
	}
	
	@AfterEach
	void afterEach() throws SQLException {
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
		//Arrange
		int maintenanceWorkOrderInDB = 2; //Testing in test database, contains 2 Maintenance Workorders
		
		//Act
		int size = workOrderDB.getAllMaintenanceWorkOrders().size();
		
		//Assert
		assertEquals(maintenanceWorkOrderInDB, size);
	}
	
	@Test
	void getAllServiceWorkOrdersTest() {
		//Arrange
		int serviceWorkOrderInDB = 2; //Testing in test database, contains 2 Service Workorders
		
		//Act
		int size = workOrderDB.getAllServiceWorkOrders().size();
		//Assert
		assertEquals(serviceWorkOrderInDB, size);
	}
	
	@Test
	void getAllRepairWorkOrdersTest() {
		//Arrange
		int repairWorkOrderInDB = 1; //Testing in test database, contains 1 Repair Workorders
		
		//Act
		int size = workOrderDB.getAllRepairWorkOrders().size();
		//Assert
		assertEquals(repairWorkOrderInDB, size);
	}
	
	@Test
	void getAllUnfinishedWorkOrdersTest() {
		//Arrange
		int unfinishedWorkOrderInDB = 2; //Testing in test database, contains 2 UnfinishedWorkorders
		
		//Act
		int size = workOrderDB.getAllUnfinishedWorkOrders().size();
		//Assert
		assertEquals(unfinishedWorkOrderInDB, size);
	}
	
	@Test
	void deleteWorkOrderByIDTest() throws SQLException {
		//Arrange
		int workOrderID = 5; //Testing on workorder 5 in test database, WorkOrder 5 contains no Spareparts used, or measurements
		boolean deleteSuccess = false;
		Workorder stillExistsCheck = new Maintenance();
		
		//Act
		deleteSuccess = workOrderDB.deleteWorkOrderByID(workOrderID);
		stillExistsCheck = workOrderDB.getWorkorderById(workOrderID);
		
		//Assert
		assertEquals(true, deleteSuccess);
		assertEquals(null, stillExistsCheck);
	}
	
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
			//Fail the test
			fail();
		}
	}
	
}