package Controller;

import model.Service;

public interface ServiceControllerIF {

	public boolean createWorkOrder(Service service);
	
	public Service findWorkOrderByID(int workOrderID);
	
}
