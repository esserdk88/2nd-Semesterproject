package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Maintenance extends Workorder implements StringArrayConvertible {

	private boolean repeated;
	private int intervalDayCount;

	public Maintenance() {
		super();
		boolean repeated = false;
		int intervalDayCount = 0;
	}

	public Maintenance(boolean repeated, int intervalDayCount, int workOrderID, String title, String type,
			Calendar startDate, Calendar endDate, short priority, String description, boolean finished,
			List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
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

	@Override
	public String[] getObjectAsStringArray() {
		return new String[] { Integer.toString(getWorkOrderID()), getTitle(), getType(), calendarToString(getStartDate()), "",
				Short.toString(getPriority()), getDescription(), Boolean.toString(isFinished()),
				Integer.toString(getAsset().getAssetID()),
				(getEmployee() == null) ? "Ingen medarbejder" : getEmployee().getName() };
	}

	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Maintenance)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Maintenance m = (Maintenance) o;
         
        if (!this.getTitle().equals(m.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(m.getType()) ) {
        	System.out.println(this.getType() + " " + m.getType());
        	return false;
        }
        if (!this.getStartDateString().equals(m.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(m.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != m.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(m.getDescription())){
        	return false;
        }
        if (this.isFinished() != m.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(m.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(m.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && m.getEmployee() == null) || (this.getEmployee() == null && m.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && m.getEmployee() != null) && !this.getEmployee().equals(m.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(m.getMeasurements())){
        	return false;
        }
        if (this.isRepeated() != m.isRepeated()) {
        	return false;
        }
        if (this.getIntervalDayCount() != m.getIntervalDayCount()) {
        	return false;
        }
        return true;
	}

}