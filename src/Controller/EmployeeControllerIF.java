package Controller;

import model.Employee;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface EmployeeControllerIF {
	
	public Employee findEmployeeByID(int employeeID);

}