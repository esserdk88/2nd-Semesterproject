package dal;

import java.security.Provider.Service;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Address;
import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.Repair;
import model.SparepartUsed;
import model.Workorder;

public class WorkOrderDB implements WorkOrderDBIF {
	
	public static final String FIELDS_COMMON = "workorder_id_PK, workorder_title, workorder_type, workorder_startdate, workorder_enddate, "
			+ "workorder_priority, workorder_description, workorder_finished, workorder_asset_id_FK, workorder_employee_id_FK";
	public static final String FIELDS_MAINTENANCE = FIELDS_COMMON + ", workorder_interval, workorder_repeatable";
	public static final String FIELDS_SERVICE = FIELDS_COMMON + ", workorder_reference_id_FK";
	public static final String FIELDS_REPAIR = FIELDS_COMMON + ", workorder_reference_id_FK, workorder_price";
	
	public static final String SELECT_MAINTENANCE_BY_ID = "SELECT " + FIELDS_MAINTENANCE + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Maintenance'";
	public static final String SELECT_SERVICE_BY_ID = "SELECT " + FIELDS_SERVICE + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Service'";
	public static final String SELECT_REPAIR_BY_ID = "SELECT " + FIELDS_REPAIR + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Repair'";
	
	public static final String SELECT_ALL_MAINTENANCE = "SELECT " + FIELDS_MAINTENANCE + " FROM Workorder WHERE workorder_type = 'Maintenance'";
	public static final String SELECT_ALL_SERVICE = "SELECT " + FIELDS_SERVICE + " FROM Workorder WHERE workorder_type = 'Service'";
	public static final String SELECT_ALL_REPAIR = "SELECT " + FIELDS_REPAIR + " FROM Workorder WHERE workorder_type = 'Repair'";

	private EmployeeDBIF employeeDB = new EmployeeDB();
	private AssetDBIF assetDB = new AssetDB();
	private SparepartUsedDBIF sparepartUsedDB = new SparepartUsedDB();
	private MeasurementDBIF measurementDB = new MeasurementDB();
	
	@Override
	public boolean addMaintenanceWorkOrder(Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID) {
		
		Maintenance maintenance = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindMaintenance = con.prepareStatement(SELECT_MAINTENANCE_BY_ID)) {
			
			//prepare statement
			psFindMaintenance.setInt(1, workOrderID);
			
			//execute statement
			ResultSet rs = psFindMaintenance.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Maintenance object from result set
				maintenance = buildMaintenanceObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING MAINTENANCE WORKORDER:" + e.getMessage());
		}
		
		return maintenance;
	}
	

	@Override
	public List<Maintenance> getAllMaintenanceWorkOrders() {
		List<Maintenance> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_MAINTENANCE)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindWorkorder.executeQuery();
			
			if (rs != null) {
				//build Employee object from result set
				while(rs.next()) {
					list.add(buildMaintenanceObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING MAINTENANCE WORKORDER:" + e.getMessage());
		}
		return list;
	}
	
	
	
	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
	}
	

	@Override
	public Service findServiceWorkOrderByID(int workOrderID) {
		Service service = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindService = con.prepareStatement(SELECT_SERVICE_BY_ID)) {
			
			//prepare statement
			psFindService.setInt(1, workOrderID);
			
			//execute statement
			ResultSet rs = psFindService.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Service object from result set
				service = buildServiceObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING SERVICE:" + e.getMessage());
		
		}
		
		return service ;
	}

	@Override
	public Repair findRepairWorkOrderByID(int workOrderID) {
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
	
	//Converters
		private Calendar convertSqlDateToCalendar(Date sqlDate) { 
			Calendar calendar = Calendar.getInstance(); 
			if(sqlDate != null) {
				calendar.setTime(sqlDate); 
			} else {
				calendar = null;
			}
			
			return calendar; 
		}
		
		//Builders
		private Maintenance buildMaintenanceObject(ResultSet rs) throws SQLException {
			// create a new Maintenance object
			Maintenance result = new Maintenance();

			// set the properties of the Maintenance object based on the values in the ResultSet
			result.setWorkOrderID(rs.getInt("workorder_id_PK"));
			result.setTitle(rs.getString("workorder_title"));
			result.setType(rs.getString("workorder_type"));
			result.setDescription(rs.getString("workorder_description"));
			result.setPriority(rs.getShort("workorder_priority"));
			result.setFinished(rs.getBoolean("workorder_finished"));
			
			//Maintenance-specific
			result.setRepeated(rs.getBoolean("workorder_repeatable"));
			result.setIntervalDayCount(rs.getInt("workorder_interval"));
			
			//Dates
			result.setStartDate(convertSqlDateToCalendar(rs.getDate("workorder_startdate")));
			result.setEndDate(convertSqlDateToCalendar(rs.getDate("workorder_enddate")));
			
			//Objects
			result.setEmployee(employeeDB.findEmployeeByID(rs.getInt("workorder_employee_id_FK")));
			result.setAsset(assetDB.findAssetByID(rs.getInt("workorder_asset_id_FK")));
			result.setSparepartsUsed(sparepartUsedDB.findSparepartListByWorkorderID(rs.getInt("workorder_id_PK")));
			result.setMeasurements(measurementDB.findMeasurementsByWorkOrderID(rs.getInt("workorder_id_PK")));

			// return the Maintenance object
					
			return result;
		}
		
		//TODO
		private Service buildServiceObject(ResultSet rs) {
				
				
			return null;
		}
		
}