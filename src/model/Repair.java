package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Repair extends Workorder implements StringArrayConvertible {

	private double price;
	private Reference reference;

	public Repair() {
		super();
	}

	public Repair(double price, Reference reference, int workOrderID, String title, String type, Calendar startDate,
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed,
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
		this.price = price;
		this.reference = reference;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Reference getReference() {
		return reference;
	}

	public void setReference(Reference reference) {
		this.reference = reference;
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
        if (!(o instanceof Repair)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Repair r = (Repair) o;
         
        if (!this.getTitle().equals(r.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(r.getType()) ) {
        	return false;
        }
        if (!this.getStartDateString().equals(r.getStartDateString()) ) {
			return false;
		}
        if (!this.getEndDateString().equals(r.getEndDateString()) ) {
			return false;
		}
        if (this.getPriority() != r.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(r.getDescription())){
        	return false;
        }
        if (this.isFinished() != r.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(r.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(r.getAsset())){
        	return false;
        }
        if ((this.getEmployee() != null && r.getEmployee() == null) || (this.getEmployee() == null && r.getEmployee() != null)) {
            return false;
        }
        if ((this.getEmployee() != null && r.getEmployee() != null) && !this.getEmployee().equals(r.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(r.getMeasurements())){
        	return false;
        }
        if(!this.getReference().equals(r.getReference())) {
        	return false;
        }
        if(this.getPrice() != r.getPrice()) {
        	return false;
        }
        return true;
	}

	@Override
	public String[] getObjectAsStringArray() {
		return new String[] { Integer.toString(getWorkOrderID()), getTitle(), getType(), calendarToString(getStartDate()), "",
				Short.toString(getPriority()), getDescription(), Boolean.toString(isFinished()),
				Integer.toString(getAsset().getAssetID()),
				(getEmployee() == null) ? "Ingen medarbejder" : getEmployee().getName() };
	}

}