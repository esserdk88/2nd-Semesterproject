INSERT INTO Address (address_streetname, address_streetnumber, address_zipcode, address_cityname)
VALUES
('Sofiendalsvej', '60', '9200', 'Aalborg SV'),
('Hobrovej', '73B', '9530', 'Støvring'),
('Blegkildeallé', '45', '9000', 'Aalborg'),
('Lindholm brygge', '21', '9400', 'Nørresundby'),
('Brenning', '15', '9400', 'Nørresundby'),
('Godsbanen', '19 1.6', '9000', 'Aalborg')

INSERT INTO Location (location_building, location_floor, location_room, location_address_id_FK)
VALUES 
('Bygning 3','1.sal','CSD-3.2.11',1),
('Bygning 3','1.sal','CSD-3.2.12',1),
('Bygning 3','1.sal','CSD-3.2.13',1),
('Bygning 3','1.sal','CSD-3.2.14',1),
('Bygning 3','1.sal','CSD-3.2.15',1),
('Bygning 1','ST.','Help desk',1)

INSERT INTO Employee (employee_start_date, employee_cpr, employee_position, employee_phone, employee_email, employee_name, employee_address_id_FK)
VALUES
(GETDATE(), 1234567890, 'Tekniker', '12345678', 'Jakob@gmail.com', 'Jakob', 1),
(GETDATE(), 1111111111, 'Elektriker', '11111111', 'Rasmus@gmail.com', 'Rasmus', 1),
(GETDATE(), 2222222222, 'Mester', '22222222', 'Mikkel@gmail.com', 'Mikkel', 1),
(GETDATE(), 3333333333, 'Tekniker', '33333333', 'Marcus@gmail.com', 'Marcus', 2),
(GETDATE(), 4444444444, 'Mester Svend', '44444444', 'Nikolai@gmail.com', 'Nikolai', 2)

INSERT INTO Reference (reference_CVR_PK, reference_name, reference_phone, reference_email, reference_contact, reference_address_id_FK)
VALUES
(12345678,'Jakob', '12345678', 'Jakob@gmail.com', '12345678', 3),
(11111111,'Lars', '23456789','Lars@gmail.com', '23456789', 4),
(22222222,'Torben', '22222222', 'Torben@gmail.com', '', 4),
(33333333, 'Gianna', '44444444', 'Gianna@gmail.com', '', 5)

INSERT INTO Supplier (supplier_CVR_PK, supplier_name, supplier_phone, supplier_email, supplier_contact, supplier_address_id_FK)
VALUES
(12345678, 'Jem og Fix', '88888888', 'JemOgFix@gmail.com', '', 5),
(98765432, 'Bauhaus', '77777777', 'Bauhaus@gmail.com', 'BauhausForDanishCrown@gmail.com', 4)

INSERT INTO Asset (asset_name, asset_acquisitiondate, asset_description, asset_status, asset_manufacturer, asset_location_id_fk)
VALUES
('El Motor', GETDATE(), 'Sidste Motor til at kører bånd i manuel', 'I brug', 'Thomasnet', 1),
('Palle stabler', GETDATE(), 'Pallestabler nummer 4', 'I brug', 'BT Saxio', 1),
('Palle stabler', GETDATE(), 'Pallestabler nummer 3', 'Til reperation', 'BT Saxio', 1),
('Palle stabler', GETDATE(), 'Pallestabler nummer 2', 'I brug', 'BT Saxio', 2),
('Computer', GETDATE(), 'Computer 6 i terminalen', 'I brug', 'BT Saxio', 2)

INSERT INTO Workorder (workorder_title, workorder_type, workorder_startdate, workorder_enddate, workorder_priority, workorder_description, workorder_finished,
workorder_interval, workorder_repeatable, workorder_price, workorder_asset_id_FK, workorder_employee_id_FK, workorder_reference_id_FK)
VALUES
('Stabler løfter ikke', 'Maintenance', GETDATE(), NULL, 1, 'Description', 0, 5, 1, 10.0, 2, 1, 12345678),
('Stabler virker ikke', 'Repair', GETDATE(), NULL, 2, 'Description', 0, null, 0, 15.1, 3, 2, 12345678),
('Stabler service tjek', 'Service', GETDATE(), NULL, 3, 'Description', 1, null, 0, 200.0, 4, 3, 11111111),
('Computer starter ikke', 'Maintenance', GETDATE(), GETDATE(), 2, 'Description', 1, 5, 1, 50000.0, 5, null, 22222222),
('El Motor tjek', 'Service', GETDATE(), GETDATE(), 2, 'Description', 1, null, 0, 500000.0, 1, null, 33333333)

INSERT INTO Sparepart (sparepart_name, sparepart_stock_amount, sparepart_price, sparepart_supplier_CVR_FK)
VALUES
('Søm 20cm', 420, 6.9, 98765432),
('Søm 4cm', 40, 6, 98765432),
('Skrue 4x5cm', 40, 20, 98765432),
('Kilerem 100x5cm', 5, 400, 12345678),
('Kugleleje 20x2x1', 100, 40, 12345678)

INSERT INTO Sparepart_Used (sparepart_used_amount, sparepart_used_sparepart_id_FK, sparepart_used_workorder_id_FK)
VALUES
(1, 1, 1),
(2, 2, 1),
(3, 3, 1),
(4, 4, 3),
(5, 5, 4)

INSERT INTO Measurement (measurement_type, measurement_value, measurement_workorder_id_FK)
VALUES
('El Måling', 11.5, 1),
('El Måling', 50, 1),
('El Måling', 100, 1),
('Luftfugtighed', 1,1),
('El Måling', 100, 2)