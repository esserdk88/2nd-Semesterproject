package dal;

import java.util.ArrayList;
import java.util.List;

import model.Department;

public class DepartmentDBStub implements DepartmentDBIF {
	
	private List<Department> dList = new ArrayList<>();
	private int currentID = 0;

	@Override
	public boolean createDepartment(Department d) {
		d.setId(nextID());
			
		return dList.add(d);
	}

	@Override
	public Department getDepartmentByID(int id) {
		boolean found = false;
		Department output = null;
		for(int i = 0;i<dList.size() && !found;i++) {
			if(dList.get(i).getId() == id) {
				found = true;
				output = dList.get(i);
			}
		}
		
		return output;
	}

	@Override
	public boolean updateDepartment(Department d) {
		boolean found = false;
		for(int i = 0;i<dList.size() && !found;i++) {
			if(dList.get(i).getId() == d.getId()) {
				found = true;
				dList.set(i, d);
			}
		}
		
		return found;
	}

	@Override
	public boolean deleteDepartment(Department d) {
		
		return dList.remove(d);
	}

	@Override
	public List<Department> getAllDepartments() {
		return dList;
	}

	public List<Department> getdList() {
		return dList;
	}

	public void setdList(List<Department> dList) {
		this.dList = dList;
	}
	
	public int getCurrentID() {
		return currentID;
	}

	public void setCurrentID(int currentID) {
		this.currentID = currentID;
	}

	private int nextID() {
		return ++currentID;
	}

}
