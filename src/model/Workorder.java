package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public abstract class Workorder {
	
	//TODO: Add private String title;

	private int workOrderID; //changed from taskId to workOrderID. Datatype also changed to int from String
	private Calendar startDate;
	private Calendar endDate; //changed from finishedDate to endDate;
	private WorkorderStatus status;
	private String description;
	private short  priority; //datatype changed from int to short
	private List<String> notes;
	
	//DCD implemented fields
	//Old fields mey need to be removed
	private Asset asset;
	private String title;
	private String type;
	private boolean finished;
	
	//Assosiations
	private List<SparePart> spareParts;
	private Employee assignedTechnician;
	private List<Measurement> measurements;
	
	//Constructor
	//Full constructor, this will be used to create this model from the database.
	public Workorder(int taskID, Calendar startDate, Calendar finishDate, WorkorderStatus status, String description, short priority,
			List<String> notes, List<SparePart> spareParts, Employee assignedTechnician,
			List<Measurement> measurements) {
		this.workOrderID = taskID;
		this.startDate = startDate;
		this.endDate = finishDate;
		this.status = status;
		this.description = description;
		this.priority = priority;
		this.notes = notes;
		
		this.spareParts = spareParts;
		this.assignedTechnician = assignedTechnician;
		this.measurements = measurements;
	}
	
	public Workorder(Asset asset, String title, String type, String description) {
		this.asset = asset;
		this.title = title;
		this.type = type;
		this.description = description;
	}
	
	public Workorder(int id, Asset asset, String title, String type, String description, boolean finished,
			Calendar startDate, Calendar endDate, short priority) {
		this.workOrderID = id;
		this.asset = asset;
		this.title = title;
		this.type = type;
		this.description = description;
		this.finished = finished;
		this.startDate = startDate;
		this.endDate = endDate;
		this.priority = priority;
	}
	
	
	
	public Calendar getEndDate() {
		return endDate;
	}
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	public Calendar getStartDate() {
		return startDate;
	}
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}
	public WorkorderStatus getStatus() {
		return status;
	}
	public void setStatus(WorkorderStatus status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(short priority) {
		this.priority = priority;
	}
	public List<String> getNotes() {
		return notes;
	}
	public void setNotes(List<String> notes) {
		this.notes = notes;
	}
	public List<SparePart> getSpareParts() {
		return spareParts;
	}
	public void setSpareParts(List<SparePart> spareParts) {
		this.spareParts = spareParts;
	}
	public Employee getAssignedTechnician() {
		return assignedTechnician;
	}
	public void setAssignedTechnician(Employee assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}
	public List<Measurement> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	
	
	

}
