package Controller;

import java.sql.SQLException;
import java.util.List;

import dal.Database;
import dal.EmployeeDBIF;
import model.Employee;



public class EmployeeController implements EmployeeControllerIF {

	private EmployeeDBIF employeeDatabase;

	public EmployeeController() {
			this.employeeDatabase = Database.getInstance().getEmployeeDataBase();
	}
	@Override
	public Employee findEmployeeByID(int employeeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		List<Employee> employeeList = null;
		
		employeeList = employeeDatabase.getAllEmployees();
		
		return employeeList;
	}

}
