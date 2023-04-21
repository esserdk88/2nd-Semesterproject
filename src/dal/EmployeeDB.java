package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import model.Employee;
import model.Location;

public class EmployeeDB implements EmployeeDBIF {
	
	public static final String FIELDS = "employee_id_PK, employee_start_date, employee_cpr, employee_position, employee_phone,"
										+ "employee_email, employee_name, employee_address_id_FK";
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT " + FIELDS + " FROM Employee Where employee_id_PK = ?";
	public static final String SELECT_ALL_EMPLOYEES = "SELECT " + FIELDS + " FROM Employee";
	
	private AddressDB addressDB = new AddressDB();
	
	@Override
	public Employee findEmployeeByID(int EmployeeID) {
		
		Employee employee = null;
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindEmployee = con.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
			
			//prepare statement
			psFindEmployee.setInt(1, EmployeeID);
			
			//execute statement
			ResultSet rs = psFindEmployee.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Location object from result set
				employee = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}
		
		return employee;
	}

	private Employee buildObject(ResultSet rs) throws SQLException {
		
		// Create a new Employee object
		Employee result = new Employee();

		// Set the properties of the employee object based on the values in the ResultSet
		result.setEmployeeID(rs.getInt("employee_id_PK"));
		result.setCprNumber(rs.getString("employee_cpr"));
		result.setStartDate(convertSqlDateToCalendar(rs.getDate("employee_start_date"))); //Convert date to calendar
		result.setPosition(rs.getString("employee_position"));
		result.setName(rs.getString("employee_name"));
		result.setPhone(rs.getString("employee_phone"));
		result.setEmail(rs.getString("employee_email"));
		result.setAddress(addressDB.findAddressByID(rs.getInt("employee_address_id_FK")));
		
		// return the location object
		return result;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> list = new ArrayList<>();
		
		// establish database connection
		try (Connection con = DatabaseConnection.getInstance().getConnection();
				PreparedStatement psFindEmployee = con.prepareStatement(SELECT_ALL_EMPLOYEES)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindEmployee.executeQuery();
			
			if (rs != null) {
				//build Address object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}
		return list;
	}
	
	public static Calendar convertSqlDateToCalendar(Date sqlDate) { 
		Calendar calendar = Calendar.getInstance(); calendar.setTime(sqlDate); 
		
		return calendar; 
	}
}