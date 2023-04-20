CREATE TABLE Workorder (
  workorder_id_PK INT PRIMARY KEY,
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