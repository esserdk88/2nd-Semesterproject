package controller.interfaces;

import model.Maintenance;

public interface MaintenanceControllerIF {
	
	public boolean createWorkOrder(Maintenance maintenance);
	
	public Maintenance findWorkOrderByID(int workOrderID);
	
}
