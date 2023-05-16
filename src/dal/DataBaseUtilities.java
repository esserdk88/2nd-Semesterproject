package dal;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;


public class DataBaseUtilities {

	//Should return false if no problems
	public static boolean check(ResultSet rs, String prefix, String columnName) throws SQLException {
		boolean success = true;
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
            success = false;
        }
		return success;
	}
	
	public static Date convertCalendarToSqlDate(Calendar cal) {
        long timeInMillis = cal.getTimeInMillis();
        return new Date(timeInMillis);
    }
	
	public static Calendar convertSqlDateToCalendar(Date sqlDate) { 
		Calendar calendar = Calendar.getInstance(); 
		if(sqlDate != null) {
			calendar.setTime(sqlDate); 
		} else {
			calendar = null;
		}
		
		return calendar; 
	}
}
