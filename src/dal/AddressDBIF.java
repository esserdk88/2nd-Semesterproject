package dal;

import java.util.List;

import model.Address;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Address.
 */

public interface AddressDBIF {
	
	public Address findAddressByID(int addressID);
	
	public List<Address> getAllAddress();
	
}
