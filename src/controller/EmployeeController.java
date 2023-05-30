package controller;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.EmployeeControllerIF;
import dal.interfaces.EmployeeDBIF;
import dao.Database;
import gui.components.ValueCheckerIF;
import model.Employee;



/**
 * The EmployeeController class implements interfaces for finding and validating employee data in a
 * database.
 */
public class EmployeeController implements EmployeeControllerIF, ValueCheckerIF {

	private EmployeeDBIF employeeDatabase;

	// This is a constructor for the `EmployeeController` class. It initializes the `employeeDatabase`
	// instance variable by calling the `getEmployeeDataBase()` method of the `Database` singleton
	// instance. This allows the `EmployeeController` to access the database and perform operations on it.
	public EmployeeController() {
			this.employeeDatabase = Database.getInstance().getEmployeeDataBase();
	}

	/**
	 * This function returns an Employee object by searching for it in the employee database using the
	 * provided employee ID.
	 * 
	 * @param employeeID employeeID is an integer value that represents the unique identifier of an
	 * employee in the employee database. This method takes in the employeeID as a parameter and returns
	 * the corresponding Employee object from the database.
	 * @return The method `findEmployeeByID` is being overridden and it returns an `Employee` object that
	 * matches the given `employeeID`. The implementation of the method is calling the `findEmployeeByID`
	 * method of the `employeeDatabase` object to retrieve the employee with the given ID.
	 */
	@Override
	public Employee findEmployeeByID(int employeeID) {
		return employeeDatabase.findEmployeeByID(employeeID);
	}

	/**
	 * This function retrieves a list of all employees from a database.
	 * 
	 * @return A list of Employee objects is being returned.
	 */
	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employeeList = null;
		
		employeeList = employeeDatabase.getAllEmployees();
		
		return employeeList;
	}
	
	/**
	 * This function checks if a given value exists in an employee database and returns a boolean value
	 * accordingly.
	 * 
	 * @param value an integer value that represents an employee ID to be validated.
	 * @return The method is returning a boolean value, either true or false.
	 */
	@Override
	public boolean validateValue(int value) {
		if(employeeDatabase.findEmployeeByID(value) != null) {
			return true;
		}
		return false;
	}

}
