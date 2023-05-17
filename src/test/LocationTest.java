package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Location;

class LocationTest {
	
	private Address address;
	private Location location;

	@BeforeEach
	void setUp() throws Exception {
		address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
		location = new Location(69, "Hoved bygningen", "2.sal", "HB242", address);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int locationID;
		String building;
		String floor;
		String room;
		Address address;
		
		//Act
		locationID = location.getLocationID();
		building = location.getBuilding();
		floor = location.getFloor();
		room = location.getRoom();
		address = location.getAddress();
		
		//Assert
		assertEquals(69, locationID);
		assertEquals("Hoved bygningen", building);
		assertEquals("2.sal", floor);
		assertEquals("HB242", room);
		assertEquals(this.address, address);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newLocationID = 420;
		String newBuilding = "Lagerbygning A";
		String newFloor = "St.";
		String newRoom = "Kontor 2";
		Address newAddress = new Address();
		
		//Act
		location.setLocationID(newLocationID);
		location.setBuilding(newBuilding);
		location.setFloor(newFloor);
		location.setRoom(newRoom);
		location.setAddress(newAddress);
		
		//Assert
		assertEquals(newLocationID, location.getLocationID());
		assertEquals(newBuilding, location.getBuilding());
		assertEquals(newFloor, location.getFloor());
		assertEquals(newRoom, location.getRoom());
		assertEquals(newAddress, location.getAddress());
	}

}
