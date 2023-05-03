package model;

/*
 * 19-04-2023: Rasmus and Mikkel - Reconstructed class to match DCD.
 */

public abstract class MasterData {
	
	private String name; 
	private String phone;
	private String email;
	private Address address;
	

	public MasterData() {
		this.name = "";
		this.phone = "";
		this.email = "";
		this.address = new Address();
	}
	
	public MasterData(String name, String phone, String email, Address address) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return getName() + " " + getPhone() + " " + getEmail() + " - " + getAddress().toString();
	}
	
	

	
}
