package model;

import java.util.Calendar;
import java.util.List;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public class Service extends Workorder implements StringArrayConvertible {

	private Reference reference;

	public Service() {
		super();
	}

	public Service(Reference reference, int workOrderID, String title, String type, Calendar startDate,
			Calendar endDate, short priority, String description, boolean finished, List<SparepartUsed> sparepartsUsed,
			Asset asset, Employee employee, List<Measurement> measurements) {
		super(workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset,
				employee, measurements);
		this.reference = reference;
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