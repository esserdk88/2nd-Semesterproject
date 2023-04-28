package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Maintenance;

class MaintenanceTest {
	
	private Maintenance maintenance;

	@BeforeEach
	void setUp() throws Exception {
		maintenance = new Maintenance();
		maintenance.setRepeated(true);
		maintenance.setIntervalDayCount(7);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		boolean repeated;
		int intervalDayCount;
		
		
		//Act
		repeated = maintenance.isRepeated();
		intervalDayCount = maintenance.getIntervalDayCount();
		
		//Assert
		assertEquals(true, repeated);
		assertEquals(7, intervalDayCount);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		boolean newRepeated = false;
		int newIntervalDayCount = 30;
		
		//Act
		maintenance.setRepeated(newRepeated);
		maintenance.setIntervalDayCount(newIntervalDayCount);
		
		//Assert
		assertEquals(newRepeated, maintenance.isRepeated());
		assertEquals(newIntervalDayCount, maintenance.getIntervalDayCount());
	}

}
