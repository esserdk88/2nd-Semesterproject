package dal;

import java.util.Calendar;
import java.util.List;
import model.Employee;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface EmployeeDBIF {
	
	//find Specific employee
	public Employee findEmployeeByID(int EmployeeID);
	
	public List<Employee> getAllEmployees();
	
}
