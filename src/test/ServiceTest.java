package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Reference;
import model.Service;

class ServiceTest {
	
	private Reference reference = new Reference();
	
	private Service service;

	@BeforeEach
	void setUp() throws Exception {
		service = new Service();
		service.setReference(reference);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		Reference reference;
		
		//Act
		reference = service.getReference();
		
		//Assert
		assertEquals(this.reference, reference);
		
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		Reference newReference = new Reference();
		
		//Act
		service.setReference(newReference);
		
		//Assert
		assertEquals(newReference, service.getReference());
		
	}

}
