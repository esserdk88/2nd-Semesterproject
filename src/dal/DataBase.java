package dal;

public class DataBase {
	private static DataBase instance;
	private AddressDBIF addressDataBase;
	private AssetDBIF assetDataBase;
	private EmployeeDBIF employeeDataBase;
	private LocationDBIF locationDataBase;
	private MeasurementDBIF measurementDataBase;
	private ReferenceDBIF referenceDataBase;
	private SparepartDBIF sparepartDataBase;
	private SparepartUsedDBIF sparepartUsedDataBase;
	private SupplierDBIF supplierDataBase;
	private WorkOrderDBIF workOrderDataBase;
	
	private DataBase() {}
	
	public static DataBase getInstance() {
		if(instance == null) {
			instance = new DataBase();
		}
		return instance;
	}
	
	public AddressDBIF getAddressDataBase() {
		return addressDataBase;
	}

	public AssetDBIF getAssetDataBase() {
		return assetDataBase;
	}

	public EmployeeDBIF getEmployeeDataBase() {
		return employeeDataBase;
	}

	public LocationDBIF getLocationDataBase() {
		return locationDataBase;
	}

	public MeasurementDBIF getMeasurementDataBase() {
		return measurementDataBase;
	}

	public ReferenceDBIF getReferenceDataBase() {
		return referenceDataBase;
	}

	public SparepartDBIF getSparepartDataBase() {
		return sparepartDataBase;
	}

	public SparepartUsedDBIF getSparepartUsedDataBase() {
		return sparepartUsedDataBase;
	}

	public SupplierDBIF getSupplierDataBase() {
		return supplierDataBase;
	}

	public WorkOrderDBIF getWorkOrderDataBase() {
		return workOrderDataBase;
	}

	public void setAddressDataBase(AddressDBIF addressDataBase) {
		this.addressDataBase = addressDataBase;
	}

	public void setAssetDataBase(AssetDBIF assetDataBase) {
		this.assetDataBase = assetDataBase;
	}

	public void setEmployeeDataBase(EmployeeDBIF employeeDataBase) {
		this.employeeDataBase = employeeDataBase;
	}

	public void setLocationDataBase(LocationDBIF locationDataBase) {
		this.locationDataBase = locationDataBase;
	}

	public void setMeasurementDataBase(MeasurementDBIF measurementDataBase) {
		this.measurementDataBase = measurementDataBase;
	}

	public void setReferenceDataBase(ReferenceDBIF referenceDataBase) {
		this.referenceDataBase = referenceDataBase;
	}

	public void setSparepartDataBase(SparepartDBIF sparepartDataBase) {
		this.sparepartDataBase = sparepartDataBase;
	}

	public void setSparepartUsedDataBase(SparepartUsedDBIF sparepartUsedDataBase) {
		this.sparepartUsedDataBase = sparepartUsedDataBase;
	}

	public void setSupplierDataBase(SupplierDBIF supplierDataBase) {
		this.supplierDataBase = supplierDataBase;
	}

	public void setWorkOrderDataBase(WorkOrderDBIF workOrderDataBase) {
		this.workOrderDataBase = workOrderDataBase;
	}
}
