package test;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import controller.WorkOrderController;
import dal.WorkOrderDB;
import dao.Database;
import model.Workorder;

class SwitchEmployeeWorkorderTest {
	
	private static WorkOrderController workorderController;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Database.getInstance().setWorkOrderDataBase(new WorkOrderDB());
		workorderController = new WorkOrderController();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	//Threads should finish in the same order they are initialized.
	@Test
	void switchEmployeeWorkorderConcurrencyControltest() {
		//Arrange - Setting up threads
		Thread t1 = new Thread(() -> {
			try {
				System.out.println("Thread " + Thread.currentThread().getId() + "has started");
				
				Workorder firstWorkorder = workorderController.getWorkorderByID(1);
				Workorder secondWorkorder = workorderController.getWorkorderByID(2);
				System.out.println("t1 retrieved workorders");
				
				boolean switched = workorderController.switchEmployeeWorkorders(firstWorkorder, secondWorkorder, true);
				System.out.println(switched);
				
			} catch (Exception e) {
				System.out.println("catch");
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " has switched employee workorders");
		});
		System.out.println("Thread 1 initialized - Thread id = " + t1.getId());
		
		Thread t2 = new Thread(() -> {
			try {
				System.out.println("Thread " + Thread.currentThread().getId() + "has started");
				
				Workorder firstWorkorder = workorderController.getWorkorderByID(1);
				Workorder secondWorkorder = workorderController.getWorkorderByID(2);
				System.out.println("t2 retrieved workorders");
				
				boolean switched = workorderController.switchEmployeeWorkorders(firstWorkorder, secondWorkorder, false);
				
				System.out.println(switched);
			} catch (Exception e) {
				System.out.println("catch");
				e.printStackTrace();
			}
			System.out.println("Thread " + Thread.currentThread().getId() + " has switched employee workorders");
		}); 
		System.out.println("Thread 2 initialized - Thread id = " + t2.getId());
		//Act
		t1.start();
		
		try {
			t1.join();
			//Thread.sleep(1000);
		} catch (InterruptedException e) {
			fail();
			e.printStackTrace();
		}
		t2.start();
		try {
			t2.join();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		//added because main thread program completion might interrupt child thread executions.
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println("done");
		
		//Assert
		
	}

}
