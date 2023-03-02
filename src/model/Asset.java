package model;

import java.util.Calendar;
import java.util.List;

public class Asset {
	
	private String assetID;
	private String name;
	private Calendar dateOfAcquire;
	private String description;
	private AssetStatus status;
	private String manufacturerName;
	
	//Assosiations
	
	private List<Task> tasks;
	private Location location;

}
