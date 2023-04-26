package dal;

import java.util.List;

import model.SparepartUsed;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for SparepartUsed.
 */

public interface SparepartUsedDBIF {
	
	public SparepartUsed findSparePartUsedByWorkorderIDAndSparepartID(int workOrderID, int sparepartID);
	public List<SparepartUsed> findSparepartListByWorkorderID(int workOrderID);
	public List<SparepartUsed> getAllSparepartUsed();
	
}
