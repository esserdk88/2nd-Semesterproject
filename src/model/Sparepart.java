package model;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

/**
 * The Sparepart class represents a spare part with its ID, name, stock amount, price, and supplier
 * information.
 */
public class Sparepart {

	private int sparepartID;
	private String name;
	private int stockAmount;
	private double price;
	private Supplier supplier;
	
	// The `public Sparepart()` is a default constructor that creates a new instance of the `Sparepart`
	// class with no parameters. It does not contain any code inside the constructor body, as it is left
	// empty.
	public Sparepart() {
		//Left Empty
	}

	// This is a constructor for the `Sparepart` class that takes in five parameters: `sparepartID` (an
	// integer), `name` (a string), `stockAmount` (an integer), `price` (a double), and `supplier` (an
	// instance of the `Supplier` class).
	public Sparepart(int sparepartID, String name, int stockAmount, double price, Supplier supplier) {
		super();
		this.sparepartID = sparepartID;
		this.name = name;
		this.stockAmount = stockAmount;
		this.price = price;
		this.supplier = supplier;
	}



	/**
	 * This function returns the sparepart ID as an integer.
	 * 
	 * @return The method is returning the value of the variable `sparepartID`, which is likely an integer
	 * representing the ID of a spare part.
	 */
	public int getSparepartID() {
		return sparepartID;
	}



	/**
	 * This function sets the ID of a spare part.
	 * 
	 * @param sparepartID The parameter "sparepartID" is an integer value that represents the ID of a
	 * spare part. This method sets the value of the sparepartID instance variable to the value passed as
	 * a parameter.
	 */
	public void setSparepartID(int sparepartID) {
		this.sparepartID = sparepartID;
	}



	/**
	 * The function returns the name.
	 * 
	 * @return The method `getName()` is returning a `String` value, which is the value of the variable
	 * `name`.
	 */
	public String getName() {
		return name;
	}


	/**
	 * This function sets the name of an object.
	 * 
	 * @param name The parameter "name" is a String type variable that represents the name of an object or
	 * entity. The method "setName" takes in a String value and sets it as the name of the object.
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * The function returns the stock amount.
	 * 
	 * @return The method is returning the value of the variable "stockAmount".
	 */
	public int getStockAmount() {
		return stockAmount;
	}


	/**
	 * This function sets the stock amount of a product.
	 * 
	 * @param stockAmount an integer value representing the amount of stock for a particular item. This
	 * method sets the value of the stockAmount variable for an object instance.
	 */
	public void setStockAmount(int stockAmount) {
		this.stockAmount = stockAmount;
	}


	/**
	 * The function returns the price as a double data type.
	 * 
	 * @return The method `getPrice()` is returning a `double` value, which is the `price` of an object.
	 */
	public double getPrice() {
		return price;
	}


	/**
	 * The function sets the price of an object.
	 * 
	 * @param price The parameter "price" is a double data type that represents the price of a product or
	 * service. The method "setPrice" sets the value of the price variable to the value passed as an
	 * argument to the method.
	 */
	public void setPrice(double price) {
		this.price = price;
	}


	/**
	 * The function returns a supplier object.
	 * 
	 * @return A Supplier object is being returned.
	 */
	public Supplier getSupplier() {
		return supplier;
	}


	/**
	 * This function sets the supplier for a given object.
	 * 
	 * @param supplier The parameter "supplier" is an object of the class "Supplier". This method sets the
	 * value of the instance variable "supplier" to the value of the parameter "supplier".
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	/**
	 * This function returns a string representation of a spare part object including its ID, name, stock
	 * amount, price, and supplier information.
	 * 
	 * @return A string representation of a Sparepart object, including its ID, name, stock amount, price,
	 * and supplier information.
	 */
	@Override
	public String toString() {
		return "(" + getSparepartID() + ") " + getName() + " " + getStockAmount() + " " + getPrice() + " " + getSupplier().toString();
	}
}