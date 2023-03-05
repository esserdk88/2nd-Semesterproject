package dal;

import java.util.List;

import model.Department;

public interface DepartmentDBIF {
	
	public boolean createDepartment(Department d);
	public Department getDepartmentByID(int id);
	public boolean updateDepartment(Department d);
	public boolean deleteDepartment(Department d);
	
	public List<Department> getAllDepartments();

}
