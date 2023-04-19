package model;

import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Maintenance extends Workorder {
	
	private boolean repeated;
	private int intervalDayCount;
	
	public Maintenance() {
		super();
	}

	public Maintenance(boolean repeated, int intervalDayCount, int workOrderID, String title, String type, 
			Calendar startDate, Calendar endDate, short priority, String description, boolean finished, 
			List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee,
			List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, 
				finished, sparepartsUsed, asset, employee, measurements);
		this.repeated = repeated;
		this.intervalDayCount = intervalDayCount;
	}

	public boolean isRepeated() {
		return repeated;
	}

	public void setRepeated(boolean repeated) {
		this.repeated = repeated;
	}

	public int getIntervalDayCount() {
		return intervalDayCount;
	}

	public void setIntervalDayCount(int intervalDayCount) {
		this.intervalDayCount = intervalDayCount;
	}

}