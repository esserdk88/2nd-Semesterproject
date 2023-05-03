package model;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Asset implements StringArrayConvertible{
	
	private int assetID;
	private String name;
	private String description;
	private Calendar aquisitionDate; //Maybe change back to Calendar
	private String status;
	private String manufacturer;
	private Location location;
	
	
	public Asset() {
		this.assetID = 0;
		this.name = "";
		this.description = "";
		this.aquisitionDate = Calendar.getInstance();
		this.status = "";
		this.manufacturer = "";
		this.location = new Location();
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
	
	public String getAquisitionDateString() {
		return calendarToString(aquisitionDate);
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
	
	public String calendarToString(Calendar calendar) {
		
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        
        String dateString = "Dato ikke sat";
        
        if(calendar != null) {
            dateString = dateFormat.format(calendar.getTime());
        }
        
         return dateString;
	}
	
	public String[] getObjectAsStringArray() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		return new String[] {Integer.toString(getAssetID()), getName(), dateFormat.format(getAquisitionDate().getTime()), getDescription(), getStatus(), getManufacturer()};
	}
	
	@Override
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Asset)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Asset m = (Asset) o;
         
        if (!this.getName().equals(m.getName())) {
        	return false;
        }
        if (!this.getDescription().equals(m.getDescription()) ) {
        	return false;
        }
        if (!this.getAquisitionDateString().equals(m.getAquisitionDateString())){
        	return false;
        }
        if (!this.getStatus().equals(m.getStatus())) {
        	return false;
        }
        if (!this.getManufacturer().equals(m.getManufacturer())) {
        	return false;
        }
        if (!this.getLocation().equals(m.getLocation())){
        	return false;
        }
        return true;
	}

	
}