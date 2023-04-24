package test;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import Controller.AssetController;
import dal.AssetDB;
import model.Address;
import model.Asset;
import model.Location;

class AssetTest {
	
	private static AssetDB assetDatabase;


	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	
	void createNewAssetTest() throws SQLException {
		//Arrange
		assetDatabase = new AssetDB();
		Asset asset = null;
		AssetController assetCtrl = new AssetController(assetDatabase);
		Address address = new Address("Aalborg", "9000", "Godsbanen", "19, 1, 6");
		Location location = new Location(1, "Bygning 3", "1. sal", "CSD-3.2.11", address);
		LocalDate today = LocalDate.now();
		Calendar sqlDate = Calendar.getInstance();
		
		//Act
		asset = assetCtrl.createNewAsset("Vægt", "Vægt til vejning af pålægspakker", sqlDate, "Active", "Gerdmans", location);
		
		//Assert
		assertNotNull(asset);
	}
	
	@Test
	void getAllAssetsTest() throws SQLException {
		//Arrange
		assetDatabase = new AssetDB();
		List<Asset> assetList = new ArrayList<Asset>();
		AssetController assetCtrl = new AssetController(assetDatabase);
		
		//Act
		assetList = assetCtrl.getAllAssets();
		
		//Assert
		assertNotNull(assetList);
	}
}
