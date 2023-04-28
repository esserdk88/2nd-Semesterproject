package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Sparepart;
import model.SparepartUsed;

class SparepartUsedTest {
	
	private int amount = 6;
	private Sparepart sparepart = new Sparepart();
	
	private SparepartUsed sparepartUsed;

	@BeforeEach
	void setUp() throws Exception {
		sparepartUsed = new SparepartUsed(amount, sparepart);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int amount;
		Sparepart sparepart;
		
		
		//Act
		amount = sparepartUsed.getAmount();
		sparepart = sparepartUsed.getSparepart();
		
		//Assert
		assertEquals(this.amount, amount);
		assertEquals(this.sparepart, sparepart);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newAmount = 42;
		Sparepart newSparepart = new Sparepart();
		
		//Act
		sparepartUsed.setAmount(newAmount);
		sparepartUsed.setSparepart(newSparepart);
		
		//Assert
		assertEquals(newAmount, sparepartUsed.getAmount());
		assertEquals(newSparepart, sparepartUsed.getSparepart());
	}

}
