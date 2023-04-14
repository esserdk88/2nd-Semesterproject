package model;

import java.util.Calendar;

public class Repair extends Workorder {
	
	private double price;
	private Reference reference;
	
	public Repair(Asset a, String title, String description, double price, Reference reference) {
		super(a, title, "Repair", description);
		this.price = price;
		this.reference = reference;
	}
	
	public Repair(int id, Asset a, String title, String description, boolean finished, Calendar startDate,
	Calendar endDate, short priority, double price, Reference ereference) {
		super(id, a, title, "Repair", description, finished, startDate, endDate, priority);
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
