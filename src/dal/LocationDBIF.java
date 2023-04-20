package dal;

import java.util.List;

import model.Location;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Location.
 */

public interface LocationDBIF {
	
	public Location findLocationByID(int locationID);
	
	public List<Location> getAllLocations();
	
}
