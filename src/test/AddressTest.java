package test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;

class AddressTest {
	
	private Address address;

	@BeforeEach
	void setUp() throws Exception {
		address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int addressID;
		String cityName;
		String zipCode;
		String streetName;
		String streetNumber;
		
		//Act
		addressID = address.getAddressID();
		cityName = address.getCityName();
		zipCode = address.getZipCode();
		streetName = address.getStreetName();
		streetNumber = address.getStreetNumber();
		
		//Assert
		assertEquals(42, addressID);
		assertEquals("Aalborg SV", cityName);
		assertEquals("9200", zipCode);
		assertEquals("Sofiendalsvej", streetName);
		assertEquals("60", streetNumber);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newAddressID = 13;
		String newCityName = "Støvring";
		String newZipCode = "9530";
		String newStreetName = "Jernbanegade";
		String newStreetNumber = "69";
		
		//Act
		address.setAddressID(newAddressID);
		address.setCityName(newCityName);
		address.setZipCode(newZipCode);
		address.setStreetName(newStreetName);
		address.setStreetNumber(newStreetNumber);
		
		//Assert
		assertEquals(newAddressID, address.getAddressID());
		assertEquals(newCityName, address.getCityName());
		assertEquals(newZipCode, address.getZipCode());
		assertEquals(newStreetName, address.getStreetName());
		assertEquals(newStreetNumber, address.getStreetNumber());
	}

}
