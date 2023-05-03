package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - DCD vs. Code
 * Small adjustments done in code and DCD.
 */

public class Address {
	//TODO: does this need a field for the id from the DB
	
	private int addressID;
	private String cityName;
	private String zipCode;
	private String streetName;
	private String streetNumber;
	
	public Address() {
		this.cityName = "";
		this.zipCode = "";
		this.streetName = "";
		this.streetNumber = "";
	}
	
	public Address(int addressID, String cityName, String zipCode, String streetName, String streetNumber) {
		this.addressID = addressID;
		this.cityName = cityName;
		this.zipCode = zipCode;
		this.streetName = streetName;
		this.streetNumber = streetNumber;
	}

	public int getAddressID() {
		return addressID;
	}

	public void setAddressID(int addressID) {
		this.addressID = addressID;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNumber() {
		return streetNumber;
	}

	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	
	@Override
	public String toString() {
		
		return "(" + getAddressID() +") " + getStreetName() + " " + getStreetNumber() + " - " + getZipCode() + " " + getCityName();
	}
	
	@Override
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Address)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Address m = (Address) o;
        
        // god whyyyyy...??
//        private int addressID;
//    	private String cityName;
//    	private String zipCode;
//    	private String streetName;
//    	private String streetNumber;
         
        if (!this.getCityName().equals(m.getCityName())) {
        	return false;
        }
        if (!this.getZipCode().equals(m.getZipCode()) ) {
        	return false;
        }
        if (!this.getStreetName().equals(m.getStreetName())){
        	return false;
        }
        if (!this.getStreetNumber().equals(m.getStreetNumber())) {
        	return false;
        }
        return true;
	}

}