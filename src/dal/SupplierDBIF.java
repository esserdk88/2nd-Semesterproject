package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Supplier;

/*
 * 20-04-2023: Rasmus/Mikkel - To get certain tuble in the tabel for Supplier
 */

public interface SupplierDBIF {

	public Supplier findSupplierByID(int supplierID);

	public List<Supplier> getAllSuppliers();

	public Supplier buildObjectFromResultset(ResultSet rs) throws SQLException;
}
