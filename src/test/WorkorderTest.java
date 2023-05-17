package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Asset;
import model.Employee;
import model.Maintenance;
import model.Measurement;
import model.SparepartUsed;
import model.Supplier;
import model.Workorder;

class WorkorderTest {
	
	private int workOrderID = 42;
	private String title = "Test opgave";
	private String type = "Test";
	private Calendar startDate = Calendar.getInstance();
	private Calendar endDate = Calendar.getInstance();
	private short priority = 6;
	private String description = "Test beskrivelse";
	private boolean finished = false;
	
	private List<SparepartUsed> sparepartsUsed = new ArrayList<>();
	private Asset asset = new Asset();
	private Employee employee = new Employee();
	private List<Measurement> measurements = new ArrayList<>();
	
	private Workorder workorder;

	@BeforeEach
	void setUp() throws Exception {
		workorder = new Maintenance(false, 0, workOrderID, title, type, startDate, endDate, priority, description, finished, sparepartsUsed, asset, employee, measurements);
	}

	@Test
	public void testGetters() {
		
		//Arrange
		int workOrderID;
		String title;
		String type;
		Calendar startDate;
		Calendar endDate;
		short priority;
		String description;
		boolean finished;
		
		List<SparepartUsed> sparepartsUsed;
		Asset asset;
		Employee employee;
		List<Measurement> measurements;
		
		
		//Act
		workOrderID = workorder.getWorkOrderID();
		title = workorder.getTitle();
		type = workorder.getType();
		startDate = workorder.getStartDate();
		endDate = workorder.getEndDate();
		priority = workorder.getPriority();
		description = workorder.getDescription();
		finished = workorder.isFinished();
		sparepartsUsed = workorder.getSparepartsUsed();
		asset = workorder.getAsset();
		employee = workorder.getEmployee();
		measurements = workorder.getMeasurements();
		
		
		//Assert
		assertEquals(this.workOrderID, workOrderID);
		assertEquals(this.title, title);
		assertEquals(this.type, type);
		assertEquals(this.startDate, startDate);
		assertEquals(this.endDate, endDate);
		assertEquals(this.priority, priority);
		assertEquals(this.description, description);
		assertEquals(this.finished, finished);
		assertEquals(this.sparepartsUsed, sparepartsUsed);
		assertEquals(this.asset, asset);
		assertEquals(this.employee, employee);
		assertEquals(this.measurements, measurements);
	}
	
	@Test
	public void testSetters() {
		
		//Arrange
		int newWorkOrderID = 69;
		String newTitle = "Ny Test Opgave";
		String newType = "Ny Type";
		Calendar newStartDate = startDate;
		newStartDate.add(Calendar.DAY_OF_MONTH, 7);
		Calendar newEndDate = endDate;
		newEndDate.add(Calendar.DAY_OF_MONTH, 7);
		short newPriority = 3;
		String newDescription = "Ny Beskrivelse";
		boolean newFinished = true;
		
		List<SparepartUsed> newSparepartsUsed = new ArrayList<>();
		Asset newAsset = new Asset();
		Employee newEmployee = new Employee();
		List<Measurement> newMeasurements = new ArrayList<>();
		
		//Act
		workorder.setWorkOrderID(newWorkOrderID);
		workorder.setTitle(newTitle);
		workorder.setType(newType);
		workorder.setStartDate(newStartDate);
		workorder.setEndDate(newEndDate);
		workorder.setPriority(newPriority);
		workorder.setDescription(newDescription);
		workorder.setFinished(newFinished);
		workorder.setSparepartsUsed(newSparepartsUsed);
		workorder.setAsset(newAsset);
		workorder.setEmployee(newEmployee);
		workorder.setMeasurements(newMeasurements);
		
		//Assert
		assertEquals(newWorkOrderID, workorder.getWorkOrderID());
		assertEquals(newTitle, workorder.getTitle());
		assertEquals(newType, workorder.getType());
		assertEquals(newStartDate, workorder.getStartDate());
		assertEquals(newEndDate, workorder.getEndDate());
		assertEquals(newPriority, workorder.getPriority());
		assertEquals(newDescription, workorder.getDescription());
		assertEquals(newFinished, workorder.isFinished());
		assertEquals(newSparepartsUsed, workorder.getSparepartsUsed());
		assertEquals(newAsset, workorder.getAsset());
		assertEquals(newEmployee, workorder.getEmployee());
		assertEquals(newMeasurements, workorder.getMeasurements());
	}

}
