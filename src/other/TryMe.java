package other;

import java.util.Iterator;

import dal.AddressDB;
import dal.LocationDB;
import model.Address;
import model.Location;

public class TryMe {

	public static void main(String[] args) {
		
		AddressDB aDB = new AddressDB();
		LocationDB locationDB = new LocationDB();
		
		
		for(Location e : locationDB.getAllLocations()) {
			System.out.println(e);
		}
		
//		for(Address e : aDB.getAllAddresses()) {
//			System.out.println(e);
//		}
		
	}
}