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
import dal.AssetDBIF;
import dal.Database;
import dal.DatabaseConnection;
import model.Address;
import model.Asset;
import model.Location;

class AssetDBTest {
	

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		DatabaseConnection.getInstance().startTransaction();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		DatabaseConnection.getInstance().rollbackTransaction();
	}
	
	@Test
	void getAllAssetsTest() throws SQLException {
		//Arrange
		List<Asset> assetList = new ArrayList<Asset>();
		AssetController assetCtrl = new AssetController(); 
		
		//Act
		assetList = assetCtrl.getAllAssets();
		
		//Assert
		assertNotNull(assetList);
	}
}
