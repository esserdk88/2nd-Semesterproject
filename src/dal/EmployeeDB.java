package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	
	public static final String SELECT_EMPLOYEE_BY_ID = "SELECT * FROM EmployeeView Where employee_id_PK = ?";
	public static final String SELECT_ALL_EMPLOYEES = "SELECT * FROM EmployeeView";
	
	private AddressDBIF addressDB = Database.getInstance().getAddressDataBase();
	private Connection con = DatabaseConnection.getInstance().getConnection();
	
	@Override
	public Employee findEmployeeByID(int employeeID) {
		
		Employee employee = null;
		
		try (PreparedStatement psFindEmployee = con.prepareStatement(SELECT_EMPLOYEE_BY_ID)) {
			
			//prepare statement
			psFindEmployee.setInt(1, employeeID);
			
			//execute statement
			ResultSet rs = psFindEmployee.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Employee object from result set
				employee = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}
		
		return employee;
	}
	public Employee buildObjectFromResultset(ResultSet rs) throws SQLException {
		if(DataBaseUtilities.check(rs, null, "employee_id_PK")){
			return buildObject(rs);
		}else return null;
	}
	private Employee buildObject(ResultSet rs) throws SQLException {
		
		// Create a new Employee object
		Employee result = new Employee();

		// Set the properties of the Employee object based on the values in the ResultSet
		result.setEmployeeID(rs.getInt("employee_id_PK"));
		result.setCprNumber(rs.getString("employee_cpr"));
		result.setStartDate(DataBaseUtilities.convertSqlDateToCalendar(rs.getDate("employee_start_date"))); //Convert date to calendar
		result.setPosition(rs.getString("employee_position"));
		result.setName(rs.getString("employee_name"));
		result.setPhone(rs.getString("employee_phone"));
		result.setEmail(rs.getString("employee_email"));
		result.setAddress(addressDB.buildObjectFromResultset(rs, "employee_"));
		//result.setAddress(addressDB.findAddressByID(rs.getInt("employee_address_id_FK")));
		
		// return the Employee object
		return result;
	}

	@Override
	public List<Employee> getAllEmployees() {
		
		List<Employee> list = new ArrayList<>();
		
		try (PreparedStatement psFindEmployee = con.prepareStatement(SELECT_ALL_EMPLOYEES)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindEmployee.executeQuery();
			
			if (rs != null) {
				//build Employee object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING EMPLOYEE:" + e.getMessage());
		}
		return list;
	}
	
}