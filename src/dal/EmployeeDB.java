package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		Connection con = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getInstance().getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Failed connecting to database in EmployeeDB. Cause "+ e.getMessage());
			e.printStackTrace();
		}
		
		try (PreparedStatement psFindEmployee = con.prepareStatement(FIND_EMPLOYEE_BY_ID)) {
			psFindEmployee.setInt(1, EmployeeID);
			rs = psFindEmployee.executeQuery();
			
			if(rs != null && rs.next()) {
				employee = buildObject(rs);
			}
			
		} catch (SQLException e) {
			System.out.println("Failed connecting to database in EmployeeDB. Cause "+ e.getMessage());
			e.printStackTrace();
		}
		
		return null;
	}
	
	//TODO update resultset get String to match actual database coloums. Not Finished.
	private Employee buildObject(ResultSet rs) {
		Employee employee = null;
		try {
			String name = rs.getString("name");
			String phone = rs.getString("phone");
		}
		catch (Exception e) {
			System.out.println("Failed connecting to database in EmployeeDB. Cause "+ e.getMessage());
			e.printStackTrace();
		}
		return employee;
	}
}
