package model;

import java.util.Calendar;
import java.util.List;

public class Asset {
	
	//new DCD fields
	private Address address;
	private Calendar aquisitionDate;
	private String manufacturer;
	private String status;
	private int assetID;
	private String name;
	private String description;
	private Location location;
	
	//Old fields
	private Calendar dateOfAcquire;
	private AssetStatus statusOLD;
	private String manufacturerName;
	private List<Workorder> tasks;
	
	public Asset(int id, String name, Location location, Calendar aquisitionDate, String description,
			String status, String manufacturer) {
		this.assetID = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.status = status;
		this.description = description;
		this.aquisitionDate = aquisitionDate;
		this.location = location;
	}
	
	//modular constructor for possible future use
	public Asset(int id, String name, Location location, Calendar aquisitionDate, String description,
			String status, String manufacturer, Address address) {
		this.assetID = id;
		this.name = name;
		this.manufacturer = manufacturer;
		this.status = status;
		this.description = description;
		this.aquisitionDate = aquisitionDate;
		this.location = location;
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Calendar getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(Calendar aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getAssetID() {
		return assetID;
	}

	public void setAssetID(int assetID) {
		this.assetID = assetID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Calendar getDateOfAcquire() {
		return dateOfAcquire;
	}

	public void setDateOfAcquire(Calendar dateOfAcquire) {
		this.dateOfAcquire = dateOfAcquire;
	}

	public AssetStatus getStatusOLD() {
		return statusOLD;
	}

	public void setStatusOLD(AssetStatus statusOLD) {
		this.statusOLD = statusOLD;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public List<Workorder> getTasks() {
		return tasks;
	}

	public void setTasks(List<Workorder> tasks) {
		this.tasks = tasks;
	}
	
}
