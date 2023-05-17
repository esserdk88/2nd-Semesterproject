CREATE TABLE Employee (
  employee_id_PK INT PRIMARY KEY,
  employee_start_date Date,
  employee_cpr VARCHAR(10),
  employee_position VARCHAR(50),
  employee_phone VARCHAR(MAX),
  employee_email VARCHAR(MAX),
  employee_name VARCHAR(MAX),
  employee_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);