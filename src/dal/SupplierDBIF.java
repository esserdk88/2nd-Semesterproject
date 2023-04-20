package dal;

import model.Supplier;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Supplier
 */

public interface SupplierDBIF {
	
	public Supplier findSupplierByID(int supplierID);
	
}
