package Controller;

import model.Repair;

public interface RepairControllerIF {

	public boolean createWorkOrder(Repair repair);
	
	public Repair findWorkOrderByID(int workOrderID);
	
	
}
