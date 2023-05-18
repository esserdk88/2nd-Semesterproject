package dalStubs;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dal.interfaces.AssetDBIF;
import model.Asset;

public class StubAssetDB implements AssetDBIF{

	@Override
	public Asset findAssetByID(int assetID) throws SQLException {
		return (assetID == 1) ? new Asset() : null;
	}

	@Override
	public List<Asset> getAllAssets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Asset buildObjectFromResultset(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
