package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public abstract class Workorder {
	
	private int workOrderID;
	private String title;
	private String type;
	private Calendar startDate;
	private Calendar endDate;
	private short priority;
	private String description;
	private boolean finished;
	
	private List<SparepartUsed> sparepartsUsed;
	private Asset asset;
	private Employee employee;
	private List<Measurement> measurements;
	
	public Workorder() {
		
		this.title = "";
		this.type = "";
		this.startDate = Calendar.getInstance();
		this.endDate = Calendar.getInstance();
		this.priority = 0;
		this.description = "";
		this.finished = false;
		
		this.sparepartsUsed = new ArrayList<>();
		this.asset = new Asset();
		this.employee = new Employee();
		this.measurements = new ArrayList<>();
		
	}

	public Workorder(int workOrderID, String title, String type, Calendar startDate, Calendar endDate, short priority,
			String description, boolean finished, List<SparepartUsed> sparepartsUsed, Asset asset, Employee employee,
			List<Measurement> measurements) {
		this.workOrderID = workOrderID;
		this.title = title;
		this.type = type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
		this.description = description;
		this.finished = finished;
		this.sparepartsUsed = sparepartsUsed;
		this.asset = asset;
		this.employee = employee;
		this.measurements = measurements;
	}

	public int getWorkOrderID() {
		return workOrderID;
	}

	public void setWorkOrderID(int workOrderID) {
		this.workOrderID = workOrderID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Calendar getStartDate() {
		return startDate;
	}
	
	public String getStartDateString() {
		return calendarToString(startDate);
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}
	
	public String getEndDateString() {
		return calendarToString(endDate);
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public short getPriority() {
		return priority;
	}

	public void setPriority(short priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public List<SparepartUsed> getSparepartsUsed() {
		return sparepartsUsed;
	}

	public void setSparepartsUsed(List<SparepartUsed> sparepartsUsed) {
		this.sparepartsUsed = sparepartsUsed;
	}

	public Asset getAsset() {
		return asset;
	}

	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public List<Measurement> getMeasurements() {
		return measurements;
	}

	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	public String formatPriority(short input) {
		String type = "";
		switch (input) {
		case 1:
			type = "Lav";
			break;
		case 2:
			type = "Mellem";
			break;
			
		case 3:
			type = "Høj";
			break;
		default:
		}
		return type;
	}

	@Override
	public String toString() {
		
		return "(" + getWorkOrderID() + ") " + getType() + ", " + getTitle() + ", " + calendarToString(getStartDate()) + ", " + 
				calendarToString(getEndDate()) + ", " + getPriority() + ", " + getDescription() + ", " + getAsset();
	}
	
	public String calendarToString(Calendar calendar) {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String dateString = "Dato ikke sat";
        
        if(calendar != null) {
            dateString = dateFormat.format(calendar.getTime());
        }
        
         return dateString;
	}
	
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Workorder or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Workorder)) {
            return false;
        }
         
        // typecast o to Workorder so that we can compare data members
        Workorder w = (Workorder) o;
         
        if (!this.getTitle().equals(w.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(w.getType()) ) {
        	return false;
        }
        if (!this.getStartDateString().equals(w.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(w.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != w.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(w.getDescription())){
        	return false;
        }
        if (this.isFinished() != w.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(w.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(w.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && w.getEmployee() == null) || (this.getEmployee() == null && w.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && w.getEmployee() != null) && !this.getEmployee().equals(w.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(w.getMeasurements())){
        	return false;
        }
        return true;
	}
}