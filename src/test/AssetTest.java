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
