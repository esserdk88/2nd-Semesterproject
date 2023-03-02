package dal;

import java.util.List;

import model.Asset;
import model.Location;

public interface LocationDBIF {
	
	//CRUD
	public boolean createLocation(String building, String floor, String room);
	public Location readLocationByID(int id);
	public Location updateLocation(int id, String building, String floor, String room, Asset asset);
	public boolean deleteLocationByID(int id);
	
	//More
	public List<Location> getAllLocations();
	
}
