package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.interfaces.AddressDBIF;
import dal.interfaces.EmployeeDBIF;
import dao.DataBaseUtilities;
import dao.Database;
import dao.DatabaseConnection;
import gui.components.ValueCheckerIF;
import model.Employee;

/**
 * The EmployeeDB class implements methods to retrieve Employee objects from a database.
 */
public class EmployeeDB implements EmployeeDBIF {

	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM EmployeeView Where employee_id_PK = ?";
	public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM EmployeeView";

	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();

	/**
	 * This Java function retrieves an employee from a database by their ID.
	 * 
	 * @param employeeID an integer representing the unique identifier of the employee to be retrieved
	 * from the database.
	 * @return An Employee object is being returned.
	 */
	@Override
	public Employee findEmployeeByID(int employeeID) {

		Employee employee = null;

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindEmployee = con.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {

			// prepare statement
			psFindEmployee.setInt(1, employeeID);

			// execute statement
			ResultSet rs = psFindEmployee.executeQuery();

			if (rs.next()) {
				// build Employee object from result set
				employee = buildObject(rs);
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}

		return employee;
	}

	/**
	 * This function builds an Employee object from a ResultSet if the ResultSet contains a valid employee
	 * ID, otherwise it returns null.
	 * 
	 * @param rs The ResultSet object containing the data retrieved from the database.
	 * @return The method `buildObjectFromResultset` is returning an object of type `Employee` or `null`
	 * if the `ResultSet` does not contain a valid `employee_id_PK` value.
	 */
	public Employee buildObjectFromResultset(ResultSet rs) throws SQLException {
		if (DataBaseUtilities.check(rs, null, "employee_id_PK")) {
			return buildObject(rs);
		} else
			return null;
	}

	/**
	 * This function builds an Employee object by setting its properties based on the values in a
	 * ResultSet.
	 * 
	 * @param rs A ResultSet object that contains the data retrieved from the database.
	 * @return The method is returning an Employee object.
	 */
	private Employee buildObject(ResultSet rs) throws SQLException {

		// Create a new Employee object
		Employee result = new Employee();

		// Set the properties of the Employee object based on the values in the
		// ResultSet
		result.setEmployeeID(rs.getInt("employee_id_PK"));
		result.setCprNumber(rs.getString("employee_cpr"));
		result.setStartDate(DataBaseUtilities.convertSqlDateToCalendar(rs.getDate("employee_start_date"))); // Convert
																											// date to
																											// calendar
		result.setPosition(rs.getString("employee_position"));
		result.setName(rs.getString("employee_name"));
		result.setPhone(rs.getString("employee_phone"));
		result.setEmail(rs.getString("employee_email"));
		result.setAddress(addressDB.buildObjectFromResultset(rs, "employee_"));
		// result.setAddress(addressDB.findAddressByID(rs.getInt("employee_address_id_FK")));

		// return the Employee object
		return result;
	}

	/**
	 * This Java function retrieves all employees from a database and returns them as a list of Employee
	 * objects.
	 * 
	 * @return A list of all employees from the database.
	 */
	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> list = new ArrayList<>();

		// establish database connection
		Connection con = DatabaseConnection.getInstance().getConnection();
		try (PreparedStatement psFindEmployee = con.prepareStatement(SELECT_ALL_EMPLOYEES)) {

			// prepare statement
			// Left empty.

			// execute statement
			ResultSet rs = psFindEmployee.executeQuery();

			// build Employee object from result set
			while (rs.next()) {
				list.add(buildObject(rs));
			}
		} catch (SQLException e) {
			System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}
		return list;
	}

}