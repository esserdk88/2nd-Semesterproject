package dal;

import java.util.List;

import model.Address;

public class AddressDB implements AddressDBIF {

	public static final String SELECT_ADDRESS_BY_ID = "SELECT * FROM Address Where address_id_PK = ?";
	public static final String SELECT_ALL_ADDRESS ="";

	@Override
	public Address findAddressByID(int addressID) {
		
		
		
		return null;
	}

	@Override
	public List<Address> getAllAddress() {
		// TODO Auto-generated method stub
		return null;
	}

}
