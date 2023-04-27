package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Address;
import model.Employee;

class EmployeeTest {
	
	private Address address;
	private Employee employee;
	Calendar startDate = Calendar.getInstance();

	@BeforeEach
	void setUp() throws Exception {
		startDate.set(1988, 8, 11);
		address = new Address(42, "Aalborg SV", "9200", "Sofiendalsvej", "60");
		employee = new Employee(69, "1108881859", startDate, "Elektriker", "Rasmus", "28697610", "rasmus.lyngberg@gmail.com", address);
		
	}

	@Test
	void testGetters() {
		//Arrange
		//MasterData will be tested in its own unitTest
		int employeeID;
		String cprNumber; 
		Calendar startDate;
		String position;
		
		
		//Act
		employeeID = employee.getEmployeeID();
		cprNumber = employee.getCprNumber();
		startDate = employee.getStartDate();
		position = employee.getPosition();
		
		//Assert
		assertEquals(69, employeeID);
		assertEquals("1108881859", cprNumber);
		assertEquals(this.startDate, startDate);
		assertEquals("Elektriker", position);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		//MasterData will be tested in its own unitTest
		int newEmployeeID = 13;
		String newCprNumber = "1412601145";
		Calendar newStartDate = Calendar.getInstance();
		String newPosition = "Bogholder";
		
		//Act
		employee.setEmployeeID(newEmployeeID);
		employee.setCprNumber(newCprNumber);
		employee.setStartDate(newStartDate);
		employee.setPosition(newPosition);
		
		//Assert
		assertEquals(newEmployeeID, employee.getEmployeeID());
		assertEquals(newCprNumber, employee.getCprNumber());
		assertEquals(newStartDate, employee.getStartDate());
		assertEquals(newPosition, employee.getPosition());
	}

}
