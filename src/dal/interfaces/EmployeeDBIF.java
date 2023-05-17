package dal.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Employee;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface EmployeeDBIF {

	// find Specific employee
	public Employee findEmployeeByID(int EmployeeID);

	public List<Employee> getAllEmployees();

	public Employee buildObjectFromResultset(ResultSet rs) throws SQLException;
}
