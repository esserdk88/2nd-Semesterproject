package model;

import java.util.Calendar;
import java.util.List;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Repair extends Workorder {
	
	private double price;
	private Reference reference;
	
	public Repair() {
		super();
	}

	public Repair(double price, Reference reference, int workOrderID, String title, String type, Calendar startDate, 
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed, 
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset, employee, measurements);
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
	
	
	
}