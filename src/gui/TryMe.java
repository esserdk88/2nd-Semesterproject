package gui;

import java.sql.Connection;
import java.sql.SQLException;

import dal.*;
import model.Department;

public class TryMe {

	public static void main(String[] args) {
		//DAL
		DepartmentDBIF ddb = new DepartmentDB();
		
		try {
			Connection con = DatabaseConnection.getInstance().getConnection();
			System.out.println(con.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Department d : ddb.getAllDepartments()) {
			System.out.println(d.toString());
		}
		
		
		
		

	}

}
