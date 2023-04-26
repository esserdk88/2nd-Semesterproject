package dal;

import dal.Database;

public class Database {
	
    private static volatile Database INSTANCE;
    private AssetDBIF assetDataBase;
	private EmployeeDBIF employeeDataBase;
    private LocationDBIF locationDataBase;
    private MeasurementDBIF measurementDataBase;
    private ReferenceDBIF referenceDataBase;
    private SparepartDBIF sparepartDataBase;
    private SparepartUsedDBIF sparepartUsedDataBase;
    private SupplierDBIF supplierDataBase;
    private WorkOrderDBIF workOrderDataBase;
    private AddressDBIF addressDataBase;

    private Database() {}

    public static Database getInstance() {
        if (INSTANCE == null) {
            synchronized (Database.class) {
                if (INSTANCE == null) {
                    INSTANCE = new Database();
                }
            }
        }
        return INSTANCE;
    }

    public AssetDBIF getAssetDataBase() {
    	if (assetDataBase == null) {
            synchronized (this) {
                if (assetDataBase == null) {
                    assetDataBase = new AssetDB();
                }
            }
        }
        return assetDataBase;
    }
    
    public EmployeeDBIF getEmployeeDataBase() {
    	if (employeeDataBase == null) {
            synchronized (this) {
                if (employeeDataBase == null) {
                	employeeDataBase = new EmployeeDB();
                }
            }
        }
		return employeeDataBase;
	}

	public LocationDBIF getLocationDataBase() {
		if (locationDataBase == null) {
            synchronized (this) {
                if (locationDataBase == null) {
                    locationDataBase = new LocationDB();
                }
            }
        }
		return locationDataBase;
	}

	public MeasurementDBIF getMeasurementDataBase() {
		if (measurementDataBase == null) {
            synchronized (this) {
                if (measurementDataBase == null) {
                    measurementDataBase = new MeasurementDB();
                }
            }
        }
		return measurementDataBase;
	}

	public ReferenceDBIF getReferenceDataBase() {
		if (referenceDataBase == null) {
            synchronized (this) {
                if (referenceDataBase == null) {
                    referenceDataBase = new ReferenceDB();
                }
            }
        }
		return referenceDataBase;
	}

	public SparepartDBIF getSparepartDataBase() {
		if (sparepartDataBase == null) {
            synchronized (this) {
                if (sparepartDataBase == null) {
                    sparepartDataBase = new SparepartDB();
                }
            }
        }
		return sparepartDataBase;
	}

	public SparepartUsedDBIF getSparepartUsedDataBase() {
		if (sparepartUsedDataBase == null) {
            synchronized (this) {
                if (sparepartUsedDataBase == null) {
                    sparepartUsedDataBase = new SparepartUsedDB();
                }
            }
        }
		return sparepartUsedDataBase;
	}

	public SupplierDBIF getSupplierDataBase() {
		if (supplierDataBase == null) {
            synchronized (this) {
                if (supplierDataBase == null) {
                    supplierDataBase = new SupplierDB();
                }
            }
        }
		return supplierDataBase;
	}

	public WorkOrderDBIF getWorkOrderDataBase() {
		if (workOrderDataBase == null) {
            synchronized (this) {
                if (workOrderDataBase == null) {
                    workOrderDataBase = new WorkOrderDB();
                }
            }
        }
		return workOrderDataBase;
	}
	
	public AddressDBIF getAddressDataBase() {
		if (addressDataBase == null) {
            synchronized (this) {
                if (addressDataBase == null) {
                	addressDataBase = new AddressDB();
                }
            }
        }
		return addressDataBase;
	}
	
    public void setAssetDataBase(AssetDBIF assetDataBase) {
        synchronized (this) {
            this.assetDataBase = assetDataBase;
        }
    }
    
    public void setEmployeeDataBase(EmployeeDBIF employeeDataBase) {
    	synchronized (this) {
    		this.employeeDataBase = employeeDataBase;
    	}
	}

	public void setLocationDataBase(LocationDBIF locationDataBase) {
		synchronized (this) {
			this.locationDataBase = locationDataBase;
		}
	}

	public void setMeasurementDataBase(MeasurementDBIF measurementDataBase) {
		synchronized (this) {
			this.measurementDataBase = measurementDataBase;
		}
	}

	public void setReferenceDataBase(ReferenceDBIF referenceDataBase) {
		synchronized (this) {
			this.referenceDataBase = referenceDataBase;
		}
	}

	public void setSparepartDataBase(SparepartDBIF sparepartDataBase) {
		synchronized (this) {
			this.sparepartDataBase = sparepartDataBase;
		}
	}

	public void setSparepartUsedDataBase(SparepartUsedDBIF sparepartUsedDataBase) {
		synchronized (this) {
			this.sparepartUsedDataBase = sparepartUsedDataBase;
		}
	}

	public void setSupplierDataBase(SupplierDBIF supplierDataBase) {
		synchronized (this) {
			this.supplierDataBase = supplierDataBase;
		}
	}

	public void setWorkOrderDataBase(WorkOrderDBIF workOrderDataBase) {
		synchronized (this) {
			this.workOrderDataBase = workOrderDataBase;
		}
	}

	public void setAddressDataBase(AddressDBIF addressDataBase) {
		synchronized (this) {
			this.addressDataBase = addressDataBase;
		}
	}
}