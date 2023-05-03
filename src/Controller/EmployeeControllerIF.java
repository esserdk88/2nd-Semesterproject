package Controller;

import java.sql.SQLException;
import java.util.List;

import model.Employee;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface EmployeeControllerIF {
	
	public Employee findEmployeeByID(int employeeID);
	public List<Employee> getAllEmployees() throws SQLException;
}