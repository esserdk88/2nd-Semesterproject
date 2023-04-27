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
	void test() {
		fail("Not yet implemented");
	}

}
