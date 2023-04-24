package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Asset {
	
	private int assetID;
	private String name;
	private String description;
	private Calendar aquisitionDate; //Maybe change back to Calendar
	private String status;
	private String manufacturer;
	private Location location;
	
	
	public Asset() {
		//Left empty.
	}
	
	public Asset(int assetID, String name, String description, Calendar aquisitionDate, String status,
			String manufacturer, Location location) {
		this.assetID = assetID;
		this.name = name;
		this.description = description;
		this.aquisitionDate = aquisitionDate;
		this.status = status;
		this.manufacturer = manufacturer;
		this.location = location;
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

	public Calendar getAquisitionDate() {
		return aquisitionDate;
	}

	public void setAquisitionDate(Calendar aquisitionDate) {
		this.aquisitionDate = aquisitionDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		
		return "" + getAssetID();
	}
}