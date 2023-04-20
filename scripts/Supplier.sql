CREATE TABLE Supplier (
  supplier_CVR_PK INT PRIMARY KEY,
  supplier_name VARCHAR(255),
  supplier_phone VARCHAR(MAX),
  supplier_email VARCHAR(MAX),
  supplier_contact VARCHAR(MAX),
  supplier_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);