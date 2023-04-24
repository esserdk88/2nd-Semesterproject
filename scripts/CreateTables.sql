
DROP TABLE Measurement;
DROP TABLE Workorder;
DROP TABLE Sparepart_Used;
DROP TABLE Sparepart;
DROP TABLE Supplier;
DROP TABLE Reference
DROP TABLE Employee;
DROP TABLE Asset;
DROP TABLE Location;
DROP TABLE Address;


CREATE TABLE Address (
  address_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  address_streetname VARCHAR(255),
  address_streetnumber VARCHAR(50),
  address_cityname VARCHAR(100),
  address_zipcode VARCHAR(20)
);

CREATE TABLE Location (
  location_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  location_building VARCHAR(MAX),
  location_floor VARCHAR(MAX),
  location_room VARCHAR(MAX),
  location_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);

CREATE TABLE Asset (
  asset_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  asset_name VARCHAR(MAX),
  asset_acquisitiondate Date,
  asset_description VARCHAR(MAX),
  asset_status VARCHAR(MAX),
  asset_manufacturer VARCHAR(MAX),
  asset_location_id_FK INT FOREIGN KEY REFERENCES Location(location_id_PK)
);

CREATE TABLE Employee (
  employee_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  employee_start_date Date,
  employee_cpr VARCHAR(10),
  employee_position VARCHAR(50),
  employee_phone VARCHAR(MAX),
  employee_email VARCHAR(MAX),
  employee_name VARCHAR(MAX),
  employee_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);

CREATE TABLE Reference (
  reference_CVR_PK INT PRIMARY KEY,
  reference_name VARCHAR(255),
  reference_phone VARCHAR(MAX),
  reference_email VARCHAR(MAX),
  reference_contact VARCHAR(MAX),
  reference_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);

CREATE TABLE Supplier (
  supplier_CVR_PK INT PRIMARY KEY,
  supplier_name VARCHAR(255),
  supplier_phone VARCHAR(MAX),
  supplier_email VARCHAR(MAX),
  supplier_contact VARCHAR(MAX),
  supplier_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);

CREATE TABLE Sparepart (
  sparepart_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  sparepart_name VARCHAR(MAX),
  sparepart_stock_amount int,
  sparepart_price decimal,
  sparepart_supplier_CVR_FK INT FOREIGN KEY REFERENCES Supplier(supplier_CVR_PK)
);

CREATE TABLE Sparepart_Used (
  sparepart_used_amount int,

  sparepart_used_workorder_id_FK INT,
  sparepart_used_sparepart_id_FK INT,

  CONSTRAINT sparepart_used_id_PK PRIMARY KEY (sparepart_used_workorder_id_FK, sparepart_used_sparepart_id_FK)
);

CREATE TABLE Workorder (
  workorder_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  workorder_title VARCHAR(MAX),
  workorder_type VARCHAR(MAX),
  workorder_startdate Date,
  workorder_enddate Date,
  workorder_priority int,
  workorder_description VARCHAR(MAX),
  workorder_finished bit,
  workorder_interval int,
  workorder_repeatable bit,
  workorder_price decimal,

  workorder_asset_id_FK INT FOREIGN KEY REFERENCES Asset(asset_id_PK),
  workorder_employee_id_FK INT FOREIGN KEY REFERENCES Employee(employee_id_PK),
  workorder_reference_id_FK INT FOREIGN KEY REFERENCES Reference(reference_CVR_PK)
);

CREATE TABLE Measurement (
  measurement_id_PK INT IDENTITY(1,1) PRIMARY KEY,
  measurement_type VARCHAR(MAX),
  measurement_value decimal,
  measurement_workorder_id_FK INT FOREIGN KEY REFERENCES Workorder(workorder_id_PK)
);