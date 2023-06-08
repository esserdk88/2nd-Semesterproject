package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import dal.interfaces.AssetDBIF;
import dal.interfaces.EmployeeDBIF;
import dal.interfaces.MeasurementDBIF;
import dal.interfaces.ReferenceDBIF;
import dal.interfaces.SparepartUsedDBIF;
import dal.interfaces.WorkOrderDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import model.Employee;
import model.Maintenance;
import model.Repair;
import model.Service;
import model.Workorder;

public class WorkOrderDB implements WorkOrderDBIF {
	
	public static final String FIELDS_COMMON_WITH_ID = "workorder_id_PK, workorder_title, workorder_type, workorder_startdate, workorder_enddate, "
			+ "workorder_priority, workorder_description, workorder_finished, workorder_asset_id_FK, workorder_employee_id_FK";
		
	//Without ID and employee.
	public static final String FIELDS_INSERT_COMMON = "workorder_title, workorder_type, workorder_startdate, workorder_enddate, "
						+ "workorder_priority, workorder_description, workorder_finished, workorder_asset_id_FK";
	
	public static final String FIELDS_UPDATE_COMMON = "workorder_title = ?, workorder_type = ?, workorder_startdate = ?, workorder_enddate = ?, " 
			+ "workorder_priority = ?, workorder_description = ?, workorder_finished = ?, workorder_asset_id_FK = ?";
	
	public static final String FIELDS_UPDATE_COMMON_WITH_EMPLOYEE = FIELDS_UPDATE_COMMON + ", workorder_employee_id_FK = ?";
	
	
	public static final String FIELDS_MAINTENANCE_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_interval, workorder_repeatable";
	public static final String FIELDS_SERVICE_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_reference_id_FK";
	public static final String FIELDS_REPAIR_WITH_ID = FIELDS_COMMON_WITH_ID + ", workorder_reference_id_FK, workorder_price";
	
	public static final String FIELDS_INSERT_MAINTENANCE = FIELDS_INSERT_COMMON + ", workorder_interval, workorder_repeatable";
	public static final String FIELDS_INSERT_SERVICE = FIELDS_INSERT_COMMON + ", workorder_reference_id_FK";
	public static final String FIELDS_INSERT_REPAIR = FIELDS_INSERT_COMMON + ", workorder_reference_id_FK, workorder_price";
	
	public static final String SELECT_MAINTENANCE_BY_ID = "SELECT * FROM MaintenanceView WHERE workorder_id_PK = ?";
	public static final String SELECT_SERVICE_BY_ID = "SELECT * FROM ServiceView WHERE workorder_id_PK = ?";
	public static final String SELECT_REPAIR_BY_ID = "SELECT * FROM RepairView WHERE workorder_id_PK = ?";
	public static final String SELECT_WORKORDER_BY_ID = "SELECT * from WorkOrdersView WHERE workorder_id_PK = ?";
	
	

	public static final String SELECT_UNFINISHED_WORKORDERS = "SELECT * from WorkOrdersView where workorder_finished = 0 and workorder_startdate <= GETDATE()";
	public static final String SELECT_ALL_WORKORDERS_BY_ASSET_ID = "SELECT * from WorkOrdersView where asset_id_PK = ?";
	public static final String SELECT_ALL_WORKORDERS_BY_IDS = "SELECT * from WorkOrdersView where workorder_id_PK IN (";

	//Used when selecting specific workorders. This script is not complete before being processed in select workordersById method.
	public static final String SELECT_WORKORDERS_BY_ID = "SELECT " + FIELDS_COMMON_WITH_ID + " FROM Workorder WHERE workorder_id_PK IN (";
	
	public static final String SELECT_ALL_WORKORDERS = "SELECT * from WorkOrdersView";
	public static final String SELECT_ALL_MAINTENANCE = "SELECT * FROM MaintenanceView";
	public static final String SELECT_ALL_SERVICE = "SELECT * FROM ServiceView";
	public static final String SELECT_ALL_REPAIR = "SELECT * FROM RepairView";
	
