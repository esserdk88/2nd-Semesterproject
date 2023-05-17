package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Sparepart;
import model.Supplier;

class SparepartTest {
	
	private int sparepartID = 42;
	private String name = "søm";
	private int stockAmount = 666;
	private double price = 6.9d;
	private Supplier supplier = new Supplier();
	
	private Sparepart sparepart;

	@BeforeEach
	void setUp() throws Exception {
		sparepart = new Sparepart(sparepartID, name, stockAmount, price, supplier);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int sparepartID;
		String name;
		int stockAmount;
		double price;
		Supplier supplier;
		
		
		//Act
		sparepartID = sparepart.getSparepartID();
		name = sparepart.getName();
		stockAmount = sparepart.getStockAmount();
		price = sparepart.getPrice();
		supplier = sparepart.getSupplier();
		
		//Assert
		assertEquals(this.sparepartID, sparepartID);
		assertEquals(this.name, name);
		assertEquals(this.stockAmount, stockAmount);
		assertEquals(this.price, price);
		assertEquals(this.supplier, supplier);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newSparepartID = 42;
		String newName = "Store Søm";
		int newStockAmount = 123;
		double newPrice = 42.0d;
		Supplier newSupplier = new Supplier();
		
		//Act
		sparepart.setSparepartID(newSparepartID);
		sparepart.setName(newName);
		sparepart.setStockAmount(newStockAmount);
		sparepart.setPrice(newPrice);
		sparepart.setSupplier(newSupplier);
		
		//Assert
		assertEquals(newSparepartID, sparepart.getSparepartID());
		assertEquals(newName, sparepart.getName());
		assertEquals(newStockAmount, sparepart.getStockAmount());
		assertEquals(newPrice, sparepart.getPrice());
		assertEquals(newSupplier, sparepart.getSupplier());
	}

}
