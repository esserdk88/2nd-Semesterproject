CREATE TABLE Reference (
  reference_CVR_PK INT PRIMARY KEY,
  reference_name VARCHAR(255),
  reference_phone VARCHAR(MAX),
  reference_email VARCHAR(MAX),
  reference_contact VARCHAR(MAX),
  reference_address_id_FK INT FOREIGN KEY REFERENCES Address(address_id_PK)
);
