package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Service;
import model.Workorder;

public class WorkOrderDB implements WorkOrderDBIF {
	
	public static final String FIELDS_COMMON_WITH_ID = "workorder_id_PK, workorder_title, workorder_type, workorder_startdate, workorder_enddate, "
			+ "workorder_priority, workorder_description, workorder_finished, workorder_asset_id_FK, workorder_employee_id_FK";
	
	//Without ID and employee.
	public static final String FIELDS_INSERT_COMMON = "workorder_title, workorder_type, workorder_startdate, workorder_enddate,"
						+ "workorder_priority, workorder_description, workorder_finished, workorder_asset_id_FK";
	
	
	public static final String FIELDS_MAINTENANCE_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_interval, workorder_repeatable";
	public static final String FIELDS_SERVICE_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_reference_id_FK";
	public static final String FIELDS_REPAIR_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_reference_id_FK, workorder_price";
	
	public static final String FIELDS_INSERT_MAINTENANCE = FIELDS_INSERT_COMMON + ", workorder_interval, workorder_repeatable";
	public static final String FIELDS_INSERT_SERVICE = FIELDS_INSERT_COMMON + ", workorder_reference_id_FK";
	public static final String FIELDS_INSERT_REPAIR = FIELDS_INSERT_COMMON + ", workorder_reference_id_FK, workorder_price";
	
//	public static final String SELECT_MAINTENANCE_BY_ID = "SELECT " + FIELDS_MAINTENANCE_WITH_ID + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Maintenance'";
//	public static final String SELECT_SERVICE_BY_ID = "SELECT " + FIELDS_SERVICE_WITH_ID + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Service'";
//	public static final String SELECT_REPAIR_BY_ID = "SELECT " + FIELDS_REPAIR_WITH_ID + " FROM Workorder WHERE workorder_id_PK = ? AND workorder_type = 'Repair'";
	public static final String SELECT_MAINTENANCE_BY_ID = "SELECT * FROM MaintenanceView WHERE workorder_id_PK = ?";
	public static final String SELECT_SERVICE_BY_ID = "SELECT * FROM ServiceView WHERE workorder_id_PK = ?";
	public static final String SELECT_REPAIR_BY_ID = "SELECT * FROM RepairView WHERE workorder_id_PK = ?";
	
//	public static final String SELECT_UNFINISHED_WORKORDERS = "SELECT * from Workorder where workorder_finished = 0 and workorder_startdate <= GETDATE()";
	public static final String SELECT_UNFINISHED_WORKORDERS = "SELECT * from WorkOrdersView where workorder_finished = 0 and workorder_startdate <= GETDATE()";
//	public static final String SELECT_ALL_MAINTENANCE = "SELECT " + FIELDS_MAINTENANCE_WITH_ID + " FROM Workorder WHERE workorder_type = 'Maintenance'";
//	public static final String SELECT_ALL_SERVICE = "SELECT " + FIELDS_SERVICE_WITH_ID + " FROM Workorder WHERE workorder_type = 'Service'";
//	public static final String SELECT_ALL_REPAIR = "SELECT " + FIELDS_REPAIR_WITH_ID + " FROM Workorder WHERE workorder_type = 'Repair'";
	
	public static final String SELECT_ALL_MAINTENANCE = "SELECT * FROM MaintenanceView";
	public static final String SELECT_ALL_SERVICE = "SELECT * FROM ServiceView";
	public static final String SELECT_ALL_REPAIR = "SELECT * FROM RepairView";
	
	public static final String INSERT_MAINTENANCE = "INSERT INTO Workorder (" + FIELDS_INSERT_MAINTENANCE + ") VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	public static final String INSERT_SERVICE = "INSERT INTO Workorder (" + FIELDS_INSERT_SERVICE + ") VALUES (?,?,?,?,?,?,?,?,?)" ;
	public static final String INSERT_REPAIR = "INSERT INTO Workorder (" + FIELDS_INSERT_REPAIR + ") VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	
	public static final String DELETE_WORK_ORDER_BY_ID = "DELETE FROM Workorder where workorder_id_PK = ?";
	public static final String DELETE_WORK_ORDER_TEST_DATA = "DELETE FROM Workorder where workorder_priority = ?";
	
