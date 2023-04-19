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
	
}