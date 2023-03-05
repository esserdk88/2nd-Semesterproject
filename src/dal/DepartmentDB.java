package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Department;

public class DepartmentDB implements DepartmentDBIF {
	
	//SQL
	private String COLUMNS = "Department_Streetname,Department_Streetnumber,Department_Postalcode,Department_Cityname";
	private String TABLE = "Department";
	
	private String INSERT = "insert into "+TABLE+" ("+COLUMNS+") VALUES(?,?,?,?)";
	private String SELECT = "SELECT "+"Department_Id,"+COLUMNS+" FROM "+TABLE+" WHERE Department_Id = ?";
	private String UPDATE = "UPDATE "+TABLE+" SET Department_Streetname=?, Department_Streetnumber=?,Department_Postalcode=?, Department_Cityname=? WHERE Department_Id=?";
	private String DELETE = "DELETE FROM "+TABLE+" WHERE Department_Id=?";
	
	private String SELECT_ALL = "SELECT "+"Department_Id,"+COLUMNS+" FROM "+TABLE;
	
	public boolean createDepartment(Department d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = INSERT;
        boolean success = false;

        try {
            // Opret en forbindelse til databasen
            conn = DatabaseConnection.getInstance().getConnection();            		

            // Opret en forberedt udsagn for at indsætte et nyt department
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, d.getStreetName());
            stmt.setInt(2, d.getStreetNumber());
            stmt.setString(3, d.getPostalcode());
            stmt.setString(4, d.getCityName());

            // Udfør indsættelsen
            stmt.executeUpdate();
            success = true;
            System.out.println("Department blev indsat i databasen.");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Luk ressourcer
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

	@Override
	// Henter et Department objekt fra databasen baseret på id
	public Department getDepartmentByID(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Department department = null;
        String sql = SELECT;

        try {
            // Opret en forbindelse til databasen
        	conn = DatabaseConnection.getInstance().getConnection();

            // Opret en forberedt udsagn for at hente departmenten baseret på id
            stmt = conn.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, id);

            // Udfør forespørgslen
            rs = stmt.executeQuery();

            // Behandl resultatet, hvis der findes et matchende Department
            department = buildObject(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Luk ressourcer
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return department;
    }

	@Override
	// Opdaterer et Department-objekt i databasen
    public boolean updateDepartment(Department d) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = UPDATE;
        boolean success = false;

        try {
            // Opret en forbindelse til databasen
        	conn = DatabaseConnection.getInstance().getConnection();

            // Opret et forberedt udsagn for at opdatere departmenten
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, d.getStreetName());
            stmt.setInt(2, d.getStreetNumber());
            stmt.setString(3, d.getPostalcode());
            stmt.setString(4, d.getCityName());
            stmt.setInt(5, d.getId());

            // Udfør opdateringen
            stmt.executeUpdate();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Luk ressourcer
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

	@Override
    // Sletter et Department-objekt fra databasen
    public boolean deleteDepartment(Department department) {
        Connection conn = null;
        PreparedStatement stmt = null;
        String sql = DELETE;
        boolean success = false;

        try {
            // Opret en forbindelse til databasen
        	conn = DatabaseConnection.getInstance().getConnection();

            // Opret et forberedt udsagn for at slette departmenten
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, department.getId());

            // Udfør sletningen
            stmt.executeUpdate();
            success = true;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Luk ressourcer
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return success;
    }

	@Override
	public List<Department> getAllDepartments() {
		Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Department> list = new ArrayList<>();
        String sql = SELECT_ALL;

        try {
            // Opret en forbindelse til databasen
        	conn = DatabaseConnection.getInstance().getConnection();

            // Opret en forberedt udsagn for at hente departmenten baseret på id
            stmt = conn.prepareStatement(sql);

            // Udfør forespørgslen
            rs = stmt.executeQuery();

            // Behandl resultatet, hvis der findes et matchende Department
            list = buildObjects(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                // Luk ressourcer
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return list;
	}
	
	
	
	private List<Department> buildObjects(ResultSet rs) throws SQLException {
		List<Department> outputList = new ArrayList<>();
		while(rs.next()) {
			String streetname = rs.getString("Department_Streetname");
			int streetnumber = rs.getInt("Department_Streetnumber");
			String postalcode = rs.getString("Department_Postalcode");
			String cityname = rs.getString("Department_Cityname");
			int id = rs.getInt("Department_Id");
			Department d = new Department(id, streetname, streetnumber, postalcode, cityname);
			outputList.add(d);
		}
		return outputList;
	}

	private Department buildObject(ResultSet rs) throws SQLException {
		Department department = null;
		if (rs.next()) {
			String streetname = rs.getString("Department_Streetname");
			int streetnumber = rs.getInt("Department_Streetnumber");
			String postalcode = rs.getString("Department_Postalcode");
			String cityname = rs.getString("Department_Cityname");
			int id = rs.getInt("Department_Id");
			department = new Department(id, streetname, streetnumber, postalcode, cityname);
		}
		return department;
	}

}
