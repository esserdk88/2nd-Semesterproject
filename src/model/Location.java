package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - DCD vs. Code.
 * No changes done.
 */

/**
 * The Location class represents a physical location with a unique ID, building, floor, room, and
 * address.
 */
public class Location {
	
	private int locationID;
	private String building;
	private String floor;
	private String room;
	private Address address;
	
	
	// This is a default constructor for the `Location` class. It initializes the `building`, `floor`, and
	// `room` variables to empty strings and creates a new `Address` object with default values.
	public Location() {
		this.building = "";
		this.floor = "";
		this.room = "";
		this.address = new Address();
	}
	
	// This is a constructor for the `Location` class that takes in five parameters: `locationID`,
	// `building`, `floor`, `room`, and `address`. It initializes the corresponding instance variables of
	// the `Location` object with the values passed in as arguments.
	public Location (int locationID, String building, String floor, String room, Address address){
		this.locationID = locationID;
		this.building = building;
		this.floor = floor;
		this.room = room;
		this.address = address;
	}

	
	/**
	 * This function returns the location ID as an integer.
	 * 
	 * @return The method is returning an integer value which represents the location ID.
	 */
	public int getLocationID() {
		return locationID;
	}

	/**
	 * This function sets the location ID of an object.
	 * 
	 * @param locationID locationID is an integer parameter that represents the unique identifier of a
	 * location. This method sets the value of the locationID instance variable to the value passed as an
	 * argument.
	 */
	public void setLocationID(int locationID) {
		this.locationID = locationID;
	}

	/**
	 * The function returns an object of type Address.
	 * 
	 * @return The method `getAddress()` is returning an object of the `Address` class.
	 */
	public Address getAddress() {
		return address;
	}
	
	/**
	 * This function sets the address of an object.
	 * 
	 * @param address The parameter "address" is an object of the class "Address". The method "setAddress"
	 * sets the value of the instance variable "address" to the value of the parameter "address".
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * The function returns the value of the "building" variable as a string.
	 * 
	 * @return The method is returning a String value, which is the value of the variable "building".
	 */
	public String getBuilding() {
		return building;
	}
	
	/**
	 * This is a Java function that sets the value of a variable called "building".
	 * 
	 * @param building The parameter "building" is a String variable that represents the name or
	 * identifier of a building. The method "setBuilding" sets the value of the instance variable
	 * "building" to the value passed as a parameter.
	 */
	public void setBuilding(String building) {
		this.building = building;
	}
	
	/**
	 * The function returns the value of the "floor" variable as a string.
	 * 
	 * @return The method `getFloor()` is returning a `String` variable named `floor`.
	 */
	public String getFloor() {
		return floor;
	}
	
	/**
	 * This is a Java function that sets the value of a variable called "floor".
	 * 
	 * @param floor The parameter "floor" is a String variable that represents the floor of a building or
	 * a room. The "setFloor" method is used to set the value of the "floor" variable to a new value
	 * passed as an argument to the method.
	 */
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	/**
	 * The function returns the value of the "room" variable as a string.
	 * 
	 * @return The method is returning a String value, which is the value of the variable "room".
	 */
	public String getRoom() {
		return room;
	}
	
	/**
	 * This is a Java function that sets the value of a variable called "room".
	 * 
	 * @param room The parameter "room" is a String variable that represents the name or identifier of a
	 * room. The method "setRoom" sets the value of the instance variable "room" to the value passed as a
	 * parameter.
	 */
	public void setRoom(String room) {
		this.room = room;
	}

	/**
	 * This function returns a string representation of a location object with its ID, building, floor,
	 * room, and address.
	 * 
	 * @return A string representation of a location object, which includes the location ID, building
	 * name, floor number, room number, and address.
	 */
	@Override
	public String toString() {
		
		
		return "(" + getLocationID() + ") " + getBuilding() + " " + getFloor() + " " + getRoom() + " - " + getAddress();
	}
	
	/**
	 * This is an implementation of the equals() method in Java that compares the data members of two
	 * Location objects for equality.
	 * 
	 * @param o The object being compared to the current Location object for equality.
	 * @return A boolean value is being returned, indicating whether the object being compared to is equal
	 * to the current object or not.
	 */
	@Override
	public boolean equals(Object o) {
		//object variables

        // If the object is compared with itself then return true 
        if (o == this) {
            return true;
        }
 
        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(o instanceof Location)) {
            return false;
        }
         
        // typecast o to Complex so that we can compare data members
        Location m = (Location) o;
         
        if (!this.getBuilding().equals(m.getBuilding())) {
        	return false;
        }
        if (!this.getFloor().equals(m.getFloor()) ) {
        	return false;
        }
        if (!this.getRoom().equals(m.getRoom())){
        	return false;
        }
        if (!this.getAddress().equals(m.getAddress())) {
        	return false;
        }
        return true;
	}
}