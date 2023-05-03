package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 * 03-05-2023: Added equals method
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
	
	@Override
	public boolean equals(Object o) {
		// object variables

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Reference or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof Reference)) {
			return false;
		}

		// typecast o to Reference so that we can compare data members
		Reference r = (Reference) o;

		if (this.getCvr() != r.getCvr()) {
			return false;
		}
		if (!this.getContact().equals(r.getContact())) {
			return false;
		}
		if (!this.getName().equals(r.getName())) {
			return false;
		}
		if (!this.getPhone().equals(r.getPhone())) {
			return false;
		}
		if (!this.getEmail().equals(r.getEmail())){
			return false;
		}
		if (!this.getAddress().equals(r.getAddress())) {
			return false;
		}
		return true;
	}

	
	
}