package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dal.DepartmentDBIF;
import dal.DepartmentDBStub;
import model.Department;

class DepartmentDBTest {
	
	private DepartmentDBStub ddb;

	@BeforeEach
	void setUp() throws Exception {
		ddb = new DepartmentDBStub();
		List<Department> dList = new ArrayList<>();
		
		dList.add(new Department(1, "testvej1", 123, "1234", "testby1"));
		dList.add(new Department(2, "testvej2", 234, "2345", "testby2"));
		dList.add(new Department(3, "testvej3", 345, "3456", "testby3"));
		dList.add(new Department(4, "testvej4", 456, "4567", "testby4"));
		dList.add(new Department(5, "testvej5", 567, "5678", "testby5"));
		
		ddb.setdList(dList);
		ddb.setCurrentID(dList.size());
	}

	@Test
	void testCreateDepartment() {
		//Arrange
		Department newD = new Department(0, "ny testvej", 420, "6969", "ny testby");
		boolean success;
		
		//Act
		success = ddb.createDepartment(newD);
		
		//Assert
		assertTrue(success);
		assertEquals(newD.getStreetName(), ddb.getDepartmentByID(ddb.getCurrentID()).getStreetName());
	}
	
	@Test
	void testGetDepartmentByID() {
		//Arrange
		Department testD = null;
		Department testDAlt = null;
		
		//Act
		testD = ddb.getDepartmentByID(3);
		testDAlt = ddb.getDepartmentByID(99);
		
		//Assert
		assertNull(testDAlt);
		assertNotNull(testD);
		assertEquals(testD.getId(), 3);
		assertEquals(testD.getStreetName(), "testvej3");
		assertEquals(testD.getStreetNumber(), 345);
		assertEquals(testD.getPostalcode(), "3456");
		assertEquals(testD.getCityName(), "testby3");
		
	}
	
	@Test
	void testUpdateDepartment() {
		//Arrange
		Department testD = ddb.getDepartmentByID(3);
		String newStreetName = "ny vej";
		String oldStreetName = testD.getStreetName();
		boolean success = false;
		
		//Act
		testD.setStreetName(newStreetName);
		success = ddb.updateDepartment(testD);
		testD = ddb.getDepartmentByID(testD.getId());
		
		//Assert
		assertTrue(success);
		assertNotNull(testD);
		assertEquals(testD.getId(), 3);
		assertEquals(testD.getStreetName(), newStreetName);
		assertEquals(testD.getStreetNumber(), 345);
		assertEquals(testD.getPostalcode(), "3456");
		assertEquals(testD.getCityName(), "testby3");
	}
	
	@Test
	void testDeleteDepartment() {
		//Arrange
		Department testD = ddb.getDepartmentByID(2);
		boolean first = false;
		boolean second = false;
		
		//Act
		first = ddb.deleteDepartment(testD);
		second = ddb.deleteDepartment(testD);
		testD = ddb.getDepartmentByID(2);
		
		//Assert
		assertTrue(first);
		assertFalse(second);
		assertNull(testD);
	}

}
