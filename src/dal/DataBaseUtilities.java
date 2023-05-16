package dal;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DataBaseUtilities {

	//Should return false if no problems
	public static boolean check(ResultSet rs, String prefix, String columnName) throws SQLException {
		boolean success = false;
		if(prefix == null) {
			prefix = "";
		}
		
		boolean check = false;
        int count = rs.getMetaData().getColumnCount();
        for (int i = 1; i <=count; i++) {
               if(rs.getMetaData().getColumnName(i).equals(prefix+columnName)) {
                  check = true;
               }
            }
        if(!check || rs.getInt(prefix+columnName) == 0) {
            success = true;
        }
		return success;
	}
}
