package test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dal.interfaces.AssetDBIF;
import dal.interfaces.ReferenceDBIF;
import dao.Database;
import model.*;

public class TestingUtilities {
	
	//Common fields for use 
	private static String title = "Test opgave";
	private static Calendar startDate = Calendar.getInstance();
	private static Calendar endDate = Calendar.getInstance();
	private static short priority = 3;
	private static String description = "Test beskrivelse";
	private static boolean finished = false;
	
	private static List<SparepartUsed> sparepartsUsed = new ArrayList<>();
	
	private static List<Measurement> measurements = new ArrayList<>();
	
	//Maintenance fields
	private static boolean repeated = true;
	private static int intervalDayCount = 7;
	
	//Repair fields
	private static double price = 100.43;
	private static AssetDBIF assetDB = Database.getInstance().getAssetDataBase();
	private static ReferenceDBIF referenceDB = Database.getInstance().getReferenceDataBase();	
	
	private static Reference reference = referenceDB.findReferenceByID(11111111);
	private static Asset asset;
	public static Repair getRepairWorkOrder() throws SQLException {
		setAsset();
		Repair repair = new Repair(price, reference, 0, title, "Repair", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		
		return repair;
	}
	public static Service getServiceWorkOrder() throws SQLException {
		setAsset();
		Service service = new Service(reference, 0, title, "Service", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		
		return service;
	}
	public static Maintenance getMaintenanceWorkOrder() throws SQLException {
		setAsset();
		Maintenance maintenance = new Maintenance(repeated, intervalDayCount, 0, title, "Maintenance", startDate, endDate, priority, description, finished, sparepartsUsed, asset, null, measurements);
		
		return maintenance;
	}
	
	private static void setAsset() throws SQLException {
		if(asset == null) {
			asset = assetDB.findAssetByID(1);
		}
	}
}
