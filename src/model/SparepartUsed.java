package model;

import gui.components.StringArrayConvertible;

/*
 * 19-04-2023: Review done by Rasmus and Mikkel - Align DCD vs. code
 */

public class SparepartUsed implements StringArrayConvertible{
	
	private int amount;
	private Sparepart sparepart;
	
	public SparepartUsed() {
		//Left empty
	}
	
	public SparepartUsed(int amount, Sparepart sparepart) {
		this.amount = amount;
		this.sparepart = sparepart;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public Sparepart getSparepart() {
		return sparepart;
	}


	public void setSparepart(Sparepart sparepart) {
		this.sparepart = sparepart;
	}


	@Override
	public String toString() {
		return sparepart.getName() + " : " + amount;
	}

	@Override
	public String[] getObjectAsStringArray() {
		return new String[] {getSparepart().getName(), String.valueOf(getAmount())};
	}
	
	
	
	
	

}
