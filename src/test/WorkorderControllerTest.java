package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.WorkOrderController;
import controller.interfaces.WorkOrderControllerIF;
import dal.interfaces.AssetDBIF;
import dal.interfaces.ReferenceDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.Address;
import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.Reference;
import model.Repair;
import model.Service;
import model.SparepartUsed;
import model.Workorder;

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
		private WorkOrderDBIF workOrderDB = Database.getInstance().getWorkOrderDataBase();
		private static AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
		private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
		private static WorkOrderControllerIF workorderController = new WorkOrderController();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseConnection.getInstance().startTransaction();
		asset = assetDB.findAssetByID(1);
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		service = new Service(reference, 0, title, "Service", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		maintenance.setEmployee(new Employee(employeeID, cpr, startDateEmp, position, name, phone, email, address));
		repair.setEmployee(new Employee(employeeID2, cpr2, startDateEmp2, position2, name2, phone2, email2, address2));
	}
	
	@AfterAll
	static void tearDown() throws SQLException {
		//DatabaseConnection.getInstance().commitTransaction();
		DatabaseConnection.getInstance().rollbackTransaction();
		System.out.println("Rollback");
	}

	@Test
	void switchEmployeeWorkordersTest() {
		//Arrange
		System.out.println(workOrderDB.addMaintenanceWorkOrder(maintenance));
		System.out.println(workOrderDB.addRepairWorkOrder(repair));
		
		Employee emp1 = maintenance.getEmployee();
		Employee emp2 = service.getEmployee();
		
		boolean success1 = false;
		boolean success2 = false;
		//made to make sure that if both switches fails, that the assert equals does not return true. because they're both false thus the same.
		boolean switchedEmployees = false;
		
		//Act
		try {
			workorderController.switchEmployeeWorkorders(repair, maintenance);
			switchedEmployees = true;
		} catch (Exception e) {
			//Something stupid happened
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
			//something stupid happened
			fail();
			e.printStackTrace();
		}
	}

}
