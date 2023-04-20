package Controller;

import java.security.Provider.Service;

public interface ServiceControllerIF {

	public boolean createWorkOrder(Service service);
	
	public Service findWorkOrderByID(int workOrderID);
	
}
