package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Location;
import model.Asset;

class AssetTest {
	
	private Address address;
	private Location location;
	private Asset asset;
	Calendar date = Calendar.getInstance();

	@BeforeEach
	void setUp() throws Exception {
		
		date.set(2005, 5, 14);
		address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
		location = new Location(69, "Hoved bygningen", "2.sal", "HB242", address);
		asset = new Asset(123, "Projector", "Projector ved tavle", date, "Virker", "HP", location);
		
		
	}

	@Test
	void testGetters() {
		//Arrange
		int assetID;
		String name;
		String description;
		Calendar aquisitionDate;
		String status;
		String manufacturer;
		Location location;
		
		//Act
		assetID = asset.getAssetID();
		name = asset.getName();
		description = asset.getDescription();
		aquisitionDate = asset.getAquisitionDate();
		status = asset.getStatus();
		manufacturer = asset.getManufacturer();
		location = asset.getLocation();
		
		//Assert
		assertEquals(123, assetID);
		assertEquals("Projector", name);
		assertEquals("Projector ved tavle", description);
		assertEquals(date, aquisitionDate);
		assertEquals("Virker", status);
		assertEquals("HP", manufacturer);
		assertEquals(this.location, location);
		
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newAssetID = 666;
		String newName = "Kontorstol";
		String newDescription = "En stol man sidder på";
		Calendar newAquisitionDate = Calendar.getInstance();
		String newStatus = "Mangler 2 hjul";
		String newManufacturer = "Carl A/S";
		Location newLocation = new Location();
		
		//Act
		asset.setAssetID(newAssetID);
		asset.setName(newName);
		asset.setDescription(newDescription);
		asset.setAquisitionDate(newAquisitionDate);
		asset.setStatus(newStatus);
		asset.setManufacturer(newManufacturer);
		asset.setLocation(newLocation);
		
		//Assert
		assertEquals(newAssetID, asset.getAssetID());
		assertEquals(newName, asset.getName());
		assertEquals(newDescription, asset.getDescription());
		assertEquals(newAquisitionDate, asset.getAquisitionDate());
		assertEquals(newStatus, asset.getStatus());
		assertEquals(newManufacturer, asset.getManufacturer());
		assertEquals(newLocation, asset.getLocation());
	}

}