	public static final String INSERT_MAINTENANCE = "INSERT INTO Workorder (" + FIELDS_INSERT_MAINTENANCE + ") VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	public static final String INSERT_SERVICE = "INSERT INTO Workorder (" + FIELDS_INSERT_SERVICE + ") VALUES (?,?,?,?,?,?,?,?,?)" ;
	public static final String INSERT_REPAIR = "INSERT INTO Workorder (" + FIELDS_INSERT_REPAIR + ") VALUES (?,?,?,?,?,?,?,?,?,?)" ;
	
	public static final String DELETE_WORK_ORDER_BY_ID = "DELETE FROM Workorder where workorder_id_PK = ?";
	
	public static final String UPDATE_WORK_ORDER_BY_ID = "UPDATE Workorder SET " + FIELDS_INSERT_COMMON + " WHERE workorder_id_PK = ?";
	
	public static final String SELECT_LATEST_KEY = "SELECT MAX (workorder_id_PK) from Workorder";
	
	private EmployeeDBIF employeeDB = Database.getInstance().getEmployeeDataBase();
	private AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
	private SparepartUsedDBIF sparepartUsedDB = Database.getInstance().getSparepartUsedDataBase();
	private MeasurementDBIF measurementDB = Database.getInstance().getMeasurementDataBase();
	private ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();
		
	// The above code is a constructor for a class called WorkOrderDB. It does not have any parameters or
	// any code inside it. It is used to create an instance of the WorkOrderDB class.
	public WorkOrderDB() {
		//Left empty
	}
	
	/**
	 * This Java function adds a maintenance work order to a database.
	 * 
	 * @param workOrder an object of type Maintenance, which contains information about the maintenance
	 * work order to be added to the database.
	 * @return A boolean value indicating whether the addition of a maintenance work order was successful
	 * or not.
	 */
	@Override
	public boolean addMaintenanceWorkOrder(Maintenance workOrder) {

		boolean success = false;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psAddMaintenance = con.prepareStatement(INSERT_MAINTENANCE)) {

			// prepare statement
			setGeneralFieldsCreateWorkOrder(psAddMaintenance, workOrder, "Maintenance");
			psAddMaintenance.setInt(9, workOrder.getIntervalDayCount());
			psAddMaintenance.setBoolean(10, workOrder.isRepeated());

			// execute statement
			psAddMaintenance.executeUpdate();

			success = true;

		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING MAINTENANCE WORKORDER:" + e.getMessage());
		}

