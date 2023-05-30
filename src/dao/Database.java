package dao;

import dal.AddressDB;
import dal.AssetDB;
import dal.EmployeeDB;
import dal.LocationDB;
import dal.MeasurementDB;
import dal.ReferenceDB;
import dal.SparepartDB;
import dal.SparepartUsedDB;
import dal.SupplierDB;
import dal.WorkOrderDB;
import dal.interfaces.AddressDBIF;
import dal.interfaces.AssetDBIF;
import dal.interfaces.EmployeeDBIF;
import dal.interfaces.LocationDBIF;
import dal.interfaces.MeasurementDBIF;
import dal.interfaces.ReferenceDBIF;
import dal.interfaces.SparepartDBIF;
import dal.interfaces.SparepartUsedDBIF;
import dal.interfaces.SupplierDBIF;
import dal.interfaces.WorkOrderDBIF;

/**
 * The Database class is a singleton that provides access to various database interfaces and their
 * implementations.
 */
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

	// This is a private constructor for the `Database` class. It is used to ensure that no other class
	// can create an instance of the `Database` class, as it is intended to be a singleton.
	private Database() {

	}

	/**
	 * This function returns an instance of the Database class, ensuring that only one instance is created
	 * using double-checked locking.
	 * 
	 * @return The method is returning an instance of the Database class.
	 */
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

	/**
	 * This function returns an instance of the AssetDB class, creating it if it doesn't already exist.
	 * 
	 * @return The method is returning an instance of the AssetDBIF interface, which is implemented by the
	 * AssetDB class. The specific instance being returned is the assetDataBase object, which is created
	 * if it does not already exist using double-checked locking to ensure thread safety.
	 */
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

	/**
	 * This function returns an instance of the EmployeeDB class, creating it if it doesn't already exist,
	 * using double-checked locking to ensure thread safety.
	 * 
	 * @return The method is returning an instance of the EmployeeDBIF interface, which is implemented by
	 * the EmployeeDB class. The instance returned is the singleton instance of the EmployeeDB class,
	 * which is created if it does not already exist.
	 */
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

	/**
	 * This function returns an instance of the LocationDB class, creating it if it doesn't already exist.
	 * 
	 * @return The method is returning an instance of the LocationDBIF interface, which is implemented by
	 * the LocationDB class. The instance being returned is the locationDataBase object, which is lazily
	 * initialized using double-checked locking to ensure thread safety.
	 */
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

	/**
	 * This function returns an instance of the MeasurementDB class, creating it if it doesn't already
	 * exist.
	 * 
	 * @return The method is returning an instance of the MeasurementDBIF interface, which is implemented
	 * by the MeasurementDB class. The specific instance being returned is the measurementDataBase object,
	 * which is lazily initialized using double-checked locking to ensure thread safety.
	 */
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

	/**
	 * This function returns an instance of the ReferenceDB class, creating it if it doesn't already
	 * exist, using double-checked locking to ensure thread safety.
	 * 
	 * @return The method is returning an instance of the ReferenceDBIF interface, which is implemented by
	 * the ReferenceDB class. The specific instance being returned is the referenceDataBase object, which
	 * is lazily initialized using double-checked locking to ensure thread safety.
	 */
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

	/**
	 * The function returns an instance of the SparepartDB class, creating it if it doesn't already exist.
	 * 
	 * @return The method is returning an instance of the SparepartDBIF interface, which is implemented by
	 * the SparepartDB class. The instance returned is the singleton instance of the SparepartDB class,
	 * which is created if it does not already exist.
	 */
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

	/**
	 * The function returns an instance of the SparepartUsedDB class, creating it if it doesn't already
	 * exist.
	 * 
	 * @return The method is returning an instance of the class SparepartUsedDBIF, which is the interface
	 * implemented by the SparepartUsedDB class. The instance returned is the singleton instance of the
	 * SparepartUsedDB class, which is created if it does not already exist.
	 */
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

	/**
	 * The function returns an instance of the SupplierDB class, creating it if it doesn't already exist.
	 * 
	 * @return The method is returning an instance of the SupplierDBIF interface, which is implemented by
	 * the SupplierDB class. The instance returned is the singleton instance of the SupplierDB class,
	 * which is created if it does not already exist.
	 */
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

	/**
	 * This function returns an instance of the WorkOrderDB class, creating it if it doesn't already
	 * exist.
	 * 
	 * @return The method is returning an instance of the WorkOrderDBIF interface, which is implemented by
	 * the WorkOrderDB class. The specific instance being returned is the workOrderDataBase object, which
	 * is created if it does not already exist using double-checked locking synchronization.
	 */
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

	/**
	 * This function returns an instance of the AddressDB class, creating it if it doesn't already exist,
	 * using double-checked locking to ensure thread safety.
	 * 
	 * @return The method `getAddressDataBase()` is returning an instance of the `AddressDBIF` interface,
	 * which is implemented by the `AddressDB` class. The instance being returned is stored in the
	 * `addressDataBase` variable, which is lazily initialized using double-checked locking to ensure
	 * thread safety.
	 */
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

	/**
	 * This function sets the asset database in a synchronized manner.
	 * 
	 * @param assetDataBase The parameter "assetDataBase" is an object of type AssetDBIF, which is an
	 * interface for an asset database. The method "setAssetDataBase" sets the value of this object to the
	 * one passed as a parameter. The method is synchronized to ensure thread safety.
	 */
	public void setAssetDataBase(AssetDBIF assetDataBase) {
		synchronized (this) {
			this.assetDataBase = assetDataBase;
		}
	}

	/**
	 * This function sets the employee database in a synchronized manner.
	 * 
	 * @param employeeDataBase The parameter "employeeDataBase" is an object of type "EmployeeDBIF", which
	 * is an interface that defines the methods for accessing and manipulating an employee database. The
	 * method "setEmployeeDataBase" sets the value of the instance variable "employeeDataBase" to the
	 * provided parameter value, while ensuring thread safety
	 */
	public void setEmployeeDataBase(EmployeeDBIF employeeDataBase) {
		synchronized (this) {
			this.employeeDataBase = employeeDataBase;
		}
	}

	/**
	 * This function sets the location database for the object in a synchronized manner.
	 * 
	 * @param locationDataBase It is an object of a class that implements the LocationDBIF interface. This
	 * method sets the locationDataBase object for the current instance of the class in a thread-safe
	 * manner using synchronized block.
	 */
	public void setLocationDataBase(LocationDBIF locationDataBase) {
		synchronized (this) {
			this.locationDataBase = locationDataBase;
		}
	}

	/**
	 * This function sets the measurement database in a synchronized manner.
	 * 
	 * @param measurementDataBase It is a variable of type MeasurementDBIF which is an interface for a
	 * measurement database. This method sets the value of the measurementDataBase variable to the
	 * provided parameter value, while ensuring thread safety using synchronized block.
	 */
	public void setMeasurementDataBase(MeasurementDBIF measurementDataBase) {
		synchronized (this) {
			this.measurementDataBase = measurementDataBase;
		}
	}

	/**
	 * This function sets the reference database in a synchronized manner.
	 * 
	 * @param referenceDataBase It is a reference to an object that implements the ReferenceDBIF
	 * interface. This method sets the reference to the referenceDataBase object in a synchronized block
	 * to ensure thread safety.
	 */
	public void setReferenceDataBase(ReferenceDBIF referenceDataBase) {
		synchronized (this) {
			this.referenceDataBase = referenceDataBase;
		}
	}

	/**
	 * This function sets the sparepart database in a synchronized manner.
	 * 
	 * @param sparepartDataBase This is a parameter of type SparepartDBIF, which is an interface for a
	 * spare parts database. The method setSparepartDataBase sets the value of this parameter to a new
	 * instance of a class that implements the SparepartDBIF interface. The method is synchronized to
	 * ensure thread safety when accessing
	 */
	public void setSparepartDataBase(SparepartDBIF sparepartDataBase) {
		synchronized (this) {
			this.sparepartDataBase = sparepartDataBase;
		}
	}

	/**
	 * This function sets the sparepartUsedDataBase object in a synchronized manner.
	 * 
	 * @param sparepartUsedDataBase This is a parameter of type SparepartUsedDBIF, which is an interface
	 * for a database that stores information about spare parts that have been used. The method
	 * setSparepartUsedDataBase() sets the value of this parameter to a new instance of a class that
	 * implements the SparepartUsedDBIF
	 */
	public void setSparepartUsedDataBase(SparepartUsedDBIF sparepartUsedDataBase) {
		synchronized (this) {
			this.sparepartUsedDataBase = sparepartUsedDataBase;
		}
	}

	/**
	 * This function sets the supplier database in a synchronized manner.
	 * 
	 * @param supplierDataBase This is a variable of type SupplierDBIF which is being set by the method
	 * setSupplierDataBase(). The method is synchronized to ensure that only one thread can access and
	 * modify the variable at a time.
	 */
	public void setSupplierDataBase(SupplierDBIF supplierDataBase) {
		synchronized (this) {
			this.supplierDataBase = supplierDataBase;
		}
	}

	/**
	 * This function sets the work order database in a synchronized manner.
	 * 
	 * @param workOrderDataBase It is a variable of type WorkOrderDBIF, which is an interface for a work
	 * order database. The method setWorkOrderDataBase() sets the value of this variable to the provided
	 * parameter value, while ensuring thread safety using synchronized block.
	 */
	public void setWorkOrderDataBase(WorkOrderDBIF workOrderDataBase) {
		synchronized (this) {
			this.workOrderDataBase = workOrderDataBase;
		}
	}

	/**
	 * This function sets the address database in a synchronized manner.
	 * 
	 * @param addressDataBase It is a variable of type AddressDBIF, which is an interface for an address
	 * database. The method sets the value of this variable to the provided address database object in a
	 * synchronized block to ensure thread safety.
	 */
	public void setAddressDataBase(AddressDBIF addressDataBase) {
		synchronized (this) {
			this.addressDataBase = addressDataBase;
		}
	}
}