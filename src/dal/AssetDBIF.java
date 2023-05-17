package dal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Asset;

/*
 * 19-04-2023: Reworked with alignment from communication diagram.
 */

public interface AssetDBIF {

	public Asset findAssetByID(int assetID) throws SQLException;

	public List<Asset> getAllAssets();

	public Asset buildObjectFromResultset(ResultSet rs) throws SQLException;
}