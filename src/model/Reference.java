package model;

public class Reference {
	
	private String cvr;
	private String name;
	private String phone;
	private String email;
	private String contact;
	private Address address;
	
	public Reference(String cvr, String name, String phone, String email, String contact, Address address) {
		this.cvr = cvr;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.contact = contact;
		this.address = address;
	}

	public String getCvr() {
		return cvr;
	}

	public void setCvr(String cvr) {
		this.cvr = cvr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	

}
