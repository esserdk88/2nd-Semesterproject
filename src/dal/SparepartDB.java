package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Sparepart;

public class SparepartDB implements SparepartDBIF {
	
	public static final String FIELDS = "sparepart_id_PK, sparepart_name, sparepart_stock_amount, sparepart_price, sparepart_supplier_CVR_FK";
	public static final String SELECT_SPAREPART_BY_ID = "SELECT " + FIELDS + " FROM Sparepart Where sparepart_id_PK = ?";
	public static final String SELECT_ALL_SPAREPARTS = "SELECT " + FIELDS + " FROM Sparepart";

	private SupplierDBIF supplierDB = Database.getInstance().getSupplierDataBase();
	private Connection con = DatabaseConnection.getInstance().getConnection();

	@Override
	public Sparepart findSparePartbyID(int sparePartID) {
		Sparepart sparepart = null;
		
		// establish database connection
		try (PreparedStatement psFindSparepart = con.prepareStatement(SELECT_SPAREPART_BY_ID)) {
			
			//prepare statement
			psFindSparepart.setInt(1, sparePartID);
			
			//execute statement
			ResultSet rs = psFindSparepart.executeQuery();
			
			if (rs != null && rs.next()) {
				//build Sparepart object from result set
				sparepart = buildObject(rs);
			}
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING SPAREPART:" + e.getMessage());
		}
		
		return sparepart;
	}
	public Sparepart buildObjectFromResultset(ResultSet rs) throws SQLException {
		if(rs.getInt("sparepart_id_PK") == 0) {
			return null;
		}else return buildObject(rs);
	}

	private Sparepart buildObject(ResultSet rs) throws SQLException {
		// Create a new Sparepart object
		Sparepart result = new Sparepart();

		// Set the properties of the Sparepart object based on the values in the ResultSet
		result.setSparepartID(rs.getInt("sparepart_id_PK"));
		result.setName(rs.getString("sparepart_name"));
		result.setStockAmount(rs.getInt("sparepart_stock_amount"));
		result.setPrice(rs.getDouble("sparepart_price"));
		result.setSupplier(supplierDB.buildObjectFromResultset(rs));
		
		// return the Sparepart object
		return result;
	}

	@Override
	public List<Sparepart> getAllSpareparts() {
		List<Sparepart> list = new ArrayList<>();
		
		// establish database connection
		try (PreparedStatement psFindSparepart = con.prepareStatement(SELECT_ALL_SPAREPARTS)) {
			
			//prepare statement
			// Left empty. 
			
			//execute statement
			ResultSet rs = psFindSparepart.executeQuery();
			
			if (rs != null) {
				//build Sparepart object from result set
				while(rs.next()) {
					list.add(buildObject(rs));
				}
			} 
		} catch (SQLException e) {
		System.out.println("ERROR FROM RETRIEVING SPAREPART:" + e.getMessage());
		}
		return list;
	}

}
