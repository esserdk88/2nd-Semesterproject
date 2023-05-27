package model;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

/**
 * The SparepartUsed class represents a spare part used in a repair job, with its amount and name.
 */
public class SparepartUsed implements StringArrayConvertible{
	
	private int amount;
	private Sparepart sparepart;
	
	// The `public SparepartUsed() {}` is a default constructor that creates an instance of the
	// `SparepartUsed` class with no parameters. It is left empty, meaning it does not contain any code or
	// instructions. This constructor can be useful when creating objects of the `SparepartUsed` class
	// without specifying any initial values for its fields.
	public SparepartUsed() {
		//Left empty
	}
	
	// This is a constructor for the `SparepartUsed` class that takes two parameters: an integer `amount`
	// and a `Sparepart` object `sparepart`. It sets the values of the `amount` and `sparepart` fields of
	// the `SparepartUsed` object being created to the values of the corresponding parameters passed to
	// the constructor.
	public SparepartUsed(int amount, Sparepart sparepart) {
		this.amount = amount;
		this.sparepart = sparepart;
	}


	/**
	 * The function returns the value of the "amount" variable.
	 * 
	 * @return The method is returning an integer value which is the value of the variable "amount".
	 */
	public int getAmount() {
		return amount;
	}


	/**
	 * This function sets the value of the "amount" variable to the input integer value.
	 * 
	 * @param amount The parameter "amount" is an integer that is being passed to the method. The method
	 * sets the value of the instance variable "amount" to the value of the parameter.
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}


	/**
	 * The function returns a Sparepart object.
	 * 
	 * @return The method is returning an object of type `Sparepart`.
	 */
	public Sparepart getSparepart() {
		return sparepart;
	}


	/**
	 * This Java function sets a sparepart object.
	 * 
	 * @param sparepart The parameter "sparepart" is an object of the class "Sparepart". The method
	 * "setSparepart" sets the value of the instance variable "sparepart" to the value of the parameter
	 * "sparepart". This is a common practice in object-oriented programming to encaps
	 */
	public void setSparepart(Sparepart sparepart) {
		this.sparepart = sparepart;
	}


	/**
	 * This function returns a string representation of a spare part and its amount.
	 * 
	 * @return The method is returning a string that concatenates the name of a spare part and its amount.
	 */
	@Override
	public String toString() {
		return sparepart.getName() + " : " + amount;
	}

	/**
	 * The function returns an array of strings containing the name of a spare part and its amount.
	 * 
	 * @return A String array containing the name of a spare part and its amount as a String value.
	 */
	@Override
	public String[] getObjectAsStringArray() {
		return new String[] {getSparepart().getName(), String.valueOf(getAmount())};
	}
}
