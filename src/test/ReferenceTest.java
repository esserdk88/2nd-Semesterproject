package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maintenance;
import model.Reference;
import model.Workorder;

class ReferenceTest {
	
	private String cvr = "12345678";
	private String contact = "Mikkel Hansen";
	
	private Reference reference;

	@BeforeEach
	void setUp() throws Exception {
		reference = new Reference();
		reference.setCvr(cvr);
		reference.setContact(contact);
		
	}

	@Test
	public void testGetters() {
		
		//Arrange
		String cvr;
		String contact;
		
		//Act
		cvr = reference.getCvr();
		contact = reference.getContact();
		
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
		reference.setCvr(newCvr);
		reference.setContact(newContact);
		
		//Assert
		assertEquals(newCvr, reference.getCvr());
		assertEquals(newContact, reference.getContact());
		
	}

}
