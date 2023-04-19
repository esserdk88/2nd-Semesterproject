package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

public class Sparepart {
	
	private String name;
	private int stockAmount;
	private double price;
	private Supplier supplier;
	
	
	public Sparepart(String name, int stockAmount, double price, Supplier supplier) {
		this.name = name;
		this.stockAmount = stockAmount;
		this.price = price;
		this.supplier = supplier;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getStockAmount() {
		return stockAmount;
	}


	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Supplier getSupplier() {
		return supplier;
	}


	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	

}