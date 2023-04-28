package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Reference;
import model.Supplier;

class SupplierTest {

	private String cvr = "12345678";
	private String contact = "Mikkel Hansen";
	
	private Supplier supplier;

	@BeforeEach
	void setUp() throws Exception {
		supplier = new Supplier();
		supplier.setCvr(cvr);
		supplier.setContact(contact);
		
	}

	@Test
	public void testGetters() {
		
		//Arrange
		String cvr;
		String contact;
		
		//Act
		cvr = supplier.getCvr();
		contact = supplier.getContact();
		
		//Assert
		assertEquals(this.cvr, cvr);
		assertEquals(this.contact, contact);
		
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		String newCvr = "98765432";
		String newContact = "Jacob Kyed";
		
		//Act
		supplier.setCvr(newCvr);
		supplier.setContact(newContact);
		
		//Assert
		assertEquals(newCvr, supplier.getCvr());
		assertEquals(newContact, supplier.getContact());
		
	}

}
