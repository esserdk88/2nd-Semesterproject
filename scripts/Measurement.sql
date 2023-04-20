CREATE TABLE Measurement (
  measurement_id_PK INT PRIMARY KEY,
  measurement_type VARCHAR(MAX),
  measurement_value decimal,
  measurement_workorder_id_FK INT FOREIGN KEY REFERENCES Workorder(workorder_id_PK)
);