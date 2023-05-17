package dalStubs;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dal.interfaces.ReferenceDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.Database;
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
	
	private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
	
	public StubWorkOrderDB() {
		reference = referenceDB.findReferenceByID(11111111);
		maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
	}
	

	@Override
	public boolean addMaintenanceWorkOrder(Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addServiceWorkOrder(Service workOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addRepairWorkOrder(Repair workOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Service findServiceWorkOrderByID(int workOrderID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Repair findRepairWorkOrderByID(int workOrderID) {
		// TODO Auto-generated method stub
		return null;
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

}
