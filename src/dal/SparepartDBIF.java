package dal;

import model.Sparepart;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Sparepart.
 */

public interface SparepartDBIF {
	
	public Sparepart findSparePartbyID(int sparePartID);
	
}
