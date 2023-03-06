package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class MaintenanceTask extends Workorder {

	private boolean repeatable;
	private int repeatIntervalDay;
	private int repeatIntervalMonth;
	
	public MaintenanceTask(String taskID, Calendar startDate, Calendar finishDate, WorkorderStatus status, String description,
			int priority, List<String> notes, List<SparePart> spareParts, Employee assignedTechnician,
			List<Measurement> measurements, boolean repeatable, int repeatIntervalDay, int repeatIntervalMonth) {
		super(taskID, startDate, finishDate, status, description, priority, notes, spareParts, assignedTechnician,
				measurements);
		this.repeatable = repeatable;
		this.repeatIntervalDay = repeatIntervalDay;
		this.repeatIntervalMonth = repeatIntervalMonth;
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public int getRepeatIntervalDay() {
		return repeatIntervalDay;
	}

	public void setRepeatIntervalDay(int repeatIntervalDay) {
		this.repeatIntervalDay = repeatIntervalDay;
	}

	public int getRepeatIntervalMonth() {
		return repeatIntervalMonth;
	}

	public void setRepeatIntervalMonth(int repeatIntervalMonth) {
		this.repeatIntervalMonth = repeatIntervalMonth;
	}
	
	
	
	
	
	
	
}