	public static final String SELECT_LATEST_KEY = "SELECT MAX (workorder_id_PK) from Workorder";
	

	private EmployeeDBIF employeeDB = Database.getInstance().getEmployeeDataBase();
	private AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
	private SparepartUsedDBIF sparepartUsedDB = Database.getInstance().getSparepartUsedDataBase();
	private MeasurementDBIF measurementDB = Database.getInstance().getMeasurementDataBase();
	private ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
	
	@Override
	public boolean addMaintenanceWorkOrder(Maintenance workOrder) {
		
		boolean success = false;
		
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psAddMaintenance = con.prepareStatement(INSERT_MAINTENANCE)) {
			
			//prepare statement
			psAddMaintenance.setString(1, workOrder.getTitle());
			psAddMaintenance.setString(2, "Maintenance");
			psAddMaintenance.setDate(3, convertCalendarToSqlDate(workOrder.getStartDate()));
			psAddMaintenance.setDate(4, convertCalendarToSqlDate(workOrder.getEndDate()));
			psAddMaintenance.setShort(5, workOrder.getPriority());
			psAddMaintenance.setString(6, workOrder.getDescription());
			psAddMaintenance.setBoolean(7, workOrder.isFinished());
			psAddMaintenance.setInt(8, workOrder.getAsset().getAssetID());
			psAddMaintenance.setInt(9, workOrder.getIntervalDayCount());
			psAddMaintenance.setBoolean(10, workOrder.isRepeated());
					
			//execute statement
			psAddMaintenance.executeUpdate();
			
			success = true;
			
		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING MAINTENANCE WORKORDER:" + e.getMessage());
		}
		
