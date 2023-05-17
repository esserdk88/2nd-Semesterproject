Use master;
go

IF DB_ID('mainDB') IS NOT NULL
BEGIN
    ALTER DATABASE mainDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE mainDB;
END
CREATE DATABASE mainDB;
go

Use mainDB;
go

IF OBJECT_ID('Employee', 'U') IS NOT NULL
BEGIN
    DROP TABLE Employee;
END
CREATE TABLE Employee (
    Employee_ID int PRIMARY KEY Identity(1,1),
    Employee_FirstName varchar(50),
    Employee_LastName varchar(50),
    Employee_Streetname varchar(50),
    Employee_Streetnumber int,
    Employee_Postalcode varchar(10),
    Employee_Cityname varchar(50),
    Employee_PhoneNumber varchar(20),
	Employee_Email varchar(50),
	Employee_Number varchar(50),
	Employee_Position varchar(50),
	Employee_DateOfEmployment date,
);

IF OBJECT_ID('Certificate', 'U') IS NOT NULL
BEGIN
    DROP TABLE Certificate;
END
CREATE TABLE Certificate (
    Certificate_ID int PRIMARY KEY Identity(1,1),
    Certificate_Name varchar(50),
    Certificate_IssuanceDate date,
    Certificate_ExpirationDate date,
    Certificate_Number varchar(50),
    Certificate_Issurer varchar(50),
	Employee_ID_FK int,
    FOREIGN KEY (Employee_ID_FK) REFERENCES Employee(Employee_ID)
);

IF OBJECT_ID('Supplier', 'U') IS NOT NULL
BEGIN
    DROP TABLE Supplier;
END
CREATE TABLE Supplier (
    Supplier_ID int PRIMARY KEY Identity(1,1),
    Supplier_Name varchar(50),
    Supplier_Streetname varchar(50),
    Supplier_Streetnumber int,
    Supplier_Postalcode varchar(10),
    Supplier_Cityname varchar(50),
    Supplier_PhoneNumber varchar(20),
	Supplier_Email varchar(50),
	Supplier_ContactPerson varchar(50)
);

IF OBJECT_ID('Sparepart', 'U') IS NOT NULL
BEGIN
    DROP TABLE Sparepart;
END
CREATE TABLE Sparepart (
    Sparepart_ID int PRIMARY KEY Identity(1,1),
    Sparepart_Name varchar(50),
    Sparepart_Amount int,
    Sparepart_Batchnumber varchar(50),
    Sparepart_Price decimal,
	Sparepart_ExpirationDate date,
	Supplier_ID_FK int,
    FOREIGN KEY (Supplier_ID_FK) REFERENCES Supplier(Supplier_ID)
);

IF OBJECT_ID('Department', 'U') IS NOT NULL
BEGIN
    DROP TABLE Department;
END
CREATE TABLE Department (
	Department_ID int PRIMARY KEY Identity(1,1),
    Department_Streetname varchar(50),
    Department_Streetnumber int,
    Department_Postalcode varchar(10),
    Department_Cityname varchar(50)
);

IF OBJECT_ID('Location', 'U') IS NOT NULL
BEGIN
    DROP TABLE Location;
END
CREATE TABLE Location (
	Location_ID int PRIMARY KEY Identity(1,1),
    Location_Building varchar(50),
    Location_Floor varchar(10),
    Location_Room varchar(50),
	Department_ID_FK int,
    FOREIGN KEY (Department_ID_FK) REFERENCES Department(Department_ID)
);

IF OBJECT_ID('Asset', 'U') IS NOT NULL
BEGIN
    DROP TABLE Asset;
END
CREATE TABLE Asset (
	Asset_ID int PRIMARY KEY Identity(1,1),
    Asset_Name varchar(50),
    Asset_AcquireDate date,
    Asset_Description varchar(1000),
	Asset_Status varchar(50),
	Asset_Manufacturer varchar(50),
	Location_ID_FK int,
    FOREIGN KEY (Location_ID_FK) REFERENCES Location(Location_ID)
);

IF OBJECT_ID('Workordre', 'U') IS NOT NULL
BEGIN
    DROP TABLE Workordre;
END
CREATE TABLE Workordre (
	Workordre_ID int PRIMARY KEY Identity(1,1),
    Workordre_Title varchar(50),
    Workordre_StartDate date,
	Workordre_FinishedDate date,
	Workordre_Status varchar(50),
    Workordre_Description varchar(1000),
	Workordre_Priority int,
	Workordre_Notes varchar(1000),
	Workordre_Type varchar(50),
	Workordre_ExternalReference varchar(50),
	Workordre_Price decimal,
	Workordre_Repeatable bit,
	Workordre_Frequency int,
	Asset_ID_FK int,
    FOREIGN KEY (Asset_ID_FK) REFERENCES Asset(Asset_ID)
);

IF OBJECT_ID('Measurement', 'U') IS NOT NULL
BEGIN
    DROP TABLE Measurement;
END
CREATE TABLE Measurement (
	Measurement_ID int PRIMARY KEY Identity(1,1),
    Measurement_Type varchar(50),
	Measurement_Value varchar(50),
	Workordre_ID_FK int,
    FOREIGN KEY (Workordre_ID_FK) REFERENCES Workordre(Workordre_ID)
);

IF OBJECT_ID('Employee_Workordre', 'U') IS NOT NULL
BEGIN
    DROP TABLE Employee_Workordre;
END
CREATE TABLE Employee_Workordre (
	Employee_Workordre_ID int PRIMARY KEY Identity(1,1),
    Employee_ID_FK int,
	Workordre_ID_FK int,
	FOREIGN KEY (Employee_ID_FK) REFERENCES Employee(Employee_ID),
    FOREIGN KEY (Workordre_ID_FK) REFERENCES Workordre(Workordre_ID)
);

IF OBJECT_ID('Sparepart_Workordre', 'U') IS NOT NULL
BEGIN
    DROP TABLE Sparepart_Workordre;
END
CREATE TABLE Sparepart_Workordre (
	Sparepart_Workordre_ID int PRIMARY KEY Identity(1,1),
    Sparepart_ID_FK int,
	Workordre_ID_FK int,
	Sparepart_Workordre_Amount int,
	FOREIGN KEY (Sparepart_ID_FK) REFERENCES Sparepart(Sparepart_ID),
    FOREIGN KEY (Workordre_ID_FK) REFERENCES Workordre(Workordre_ID)
);
