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
	public String[] getObjectAsStringArray() {
		// TODO Auto-generated method stub
		return new String[] { Integer.toString(getWorkOrderID()), getTitle(), getType(), calendarToString(getStartDate()), "",
				Short.toString(getPriority()), getDescription(), Boolean.toString(isFinished()),
				Integer.toString(getAsset().getAssetID()),
				(getEmployee() == null) ? "Ingen medarbejder" : getEmployee().getName() };
	}

}