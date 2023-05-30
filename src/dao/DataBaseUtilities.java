package dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 * The class provides utility methods for checking database results and converting between SQL dates
 * and calendars in Java.
 */
public class DataBaseUtilities {

	// Should return true if successful
	/**
	 * The function checks if a ResultSet contains a non-zero value for a specified column with an
	 * optional prefix.
	 * 
	 * @param rs a ResultSet object that contains the results of a database query
	 * @param prefix A string that is added before the column name to form the full column name. It can be
	 * null if no prefix is needed.
	 * @param columnName The name of the column in the ResultSet that needs to be checked.
	 * @return The method is returning a boolean value indicating whether the ResultSet rs contains a
	 * non-zero value for the specified column name with the given prefix.
	 */
	public static boolean check(ResultSet rs, String prefix, String columnName) throws SQLException {
		boolean success = true;
		if (prefix == null) {
			prefix = "";
		}

		boolean check = false;
		int count = rs.getMetaData().getColumnCount();
		for (int i = 1; i <= count; i++) {
			if (rs.getMetaData().getColumnName(i).equals(prefix + columnName)) {
				check = true;
			}
		}
		if (!check || rs.getInt(prefix + columnName) == 0) {
			success = false;
		}
		return success;
	}

	/**
	 * This Java function converts a Calendar object to a SQL Date object.
	 * 
	 * @param cal A Calendar object that represents a specific date and time.
	 * @return A java.sql.Date object is being returned.
	 */
	public static Date convertCalendarToSqlDate(Calendar cal) {
		if (cal == null) {
			return null;
		}
		long timeInMillis = cal.getTimeInMillis();
		return new Date(timeInMillis);
	}

	/**
	 * This function converts a SQL date to a calendar object in Java.
	 * 
	 * @param sqlDate a java.sql.Date object that needs to be converted to a java.util.Calendar object.
	 * @return A Calendar object is being returned.
	 */
	public static Calendar convertSqlDateToCalendar(Date sqlDate) {
		Calendar calendar = Calendar.getInstance();
		if (sqlDate != null) {
			calendar.setTime(sqlDate);
		} else {
			calendar = null;
		}

		return calendar;
	}
}
