package dal;

import model.Reference;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Reference.
 */

public interface ReferenceDBIF {

	public Reference findReferenceByID(int referenceID);
	
}