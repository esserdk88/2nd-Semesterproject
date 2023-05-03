package model;

import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Service extends Workorder{
	
	private Reference reference;
	
	
	public Service() {
		super();
	}

	public Service(Reference reference, int workOrderID, String title, String type, Calendar startDate, 
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed, 
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, 
				sparepartsUsed, asset, employee, measurements);
		this.reference = reference;
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
        if (!(o instanceof Service)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Service s = (Service) o;
        
        // god whyyyyy...??
         
        if (!this.getTitle().equals(s.getTitle())) {
        	return false;
        }
        if (!this.getType().equals(s.getType()) ) {
        	System.out.println(this.getType() + " " + s.getType());
        	return false;
        }
        //TODO: CALENDAR DATES!
        if (this.getPriority() != s.getPriority()) {
        	return false;
        }
        if (!this.getDescription().equals(s.getDescription())){
        	return false;
        }
        if (this.isFinished() != s.isFinished()) {
        	return false;
        }
        if (!this.getSparepartsUsed().equals(s.getSparepartsUsed())) {
        	return false;
        }
        if (!this.getAsset().equals(s.getAsset())){
        	return false;
        }
    	if(this.getEmployee() == null && s.getEmployee() == null) {
    		
    	} else {
    		return false;
    	}
        if ((this.getEmployee() != null && s.getEmployee() != null) && !this.getEmployee().equals(s.getEmployee())){
        	return false;
        }
        if (!this.getMeasurements().equals(s.getMeasurements())){
        	return false;
        }
        if(!this.getReference().equals(s.getReference())) {
        	return false;
        }
        return true;
	}
	
}