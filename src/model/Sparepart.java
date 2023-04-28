package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

public class Sparepart {

	private int sparepartID;
	private String name;
	private int stockAmount;
	private double price;
	private Supplier supplier;
	
	public Sparepart() {
		//Left Empty
	}

	public Sparepart(int sparepartID, String name, int stockAmount, double price, Supplier supplier) {
		super();
		this.sparepartID = sparepartID;
		this.name = name;
		this.stockAmount = stockAmount;
		this.price = price;
		this.supplier = supplier;
	}



	public int getSparepartID() {
		return sparepartID;
	}



	public void setSparepartID(int sparepartID) {
		this.sparepartID = sparepartID;
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

	@Override
	public String toString() {
		return "(" + getSparepartID() + ") " + getName() + " " + getStockAmount() + " " + getPrice() + " " + getSupplier().toString();
	}
	
	
	

//	private int sparepartID;
//	private String name;
//	private int stockAmount;
//	private double price;
//	private Supplier supplier;
}