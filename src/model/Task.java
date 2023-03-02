package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public abstract class Task {

	private String taskID;
	private Date startDate;
	private Date finishDate;
	private TaskStatus status;
	private String description;
	private int priority;
	private List<String> notes;
	
	//Assosiations
	private List<SparePart> spareParts;
	private Technician assignedTechnician;
	private List<Measurement> measurements;
	
	//Constructor
	//Full constructor, this will be used to create this model from the database.
	public Task(String taskID, Date startDate, Date finishDate, TaskStatus status, String description, int priority,
			List<String> notes, List<SparePart> spareParts, Technician assignedTechnician,
			List<Measurement> measurements) {
		this.taskID = taskID;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.status = status;
		this.description = description;
		this.priority = priority;
		this.notes = notes;
		
		this.spareParts = spareParts;
		this.assignedTechnician = assignedTechnician;
		this.measurements = measurements;
	}
	
	//Getters and Setters
	public String getTaskID() {
		return taskID;
	}
	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public TaskStatus getStatus() {
		return status;
	}
	public void setStatus(TaskStatus status) {
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
	public void setPriority(int priority) {
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
	public Technician getAssignedTechnician() {
		return assignedTechnician;
	}
	public void setAssignedTechnician(Technician assignedTechnician) {
		this.assignedTechnician = assignedTechnician;
	}
	public List<Measurement> getMeasurements() {
		return measurements;
	}
	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	
	
	

}
