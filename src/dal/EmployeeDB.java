package dal;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import model.Employee;

public class EmployeeDB implements EmployeeDBIF {
	
	private ArrayList<String> EMPLOYEE_COLUMNS_LIST = new ArrayList<String>(Arrays.asList(
			"Employee_ID",
			"Employee_FirstName",
			"Employee_LastName",
			"Employee_Streetname",
			"Employee_Streetnumber",
			"Employee_Postalcode",
			"Employee_Cityname",
			"Employee_PhoneNumber",
			"Employee_Email",
			"Employee_Number",
			"Employee_Position",
			"Employee_DateOfEmployment"));
	private String EMPLOYEE_COLUMNS = listToString(EMPLOYEE_COLUMNS_LIST,true);
	private String EMPLOYEE_TABLE = "Employee";
	private String QUERY_EMPLOYEE_BY_ID = "Select " + EMPLOYEE_COLUMNS +" From " + EMPLOYEE_TABLE +" Where Employee_ID = ?";
	private String UPDATE_EMPLOYEE = "Update " + EMPLOYEE_TABLE + " SET " + EMPLOYEE_COLUMNS + " Values (?,?,?,?,?,?,?,?) Where Employee_ID = ?";
	
	private final String FIND_EMPLOYEE_BY_ID = "unfinished String"; //TODO finish string when skema is done.
	
	
	private String listToString(ArrayList<String> list, boolean keepID) {
		String outputString = "";
		
		if(!keepID) {
			//Remove the id
			list.remove(0);
		}
		for(int i = 0;i<list.size();i++) {
			if(i==list.size()-1) {
				//Last one
				outputString += list.get(i);
			} else {
				//Others
				outputString += list.get(i) + ",";
			}
		}
		return outputString;
	}
	
	@Override
	public boolean createEmployee(Employee e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Employee readEmployeeByID(int id,boolean fullPull) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee updateEmployee(int id, Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteEmployeeByID(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Employee objectBuilder(ResultSet rs) {
		return null;
	}
	
	public void printSQL() {
		System.out.println("Tablename: "+EMPLOYEE_COLUMNS);
		System.out.println("Columns: "+EMPLOYEE_COLUMNS);
		System.out.println("Select by ID: "+QUERY_EMPLOYEE_BY_ID);
		System.out.println("Update: "+UPDATE_EMPLOYEE);
	}

	@Override
	public Employee findEmployeeByID(int EmployeeID, boolean withRelations) {
		Employee employee = null;
		Connection con = DBConnection.getInstance().getDBcon();
		
		return null;
	}

}
