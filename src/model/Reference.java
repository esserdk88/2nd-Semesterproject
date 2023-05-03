package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

public class Reference extends MasterData {
	
	private int cvr;
	private String contact;
	
	public Reference() {
		super();
	}
	
	public Reference(int cvr, String contact, String name, String phone, String email, Address address) {
		super(name, phone, email, address);
		this.cvr = cvr;
		this.contact = contact;
	}

	public int getCvr() {
		return cvr;
	}

	public void setCvr(int cvr) {
		this.cvr = cvr;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "(" + getCvr() + ") " + getContact() + " " + super.toString();
	}
	
	
	
}