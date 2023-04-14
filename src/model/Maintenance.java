package model;

import java.util.Calendar;

public class Maintenance extends Workorder {
	
	private boolean repeated;
	private int intervalDayCount;
	
	public Maintenance(Asset asset, String title, String description, boolean repeated, int intervalDayCount) {
		super(asset, title, "Maintenance", description); 
		this.repeated = repeated;
		this.intervalDayCount = intervalDayCount;
	}
	
	public Maintenance(int id, Asset a, String title, String description, boolean finished, Calendar startDate, Calendar endDate, 
			short priority, boolean repeaded, int intervalDayCount) {
		super(id, a, title, "Maintenance", description, finished, startDate, endDate, priority);
		this.repeated = repeaded;
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
