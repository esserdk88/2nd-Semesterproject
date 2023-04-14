package dal;

import java.util.Calendar;
import java.util.List;
import model.Employee;
import model.WorkorderStatus;

public interface EmployeeDBIF {

	//CRUD
	public boolean createEmployee(Employee e);
	public Employee readEmployeeByID(int id,boolean fullPull);
	public Employee updateEmployee(int id, Employee e);
	public boolean deleteEmployeeByID(int id);
	
	//More
	public List<Employee> getAllEmployees();
	
	//find Specific employee
	public Employee findEmployeeByID(int EmployeeID, boolean withRelations);
}