		return success;
	}
	
	@Override
	public boolean addServiceWorkOrder (Service workOrder) {
		
		boolean success = false;
		
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psAddService = con.prepareStatement(INSERT_SERVICE)) {
			
			//prepare statement
			psAddService.setString(1, workOrder.getTitle());
			psAddService.setString(2, "Service");
			psAddService.setDate(3, convertCalendarToSqlDate(workOrder.getStartDate()));
			psAddService.setDate(4, convertCalendarToSqlDate(workOrder.getEndDate()));
			psAddService.setShort(5, workOrder.getPriority());
			psAddService.setString(6, workOrder.getDescription());
			psAddService.setBoolean(7, workOrder.isFinished());
			psAddService.setInt(8, workOrder.getAsset().getAssetID());
			psAddService.setInt(9, workOrder.getReference().getCvr());
					
			//execute statement
			psAddService.executeUpdate();
			
			success = true;
			
		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING SERVICE WORKORDER:" + e.getMessage());
		}
		
		return success;
	}
	
	@Override
	public boolean addRepairWorkOrder (Repair workOrder) {
		boolean success = false;
		
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psAddRepair = con.prepareStatement(INSERT_REPAIR)) {
			
			//prepare statement
			psAddRepair.setString(1, workOrder.getTitle());
			psAddRepair.setString(2, "Repair");
			psAddRepair.setDate(3, convertCalendarToSqlDate(workOrder.getStartDate()));
			psAddRepair.setDate(4, convertCalendarToSqlDate(workOrder.getEndDate()));
			psAddRepair.setShort(5, workOrder.getPriority());
			psAddRepair.setString(6, workOrder.getDescription());
			psAddRepair.setBoolean(7, workOrder.isFinished());
			psAddRepair.setInt(8, workOrder.getAsset().getAssetID());
			psAddRepair.setInt(9, workOrder.getReference().getCvr());
			psAddRepair.setDouble(10, workOrder.getPrice());
					
			//execute statement
			psAddRepair.executeUpdate();
			
			success = true;
			
		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING REPAIR WORKORDER:" + e.getMessage());
		}
		
		return success;
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
		return service;
	}

	@Override
	public Repair findRepairWorkOrderByID(int workOrderID) {
		Repair repair = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindRepair = con.prepareStatement(SELECT_REPAIR_BY_ID)) {
			
			//prepare statement
			psFindRepair.setInt(1, workOrderID);
			
			//execute statement
			ResultSet rs = psFindRepair.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Repair object from result set
				repair = buildRepairObject(rs);
			}
			
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING REPAIR:" + e.getMessage());
		
		}
		return repair;
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
				//build Maintenance object from result set
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
	public List<Service> getAllServiceWorkOrders() {
		List<Service> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_SERVICE)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindWorkorder.executeQuery();
			
			if (rs != null) {
				//build Service object from result set
				while(rs.next()) {
					list.add(buildServiceObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING SERVICE WORKORDER:" + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Repair> getAllRepairWorkOrders() {
		List<Repair> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_REPAIR)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindWorkorder.executeQuery();
			
			if (rs != null) {
				//build Repair object from result set
				while(rs.next()) {
					list.add(buildRepairObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING REPAIR WORKORDER:" + e.getMessage());
		}
		return list;
	}
	
	@Override
	public List<Workorder> getAllUnfinishedWorkOrders() {
		List<Workorder> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_UNFINISHED_WORKORDERS)) {
			
			//prepare statement
			// Left empty.
			
			//execute statement
			ResultSet rs = psFindWorkorder.executeQuery();
			
			if (rs != null) {
				//build WorkOrder object from result set
				while(rs.next()) {
					switch (rs.getString("workorder_type")) {
					case "Maintenance":  
						list.add(buildMaintenanceObject(rs));
						break;
					case "Service":  
						list.add(buildServiceObject(rs));
						break;	
					case "Repair":  
						list.add(buildRepairObject(rs));
						break;	
					default:
						//Left empty.
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING UNFINISHED WORKORDER:" + e.getMessage());
		}
		return list;
	}
	
	public boolean deleteWorkOrderByID(int workOrderID) {
		
		boolean success = false;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psDeleteWorkOrder = con.prepareStatement(DELETE_WORK_ORDER_BY_ID)) {
			
			//prepare statement
			psDeleteWorkOrder.setInt(1, workOrderID);
			
			//execute statement
			psDeleteWorkOrder.executeUpdate();
			psDeleteWorkOrder.getUpdateCount(); 
			if(psDeleteWorkOrder.getUpdateCount() > 0) {
				success = true;
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR FROM DELETING WORKORDER:" + e.getMessage());
		}
		
		return success;
	}
	
	@Override
	public boolean deleteWorkOrderTestData(short ID) {
		boolean success = false;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psDeleteWorkOrder = con.prepareStatement(DELETE_WORK_ORDER_TEST_DATA)) {
			
			//prepare statement
			psDeleteWorkOrder.setInt(1, ID);
			
			//execute statement
			psDeleteWorkOrder.executeUpdate();
			psDeleteWorkOrder.getUpdateCount(); 
			if(psDeleteWorkOrder.getUpdateCount() > 0) {
				success = true;
			}
			
		} catch (SQLException e) {
			System.out.println("ERROR FROM DELETING TEST DATA:" + e.getMessage());
		}
		
		return success;
	}
	
	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
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
	
	private Date convertCalendarToSqlDate(Calendar cal) {
        long timeInMillis = cal.getTimeInMillis();
        return new Date(timeInMillis);
    }
	public Maintenance buildMaintenanceObjectFromResultset(ResultSet rs) throws SQLException {
		if(rs.getInt("workorder_id_PK") == 0) {
			return null;
		}else return buildMaintenanceObject(rs);
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
//		result.setEmployee(employeeDB.findEmployeeByID(rs.getInt("workorder_employee_id_FK")));
//		result.setAsset(assetDB.findAssetByID(rs.getInt("workorder_asset_id_FK")));
//		result.setSparepartsUsed(sparepartUsedDB.findSparepartListByWorkorderID(rs.getInt("workorder_id_PK")));
//		result.setMeasurements(measurementDB.findMeasurementsByWorkOrderID(rs.getInt("workorder_id_PK")));
		result.setEmployee(employeeDB.buildObjectFromResultset(rs));
		result.setAsset(assetDB.buildObjectFromResultset(rs));
		

		// return the Maintenance object
				
		return result;
	}
	
	public Service buildServiceObjectFromResultset(ResultSet rs) throws SQLException {
		if(rs.getInt("workorder_id_PK") == 0) {
			return null;
		}else return buildServiceObject(rs);
	}
	private Service buildServiceObject(ResultSet rs) throws SQLException {
		// create a new Service object
		Service result = new Service();

		// set the properties of the Service object based on the values in the ResultSet
		result.setWorkOrderID(rs.getInt("workorder_id_PK"));
		result.setTitle(rs.getString("workorder_title"));
		result.setType(rs.getString("workorder_type"));
		result.setDescription(rs.getString("workorder_description"));
		result.setPriority(rs.getShort("workorder_priority"));
		result.setFinished(rs.getBoolean("workorder_finished"));
		
		//Service-specific
//		result.setReference(referenceDB.findReferenceByID(rs.getInt("workorder_reference_id_FK")));
		result.setReference(referenceDB.buildObjectFromResultset(rs));
		
		//Dates
		result.setStartDate(convertSqlDateToCalendar(rs.getDate("workorder_startdate")));
		result.setEndDate(convertSqlDateToCalendar(rs.getDate("workorder_enddate")));
		
		//Objects
//		result.setEmployee(employeeDB.findEmployeeByID(rs.getInt("workorder_employee_id_FK")));
//		result.setAsset(assetDB.findAssetByID(rs.getInt("workorder_asset_id_FK")));
//		result.setSparepartsUsed(sparepartUsedDB.findSparepartListByWorkorderID(rs.getInt("workorder_id_PK")));
//		result.setMeasurements(measurementDB.findMeasurementsByWorkOrderID(rs.getInt("workorder_id_PK")));
		result.setEmployee(employeeDB.buildObjectFromResultset(rs));
		result.setAsset(assetDB.buildObjectFromResultset(rs));
		// return the Service object
		return result;
	}
	
	public Repair buildRepairObjectFromResultset(ResultSet rs) throws SQLException {
		if(rs.getInt("workorder_id_PK") == 0) {
			return null;
		}else return buildRepairObject(rs);
	}
	private Repair buildRepairObject(ResultSet rs) throws SQLException {
		// create a new Repair object
		Repair result = new Repair();

		// set the properties of the Repair object based on the values in the ResultSet
		result.setWorkOrderID(rs.getInt("workorder_id_PK"));
		result.setTitle(rs.getString("workorder_title"));
		result.setType(rs.getString("workorder_type"));
		result.setDescription(rs.getString("workorder_description"));
		result.setPriority(rs.getShort("workorder_priority"));
		result.setFinished(rs.getBoolean("workorder_finished"));
		
		//Repair-specific
		result.setPrice(rs.getDouble("workorder_price"));
//		result.setReference(referenceDB.findReferenceByID(rs.getInt("workorder_reference_id_FK")));
		result.setReference(referenceDB.buildObjectFromResultset(rs));
		
		//Dates
		result.setStartDate(convertSqlDateToCalendar(rs.getDate("workorder_startdate")));
		result.setEndDate(convertSqlDateToCalendar(rs.getDate("workorder_enddate")));
		
		//Objects
//		result.setEmployee(employeeDB.findEmployeeByID(rs.getInt("workorder_employee_id_FK")));
//		result.setAsset(assetDB.findAssetByID(rs.getInt("workorder_asset_id_FK")));
//		result.setSparepartsUsed(sparepartUsedDB.findSparepartListByWorkorderID(rs.getInt("workorder_id_PK")));
//		result.setMeasurements(measurementDB.findMeasurementsByWorkOrderID(rs.getInt("workorder_id_PK")));
		result.setEmployee(employeeDB.buildObjectFromResultset(rs));
		result.setAsset(assetDB.buildObjectFromResultset(rs));
		// return the Repair object
		return result;
	}

	@Override
	public int getLatestKey() {
		
		int outputKey = -1;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindLatestKey = con.prepareStatement(SELECT_LATEST_KEY)) {
			
			//prepare statement left empty
			
			//execute statement
			ResultSet rs = psFindLatestKey.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Maintenance object from result set
				outputKey = rs.getInt(1);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING LATEST KEY:" + e.getMessage());
		}
		
		return outputKey;
	}
	
}