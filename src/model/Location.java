package model;

public class Location {
	
	private String building;
	private String floor;
	private String room;
	private Address address;
	
	//Construtor for Location-class
	public Location (String building, String floor, String room, Address address){
		this.building = building;
		this.floor = floor;
		this.room = room;
		this.address = address;
	}
	
	public Address getAddress() {
		return address;
	}
	
	public void setAddress(Address address) {
		this.address = address;
	}
	
	public String getBuilding() {
		return building;
	}
	
	public void setBuilding(String building) {
		this.building = building;
	}
	
	public String getFloor() {
		return floor;
	}
	
	public void setFloor(String floor) {
		this.floor = floor;
	}
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}

}