package dalStubs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dal.interfaces.ReferenceDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
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

public class StubWorkOrderDB implements WorkOrderDBIF{
	
	private static String title = "Test opgave";
	private static Calendar startDate = Calendar.getInstance();
	private static Calendar endDate = Calendar.getInstance();
	private static short priority = 3;
	private static String description = "Test beskrivelse";
	private static boolean finished = false;
	
	private static double price = 100.43;
	
	private static boolean repeated = true;
	private static int intervalDayCount = 7;
	
	
	private static List<SparepartUsed> sparepartsUsed = new ArrayList<>();
	private static Asset asset;
	private static List<Measurement> measurements = new ArrayList<>();
	
	private static Maintenance maintenance;
	private static Repair repair;
	private static Reference reference;
	
	//Employees
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
	
	private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
	
	public StubWorkOrderDB() {
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		maintenance.setEmployee(new Employee(employeeID, cpr, startDateEmp, position, name, phone, email, address));
		repair.setEmployee(new Employee(employeeID2, cpr2, startDateEmp2, position2, name2, phone2, email2, address2));
	}
	

	@Override
	public boolean addMaintenanceWorkOrder(Maintenance workOrder) {
		//Should Return true as adding the Workorder as a success
		return true;
	}

	@Override
	public boolean addServiceWorkOrder(Service workOrder) {
		//Should Return true as adding the Workorder as a success
		return true;
	}

	@Override
	public boolean addRepairWorkOrder(Repair workOrder) {
		//Should Return true as adding the Workorder as a success
		return true;
	}

	@Override
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID) {
		return (workOrderID == 1) ? new Maintenance() : null;
	}

	@Override
	public Service findServiceWorkOrderByID(int workOrderID) {
		return (workOrderID == 1) ? new Service() : null;
	}

	@Override
	public Repair findRepairWorkOrderByID(int workOrderID) {
		return (workOrderID == 1) ? new Repair() : null;
	}

	@Override
	public List<Maintenance> getAllMaintenanceWorkOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Service> getAllServiceWorkOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Repair> getAllRepairWorkOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workorder> getAllUnfinishedWorkOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Workorder> getWorkordersById(int[] workorderIds) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//Can only return two types of workorder atm
	@Override
	public Workorder getWorkorderById(int workorderId) throws SQLException {
		if(workorderId == 1) {
			return maintenance;
		}
		else if(workorderId == 2) {
			return repair;
		}
		else {
			return null;
		}
	}

	@Override
	public boolean updateWorkorder(Workorder workorder) {
		//nothing should happen
		return true;
	}

	@Override
	public boolean deleteWorkOrderByID(int workOrderID) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getLatestKey() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Workorder> searchWorkOrderDataBase(String name, List<Short> priority, String location) {
		// TODO Auto-generated method stub
		return null;
	}

}
