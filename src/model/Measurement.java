package model;

/*
 * 19-04-2023: Reworked with alignment from DCD
 */

public class Measurement {
	
	private String title;
	private double value;
	
	
	public Measurement(String title, double value) {
		this.title = title;
		this.value = value;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	
}
