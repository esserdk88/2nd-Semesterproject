package controller;

import java.sql.SQLException;
import java.util.List;

import controller.interfaces.EmployeeControllerIF;
import dal.interfaces.EmployeeDBIF;
import dao.Database;
import gui.components.ValueCheckerIF;
import model.Employee;



public class EmployeeController implements EmployeeControllerIF, ValueCheckerIF {

	private EmployeeDBIF employeeDatabase;

	public EmployeeController() {
			this.employeeDatabase = Database.getInstance().getEmployeeDataBase();
	}
	@Override
	public Employee findEmployeeByID(int employeeID) {
		return employeeDatabase.findEmployeeByID(employeeID);
	}

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employeeList = null;
		
		employeeList = employeeDatabase.getAllEmployees();
		
		return employeeList;
	}
	@Override
	public boolean validateValue(int value) {
		if(employeeDatabase.findEmployeeByID(value) != null) {
			return true;
		}
		return false;
	}

}
