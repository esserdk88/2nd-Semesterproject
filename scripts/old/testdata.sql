--Test data
--Must only be run once after database clear!!

use mainDB;
go

--Department
insert into Department (Department_Streetname,Department_Streetnumber,Department_Postalcode,Department_Cityname)
VALUES 
('Eksempelvej', 123, '1234', 'Eksempelby')

--Location
insert into Location (Location_Building,Location_Floor,Location_Room,Department_ID_FK)
VALUES 
('EksempelBygning','TestEtage','PrøveRum',1),
('EksempelBygning2','TestEtage','PrøveRum2',1),
('EksempelBygning','TestEtage','PrøveRum3',1),
('EksempelBygning','TestEtage','PrøveRum4',1),
('EksempelBygning2','TestEtage','PrøveRum5',1)

--Asset
insert into Asset (Asset_Name, Asset_AcquireDate, Asset_Description, Asset_Status, Asset_Manufacturer, Location_ID_FK)
VALUES 
('EksempelEnhed', '2002-11-05', 'test description', 'PrøveStatus','Test fabrikant',1)

--Workorder
insert into Workordre(Workordre_Title,Workordre_StartDate,Workordre_FinishedDate,Workordre_Status,Workordre_Description,Workordre_Priority,Workordre_Notes,Workordre_Type,Workordre_ExternalReference,Workordre_Price,Workordre_Repeatable,Workordre_Frequency,Asset_ID_FK)
VALUES 
('Test title','2023-03-03',null,'PrøveStatus','Test beskrivelse',3,'Prøve notat','Prøve type','Prøve ekstern ref',123.456,1,7,1)

--Measurement
insert into Measurement(Measurement_Type,Measurement_Value,Workordre_ID_FK)
VALUES 
('Temperatur F1', '45',1),
('Temperatur F2', '62',1)

--Employee
insert into Employee(Employee_FirstName,Employee_LastName,Employee_Streetname,Employee_Streetnumber,Employee_Postalcode,Employee_Cityname,Employee_PhoneNumber,Employee_Email,Employee_Number,Employee_Position,Employee_DateOfEmployment)
VALUES 
('Test fornavn','Test efternavn','Testgade',123,1234,'Test by','+4512345678','Test@test.com','987654321','PrøveStilling','2005-10-12')

--Certificate
insert into [Certificate](Certificate_Name,Certificate_IssuanceDate,Certificate_ExpirationDate,Certificate_Number,Certificate_Issurer,Employee_ID_FK)
VALUES 
('Prøve bevis','2001-05-20','2011-05-20','123456789','Test skolen',1)

--Supplier
insert into Supplier(Supplier_Name,Supplier_Streetname,Supplier_Streetnumber,Supplier_Postalcode,Supplier_Cityname,Supplier_PhoneNumber,Supplier_Email,Supplier_ContactPerson)
VALUES 
('Test grosist','Test vej',123,1234,'test by','12312312','prøve@prøve.com','Test kontakt person')

--Sparepart
insert into Sparepart(Sparepart_Name,Sparepart_Amount,Sparepart_Batchnumber,Sparepart_Price,Sparepart_ExpirationDate,Supplier_ID_FK)
VALUES 
('Test reservedel',50,'Test-123456',42.69,null,1),
('Test reservedel2',10,'Test-569854',42.69,'2026-10-7',1)
