package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Reference;
import model.Repair;

class RepairTest {
	
	private double price = 42.0d;
	private Reference reference = new Reference();
	
	private Repair repair;

	@BeforeEach
	void setUp() throws Exception {
		repair = new Repair();
		repair.setPrice(price);
		repair.setReference(reference);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		double price;
		Reference reference;
		
		//Act
		price = repair.getPrice();
		reference = repair.getReference();
		
		//Assert
		assertEquals(this.price, price);
		assertEquals(this.reference, reference);
		
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		double newPrice = 69.0d;
		Reference newReference = new Reference();
		
		//Act
		repair.setPrice(newPrice);
		repair.setReference(newReference);
		
		//Assert
		assertEquals(newPrice, repair.getPrice());
		assertEquals(newReference, repair.getReference());
		
	}

}
