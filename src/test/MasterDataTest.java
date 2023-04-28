package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Employee;
import model.Maintenance;
import model.MasterData;

class MasterDataTest {

	private MasterData masterData;
	
	private String name = "Rasmus"; 
	private String phone = "28697610";
	private String email = "rasmus.lyngberg@gmail.com";
	private Address address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");

	@BeforeEach
	void setUp() throws Exception {
		masterData = new Employee();
		masterData.setName(name);
		masterData.setPhone(phone);
		masterData.setEmail(email);
		masterData.setAddress(address);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		String name; 
		String phone;
		String email;
		Address address;
		
		
		//Act
		name = masterData.getName();
		phone = masterData.getPhone();
		email = masterData.getEmail();
		address = masterData.getAddress();
		
		//Assert
		assertEquals(this.name, name);
		assertEquals(this.phone, phone);
		assertEquals(this.email, email);
		assertEquals(this.address, address);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		String newName = "Marcus"; 
		String newPhone = "26485978";
		String newEmail = "Marcus@grinder.sut";
		Address newAddress = new Address();
		
		//Act
		masterData.setName(newName);
		masterData.setPhone(newPhone);
		masterData.setEmail(newEmail);
		masterData.setAddress(newAddress);
		
		//Assert
		assertEquals(newName, masterData.getName());
		assertEquals(newPhone, masterData.getPhone());
		assertEquals(newEmail, masterData.getEmail());
		assertEquals(newAddress, masterData.getAddress());
	}
	
}