		return success;
	}

	/**
	 * This Java function adds a service work order to a database.
	 * 
	 * @param workOrder an object of type Service, which contains information about a service work order
	 * that needs to be added to the database.
	 * @return The method is returning a boolean value, which indicates whether the addition of a service
	 * work order was successful or not.
	 */
	@Override
	public boolean addServiceWorkOrder(Service workOrder) {

		boolean success = false;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psAddService = con.prepareStatement(INSERT_SERVICE)) {

			// prepare statement
			setGeneralFieldsCreateWorkOrder(psAddService, workOrder, "Service");
			psAddService.setInt(9, workOrder.getReference().getCvr());

			// execute statement
			psAddService.executeUpdate();

			success = true;

		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING SERVICE WORKORDER:" + e.getMessage());
		}

		return success;
	}

	/**
	 * This Java function adds a repair work order to a database.
	 * 
	 * @param workOrder an object of type Repair, which contains information about a repair work order
	 * such as the date, description, and customer information.
	 * @return The method is returning a boolean value indicating whether the addition of a repair work
	 * order was successful or not.
	 */
	@Override
	public boolean addRepairWorkOrder(Repair workOrder) {
		boolean success = false;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psAddRepair = con.prepareStatement(INSERT_REPAIR)) {

			// prepare statement
			setGeneralFieldsCreateWorkOrder(psAddRepair, workOrder, "Repair");
			psAddRepair.setInt(9, workOrder.getReference().getCvr());
			psAddRepair.setDouble(10, workOrder.getPrice());

			// execute statement
			psAddRepair.executeUpdate();

			success = true;

		} catch (SQLException e) {
			System.out.println("ERROR FROM INSERTING REPAIR WORKORDER:" + e.getMessage());
		}

		return success;
	}

	/**
	 * This Java function retrieves a maintenance work order from a database by its ID.
	 * 
	 * @param workOrderID an integer representing the ID of the maintenance work order that needs to be
	 * retrieved from the database.
	 * @return The method is returning a Maintenance object that corresponds to the work order ID passed
	 * as a parameter. If no Maintenance object is found, it returns null.
	 */
	@Override
	public Maintenance findMaintenanceWorkOrderByID(int workOrderID) {

		Maintenance maintenance = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindMaintenance = con.prepareStatement(SELECT_MAINTENANCE_BY_ID)) {

			// prepare statement
			psFindMaintenance.setInt(1, workOrderID);

			// execute statement
			ResultSet rs = psFindMaintenance.executeQuery();

			if (rs.next()) {
				// build Maintenance object from result set
				maintenance = buildMaintenanceObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MAINTENANCE WORKORDER:" + e.getMessage());
		}

		return maintenance;
	}

	/**
	 * This Java function retrieves a service work order by its ID from a database.
	 * 
	 * @param workOrderID an integer representing the ID of the work order for which the service needs to
	 * be found.
	 * @return The method is returning a Service object.
	 */
	@Override
	public Service findServiceWorkOrderByID(int workOrderID) {
		Service service = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindService = con.prepareStatement(SELECT_SERVICE_BY_ID)) {

			// prepare statement
			psFindService.setInt(1, workOrderID);

			// execute statement
			ResultSet rs = psFindService.executeQuery();

			if (rs.next()) {
				// build Service object from result set
				service = buildServiceObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SERVICE:" + e.getMessage());

		}
		return service;
	}

	/**
	 * This Java function retrieves a repair work order from a database by its ID.
	 * 
	 * @param workOrderID an integer representing the ID of the repair work order that needs to be
	 * retrieved from the database.
	 * @return The method is returning a Repair object.
	 */
	@Override
	public Repair findRepairWorkOrderByID(int workOrderID) {
		Repair repair = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindRepair = con.prepareStatement(SELECT_REPAIR_BY_ID)) {

			// prepare statement
			psFindRepair.setInt(1, workOrderID);

			// execute statement
			ResultSet rs = psFindRepair.executeQuery();

			if (rs.next()) {
				// build Repair object from result set
				repair = buildRepairObject(rs);
			}

		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING REPAIR:" + e.getMessage());

		}
		return repair;
	}
	
	/**
	 * This function retrieves all workorders from a database and returns them as a list of Workorder
	 * objects.
	 * 
	 * @return A list of Workorder objects is being returned.
	 */
	public List<Workorder> getAllWorkorders() throws SQLException {
		List<Workorder> workorders = new ArrayList<>();
		
		Connection con = DatabaseConnection.getInstance().getConnection();
		try(PreparedStatement psFindAllWorkorders = con.prepareStatement(SELECT_ALL_WORKORDERS)) {
			ResultSet rs = psFindAllWorkorders.executeQuery();
			
			while(rs.next()) {
				workorders.add(buildWorkorderObjectFromResultset(rs));
			}
		}
		
		return workorders;
	}

	/**
	 * This Java function retrieves all maintenance work orders from a database and returns them as a list
	 * of Maintenance objects.
	 * 
	 * @return A list of Maintenance objects, which are retrieved from the database using a prepared
	 * statement.
	 */
	@Override
	public List<Maintenance> getAllMaintenanceWorkOrders() {
		List<Maintenance> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_MAINTENANCE)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindWorkorder.executeQuery();

			// build Maintenance object from result set
			while (rs.next()) {
				list.add(buildMaintenanceObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING MAINTENANCE WORKORDER:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function retrieves all service work orders from a database and returns them as a list of
	 * Service objects.
	 * 
	 * @return A list of Service objects representing all service work orders retrieved from the database.
	 */
	@Override
	public List<Service> getAllServiceWorkOrders() {
		List<Service> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_SERVICE)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindWorkorder.executeQuery();

			// build Service object from result set
			while (rs.next()) {
				list.add(buildServiceObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING SERVICE WORKORDER:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This function retrieves all repair work orders from a database and returns them as a list of Repair
	 * objects.
	 * 
	 * @return A list of Repair objects, which are retrieved from the database using a prepared statement.
	 */
	@Override
	public List<Repair> getAllRepairWorkOrders() {
		List<Repair> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_REPAIR)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindWorkorder.executeQuery();

			// build Repair object from result set
			while (rs.next()) {
				list.add(buildRepairObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING REPAIR WORKORDER:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function retrieves all unfinished work orders from a database and returns them as a list
	 * of Workorder objects.
	 * 
	 * @return A list of unfinished work orders.
	 */
	@Override
	public List<Workorder> getAllUnfinishedWorkOrders() {
		List<Workorder> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_UNFINISHED_WORKORDERS)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindWorkorder.executeQuery();

			// build WorkOrder object from result set
			while (rs.next()) {
				list.add(switchWorkorderBuilderFromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING UNFINISHED WORKORDER:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function retrieves all work orders associated with a given asset ID from a database and
	 * returns them as a list.
	 * 
	 * @param assetID The ID of the asset for which we want to retrieve all work orders.
	 * @return A list of Workorder objects that are associated with a specific asset ID.
	 */
	@Override
	public List<Workorder> getAllWorkOrdersByAssetID(int assetID) {
		List<Workorder> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindWorkorder = con.prepareStatement(SELECT_ALL_WORKORDERS_BY_ASSET_ID)) {

			// prepare statement
			psFindWorkorder.setInt(1, assetID);

			// execute statement
			ResultSet rs = psFindWorkorder.executeQuery();

			// build WorkOrder object from result set
			while (rs.next()) {
				list.add(switchWorkorderBuilderFromResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING UNFINISHED WORKORDER:" + e.getMessage());
		}
		return list;
	}

	/**
	 * This Java function deletes a work order from a database by its ID and returns a boolean indicating
	 * whether the deletion was successful.
	 * 
	 * @param workOrderID an integer representing the ID of the work order that needs to be deleted from
	 * the database.
	 * @return The method is returning a boolean value indicating whether the deletion of the work order
	 * with the specified ID was successful or not.
	 */
	public boolean deleteWorkOrderByID(int workOrderID) {

		boolean success = false;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psDeleteWorkOrder = con.prepareStatement(DELETE_WORK_ORDER_BY_ID)) {

			// prepare statement
			psDeleteWorkOrder.setInt(1, workOrderID);

			// execute statement
			psDeleteWorkOrder.executeUpdate();
			success = psDeleteWorkOrder.getUpdateCount() > 0;

		} catch (SQLException e) {
			System.out.println("ERROR FROM DELETING WORKORDER:" + e.getMessage());
		}

		return success;
	}

	/**
	 * This Java function updates a work order in a database with information provided in a Workorder
	 * object.
	 * 
	 * @param workorder A Workorder object that contains the updated information for the work order.
	 * @return A boolean value indicating whether the update of the workorder was successful or not.
	 */
	public boolean updateWorkorder(Workorder workorder) {
		boolean success = false;
		String finishedStatement = "UPDATE Workorder SET " + FIELDS_UPDATE_COMMON_WITH_EMPLOYEE
				+ " WHERE workorder_id_PK = ?";
		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psUpdateWorkorder = con.prepareStatement(finishedStatement)) {
			psUpdateWorkorder.setString(1, workorder.getTitle());
			psUpdateWorkorder.setString(2, workorder.getType());
			if (workorder.getStartDate() != null) {
				psUpdateWorkorder.setDate(3, DataBaseUtilities.convertCalendarToSqlDate(workorder.getStartDate()));
			} else {
				psUpdateWorkorder.setDate(3, null);
			}
			if (workorder.getEndDate() != null) {
				psUpdateWorkorder.setDate(4, DataBaseUtilities.convertCalendarToSqlDate(workorder.getEndDate()));
			} else {
				psUpdateWorkorder.setDate(4, null);
			}
			psUpdateWorkorder.setInt(5, workorder.getPriority());
			psUpdateWorkorder.setString(6, workorder.getDescription());
			psUpdateWorkorder.setBoolean(7, workorder.isFinished());
			psUpdateWorkorder.setInt(8, workorder.getAsset().getAssetID());
			psUpdateWorkorder.setInt(9, workorder.getEmployee().getEmployeeID());
			psUpdateWorkorder.setInt(10, workorder.getWorkOrderID());
			psUpdateWorkorder.executeUpdate();

			success = true;

		} catch (SQLException e) {
			System.out.println("ERROR FROM UPDATING WORKORDER");
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean assignEmployeeToWorkOrder(Employee employee, Maintenance workOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	// Builders

	/**
	 * This function builds a Maintenance object by setting its general fields and specific fields related
	 * to maintenance work orders.
	 * 
	 * @param rs The "rs" parameter is a ResultSet object, which is a table of data representing a
	 * database result set. It is used to retrieve the data from the database query result. In this
	 * method, the ResultSet object is used to retrieve the values of the columns in the current row of
	 * the result set and
	 * @return The method `buildMaintenanceObject` is returning a `Maintenance` object.
	 */
	private Maintenance buildMaintenanceObject(ResultSet rs) throws SQLException {
		// create a new Maintenance object
		Maintenance result = new Maintenance();

		// Set WorkOrder General Fields
		buildGeneralFields(result, rs);

		// Maintenance-specific
		result.setRepeated(rs.getBoolean("workorder_repeatable"));
		result.setIntervalDayCount(rs.getInt("workorder_interval"));

		// return the Maintenance object
		return result;
	}

	/**
	 * This function builds a Service object by setting its general fields and a reference object from a
	 * ResultSet.
	 * 
	 * @param rs The "rs" parameter is a ResultSet object, which is a table of data representing a
	 * database result set. It is used to retrieve the data from the database query result. In this
	 * method, the ResultSet object is used to build a Service object by setting its general fields and
	 * service-specific fields.
	 * @return The method `buildServiceObject` is returning a `Service` object.
	 */
	private Service buildServiceObject(ResultSet rs) throws SQLException {
		// create a new Service object
		Service result = new Service();

		// Set WorkOrder General Fields
		buildGeneralFields(result, rs);

		// Service-specific
		result.setReference(referenceDB.buildObjectFromResultset(rs));

		// return the Service object
		return result;
	}

	/**
	 * This Java function builds a Repair object from a ResultSet object, setting its general fields and
	 * specific fields.
	 * 
	 * @param rs rs is a ResultSet object that contains the data retrieved from a database query. It is
	 * used to extract the values of the columns in the current row of the ResultSet and build a Repair
	 * object from them.
	 * @return The method `buildRepairObject` is returning a `Repair` object.
	 */
	private Repair buildRepairObject(ResultSet rs) throws SQLException {
		// create a new Repair object
		Repair result = new Repair();

		// Set WorkOrder General Fields
		buildGeneralFields(result, rs);

		// Repair-specific
		result.setPrice(rs.getDouble("workorder_price"));
		result.setReference(referenceDB.buildObjectFromResultset(rs));

		// return the Repair object
		return result;
	}

	/**
	 * This Java function retrieves the latest key from a database connection.
	 * 
	 * @return The method is returning an integer value, which is the latest key retrieved from the
	 * database. If there is an error while retrieving the latest key, the method returns -1.
	 */
	@Override
	public int getLatestKey() {

		int outputKey = -1;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindLatestKey = con.prepareStatement(SELECT_LATEST_KEY)) {

			// prepare statement left empty

			// execute statement
			ResultSet rs = psFindLatestKey.executeQuery();

			if (rs.next()) {
				outputKey = rs.getInt(1);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING LATEST KEY:" + e.getMessage());
		}

		return outputKey;
	}

	/**
	 * This Java function retrieves a work order from a database by its ID and returns it as a Workorder
	 * object.
	 * 
	 * @param workorderId an integer representing the ID of the workorder that needs to be retrieved from
	 * the database.
	 * @return This method returns a Workorder object with the details of a work order identified by the
	 * given workorderId. If no work order is found with the given ID, it returns null.
	 */
	@Override
	public Workorder getWorkorderById(int workorderId) throws SQLException {
		Workorder workorder = null;

		// Setting Connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psGetWorkorderById = con.prepareStatement(SELECT_WORKORDER_BY_ID)) {

			// Prepare statement
			psGetWorkorderById.setInt(1, workorderId);

			// Execute Statement
			ResultSet rs = psGetWorkorderById.executeQuery();

			// Build WorkOrder
			if (rs.next()) {
				workorder = switchWorkorderBuilderFromResultSet(rs);
			}
		}
		return workorder;
	}

	/**
	 * This Java function retrieves a list of workorders by their IDs from a database.
	 * 
	 * @param workorderIds an array of integers representing the IDs of the workorders to retrieve from
	 * the database.
	 * @return A list of Workorder objects that match the given array of workorderIds.
	 */
	@Override
	public List<Workorder> getWorkordersById(int[] workorderIds) {

		List<Workorder> workorders = new ArrayList<>();

		String placeholders = IntStream.range(0, workorderIds.length).mapToObj(i -> "?")
				.collect(Collectors.joining(","));

		String finishedStatement = SELECT_ALL_WORKORDERS_BY_IDS + placeholders + ")";

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psSelectWorkordersByIds = con.prepareStatement(finishedStatement)) {
			for (int i = 0; i < workorderIds.length; i++) {
				psSelectWorkordersByIds.setInt(i + 1, workorderIds[i]);
			}
			ResultSet rs = psSelectWorkordersByIds.executeQuery();

			while (rs.next()) {
				workorders.add(switchWorkorderBuilderFromResultSet(rs));
			}
		} catch (Exception e) {
			System.out.println("ERROR FROM RETRIEVING WORKORDERS BY MULTIBLE IDS: CHECK FOR VALID IDS");
		}
		return workorders;
	}

	/**
	 * This function builds a Workorder object from a ResultSet if the ResultSet contains a
	 * workorder_id_PK column.
	 * 
	 * @param rs ResultSet object that contains the data retrieved from a database query. It represents a
	 * set of rows retrieved from a database table or the result of any other database operation.
	 * @return The method `buildWorkorderObjectFromResultset` returns a `Workorder` object that is built
	 * from the data in the `ResultSet` parameter `rs`. If the `ResultSet` does not contain a valid
	 * `workorder_id_PK` value, the method returns `null`.
	 */
	public Workorder buildWorkorderObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "workorder_id_PK")) {
			return switchWorkorderBuilderFromResultSet(rs);
		} else
			return null;
	}
	
	/**
	 * This function searches a work order database based on various parameters and returns a list of
	 * matching work orders.
	 * 
	 * @param name A string representing the title of the work order to search for.
	 * @param priority A list of Short values representing the priority levels to search for in the work
	 * order database.
	 * @param location A string representing the location of the work orders to be searched.
	 * @param isFinished A boolean value indicating whether to search for finished or unfinished work
	 * orders. If true, the method will search for finished work orders, and if false, it will search for
	 * unfinished work orders.
	 * @return A list of Workorder objects that match the search criteria specified in the method
	 * parameters.
	 */
	@Override
	public List<Workorder> searchWorkOrderDataBase(String name, List<Short> priority, String location, boolean isFinished){
		List<Workorder> workOrders = new ArrayList<>();
		StringBuilder queryBuilder = new StringBuilder("SELECT * FROM WorkOrdersView WHERE 1=1");
		List<Object> parameters = new ArrayList<>();
		
		if(name != null && !name.isEmpty()) {
			queryBuilder.append(" AND workorder_title like ?");
			parameters.add("%" + name + "%");
		}
		
		if(priority != null && priority.size() != 0) {
			queryBuilder.append(" AND workorder_priority IN (");
			for (int i = 0; i < priority.size(); i++) {
				queryBuilder.append("?");
				if (i < priority.size() - 1) {
                    queryBuilder.append(", ");
                }
                parameters.add(priority.get(i));
			}
			queryBuilder.append(")");
		}
		
		if (location != null && !location.isEmpty()) {
            queryBuilder.append(" AND location_id_PK = ?");
            parameters.add(location);
        }
		
		if(isFinished) {
			queryBuilder.append(" AND workorder_finished = 1");
		}
		else {
			queryBuilder.append(" AND workorder_finished = 0");
		}
		
		String query = queryBuilder.toString();
		
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psSearchWorkOrders = con.prepareStatement(query)) {
			
			for (int i = 0; i < parameters.size(); i++) {
				psSearchWorkOrders.setObject(i + 1, parameters.get(i));
            }
			
			ResultSet rs = psSearchWorkOrders.executeQuery();
			while (rs.next()) {
				workOrders.add(switchWorkorderBuilderFromResultSet(rs));
			}
			
		}catch (Exception e) {
			System.out.println("ERROR SEARCHING WORKORDER DATABASE: ");
			System.out.println(query);
		}
		
		return workOrders;
	}
	
	
	/**
	 * This Java function builds a Workorder object by setting its general fields, dates, and related
	 * objects from a ResultSet.
	 * 
	 * @param result an instance of the Workorder class that will be populated with data from the
	 * ResultSet.
	 * @param rs ResultSet object containing the data retrieved from the database query.
	 * @return The method is returning a Workorder object.
	 */
	private Workorder buildGeneralFields(Workorder result, ResultSet rs) throws SQLException {

		// General Fields
		result.setWorkOrderID(rs.getInt("workorder_id_PK"));
		result.setTitle(rs.getString("workorder_title"));
		result.setType(rs.getString("workorder_type"));
		result.setDescription(rs.getString("workorder_description"));
		result.setPriority(rs.getShort("workorder_priority"));
		result.setFinished(rs.getBoolean("workorder_finished"));

		// Dates
		result.setStartDate(DataBaseUtilities.convertSqlDateToCalendar(rs.getDate("workorder_startdate")));
		result.setEndDate(DataBaseUtilities.convertSqlDateToCalendar(rs.getDate("workorder_enddate")));
		result.setEmployee(employeeDB.buildObjectFromResultset(rs));
		result.setAsset(assetDB.buildObjectFromResultset(rs));
		return result;
	}

	/**
	 * The function switches between different types of workorders based on a string value and returns the
	 * corresponding object.
	 * 
	 * @param rs ResultSet object that contains the data retrieved from a database query.
	 * @return The method is returning a Workorder object.
	 */
	private Workorder switchWorkorderBuilderFromResultSet(ResultSet rs) throws SQLException {
		Workorder workorder;
		switch (rs.getString("workorder_type")) {
		case "Maintenance":
			workorder = buildMaintenanceObject(rs);
			break;
		case "Service":
			workorder = buildServiceObject(rs);
			break;
		case "Repair":
			workorder = buildRepairObject(rs);
			break;
		default:
			workorder = null;
			break;
		}
		return workorder;

	}

	/**
	 * This function sets the general fields of a work order in a prepared statement for insertion into a
	 * database.
	 * 
	 * @param preparedStatement A prepared statement object that is used to execute a SQL statement with
	 * parameters.
	 * @param workOrder an object of the Workorder class that contains information about the work order
	 * being created.
	 * @param type The type of the work order (e.g. maintenance, repair, installation, etc.)
	 */
	private void setGeneralFieldsCreateWorkOrder(PreparedStatement preparedStatement, Workorder workOrder, String type)
			throws SQLException {
		preparedStatement.setString(1, workOrder.getTitle());
		preparedStatement.setString(2, type);
		preparedStatement.setDate(3, DataBaseUtilities.convertCalendarToSqlDate(workOrder.getStartDate()));
		preparedStatement.setDate(4, DataBaseUtilities.convertCalendarToSqlDate(workOrder.getEndDate()));
		preparedStatement.setShort(5, workOrder.getPriority());
		preparedStatement.setString(6, workOrder.getDescription());
		preparedStatement.setBoolean(7, workOrder.isFinished());
		preparedStatement.setInt(8, workOrder.getAsset().getAssetID());
	}
	
}