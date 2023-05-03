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
	
	@Override
	public boolean equals(Object o) {
		// object variables

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Complex or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof Reference)) {
			return false;
		}

		// typecast o to Complex so that we can compare data members
		Reference r = (Reference) o;

		// god whyyyyy...??
		// private int employeeID;
		// private String cprNumber;
		// private Calendar startDate;
		// private String position;
		// private String name;
		// private String phone;
		// private String email;
		// private Address address;

		if (this.getCvr() != r.getCvr()) {
			return false;
		}
		// TODO: CALENDAR DATES!
		// if (!this.getStartDate().equals(m.getStartDate()) ) {
		// return false;
		// }
		if (!this.getAddress().equals(r.getAddress())) {
			return false;
		}
		if (!this.getContact().equals(r.getContact())) {
			return false;
		}
		if (!this.getEmail().equals(r.getEmail())){
			return false;
		}
		if (!this.getName().equals(r.getName())) {
			return false;
		}
		if (!this.getPhone().equals(r.getPhone())) {
			return false;
		}
		return true;
	}
	
}