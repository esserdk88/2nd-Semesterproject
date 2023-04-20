package dal;

import model.SparepartUsed;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for SparepartUsed.
 */

public interface SparepartUsedDBIF {
	
	public SparepartUsed findSparePartUsedID(int sparePartUsedID);
	
}
