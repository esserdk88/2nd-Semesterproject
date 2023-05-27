package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

/**
 * The Workorder class represents a work order with various attributes such as ID, title, type, start
 * and end dates, priority, description, and related objects such as spare parts used, assets,
 * employees, and measurements.
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
	
	// This is a default constructor for the `Workorder` class. It initializes all the instance variables
	// to default values. The `title`, `type`, and `description` are set to empty strings, `startDate` and
	// `endDate` are set to the current date and time using the `Calendar.getInstance()` method,
	// `priority` is set to 0, `finished` is set to `false`, and the `sparepartsUsed` and `measurements`
	// lists are initialized as empty `ArrayList`s. The `asset` and `employee` objects are also
	// initialized with their default constructors.
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

	// This is a constructor for the `Workorder` class that takes in several parameters to initialize the
	// instance variables of a `Workorder` object. The parameters include `workOrderID`, `title`, `type`,
	// `startDate`, `endDate`, `priority`, `description`, `finished`, `sparepartsUsed`, `asset`,
	// `employee`, and `measurements`. The constructor sets the values of these parameters to the
	// corresponding instance variables of the `Workorder` object using the `this` keyword.
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

	/**
	 * This function returns the work order ID as an integer.
	 * 
	 * @return The method is returning an integer value which represents the work order ID.
	 */
	public int getWorkOrderID() {
		return workOrderID;
	}

	/**
	 * This function sets the work order ID for a particular object.
	 * 
	 * @param workOrderID The parameter `workOrderID` is an integer value that represents the unique
	 * identifier of a work order. The `setWorkOrderID` method is used to set the value of this identifier
	 * for a particular instance of a class.
	 */
	public void setWorkOrderID(int workOrderID) {
		this.workOrderID = workOrderID;
	}

	/**
	 * The function returns the title of an object.
	 * 
	 * @return The method `getTitle()` is returning a `String` value, which is the value of the `title`
	 * variable.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * This Java function sets the title of an object.
	 * 
	 * @param title The parameter "title" is a String type variable that represents the title of an
	 * object. The method "setTitle" sets the value of the title variable to the value passed as an
	 * argument to the method.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * The function returns a string representing the type.
	 * 
	 * @return The method is returning a String value, which is the value of the variable "type".
	 */
	public String getType() {
		return type;
	}

	/**
	 * This function sets the type of an object in Java.
	 * 
	 * @param type The parameter "type" is a String data type that represents the type of an object or
	 * variable. The method "setType" sets the value of the "type" instance variable to the value passed
	 * as a parameter.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * The function returns the start date of a calendar.
	 * 
	 * @return The method `getStartDate()` is returning a `Calendar` object representing the start date.
	 */
	public Calendar getStartDate() {
		return startDate;
	}
	
	/**
	 * The function returns a string representation of a calendar start date.
	 * 
	 * @return A string representation of the start date, which is obtained by calling the method
	 * `calendarToString()` on the `startDate` object.
	 */
	public String getStartDateString() {
		return calendarToString(startDate);
	}

	/**
	 * This function sets the start date of an object using a Calendar object.
	 * 
	 * @param startDate startDate is a variable of type Calendar that is being set by the method
	 * setStartDate. The value passed as an argument to this method will be assigned to the startDate
	 * variable.
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * The function returns the end date of a calendar.
	 * 
	 * @return The method is returning a Calendar object named "endDate".
	 */
	public Calendar getEndDate() {
		return endDate;
	}
	
	/**
	 * The function returns a string representation of the end date.
	 * 
	 * @return A string representation of the end date in the format specified by the `calendarToString`
	 * method.
	 */
	public String getEndDateString() {
		return calendarToString(endDate);
	}

	/**
	 * This function sets the end date of a calendar object.
	 * 
	 * @param endDate The parameter "endDate" is a Calendar object that represents the end date of a
	 * certain event or task. This method sets the value of the "endDate" instance variable to the value
	 * of the "endDate" parameter.
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * The function returns a short value representing the priority.
	 * 
	 * @return The method is returning a short value, which is the value of the variable "priority".
	 */
	public short getPriority() {
		return priority;
	}

	/**
	 * This function sets the priority of an object to a given short value.
	 * 
	 * @param priority The parameter "priority" is a short data type that represents the priority level of
	 * a task or an item. This method sets the priority of an object to the value passed as an argument.
	 */
	public void setPriority(short priority) {
		this.priority = priority;
	}

	/**
	 * This function returns the description of an object as a string.
	 * 
	 * @return The method `getDescription()` is returning a `String` value, which is the value of the
	 * variable `description`.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * This function sets the description of an object.
	 * 
	 * @param description The parameter "description" is a String variable that represents the description
	 * of an object or entity. The method "setDescription" sets the value of the description variable to
	 * the value passed as an argument to the method.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * The function returns a boolean value indicating whether a task is finished or not.
	 * 
	 * @return A boolean value is being returned. The value of the variable "finished" is being returned.
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * This function sets the value of a boolean variable called "finished".
	 * 
	 * @param finished A boolean variable that represents whether a task or process has been completed or
	 * not. If the value is true, it means that the task has been finished, and if the value is false, it
	 * means that the task is still ongoing or has not yet started. The method setFinished() is used to
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * The function returns a list of SparepartUsed objects.
	 * 
	 * @return A List of SparepartUsed objects is being returned.
	 */
	public List<SparepartUsed> getSparepartsUsed() {
		return sparepartsUsed;
	}

	/**
	 * This function sets the list of spare parts used in a certain process.
	 * 
	 * @param sparepartsUsed A list of objects of type SparepartUsed that will be set as the value of the
	 * instance variable "sparepartsUsed".
	 */
	public void setSparepartsUsed(List<SparepartUsed> sparepartsUsed) {
		this.sparepartsUsed = sparepartsUsed;
	}

	/**
	 * The function returns an asset object.
	 * 
	 * @return The method is returning an object of type Asset.
	 */
	public Asset getAsset() {
		return asset;
	}

	/**
	 * This function sets the value of the "asset" variable to the input "asset" object.
	 * 
	 * @param asset The parameter "asset" is an object of the class "Asset". The method "setAsset" sets
	 * the value of the instance variable "asset" to the value of the parameter "asset".
	 */
	public void setAsset(Asset asset) {
		this.asset = asset;
	}

	/**
	 * The function returns an object of type Employee.
	 * 
	 * @return The method is returning an object of the class Employee.
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * This function sets the value of the "employee" variable to the provided "Employee" object.
	 * 
	 * @param employee The parameter "employee" is an object of the class "Employee". The method
	 * "setEmployee" sets the value of the instance variable "employee" to the value passed as the
	 * parameter.
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * This function returns a list of measurements.
	 * 
	 * @return A List of Measurement objects is being returned.
	 */
	public List<Measurement> getMeasurements() {
		return measurements;
	}

	/**
	 * This function sets the list of measurements for a given object.
	 * 
	 * @param measurements The "measurements" parameter is a List of objects of type "Measurement". This
	 * method sets the value of the instance variable "measurements" to the value of the parameter
	 * "measurements".
	 */
	public void setMeasurements(List<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	/**
	 * The function takes a short input and returns a string indicating the priority level.
	 * 
	 * @param input a short integer representing the priority level of a task, where 1 is low, 2 is
	 * medium, and 3 is high.
	 * @return The method is returning a String value that represents the priority level based on the
	 * input parameter. The returned value could be "Lav", "Mellem", or "Høj" depending on the value of
	 * the input parameter. If the input parameter is not 1, 2, or 3, then an empty String will be
	 * returned.
	 */
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

	/**
	 * This function returns a string representation of a work order object.
	 * 
	 * @return A string representation of a work order object, including its ID, type, title, start and
	 * end dates, priority, description, and associated asset.
	 */
	@Override
	public String toString() {
		
		return "(" + getWorkOrderID() + ") " + getType() + ", " + getTitle() + ", " + calendarToString(getStartDate()) + ", " + 
				calendarToString(getEndDate()) + ", " + getPriority() + ", " + getDescription() + ", " + getAsset();
	}
	
	/**
	 * The function converts a Calendar object to a formatted string representing the date.
	 * 
	 * @param calendar A Calendar object representing a specific date and time.
	 * @return The method returns a string representation of the date in the format "dd/MM/yyyy". If the
	 * input calendar is null, the method returns the string "Dato ikke sat" (which means "Date not set"
	 * in Danish).
	 */
	public String calendarToString(Calendar calendar) {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String dateString = "Dato ikke sat";
        
        if(calendar != null) {
            dateString = dateFormat.format(calendar.getTime());
        }
        
         return dateString;
	}
	
	/**
	 * This is an implementation of the equals method in Java that compares the data members of two
	 * Workorder objects and returns true if they are equal.
	 * 
	 * @param o The object being compared to the current Workorder object for equality.
	 * @return A boolean value is being returned, either true or false, depending on whether the object
	 * being compared to the current object is equal or not.
	 */
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