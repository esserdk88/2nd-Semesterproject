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

import controller.AssetController;
import dal.AssetDB;
import dal.interfaces.AssetDBIF;
import dao.Database;
import dao.DatabaseConnection;
import model.Address;
import model.Asset;
import model.Location;

class AssetDBTest {
	
	private static AssetController assetController;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Database.getInstance().setAssetDataBase(new AssetDB());
		assetController = new AssetController();
		DatabaseConnection.setTestingEnvironment();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		DatabaseConnection.getInstance().rollbackTransaction();
	}
	
	@Test
	void getAllAssetsTest() throws SQLException {
		//Arrange
		List<Asset> assetList = new ArrayList<Asset>();
		
		//Act
		assetList = assetController.getAllAssets();
		
		//Assert
		assertNotNull(assetList);
	}
}
